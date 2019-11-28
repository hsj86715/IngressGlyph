const String L01 = "L0 ~ L1";
const String L234 = "L2 ~ L4";
const String L56 = "L5 ~ L6";
const String L7 = "L7";
const String L8 = "L8";
const String ALL = "ALL";

int level2Length(String level) {
  if (level == L8) {
    return 5;
  } else if (level == L7) {
    return 4;
  } else if (level == L56) {
    return 3;
  } else if (level == L234) {
    return 2;
  } else if (level == L01) {
    return 1;
  } else {
    return -1;
  }
}

String listToString(List list) {
  String result = "";
  if (list == null || list.isEmpty) {
    return result;
  }
  list.forEach((item) {
    result += item.toString();
    result += ",";
  });
  return result.substring(0, result.length - 1);
}

List<int> pathStringToList(String pathStr) {
  if (pathStr == null || pathStr.trim().length == 0) {
    return null;
  }
  List<String> numbers = pathStr.split(",");
  List<int> path = List();
  numbers.forEach((num) {
    path.add(int.parse(num));
  });
  return path;
}
