import 'package:ingress_assistant/utils/tools.dart';
import 'dart:core';

class GlyphName {
  int _id;
  String _name;
  String _alias;
  String _alias1;
  String _alias2;
  String _alias3;

  int get id => _id;

  set id(int value) {
    _id = value;
  }

  String get alias => _alias;

  set alias(String value) {
    _alias = value;
  }

  String get alias1 => _alias1;

  set alias1(String value) {
    _alias1 = value;
  }

  String get alias2 => _alias2;

  set alias2(String value) {
    _alias2 = value;
  }

  String get alias3 => _alias3;

  set alias3(String value) {
    _alias3 = value;
  }

  String aliasFull() {
    if (alias == null || alias.trim() == "") {
      return null;
    }
    if (alias1 == null || alias1.trim() == "") {
      return alias;
    }
    if (alias2 == null || alias2.trim() == "") {
      return "$alias/$alias1";
    }
    if (alias3 == null || alias3.trim() == "") {
      return "$alias/$alias1/$alias2";
    }
    return "$alias/$alias1/$alias2/$alias3";
  }

  String get name => _name;

  set name(String value) {
    _name = value;
  }

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GlyphName &&
          runtimeType == other.runtimeType &&
          _id == other._id &&
          _name == other._name &&
          _alias == other._alias &&
          _alias1 == other._alias1 &&
          _alias2 == other._alias2 &&
          _alias3 == other._alias3;

  @override
  int get hashCode =>
      _id.hashCode ^
      _name.hashCode ^
      _alias.hashCode ^
      _alias1.hashCode ^
      _alias2.hashCode ^
      _alias3.hashCode;

  @override
  String toString() {
    return 'GlyphName{id: $_id, name: $_name, alias: $_alias, alias1: $_alias1, '
        'alias2: $_alias2, alias3: $_alias3}';
  }
}

class GlyphPath {
  int _id;
  List<int> _path;
  List<int> _path1;
  List<int> _path2;
  List<int> _path3;
  List<int> _path4;

  int get id => _id;

  set id(int value) {
    _id = value;
  }

  List<int> get path => _path;

  set path(List<int> value) {
    _path = value;
  }

  List<int> get path1 => _path1;

  set path1(List<int> value) {
    _path1 = value;
  }

  List<int> get path2 => _path2;

  set path2(List<int> value) {
    _path2 = value;
  }

  List<int> get path3 => _path3;

  set path3(List<int> value) {
    _path3 = value;
  }

  List<int> get path4 => _path4;

  set path4(List<int> value) {
    _path4 = value;
  }

  @override
  String toString() {
    return 'GlyphPath{id: $_id, path: $_path, path1: $_path1, path2: $_path2, '
        'path3: $_path3, path4: $_path4}';
  }

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GlyphPath &&
          runtimeType == other.runtimeType &&
          _path == other._path &&
          _path1 == other._path1 &&
          _path2 == other._path2 &&
          _path3 == other._path3 &&
          _path4 == other._path4;

  @override
  int get hashCode =>
      _path.hashCode ^
      _path1.hashCode ^
      _path2.hashCode ^
      _path3.hashCode ^
      _path4.hashCode;
}

class LearnAndPractise {
  int _id;
  int _learnCount;
  int _practiseCount;
  int _practiseCorrect;
  int _practiseBest;

  int get id => _id;

  set id(int value) {
    _id = value;
  }

  int get learnCount => _learnCount;

  set learnCount(int value) {
    _learnCount = value;
  }

  int get practiseCount => _practiseCount;

  set practiseCount(int value) {
    _practiseCount = value;
  }

  int get practiseCorrect => _practiseCorrect;

  set practiseCorrect(int value) {
    _practiseCorrect = value;
  }

  int get practiseBest => _practiseBest;

  set practiseBest(int value) {
    _practiseBest = value;
  }
}

class GlyphInfo extends LearnAndPractise with Comparable<GlyphInfo> {
  String _name;
  String _alias;
  String _alias1;
  String _alias2;
  String _alias3;
  List<int> _path;
  int _pathId;

  GlyphInfo();

  GlyphInfo.copy(GlyphInfo info, String showName) {
    _id = info._id;
    _name = showName;
    _path = info._path;
    _pathId = info._pathId;
    _learnCount = info._learnCount;
    _practiseBest = info._practiseBest;
    _practiseCorrect = info._practiseCorrect;
    _practiseCount = info._practiseCount;
  }

  String get name => _name;

  set name(String value) {
    _name = value;
  }

  String get alias => _alias;

  set alias(String value) {
    _alias = value;
  }

  String get alias1 => _alias1;

  set alias1(String value) {
    _alias1 = value;
  }

  String get alias2 => _alias2;

  set alias2(String value) {
    _alias2 = value;
  }

  String get alias3 => _alias3;

  set alias3(String value) {
    _alias3 = value;
  }

  List<int> get path => _path;

  set path(List<int> value) {
    _path = value;
  }

  int get pathId => _pathId;

  set pathId(int value) {
    _pathId = value;
  }

  String aliasFull() {
    if (alias == null || alias.trim() == "") {
      return null;
    }
    if (alias1 == null || alias1.trim() == "") {
      return alias;
    }
    if (alias2 == null || alias2.trim() == "") {
      return "$alias/$alias1";
    }
    if (alias3 == null || alias3.trim() == "") {
      return "$alias/$alias1/$alias2";
    }
    return "$alias/$alias1/$alias2/$alias3";
  }

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is GlyphInfo &&
          runtimeType == other.runtimeType &&
          _name == other._name &&
          _pathId == other._pathId;

