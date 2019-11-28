import 'dart:collection';
import 'dart:core';

import 'package:ingress_assistant/data/glyph_db.dart';
import 'package:ingress_assistant/data/glyph_beans.dart';
import 'package:ingress_assistant/utils/tools.dart';
import 'package:sqflite/sqflite.dart';
import 'package:sqlcool/sqlcool.dart';

export 'package:ingress_assistant/data/glyph_beans.dart';

class DataModule {
  final List<String> _hackColumns = [
    SequencesColumn.FIRST,
    SequencesColumn.SECOND,
    SequencesColumn.THIRD,
    SequencesColumn.FOURTH,
    SequencesColumn.FIFTH
  ];

  static DataModule _instance;
  DBHelper _dbHelper;
  Db _db;
  Map<String, GlyphInfo> _glyphMap;

  DataModule._() {
    _dbHelper = DBHelper();
  }

  static DataModule _dataModule() {
    if (_instance == null) {
      _instance = DataModule._();
    }
    return _instance;
  }

  factory DataModule() => _dataModule();

  ///初始化数据库，将原始数据插入数据库中
  Future<int> initBaseData() async {
    await _safeDBInstance();
    return 0;
  }

  ///保证_db对象实例安全
  Future<void> _safeDBInstance() async {
    if (_db == null) {
      Database database = await _dbHelper.createOrOpenDB();
      _db = Db(sqfliteDatabase: database);
    }
  }

  void dispose() {
    _glyphMap.clear();
  }

  ///获取所有的基本图形
  Future<List<GlyphInfo>> getAllGlyphs() async {
    await _safeDBInstance();
    List<GlyphInfo> categoryGlyphs;
    List<Map<String, dynamic>> result = await _db.select(table: VIEW_GLYPH);
    if (result != null && result.isNotEmpty) {
      categoryGlyphs = List();
      result.forEach((subResult) {
        categoryGlyphs.add(_pkgGlyphInfo(subResult));
      });
    }
    categoryGlyphs.sort();
    return categoryGlyphs;
  }

  Future<List<String>> getAllGlyphNames() async {
    await _safeDBInstance();
    List<Map<String, dynamic>> result = await _db.select(table: TABLE_NAMES);
    if (result != null && result.isNotEmpty) {
      List<String> allNames = List();
      result.forEach((rowResult) {
        allNames.add(rowResult[NameColumn.NAME]);
        if (rowResult[NameColumn.ALIAS] != null) {
          allNames.add(rowResult[NameColumn.ALIAS]);
        }
        if (rowResult[NameColumn.ALIAS1] != null) {
          allNames.add(rowResult[NameColumn.ALIAS1]);
        }
        if (rowResult[NameColumn.ALIAS2] != null) {
          allNames.add(rowResult[NameColumn.ALIAS2]);
        }
        if (rowResult[NameColumn.ALIAS3] != null) {
          allNames.add(rowResult[NameColumn.ALIAS3]);
        }
      });
      return allNames;
    }
    return null;
  }

  GlyphInfo _pkgGlyphInfo(Map<String, dynamic> result) {
    GlyphInfo glyphInfo = GlyphInfo();
    glyphInfo.id = result[GlyphViewColumn.ID];
    glyphInfo.name = result[GlyphViewColumn.NAME];
    glyphInfo.alias = result[GlyphViewColumn.ALISA];
    glyphInfo.alias1 = result[GlyphViewColumn.ALISA1];
    glyphInfo.alias2 = result[GlyphViewColumn.ALISA2];
    glyphInfo.alias3 = result[GlyphViewColumn.ALISA3];
    glyphInfo.path = pathStringToList(result[GlyphViewColumn.PATH]);
    glyphInfo.pathId = result[GlyphViewColumn.PATH_ID];
    glyphInfo.learnCount = result[GlyphViewColumn.LEARN_COUNT];
    glyphInfo.practiseCount = result[GlyphViewColumn.PRACTISE_COUNT];
    glyphInfo.practiseCorrect = result[GlyphViewColumn.PRACTISE_CORRECT];
    glyphInfo.practiseBest = result[GlyphViewColumn.PRACTISE_BEST];
    return glyphInfo;
  }

