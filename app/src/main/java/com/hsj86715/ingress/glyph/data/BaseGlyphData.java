package com.hsj86715.ingress.glyph.data;

import android.support.annotation.StringDef;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hushujun on 2017/4/1.
 */

public class BaseGlyphData {
    public static final String H_BODY = "Body";
    public static final String H_ENLIGHTENMENT = "Enlightenment";
    public static final String H_HUMAN = "Human";
    public static final String H_MIND = "Mind";
    public static final String H_RESISTANCE_STRUGGLE = "Resistance/Struggle";
    public static final String H_PORTAL = "Portal";
    public static final String H_SELF = "Self";
    public static final String H_SHAPERS = "Shapers";
    public static final String H_SOUL = "Soul";
    public static final String H_XM = "XM";
    public static final String A_ADVANCE = "Advance";
    public static final String A_ATTACK_WAR = "Attack/War";
    public static final String A_AVOID = "Avoid";
    public static final String A_BREATHE_LIVE = "Breathe/Live";
    public static final String A_CAPTURE = "Capture";
    public static final String A_CHANGE = "Change";
    public static final String A_CREATE = "Create";
    public static final String A_DEFEND = "Defend";
    public static final String A_DESTROY = "Destroy";
    public static final String A_DIE = "Die";
    public static final String A_DISCOVER = "Discover";
    public static final String A_ESCAPE = "Escape";
    public static final String A_HIDE = "Hide";
    public static final String A_HELP = "Help";
    public static final String A_JOURNEY = "Journey";
    public static final String A_LIBERATE = "Liberate";
    public static final String A_NOURISH = "Nourish";
    public static final String A_PURSUE = "Pursue";
    public static final String A_REACT = "React";
    public static final String A_REBEL = "Rebel";
    public static final String A_RETREAT = "Retreat";
    public static final String A_RECHARGE_REPAIR = "Recharge/Repair";
    public static final String A_RESTRAINT = "Restraint";
    public static final String A_SAVE = "Save";
    public static final String A_SEARCH = "Search";
    public static final String A_SEE = "See";
    public static final String A_SEPERATE = "Seperate";
    public static final String A_SHARE = "Share";
    public static final String A_TOGETHER = "Together";
    public static final String A_USE = "Use";
    public static final String T_ANSWER = "Answer";
    public static final String T_CONTEMPLATE = "Contemplate";
    public static final String T_COURAGE = "Courage";
    public static final String T_DATA = "Data";
    public static final String T_DESTINY = "Destiny";
    public static final String T_FEAR = "Fear";
    public static final String T_FORGET = "Forget";
    public static final String T_IDEA_THOUGHT = "Idea/Thought";
    public static final String T_IGNORE = "Ignore";
    public static final String T_LIE = "Lie";
    public static final String T_MESSAGE = "Message";
    public static final String T_QUESTION = "Question";
    public static final String T_TRUTH = "Truth";
    public static final String T_WANT = "Want";
    public static final String FD_DETERIORATE = "Deteriorate";
    public static final String FD_EQUAL = "Equal";
    public static final String FD_EVOLUTION_SUCCESS = "Evolution/Success";
    public static final String FD_FAILURE = "Failure";
    public static final String FD_FOLLOW = "Follow";
    public static final String FD_GAIN = "Gain";
    public static final String FD_GROW = "Grow";
    public static final String FD_IMPROVE = "Improve";
    public static final String FD_LEAD = "Lead";
    public static final String FD_LESS = "Less";
    public static final String FD_LOSE = "Lose";
    public static final String FD_MORE = "More";
    public static final String FD_REDUCE = "Reduce";
    public static final String TS_AGAIN_REPEAT = "Again/Repeat";
    public static final String TS_CLOSE_ALL = "Close-All";
    public static final String TS_DISTANCE_OUTSIDE = "Distance/Outside";
    public static final String TS_END = "End";
    public static final String TS_FUTURE = "Future";
    public static final String TS_NEW = "New";
    public static final String TS_NOT_INSIDE = "Not/Inside";
    public static final String TS_NOW_PRESENT = "Now/Present";
    public static final String TS_OLD = "Old";
    public static final String TS_OPEN_ACCEPT = "Open/Accept";
    public static final String TS_OPEN_ALL = "Open-All";
    public static final String TS_PAST = "Past";
    public static final String CE_ALL = "All";
    public static final String CE_BALANCE_PERFECTION = "Balance/Perfection";
    public static final String CE_BARRIER = "Barrier";
    public static final String CE_CHAOS = "Chaos";
    public static final String CE_CIVILIZATION = "Civilization";
    public static final String CE_CLEAR = "Clear";
    public static final String CE_COMPLEX = "Complex";
    public static final String CE_CONFLICT = "Conflict";
    public static final String CE_DANGER = "Danger";
    public static final String CE_DIFFICULT = "Difficult";
    public static final String CE_EASY = "Easy";
    public static final String CE_HARM = "Harm";
    public static final String CE_HARMONY_PEACE = "Harmony/Peace";
    public static final String CE_HAVE = "Have";
    public static final String CE_IMPERFECT = "Imperfect";
    public static final String CE_IMPURE = "Impure";
    public static final String CE_NATURE = "Nature";
    public static final String CE_PATH = "Path";
    public static final String CE_POTENTIAL = "Potential";
    public static final String CE_PURE = "Pure";
    public static final String CE_SAFETY = "Safety";
    public static final String CE_SIMPLE = "Simple";
    public static final String CE_STABILITY_STAY = "Stability/Stay";
    public static final String CE_STRONG = "Strong";
    public static final String CE_WEAK = "Weak";
    public static final String C_ALL = "all";
    public static final String C_HUMAN = "human";
    public static final String C_ACTION = "action";
    public static final String C_THOUGHT = "thought";
    public static final String C_FLU_DIRE = "fluctuation/direction";
    public static final String C_TIME_SPACE = "time/space";
    public static final String C_COND_ENV = "condition/environment";
    private Map<String, int[]> mBaseGlyphs;
    private Map<String, String[]> mCategoryGlyphs;
    private Map<String, String[][]> mTwoSequences;
    private Map<String, String[][]> mThreeSequences;
    private Map<String, String[][]> mFourSequences;
    private Map<String, String[][]> mFiveSequences;
    private String[][] mGlyphPairs;

    private BaseGlyphData() {
        initBaseGlyphs();
        initGlyphCategories();
    }

    public static BaseGlyphData getInstance() {
        return GlyphDataHolder.HOLDER;
    }