  @override
  int get hashCode => _name.hashCode ^ _pathId.hashCode;

  @override
  int compareTo(GlyphInfo other) {
    return _name.compareTo(other._name);
  }

  @override
  String toString() {
    return 'GlyphInfo{id: $_id, name: $_name, path: $_path}';
  }
}

class HackSequence extends LearnAndPractise with Comparable<HackSequence> {
  GlyphInfo _head;
  String _level;
  List<GlyphInfo> _sequences;
  String _sentence;

  GlyphInfo get head => _head;

  set head(GlyphInfo value) {
    _head = value;
  }

  String get level => _level;

  set level(String value) {
    _level = value;
  }

  List<GlyphInfo> get sequences => _sequences;

  set sequences(List<GlyphInfo> value) {
    _sequences = value;
  }

  String get sentence => _sentence;

  set sentence(String value) {
    _sentence = value;
  }

  @override
  String toString() {
    return 'HackSequence{id: $_id, head: $_head, level: $_level, sentence: $_sentence, '
        'sequences: $_sequences, learnCount: $_learnCount, practiseCount: $_practiseCount, '
        'practiseCorrect: $_practiseCorrect, practiseBest: $_practiseBest}';
  }

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is HackSequence &&
          runtimeType == other.runtimeType &&
          _head == other._head &&
          level == other.level &&
          _sequences == other._sequences;

  @override
  int get hashCode => _head.hashCode ^ level.hashCode ^ _sequences.hashCode;

  @override
  int compareTo(HackSequence other) {
    return _head.compareTo(other._head) * 1000 +
        _sequences.length.compareTo(other._sequences.length);
  }
}

class CountResult extends Comparable<CountResult> {
  String _name;
  int _count;

  String get name => _name;

  set name(String value) {
    _name = value;
  }

  int get count => _count;

  set count(int value) {
    _count = value;
  }

  @override
  String toString() {
    return 'CountResult{name: $_name, count: $_count}';
  }

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is CountResult &&
          runtimeType == other.runtimeType &&
          _name == other._name &&
          _count == other._count;

  @override
  int get hashCode => _name.hashCode ^ _count.hashCode;

  @override
  int compareTo(CountResult other) {
    return _count.compareTo(other._count);
  }
}

class PractiseResult extends CountResult {
  bool _correct;

  bool get correct => _correct;

  set correct(bool value) {
    _correct = value;
  }

  @override
  String toString() {
    return 'PractiseResult{name: $_name, count: $_count, correct: $_correct}';
  }
}

class PractiseCountResult extends CountResult {
  int _correct;

  int get correct => _correct;

  set correct(int value) {
    _correct = value;
  }

  @override
  String toString() {
    return 'GlyphPractiseCount{name: $_name, count: $_count, correct: $_correct}';
  }
}

class EditTemp {
  int _id;
  int _glyphId;
  String _alias;
  String _alias1;
  String _alias2;
  String _alias3;
  String _path1;
  String _path2;
  String _path3;
  String _path4;

  int get id => _id;

  set id(int value) {
    _id = value;
  }

  EditTemp();

  EditTemp.fromName(GlyphName name) {
    assert(name != null);
    this._alias = name._alias;
    this._alias1 = name.alias1;
    this._alias2 = name._alias2;
    this._alias3 = name._alias3;
  }

  EditTemp.fromPath(GlyphPath path) {
    assert(path != null);
    this._path1 = listToString(path._path1);
    this._path2 = listToString(path._path2);
    this._path3 = listToString(path._path3);
    this._path4 = listToString(path._path4);
  }

  EditTemp.fromNameAndPath(GlyphName name, GlyphPath path) {
    assert(name != null);
    assert(path != null);
    this._alias = name._alias;
    this._alias1 = name.alias1;
    this._alias2 = name._alias2;
    this._alias3 = name._alias3;
    this._path1 = listToString(path._path1);
    this._path2 = listToString(path._path2);
    this._path3 = listToString(path._path3);
    this._path4 = listToString(path._path4);
  }

  int get glyphId => _glyphId;

  set glyphId(int value) {
    _glyphId = value;
  }

  String get alias => _alias;

  set alias(String value) {
    _alias = value;
  }

  String get alias1 => _alias1;

  set alias1(String value) {
    _alias1 = value;
  }

  String get alias2 => _alias2;

  set alias2(String value) {
    _alias2 = value;
  }

  String get alias3 => _alias3;

  set alias3(String value) {
    _alias3 = value;
  }

  String get path1 => _path1;

  set path1(String value) {
    _path1 = value;
  }

  String get path2 => _path2;

  set path2(String value) {
    _path2 = value;
  }

  String get path3 => _path3;

  set path3(String value) {
    _path3 = value;
  }

  String get path4 => _path4;

  set path4(String value) {
    _path4 = value;
  }

  @override
  String toString() {
    return 'EditTemp{id: $_id, glyphId: $_glyphId, alias: $_alias, alias1: $_alias1, '
        'alias2: $_alias2, alias3: $_alias3, path1: $_path1, path2: $_path2, '
        'path3: $_path3, path4: $_path4}';
  }
}
