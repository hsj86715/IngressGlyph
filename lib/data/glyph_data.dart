import 'dart:collection';
import 'dart:core';

const C_ALL = "All";
const C_HUMAN = "Human";
const C_ACTION = "Action";
const C_THOUGHT = "Thought";
const C_FLU_DIRE = "Fluctuation/Direction";
const C_TIME_SPACE = "Time/Space";
const C_COND_ENV = "Condition/Environment";

class BaseGlyph {
  static const T_KNOWLEDGE = "Knowledge";
  static const H_BODY_SHELL = "Body/Shell";
  static const H_ENLIGHTENMENT = "Enlightenment/Enlightened";
  static const H_BEING_HUMAN = "Being/Human";
  static const H_MIND = "Mind";
  static const H_RESISTANCE_STRUGGLE = "Resistance/Struggle";
  static const H_PORTAL = "Portal";
  static const H_INDIVIDUAL_SELF = "Individual/Self";
  static const H_COLLECTIVE_SHAPERS = "Shapers/Collective";
  static const H_SOUL_SPIRIT = "Soul/Spirit";
  static const H_XM = "XM";
  static const A_ADVANCE = "Advance";
  static const A_ATTACK_WAR = "Attack/War";
  static const A_AVOID = "Avoid";
  static const A_BREATHE_LIVE = "Breathe/Live";
  static const A_CAPTURE = "Capture";
  static const A_CHANGE_MODIFY = "Change/Modify";
  static const A_CREATE_CREATION = "Create/Creation";
  static const A_DEFEND = "Defend";
  static const A_DESTROY_DESTRUCTION = "Destroy/Destruction";
  static const A_DIE_DEATH = "Die/Death";
  static const A_DISCOVER = "Discover";
  static const A_ESCAPE = "Escape";
  static const A_HIDE = "Hide";
  static const A_HELP = "Help";
  static const A_JOURNEY = "Journey";
  static const A_LIBERATE = "Liberate";
  static const A_NOURISH = "Nourish";
  static const A_PURSUE_ASPIRATION = "Pursue/Aspiration";
  static const A_REACT = "React";
  static const A_REBEL = "Rebel";
  static const A_RETREAT = "Retreat";
  static const A_RECHARGE_REPAIR = "Recharge/Repair";
  static const A_RESTRAINT = "Restraint";
  static const A_SAVE_RESCUE = "Save/Rescue";
  static const A_SEARCH_SEEK = "Search/Seek";
  static const A_SEE = "See";
  static const A_SEPERATE = "Seperate";
  static const A_SHARE = "Share";
  static const A_TOGETHER = "Together";
  static const A_USE = "Use";
  static const T_ANSWER = "Answer";
  static const T_CONTEMPLATE = "Contemplate";
  static const T_COURAGE = "Courage";
  static const T_DATA = "Data";
  static const T_DESTINY = "Destiny";
  static const T_FEAR = "Fear";
  static const T_FORGET = "Forget";
  static const T_IDEA_THOUGHT = "Idea/Thought";
  static const T_IGNORE = "Ignore";
  static const T_LIE = "Lie";
  static const T_MESSAGE = "Message/Notification";
  static const T_QUESTION = "Question";
  static const T_TRUTH = "Truth";
  static const T_WANT_DESIRE = "Want/Desire";
  static const FD_DETERIORATE_ERODE = "Deteriorate/Erode";
  static const FD_EQUAL = "Equal";
  static const FD_EVOLUTION_PROGRESS_SUCCESS = "Evolution/Progress/Success";
  static const FD_FAILURE = "Failure";
  static const FD_FOLLOW = "Follow";
  static const FD_GAIN = "Gain";
  static const FD_GROW = "Grow";
  static const FD_IMPROVE = "Improve";
  static const FD_LEAD = "Lead";
  static const FD_LESS = "Less";
  static const FD_LOSE_LOSS = "Lose/Loss";
  static const FD_MORE = "More";
  static const FD_CONTRACT_REDUCE = "Contract/Reduce";
  static const TS_AGAIN_REPEAT = "Again/Repeat";
  static const TS_CLEAR_ALL = "Clear All";
  static const TS_DISTANCE_OUTSIDE = "Distance/Outside";
  static const TS_CLOSE_END_FINALITY = "Close/End/Finality";
  static const TS_FUTURE = "Future";
  static const TS_NEW = "New";
  static const TS_INSIDE_NOT = "Inside/Not";
  static const TS_NOW_PRESENT = "Now/Present";
  static const TS_OLD = "Old";
  static const TS_OPEN_ACCEPT = "Open/Accept";
  static const TS_OPEN_ALL = "Open-All";
  static const TS_PAST = "Past";
  static const CE_ALL = "All";
  static const CE_BALANCE_PERFECTION = "Balance/Perfection";
  static const CE_BARRIER_OBSTACLE = "Barrier/Obstacle";
  static const CE_CHAOS_DISORDER = "Chaos/Disorder";
  static const CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE =
      "Civilization/City/Government/Structure";
  static const CE_CLEAR = "Clear";
  static const CE_COMPLEX = "Complex";
  static const CE_CONFLICT = "Conflict";
  static const CE_DANGER = "Danger";
  static const CE_DIFFICULT = "Difficult";
  static const CE_EASY = "Easy";
  static const CE_HARM = "Harm";
  static const CE_HARMONY_PEACE = "Harmony/Peace";
  static const CE_HAVE = "Have";
  static const CE_IMPERFECT = "Imperfect";
  static const CE_IMPURE = "Impure";
  static const CE_NATURE = "Nature";
  static const CE_PATH = "Path";
  static const CE_POTENTIAL = "Potential";
  static const CE_PURE_PURITY = "Pure/Purity";
  static const CE_SAFETY = "Safety";
  static const CE_SIMPLE = "Simple";
  static const CE_STABILITY_STAY = "Stability/Stay";
  static const CE_STRONG = "Strong";

  //new Added 2017.11.18
//    public static  static const C_NEW_ADDED = "New Added";
//    public static  static const C_NO_CAREGORY = "Unknown Category";
  static const CE_WEAK = "Weak";
  static const A_ABANDON = "Abandon";
  static const TS_BEGIN = "Begin";
  static const TS_DESTINATION = "Destination";
  static const FD_LEGACY = "Legacy";
  static const CE_NZEER = "N_Zeer";
  static const T_PERSPECTIVE = "Perspective";
  static const H_US_WE = "Us/We";
  static const CE_ADAPT = "Adapt";
  static const TS_AFTER = "After";
  static const TS_BEFORE = "Before";
  static const CE_CONSEQUENCE = "Consequence";
  static const H_CREATIVITY = "Creativity";

  static const H_I_ME = "I/Me";
  static const T_INTELLIGENCE = "Intelligence";
  static const A_INTERRUPT = "Interrupt";
  static const A_LIVE_AGAIN = "Live Again/Reincarnate";
  static const T_MYSTERY = "Mystery";
  static const A_PRESENCE = "Presence";
  static const A_CHASE = "Chase";

  //       static const H_RESIST = "Resist";
  static const A_SUSTAIN = "Sustain";
  static const A_SUSTAIN_ALL = "Sustain All";
  static const T_TECHNOLOGY = "Technology";
  static const H_THEM = "Them";
  static const CE_UNBOUNDED = "Unbounded";
  static const FD_VICTORY = "Victory";
  static const FD_WORTH = "Worth";
  static const H_OTHER_YOU_YOUR = "Other/You/Your";

  //2019.11.15 Add new
  static const H_OSIRIS = "Osiris";
  static const A_CALL = "Call";
  static const CE_FIELD = "Field";
  static const H_NEMESIS = "Nemesis";
  static const CE_KEY = "Key";
  static const A_LINK = "Link";
  static const H_SHIELD = "Shield";
  static const CE_STAR = "Star";
  static const CE_TOAST = "Toast/Toasty";

  Map<String, List<int>> _mBaseGlyphs;
  Map<String, List<String>> _mCategoryGlyphs;
  Map<String, List<List<String>>> _mTwoSequences;
  Map<String, List<List<String>>> _mThreeSequences;
  Map<String, List<List<String>>> _mFourSequences;
  Map<String, List<List<String>>> _mFiveSequences;
  List<List<String>> _mGlyphPairs;

  BaseGlyph() {
    _initBaseGlyphs();
    _initGlyphCategories();
  }