    private void initBaseGlyphs() {
        mBaseGlyphs = new LinkedHashMap<>();
        mBaseGlyphs.put(H_BODY, new int[]{1, 5, 2, 1});
        mBaseGlyphs.put(H_ENLIGHTENMENT, new int[]{5, 2, 1, 5, 4, 3, 11, 9});
        mBaseGlyphs.put(H_HUMAN, new int[]{9, 7, 5, 2, 10, 9});
        mBaseGlyphs.put(H_MIND, new int[]{9, 1, 5, 7, 9});
        mBaseGlyphs.put(H_RESISTANCE_STRUGGLE, new int[]{2, 5, 4, 1, 9, 7});
        mBaseGlyphs.put(H_PORTAL, new int[]{6, 8, 7, 10, 11, 3, 2, 5, 6});
        mBaseGlyphs.put(H_SELF, new int[]{8, 9, 11});
        mBaseGlyphs.put(H_SHAPERS, new int[]{8, 7, 5, 4, 2, 10, 11});
        mBaseGlyphs.put(H_SOUL, new int[]{9, 1, 2, 10, 9});
        mBaseGlyphs.put(H_XM, new int[]{7, 5, 2, 10, 1, 7});

        mBaseGlyphs.put(A_ADVANCE, new int[]{8, 5, 4});
        mBaseGlyphs.put(A_ATTACK_WAR, new int[]{8, 5, 4, 2, 11});
        mBaseGlyphs.put(A_AVOID, new int[]{6, 4, 2, 3, 10});
        mBaseGlyphs.put(A_BREATHE_LIVE, new int[]{6, 5, 1, 2, 3});
        mBaseGlyphs.put(A_CAPTURE, new int[]{3, 10, 1, 7, 8, 9});
        mBaseGlyphs.put(A_CHANGE, new int[]{7, 1, 9, 10});
        mBaseGlyphs.put(A_CREATE, new int[]{8, 7, 1, 2, 3,});
        mBaseGlyphs.put(A_DEFEND, new int[]{6, 7, 9, 10, 3});
        mBaseGlyphs.put(A_DESTROY, new int[]{6, 5, 1, 10, 11});
        mBaseGlyphs.put(A_DIE, new int[]{8, 7, 1, 10, 11});
        mBaseGlyphs.put(A_DISCOVER, new int[]{3, 11, 9, 8});
        mBaseGlyphs.put(A_ESCAPE, new int[]{4, 3, 2, 5, 7});
        mBaseGlyphs.put(A_HIDE, new int[]{5, 2, 3, 10, 7});
        mBaseGlyphs.put(A_HELP, new int[]{6, 5, 1, 7, 10});
        mBaseGlyphs.put(A_JOURNEY, new int[]{3, 2, 1, 5, 6, 8, 9});
        mBaseGlyphs.put(A_LIBERATE, new int[]{4, 3, 2, 1, 5, 8});
        mBaseGlyphs.put(A_NOURISH, new int[]{1, 7, 8, 9, 1});
        mBaseGlyphs.put(A_PURSUE, new int[]{6, 5, 4, 2});
        mBaseGlyphs.put(A_REACT, new int[]{2, 5, 1, 10, 11});
        mBaseGlyphs.put(A_REBEL, new int[]{6, 7, 1, 2, 3, 11});
        mBaseGlyphs.put(A_RETREAT, new int[]{4, 2, 11});
        mBaseGlyphs.put(A_RECHARGE_REPAIR, new int[]{4, 6, 5, 1, 4});
        mBaseGlyphs.put(A_RESTRAINT, new int[]{6, 5, 1, 10, 11, 9});
        mBaseGlyphs.put(A_SAVE, new int[]{7, 1, 10, 3});
        mBaseGlyphs.put(A_SEARCH, new int[]{1, 2, 5, 7, 10});
        mBaseGlyphs.put(A_SEE, new int[]{5, 4});
        mBaseGlyphs.put(A_SEPERATE, new int[]{6, 5, 7, 1, 2, 10, 11});
        mBaseGlyphs.put(A_SHARE, new int[]{9, 8, 7, 10, 11});
        mBaseGlyphs.put(A_TOGETHER, new int[]{1, 5, 2, 1, 7, 8});
        mBaseGlyphs.put(A_USE, new int[]{1, 10, 3});

        mBaseGlyphs.put(T_ANSWER, new int[]{5, 2, 10, 1});
        mBaseGlyphs.put(T_CONTEMPLATE, new int[]{4, 3, 11, 9, 7, 5, 1, 2});
        mBaseGlyphs.put(T_COURAGE, new int[]{8, 5, 7, 10});
        mBaseGlyphs.put(T_DATA, new int[]{4, 2, 1, 7, 9});
        mBaseGlyphs.put(T_DESTINY, new int[]{5, 1, 2, 10, 7, 9});
        mBaseGlyphs.put(T_FEAR, new int[]{5, 2, 10, 3});
        mBaseGlyphs.put(T_FORGET, new int[]{8, 7});
        mBaseGlyphs.put(T_IDEA_THOUGHT, new int[]{7, 8, 6, 5, 1, 10, 11, 3, 2});
        mBaseGlyphs.put(T_IGNORE, new int[]{10, 11});
        mBaseGlyphs.put(T_LIE, new int[]{7, 5, 1, 10, 2, 1});
        mBaseGlyphs.put(T_MESSAGE, new int[]{8, 5, 1, 10, 3});
        mBaseGlyphs.put(T_QUESTION, new int[]{4, 2, 5, 7});
        mBaseGlyphs.put(T_TRUTH, new int[]{5, 1, 10, 2, 1, 7, 5});
        mBaseGlyphs.put(T_WANT, new int[]{8, 7, 9, 10});

        mBaseGlyphs.put(FD_DETERIORATE, new int[]{5, 1, 7, 8});
        mBaseGlyphs.put(FD_EQUAL, new int[]{7, 5, 2, 10});
        mBaseGlyphs.put(FD_EVOLUTION_SUCCESS, new int[]{4, 1, 5, 7});
        mBaseGlyphs.put(FD_FAILURE, new int[]{4, 1, 2, 10});
        mBaseGlyphs.put(FD_FOLLOW, new int[]{4, 2, 3, 11});
        mBaseGlyphs.put(FD_GAIN, new int[]{6, 7});
        mBaseGlyphs.put(FD_GROW, new int[]{8, 5, 7});
        mBaseGlyphs.put(FD_IMPROVE, new int[]{3, 2, 1, 10});
        mBaseGlyphs.put(FD_LEAD, new int[]{4, 6, 8, 7, 9});
        mBaseGlyphs.put(FD_LESS, new int[]{5, 1, 2});
        mBaseGlyphs.put(FD_LOSE, new int[]{10, 3});
        mBaseGlyphs.put(FD_MORE, new int[]{7, 1, 10});
        mBaseGlyphs.put(FD_REDUCE, new int[]{10, 2, 11});

        mBaseGlyphs.put(TS_AGAIN_REPEAT, new int[]{8, 5, 7, 1, 2, 10});
        mBaseGlyphs.put(TS_CLOSE_ALL, new int[]{4, 6, 8, 9, 11, 3, 4, 1, 9});
        mBaseGlyphs.put(TS_DISTANCE_OUTSIDE, new int[]{8, 6, 4});
        mBaseGlyphs.put(TS_END, new int[]{4, 1, 9, 10, 3, 4});
        mBaseGlyphs.put(TS_FUTURE, new int[]{3, 2, 10, 11});
        mBaseGlyphs.put(TS_NEW, new int[]{2, 10, 11});
        mBaseGlyphs.put(TS_NOT_INSIDE, new int[]{5, 2, 10});
        mBaseGlyphs.put(TS_NOW_PRESENT, new int[]{5, 7, 10, 2});
        mBaseGlyphs.put(TS_OLD, new int[]{6, 5, 7});
        mBaseGlyphs.put(TS_OPEN_ACCEPT, new int[]{9, 7, 10, 9});
        mBaseGlyphs.put(TS_OPEN_ALL, new int[]{9, 7, 10, 9, 11, 3, 4, 6, 8, 9});
        mBaseGlyphs.put(TS_PAST, new int[]{6, 5, 7, 8});

        mBaseGlyphs.put(CE_ALL, new int[]{4, 6, 8, 9, 11, 3, 4});
        mBaseGlyphs.put(CE_BALANCE_PERFECTION, new int[]{4, 1, 7, 8, 9, 11, 10, 1});
        mBaseGlyphs.put(CE_BARRIER, new int[]{4, 1, 10, 11});
        mBaseGlyphs.put(CE_CHAOS, new int[]{8, 6, 4, 3, 2, 1, 7, 9});
        mBaseGlyphs.put(CE_CIVILIZATION, new int[]{6, 5, 7, 10, 2, 3});
        mBaseGlyphs.put(CE_CLEAR, new int[]{4, 1, 9});
        mBaseGlyphs.put(CE_COMPLEX, new int[]{2, 5, 1, 7});
        mBaseGlyphs.put(CE_CONFLICT, new int[]{8, 5, 7, 10, 2, 11});
        mBaseGlyphs.put(CE_DANGER, new int[]{4, 5, 1, 9});
        mBaseGlyphs.put(CE_DIFFICULT, new int[]{7, 1, 10, 2, 3});
        mBaseGlyphs.put(CE_EASY, new int[]{2, 1, 7, 9});
        mBaseGlyphs.put(CE_HARM, new int[]{1, 2, 4, 5, 1, 10, 11});
        mBaseGlyphs.put(CE_HARMONY_PEACE, new int[]{1, 5, 4, 2, 1, 7, 9, 10, 1});
        mBaseGlyphs.put(CE_HAVE, new int[]{10, 1, 7, 9});
        mBaseGlyphs.put(CE_IMPERFECT, new int[]{2, 1, 7, 5, 1});
        mBaseGlyphs.put(CE_IMPURE, new int[]{1, 5, 7, 1, 9});
        mBaseGlyphs.put(CE_NATURE, new int[]{8, 7, 5, 2, 10, 11});
        mBaseGlyphs.put(CE_PATH, new int[]{4, 1, 7, 8});
        mBaseGlyphs.put(CE_POTENTIAL, new int[]{4, 1, 10, 11, 3});
        mBaseGlyphs.put(CE_PURE, new int[]{4, 1, 10, 2, 1});
        mBaseGlyphs.put(CE_SAFETY, new int[]{8, 5, 2, 11});
        mBaseGlyphs.put(CE_SIMPLE, new int[]{7, 10});
        mBaseGlyphs.put(CE_STABILITY_STAY, new int[]{8, 7, 10, 11});
        mBaseGlyphs.put(CE_STRONG, new int[]{5, 7, 10, 2, 5});
        mBaseGlyphs.put(CE_WEAK, new int[]{6, 5, 2, 10});
    }