  ///此处封装用户转换同一个Glyph在不同的序列中可以显示不同的名字
  Future<Map<String, GlyphInfo>> _getGlyphMap() async {
    if (_glyphMap == null || _glyphMap.isEmpty) {
      List<Map<String, dynamic>> result = await _db.select(table: VIEW_GLYPH);
      if (result != null && result.isNotEmpty) {
        _glyphMap = Map();
        result.forEach((sunResult) {
          GlyphInfo glyphInfo = _pkgGlyphInfo(sunResult);
          _glyphMap[glyphInfo.name] = glyphInfo;
          if (glyphInfo.alias != null && glyphInfo.alias.isNotEmpty) {
            _glyphMap[glyphInfo.alias] =
                GlyphInfo.copy(glyphInfo, glyphInfo.alias);
          }
          if (glyphInfo.alias1 != null && glyphInfo.name.isNotEmpty) {
            _glyphMap[glyphInfo.alias1] =
                GlyphInfo.copy(glyphInfo, glyphInfo.alias1);
          }
          if (glyphInfo.alias2 != null && glyphInfo.alias2.isNotEmpty) {
            _glyphMap[glyphInfo.alias2] =
                GlyphInfo.copy(glyphInfo, glyphInfo.alias2);
          }
          if (glyphInfo.alias3 != null && glyphInfo.alias3.isNotEmpty) {
            _glyphMap[glyphInfo.alias3] =
                GlyphInfo.copy(glyphInfo, glyphInfo.alias3);
          }
        });
      }
    }
    return _glyphMap;
  }

  ///获取指定等级的入侵序列
  Future<List<HackSequence>> getHackList(String levelOrKeyword) async {
    await _safeDBInstance();

    Map<String, GlyphInfo> glyphInfoMap = await _getGlyphMap();
    List<Map<String, dynamic>> result;
    if (levelOrKeyword != null && levelOrKeyword.isNotEmpty) {
      if (levelOrKeyword == ALL) {
        //返回长度为2以上
        result = await _db.select(
            table: TABLE_SEQUENCE,
            where: "${SequencesColumn.LEVEL}!='$L01'",
            orderBy: "${SequencesColumn.HEAD} ASC");
      } else if (levelOrKeyword == L01 ||
          levelOrKeyword == L234 ||
          levelOrKeyword == L56 ||
          levelOrKeyword == L7 ||
          levelOrKeyword == L8) {
        //返回指定等级
        result = await _db.select(
            table: TABLE_SEQUENCE,
            where: "${SequencesColumn.LEVEL}='$levelOrKeyword'",
            orderBy: "${SequencesColumn.HEAD} ASC");
      } else {
        //关键字检索
        result = await _db.select(
            table: TABLE_SEQUENCE,
            where: '${SequencesColumn.FIRST}="$levelOrKeyword" '
                'OR ${SequencesColumn.SECOND}="$levelOrKeyword" '
                'OR ${SequencesColumn.THIRD}="$levelOrKeyword" '
                'OR ${SequencesColumn.FOURTH}="$levelOrKeyword" '
                'OR ${SequencesColumn.FIFTH}="$levelOrKeyword"',
            orderBy: "${SequencesColumn.HEAD} ASC");
      }
    } else {
      //返回所有
      result = await _db.select(
          table: TABLE_SEQUENCE, orderBy: "${SequencesColumn.HEAD} ASC");
    }

    List<HackSequence> hackLists = List();
    if (result != null && result.isNotEmpty) {
      result.forEach((rowResult) {
        hackLists.add(_pkgHackSequence(rowResult, glyphInfoMap));
      });
    }
//    hackLists.sort();
    return hackLists;
  }

  HackSequence _pkgHackSequence(
      Map<String, dynamic> rowResult, Map<String, GlyphInfo> glyphInfoMap) {
    HackSequence hackList = HackSequence();
    hackList.id = rowResult[SequencesColumn.ID];
    hackList.level = rowResult[SequencesColumn.LEVEL];
    hackList.sentence = rowResult[SequencesColumn.SENTENCE];
    hackList.learnCount = rowResult[SequencesColumn.LEARN_COUNT];
    hackList.practiseCount = rowResult[SequencesColumn.PRACTISE_COUNT];
    hackList.practiseCorrect = rowResult[SequencesColumn.PRACTISE_CORRECT];
    hackList.practiseBest = rowResult[SequencesColumn.PRACTISE_BEST];
    hackList.head = glyphInfoMap[rowResult[SequencesColumn.HEAD]];

    List<GlyphInfo> sequences = List();
    int length = level2Length(hackList.level);
    for (int i = 0; i < length; i++) {
      GlyphInfo item = glyphInfoMap[rowResult[_hackColumns[i]]];
//      print("Add name:${rowResult[_hackColumns[i]]}, Item: $item");
      sequences.add(item);
    }
    hackList.sequences = sequences;
    return hackList;
  }