  void _initBaseGlyphs() {
    _mBaseGlyphs = LinkedHashMap<String, List<int>>();
    _mBaseGlyphs[A_ABANDON] = [3, 2, 1, 7, 8, 9];
    _mBaseGlyphs[CE_ADAPT] = [6, 7, 1, 10];
    _mBaseGlyphs[A_ADVANCE] = [8, 5, 4];
    _mBaseGlyphs[TS_AFTER] = [1, 2, 3, 11, 10, 1];
    _mBaseGlyphs[TS_AGAIN_REPEAT] = [8, 5, 7, 1, 2, 10];
    _mBaseGlyphs[CE_ALL] = [4, 6, 8, 9, 11, 3, 4];
    _mBaseGlyphs[T_ANSWER] = [5, 2, 10, 1];
    _mBaseGlyphs[A_ATTACK_WAR] = [8, 5, 4, 2, 11];
    _mBaseGlyphs[A_AVOID] = [6, 4, 2, 3, 10];
    _mBaseGlyphs[CE_BALANCE_PERFECTION] = [4, 1, 7, 8, 9, 11, 10, 1];
    _mBaseGlyphs[CE_BARRIER_OBSTACLE] = [4, 1, 10, 11];
    _mBaseGlyphs[TS_BEFORE] = [6, 5, 1, 7, 8, 6];
    _mBaseGlyphs[TS_BEGIN] = [4, 7, 9, 10];
    _mBaseGlyphs[H_BEING_HUMAN] = [9, 7, 5, 2, 10, 9];
    _mBaseGlyphs[H_BODY_SHELL] = [1, 5, 2, 1];
    _mBaseGlyphs[A_BREATHE_LIVE] = [6, 5, 1, 2, 3];
    _mBaseGlyphs[A_CAPTURE] = [3, 10, 1, 7, 8, 9];
    _mBaseGlyphs[A_CHANGE_MODIFY] = [7, 1, 9, 10];
    _mBaseGlyphs[CE_CHAOS_DISORDER] = [8, 6, 4, 3, 2, 1, 7, 9];
    _mBaseGlyphs[A_CHASE] = [8, 7, 5, 1, 4];
    _mBaseGlyphs[CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE] = [
      6,
      5,
      7,
      10,
      2,
      3
    ];
    _mBaseGlyphs[CE_CLEAR] = [4, 1, 9];
    _mBaseGlyphs[TS_CLEAR_ALL] = [4, 6, 8, 9, 11, 3, 4, 1, 9];
    _mBaseGlyphs[TS_CLOSE_END_FINALITY] = [4, 1, 9, 10, 3, 4];
    _mBaseGlyphs[H_COLLECTIVE_SHAPERS] = [8, 7, 5, 4, 2, 10, 11];
    _mBaseGlyphs[CE_COMPLEX] = [2, 5, 1, 7];
    _mBaseGlyphs[CE_CONFLICT] = [8, 5, 7, 10, 2, 11];
    _mBaseGlyphs[CE_CONSEQUENCE] = [6, 5, 7, 10, 11];
    _mBaseGlyphs[T_CONTEMPLATE] = [4, 3, 11, 9, 7, 5, 1, 2];
    _mBaseGlyphs[T_COURAGE] = [8, 5, 7, 10];
    _mBaseGlyphs[A_CREATE_CREATION] = [8, 7, 1, 2, 3];
    _mBaseGlyphs[H_CREATIVITY] = [5, 1, 9, 5];
    _mBaseGlyphs[CE_DANGER] = [4, 5, 1, 9];
    _mBaseGlyphs[T_DATA] = [4, 2, 1, 7, 9];
//        _mBaseGlyphs[CE_DEATH] = [8, 7, 1, 10, 11];
    _mBaseGlyphs[A_DEFEND] = [6, 7, 9, 10, 3];
    _mBaseGlyphs[TS_DESTINATION] = [3, 11, 9];
    _mBaseGlyphs[T_DESTINY] = [5, 1, 2, 10, 7, 9];
    _mBaseGlyphs[FD_DETERIORATE_ERODE] = [5, 1, 7, 8];
    _mBaseGlyphs[A_DESTROY_DESTRUCTION] = [6, 5, 1, 10, 11];
    _mBaseGlyphs[A_DIE_DEATH] = [8, 7, 1, 10, 11];
    _mBaseGlyphs[CE_DIFFICULT] = [7, 1, 10, 2, 3];
    _mBaseGlyphs[A_DISCOVER] = [3, 11, 9, 8];
    _mBaseGlyphs[TS_DISTANCE_OUTSIDE] = [8, 6, 4];
    _mBaseGlyphs[CE_EASY] = [2, 1, 7, 9];
//        _mBaseGlyphs[H_ENLIGHTENED] = [5, 2, 1, 5, 4, 3, 11, 9, 8];
    _mBaseGlyphs[H_ENLIGHTENMENT] = [5, 2, 1, 5, 4, 3, 11, 9];
    _mBaseGlyphs[FD_EQUAL] = [7, 5, 2, 10];
    _mBaseGlyphs[A_ESCAPE] = [4, 3, 2, 5, 7];
    _mBaseGlyphs[FD_EVOLUTION_PROGRESS_SUCCESS] = [4, 1, 5, 7];
    _mBaseGlyphs[FD_FAILURE] = [4, 1, 2, 10];
    _mBaseGlyphs[T_FEAR] = [5, 2, 10, 3];
    _mBaseGlyphs[FD_FOLLOW] = [4, 2, 3, 11];
    _mBaseGlyphs[T_FORGET] = [8, 7];
    _mBaseGlyphs[TS_FUTURE] = [3, 2, 10, 11];
    _mBaseGlyphs[FD_GAIN] = [6, 7];
    _mBaseGlyphs[FD_GROW] = [8, 5, 7];
    _mBaseGlyphs[CE_HARM] = [1, 2, 4, 5, 1, 10, 11];
    _mBaseGlyphs[CE_HARMONY_PEACE] = [1, 5, 4, 2, 1, 7, 9, 10, 1];
    _mBaseGlyphs[CE_HAVE] = [10, 1, 7, 9];
    _mBaseGlyphs[A_HELP] = [6, 5, 1, 7, 10];
    _mBaseGlyphs[A_HIDE] = [5, 2, 3, 10, 7];
    _mBaseGlyphs[H_I_ME] = [5, 2, 9, 5];
    _mBaseGlyphs[T_IDEA_THOUGHT] = [7, 8, 6, 5, 1, 10, 11, 3, 2];
    _mBaseGlyphs[T_IGNORE] = [10, 11];
    _mBaseGlyphs[CE_IMPERFECT] = [2, 1, 7, 5, 1];
    _mBaseGlyphs[FD_IMPROVE] = [3, 2, 1, 10];
    _mBaseGlyphs[CE_IMPURE] = [1, 5, 7, 1, 9];
    _mBaseGlyphs[H_INDIVIDUAL_SELF] = [8, 9, 11];
    _mBaseGlyphs[TS_INSIDE_NOT] = [5, 2, 10];
    _mBaseGlyphs[T_INTELLIGENCE] = [8, 7, 5, 1, 2, 3];
    _mBaseGlyphs[A_INTERRUPT] = [4, 1, 5, 6, 8, 7, 1, 9];
    _mBaseGlyphs[A_JOURNEY] = [3, 2, 1, 5, 6, 8, 9];
    _mBaseGlyphs[T_KNOWLEDGE] = [1, 2, 9, 5, 1];
    _mBaseGlyphs[FD_LEAD] = [4, 6, 8, 7, 9];
    _mBaseGlyphs[FD_LEGACY] = [8, 7, 5, 6, 4, 3, 2, 10, 11];
    _mBaseGlyphs[FD_LESS] = [5, 1, 2];
    _mBaseGlyphs[A_LIBERATE] = [4, 3, 2, 1, 5, 8];
    _mBaseGlyphs[T_LIE] = [7, 5, 1, 10, 2, 1];
    _mBaseGlyphs[A_LIVE_AGAIN] = [8, 5, 7, 1, 2, 3];
    _mBaseGlyphs[FD_LOSE_LOSS] = [10, 3];
    _mBaseGlyphs[T_MESSAGE] = [8, 5, 1, 10, 3];
    _mBaseGlyphs[H_MIND] = [9, 1, 5, 7, 9];
    _mBaseGlyphs[FD_MORE] = [7, 1, 10];
    _mBaseGlyphs[T_MYSTERY] = [6, 5, 2, 4, 5, 7];
    _mBaseGlyphs[CE_NATURE] = [8, 7, 5, 2, 10, 11];
    _mBaseGlyphs[TS_NEW] = [2, 10, 11];
    _mBaseGlyphs[CE_NZEER] = [9, 1, 2, 4, 1, 5, 4];
    _mBaseGlyphs[A_NOURISH] = [1, 7, 8, 9, 1];
    _mBaseGlyphs[TS_NOW_PRESENT] = [5, 7, 10, 2];
    _mBaseGlyphs[TS_OLD] = [6, 5, 7];
    _mBaseGlyphs[TS_OPEN_ACCEPT] = [9, 7, 10, 9];
    _mBaseGlyphs[TS_OPEN_ALL] = [9, 7, 10, 9, 11, 3, 4, 6, 8, 9];
    _mBaseGlyphs[H_OTHER_YOU_YOUR] = [4, 7, 10, 4];
    _mBaseGlyphs[TS_PAST] = [6, 5, 7, 8];
    _mBaseGlyphs[CE_PATH] = [4, 1, 7, 8];
    _mBaseGlyphs[T_PERSPECTIVE] = [8, 7, 1, 2, 4, 5, 1, 10, 11];
    _mBaseGlyphs[H_PORTAL] = [6, 8, 7, 10, 11, 3, 2, 5, 6];
    _mBaseGlyphs[CE_POTENTIAL] = [4, 1, 10, 11, 3];
    _mBaseGlyphs[A_PRESENCE] = [7, 10, 2, 1, 5, 7, 9, 10];
    _mBaseGlyphs[CE_PURE_PURITY] = [4, 1, 10, 2, 1];
    _mBaseGlyphs[A_PURSUE_ASPIRATION] = [6, 5, 4, 2];
    _mBaseGlyphs[T_QUESTION] = [4, 2, 5, 7];
    _mBaseGlyphs[A_REACT] = [2, 5, 1, 10, 11];
    _mBaseGlyphs[A_REBEL] = [6, 7, 1, 2, 3, 11];
    _mBaseGlyphs[A_RECHARGE_REPAIR] = [4, 6, 5, 1, 4];
    _mBaseGlyphs[FD_CONTRACT_REDUCE] = [10, 2, 11];
//        _mBaseGlyphs[H_RESIST] = [2, 5, 4, 1, 9, 10];
    _mBaseGlyphs[H_RESISTANCE_STRUGGLE] = [2, 5, 4, 1, 9, 7];
    _mBaseGlyphs[A_RESTRAINT] = [6, 5, 1, 10, 11, 9];
    _mBaseGlyphs[A_RETREAT] = [4, 2, 11];
    _mBaseGlyphs[CE_SAFETY] = [8, 5, 2, 11];
    _mBaseGlyphs[A_SAVE_RESCUE] = [7, 1, 10, 3];
    _mBaseGlyphs[A_SEARCH_SEEK] = [1, 2, 5, 7, 10];
    _mBaseGlyphs[A_SEE] = [5, 4];
    _mBaseGlyphs[A_SEPERATE] = [6, 5, 7, 1, 2, 10, 11];
    _mBaseGlyphs[A_SHARE] = [9, 8, 7, 10, 11];
    _mBaseGlyphs[CE_SIMPLE] = [7, 10];
    _mBaseGlyphs[H_SOUL_SPIRIT] = [9, 1, 2, 10, 9];
    _mBaseGlyphs[CE_STABILITY_STAY] = [8, 7, 10, 11];
    _mBaseGlyphs[CE_STRONG] = [5, 7, 10, 2, 5];
    _mBaseGlyphs[A_SUSTAIN] = [4, 1, 2, 3, 11, 10, 1, 9];
    _mBaseGlyphs[A_SUSTAIN_ALL] = [4, 1, 2, 3, 11, 10, 1, 9, 8, 6, 4, 3, 11, 9];
    _mBaseGlyphs[T_TECHNOLOGY] = [3, 2, 1, 7, 5, 1, 10, 11];
    _mBaseGlyphs[H_THEM] = [4, 7, 10];
    _mBaseGlyphs[A_TOGETHER] = [1, 5, 2, 1, 7, 8];
    _mBaseGlyphs[T_TRUTH] = [5, 1, 10, 2, 1, 7, 5];
    _mBaseGlyphs[CE_UNBOUNDED] = [1, 2, 5, 7, 10, 3, 4, 6, 8, 9, 11];
    _mBaseGlyphs[H_US_WE] = [5, 2, 9];
    _mBaseGlyphs[A_USE] = [1, 10, 3];
    _mBaseGlyphs[FD_VICTORY] = [4, 5, 9, 2, 4];
    _mBaseGlyphs[T_WANT_DESIRE] = [8, 7, 9, 10];
    _mBaseGlyphs[CE_WEAK] = [6, 5, 2, 10];
    _mBaseGlyphs[FD_WORTH] = [6, 7, 1, 10, 3];
    _mBaseGlyphs[H_XM] = [7, 5, 2, 10, 1, 7];
    //2019.11.15
    _mBaseGlyphs[H_OSIRIS] = [2, 5, 1, 2, 4, 5, 8, 11, 2];
    _mBaseGlyphs[A_CALL] = [4, 5, 1];
    _mBaseGlyphs[CE_FIELD] = [3, 6, 7, 9, 10, 3];
    _mBaseGlyphs[H_NEMESIS] = [1, 2, 5, 1, 10, 7, 1];
    _mBaseGlyphs[CE_KEY] = [1, 2, 10, 1, 5, 7, 9];
    _mBaseGlyphs[A_LINK] = [6, 3];
    _mBaseGlyphs[H_SHIELD] = [2, 4, 5, 7, 9, 10, 2];
    _mBaseGlyphs[CE_STAR] = [1, 2, 3, 1, 7, 8, 1, 5, 6, 1, 10, 11, 1, 4, 9];
    _mBaseGlyphs[CE_TOAST] = [2, 3, 6, 5, 8, 11, 2];
  }