    private void initGlyphCategories() {
        mCategoryGlyphs = new LinkedHashMap<>();
        mCategoryGlyphs.put(C_HUMAN, new String[]{H_BODY, H_ENLIGHTENMENT, H_HUMAN, H_MIND,
                H_RESISTANCE_STRUGGLE, H_PORTAL, H_SELF, H_SHAPERS, H_SOUL, H_XM});

        mCategoryGlyphs.put(C_ACTION, new String[]{A_ADVANCE, A_ATTACK_WAR, A_AVOID, A_BREATHE_LIVE,
                A_CAPTURE, A_CHANGE, A_CREATE, A_DEFEND, A_DESTROY, A_DIE, A_DISCOVER, A_ESCAPE,
                A_HIDE, A_JOURNEY, A_LIBERATE, A_NOURISH, A_PURSUE, A_REACT, A_REBEL, A_RETREAT,
                A_RECHARGE_REPAIR, A_RESTRAINT, A_SAVE, A_SEARCH, A_SEE, A_SEPERATE, A_SHARE,
                A_TOGETHER, A_USE});

        mCategoryGlyphs.put(C_THOUGHT, new String[]{T_ANSWER, T_CONTEMPLATE, T_COURAGE, T_DATA,
                T_DESTINY, T_FEAR, T_FORGET, T_IDEA_THOUGHT, T_IGNORE, T_LIE, T_MESSAGE, T_QUESTION,
                T_TRUTH, T_WANT});

        mCategoryGlyphs.put(C_FLU_DIRE, new String[]{FD_DETERIORATE, FD_EQUAL, FD_EVOLUTION_SUCCESS,
                FD_FAILURE, FD_FOLLOW, FD_GAIN, FD_GROW, FD_IMPROVE, FD_LEAD, FD_LESS, FD_LOSE,
                FD_MORE, FD_REDUCE});

        mCategoryGlyphs.put(C_TIME_SPACE, new String[]{TS_AGAIN_REPEAT, TS_CLOSE_ALL, TS_DISTANCE_OUTSIDE,
                TS_END, TS_FUTURE, TS_NEW, TS_NOT_INSIDE, TS_NOW_PRESENT, TS_OLD, TS_OPEN_ACCEPT,
                TS_OPEN_ALL, TS_PAST});

        mCategoryGlyphs.put(C_COND_ENV, new String[]{CE_ALL, CE_BALANCE_PERFECTION, CE_BARRIER,
                CE_CHAOS, CE_CIVILIZATION, CE_CLEAR, CE_COMPLEX, CE_CONFLICT, CE_DANGER, CE_DIFFICULT,
                CE_EASY, CE_HARM, CE_HARMONY_PEACE, CE_HAVE, A_HELP, CE_IMPERFECT, CE_IMPURE,
                CE_NATURE, CE_PATH, CE_POTENTIAL, CE_PURE, CE_SAFETY, CE_SIMPLE, CE_STABILITY_STAY,
                CE_STRONG, CE_WEAK});
    }

    private void initTwoSequences() {
        mTwoSequences = new LinkedHashMap<>();
        mTwoSequences.put(CE_CIVILIZATION, new String[][]{
                {CE_CIVILIZATION, CE_CHAOS}
        });
        mTwoSequences.put(A_PURSUE, new String[][]{
                {A_PURSUE, H_XM},
                {A_PURSUE, T_TRUTH}
        });
        mTwoSequences.put(A_SEPERATE, new String[][]{
                {A_SEPERATE, A_ATTACK_WAR}
        });
        mTwoSequences.put(A_CREATE, new String[][]{
                {A_CREATE, CE_DANGER}
        });
        mTwoSequences.put(CE_PATH, new String[][]{
                {CE_PATH, CE_BALANCE_PERFECTION}
        });
        mTwoSequences.put(A_DISCOVER, new String[][]{
                {A_DISCOVER, CE_SAFETY},
                {A_DISCOVER, T_LIE},
                {A_DISCOVER, H_PORTAL}
        });
        mTwoSequences.put(A_ATTACK_WAR, new String[][]{
                {A_ATTACK_WAR, CE_CHAOS},
                {A_ATTACK_WAR, CE_DIFFICULT}
        });
        mTwoSequences.put(A_LIBERATE, new String[][]{
                {A_LIBERATE, H_XM}
        });
        mTwoSequences.put(T_QUESTION, new String[][]{
                {T_QUESTION, A_ATTACK_WAR}
        });
        mTwoSequences.put(FD_LEAD, new String[][]{
                {FD_LEAD, H_ENLIGHTENMENT}
        });
        mTwoSequences.put(A_SEARCH, new String[][]{
                {A_SEARCH, CE_POTENTIAL},
                {A_SEARCH, TS_PAST}
        });
        mTwoSequences.put(A_CAPTURE, new String[][]{
                {A_CAPTURE, H_PORTAL}
        });
        mTwoSequences.put(CE_PURE, new String[][]{
                {CE_PURE, T_TRUTH},
                {CE_PURE, H_BODY},
                {CE_PURE, H_HUMAN}
        });
        mTwoSequences.put(A_NOURISH, new String[][]{
                {A_NOURISH, A_JOURNEY}
        });
        mTwoSequences.put(CE_ALL, new String[][]{
                {CE_ALL, CE_CHAOS}
        });
        mTwoSequences.put(TS_OPEN_ALL, new String[][]{
                {TS_OPEN_ALL, T_TRUTH}
        });
    }