  Future<GlyphPath> getSequencePaths(int pathId) async {
    await _safeDBInstance();
    GlyphPath path;
    List<Map<String, dynamic>> result = await _db.select(
        table: TABLE_PATH, where: "${PathColumn.ID}=$pathId", limit: 1);
    if (result != null && result.isNotEmpty) {
      path = GlyphPath();
      Map<String, dynamic> rowResult = result[0];
      path.id = rowResult[PathColumn.ID];
      path.path = pathStringToList(rowResult[PathColumn.PATH]);
      path.path1 = pathStringToList(rowResult[PathColumn.PATH1]);
      path.path2 = pathStringToList(rowResult[PathColumn.PATH2]);
      path.path3 = pathStringToList(rowResult[PathColumn.PATH3]);
      path.path4 = pathStringToList(rowResult[PathColumn.PATH4]);
    }
    return path;
  }

  @deprecated
  Future<GlyphName> getSequenceNames(int nameId) async {
    await _safeDBInstance();
    GlyphName name;
    List<Map<String, dynamic>> result = await _db.select(
        table: TABLE_NAMES, where: "${NameColumn.ID}=$nameId", limit: 1);
    if (result != null && result.isNotEmpty) {
      name = GlyphName();
      Map<String, dynamic> rowResult = result[0];
      name.id = rowResult[NameColumn.ID];
      name.name = rowResult[NameColumn.NAME];
      name.alias = rowResult[NameColumn.ALIAS];
      name.alias1 = rowResult[NameColumn.ALIAS1];
      name.alias2 = rowResult[NameColumn.ALIAS2];
      name.alias3 = rowResult[NameColumn.ALIAS3];
    }
    return name;
  }

  @deprecated
  Future<EditTemp> getEditTemp(int glyphId) async {
    await _safeDBInstance();
    EditTemp editTemp;
    List<Map<String, dynamic>> result = await _db.select(
        table: TABLE_EDIT_TEMP,
        where: "${EditTempColumn.GLYPH_ID}=$glyphId",
        limit: 1);
    if (result != null && result.isNotEmpty) {
      editTemp = EditTemp();
      Map<String, dynamic> rowResult = result[0];
      editTemp.id = rowResult[EditTempColumn.ID];
      editTemp.glyphId = rowResult[EditTempColumn.GLYPH_ID];
      editTemp.alias = rowResult[EditTempColumn.ALIAS];
      editTemp.alias1 = rowResult[EditTempColumn.ALIAS1];
      editTemp.alias2 = rowResult[EditTempColumn.ALIAS2];
      editTemp.alias3 = rowResult[EditTempColumn.ALIAS3];
      editTemp.path1 = rowResult[EditTempColumn.PATH1];
      editTemp.path2 = rowResult[EditTempColumn.PATH2];
      editTemp.path3 = rowResult[EditTempColumn.PATH3];
      editTemp.path4 = rowResult[EditTempColumn.PATH4];
    }
    return editTemp;
  }

  @deprecated
  Future<int> insertOrUpdateEditTemp(EditTemp editTemp) async {
    if (editTemp == null) {
      return -1;
    }
    await _safeDBInstance();
    Map<String, String> rowValue = HashMap();
    rowValue[EditTempColumn.ALIAS] = editTemp.alias;
    rowValue[EditTempColumn.ALIAS1] = editTemp.alias1;
    rowValue[EditTempColumn.ALIAS2] = editTemp.alias2;
    rowValue[EditTempColumn.ALIAS3] = editTemp.alias3;
    rowValue[EditTempColumn.PATH1] = editTemp.path1;
    rowValue[EditTempColumn.PATH2] = editTemp.path2;
    rowValue[EditTempColumn.PATH3] = editTemp.path3;
    rowValue[EditTempColumn.PATH4] = editTemp.path4;
    rowValue[EditTempColumn.GLYPH_ID] = editTemp.glyphId.toString();
    if (editTemp.id != null) {
      return await _db.update(
          table: TABLE_EDIT_TEMP,
          row: rowValue,
          where: "${EditTempColumn.GLYPH_ID}=${editTemp.glyphId}");
    } else {
      return await _db.insert(table: TABLE_EDIT_TEMP, row: rowValue);
    }
  }

