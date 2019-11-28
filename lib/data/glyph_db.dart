import 'dart:collection';

import 'package:sqflite/sqflite.dart';
import 'package:sqlcool/sqlcool.dart';
import 'package:path/path.dart';
import 'package:ingress_assistant/data/asset_data.dart';
import 'package:ingress_assistant/utils/tools.dart';

const String TABLE_NAMES = "glyph_name";
const String TABLE_BASE = "glyph_base";
const String TABLE_GLYPH_COST = "glyph_cost"; //version 3 new table
const String TABLE_PATH = "glyph_path";
const String TABLE_SEQUENCE = "sequence_list";
const String TABLE_SEQUENCE_COST = "sequence_cost"; //version 3 new table
const String VIEW_GLYPH = "glyph_view";
const String TABLE_SEARCH = "search_record"; //version 3 new table
const String VIEW_GLYPH_COST = "glyph_cost_view"; //version 3 new view
const String TABLE_EDIT_TEMP = "edit_temp";

Db db = Db();

class _BaseColumns {
  static const String ID = "_id";
  static const String COUNT = "_count";
}

class PractiseColumn {
  static const String ID = _BaseColumns.ID;
  static const String LEARN_COUNT = "learn_count";
  static const String PRACTISE_COUNT = "practise_count";
  static const String PRACTISE_CORRECT = "practise_correct";

  //version 2 added column
  static const String PRACTISE_BEST = "practise_best_time";
}

class NameColumn {
  static const String ID = _BaseColumns.ID;
  static const String NAME = "name";
  static const String ALIAS = "alias";
  static const String ALIAS1 = "alias1";
  static const String ALIAS2 = "alias2";
  static const String ALIAS3 = "alias3";
}

class PathColumn {
  static const String ID = _BaseColumns.ID;
  static const String PATH = "path";
  static const String PATH1 = "path1";
  static const String PATH2 = "path2";
  static const String PATH3 = "path3";
  static const String PATH4 = "path4";
}

class GlyphBaseColumn {
  static const String ID = _BaseColumns.ID;
  static const String NAME_ID = "name_id";
  static const String PATH_ID = "path_id";
  static const String LEARN_COUNT = PractiseColumn.LEARN_COUNT;
  static const String PRACTISE_COUNT = PractiseColumn.PRACTISE_CORRECT;
  static const String PRACTISE_CORRECT = PractiseColumn.PRACTISE_COUNT;

  //version 2 added column
  static const String PRACTISE_BEST = PractiseColumn.PRACTISE_BEST;
}

class GlyphCostColumn {
  static const String ID = _BaseColumns.ID;
  static const String GLYPH_ID = "glyph_id";
  static const String CREATE_TIME = "create_time";
  static const String PRACTISE_COST = "practise_cost";
  static const String CORRECT = "is_correct";
}

class SequencesColumn {
  static const String ID = _BaseColumns.ID;
  static const String HEAD = "head";
  static const String LEVEL = "level";
  static const String FIRST = "first";
  static const String SECOND = "second";
  static const String THIRD = "third";
  static const String FOURTH = "fourth";
  static const String FIFTH = "fifth"; //version 3 new column
  static const String SENTENCE = "sentence";
  static const String LEARN_COUNT = PractiseColumn.LEARN_COUNT;
  static const String PRACTISE_COUNT = PractiseColumn.PRACTISE_COUNT;
  static const String PRACTISE_CORRECT = PractiseColumn.PRACTISE_CORRECT;

  //version 2 added column
  static const String PRACTISE_BEST = PractiseColumn.PRACTISE_BEST;
}

class SequenceCostColumn {
  static const String ID = _BaseColumns.ID;
  static const String SEQUENCE_ID = "sequence_id";
  static const String CREATE_TIME = "create_time";
  static const String PRACTISE_COST = "practise_cost";
  static const String CORRECT = "is_correct";
}

class SearchColumn {
  static const String ID = _BaseColumns.ID;
  static const String KEY_WORD = "key_word";
  static const String CREATE_TIME = "create_time";
}