    private void initThreeSequences() {
        mThreeSequences = new LinkedHashMap<>();
        mThreeSequences.put(FD_GAIN, new String[][]{
                {FD_GAIN, CE_DIFFICULT, CE_BARRIER},
                {FD_GAIN, CE_CIVILIZATION, CE_HARMONY_PEACE}
        });
        mThreeSequences.put(A_DESTROY, new String[][]{
                {A_DESTROY, CE_WEAK, CE_CIVILIZATION},
                {A_DESTROY, T_DESTINY, CE_BARRIER}
        });
        mThreeSequences.put(CE_CIVILIZATION, new String[][]{
                {CE_CIVILIZATION, A_ATTACK_WAR, CE_CHAOS}
        });
        mThreeSequences.put(A_AVOID, new String[][]{
                {A_AVOID, T_DESTINY, T_LIE},
                {A_AVOID, CE_COMPLEX, CE_CONFLICT}
        });
        mThreeSequences.put(A_SEPERATE, new String[][]{
                {A_SEPERATE, TS_FUTURE, FD_EVOLUTION_SUCCESS}
        });
        mThreeSequences.put(CE_PATH, new String[][]{
                {CE_PATH, CE_HARMONY_PEACE, CE_DIFFICULT}
        });
        mThreeSequences.put(TS_AGAIN_REPEAT, new String[][]{
                {TS_AGAIN_REPEAT, A_JOURNEY, TS_DISTANCE_OUTSIDE}
        });
        mThreeSequences.put(A_DISCOVER, new String[][]{
                {A_DISCOVER, H_SHAPERS, T_LIE},
                {A_DISCOVER, CE_PURE, T_TRUTH}
        });
        mThreeSequences.put(CE_NATURE, new String[][]{
                {CE_NATURE, CE_PURE, A_DEFEND}
        });
        mThreeSequences.put(A_ATTACK_WAR, new String[][]{
                {A_ATTACK_WAR, A_CREATE, CE_DANGER},
                {A_ATTACK_WAR, A_DESTROY, TS_FUTURE},
                {A_ATTACK_WAR, CE_DIFFICULT, TS_FUTURE},
                {A_ATTACK_WAR, H_SHAPERS, FD_EVOLUTION_SUCCESS}
        });
        mThreeSequences.put(A_LIBERATE, new String[][]{
                {A_LIBERATE, H_PORTAL, CE_POTENTIAL}
        });
        mThreeSequences.put(TS_NOT_INSIDE, new String[][]{
                {TS_NOT_INSIDE, H_MIND, TS_FUTURE}
        });
        mThreeSequences.put(T_FEAR, new String[][]{
                {T_FEAR, CE_CHAOS, H_XM}
        });
        mThreeSequences.put(A_CHANGE, new String[][]{
                {A_CHANGE, H_HUMAN, TS_FUTURE}
        });
        mThreeSequences.put(T_QUESTION, new String[][]{
                {T_QUESTION, H_SHAPERS, CE_CHAOS},
                {T_QUESTION, A_HIDE, T_TRUTH},
                {T_QUESTION, CE_CONFLICT, T_DATA}
        });
        mThreeSequences.put(A_ESCAPE, new String[][]{
                {A_ESCAPE, CE_IMPURE, FD_EVOLUTION_SUCCESS}
        });
        mThreeSequences.put(CE_COMPLEX, new String[][]{
                {CE_COMPLEX, A_JOURNEY, TS_FUTURE}
        });
        mThreeSequences.put(FD_FOLLOW, new String[][]{
                {FD_FOLLOW, CE_PURE, A_JOURNEY}
        });
        mThreeSequences.put(FD_LEAD, new String[][]{
                {FD_LEAD, H_RESISTANCE_STRUGGLE, T_QUESTION}
        });
        mThreeSequences.put(A_REACT, new String[][]{
                {A_REACT, CE_IMPURE, CE_CIVILIZATION}
        });
        mThreeSequences.put(A_CAPTURE, new String[][]{
                {A_CAPTURE, H_SHAPERS, H_PORTAL}
        });
        mThreeSequences.put(A_JOURNEY, new String[][]{
                {A_JOURNEY, TS_NOT_INSIDE, H_SOUL}
        });
        mThreeSequences.put(FD_LOSE, new String[][]{
                {FD_LOSE, A_ATTACK_WAR, A_RETREAT}
        });
        mThreeSequences.put(FD_IMPROVE, new String[][]{
                {FD_IMPROVE, A_ADVANCE, TS_NOW_PRESENT},
                {FD_IMPROVE, H_HUMAN, H_SHAPERS}
        });
        mThreeSequences.put(TS_FUTURE, new String[][]{
                {TS_FUTURE, FD_EQUAL, TS_PAST}
        });
        mThreeSequences.put(A_TOGETHER, new String[][]{
                {A_TOGETHER, A_PURSUE, CE_SAFETY}
        });
        mThreeSequences.put(CE_HARM, new String[][]{
                {CE_HARM, CE_DANGER, A_AVOID}
        });
        mThreeSequences.put(CE_BALANCE_PERFECTION, new String[][]{
                {CE_BALANCE_PERFECTION, CE_PATH, CE_HARMONY_PEACE}
        });
        mThreeSequences.put(H_MIND, new String[][]{
                {H_MIND, TS_OPEN_ACCEPT, A_BREATHE_LIVE}
        });
        mThreeSequences.put(CE_ALL, new String[][]{
                {CE_ALL, H_XM, A_LIBERATE}
        });
        mThreeSequences.put(TS_OPEN_ALL, new String[][]{
                {TS_OPEN_ALL, H_PORTAL, FD_EVOLUTION_SUCCESS}
        });
        mThreeSequences.put(H_XM, new String[][]{
                {H_XM, A_NOURISH, CE_CIVILIZATION}
        });
        mThreeSequences.put(T_TRUTH, new String[][]{
                {T_TRUTH, A_NOURISH, H_SOUL}
        });
    }