  Future<int> updateGlyph(LearnAndPractise lap) async {
    return await _updateLAP(TABLE_BASE, lap);
  }

  ///记录Glyph的练习耗时
  Future<int> insertGlyphCost(int glyphId, int cost, bool correct) async {
    await _safeDBInstance();
    Map<String, String> rowValue = HashMap();
    rowValue[GlyphCostColumn.GLYPH_ID] = glyphId.toString();
    rowValue[GlyphCostColumn.PRACTISE_COST] = cost.toString();
    rowValue[GlyphCostColumn.CORRECT] = correct ? '1' : '0';
    return await _db.insert(table: TABLE_GLYPH_COST, row: rowValue);
  }

  Future<int> updateSequence(LearnAndPractise lap) async {
    return await _updateLAP(TABLE_SEQUENCE, lap);
  }

  ///记录Sequence的练习耗时
  Future<int> insertSequenceCost(int seqId, int cost, bool correct) async {
    await _safeDBInstance();
    Map<String, String> rowValue = HashMap();
    rowValue[SequenceCostColumn.SEQUENCE_ID] = seqId.toString();
    rowValue[SequenceCostColumn.PRACTISE_COST] = cost.toString();
    rowValue[SequenceCostColumn.CORRECT] = correct ? '1' : '0';
    return await _db.insert(table: TABLE_SEQUENCE_COST, row: rowValue);
  }

  Future<int> _updateLAP(String tableName, LearnAndPractise lap) async {
    await _safeDBInstance();
    Map<String, String> rowValue = HashMap();
    rowValue[PractiseColumn.LEARN_COUNT] = lap.learnCount.toString();
    rowValue[PractiseColumn.PRACTISE_COUNT] = lap.practiseCount.toString();
    rowValue[PractiseColumn.PRACTISE_CORRECT] = lap.practiseCorrect.toString();
    if (lap.practiseBest != null) {
      //null对象在toString时会存储为`null`字符串，从而导致在查询时无法将数据还原为数字
      rowValue[PractiseColumn.PRACTISE_BEST] = lap.practiseBest.toString();
    }
    return await _db.update(
        table: tableName,
        row: rowValue,
        where: "${PractiseColumn.ID}=${lap.id}");
  }

  ///更新/添加Sequence的助记语
  Future<int> updateSequenceSentence(int seqId, String sentence) async {
    await _safeDBInstance();
    Map<String, String> rowValue = HashMap();
    rowValue[SequencesColumn.SENTENCE] = sentence;
    return await _db.update(
        table: TABLE_SEQUENCE,
        row: rowValue,
        where: "${SequencesColumn.ID} = $seqId");
  }

  ///插入搜索记录
  Future<int> insertSearchRecord(String keyword) async {
    await _safeDBInstance();
    Map<String, String> rowValue = HashMap();
    rowValue[SearchColumn.KEY_WORD] = keyword;
    return await _db.insert(table: TABLE_SEARCH, row: rowValue);
  }

  ///查出最近的N条搜索记录
  Future<List<String>> getLatestSearch({int limit = 10}) async {
    await _safeDBInstance();
    List<Map<String, dynamic>> result = await _db.select(
        table: TABLE_SEARCH,
        groupBy: "${SearchColumn.KEY_WORD}",
        orderBy: "${SearchColumn.CREATE_TIME} DESC",
        limit: limit);
    List<String> records = List();
    if (result != null && result.isNotEmpty) {
      result.forEach((rowResult) {
        records.add(rowResult[SearchColumn.KEY_WORD]);
      });
    }
    return records;
  }

  @deprecated
  Future<int> updateSequenceName(GlyphName name) async {
    if (name == null) {
      return -1;
    }
    await _safeDBInstance();
    Map<String, String> rowValue = HashMap();
    rowValue[NameColumn.ALIAS] = name.alias;
    rowValue[NameColumn.ALIAS1] = name.alias1;
    rowValue[NameColumn.ALIAS2] = name.alias2;
    rowValue[NameColumn.ALIAS3] = name.alias3;
    return await _db.update(
        table: TABLE_NAMES,
        row: rowValue,
        where: "${NameColumn.ID}=${name.id}");
  }