class EditTempColumn {
  static const String ID = _BaseColumns.ID;
  static const String GLYPH_ID = "glyph_id";
  static const String ALIAS = NameColumn.ALIAS;
  static const String ALIAS1 = NameColumn.ALIAS1;
  static const String ALIAS2 = NameColumn.ALIAS2;
  static const String ALIAS3 = NameColumn.ALIAS3;
  static const String PATH1 = PathColumn.PATH1;
  static const String PATH2 = PathColumn.PATH2;
  static const String PATH3 = PathColumn.PATH3;
  static const String PATH4 = PathColumn.PATH4;
}

class GlyphViewColumn {
  static const String ID = _BaseColumns.ID;
  static const String NAME = NameColumn.NAME;
  static const String ALISA = NameColumn.ALIAS;
  static const String ALISA1 = NameColumn.ALIAS1;
  static const String ALISA2 = NameColumn.ALIAS2;
  static const String ALISA3 = NameColumn.ALIAS3;
  static const String PATH = PathColumn.PATH;
  static const String PATH_ID = GlyphBaseColumn.PATH_ID;
  static const String LEARN_COUNT = PractiseColumn.LEARN_COUNT;
  static const String PRACTISE_COUNT = PractiseColumn.PRACTISE_COUNT;
  static const String PRACTISE_CORRECT = PractiseColumn.PRACTISE_CORRECT;
  static const String PRACTISE_BEST = PractiseColumn.PRACTISE_BEST;
}

class GlyphCostViewColumn extends NameColumn {
  static const String ID = GlyphCostColumn.ID;
  static const String GLYPH_ID = GlyphCostColumn.GLYPH_ID;
  static const String CREATE_TIME = GlyphCostColumn.CREATE_TIME;
  static const String PRACTISE_COST = GlyphCostColumn.PRACTISE_COST;
  static const String CORRECT = GlyphCostColumn.CORRECT;
  static const String NAME = GlyphViewColumn.NAME;
  static const String ALISA = GlyphViewColumn.ALISA;
  static const String ALISA1 = GlyphViewColumn.ALISA1;
  static const String ALISA2 = GlyphViewColumn.ALISA2;
  static const String ALISA3 = GlyphViewColumn.ALISA3;
}

class DBHelper {
  static const int _DB_VERSION = 3; //2019.11.20 upgrade to 3
  static const String _DB_NAME = "glyph_sequence.db";

  Future<Database> createOrOpenDB() async {
    var databasesPath = await getDatabasesPath();
    String path = join(databasesPath, _DB_NAME);
    return databaseFactory.openDatabase(path,
        options: OpenDatabaseOptions(
            version: _DB_VERSION,
            onConfigure: _onDBConfigure,
            onCreate: _onDBCreate,
            onUpgrade: _onDBUpgrade,
            onOpen: _onDBOpen,
            onDowngrade: _onDBDowngrade));
  }

  void _onDBConfigure(Database database) {
    print("DB Configure: $database");
  }

  void _onDBCreate(Database database, int version) {
    print("DB Create: $database, version=$version");
    //建表
    _createGlyphNamesTable(database);
    _createPathTable(database);
    _createGlyphBaseTable(database);
    _createSequencesTable(database);
    _createEditTempTable(database);
    _createGlyphView(database);
    //插入原始数据
    _initBasicData(database);

    _createGlyphCostTable(database);
    _createSequenceCostTable(database);
    _createSearchTable(database);
    _createGlyphCostView(database);
  }

  void _onDBUpgrade(Database database, int oldVersion, int newVersion) {
    print(
        "DB Upgrade: $database, oldVersion=$oldVersion, newVersion=$newVersion");
    switch (oldVersion) {
      case 1: //1升2
        _updateGlyphBaseTable2Version2(database);
        _updateSequencesTable2Version2(database);
        _updateGlyphView(database);
        break;
      case 2: //2升3
        _createGlyphCostTable(database);
        _createSequenceCostTable(database);
        _createSearchTable(database);

        _updateDB2Version3(database);

        _updateGlyphView(database);
        _createGlyphCostView(database);
        break;
      default:
        break;
    }
  }