  void _initGlyphCategories() {
    _mCategoryGlyphs = LinkedHashMap<String, List<String>>();
    _mCategoryGlyphs[C_HUMAN] = [
      H_BODY_SHELL,
      H_ENLIGHTENMENT,
      H_BEING_HUMAN,
      H_MIND,
      H_RESISTANCE_STRUGGLE,
      H_PORTAL,
      H_INDIVIDUAL_SELF,
      H_COLLECTIVE_SHAPERS,
      H_SOUL_SPIRIT,
      H_XM,
      H_CREATIVITY,
      H_I_ME,
      H_THEM,
      H_US_WE,
      H_OTHER_YOU_YOUR,
      H_NEMESIS,
      H_SHIELD,
      H_OSIRIS
    ];

    _mCategoryGlyphs[C_ACTION] = [
      A_ADVANCE,
      A_ATTACK_WAR,
      A_AVOID,
      A_BREATHE_LIVE,
      A_CAPTURE,
      A_CHANGE_MODIFY,
      A_CREATE_CREATION,
      A_DEFEND,
      A_DESTROY_DESTRUCTION,
      A_DIE_DEATH,
      A_DISCOVER,
      A_ESCAPE,
      A_HIDE,
      A_JOURNEY,
      A_LIBERATE,
      A_NOURISH,
      A_PURSUE_ASPIRATION,
      A_REACT,
      A_REBEL,
      A_RETREAT,
      A_RECHARGE_REPAIR,
      A_RESTRAINT,
      A_SAVE_RESCUE,
      A_SEARCH_SEEK,
      A_SEE,
      A_SEPERATE,
      A_SHARE,
      A_TOGETHER,
      A_USE,
      A_ABANDON,
      A_INTERRUPT,
      A_LIVE_AGAIN,
      A_PRESENCE,
      A_CHASE,
      A_SUSTAIN,
      A_SUSTAIN_ALL,
      A_CALL,
      A_LINK
    ];

    _mCategoryGlyphs[C_THOUGHT] = [
      T_ANSWER,
      T_CONTEMPLATE,
      T_COURAGE,
      T_DATA,
      T_DESTINY,
      T_FEAR,
      T_FORGET,
      T_IDEA_THOUGHT,
      T_IGNORE,
      T_LIE,
      T_MESSAGE,
      T_QUESTION,
      T_TRUTH,
      T_WANT_DESIRE,
      T_INTELLIGENCE,
      T_KNOWLEDGE,
      T_MYSTERY,
      T_PERSPECTIVE,
      T_TECHNOLOGY
    ];

    _mCategoryGlyphs[C_FLU_DIRE] = [
      FD_DETERIORATE_ERODE,
      FD_EQUAL,
      FD_EVOLUTION_PROGRESS_SUCCESS,
      FD_FAILURE,
      FD_FOLLOW,
      FD_GAIN,
      FD_GROW,
      FD_IMPROVE,
      FD_LEAD,
      FD_LESS,
      FD_LOSE_LOSS,
      FD_MORE,
      FD_CONTRACT_REDUCE,
      FD_LEGACY,
      FD_VICTORY,
      FD_WORTH
    ];

    _mCategoryGlyphs[C_TIME_SPACE] = [
      TS_AGAIN_REPEAT,
      TS_CLEAR_ALL,
      TS_DISTANCE_OUTSIDE,
      TS_CLOSE_END_FINALITY,
      TS_FUTURE,
      TS_NEW,
      TS_INSIDE_NOT,
      TS_NOW_PRESENT,
      TS_OLD,
      TS_OPEN_ACCEPT,
      TS_OPEN_ALL,
      TS_PAST,
      TS_AFTER,
      TS_BEFORE,
      TS_BEGIN,
      TS_DESTINATION
    ];

    _mCategoryGlyphs[C_COND_ENV] = [
      CE_ALL,
      CE_BALANCE_PERFECTION,
      CE_BARRIER_OBSTACLE,
      CE_CHAOS_DISORDER,
      CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
      CE_CLEAR,
      CE_COMPLEX,
      CE_CONFLICT,
      CE_DANGER,
      CE_DIFFICULT,
      CE_EASY,
      CE_HARM,
      CE_HARMONY_PEACE,
      CE_HAVE,
      A_HELP,
      CE_IMPERFECT,
      CE_IMPURE,
      CE_NATURE,
      CE_PATH,
      CE_POTENTIAL,
      CE_PURE_PURITY,
      CE_SAFETY,
      CE_SIMPLE,
      CE_STABILITY_STAY,
      CE_STRONG,
      CE_WEAK,
      CE_ADAPT,
      CE_CONSEQUENCE,
      CE_NZEER,
      CE_UNBOUNDED,
      CE_FIELD,
      CE_KEY,
      CE_STAR
    ];

//        _mCategoryGlyphs[C_NEW_ADDED] = [A_ABANDON, TS_BEGIN, TS_DESTINATION, FD_LEGACY,
//                CE_NZEER, T_KNOWLEDGE, T_PERSPECTIVE, H_US_WE, CE_ADAPT, TS_AFTER, TS_BEFORE,
//                CE_CONSEQUENCE, H_CREATIVITY, /*H_ENLIGHTENED,*/ H_I_ME, T_INTELLIGENCE, A_INTERRUPT,
//                A_LIVE_AGAIN, T_MYSTERY, A_PRESENCE, A_CHASE, /*H_RESIST,*/ A_SUSTAIN, A_SUSTAIN_ALL,
//                T_TECHNOLOGY, H_THEM, CE_UNBOUNDED, FD_VICTORY, FD_WORTH, H_OTHER_YOU_YOUR];
//
//        _mCategoryGlyphs[C_NO_CAREGORY] = [UNKNOWN_FISH, UNKNOWN_BOMB, UNKNOWN_PLOUGH,
//                UNKNOWN_SOWN];
  }

  void _initTwoSequences() {
    _mTwoSequences = LinkedHashMap<String, List<List<String>>>();
    _mTwoSequences[A_ABANDON] = [
      [A_ABANDON, T_FEAR]
    ];
    _mTwoSequences[CE_ADAPT] = [
      [CE_ADAPT, T_TECHNOLOGY]
    ];
    _mTwoSequences[CE_ALL] = [
      [CE_ALL, CE_CHAOS_DISORDER]
    ];
    _mTwoSequences[A_ATTACK_WAR] = [
      [A_ATTACK_WAR, CE_CHAOS_DISORDER],
      [A_ATTACK_WAR, CE_DIFFICULT]
    ];
    _mTwoSequences[A_CAPTURE] = [
      [A_CAPTURE, H_PORTAL]
    ];
    _mTwoSequences[A_CHANGE_MODIFY] = [
      [A_CHANGE_MODIFY, T_PERSPECTIVE]
    ];
    _mTwoSequences[CE_CLEAR] = [
      [CE_CLEAR, CE_CONSEQUENCE]
    ];
    _mTwoSequences[CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE] = [
      [CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, CE_CHAOS_DISORDER]
    ];
    _mTwoSequences[A_CREATE_CREATION] = [
      [A_CREATE_CREATION, CE_DANGER]
    ];
    _mTwoSequences[A_DESTROY_DESTRUCTION] = [
      [A_DESTROY_DESTRUCTION, A_LIVE_AGAIN]
    ];
    _mTwoSequences[A_DISCOVER] = [
      [A_DISCOVER, CE_SAFETY],
      [A_DISCOVER, T_LIE],
      [A_DISCOVER, H_PORTAL]
    ];
    _mTwoSequences[TS_FUTURE] = [
      [TS_FUTURE, TS_DESTINATION]
    ];
    _mTwoSequences[A_HELP] = [
      [A_HELP, H_THEM]
    ];
    _mTwoSequences[A_INTERRUPT] = [
      [A_INTERRUPT, FD_EVOLUTION_PROGRESS_SUCCESS]
    ];
    _mTwoSequences[FD_LEAD] = [
      [FD_LEAD, H_ENLIGHTENMENT]
    ];
    _mTwoSequences[A_LIBERATE] = [
      [A_LIBERATE, H_XM]
    ];
    _mTwoSequences[A_NOURISH] = [
      [A_NOURISH, A_JOURNEY]
    ];
    _mTwoSequences[TS_OPEN_ALL] = [
      [TS_OPEN_ALL, T_TRUTH]
    ];
    _mTwoSequences[H_OTHER_YOU_YOUR] = [
      [H_OTHER_YOU_YOUR, CE_ADAPT]
    ];
    _mTwoSequences[CE_PATH] = [
      [CE_PATH, CE_BALANCE_PERFECTION]
    ];
    _mTwoSequences[H_PORTAL] = [
      [H_PORTAL, A_PRESENCE]
    ];
    _mTwoSequences[CE_PURE_PURITY] = [
      [CE_PURE_PURITY, T_TRUTH],
      [CE_PURE_PURITY, H_BODY_SHELL],
      [CE_PURE_PURITY, H_BEING_HUMAN]
    ];
    _mTwoSequences[A_PURSUE_ASPIRATION] = [
      [A_PURSUE_ASPIRATION, H_XM],
      [A_PURSUE_ASPIRATION, T_TRUTH]
    ];
    _mTwoSequences[T_QUESTION] = [
      [T_QUESTION, A_ATTACK_WAR]
    ];
    _mTwoSequences[A_SEARCH_SEEK] = [
      [A_SEARCH_SEEK, CE_POTENTIAL],
      [A_SEARCH_SEEK, TS_PAST]
    ];
    _mTwoSequences[A_SEE] = [
      [A_SEE, CE_UNBOUNDED]
    ];
    _mTwoSequences[A_SEPERATE] = [
      [A_SEPERATE, A_ATTACK_WAR]
    ];
  }