  @deprecated
  Future<int> deleteEditTemp(int glyphId) async {
    await _safeDBInstance();
    return await _db.delete(
        table: TABLE_EDIT_TEMP, where: "${EditTempColumn.GLYPH_ID}=$glyphId");
  }

  ///统计Glyph在所有序列中出现的次数
  Future<List<CountResult>> staticsPopularGlyphs({int limit}) async {
    await _safeDBInstance();
    List<CountResult> countResults = List();
    List<List<Map<String, dynamic>>> resultList = List();
    resultList.add(await _db.select(
        table: TABLE_SEQUENCE,
        columns:
            "COUNT(${SequencesColumn.FIRST}) AS _count, ${SequencesColumn.FIRST} AS _name",
        groupBy: "${SequencesColumn.FIRST}",
        orderBy: "_count DESC"));
    resultList.add(await _db.select(
        table: TABLE_SEQUENCE,
        columns:
            "COUNT(${SequencesColumn.SECOND}) AS _count, ${SequencesColumn.SECOND} AS _name",
        groupBy: "${SequencesColumn.SECOND}",
        orderBy: "_count DESC"));
    resultList.add(await _db.select(
        table: TABLE_SEQUENCE,
        columns:
            "COUNT(${SequencesColumn.THIRD}) AS _count, ${SequencesColumn.THIRD} AS _name",
        groupBy: "${SequencesColumn.THIRD}",
        orderBy: "_count DESC"));
    resultList.add(await _db.select(
        table: TABLE_SEQUENCE,
        columns:
            "COUNT(${SequencesColumn.FOURTH}) AS _count, ${SequencesColumn.FOURTH} AS _name",
        groupBy: "${SequencesColumn.FOURTH}",
        orderBy: "_count DESC"));
    resultList.add(await _db.select(
        table: TABLE_SEQUENCE,
        columns:
            "COUNT(${SequencesColumn.FIFTH}) AS _count, ${SequencesColumn.FIFTH} AS _name",
        groupBy: "${SequencesColumn.FIFTH}",
        orderBy: "_count DESC"));
    resultList.forEach((result) {
      if (countResults.isEmpty) {
        result.forEach((rowResult) {
          CountResult result = CountResult();
          result.name = rowResult['_name'];
          result.count = rowResult['_count'];
          countResults.add(result);
        });
      } else {
        result.forEach((rowResult) {
          bool found = false;
          countResults.forEach((countResult) {
            if (countResult.name == rowResult['_name']) {
              countResult.count += rowResult['_count'];
              found = true;
            }
          });
          if (!found && rowResult['_name'] != null) {
            CountResult result = CountResult();
            result.name = rowResult['_name'];
            result.count = rowResult['_count'];
            countResults.add(result);
          }
        });
      }
    });
    countResults.sort();
    List<CountResult> limitedResult =
        countResults.sublist(countResults.length - limit);
    limitedResult.shuffle();
    return limitedResult;
  }

  ///统计错误次数最多的N个Glyph
  Future<List<CountResult>> staticsGlyphWrongMost({int limit = 10}) async {
    await _safeDBInstance();
    List<Map<String, dynamic>> result = await _db.select(
        table: VIEW_GLYPH_COST,
        columns: "COUNT(${GlyphCostViewColumn.ID}) as _count, "
            "${GlyphCostViewColumn.NAME},${GlyphCostViewColumn.ALISA}",
        where: "${GlyphCostViewColumn.CORRECT} = 0",
        groupBy: "${GlyphCostViewColumn.GLYPH_ID}",
        orderBy: "_count DESC",
        limit: limit);
    List<CountResult> countResults = List();
    if (result != null && result.isNotEmpty) {
      result.forEach((rowResult) {
        CountResult countResult = CountResult();
        countResult.count = rowResult['_count'];
        countResult.name = rowResult[GlyphCostViewColumn.NAME];
//        if (rowResult[GlyphCostViewColumn.ALISA] != null) {
//          countResult.name =
//              '${countResult.name}/${rowResult[GlyphCostViewColumn.ALISA]}';
//        }
        countResults.add(countResult);
      });
    }
    return countResults;
  }