  void _onDBDowngrade(Database database, int oldVersion, int newVersion) {
    print(
        "DB Downgrade: $database, oldVersion=$oldVersion, newVersion=$newVersion");
  }

  void _onDBOpen(Database database) {
    print("DB Open: $database");
  }

  void _initBasicData(Database database) {
    print("DB insert Basic data start: ${DateTime.now()}");
    _insertGlyphBasicData(database, AssetGlyph.init());
    _insertSequencesData(database, AssetSequences.init());
    print("DB insert Basic data completed: ${DateTime.now()}");
  }

  void _createGlyphNamesTable(Database database) {
    print("Create Glyph Name Table");
    database.execute("CREATE TABLE IF NOT EXISTS $TABLE_NAMES ("
        "${NameColumn.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
        "${NameColumn.NAME} TEXT NOT NULL, "
        "${NameColumn.ALIAS} TEXT, "
        "${NameColumn.ALIAS1} TEXT, "
        "${NameColumn.ALIAS2} TEXT, "
        "${NameColumn.ALIAS3} TEXT)");
  }

  void _createPathTable(Database database) {
    print("Create Path Table");
    database.execute("CREATE TABLE IF NOT EXISTS $TABLE_PATH ("
        "${PathColumn.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
        "${PathColumn.PATH} TEXT NOT NULL, "
        "${PathColumn.PATH1} TEXT, "
        "${PathColumn.PATH2} TEXT, "
        "${PathColumn.PATH3} TEXT, "
        "${PathColumn.PATH4} TEXT)");
  }

  void _createGlyphBaseTable(Database database) {
    print("Create Glyph Base Table");
    database.execute("CREATE TABLE IF NOT EXISTS $TABLE_BASE ("
        "${GlyphBaseColumn.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
        "${GlyphBaseColumn.NAME_ID} INTEGER NOT NULL, "
        "${GlyphBaseColumn.PATH_ID} INTEGER NOT NULL, "
        "${GlyphBaseColumn.LEARN_COUNT} INTEGER DEFAULT 0, "
        "${GlyphBaseColumn.PRACTISE_COUNT} INTEGER DEFAULT 0, "
        "${GlyphBaseColumn.PRACTISE_CORRECT} INTEGER DEFAULT 0, "
        "${GlyphBaseColumn.PRACTISE_BEST} INTEGER)");
  }

  void _createGlyphCostTable(Database database) {
    print("Create Glyph Cost Table");
    database.execute("CREATE TABLE IF NOT EXISTS $TABLE_GLYPH_COST ("
        "${GlyphCostColumn.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
        "${GlyphCostColumn.GLYPH_ID}  INTEGER NOT NULL, "
        "${GlyphCostColumn.PRACTISE_COST}  INTEGER NOT NULL, "
        "${GlyphCostColumn.CORRECT} INTEGER NOT NULL DEFAULT 0, "
        "${GlyphCostColumn.CREATE_TIME} TimeStamp NOT NULL DEFAULT (datetime('now','localtime')) )");
  }

  void _createSequencesTable(Database database) {
    print("Create Sequences Table");
    database.execute("CREATE TABLE IF NOT EXISTS $TABLE_SEQUENCE ("
        "${SequencesColumn.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
        "${SequencesColumn.HEAD} TEXT NOT NULL, "
        "${SequencesColumn.LEVEL} TEXT NOT NULL, "
        "${SequencesColumn.FIRST} TEXT NOT NULL, "
        "${SequencesColumn.SECOND} TEXT, "
        "${SequencesColumn.THIRD} TEXT, "
        "${SequencesColumn.FOURTH} TEXT, "
        "${SequencesColumn.FIFTH} TEXT, "
        "${SequencesColumn.SENTENCE} TEXT, "
        "${SequencesColumn.LEARN_COUNT} INTEGER DEFAULT 0, "
        "${SequencesColumn.PRACTISE_COUNT} INTEGER DEFAULT 0, "
        "${SequencesColumn.PRACTISE_CORRECT} INTEGER DEFAULT 0, "
        "${SequencesColumn.PRACTISE_BEST} INTEGER)");
  }