    private void initFourSequences() {
        mFourSequences = new LinkedHashMap<>();
        mFourSequences.put(FD_GAIN, new String[][]{
                {FD_GAIN, H_PORTAL, A_ATTACK_WAR, CE_WEAK}
        });
        mFourSequences.put(TS_PAST, new String[][]{
                {TS_PAST, TS_AGAIN_REPEAT, TS_NOW_PRESENT, TS_AGAIN_REPEAT}
        });
        mFourSequences.put(A_DESTROY, new String[][]{
                {A_DESTROY, T_DESTINY, H_HUMAN, T_LIE},
                {A_DESTROY, CE_COMPLEX, H_SHAPERS, T_LIE}
        });
        mFourSequences.put(A_RESTRAINT, new String[][]{
                {A_RESTRAINT, CE_PATH, FD_GAIN, CE_HARMONY_PEACE},
                {A_RESTRAINT, T_FEAR, A_AVOID, CE_DANGER}
        });
        mFourSequences.put(A_BREATHE_LIVE, new String[][]{
                {A_BREATHE_LIVE, TS_AGAIN_REPEAT, A_JOURNEY, TS_AGAIN_REPEAT},
                {A_BREATHE_LIVE, CE_NATURE, CE_BALANCE_PERFECTION, CE_HARMONY_PEACE}
        });
        mFourSequences.put(A_DEFEND, new String[][]{
                {A_DEFEND, T_MESSAGE, T_ANSWER, T_IDEA_THOUGHT}
        });
        mFourSequences.put(A_HELP, new String[][]{
                {A_HELP, FD_GAIN, A_CREATE, A_PURSUE},
                {A_HELP, H_SHAPERS, A_CREATE, TS_FUTURE}
        });
        mFourSequences.put(A_AVOID, new String[][]{
                {A_AVOID, H_XM, T_MESSAGE, T_LIE}
        });
        mFourSequences.put(A_SEPERATE, new String[][]{
                {A_SEPERATE, CE_WEAK, T_IGNORE, T_TRUTH}
        });
        mFourSequences.put(T_FORGET, new String[][]{
                {T_FORGET, CE_CONFLICT, TS_OPEN_ACCEPT, A_ATTACK_WAR}
        });
        mFourSequences.put(A_CREATE, new String[][]{
                {A_CREATE, TS_DISTANCE_OUTSIDE, CE_IMPURE, CE_PATH},
                {A_CREATE, TS_FUTURE, TS_NOT_INSIDE, A_ATTACK_WAR},
                {A_CREATE, TS_FUTURE, A_CHANGE, T_DESTINY}
        });
        mFourSequences.put(A_ADVANCE, new String[][]{
                {A_ADVANCE, CE_CIVILIZATION, TS_AGAIN_REPEAT, FD_FAILURE}}
        );
        mFourSequences.put(CE_PATH, new String[][]{
                {CE_PATH, A_RESTRAINT, CE_STRONG, CE_SAFETY}
        });
        mFourSequences.put(FD_DETERIORATE, new String[][]{
                {FD_DETERIORATE, H_HUMAN, CE_WEAK, A_REBEL}
        });
        mFourSequences.put(T_COURAGE, new String[][]{
                {T_COURAGE, A_ATTACK_WAR, H_SHAPERS, TS_FUTURE}
        });
        mFourSequences.put(CE_STABILITY_STAY, new String[][]{
                {CE_STABILITY_STAY, A_TOGETHER, A_DEFEND, T_TRUTH}
        });
        mFourSequences.put(H_SHAPERS, new String[][]{
                {H_SHAPERS, A_SEE, CE_POTENTIAL, FD_EVOLUTION_SUCCESS},
                {H_SHAPERS, H_PORTAL, H_MIND, A_RESTRAINT},
                {H_SHAPERS, TS_PAST, TS_NOW_PRESENT, TS_FUTURE},
                {H_SHAPERS, H_MIND, CE_COMPLEX, CE_HARMONY_PEACE},
                {H_SHAPERS, CE_HAVE, CE_STRONG, CE_PATH},
                {H_SHAPERS, CE_CHAOS, CE_PURE, CE_HARM},
                {H_SHAPERS, T_MESSAGE, TS_END, CE_CIVILIZATION}
        });
        mFourSequences.put(A_ATTACK_WAR, new String[][]{
                {A_ATTACK_WAR, CE_WEAK, H_SHAPERS, T_LIE},
                {A_ATTACK_WAR, H_RESISTANCE_STRUGGLE, A_PURSUE, H_ENLIGHTENMENT},
                {A_ATTACK_WAR, H_ENLIGHTENMENT, A_PURSUE, H_RESISTANCE_STRUGGLE}
        });
        mFourSequences.put(CE_CHAOS, new String[][]{
                {CE_CHAOS, CE_BARRIER, H_SHAPERS, H_PORTAL}
        });
        mFourSequences.put(A_LIBERATE, new String[][]{
                {A_LIBERATE, H_XM, H_PORTAL, A_TOGETHER}
        });
        mFourSequences.put(A_SEE, new String[][]{
                {A_SEE, T_TRUTH, A_SEE, TS_FUTURE}
        });
        mFourSequences.put(FD_LESS, new String[][]{
                {FD_LESS, CE_CHAOS, FD_MORE, CE_STABILITY_STAY},
                {FD_LESS, T_TRUTH, FD_MORE, CE_CHAOS},
                {FD_LESS, H_SOUL, FD_MORE, H_MIND}
        });
        mFourSequences.put(TS_NOT_INSIDE, new String[][]{
                {TS_NOT_INSIDE, H_MIND, A_JOURNEY, CE_BALANCE_PERFECTION}
        });
        mFourSequences.put(A_HIDE, new String[][]{
                {A_HIDE, CE_IMPURE, H_HUMAN, T_IDEA_THOUGHT}
        });
        mFourSequences.put(CE_SIMPLE, new String[][]{
                {CE_SIMPLE, CE_CIVILIZATION, CE_IMPURE, CE_WEAK},
                {CE_SIMPLE, T_TRUTH, A_BREATHE_LIVE, CE_NATURE},
                {CE_SIMPLE, T_MESSAGE, CE_COMPLEX, T_IDEA_THOUGHT}
        });
        mFourSequences.put(A_CHANGE, new String[][]{
                {A_CHANGE, H_HUMAN, CE_POTENTIAL, A_USE},
                {A_CHANGE, TS_FUTURE, A_CAPTURE, T_DESTINY},
                {A_CHANGE, H_BODY, FD_IMPROVE, H_HUMAN}
        });
        mFourSequences.put(T_QUESTION, new String[][]{
                {T_QUESTION, T_TRUTH, FD_GAIN, TS_FUTURE}
        });
        mFourSequences.put(A_ESCAPE, new String[][]{
                {A_ESCAPE, CE_SIMPLE, H_HUMAN, TS_FUTURE}
        });
        mFourSequences.put(CE_COMPLEX, new String[][]{
                {CE_COMPLEX, H_SHAPERS, CE_CIVILIZATION, CE_STRONG}
        });
        mFourSequences.put(CE_CLEAR, new String[][]{
                {CE_CLEAR, H_MIND, TS_OPEN_ACCEPT, H_MIND}
        });
        mFourSequences.put(FD_FOLLOW, new String[][]{
                {FD_FOLLOW, H_SHAPERS, H_PORTAL, T_MESSAGE}
        });
        mFourSequences.put(FD_LEAD, new String[][]{
                {FD_LEAD, A_PURSUE, A_REACT, A_DEFEND}
        });
        mFourSequences.put(T_CONTEMPLATE, new String[][]{
                {T_CONTEMPLATE, CE_COMPLEX, H_SHAPERS, T_TRUTH},
                {T_CONTEMPLATE, CE_COMPLEX, H_SHAPERS, CE_CIVILIZATION},
                {T_CONTEMPLATE, H_SELF, H_SHAPERS, T_TRUTH},
                {T_CONTEMPLATE, H_SELF, CE_PATH, T_TRUTH}
        });
        mFourSequences.put(A_SEARCH, new String[][]{
                {A_SEARCH, T_TRUTH, A_SAVE, CE_CIVILIZATION},
                {A_SEARCH, H_XM, A_SAVE, H_PORTAL},
                {A_SEARCH, T_DATA, A_DISCOVER, CE_PATH}
        });
        mFourSequences.put(A_CAPTURE, new String[][]{
                {A_CAPTURE, T_FEAR, A_DISCOVER, T_COURAGE}
        });
        mFourSequences.put(A_JOURNEY, new String[][]{
                {A_JOURNEY, TS_NOT_INSIDE, FD_IMPROVE, H_SOUL}
        });
        mFourSequences.put(T_IGNORE, new String[][]{
                {T_IGNORE, H_HUMAN, CE_CHAOS, T_LIE}
        });
        mFourSequences.put(FD_LOSE, new String[][]{
                {FD_LOSE, CE_DANGER, FD_GAIN, CE_SAFETY}
        });
        mFourSequences.put(FD_IMPROVE, new String[][]{
                {FD_IMPROVE, H_BODY, A_PURSUE, A_JOURNEY},
                {FD_IMPROVE, H_BODY, H_MIND, H_SOUL},
                {FD_IMPROVE, H_MIND, A_JOURNEY, TS_NOT_INSIDE}
        });
        mFourSequences.put(A_TOGETHER, new String[][]{
                {A_TOGETHER, A_DISCOVER, CE_HARMONY_PEACE, FD_EQUAL}
        });
        mFourSequences.put(H_RESISTANCE_STRUGGLE, new String[][]{
                {H_RESISTANCE_STRUGGLE, FD_IMPROVE, H_HUMAN, H_SOUL},
                {H_RESISTANCE_STRUGGLE, A_DEFEND, H_SHAPERS, CE_DANGER}
        });
        mFourSequences.put(CE_BALANCE_PERFECTION, new String[][]{
                {CE_BALANCE_PERFECTION, CE_BALANCE_PERFECTION, CE_SAFETY, CE_ALL}
        });
        mFourSequences.put(H_HUMAN, new String[][]{
                {H_HUMAN, CE_HAVE, CE_IMPURE, CE_CIVILIZATION},
                {H_HUMAN, H_SOUL, CE_STRONG, CE_PURE},
                {H_HUMAN, TS_PAST, TS_NOW_PRESENT, TS_FUTURE}
        });
        mFourSequences.put(A_NOURISH, new String[][]{
                {A_NOURISH, H_XM, A_CREATE, T_IDEA_THOUGHT}
        });
        mFourSequences.put(H_SOUL, new String[][]{
                {H_SOUL, A_REBEL, H_HUMAN, A_DIE}
        });
        mFourSequences.put(CE_ALL, new String[][]{
                {C_ALL, CE_CHAOS, TS_NOT_INSIDE, H_BODY}
        });
        mFourSequences.put(TS_CLOSE_ALL, new String[][]{
                {TS_CLOSE_ALL, TS_OPEN_ALL, A_DISCOVER, T_TRUTH}
        });
        mFourSequences.put(CE_STRONG, new String[][]{
                {CE_STRONG, T_IDEA_THOUGHT, A_PURSUE, T_TRUTH},
                {CE_STRONG, A_TOGETHER, A_AVOID, A_ATTACK_WAR},
                {CE_STRONG, H_RESISTANCE_STRUGGLE, A_CAPTURE, H_PORTAL}
        });
        mFourSequences.put(H_XM, new String[][]{
                {H_XM, CE_HAVE, H_MIND, A_JOURNEY},
                {H_XM, A_DIE, CE_CHAOS, A_BREATHE_LIVE}
        });
        mFourSequences.put(T_TRUTH, new String[][]{
                {T_TRUTH, T_IDEA_THOUGHT, A_DISCOVER, H_XM}
        });
        mFourSequences.put(H_PORTAL, new String[][]{
                {H_PORTAL, CE_HAVE, T_TRUTH, T_DATA},
                {H_PORTAL, CE_POTENTIAL, A_CHANGE, TS_FUTURE},
                {H_PORTAL, A_DIE, CE_CIVILIZATION, A_DIE},
                {H_PORTAL, A_CHANGE, CE_CIVILIZATION, TS_END}
        });
        mFourSequences.put(CE_HARMONY_PEACE, new String[][]{
                {CE_HARMONY_PEACE, CE_PATH, A_NOURISH, TS_NOW_PRESENT}
        });
    }

