import 'package:flutter/material.dart';
import 'package:ingress_assistant/data/data_module.dart';
import 'package:ingress_assistant/custom/glyph_widget.dart';
import 'package:ingress_assistant/generated/i18n.dart';
import 'package:ingress_assistant/utils/tools.dart';
import 'package:ingress_assistant/utils/firebase_tools.dart';

class RememberPage extends StatefulWidget {
  RememberPage({Key key}) : super(key: key) {
//    FireBase().analytics.setCurrentScreen(
//        screenName: "Remember Page", screenClassOverride: "RememberPage");
  }

  @override
  State<StatefulWidget> createState() {
    return RememberPageState();
  }
}

class RememberPageState extends State<RememberPage> {
  final Size _itemSize = Size(80, 80);
  List<HackSequence> _hackList;
  String _lastHead, _lastSearch;
  String _actionLevel = L234;

  Future<String> _showSearchView() async {
    final List<String> keywords = await DataModule().getAllGlyphNames();
//    print("Keywords: $keywords");
    final List<String> history = await DataModule().getLatestSearch();
//    print("History: $history");
    return showSearch<String>(
      context: context,
      delegate: _GlyphSearchDelegate(keywords, history),
    );
  }

  void handleActionSearch() {
    _showSearchView().then((selected) {
      print("Search keyword:$selected");
      DataModule().insertSearchRecord(selected);
      if (_lastSearch == null || _lastSearch != selected) {
        _lastSearch = selected;
      }
    });
  }

  void handleActionLevel(String level) {
    print("handleActionLevel: $level");
    if (_lastSearch != null) {
      _lastSearch = null;
      _actionLevel = level;
      setState(() {});
    } else {
      if (_actionLevel != level) {
        _actionLevel = level;
        setState(() {});
      }
    }
  }

  @override
  Widget build(BuildContext context) {
//    print("Build :${widget.actionMenu}");
    return FutureBuilder(
        future: DataModule().getHackList(_lastSearch ?? _actionLevel),
        builder: (context, snapshot) {
//          print("Get Sequence, level: ${widget.actionMenu}, result: $snapshot");
          if (snapshot.connectionState == ConnectionState.done &&
              snapshot.hasData) {
            _hackList = snapshot.data;
            return _buildDataDoneWidget();
          } else {
            return _buildDataWaitingWidget();
          }
        });
  }

  Widget _buildDataDoneWidget() {
    return Scrollbar(
        child: ListView.builder(
            padding: EdgeInsets.only(top: 4),
            itemCount: _hackList.length,
            itemBuilder: (context, index) {
              HackSequence hackList = _hackList[index];
//          print("build item: $hackList");
              String head = hackList.head.name;
              if (_lastHead == null || _lastHead != head) {
                _lastHead = head;
                return _buildHeadListItem(hackList);
              } else {
                return _buildNormalListItem(hackList);
              }
            }));
  }

  Widget _buildDataWaitingWidget() {
    return Center(child: Text(S.of(context).splash_loading));
  }

  Widget _buildGlyphInfoItem(GlyphInfo glyphInfo) {
    return Column(
      children: <Widget>[
        GlyphSequenceWidget(_itemSize, glyphInfo.path),
        SizedBox(height: 2),
        Text(glyphInfo.name, style: TextStyle(fontSize: 14))
      ],
    );
  }

  Widget _buildNormalListItem(HackSequence hackList) {
    List<Widget> hackItems = List();
    hackList.sequences.forEach((GlyphInfo glyphInfo) {
      hackItems.add(_buildGlyphInfoItem(glyphInfo));
    });
    return Padding(
        padding: EdgeInsets.only(top: 2, bottom: 2),
        child: Row(
            mainAxisAlignment: (_actionLevel == ALL || _lastSearch != null)
                ? MainAxisAlignment.start
                : MainAxisAlignment.spaceEvenly,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: hackItems));
  }

  Widget _buildHeadListItem(HackSequence hackList) {
    return Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Padding(
              padding: EdgeInsets.only(top: 8, left: 4),
              child: Text(hackList.head.name,
                  style: TextStyle(
                      fontSize: 14,
                      fontStyle: FontStyle.italic,
                      fontWeight: FontWeight.bold))),
          Divider(height: 1, color: Colors.grey[800]),
          SizedBox(height: 2),
          _buildNormalListItem(hackList)
        ]);
  }
}

class _GlyphSearchDelegate extends SearchDelegate<String> {
  final List<String> _keyWords;
  final List<String> _history;

  _GlyphSearchDelegate(this._keyWords, this._history);

  @override
  List<Widget> buildActions(BuildContext context) {
    return <Widget>[
      IconButton(
          tooltip: 'Clear',
          icon: const Icon(Icons.clear),
          onPressed: () {
            query = '';
            showSuggestions(context);
          })
    ];
  }

  @override
  Widget buildLeading(BuildContext context) {
    return IconButton(
        tooltip: 'Back',
        icon: AnimatedIcon(
            icon: AnimatedIcons.menu_arrow, progress: transitionAnimation),
        onPressed: () {
          close(context, null);
        });
  }

  @override
  Widget buildResults(BuildContext context) {
    //todo Nothing
    return null;
  }

  @override
  Widget buildSuggestions(BuildContext context) {
    final Iterable<String> suggestions = query.isEmpty
        ? _history
        : _keyWords.where((String key) {
            return key.toLowerCase().startsWith(query.toLowerCase());
          });
    return _SuggestionList(
        query: query,
        suggestions: suggestions.toList(),
        onSelected: (String suggestion) {
          close(context, suggestion);
        });
  }
}

class _SuggestionList extends StatelessWidget {
  final List<String> suggestions;
  final String query;
  final ValueChanged<String> onSelected;
  const _SuggestionList({this.suggestions, this.query, this.onSelected});

  @override
  Widget build(BuildContext context) {
    final ThemeData theme = Theme.of(context);
    return Scrollbar(
        child: ListView.builder(
            itemCount: suggestions.length,
            itemBuilder: (BuildContext context, int i) {
              final String suggestion = suggestions[i];
              return ListTile(
                  leading: query.isEmpty
                      ? const Icon(Icons.history)
                      : const Icon(null),
                  title: RichText(
                      text: TextSpan(
                          text: suggestion.substring(0, query.length),
                          style: theme.textTheme.subhead
                              .copyWith(fontWeight: FontWeight.bold),
                          children: <TextSpan>[
                        TextSpan(
                            text: suggestion.substring(query.length),
                            style: theme.textTheme.subhead)
                      ])),
                  onTap: () {
                    onSelected(suggestion);
                  });
            }));
  }
}