  void _createSequenceCostTable(Database database) {
    print("Create Sequence Cost Table");
    database.execute("CREATE TABLE IF NOT EXISTS $TABLE_SEQUENCE_COST ("
        "${SequenceCostColumn.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
        "${SequenceCostColumn.SEQUENCE_ID}  INTEGER NOT NULL, "
        "${SequenceCostColumn.PRACTISE_COST}  INTEGER NOT NULL, "
        "${SequenceCostColumn.CORRECT} INTEGER NOT NULL DEFAULT 0, "
        "${SequenceCostColumn.CREATE_TIME} TimeStamp NOT NULL DEFAULT (datetime('now','localtime')) )");
  }

  void _createSearchTable(Database database) {
    print("Create Search Table");
    database.execute("CREATE TABLE IF NOT EXISTS $TABLE_SEARCH ("
        "${SearchColumn.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
        "${SearchColumn.KEY_WORD} TEXT NOT NULL, "
        "${SearchColumn.CREATE_TIME} TimeStamp NOT NULL DEFAULT (datetime('now','localtime')) )");
  }

  void _createEditTempTable(Database database) {
    print("Create Edit temp table");
    database.execute("CREATE TABLE IF NOT EXISTS $TABLE_EDIT_TEMP ("
        "${EditTempColumn.ID} INTEGER PRIMARY KEY AUTOINCREMENT, "
        "${EditTempColumn.GLYPH_ID} INTEGER NOT NULL, "
        "${EditTempColumn.ALIAS} TEXT, "
        "${EditTempColumn.ALIAS1} TEXT, "
        "${EditTempColumn.ALIAS2} TEXT, "
        "${EditTempColumn.ALIAS3} TEXT, "
        "${EditTempColumn.PATH1} TEXT, "
        "${EditTempColumn.PATH2} TEXT, "
        "${EditTempColumn.PATH3} TEXT, "
        "${EditTempColumn.PATH4} TEXT)");
  }

  void _createGlyphView(Database database) {
    print("Create Glyoh View");
    database.execute("CREATE VIEW $VIEW_GLYPH AS SELECT "
        "$TABLE_BASE.${GlyphBaseColumn.ID}, "
        "$TABLE_NAMES.${NameColumn.NAME}, "
        "$TABLE_NAMES.${NameColumn.ALIAS} AS ${GlyphViewColumn.ALISA}, "
        "$TABLE_NAMES.${NameColumn.ALIAS1} AS ${GlyphViewColumn.ALISA1}, "
        "$TABLE_NAMES.${NameColumn.ALIAS2} AS ${GlyphViewColumn.ALISA2}, "
        "$TABLE_NAMES.${NameColumn.ALIAS3} AS ${GlyphViewColumn.ALISA3}, "
        "$TABLE_PATH.${PathColumn.PATH}, "
        "$TABLE_PATH.${PathColumn.ID} AS ${GlyphViewColumn.PATH_ID}, "
        "$TABLE_BASE.${GlyphBaseColumn.LEARN_COUNT}, "
        "$TABLE_BASE.${GlyphBaseColumn.PRACTISE_COUNT}, "
        "$TABLE_BASE.${GlyphBaseColumn.PRACTISE_CORRECT}, "
        "$TABLE_BASE.${GlyphBaseColumn.PRACTISE_BEST} "
        "FROM "
        "$TABLE_BASE, $TABLE_NAMES, $TABLE_PATH "
        "WHERE "
        "$TABLE_BASE.${GlyphBaseColumn.NAME_ID} = $TABLE_NAMES.${NameColumn.ID} "
        "AND "
        "$TABLE_BASE.${GlyphBaseColumn.PATH_ID} = $TABLE_PATH.${PathColumn.ID}");
  }

