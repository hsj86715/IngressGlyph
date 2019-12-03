import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:ingress_assistant/utils/firebase_tools.dart';

class CheatPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _CheatPageState();
  }

  CheatPage() {
//    FireBase().analytics.setCurrentScreen(
//        screenName: "Cheat Page", screenClassOverride: "CheatPage");
  }
}

class _CheatPageState extends State<CheatPage> {
  @override
  Widget build(BuildContext context) {
    return Scrollbar(
        child: SingleChildScrollView(
            child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
          _buildTableHeader("Player"),
          _buildPlayerTable(),
          SizedBox(height: 15),
          _buildTableHeader("Portal"),
          _buildPortalTable(),
          SizedBox(height: 15),
          _buildTableHeader("Link Amp"),
          _buildLinkAmpTable(),
          SizedBox(height: 15),
          _buildTableHeader("Links Mitigation"),
          _buildLinksMitigationTable(),
          SizedBox(height: 15),
          _buildTableHeader("Shield"),
          _buildShieldTable(),
          SizedBox(height: 15),
          _buildTableHeader("Heat Sink"),
          _buildHeatSinkTable(),
          _buildTableFooter("VR,R,C: 300×0.3×0.75×0.9=61s"),
          SizedBox(height: 15),
          _buildTableHeader("Multi-hack"),
          _buildMultiHackTable(),
          _buildTableFooter("VR,R,C: 4+12+4+2=22"),
          SizedBox(height: 15),
          _buildTableHeader("Force Amp"),
          _buildForceAmpTable(),
          SizedBox(height: 15),
          _buildTableHeader("Turret"),
          _buildTurretTable(),
          SizedBox(height: 15),
          _buildTableHeader("Xmp Burster"),
          _buildXmpBursterTable(),
          SizedBox(height: 15),
          _buildTableHeader("Ultra Strike"),
          _buildUltraStrikeTable(),
          SizedBox(height: 15),
          _buildTableHeader("Resonator"),
          _buildResonatorTable(),
          SizedBox(height: 15),
          _buildTableHeader("Access Points"),
          _buildAccessPointsTable(),
          SizedBox(height: 15)
        ])));
  }

  Widget _buildTableHeader(String name) {
    return Container(
        decoration: BoxDecoration(color: Color(0xFF045944)),
        alignment: Alignment.center,
        padding: EdgeInsets.symmetric(vertical: 6),
        child: Text(name, style: TextStyle(color: Colors.white, fontSize: 16)));
  }

  Widget _buildTableFooter(String content) {
    return Container(
        alignment: Alignment.center,
        padding: EdgeInsets.symmetric(vertical: 6),
        child:
            Text(content, style: Theme.of(context).textTheme.body1));
  }

  TableRow _buildTableRow(List<String> rowData,
      {Color defFontColor,
      double fontSize,
      Color rowBgColor,
      Map<String, Color> specialColor}) {
    List<Widget> rowChildren = List();
    rowData.forEach((column) {
      rowChildren.add(Center(
          child: Padding(
              padding: EdgeInsets.symmetric(vertical: 6),
              child: Text(column,
                  style: TextStyle(
                      color: (specialColor == null ||
                              specialColor[column] == null)
                          ? (defFontColor == null ? Theme.of(context).textTheme.display1.color : defFontColor)
                          : specialColor[column],
                      fontSize: fontSize == null ? 14 : fontSize)))));
    });
    return TableRow(
        decoration: BoxDecoration(
            color: rowBgColor == null ? Colors.transparent : rowBgColor),
        children: rowChildren);
  }

  Widget _buildPlayerTable() {
    return Table(
        border: TableBorder.symmetric(
            inside: BorderSide(color: Theme.of(context).dividerColor, width: 0.4)),
        children: [
          //title
          _buildTableRow(["Level", "AP", "Medals", "XM", "Recharge"],
              fontSize: 16),
          //1-4
          _buildTableRow(["1", "0", "", "3,000", "250km"],
              rowBgColor: Color(0x22045944)),
          _buildTableRow(["2", "2,500", "", "4,000", "500km"],
              rowBgColor: Color(0x44045944)),
          _buildTableRow(["3", "20,000", "", "5,000", "750km"],
              rowBgColor: Color(0x66045944)),
          _buildTableRow(["4", "70,000", "", "6,000", "1,000km"],
              rowBgColor: Color(0x88045944)),
          //5-8
          _buildTableRow(["5", "150,000", "", "7,000", "1,250km"],
              rowBgColor: Color(0x22045944)),
          _buildTableRow(["6", "300,000", "", "8,000", "1,500km"],
              rowBgColor: Color(0x44045944)),
          _buildTableRow(["7", "600,000", "", "9,000", "1,750km"],
              rowBgColor: Color(0x66045944)),
          _buildTableRow(["8", "1,200,000", "", "10,000", "2,000km"],
              rowBgColor: Color(0x88045944)),
          //9-12
          _buildTableRow(["9", "240,000", "4S 1G", "11,500", "2,250km"],
              rowBgColor: Color(0x22045944)),
          _buildTableRow(["10", "4,000,000", "5S 2G", "13,000", "2,500km"],
              rowBgColor: Color(0x44045944)),
          _buildTableRow(["11", "6,000,000", "6S 4G", "14,500", "2,750km"],
              rowBgColor: Color(0x66045944)),
          _buildTableRow(["12", "8,400,000", "7S 6G", "16,000", "3,000km"],
              rowBgColor: Color(0x88045944)),
          //13-16
          _buildTableRow(["13", "12,000,000", "7G 1P", "17,500", "3,250km"],
              rowBgColor: Color(0x22045944)),
          _buildTableRow(["14", "17,000,000", "2P", "19,000", "3,500km"],
              rowBgColor: Color(0x44045944)),
          _buildTableRow(["15", "24,000,000", "3P", "20,500", "3,750km"],
              rowBgColor: Color(0x66045944)),
          _buildTableRow(["16", "40,000,000", "4P 2B", "22,000", "4,000km"],
              rowBgColor: Color(0x88045944))
        ]);
  }

  Widget _buildPortalTable() {
    return Table(
        columnWidths: const {
          0: FlexColumnWidth(),
          1: FlexColumnWidth(),
          2: FlexColumnWidth(),
          3: FlexColumnWidth(),
          4: FlexColumnWidth(2)
        },
        border: TableBorder.symmetric(
            inside: BorderSide(color: Theme.of(context).dividerColor, width: 0.4)),
        children: [
          _buildTableRow(["Level", "Link", "Glyphs", "Hack", "ZAP"],
              fontSize: 16),
          _buildTableRow(["1", "0.16km", "1 20s", "50XM", "75XM 40m"],
              rowBgColor: Color(0x44045944),
              specialColor: {"1": Color(0xFFDEFB7C)}),
          _buildTableRow(["2", "2.56km", "2 20s", "100XM", "150XM 45m"],
              specialColor: {"2": Color(0xFFEDBB68)}),
          _buildTableRow(["3", "12.96km", "3 20s", "150XM", "300XM 50m"],
              rowBgColor: Color(0x44045944),
              specialColor: {"3": Color(0xFFDD776D)}),
          _buildTableRow(["4", "40.96km", "3 19s", "200XM", "500XM 55m"],
              specialColor: {"4": Color(0xFFF76372)}),
          _buildTableRow(["5", "100km", "3 18s", "250XM", "750XM 60m"],
              rowBgColor: Color(0x44045944),
              specialColor: {"5": Color(0xFFD15082)}),
          _buildTableRow(["6", "207.36km", "4 17s", "300XM", "1,125XM 65m"],
              specialColor: {"6": Color(0xFFDD50BE)}),
          _buildTableRow(["7", "384.16km", "4 16s", "350XM", "1,625XM 70m"],
              rowBgColor: Color(0x44045944),
              specialColor: {"7": Color(0xFF9C5CC2)}),
          _buildTableRow(["8", "655.36km", "5 15s", "400XM", "2,500XM 75m"],
              specialColor: {"8": Color(0xFF5E47C1)})
        ]);
  }

  Widget _buildLinkAmpTable() {
    return Table(
        columnWidths: const {
          0: FlexColumnWidth(),
          1: FlexColumnWidth(5),
          2: FlexColumnWidth(3),
          3: FlexColumnWidth(4)
        },
        border: TableBorder.symmetric(
            inside: BorderSide(color: Theme.of(context).dividerColor, width: 0.4)),
        children: [
          _buildTableRow(["#", "Type", "Link Range", "Link Mitigation"],
              fontSize: 16),
          //Link Amp
          _buildTableRow(["1", "Link Amp", "×2", ""],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Link Amp": Color(0xFF613BF1)}),
          _buildTableRow(["2", "Link Amp", "×2.5", ""],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Link Amp": Color(0xFF613BF1)}),
          _buildTableRow(["3", "Link Amp", "×2.75", ""],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Link Amp": Color(0xFF613BF1)}),
          _buildTableRow(["4", "Link Amp", "×3", ""],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Link Amp": Color(0xFF613BF1)}),
          //SoftBank Ultra Link
          _buildTableRow(["1", "SoftBank Ultra Link", "×5", "×1.5"],
              rowBgColor: Color(0xFFFCE6F9),
              defFontColor: Colors.black54,
              specialColor: {"SoftBank Ultra Link": Color(0xFFFE50BF)}),
          _buildTableRow(["2", "SoftBank Ultra Link", "×6.25", "×1.625"],
              rowBgColor: Color(0xFFFCE6F9),
              defFontColor: Colors.black54,
              specialColor: {"SoftBank Ultra Link": Color(0xFFFE50BF)}),
          _buildTableRow(["3", "SoftBank Ultra Link", "×6.825", "×1.6875"],
              rowBgColor: Color(0xFFFCE6F9),
              defFontColor: Colors.black54,
              specialColor: {"SoftBank Ultra Link": Color(0xFFFE50BF)}),
          _buildTableRow(["4", "SoftBank Ultra Link", "×7.5", "×1.75"],
              rowBgColor: Color(0xFFFCE6F9),
              defFontColor: Colors.black54,
              specialColor: {"SoftBank Ultra Link": Color(0xFFFE50BF)})
        ]);
  }

  Widget _buildLinksMitigationTable() {
    return Table(
      border: TableBorder.symmetric(
          inside: BorderSide(color: Theme.of(context).dividerColor, width: 0.4)),
      children: [
        _buildTableRow(['0', '1', '2', '3', '4', '5', '6', '7'],
            rowBgColor: Color(0xFFD3F3E2)),
        _buildTableRow(['0', '16', '28', '37', '43', '48', '51', '53']),
        _buildTableRow(['8', '9', '10', '11', '12', '13', '15', '17'],
            rowBgColor: Color(0xFFD3F3E2)),
        _buildTableRow(['55', '57', '58', '59', '60', '61', '62', '63']),
        _buildTableRow(['20', '23', '28', '37', '53', '92', '386',''],
            rowBgColor: Color(0xFFD3F3E2)),
        _buildTableRow(['64', '65', '66', '67', '68', '69', '70',''])
      ],
    );
  }

  Widget _buildShieldTable() {
    return Table(
        border: TableBorder.symmetric(
            inside: BorderSide(color: Theme.of(context).dividerColor, width: 0.4)),
        children: [
          _buildTableRow(["Type", "Mitigation", "Stickiness"], fontSize: 16),
          _buildTableRow(["Common", "30", "0"],
              rowBgColor: Color(0xFFD3F3E2),
              defFontColor: Colors.black54,
              specialColor: {"Common": Color(0xFF35975B)}),
          _buildTableRow(["Rare", "40", "150,000"],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Rare": Color(0xFF613BF1)}),
          _buildTableRow(["Very Rare", "60", "450,000"],
              rowBgColor: Color(0xFFFCE6F9),
              defFontColor: Colors.black54,
              specialColor: {"Very Rare": Color(0xFFFE50BF)}),
          _buildTableRow(["AXA", "70", "800,000"],
              rowBgColor: Color(0xFFFCE6F9),
              defFontColor: Colors.black54,
              specialColor: {"AXA": Color(0xFFFE50BF)})
        ]);
  }

  Widget _buildHeatSinkTable() {
    return Table(
        border: TableBorder.symmetric(
            inside: BorderSide(color: Theme.of(context).dividerColor, width: 0.4)),
        children: [
          _buildTableRow(["Type", "Time", "Time(after first)"], fontSize: 16),
          _buildTableRow(["Common", "×0.8", "×0.9"],
              rowBgColor: Color(0xFFD3F3E2),
              defFontColor: Colors.black54,
              specialColor: {"Common": Color(0xFF35975B)}),
          _buildTableRow(["Rare", "×0.5", "×0.75"],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Rare": Color(0xFF613BF1)}),
          _buildTableRow(["Very Rare", "×0.3", "×0.65"],
              rowBgColor: Color(0xFFFCE6F9),
              defFontColor: Colors.black54,
              specialColor: {"Very Rare": Color(0xFFFE50BF)})
        ]);
  }

  Widget _buildMultiHackTable() {
    return Table(
        border: TableBorder.symmetric(
            inside: BorderSide(color: Theme.of(context).dividerColor, width: 0.4)),
        children: [
          _buildTableRow(["Type", "Hacks", "Hacks(after first)"], fontSize: 16),
          _buildTableRow(["Common", "+4", "+2"],
              rowBgColor: Color(0xFFD3F3E2),
              defFontColor: Colors.black54,
              specialColor: {"Common": Color(0xFF35975B)}),
          _buildTableRow(["Rare", "+8", "+4"],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Rare": Color(0xFF613BF1)}),
          _buildTableRow(["Very Rare", "+12", "+6"],
              rowBgColor: Color(0xFFFCE6F9),
              defFontColor: Colors.black54,
              specialColor: {"Very Rare": Color(0xFFFE50BF)})
        ]);
  }

  Widget _buildForceAmpTable() {
    return Table(
        columnWidths: const {
          0: FlexColumnWidth(),
          1: FlexColumnWidth(3),
          2: FlexColumnWidth(2)
        },
        border: TableBorder.symmetric(
            inside: BorderSide(color: Theme.of(context).dividerColor, width: 0.4)),
        children: [
          _buildTableRow(["#", "Type", "Damage"], fontSize: 16),
          _buildTableRow(["1", "Force Amp", "×2"],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Force Amp": Color(0xFF613BF1)}),
          _buildTableRow(["2", "Force Amp", "×2.25"],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Force Amp": Color(0xFF613BF1)}),
          _buildTableRow(["3", "Force Amp", "×2.375"],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Force Amp": Color(0xFF613BF1)}),
          _buildTableRow(["4", "Force Amp", "×2.5"],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Force Amp": Color(0xFF613BF1)})
        ]);
  }

  Widget _buildTurretTable() {
    return Table(
        columnWidths: const {
          0: FlexColumnWidth(),
          1: FlexColumnWidth(2),
          2: FlexColumnWidth(3),
          3: FlexColumnWidth(3)
        },
        border: TableBorder.symmetric(
            inside: BorderSide(color: Theme.of(context).dividerColor, width: 0.4)),
        children: [
          _buildTableRow(["#", "Type", "Attack Range", "Critical Hit"],
              fontSize: 16),
          _buildTableRow(["1", "Turret", "×2", "+30%"],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Turret": Color(0xFF613BF1)}),
          _buildTableRow(["2", "Turret", "×2.25", "+37.5%"],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Turret": Color(0xFF613BF1)}),
          _buildTableRow(["3", "Turret", "×2.375", "+41.25%"],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Turret": Color(0xFF613BF1)}),
          _buildTableRow(["4", "Turret", "×2.5", "+45%"],
              rowBgColor: Color(0xFFEDE6FD),
              defFontColor: Colors.black54,
              specialColor: {"Turret": Color(0xFF613BF1)})
        ]);
  }

  Widget _buildXmpBursterTable() {
    return Table(
        border: TableBorder.symmetric(
            inside: BorderSide(color: Theme.of(context).dividerColor, width: 0.4)),
        children: [
          _buildTableRow(["Level", "Cost", "Damage", "Range"], fontSize: 16),
          _buildTableRow(["1", "50XM", "150XM", "42m"],
              rowBgColor: Color(0x44045944),
              specialColor: {"1": Color(0xFFDEFB7C)}),
          _buildTableRow(["2", "100XM", "300XM", "46m"],
              specialColor: {"2": Color(0xFFEDBB68)}),
          _buildTableRow(["3", "150XM", "500XM", "58m"],
              rowBgColor: Color(0x44045944),
              specialColor: {"3": Color(0xFFDD776D)}),
          _buildTableRow(["4", "200XM", "900XM", "72m"],
              specialColor: {"4": Color(0xFFF76372)}),
          _buildTableRow(["5", "250XM", "1,200XM", "90m"],
              rowBgColor: Color(0x44045944),
              specialColor: {"5": Color(0xFFD15082)}),
          _buildTableRow(["6", "300XM", "1,500XM", "112m"],
              specialColor: {"6": Color(0xFFDD50BE)}),
          _buildTableRow(["7", "350XM", "1,800XM", "138m"],
              rowBgColor: Color(0x44045944),
              specialColor: {"7": Color(0xFF9C5CC2)}),
          _buildTableRow(["8", "400XM", "2,700XM", "168m"],
              specialColor: {"8": Color(0xFF5E47C1)})
        ]);
  }

  Widget _buildUltraStrikeTable() {
    return Table(
        border: TableBorder.symmetric(
            inside: BorderSide(color: Theme.of(context).dividerColor, width: 0.4)),
        children: [
          _buildTableRow(["Level", "Cost", "Damage", "Range"], fontSize: 16),
          _buildTableRow(["1", "50XM", "300XM", "10m"],
              rowBgColor: Color(0x44045944),
              specialColor: {"1": Color(0xFFDEFB7C)}),
          _buildTableRow(["2", "100XM", "600XM", "13m"],
              specialColor: {"2": Color(0xFFEDBB68)}),
          _buildTableRow(["3", "150XM", "1,000XM", "16m"],
              rowBgColor: Color(0x44045944),
              specialColor: {"3": Color(0xFFDD776D)}),
          _buildTableRow(["4", "200XM", "1,800XM", "18m"],
              specialColor: {"4": Color(0xFFF76372)}),
          _buildTableRow(["5", "250XM", "2,400XM", "21m"],
              rowBgColor: Color(0x44045944),
              specialColor: {"5": Color(0xFFD15082)}),
          _buildTableRow(["6", "300XM", "3,000XM", "24m"],
              specialColor: {"6": Color(0xFFDD50BE)}),
          _buildTableRow(["7", "350XM", "3,600XM", "27m"],
              rowBgColor: Color(0x44045944),
              specialColor: {"7": Color(0xFF9C5CC2)}),
          _buildTableRow(["8", "400XM", "5,400XM", "30m"],
              specialColor: {"8": Color(0xFF5E47C1)})
        ]);
  }

  Widget _buildResonatorTable() {
    return Table(
        columnWidths: const {
          0: FlexColumnWidth(),
          1: FlexColumnWidth(2),
          2: FlexColumnWidth()
        },
        border: TableBorder.symmetric(
            inside: BorderSide(color: Theme.of(context).dividerColor, width: 0.4)),
        children: [
          _buildTableRow(["Level", "Enery", "Max Deploy"], fontSize: 16),
          _buildTableRow(["1", "1,000XM", "8"],
              rowBgColor: Color(0x44045944),
              specialColor: {"1": Color(0xFFDEFB7C)}),
          _buildTableRow(["2", "1,500XM", "4"],
              specialColor: {"2": Color(0xFFEDBB68)}),
          _buildTableRow(["3", "2,000XM", "4"],
              rowBgColor: Color(0x44045944),
              specialColor: {"3": Color(0xFFDD776D)}),
          _buildTableRow(["4", "2,500XM", " 4 "],
              specialColor: {"4": Color(0xFFF76372)}),
          _buildTableRow(["5", "3,000XM", "2"],
              rowBgColor: Color(0x44045944),
              specialColor: {"5": Color(0xFFD15082)}),
          _buildTableRow(["6", "4,000XM", "2"],
              specialColor: {"6": Color(0xFFDD50BE)}),
          _buildTableRow(["7", "5,000XM", "1"],
              rowBgColor: Color(0x44045944),
              specialColor: {"7": Color(0xFF9C5CC2)}),
          _buildTableRow(["8", "6,000XM", "1"],
              specialColor: {"8": Color(0xFF5E47C1)})
        ]);
  }

  Widget _buildAccessPointsTable() {
    return Table(
        columnWidths: const {0: FlexColumnWidth(2), 1: FlexColumnWidth()},
        border: TableBorder.symmetric(
            inside: BorderSide(color: Theme.of(context).dividerColor, width: 0.4)),
        children: [
          _buildTableRow(["Recharge", "10AP"]),
          _buildTableRow(["Upgrade", "65AP"], rowBgColor: Color(0x44045944)),
          _buildTableRow(["Destory Resonator", "75AP"]),
          _buildTableRow(["Hack", "100AP"], rowBgColor: Color(0x44045944)),
          _buildTableRow(["Deploy", "125AP"]),
          _buildTableRow(["Install mod", "125AP"],
              rowBgColor: Color(0x44045944)),
          _buildTableRow(["Destory Link", "187AP"]),
          _buildTableRow(["Portal Edit", "200AP"],
              rowBgColor: Color(0x44045944)),
          _buildTableRow(["Complete Portal", "250AP"]),
          _buildTableRow(["Link", "313AP"], rowBgColor: Color(0x44045944)),
          _buildTableRow(["Capture Portal", "500AP"]),
          _buildTableRow(["Improve Photo", "500AP"],
              rowBgColor: Color(0x44045944)),
          _buildTableRow(["Destory Filed", "750AP"]),
          _buildTableRow(["New Portal", "1,000AP"],
              rowBgColor: Color(0x44045944)),
          _buildTableRow(["Filed", "1,250AP"]),
          _buildTableRow(["Recruit", "3,000AP"], rowBgColor: Color(0x44045944))
        ]);
  }
}
