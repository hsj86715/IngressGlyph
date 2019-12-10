import '../../bar_chart/bar_chart.dart';
import '../../base/base_chart/base_chart.dart';
import '../../line_chart/line_chart.dart';

/// This class is suitable for axis base charts
/// in the axis base charts we have a grid behind the charts
/// the direct subclasses are [LineChart], [BarChart]
abstract class AxisChart extends BaseChart {}