  void _initThreeSequences() {
    _mThreeSequences = LinkedHashMap<String, List<List<String>>>();
    _mThreeSequences[A_ABANDON] = [
      [A_ABANDON, T_FEAR, A_TOGETHER]
    ];
    _mThreeSequences[TS_OPEN_ACCEPT] = [
      [TS_OPEN_ACCEPT, H_BEING_HUMAN, CE_WEAK]
    ];
    _mThreeSequences[A_ADVANCE] = [
      [A_ADVANCE, CE_PURE_PURITY, T_TRUTH]
    ];
    _mThreeSequences[TS_AGAIN_REPEAT] = [
      [TS_AGAIN_REPEAT, A_JOURNEY, TS_DISTANCE_OUTSIDE]
    ];
    _mThreeSequences[CE_ALL] = [
      [CE_ALL, H_XM, A_LIBERATE],
      [CE_ALL, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, CE_CHAOS_DISORDER]
    ];
    _mThreeSequences[T_ANSWER] = [
      [T_ANSWER, CE_NZEER, T_QUESTION]
    ];
    _mThreeSequences[A_ATTACK_WAR] = [
      [A_ATTACK_WAR, H_COLLECTIVE_SHAPERS, CE_CHAOS_DISORDER],
      [A_ATTACK_WAR, A_CREATE_CREATION, CE_DANGER],
      [A_ATTACK_WAR, A_DESTROY_DESTRUCTION, TS_FUTURE],
      [A_ATTACK_WAR, CE_DIFFICULT, TS_FUTURE],
      [A_ATTACK_WAR, H_COLLECTIVE_SHAPERS, FD_EVOLUTION_PROGRESS_SUCCESS]
    ];
    _mThreeSequences[A_AVOID] = [
      [A_AVOID, CE_CHAOS_DISORDER, H_SOUL_SPIRIT],
      [A_AVOID, T_DESTINY, T_LIE],
      [A_AVOID, CE_PURE_PURITY, CE_CHAOS_DISORDER],
      [A_AVOID, CE_COMPLEX, CE_CONFLICT]
    ];
    _mThreeSequences[CE_BALANCE_PERFECTION] = [
      [CE_BALANCE_PERFECTION, CE_PATH, CE_HARMONY_PEACE]
    ];
    _mThreeSequences[TS_BEGIN] = [
      [TS_BEGIN, TS_NEW, H_RESISTANCE_STRUGGLE]
    ];
    _mThreeSequences[H_BEING_HUMAN] = [
      [H_BEING_HUMAN, T_KNOWLEDGE, FD_LEGACY],
      [H_BEING_HUMAN, A_ESCAPE, CE_ALL],
      [H_BEING_HUMAN, T_INTELLIGENCE, CE_UNBOUNDED],
      [H_BEING_HUMAN, FD_EVOLUTION_PROGRESS_SUCCESS, CE_UNBOUNDED],
      [H_BEING_HUMAN, FD_FAILURE, CE_UNBOUNDED]
    ];
    _mThreeSequences[A_CAPTURE] = [
      [A_CAPTURE, H_COLLECTIVE_SHAPERS, H_PORTAL],
      [A_CAPTURE, FD_VICTORY, A_TOGETHER]
    ];
    _mThreeSequences[A_CHANGE_MODIFY] = [
      [A_CHANGE_MODIFY, H_BEING_HUMAN, TS_FUTURE],
      [A_CHANGE_MODIFY, T_PERSPECTIVE, T_TECHNOLOGY]
    ];
    _mThreeSequences[CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE] = [
      [
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        A_ATTACK_WAR,
        CE_CHAOS_DISORDER
      ]
    ];
    _mThreeSequences[CE_CLEAR] = [
      [CE_CLEAR, H_OTHER_YOU_YOUR, H_MIND]
    ];
    _mThreeSequences[CE_COMPLEX] = [
      [CE_COMPLEX, A_JOURNEY, TS_FUTURE]
    ];
    _mThreeSequences[CE_CONFLICT] = [
      [CE_CONFLICT, FD_GROW, A_ATTACK_WAR]
    ];
    _mThreeSequences[T_COURAGE] = [
      [T_COURAGE, T_DESTINY, A_REBEL]
    ];
    _mThreeSequences[A_CREATE_CREATION] = [
      [A_CREATE_CREATION, T_TRUTH, FD_LEGACY],
      [A_CREATE_CREATION, TS_NEW, TS_FUTURE]
    ];
    _mThreeSequences[CE_DANGER] = [
      [CE_DANGER, A_CHANGE_MODIFY, TS_PAST]
    ];
    _mThreeSequences[A_DEFEND] = [
      [A_DEFEND, H_BEING_HUMAN, T_LIE]
    ];
    _mThreeSequences[A_DESTROY_DESTRUCTION] = [
      [A_DESTROY_DESTRUCTION, CE_DIFFICULT, CE_BARRIER_OBSTACLE],
      [A_DESTROY_DESTRUCTION, CE_IMPURE, T_TRUTH],
      [
        A_DESTROY_DESTRUCTION,
        CE_WEAK,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE
      ],
      [A_DESTROY_DESTRUCTION, T_DESTINY, CE_BARRIER_OBSTACLE]
    ];
    _mThreeSequences[A_DISCOVER] = [
      [A_DISCOVER, H_RESISTANCE_STRUGGLE, T_TRUTH],
      [A_DISCOVER, H_COLLECTIVE_SHAPERS, H_ENLIGHTENMENT],
      [A_DISCOVER, H_COLLECTIVE_SHAPERS, T_LIE],
      [A_DISCOVER, H_COLLECTIVE_SHAPERS, T_MESSAGE],
      [A_DISCOVER, CE_PURE_PURITY, T_TRUTH]
    ];
    _mThreeSequences[A_ESCAPE] = [
      [A_ESCAPE, CE_IMPURE, FD_EVOLUTION_PROGRESS_SUCCESS],
      [A_ESCAPE, CE_IMPURE, TS_FUTURE]
    ];
    _mThreeSequences[T_FEAR] = [
      [T_FEAR, CE_CHAOS_DISORDER, H_XM],
      [T_FEAR, CE_IMPERFECT, T_TECHNOLOGY]
    ];
    _mThreeSequences[FD_FOLLOW] = [
      [FD_FOLLOW, CE_PURE_PURITY, A_JOURNEY]
    ];
    _mThreeSequences[TS_FUTURE] = [
      [TS_FUTURE, FD_EQUAL, TS_PAST]
    ];
    _mThreeSequences[FD_GAIN] = [
      [FD_GAIN, CE_DIFFICULT, CE_BARRIER_OBSTACLE],
      [FD_GAIN, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, CE_HARMONY_PEACE],
      [FD_GAIN, FD_MORE, T_KNOWLEDGE]
    ];
    _mThreeSequences[FD_GROW] = [
      [FD_GROW, H_COLLECTIVE_SHAPERS, A_PRESENCE]
    ];
    _mThreeSequences[CE_HARM] = [
      [CE_HARM, CE_DANGER, A_AVOID]
    ];
    _mThreeSequences[A_HELP] = [
      [A_HELP, H_US_WE, CE_ALL]
    ];
    _mThreeSequences[H_BEING_HUMAN] = [
      [H_BEING_HUMAN, FD_GAIN, CE_SAFETY],
      [H_BEING_HUMAN, T_KNOWLEDGE, FD_LEGACY]
    ];
    _mThreeSequences[CE_HARMONY_PEACE] = [
      [CE_HARMONY_PEACE, CE_SIMPLE, A_JOURNEY],
      [CE_HARMONY_PEACE, FD_WORTH, FD_MORE]
    ];
    _mThreeSequences[FD_IMPROVE] = [
      [FD_IMPROVE, A_ADVANCE, TS_NOW_PRESENT],
      [FD_IMPROVE, H_BEING_HUMAN, H_COLLECTIVE_SHAPERS]
    ];
    _mThreeSequences[TS_INSIDE_NOT] = [
      [TS_INSIDE_NOT, H_XM, T_TRUTH],
      [TS_INSIDE_NOT, H_MIND, TS_FUTURE]
    ];
    _mThreeSequences[A_INTERRUPT] = [
      [A_INTERRUPT, H_ENLIGHTENMENT, T_TECHNOLOGY]
    ];
    _mThreeSequences[A_JOURNEY] = [
      [A_JOURNEY, TS_INSIDE_NOT, H_SOUL_SPIRIT]
    ];
    _mThreeSequences[FD_LEAD] = [
      [FD_LEAD, H_ENLIGHTENMENT, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE],
      [FD_LEAD, H_RESISTANCE_STRUGGLE, T_QUESTION]
    ];
    _mThreeSequences[A_LIBERATE] = [
      [A_LIBERATE, H_BEING_HUMAN, TS_FUTURE],
      [A_LIBERATE, H_PORTAL, CE_POTENTIAL]
    ];
    _mThreeSequences[T_LIE] = [
      [T_LIE, FD_EQUAL, TS_FUTURE]
    ];
    _mThreeSequences[FD_LOSE_LOSS] = [
      [FD_LOSE_LOSS, A_ATTACK_WAR, A_RETREAT]
    ];
    _mThreeSequences[H_MIND] = [
      [H_MIND, TS_OPEN_ACCEPT, A_BREATHE_LIVE]
    ];
    _mThreeSequences[CE_NATURE] = [
      [CE_NATURE, CE_PURE_PURITY, A_DEFEND]
    ];
    _mThreeSequences[CE_NZEER] = [
      [CE_NZEER, T_TECHNOLOGY, CE_BALANCE_PERFECTION]
    ];
    _mThreeSequences[TS_OPEN_ALL] = [
      [TS_OPEN_ALL, H_PORTAL, FD_EVOLUTION_PROGRESS_SUCCESS]
    ];
    _mThreeSequences[H_OTHER_YOU_YOUR] = [
      [H_OTHER_YOU_YOUR, A_HIDE, CE_CHAOS_DISORDER]
    ];
    _mThreeSequences[CE_PATH] = [
      [CE_PATH, CE_HARMONY_PEACE, CE_DIFFICULT]
    ];
    _mThreeSequences[A_PURSUE_ASPIRATION] = [
      [A_PURSUE_ASPIRATION, CE_PURE_PURITY, H_BODY_SHELL]
    ];
    _mThreeSequences[T_QUESTION] = [
      [T_QUESTION, H_COLLECTIVE_SHAPERS, CE_CHAOS_DISORDER],
      [T_QUESTION, A_HIDE, T_TRUTH],
      [T_QUESTION, CE_POTENTIAL, T_MYSTERY],
      [T_QUESTION, CE_CONFLICT, T_DATA]
    ];
    _mThreeSequences[A_REACT] = [
      [A_REACT, CE_IMPURE, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE]
    ];
    _mThreeSequences[A_RETREAT] = [
      [A_RETREAT, A_SEARCH_SEEK, CE_SAFETY]
    ];
    _mThreeSequences[A_SEPERATE] = [
      [A_SEPERATE, TS_FUTURE, FD_EVOLUTION_PROGRESS_SUCCESS]
    ];
    _mThreeSequences[A_SHARE] = [
      [A_SHARE, H_ENLIGHTENMENT, T_KNOWLEDGE],
      [A_SHARE, H_RESISTANCE_STRUGGLE, T_MESSAGE]
    ];
    _mThreeSequences[T_TECHNOLOGY] = [
      [T_TECHNOLOGY, A_CAPTURE, FD_VICTORY]
    ];
    _mThreeSequences[A_TOGETHER] = [
      [A_TOGETHER, A_PURSUE_ASPIRATION, CE_SAFETY],
      [A_TOGETHER, TS_CLOSE_END_FINALITY, T_MYSTERY]
    ];
    _mThreeSequences[T_TRUTH] = [
      [T_TRUTH, A_NOURISH, H_SOUL_SPIRIT]
    ];
    _mThreeSequences[H_XM] = [
      [H_XM, A_NOURISH, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE],
      [H_XM, FD_GROW, H_CREATIVITY]
    ];
  }