    private void initFiveSequences() {
        mFiveSequences = new LinkedHashMap<>();
        mFiveSequences.put(FD_GAIN, new String[][]{
                {FD_GAIN, T_TRUTH, TS_OPEN_ACCEPT, H_HUMAN, H_SOUL}
        });
        mFiveSequences.put(TS_OLD, new String[][]{
                {TS_OLD, CE_NATURE, FD_LESS, CE_STRONG, TS_NOW_PRESENT}
        });
        mFiveSequences.put(TS_PAST, new String[][]{
                {TS_PAST, CE_CHAOS, A_CREATE, TS_FUTURE, CE_HARMONY_PEACE},
                {TS_PAST, CE_PATH, A_CREATE, TS_FUTURE, A_JOURNEY}
        });
        mFiveSequences.put(A_DESTROY, new String[][]{
                {A_DESTROY, T_LIE, TS_NOT_INSIDE, FD_GAIN, H_SOUL},
                {A_DESTROY, CE_CIVILIZATION, TS_END, CE_CONFLICT, A_ATTACK_WAR}
        });
        mFiveSequences.put(A_BREATHE_LIVE, new String[][]{
                {A_BREATHE_LIVE, TS_NOT_INSIDE, H_XM, FD_LOSE, H_SELF}
        });
        mFiveSequences.put(A_DEFEND, new String[][]{
                {A_DEFEND, T_DESTINY, A_DEFEND, H_HUMAN, CE_CIVILIZATION},
                {A_DEFEND, H_HUMAN, CE_CIVILIZATION, H_XM, T_MESSAGE},
                {A_DEFEND, H_HUMAN, CE_CIVILIZATION, H_PORTAL, T_DATA},
                {A_DEFEND, H_HUMAN, CE_CIVILIZATION, H_SHAPERS, T_LIE},
                {A_DEFEND, H_HUMAN, CE_CIVILIZATION, H_SHAPERS, H_PORTAL}
        });
        mFiveSequences.put(A_HELP, new String[][]{
                {A_HELP, H_ENLIGHTENMENT, A_CAPTURE, CE_ALL, H_PORTAL},
                {A_HELP, H_RESISTANCE_STRUGGLE, A_CAPTURE, CE_ALL, H_PORTAL},
                {A_HELP, H_HUMAN, CE_CIVILIZATION, A_PURSUE, T_DESTINY}
        });
        mFiveSequences.put(A_PURSUE, new String[][]{
                {A_PURSUE, CE_PATH, TS_DISTANCE_OUTSIDE, H_SHAPERS, T_LIE},
                {A_PURSUE, CE_CONFLICT, A_ATTACK_WAR, A_ADVANCE, CE_CHAOS}
        });
        mFiveSequences.put(CE_WEAK, new String[][]{
                {CE_WEAK, H_HUMAN, T_DESTINY, A_DESTROY, CE_CIVILIZATION}
        });
        mFiveSequences.put(A_AVOID, new String[][]{
                {A_AVOID, CE_CHAOS, A_RECHARGE_REPAIR, CE_POTENTIAL, A_ATTACK_WAR},
                {A_AVOID, CE_BALANCE_PERFECTION, CE_STABILITY_STAY, H_HUMAN, H_SELF},
                {A_AVOID, CE_CHAOS, A_AVOID, H_SHAPERS, T_LIE}
        });
        mFiveSequences.put(A_REBEL, new String[][]{
                {A_REBEL, T_IDEA_THOUGHT, FD_EVOLUTION_SUCCESS, T_DESTINY, TS_NOW_PRESENT}
        });
        mFiveSequences.put(A_SEPERATE, new String[][]{
                {A_SEPERATE, H_MIND, H_BODY, A_DISCOVER, H_ENLIGHTENMENT},
                {A_SEPERATE, T_TRUTH, T_LIE, H_SHAPERS, TS_FUTURE}
        });
        mFiveSequences.put(T_FORGET, new String[][]{
                {T_FORGET, TS_PAST, A_SEE, TS_NOW_PRESENT, CE_DANGER},
                {T_FORGET, A_ATTACK_WAR, A_SEE, TS_DISTANCE_OUTSIDE, CE_HARMONY_PEACE}
        });
        mFiveSequences.put(A_CREATE, new String[][]{
                {A_CREATE, CE_PURE, TS_FUTURE, TS_NOT_INSIDE, A_ATTACK_WAR},
                {A_CREATE, CE_PURE, TS_FUTURE, H_HUMAN, CE_CIVILIZATION},
                {A_CREATE, A_SEPERATE, CE_PATH, TS_END, A_JOURNEY}
        });
        mFiveSequences.put(A_ADVANCE, new String[][]{
                {A_ADVANCE, CE_CIVILIZATION, A_PURSUE, H_SHAPERS, CE_PATH}
        });
        mFiveSequences.put(TS_DISTANCE_OUTSIDE, new String[][]{
                {TS_DISTANCE_OUTSIDE, H_SELF, A_AVOID, H_HUMAN, T_LIE}
        });
        mFiveSequences.put(T_COURAGE, new String[][]{
                {T_COURAGE, A_ATTACK_WAR, H_SHAPERS, H_PORTAL, A_TOGETHER}
        });
        mFiveSequences.put(T_WANT, new String[][]{
                {T_WANT, T_TRUTH, A_PURSUE, CE_DIFFICULT, CE_PATH}
        });
        mFiveSequences.put(CE_STABILITY_STAY, new String[][]{
                {CE_STABILITY_STAY, CE_STRONG, A_TOGETHER, A_DEFEND, H_RESISTANCE_STRUGGLE}
        });
        mFiveSequences.put(H_SHAPERS, new String[][]{
                {H_SHAPERS, H_PORTAL, T_DATA, A_REACT, CE_CHAOS},
                {H_SHAPERS, H_PORTAL, T_MESSAGE, A_DESTROY, CE_CIVILIZATION},
                {H_SHAPERS, T_WANT, H_HUMAN, H_MIND, TS_FUTURE},
                {H_SHAPERS, FD_LEAD, H_HUMAN, CE_COMPLEX, A_JOURNEY}
        });
        mFiveSequences.put(CE_CHAOS, new String[][]{
                {CE_CHAOS, A_ATTACK_WAR, CE_CONFLICT, A_DISCOVER, CE_HARMONY_PEACE}
        });
        mFiveSequences.put(A_LIBERATE, new String[][]{
                {A_LIBERATE, H_PORTAL, A_LIBERATE, H_HUMAN, H_MIND}
        });
        mFiveSequences.put(TS_NOW_PRESENT, new String[][]{
                {TS_NOW_PRESENT, CE_CHAOS, A_CREATE, TS_FUTURE, CE_CIVILIZATION}
        });
        mFiveSequences.put(TS_NOT_INSIDE, new String[][]{
                {TS_NOT_INSIDE, H_MIND, TS_NOT_INSIDE, H_SOUL, CE_HARMONY_PEACE}
        });
        mFiveSequences.put(T_ANSWER, new String[][]{
                {T_ANSWER, T_QUESTION, A_DISCOVER, CE_DIFFICULT, T_TRUTH}
        });
        mFiveSequences.put(A_HIDE, new String[][]{
                {A_HIDE, H_RESISTANCE_STRUGGLE, A_ADVANCE, CE_STRONG, A_TOGETHER}
        });
        mFiveSequences.put(CE_SIMPLE, new String[][]{
                {CE_SIMPLE, T_TRUTH, H_SHAPERS, A_DESTROY, CE_CIVILIZATION},
                {CE_SIMPLE, TS_OLD, T_TRUTH, A_JOURNEY, TS_NOT_INSIDE}
        });
        mFiveSequences.put(FD_MORE, new String[][]{
                {FD_MORE, T_DATA, FD_GAIN, H_PORTAL, A_ADVANCE}
        });
        mFiveSequences.put(A_SAVE, new String[][]{
                {A_SAVE, H_HUMAN, CE_CIVILIZATION, A_DESTROY, H_PORTAL}
        });
        mFiveSequences.put(T_QUESTION, new String[][]{
                {T_QUESTION, FD_LESS, T_FORGET, CE_ALL, T_LIE}
        });
        mFiveSequences.put(A_ESCAPE, new String[][]{
                {A_ESCAPE, H_BODY, A_JOURNEY, TS_DISTANCE_OUTSIDE, TS_NOW_PRESENT},
                {A_ESCAPE, H_BODY, H_MIND, H_SELF, T_WANT}
        });
        mFiveSequences.put(CE_CLEAR, new String[][]{
                {CE_CLEAR, H_MIND, A_LIBERATE, CE_BARRIER, H_BODY}
        });
        mFiveSequences.put(T_CONTEMPLATE, new String[][]{
                {T_CONTEMPLATE, TS_FUTURE, TS_NOT_INSIDE, H_SHAPERS, CE_PATH},
                {T_CONTEMPLATE, A_RESTRAINT, A_DISCOVER, FD_MORE, T_COURAGE}
        });
        mFiveSequences.put(A_USE, new String[][]{
                {A_USE, A_RESTRAINT, FD_FOLLOW, CE_EASY, CE_PATH},
                {A_USE, H_MIND, A_USE, T_COURAGE, A_CHANGE}
        });
        mFiveSequences.put(A_SEARCH, new String[][]{
                {A_SEARCH, T_DESTINY, A_CREATE, CE_PURE, TS_FUTURE}
        });
        mFiveSequences.put(CE_EASY, new String[][]{
                {CE_EASY, CE_PATH, TS_FUTURE, FD_FOLLOW, H_SHAPERS}
        });
        mFiveSequences.put(A_REACT, new String[][]{
                {A_REACT, A_REBEL, T_QUESTION, H_SHAPERS, T_LIE}
        });
        mFiveSequences.put(A_CAPTURE, new String[][]{
                {A_CAPTURE, H_PORTAL, A_DEFEND, H_PORTAL, T_COURAGE}
        });
        mFiveSequences.put(FD_LOSE, new String[][]{
                {FD_LOSE, H_SHAPERS, T_MESSAGE, FD_GAIN, CE_CHAOS}
        });
        mFiveSequences.put(CE_PURE, new String[][]{
                {CE_PURE, H_HUMAN, FD_FAILURE, TS_NOW_PRESENT, CE_CHAOS}
        });
        mFiveSequences.put(CE_IMPERFECT, new String[][]{
                {CE_IMPERFECT, H_XM, T_MESSAGE, H_HUMAN, CE_CHAOS},
                {CE_IMPERFECT, T_TRUTH, TS_OPEN_ACCEPT, CE_COMPLEX, T_ANSWER}
        });
        mFiveSequences.put(H_HUMAN, new String[][]{
                {H_HUMAN, H_SHAPERS, A_TOGETHER, A_CREATE, T_DESTINY},
                {H_HUMAN, TS_NOT_INSIDE, A_TOGETHER, CE_CIVILIZATION, FD_DETERIORATE}
        });
        mFiveSequences.put(A_RECHARGE_REPAIR, new String[][]{
                {A_RECHARGE_REPAIR, TS_NOW_PRESENT, A_RECHARGE_REPAIR, H_HUMAN, H_SOUL},
                {A_RECHARGE_REPAIR, H_SOUL, FD_LESS, H_HUMAN, CE_HARM}
        });
        mFiveSequences.put(TS_CLOSE_ALL, new String[][]{
                {TS_CLOSE_ALL, T_IDEA_THOUGHT, TS_PAST, TS_NOW_PRESENT, TS_FUTURE}
        });
        mFiveSequences.put(CE_STRONG, new String[][]{
                {CE_STRONG, A_TOGETHER, A_ATTACK_WAR, A_TOGETHER, T_DESTINY},
                {CE_STRONG, A_TOGETHER, A_ATTACK_WAR, A_TOGETHER, CE_CHAOS}
        });
        mFiveSequences.put(H_XM, new String[][]{
                {H_XM, CE_PATH, TS_FUTURE, T_DESTINY, CE_HARMONY_PEACE},
                {H_XM, A_CREATE, CE_COMPLEX, H_HUMAN, T_DESTINY}
        });
        mFiveSequences.put(H_PORTAL, new String[][]{
                {H_PORTAL, A_CREATE, CE_DANGER, A_PURSUE, CE_SAFETY},
                {H_PORTAL, CE_POTENTIAL, A_HELP, H_HUMAN, TS_FUTURE},
                {H_PORTAL, CE_BARRIER, A_DEFEND, H_HUMAN, H_SHAPERS},
                {H_PORTAL, FD_IMPROVE, H_HUMAN, TS_FUTURE, CE_CIVILIZATION}
        });
    }