  void _createGlyphCostView(Database database) {
    print("Create Glyoh Cost View");
    database.execute("CREATE VIEW $VIEW_GLYPH_COST AS SELECT "
        "$TABLE_GLYPH_COST.${GlyphViewColumn.ID}, "
        "$TABLE_GLYPH_COST.${GlyphCostColumn.CORRECT}, "
        "$TABLE_GLYPH_COST.${GlyphCostColumn.GLYPH_ID}, "
        "$TABLE_GLYPH_COST.${GlyphCostColumn.PRACTISE_COST}, "
        "$TABLE_GLYPH_COST.${GlyphCostColumn.CREATE_TIME}, "
        "$VIEW_GLYPH.${GlyphViewColumn.NAME}, "
        "$VIEW_GLYPH.${GlyphViewColumn.ALISA}, "
        "$VIEW_GLYPH.${GlyphViewColumn.ALISA1}, "
        "$VIEW_GLYPH.${GlyphViewColumn.ALISA2}, "
        "$VIEW_GLYPH.${GlyphViewColumn.ALISA3} "
        "FROM "
        "$TABLE_GLYPH_COST, $VIEW_GLYPH "
        "WHERE "
        "$TABLE_GLYPH_COST.${GlyphCostColumn.GLYPH_ID} = $VIEW_GLYPH.${GlyphViewColumn.ID}");
  }

  void _updateGlyphBaseTable2Version2(Database database) {
    print("Update Glyph Base Table to version 2");
    database.execute(
        "ALTER TABLE $TABLE_BASE ADD ${GlyphBaseColumn.PRACTISE_BEST} INTEGER");
  }

  void _updateSequencesTable2Version2(Database database) {
    print("Update Sequences Table to version 2");
    database.execute(
        "ALTER TABLE $TABLE_SEQUENCE ADD ${SequencesColumn.PRACTISE_BEST} INTEGER");
  }

  void _updateDB2Version3(Database database) {
    database.execute("DROP TABLE $TABLE_NAMES");
    _createGlyphNamesTable(database);

    database.execute("DROP TABLE $TABLE_PATH");
    _createPathTable(database);

    database.execute("DROP TABLE $TABLE_BASE");
    _createGlyphBaseTable(database);
    _insertGlyphBasicData(database, AssetGlyph.init());

    database.execute("DROP TABLE $TABLE_SEQUENCE");
    _createSequencesTable(database);
    _insertSequencesData(database, AssetSequences.init());
  }

  void _updateGlyphView(Database database) {
    print("Update Glyph View");
    database.execute("DROP VIEW $VIEW_GLYPH");
    _createGlyphView(database);
  }

  void _insertGlyphBasicData(Database database, AssetGlyph baseGlyph) {
    print("DB insert glyph data: ${DateTime.now()}");

    Map<String, int> nameIds = HashMap();
    int nameId = 0;
    int pathId = 0;

    //获取到原始序列
    Map<String, List<int>> catGlyphs = baseGlyph.baseGlyph;
    catGlyphs.forEach((name, sequence) {
      //拆分多个名字
      List<String> names;
      if (name.contains(",")) {
        names = name.split(",");
      } else {
        names = [name];
      }

      nameId++;
      //插入名称
      switch (names.length) {
        case 1:
          database.execute(
              'INSERT INTO $TABLE_NAMES (${NameColumn.ID}, ${NameColumn.NAME}) '
              'VALUES ($nameId, "${names[0]}")');
          break;
        case 2:
          database.execute(
              'INSERT INTO $TABLE_NAMES (${NameColumn.ID}, ${NameColumn.NAME}, '
              '${NameColumn.ALIAS}) '
              'VALUES ($nameId, "${names[0]}", "${names[1]}")');
          break;
        case 3:
          database.execute(
              'INSERT INTO $TABLE_NAMES (${NameColumn.ID}, ${NameColumn.NAME}, '
              '${NameColumn.ALIAS}, ${NameColumn.ALIAS1}) '
              'VALUES ($nameId, "${names[0]}", "${names[1]}", "${names[2]}")');
          break;
        case 4:
          database.execute(
              'INSERT INTO $TABLE_NAMES (${NameColumn.ID}, ${NameColumn.NAME}, '
              '${NameColumn.ALIAS}, ${NameColumn.ALIAS1}, ${NameColumn.ALIAS2}) '
              'VALUES ($nameId, "${names[0]}", "${names[1]}", "${names[2]}", "${names[3]}")');
          break;
        case 5:
          database.execute(
              'INSERT INTO $TABLE_NAMES (${NameColumn.ID}, ${NameColumn.NAME}, '
              '${NameColumn.ALIAS}, ${NameColumn.ALIAS1}, ${NameColumn.ALIAS2}, '
              '${NameColumn.ALIAS3}) '
              'VALUES ($nameId, "${names[0]}", "${names[1]}", "${names[2]}", '
              '"${names[3]}", "${names[4]}")');
          break;
        default:
          break;
      }
      //缓存name id，后面将会用到
      nameIds[name] = nameId;

      //插入序列路径
      pathId++;
      database.execute(
          "INSERT INTO $TABLE_PATH (${PathColumn.ID}, ${PathColumn.PATH}) "
          "VALUES ($pathId, '${listToString(sequence)}')");

      //封装基础序列
      Map<String, String> glyphRow = HashMap();
      glyphRow[GlyphBaseColumn.NAME_ID] = nameId.toString();
      glyphRow[GlyphBaseColumn.PATH_ID] = pathId.toString();
      //插入基础序列
      database.execute("INSERT INTO $TABLE_BASE ("
          "${GlyphBaseColumn.NAME_ID}, ${GlyphBaseColumn.PATH_ID}) "
          "VALUES ($nameId, $pathId)");
    });
  }