  void _initFourSequences() {
    _mFourSequences = LinkedHashMap<String, List<List<String>>>();
    _mFourSequences[A_ABANDON] = [
      [A_ABANDON, T_FEAR, A_SEE, TS_FUTURE],
      [A_ABANDON, T_FEAR, A_DEFEND, TS_FUTURE]
    ];
    _mFourSequences[CE_ADAPT] = [
      [CE_ADAPT, T_IDEA_THOUGHT, A_DISCOVER, FD_EVOLUTION_PROGRESS_SUCCESS]
    ];
    _mFourSequences[A_ADVANCE] = [
      [
        A_ADVANCE,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        TS_AGAIN_REPEAT,
        FD_FAILURE
      ]
    ];
    _mFourSequences[TS_AFTER] = [
      [TS_AFTER, CE_IMPERFECT, H_BEING_HUMAN, A_PRESENCE]
    ];
    _mFourSequences[CE_ALL] = [
      [CE_ALL, CE_CHAOS_DISORDER, TS_INSIDE_NOT, H_BODY_SHELL]
    ];
    _mFourSequences[A_ATTACK_WAR] = [
      [A_ATTACK_WAR, CE_WEAK, H_COLLECTIVE_SHAPERS, T_LIE],
      [
        A_ATTACK_WAR,
        H_RESISTANCE_STRUGGLE,
        A_PURSUE_ASPIRATION,
        H_ENLIGHTENMENT
      ],
      [
        A_ATTACK_WAR,
        H_ENLIGHTENMENT,
        A_PURSUE_ASPIRATION,
        H_RESISTANCE_STRUGGLE
      ],
      [A_ATTACK_WAR, TS_INSIDE_NOT, FD_WORTH, CE_CONSEQUENCE]
    ];
    _mFourSequences[A_AVOID] = [
      [A_AVOID, H_XM, T_MESSAGE, T_LIE]
    ];
    _mFourSequences[CE_BALANCE_PERFECTION] = [
      [CE_BALANCE_PERFECTION, CE_BALANCE_PERFECTION, CE_SAFETY, CE_ALL]
    ];
    _mFourSequences[TS_BEFORE] = [
      [TS_BEFORE, T_MYSTERY, TS_AFTER, T_KNOWLEDGE]
    ];
    _mFourSequences[TS_BEGIN] = [
      [TS_BEGIN, A_JOURNEY, A_BREATHE_LIVE, H_XM],
      [TS_BEGIN, H_BEING_HUMAN, FD_LEGACY, TS_NOW_PRESENT]
    ];
    _mFourSequences[H_BEING_HUMAN] = [
      [
        H_BEING_HUMAN,
        CE_HAVE,
        CE_IMPURE,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE
      ],
      [H_BEING_HUMAN, H_SOUL_SPIRIT, CE_STRONG, CE_PURE_PURITY],
      [H_BEING_HUMAN, TS_PAST, TS_NOW_PRESENT, TS_FUTURE]
    ];
    _mFourSequences[A_BREATHE_LIVE] = [
      [A_BREATHE_LIVE, TS_AGAIN_REPEAT, A_JOURNEY, TS_AGAIN_REPEAT],
      [A_BREATHE_LIVE, CE_NATURE, CE_BALANCE_PERFECTION, CE_HARMONY_PEACE]
    ];
    _mFourSequences[A_CAPTURE] = [
      [A_CAPTURE, T_FEAR, A_DISCOVER, T_COURAGE]
    ];
    _mFourSequences[A_CHANGE_MODIFY] = [
      [A_CHANGE_MODIFY, H_BEING_HUMAN, CE_POTENTIAL, A_USE],
      [A_CHANGE_MODIFY, TS_FUTURE, A_CAPTURE, T_DESTINY],
      [A_CHANGE_MODIFY, H_BODY_SHELL, FD_IMPROVE, H_BEING_HUMAN],
      [A_CHANGE_MODIFY, T_PERSPECTIVE, TS_BEGIN, TS_NEW]
    ];
    _mFourSequences[CE_CHAOS_DISORDER] = [
      [CE_CHAOS_DISORDER, CE_BARRIER_OBSTACLE, H_COLLECTIVE_SHAPERS, H_PORTAL]
    ];
    _mFourSequences[CE_CLEAR] = [
      [CE_CLEAR, H_MIND, TS_OPEN_ACCEPT, H_MIND]
    ];
    _mFourSequences[TS_CLEAR_ALL] = [
      [TS_CLEAR_ALL, TS_OPEN_ALL, A_DISCOVER, T_TRUTH]
    ];
    _mFourSequences[H_COLLECTIVE_SHAPERS] = [
      [
        H_COLLECTIVE_SHAPERS,
        A_SEE,
        CE_POTENTIAL,
        FD_EVOLUTION_PROGRESS_SUCCESS
      ],
      [H_COLLECTIVE_SHAPERS, H_PORTAL, H_MIND, A_RESTRAINT],
      [H_COLLECTIVE_SHAPERS, TS_PAST, TS_NOW_PRESENT, TS_FUTURE],
      [H_COLLECTIVE_SHAPERS, H_MIND, CE_COMPLEX, CE_HARMONY_PEACE],
      [H_COLLECTIVE_SHAPERS, CE_HAVE, CE_STRONG, CE_PATH],
      [H_COLLECTIVE_SHAPERS, CE_CHAOS_DISORDER, CE_PURE_PURITY, CE_HARM],
      [
        H_COLLECTIVE_SHAPERS,
        T_MESSAGE,
        TS_CLOSE_END_FINALITY,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE
      ]
    ];
    _mFourSequences[CE_COMPLEX] = [
      [
        CE_COMPLEX,
        H_COLLECTIVE_SHAPERS,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        CE_STRONG
      ]
    ];
    _mFourSequences[T_CONTEMPLATE] = [
      [T_CONTEMPLATE, CE_COMPLEX, H_COLLECTIVE_SHAPERS, T_TRUTH],
      [
        T_CONTEMPLATE,
        CE_COMPLEX,
        H_COLLECTIVE_SHAPERS,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE
      ],
      [T_CONTEMPLATE, H_INDIVIDUAL_SELF, H_COLLECTIVE_SHAPERS, T_TRUTH],
      [T_CONTEMPLATE, H_INDIVIDUAL_SELF, CE_PATH, T_TRUTH]
    ];
    _mFourSequences[T_COURAGE] = [
      [T_COURAGE, A_ATTACK_WAR, H_COLLECTIVE_SHAPERS, TS_FUTURE]
    ];
    _mFourSequences[A_CREATE_CREATION] = [
      [A_CREATE_CREATION, TS_DISTANCE_OUTSIDE, CE_IMPURE, CE_PATH],
      [A_CREATE_CREATION, TS_FUTURE, TS_INSIDE_NOT, A_ATTACK_WAR],
      [A_CREATE_CREATION, TS_FUTURE, A_CHANGE_MODIFY, T_DESTINY],
      [A_CREATE_CREATION, TS_NEW, H_PORTAL, CE_POTENTIAL]
    ];
    _mFourSequences[A_DEFEND] = [
      [A_DEFEND, H_BEING_HUMAN, CE_NZEER, T_LIE],
      [A_DEFEND, T_MESSAGE, T_ANSWER, T_IDEA_THOUGHT]
    ];
    _mFourSequences[A_DESTROY_DESTRUCTION] = [
      [A_DESTROY_DESTRUCTION, T_DESTINY, H_BEING_HUMAN, T_LIE],
      [A_DESTROY_DESTRUCTION, CE_COMPLEX, H_COLLECTIVE_SHAPERS, T_LIE]
    ];
    _mFourSequences[FD_DETERIORATE_ERODE] = [
      [FD_DETERIORATE_ERODE, H_BEING_HUMAN, CE_WEAK, A_REBEL]
    ];
    _mFourSequences[CE_DIFFICULT] = [
      [CE_DIFFICULT, T_KNOWLEDGE, A_ADVANCE, H_MIND]
    ];
    _mFourSequences[H_ENLIGHTENMENT] = [
      [H_ENLIGHTENMENT, A_CAPTURE, FD_VICTORY, A_TOGETHER]
    ];
    _mFourSequences[A_ESCAPE] = [
      [A_ESCAPE, CE_SIMPLE, H_BEING_HUMAN, TS_FUTURE],
      [A_ESCAPE, TS_BEFORE, CE_PURE_PURITY, A_DIE_DEATH]
    ];
    _mFourSequences[T_FEAR] = [
      [T_FEAR, CE_IMPERFECT, CE_NZEER, T_TECHNOLOGY]
    ];
    _mFourSequences[FD_FOLLOW] = [
      [FD_FOLLOW, H_COLLECTIVE_SHAPERS, H_PORTAL, T_MESSAGE]
    ];
    _mFourSequences[T_FORGET] = [
      [T_FORGET, CE_CONFLICT, TS_OPEN_ACCEPT, A_ATTACK_WAR]
    ];
    _mFourSequences[FD_GAIN] = [
      [FD_GAIN, H_PORTAL, A_ATTACK_WAR, CE_WEAK]
    ];
    _mFourSequences[CE_HARMONY_PEACE] = [
      [CE_HARMONY_PEACE, CE_PATH, A_NOURISH, TS_NOW_PRESENT]
    ];
    _mFourSequences[A_HELP] = [
      [A_HELP, FD_GAIN, A_CREATE_CREATION, A_PURSUE_ASPIRATION],
      [A_HELP, H_COLLECTIVE_SHAPERS, A_CREATE_CREATION, TS_FUTURE],
      [A_HELP, H_US_WE, A_SAVE_RESCUE, H_US_WE],
      [A_HELP, H_ENLIGHTENMENT, CE_STRONG, FD_VICTORY],
      [A_HELP, H_RESISTANCE_STRUGGLE, CE_STRONG, FD_VICTORY]
    ];
    _mFourSequences[A_HIDE] = [
      [A_HIDE, CE_IMPURE, H_BEING_HUMAN, T_IDEA_THOUGHT],
      [A_HIDE, CE_CHAOS_DISORDER, TS_INSIDE_NOT, H_BODY_SHELL]
    ];
    _mFourSequences[T_IGNORE] = [
      [T_IGNORE, H_BEING_HUMAN, CE_CHAOS_DISORDER, T_LIE]
    ];
    _mFourSequences[FD_IMPROVE] = [
      [FD_IMPROVE, H_BODY_SHELL, A_PURSUE_ASPIRATION, A_JOURNEY],
      [FD_IMPROVE, H_BODY_SHELL, H_MIND, H_SOUL_SPIRIT],
      [FD_IMPROVE, H_MIND, A_JOURNEY, TS_INSIDE_NOT]
    ];
    _mFourSequences[TS_INSIDE_NOT] = [
      [TS_INSIDE_NOT, H_MIND, A_JOURNEY, CE_BALANCE_PERFECTION]
    ];
    _mFourSequences[A_INTERRUPT] = [
      [A_INTERRUPT, T_MESSAGE, FD_GAIN, A_ADVANCE]
    ];
    _mFourSequences[A_JOURNEY] = [
      [A_JOURNEY, TS_INSIDE_NOT, FD_IMPROVE, H_SOUL_SPIRIT]
    ];
    _mFourSequences[T_KNOWLEDGE] = [
      [T_KNOWLEDGE, A_HELP, FD_GAIN, FD_VICTORY]
    ];
    _mFourSequences[FD_LEAD] = [
      [FD_LEAD, A_PURSUE_ASPIRATION, A_REACT, A_DEFEND]
    ];
    _mFourSequences[FD_LESS] = [
      [FD_LESS, CE_CHAOS_DISORDER, FD_MORE, CE_STABILITY_STAY],
      [FD_LESS, T_TRUTH, FD_MORE, CE_CHAOS_DISORDER],
      [FD_LESS, H_SOUL_SPIRIT, FD_MORE, H_MIND]
    ];
    _mFourSequences[A_LIBERATE] = [
      [A_LIBERATE, H_XM, H_PORTAL, A_TOGETHER]
    ];
    _mFourSequences[A_LIVE_AGAIN] = [
      [
        A_LIVE_AGAIN,
        A_DIE_DEATH,
        TS_AGAIN_REPEAT,
        FD_EVOLUTION_PROGRESS_SUCCESS
      ]
    ];
    _mFourSequences[FD_LOSE_LOSS] = [
      [FD_LOSE_LOSS, CE_DANGER, FD_GAIN, CE_SAFETY]
    ];
    _mFourSequences[A_NOURISH] = [
      [A_NOURISH, H_XM, A_CREATE_CREATION, T_IDEA_THOUGHT]
    ];
    _mFourSequences[CE_NZEER] = [
      [CE_NZEER, FD_LEGACY, A_CREATE_CREATION, TS_FUTURE],
      [CE_NZEER, H_COLLECTIVE_SHAPERS, H_RESISTANCE_STRUGGLE, T_KNOWLEDGE],
      [CE_NZEER, T_TECHNOLOGY, H_MIND, FD_EVOLUTION_PROGRESS_SUCCESS],
      [CE_NZEER, T_TECHNOLOGY, CE_BALANCE_PERFECTION, T_TRUTH]
    ];
    _mFourSequences[TS_PAST] = [
      [TS_PAST, TS_AGAIN_REPEAT, TS_NOW_PRESENT, TS_AGAIN_REPEAT]
    ];
    _mFourSequences[CE_PATH] = [
      [CE_PATH, A_RESTRAINT, CE_STRONG, CE_SAFETY]
    ];
    _mFourSequences[H_PORTAL] = [
      [H_PORTAL, CE_HAVE, T_TRUTH, T_DATA],
      [H_PORTAL, CE_POTENTIAL, A_CHANGE_MODIFY, TS_FUTURE],
      [
        H_PORTAL,
        A_DIE_DEATH,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        A_DIE_DEATH
      ],
      [
        H_PORTAL,
        A_CHANGE_MODIFY,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        TS_CLOSE_END_FINALITY
      ]
    ];
    _mFourSequences[T_QUESTION] = [
      [T_QUESTION, T_TRUTH, FD_GAIN, TS_FUTURE]
    ];
    _mFourSequences[H_RESISTANCE_STRUGGLE] = [
      [H_RESISTANCE_STRUGGLE, FD_IMPROVE, H_BEING_HUMAN, H_SOUL_SPIRIT],
      [H_RESISTANCE_STRUGGLE, A_DEFEND, H_COLLECTIVE_SHAPERS, CE_DANGER]
    ];
    _mFourSequences[A_RESTRAINT] = [
      [A_RESTRAINT, CE_PATH, FD_GAIN, CE_HARMONY_PEACE],
      [A_RESTRAINT, T_FEAR, A_AVOID, CE_DANGER]
    ];
    _mFourSequences[A_SEARCH_SEEK] = [
      [
        A_SEARCH_SEEK,
        T_TRUTH,
        A_SAVE_RESCUE,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE
      ],
      [A_SEARCH_SEEK, H_XM, A_SAVE_RESCUE, H_PORTAL],
      [A_SEARCH_SEEK, T_DATA, A_DISCOVER, CE_PATH],
      [A_SEARCH_SEEK, H_XM, TS_DISTANCE_OUTSIDE, TS_DESTINATION]
    ];
    _mFourSequences[A_SEE] = [
      [A_SEE, T_TRUTH, A_SEE, TS_FUTURE]
    ];
    _mFourSequences[A_SEPERATE] = [
      [A_SEPERATE, CE_WEAK, T_IGNORE, T_TRUTH]
    ];
    _mFourSequences[CE_SIMPLE] = [
      [
        CE_SIMPLE,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        CE_IMPURE,
        CE_WEAK
      ],
      [CE_SIMPLE, T_TRUTH, A_BREATHE_LIVE, CE_NATURE],
      [CE_SIMPLE, T_MESSAGE, CE_COMPLEX, T_IDEA_THOUGHT]
    ];
    _mFourSequences[H_SOUL_SPIRIT] = [
      [H_SOUL_SPIRIT, A_REBEL, H_BEING_HUMAN, A_DIE_DEATH]
    ];
    _mFourSequences[CE_STABILITY_STAY] = [
      [CE_STABILITY_STAY, A_TOGETHER, A_DEFEND, T_TRUTH]
    ];
    _mFourSequences[CE_STRONG] = [
      [CE_STRONG, T_IDEA_THOUGHT, A_PURSUE_ASPIRATION, T_TRUTH],
      [CE_STRONG, A_TOGETHER, A_AVOID, A_ATTACK_WAR],
      [CE_STRONG, H_RESISTANCE_STRUGGLE, A_CAPTURE, H_PORTAL]
    ];
    _mFourSequences[T_TECHNOLOGY] = [
      [T_TECHNOLOGY, T_INTELLIGENCE, A_SEE, CE_ALL],
      [T_TECHNOLOGY, T_INTELLIGENCE, FD_GROW, CE_UNBOUNDED]
    ];
    _mFourSequences[A_TOGETHER] = [
      [A_TOGETHER, A_DISCOVER, CE_HARMONY_PEACE, FD_EQUAL]
    ];
    _mFourSequences[T_TRUTH] = [
      [T_TRUTH, T_IDEA_THOUGHT, A_DISCOVER, H_XM]
    ];
//        _mFourSequences[UNITY,new String[][]{
//                {UNITY,A_TOGETHER,TS_CLOSE_END_FINALITY,T_MYSTERY}
//        });
    _mFourSequences[H_XM] = [
      [H_XM, CE_HAVE, H_MIND, A_JOURNEY],
      [H_XM, A_DIE_DEATH, CE_CHAOS_DISORDER, A_BREATHE_LIVE],
      [H_XM, A_NOURISH, CE_STRONG, T_IDEA_THOUGHT],
      [H_XM, A_SHARE, H_OTHER_YOU_YOUR, T_IDEA_THOUGHT]
    ];
  }