    private void initGlyphPairs() {
        mGlyphPairs = new String[][]{{FD_GAIN, FD_LOSE}, {A_DESTROY, A_CREATE}, {A_ADVANCE, A_RETREAT},
                {TS_OLD, TS_NEW}, {A_BREATHE_LIVE, A_DIE}, {A_DEFEND, A_ATTACK_WAR}, {A_PURSUE, T_WANT},
                {CE_PATH, CE_BARRIER}, {T_COURAGE, T_FEAR}, {CE_CIVILIZATION, CE_NATURE},
                {A_LIBERATE, A_CAPTURE}, {FD_LESS, FD_MORE}, {H_MIND, H_SOUL}, {T_FORGET, T_IGNORE},
                {CE_PURE, CE_IMPURE}, {FD_EVOLUTION_SUCCESS, FD_FAILURE}, {FD_DETERIORATE, FD_IMPROVE},
                {T_MESSAGE, T_DATA}, {TS_PAST, TS_NOW_PRESENT, TS_FUTURE}, {CE_ALL, TS_CLOSE_ALL, TS_OPEN_ALL}};
    }

    public Map<String, int[]> getGlyphByCategory(@Category String category) {
        if (category.equals(C_ALL)) {
            return mBaseGlyphs;
        } else {
            String[] glyphKeys = mCategoryGlyphs.get(category);
            Map<String, int[]> categoryResult = new LinkedHashMap<>();
            for (String name : glyphKeys) {
                categoryResult.put(name, mBaseGlyphs.get(name));
            }
            return categoryResult;
        }
    }