  void _insertSequencesData(Database database, AssetSequences baseGlyph) {
    print("DB insert hacklist data: ${DateTime.now()}");

    List<List<List<String>>> lengthSequences = [
      baseGlyph.l01,
      baseGlyph.l234,
      baseGlyph.l56,
      baseGlyph.l7,
      baseGlyph.l8
    ];
    lengthSequences.forEach((lengthSeqs) {
      //遍历不同长度的序列
      lengthSeqs.forEach((sequences) {
        print("Insert Sequences: $sequences");
        switch (sequences.length) {
          case 1:
            database.execute(
                'INSERT INTO $TABLE_SEQUENCE (${SequencesColumn.HEAD}, ${SequencesColumn.LEVEL}, '
                '${SequencesColumn.FIRST}) '
                'VALUES ("${sequences[0]}", "$L01", "${sequences[0]}")');
            break;
          case 2:
            database.execute(
                'INSERT INTO $TABLE_SEQUENCE (${SequencesColumn.HEAD}, ${SequencesColumn.LEVEL}, '
                '${SequencesColumn.FIRST}, ${SequencesColumn.SECOND}) '
                'VALUES ("${sequences[0]}", "$L234", "${sequences[0]}", '
                '"${sequences[1]}")');
            break;
          case 3:
            database.execute(
                'INSERT INTO $TABLE_SEQUENCE (${SequencesColumn.HEAD}, ${SequencesColumn.LEVEL}, '
                '${SequencesColumn.FIRST}, ${SequencesColumn.SECOND}, ${SequencesColumn.THIRD}) '
                'VALUES ("${sequences[0]}", "$L56", "${sequences[0]}", '
                '"${sequences[1]}", "${sequences[2]}")');
            break;
          case 4:
            database.execute(
                'INSERT INTO $TABLE_SEQUENCE (${SequencesColumn.HEAD}, ${SequencesColumn.LEVEL}, '
                '${SequencesColumn.FIRST}, ${SequencesColumn.SECOND}, '
                '${SequencesColumn.THIRD}, ${SequencesColumn.FOURTH}) '
                'VALUES ("${sequences[0]}", "$L7", "${sequences[0]}", '
                '"${sequences[1]}", "${sequences[2]}", '
                '"${sequences[3]}")');
            break;
          case 5:
            database.execute(
                'INSERT INTO $TABLE_SEQUENCE (${SequencesColumn.HEAD}, ${SequencesColumn.LEVEL}, '
                '${SequencesColumn.FIRST}, ${SequencesColumn.SECOND}, ${SequencesColumn.THIRD}, '
                '${SequencesColumn.FOURTH}, ${SequencesColumn.FIFTH}) '
                'VALUES ("${sequences[0]}", "$L8", "${sequences[0]}", '
                '"${sequences[1]}", "${sequences[2]}", '
                '"${sequences[3]}", "${sequences[4]}")');
            break;
          default:
            break;
        }
      });
    });
  }
}