  void _initFiveSequences() {
    _mFiveSequences = LinkedHashMap<String, List<List<String>>>();
    _mFiveSequences[A_ABANDON] = [
      [A_ABANDON, CE_ALL, T_TECHNOLOGY, A_SAVE_RESCUE, H_US_WE],
      [A_ABANDON, T_FEAR, A_DEFEND, TS_FUTURE, A_TOGETHER],
      [A_ABANDON, T_FEAR, A_SEE, TS_FUTURE, TS_DESTINATION]
    ];
    _mFiveSequences[CE_ADAPT] = [
      [
        CE_ADAPT,
        T_IDEA_THOUGHT,
        A_DISCOVER,
        CE_SIMPLE,
        FD_EVOLUTION_PROGRESS_SUCCESS
      ]
    ];
    _mFiveSequences[A_ADVANCE] = [
      [
        A_ADVANCE,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        A_PURSUE_ASPIRATION,
        H_COLLECTIVE_SHAPERS,
        CE_PATH
      ]
    ];
    _mFiveSequences[TS_AFTER] = [
      [TS_AFTER, CE_IMPERFECT, H_BEING_HUMAN, A_PRESENCE, CE_CONSEQUENCE]
    ];
    _mFiveSequences[T_ANSWER] = [
      [T_ANSWER, T_QUESTION, A_DISCOVER, CE_DIFFICULT, T_TRUTH],
      [T_ANSWER, CE_NZEER, T_QUESTION, CE_POTENTIAL, T_KNOWLEDGE]
    ];
    _mFiveSequences[A_AVOID] = [
      [
        A_AVOID,
        CE_CHAOS_DISORDER,
        A_RECHARGE_REPAIR,
        CE_POTENTIAL,
        A_ATTACK_WAR
      ],
      [
        A_AVOID,
        CE_BALANCE_PERFECTION,
        CE_STABILITY_STAY,
        H_BEING_HUMAN,
        H_INDIVIDUAL_SELF
      ],
      [A_AVOID, CE_CHAOS_DISORDER, A_AVOID, H_COLLECTIVE_SHAPERS, T_LIE]
    ];
    _mFiveSequences[TS_BEGIN] = [
      [TS_BEGIN, A_JOURNEY, A_BREATHE_LIVE, H_XM, FD_EVOLUTION_PROGRESS_SUCCESS]
    ];
    _mFiveSequences[H_BEING_HUMAN] = [
      [
        H_BEING_HUMAN,
        H_COLLECTIVE_SHAPERS,
        A_TOGETHER,
        A_CREATE_CREATION,
        T_DESTINY
      ],
      [
        H_BEING_HUMAN,
        TS_INSIDE_NOT,
        A_TOGETHER,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        FD_DETERIORATE_ERODE
      ],
      [H_BEING_HUMAN, FD_LEGACY, A_ABANDON, TS_OLD, T_KNOWLEDGE],
      [H_BEING_HUMAN, FD_LEGACY, CE_HAVE, A_ABANDON, TS_NOW_PRESENT]
    ];
    _mFiveSequences[A_BREATHE_LIVE] = [
      [A_BREATHE_LIVE, TS_INSIDE_NOT, H_XM, FD_LOSE_LOSS, H_INDIVIDUAL_SELF]
    ];
    _mFiveSequences[A_CAPTURE] = [
      [A_CAPTURE, H_PORTAL, A_DEFEND, H_PORTAL, T_COURAGE]
    ];
    _mFiveSequences[A_CHANGE_MODIFY] = [
      [A_CHANGE_MODIFY, T_PERSPECTIVE, TS_BEGIN, TS_NEW, H_RESISTANCE_STRUGGLE]
    ];
    _mFiveSequences[CE_CHAOS_DISORDER] = [
      [
        CE_CHAOS_DISORDER,
        A_ATTACK_WAR,
        CE_CONFLICT,
        A_DISCOVER,
        CE_HARMONY_PEACE
      ]
    ];
    _mFiveSequences[CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE] = [
      [
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        A_DIE_DEATH,
        TS_NEW,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        TS_BEGIN
      ]
    ];
    _mFiveSequences[CE_CLEAR] = [
      [CE_CLEAR, H_MIND, A_LIBERATE, CE_BARRIER_OBSTACLE, H_BODY_SHELL],
      [CE_CLEAR, H_OTHER_YOU_YOUR, H_MIND, T_IDEA_THOUGHT, FD_GROW],
      [CE_CLEAR, H_OTHER_YOU_YOUR, H_MIND, FD_MORE, CE_BALANCE_PERFECTION]
    ];
    _mFiveSequences[TS_CLEAR_ALL] = [
      [TS_CLEAR_ALL, T_IDEA_THOUGHT, TS_PAST, TS_NOW_PRESENT, TS_FUTURE]
    ];
    _mFiveSequences[TS_CLOSE_END_FINALITY] = [
      [
        TS_CLOSE_END_FINALITY,
        TS_OLD,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        A_CREATE_CREATION,
        TS_NEW
      ]
    ];
    _mFiveSequences[H_COLLECTIVE_SHAPERS] = [
      [H_COLLECTIVE_SHAPERS, H_PORTAL, T_DATA, A_REACT, CE_CHAOS_DISORDER],
      [
        H_COLLECTIVE_SHAPERS,
        H_PORTAL,
        T_MESSAGE,
        A_DESTROY_DESTRUCTION,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE
      ],
      [H_COLLECTIVE_SHAPERS, T_WANT_DESIRE, H_BEING_HUMAN, H_MIND, TS_FUTURE],
      [H_COLLECTIVE_SHAPERS, FD_LEAD, H_BEING_HUMAN, CE_COMPLEX, A_JOURNEY],
      [H_COLLECTIVE_SHAPERS, A_SEE, CE_COMPLEX, CE_PATH, T_DESTINY]
    ];
    _mFiveSequences[T_CONTEMPLATE] = [
      [T_CONTEMPLATE, TS_FUTURE, TS_INSIDE_NOT, H_COLLECTIVE_SHAPERS, CE_PATH],
      [T_CONTEMPLATE, A_RESTRAINT, A_DISCOVER, FD_MORE, T_COURAGE]
    ];
    _mFiveSequences[T_COURAGE] = [
      [T_COURAGE, A_ATTACK_WAR, H_COLLECTIVE_SHAPERS, H_PORTAL, A_TOGETHER]
    ];
    _mFiveSequences[A_CREATE_CREATION] = [
      [A_CREATE_CREATION, TS_NEW, TS_FUTURE, A_SEE, CE_ALL],
      [A_CREATE_CREATION, TS_NEW, H_PORTAL, CE_POTENTIAL, TS_FUTURE],
      [
        A_CREATE_CREATION,
        CE_PURE_PURITY,
        TS_FUTURE,
        TS_INSIDE_NOT,
        A_ATTACK_WAR
      ],
      [
        A_CREATE_CREATION,
        CE_PURE_PURITY,
        TS_FUTURE,
        H_BEING_HUMAN,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE
      ],
      [A_CREATE_CREATION, A_SEPERATE, CE_PATH, TS_CLOSE_END_FINALITY, A_JOURNEY]
    ];
    _mFiveSequences[A_DEFEND] = [
      [
        A_DEFEND,
        T_DESTINY,
        A_DEFEND,
        H_BEING_HUMAN,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE
      ],
      [
        A_DEFEND,
        H_BEING_HUMAN,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        H_XM,
        T_MESSAGE
      ],
      [
        A_DEFEND,
        H_BEING_HUMAN,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        H_PORTAL,
        T_DATA
      ],
      [
        A_DEFEND,
        H_BEING_HUMAN,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        H_COLLECTIVE_SHAPERS,
        T_LIE
      ],
      [
        A_DEFEND,
        H_BEING_HUMAN,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        H_COLLECTIVE_SHAPERS,
        H_PORTAL
      ]
    ];
    _mFiveSequences[A_DESTROY_DESTRUCTION] = [
      [A_DESTROY_DESTRUCTION, T_LIE, TS_INSIDE_NOT, FD_GAIN, H_SOUL_SPIRIT],
      [
        A_DESTROY_DESTRUCTION,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        TS_CLOSE_END_FINALITY,
        CE_CONFLICT,
        A_ATTACK_WAR
      ]
    ];
    _mFiveSequences[TS_DISTANCE_OUTSIDE] = [
      [TS_DISTANCE_OUTSIDE, H_INDIVIDUAL_SELF, A_AVOID, H_BEING_HUMAN, T_LIE]
    ];
    _mFiveSequences[CE_EASY] = [
      [CE_EASY, CE_PATH, TS_FUTURE, FD_FOLLOW, H_COLLECTIVE_SHAPERS]
    ];
    _mFiveSequences[A_ESCAPE] = [
      [A_ESCAPE, H_BODY_SHELL, A_JOURNEY, TS_DISTANCE_OUTSIDE, TS_NOW_PRESENT],
      [A_ESCAPE, H_BODY_SHELL, H_MIND, H_INDIVIDUAL_SELF, T_WANT_DESIRE],
      [A_ESCAPE, TS_BEFORE, A_DIE_DEATH, TS_CLOSE_END_FINALITY, CE_ALL]
    ];
    _mFiveSequences[T_FORGET] = [
      [T_FORGET, TS_PAST, A_SEE, TS_NOW_PRESENT, CE_DANGER],
      [T_FORGET, A_ATTACK_WAR, A_SEE, TS_DISTANCE_OUTSIDE, CE_HARMONY_PEACE]
    ];
    _mFiveSequences[FD_GAIN] = [
      [FD_GAIN, T_TRUTH, TS_OPEN_ACCEPT, H_BEING_HUMAN, H_SOUL_SPIRIT]
    ];
    _mFiveSequences[FD_GROW] = [
      [FD_GROW, CE_UNBOUNDED, A_CREATE_CREATION, TS_NEW, TS_FUTURE]
    ];
    _mFiveSequences[CE_HAVE] = [
      [CE_HAVE, CE_HARMONY_PEACE, A_TOGETHER, TS_CLOSE_END_FINALITY, T_MYSTERY]
    ];
    _mFiveSequences[A_HELP] = [
      [A_HELP, H_ENLIGHTENMENT, A_CAPTURE, CE_ALL, H_PORTAL],
      [A_HELP, H_RESISTANCE_STRUGGLE, A_CAPTURE, CE_ALL, H_PORTAL],
      [
        A_HELP,
        H_BEING_HUMAN,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        A_PURSUE_ASPIRATION,
        T_DESTINY
      ],
      [A_HELP, FD_GAIN, T_KNOWLEDGE, TS_INSIDE_NOT, H_RESISTANCE_STRUGGLE],
      [A_HELP, H_US_WE, FD_EVOLUTION_PROGRESS_SUCCESS, CE_STRONG, FD_VICTORY],
      [A_HELP, H_US_WE, A_SAVE_RESCUE, H_US_WE, CE_ALL],
      [A_HELP, H_US_WE, A_SAVE_RESCUE, H_US_WE, A_DESTROY_DESTRUCTION]
    ];
    _mFiveSequences[A_HIDE] = [
      [A_HIDE, H_RESISTANCE_STRUGGLE, A_ADVANCE, CE_STRONG, A_TOGETHER],
      [A_HIDE, H_THEM, TS_INSIDE_NOT, CE_COMPLEX, T_INTELLIGENCE]
    ];
    _mFiveSequences[CE_IMPERFECT] = [
      [CE_IMPERFECT, T_TRUTH, TS_OPEN_ACCEPT, CE_COMPLEX, T_ANSWER],
      [CE_IMPERFECT, T_MESSAGE, TS_BEGIN, H_BEING_HUMAN, CE_CHAOS_DISORDER],
      [CE_IMPERFECT, H_XM, T_MESSAGE, H_BEING_HUMAN, CE_CHAOS_DISORDER]
    ];
    _mFiveSequences[TS_INSIDE_NOT] = [
      [TS_INSIDE_NOT, H_MIND, TS_INSIDE_NOT, H_SOUL_SPIRIT, CE_HARMONY_PEACE]
    ];
    _mFiveSequences[A_INTERRUPT] = [
      [A_INTERRUPT, H_ENLIGHTENMENT, T_TECHNOLOGY, A_CAPTURE, FD_VICTORY],
      [A_INTERRUPT, T_MESSAGE, FD_LOSE_LOSS, H_COLLECTIVE_SHAPERS, CE_POTENTIAL]
    ];
    _mFiveSequences[A_LIBERATE] = [
      [A_LIBERATE, H_PORTAL, A_LIBERATE, H_BEING_HUMAN, H_MIND],
      [
        A_LIBERATE,
        H_INDIVIDUAL_SELF,
        A_LIBERATE,
        H_BEING_HUMAN,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE
      ]
    ];
    _mFiveSequences[A_LIVE_AGAIN] = [
      [
        A_LIVE_AGAIN,
        A_DIE_DEATH,
        TS_AGAIN_REPEAT,
        FD_EVOLUTION_PROGRESS_SUCCESS,
        TS_FUTURE
      ]
    ];
    _mFiveSequences[FD_LOSE_LOSS] = [
      [
        FD_LOSE_LOSS,
        H_COLLECTIVE_SHAPERS,
        T_MESSAGE,
        FD_GAIN,
        CE_CHAOS_DISORDER
      ]
    ];
    _mFiveSequences[H_MIND] = [
      [H_MIND, H_BODY_SHELL, H_SOUL_SPIRIT, CE_PURE_PURITY, H_BEING_HUMAN],
      [H_MIND, T_TECHNOLOGY, A_CAPTURE, H_BEING_HUMAN, H_SOUL_SPIRIT]
    ];
    _mFiveSequences[FD_MORE] = [
      [FD_MORE, T_DATA, FD_GAIN, H_PORTAL, A_ADVANCE]
    ];
    _mFiveSequences[T_MYSTERY] = [
      [T_MYSTERY, TS_BEFORE, CE_PURE_PURITY, T_KNOWLEDGE, TS_AFTER]
    ];
    _mFiveSequences[TS_NOW_PRESENT] = [
      [
        TS_NOW_PRESENT,
        CE_CHAOS_DISORDER,
        A_CREATE_CREATION,
        TS_FUTURE,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE
      ],
      [TS_NOW_PRESENT, H_RESISTANCE_STRUGGLE, FD_WORTH, CE_STRONG, FD_VICTORY]
    ];
    _mFiveSequences[CE_NZEER] = [
      [CE_NZEER, A_HIDE, H_US_WE, FD_EQUAL, H_THEM]
    ];
    _mFiveSequences[TS_OLD] = [
      [TS_OLD, CE_NATURE, FD_LESS, CE_STRONG, TS_NOW_PRESENT]
    ];
    _mFiveSequences[H_OTHER_YOU_YOUR] = [
      [H_OTHER_YOU_YOUR, A_HIDE, CE_CHAOS_DISORDER, TS_INSIDE_NOT, H_BODY_SHELL]
    ];
    _mFiveSequences[TS_PAST] = [
      [
        TS_PAST,
        CE_CHAOS_DISORDER,
        A_CREATE_CREATION,
        TS_FUTURE,
        CE_HARMONY_PEACE
      ],
      [TS_PAST, CE_PATH, A_CREATE_CREATION, TS_FUTURE, A_JOURNEY]
    ];
    _mFiveSequences[H_PORTAL] = [
      [H_PORTAL, A_CREATE_CREATION, CE_DANGER, A_PURSUE_ASPIRATION, CE_SAFETY],
      [H_PORTAL, CE_POTENTIAL, A_HELP, H_BEING_HUMAN, TS_FUTURE],
      [
        H_PORTAL,
        CE_BARRIER_OBSTACLE,
        A_DEFEND,
        H_BEING_HUMAN,
        H_COLLECTIVE_SHAPERS
      ],
      [
        H_PORTAL,
        FD_IMPROVE,
        H_BEING_HUMAN,
        TS_FUTURE,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE
      ]
    ];
    _mFiveSequences[A_PRESENCE] = [
      [A_PRESENCE, T_LIE, FD_EQUAL, TS_FUTURE, CE_CONSEQUENCE]
    ];
    _mFiveSequences[CE_PURE_PURITY] = [
      [
        CE_PURE_PURITY,
        H_BEING_HUMAN,
        FD_FAILURE,
        TS_NOW_PRESENT,
        CE_CHAOS_DISORDER
      ]
    ];
    _mFiveSequences[A_PURSUE_ASPIRATION] = [
      [
        A_PURSUE_ASPIRATION,
        CE_PATH,
        TS_DISTANCE_OUTSIDE,
        H_COLLECTIVE_SHAPERS,
        T_LIE
      ],
      [
        A_PURSUE_ASPIRATION,
        CE_CONFLICT,
        A_ATTACK_WAR,
        A_ADVANCE,
        CE_CHAOS_DISORDER
      ]
    ];
    _mFiveSequences[T_QUESTION] = [
      [T_QUESTION, FD_LESS, T_FORGET, CE_ALL, T_LIE],
      [T_QUESTION, CE_POTENTIAL, T_MYSTERY, A_JOURNEY, FD_GROW]
    ];
    _mFiveSequences[A_REACT] = [
      [A_REACT, A_REBEL, T_QUESTION, H_COLLECTIVE_SHAPERS, T_LIE]
    ];
    _mFiveSequences[A_REBEL] = [
      [
        A_REBEL,
        T_IDEA_THOUGHT,
        FD_EVOLUTION_PROGRESS_SUCCESS,
        T_DESTINY,
        TS_NOW_PRESENT
      ]
    ];
    _mFiveSequences[A_RECHARGE_REPAIR] = [
      [
        A_RECHARGE_REPAIR,
        TS_NOW_PRESENT,
        A_RECHARGE_REPAIR,
        H_BEING_HUMAN,
        H_SOUL_SPIRIT
      ],
      [A_RECHARGE_REPAIR, H_SOUL_SPIRIT, FD_LESS, H_BEING_HUMAN, CE_HARM]
    ];
    _mFiveSequences[A_SAVE_RESCUE] = [
      [
        A_SAVE_RESCUE,
        H_BEING_HUMAN,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE,
        A_DESTROY_DESTRUCTION,
        H_PORTAL
      ]
    ];
    _mFiveSequences[A_SEARCH_SEEK] = [
      [A_SEARCH_SEEK, T_DESTINY, A_CREATE_CREATION, CE_PURE_PURITY, TS_FUTURE]
    ];
    _mFiveSequences[A_SEE] = [
      [A_SEE, T_TRUTH, A_SEE, TS_FUTURE, TS_BEGIN]
    ];
    _mFiveSequences[A_SEPERATE] = [
      [A_SEPERATE, H_MIND, H_BODY_SHELL, A_DISCOVER, H_ENLIGHTENMENT],
      [A_SEPERATE, T_TRUTH, T_LIE, H_COLLECTIVE_SHAPERS, TS_FUTURE]
    ];
    _mFiveSequences[CE_SIMPLE] = [
      [
        CE_SIMPLE,
        T_TRUTH,
        H_COLLECTIVE_SHAPERS,
        A_DESTROY_DESTRUCTION,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE
      ],
      [CE_SIMPLE, T_TRUTH, T_FORGET, CE_EASY, FD_EVOLUTION_PROGRESS_SUCCESS],
      [CE_SIMPLE, TS_OLD, T_TRUTH, A_JOURNEY, TS_INSIDE_NOT]
    ];
    _mFiveSequences[CE_STABILITY_STAY] = [
      [
        CE_STABILITY_STAY,
        CE_STRONG,
        A_TOGETHER,
        A_DEFEND,
        H_RESISTANCE_STRUGGLE
      ]
    ];
    _mFiveSequences[CE_STRONG] = [
      [CE_STRONG, A_TOGETHER, A_ATTACK_WAR, A_TOGETHER, T_DESTINY],
      [CE_STRONG, A_TOGETHER, A_ATTACK_WAR, A_TOGETHER, CE_CHAOS_DISORDER]
    ];
    _mFiveSequences[T_TECHNOLOGY] = [
      [T_TECHNOLOGY, T_INTELLIGENCE, A_SEE, CE_ALL, CE_UNBOUNDED]
    ];
    _mFiveSequences[A_TOGETHER] = [
      [
        A_TOGETHER,
        FD_EVOLUTION_PROGRESS_SUCCESS,
        A_RECHARGE_REPAIR,
        TS_NOW_PRESENT,
        A_JOURNEY
      ]
    ];
    _mFiveSequences[A_USE] = [
      [A_USE, A_RESTRAINT, FD_FOLLOW, CE_EASY, CE_PATH],
      [A_USE, H_MIND, A_USE, T_COURAGE, A_CHANGE_MODIFY]
    ];
    _mFiveSequences[T_WANT_DESIRE] = [
      [T_WANT_DESIRE, TS_NEW, TS_DESTINATION, T_IGNORE, CE_CONSEQUENCE],
      [T_WANT_DESIRE, T_TRUTH, A_PURSUE_ASPIRATION, CE_DIFFICULT, CE_PATH]
    ];
    _mFiveSequences[CE_WEAK] = [
      [
        CE_WEAK,
        H_BEING_HUMAN,
        T_DESTINY,
        A_DESTROY_DESTRUCTION,
        CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE
      ]
    ];
    _mFiveSequences[H_XM] = [
      [H_XM, CE_PATH, TS_FUTURE, T_DESTINY, CE_HARMONY_PEACE],
      [H_XM, A_CREATE_CREATION, CE_COMPLEX, H_BEING_HUMAN, T_DESTINY],
      [H_XM, H_PORTAL, A_SHARE, H_OTHER_YOU_YOUR, T_IDEA_THOUGHT]
    ];
    _mFiveSequences[H_OTHER_YOU_YOUR] = [
      [
        H_OTHER_YOU_YOUR,
        A_HIDE,
        CE_CHAOS_DISORDER,
        TS_INSIDE_NOT,
        H_BODY_SHELL
      ],
      [H_OTHER_YOU_YOUR, T_MESSAGE, CE_CLEAR, A_USE, CE_CHAOS_DISORDER]
    ];
  }