    public Map<String, int[]> getBaseGlyph() {
        return mBaseGlyphs;
    }

    public Map<String, String[][]> getTwoSequences() {
        if (mTwoSequences == null) {
            initTwoSequences();
        }
        return mTwoSequences;
    }

    public Map<String, String[][]> getThreeSequences() {
        if (mThreeSequences == null) {
            initThreeSequences();
        }
        return mThreeSequences;
    }

    public Map<String, String[][]> getFourSequences() {
        if (mFourSequences == null) {
            initFourSequences();
        }
        return mFourSequences;
    }

    public Map<String, String[][]> getFiveSequences() {
        if (mFiveSequences == null) {
            initFiveSequences();
        }
        return mFiveSequences;
    }

    public String[][] getGlyphPairs() {
        if (mGlyphPairs == null) {
            initGlyphPairs();
        }
        return mGlyphPairs;
    }

    public int[] getGlyphPath(@GlyphName String name) {
        return mBaseGlyphs.get(name);
    }

    @StringDef({H_BODY, H_ENLIGHTENMENT, H_HUMAN, H_MIND, H_RESISTANCE_STRUGGLE, H_PORTAL, H_SELF,
            H_SHAPERS, H_SOUL, H_XM, A_ADVANCE, A_ATTACK_WAR, A_AVOID, A_BREATHE_LIVE, A_CAPTURE,
            A_CHANGE, A_CREATE, A_DEFEND, A_DESTROY, A_DIE, A_DISCOVER, A_ESCAPE, A_HIDE, A_JOURNEY,
            A_LIBERATE, A_NOURISH, A_PURSUE, A_REACT, A_REBEL, A_RETREAT, A_RECHARGE_REPAIR,
            A_RESTRAINT, A_SAVE, A_SEARCH, A_SEE, A_SEPERATE, A_SHARE, A_TOGETHER, A_USE, T_ANSWER,
            T_CONTEMPLATE, T_COURAGE, T_DATA, T_DESTINY, T_FEAR, T_FORGET, T_IDEA_THOUGHT, T_IGNORE,
            T_LIE, T_MESSAGE, T_QUESTION, T_TRUTH, T_WANT, FD_DETERIORATE, FD_EQUAL, FD_EVOLUTION_SUCCESS,
            FD_FAILURE, FD_FOLLOW, FD_GAIN, FD_GROW, FD_IMPROVE, FD_LEAD, FD_LESS, FD_LOSE, FD_MORE,
            FD_REDUCE, TS_AGAIN_REPEAT, TS_CLOSE_ALL, TS_DISTANCE_OUTSIDE, TS_END, TS_FUTURE, TS_NEW,
            TS_NOT_INSIDE, TS_NOW_PRESENT, TS_OLD, TS_OPEN_ACCEPT, TS_OPEN_ALL, TS_PAST, CE_ALL,
            CE_BALANCE_PERFECTION, CE_BARRIER, CE_CHAOS, CE_CIVILIZATION, CE_CLEAR, CE_COMPLEX,
            CE_CONFLICT, CE_DANGER, CE_DIFFICULT, CE_EASY, CE_HARM, CE_HARMONY_PEACE, CE_HAVE,
            A_HELP, CE_IMPERFECT, CE_IMPURE, CE_NATURE, CE_PATH, CE_POTENTIAL, CE_PURE, CE_SAFETY,
            CE_SIMPLE, CE_STABILITY_STAY, CE_STRONG, CE_WEAK})
    public @interface GlyphName {

    }

    @StringDef({C_ALL, C_HUMAN, C_ACTION, C_THOUGHT, C_FLU_DIRE, C_TIME_SPACE, C_COND_ENV})
    public @interface Category {

    }

    private static final class GlyphDataHolder {
        private static final BaseGlyphData HOLDER = new BaseGlyphData();
    }
}