  ///统计绘制速度最慢的N个Glyph
  Future<List<CountResult>> staticsGlyphSlowest({int limit = 10}) async {
    await _safeDBInstance();
    List<Map<String, dynamic>> result = await _db.select(
        table: VIEW_GLYPH,
        columns: "${GlyphViewColumn.NAME},${GlyphViewColumn.ALISA},"
            "${GlyphViewColumn.PRACTISE_BEST}",
        where: "${GlyphViewColumn.PRACTISE_BEST} IS NOT NULL",
        orderBy: "${GlyphViewColumn.PRACTISE_BEST} DESC",
        limit: limit);
    List<CountResult> countResults = List();
    if (result != null && result.isNotEmpty) {
      result.forEach((rowResult) {
        CountResult countResult = CountResult();
        countResult.count = rowResult[GlyphViewColumn.PRACTISE_BEST];
        countResult.name = rowResult[GlyphViewColumn.NAME];
        if (rowResult[GlyphViewColumn.ALISA] != null) {
          countResult.name =
              '${countResult.name}/${rowResult[GlyphViewColumn.ALISA]}';
        }
        countResults.add(countResult);
      });
    }
    return countResults;
  }

  ///统计不同等级Sequence的练习次数
  Future<List<PractiseCountResult>> staticsSequencePractise() async {
    await _safeDBInstance();
    List<Map<String, dynamic>> result = await _db.select(
        table: TABLE_SEQUENCE,
        columns: 'sum(${SequencesColumn.PRACTISE_COUNT}) as level_practise, '
            'sum(${SequencesColumn.PRACTISE_CORRECT}) as practise_correct, '
            '${SequencesColumn.LEVEL}',
        where: '${SequencesColumn.PRACTISE_COUNT} > 0',
        groupBy: SequencesColumn.LEVEL);
    List<PractiseCountResult> countResults = List();
    if (result != null && result.isNotEmpty) {
      result.forEach((rowResult) {
        PractiseCountResult countResult = PractiseCountResult();
        countResult.name = '${rowResult[SequencesColumn.LEVEL]}';
        countResult.count = rowResult['level_practise'];
        countResult.correct = rowResult['practise_correct'];
        countResults.add(countResult);
      });
    }
    return countResults;
  }

  ///统计Glyph最近N次的练习记录，用于在Learn Page上展示前几次的绘制结果
  Future<List<PractiseResult>> staticGlyphHistory(int glyphId,
      {int limit = 10}) async {
    await _safeDBInstance();
    List<Map<String, dynamic>> result = await _db.select(
        table: TABLE_GLYPH_COST,
        where: '${GlyphCostColumn.GLYPH_ID} = $glyphId',
        orderBy: '${GlyphCostColumn.CREATE_TIME} DESC',
        limit: limit);
    if (result.length < 2) {
      //至少有两条记录
      return null;
    }
    List<PractiseResult> countResults = List();
    if (result != null && result.isNotEmpty) {
      result.forEach((rowResult) {
        PractiseResult practise = PractiseResult();
        practise.name = rowResult[GlyphCostColumn.CREATE_TIME];
        practise.count = rowResult[GlyphCostColumn.PRACTISE_COST];
        practise.correct = rowResult[GlyphCostColumn.CORRECT] == 1;
        countResults.add(practise);
      });
    }
    return countResults;
  }

  ///统计Sequence最近N次的练习记录，用于在Practice Page上展示前几次的绘制结果
  Future<List<PractiseResult>> staticSequenceHistory(int seqId,
      {int limit = 20}) async {
    await _safeDBInstance();
    List<Map<String, dynamic>> result = await _db.select(
        table: TABLE_SEQUENCE_COST,
        where: '${SequenceCostColumn.SEQUENCE_ID} = $seqId',
        orderBy: '${SequenceCostColumn.CREATE_TIME} DESC',
        limit: limit);
    if (result.length < 2) {
      //至少有两条记录
      return null;
    }
    List<PractiseResult> countResults = List();
    if (result != null && result.isNotEmpty) {
      result.forEach((rowResult) {
        PractiseResult practise = PractiseResult();
        practise.name = rowResult[SequenceCostColumn.CREATE_TIME];
        practise.count = rowResult[SequenceCostColumn.PRACTISE_COST];
        practise.correct = rowResult[SequenceCostColumn.CORRECT] == 1;
        countResults.add(practise);
      });
    }
    return countResults;
  }
}