  void _initGlyphPairs() {
    _mGlyphPairs = [
      [TS_BEFORE, TS_AFTER],
      [FD_GAIN, FD_LOSE_LOSS],
      [A_DESTROY_DESTRUCTION, A_CREATE_CREATION],
      [A_ADVANCE, A_RETREAT],
      [TS_OLD, TS_NEW],
      [A_BREATHE_LIVE, A_DIE_DEATH],
      [A_DEFEND, A_ATTACK_WAR],
      [A_PURSUE_ASPIRATION, T_WANT_DESIRE],
      [CE_PATH, CE_BARRIER_OBSTACLE],
      [T_COURAGE, T_FEAR],
      [CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, CE_NATURE],
      [A_LIBERATE, A_CAPTURE],
      [FD_LESS, FD_MORE],
      [H_MIND, H_SOUL_SPIRIT],
      [T_FORGET, T_IGNORE],
      [CE_PURE_PURITY, CE_IMPURE],
      [FD_EVOLUTION_PROGRESS_SUCCESS, FD_FAILURE],
      [FD_DETERIORATE_ERODE, FD_IMPROVE],
      [T_MESSAGE, T_DATA],
      [A_INTERRUPT, A_SUSTAIN],
      [H_US_WE, H_THEM],
      [H_I_ME, H_OTHER_YOU_YOUR],
      [FD_GROW, FD_CONTRACT_REDUCE],
      [TS_DISTANCE_OUTSIDE, TS_DESTINATION],
      [TS_PAST, TS_NOW_PRESENT, TS_FUTURE],
      [CE_ALL, TS_CLEAR_ALL, TS_OPEN_ALL]
    ];
  }

  Map<String, List<int>> getGlyphByCategory(String category) {
    if (category == C_ALL) {
      return _mBaseGlyphs;
    } else {
      List<String> glyphKeys = _mCategoryGlyphs[category];
      var categoryResult = LinkedHashMap<String, List<int>>();
      glyphKeys.forEach((key) {
        categoryResult[key] = _mBaseGlyphs[key];
      });
      return categoryResult;
    }
  }

  void dispose() {
    if (_mFiveSequences != null) {
      _mFiveSequences.clear();
      _mFiveSequences = null;
    }
    if (_mFourSequences != null) {
      _mFourSequences.clear();
      _mFourSequences = null;
    }
    if (_mThreeSequences != null) {
      _mThreeSequences.clear();
      _mThreeSequences = null;
    }
    if (_mTwoSequences != null) {
      _mTwoSequences.clear();
      _mTwoSequences = null;
    }
    if (_mGlyphPairs != null) {
      _mGlyphPairs.clear();
      _mGlyphPairs = null;
    }
  }

  Map<String, List<List<String>>> getTwoSequences() {
    if (_mTwoSequences == null) {
      _initTwoSequences();
    }
    return _mTwoSequences;
  }

  Map<String, List<List<String>>> getThreeSequences() {
    if (_mThreeSequences == null) {
      _initThreeSequences();
    }
    return _mThreeSequences;
  }

  Map<String, List<List<String>>> getFourSequences() {
    if (_mFourSequences == null) {
      _initFourSequences();
    }
    return _mFourSequences;
  }

  Map<String, List<List<String>>> getFiveSequences() {
    if (_mFiveSequences == null) {
      _initFiveSequences();
    }
    return _mFiveSequences;
  }

  List<List<String>> getGlyphPairs() {
    if (_mGlyphPairs == null) {
      _initGlyphPairs();
    }
    return _mGlyphPairs;
  }
}
