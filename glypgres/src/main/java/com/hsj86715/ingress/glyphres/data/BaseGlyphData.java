package com.hsj86715.ingress.glyphres.data;

import android.support.annotation.StringDef;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hushujun on 2017/5/17.
 */

public class BaseGlyphData {
    private static final String H_BODY_SHELL = "Body/Shell";
    public static final String H_ENLIGHTENMENT = "Enlightenment";
    private static final String H_BEING_HUMAN = "Being/Human";
    private static final String H_MIND = "Mind";
    private static final String H_RESISTANCE_STRUGGLE = "Resistance/Struggle";
    private static final String H_PORTAL = "Portal";
    private static final String H_INDIVIDUAL_SELF = "Individual/Self";
    private static final String H_COLLECTIVE_SHAPERS = "Shapers/Collective";
    private static final String H_SOUL_SPIRIT = "Soul/Spirit";
    private static final String H_XM = "XM";
    private static final String A_ADVANCE = "Advance";
    private static final String A_ATTACK_WAR = "Attack/War";
    private static final String A_AVOID = "Avoid";
    private static final String A_BREATHE_LIVE = "Breathe/Live";
    private static final String A_CAPTURE = "Capture";
    private static final String A_CHANGE_MODIFY = "Change/Modify";
    private static final String A_CREATE_CREATION = "Create/Creation";
    private static final String A_DEFEND = "Defend";
    private static final String A_DESTROY_DESTRUCTION = "Destroy/Destruction";
    private static final String A_DIE_DEATH = "Die/Death";
    private static final String A_DISCOVER = "Discover";
    private static final String A_ESCAPE = "Escape";
    private static final String A_HIDE = "Hide";
    private static final String A_HELP = "Help";
    private static final String A_JOURNEY = "Journey";
    private static final String A_LIBERATE = "Liberate";
    private static final String A_NOURISH = "Nourish";
    private static final String A_PURSUE_ASPIRATION = "Pursue/Aspiration";
    private static final String A_REACT = "React";
    private static final String A_REBEL = "Rebel";
    private static final String A_RETREAT = "Retreat";
    private static final String A_RECHARGE_REPAIR = "Recharge/Repair";
    private static final String A_RESTRAINT = "Restraint";
    private static final String A_SAVE_RESCUE = "Save/Rescue";
    private static final String A_SEARCH_SEEK = "Search/Seek";
    private static final String A_SEE = "See";
    private static final String A_SEPERATE = "Seperate";
    private static final String A_SHARE = "Share";
    private static final String A_TOGETHER = "Together";
    private static final String A_USE = "Use";
    private static final String T_ANSWER = "Answer";
    private static final String T_CONTEMPLATE = "Contemplate";
    private static final String T_COURAGE = "Courage";
    private static final String T_DATA = "Data";
    private static final String T_DESTINY = "Destiny";
    private static final String T_FEAR = "Fear";
    private static final String T_FORGET = "Forget";
    private static final String T_IDEA_THOUGHT = "Idea/Thought";
    private static final String T_IGNORE = "Ignore";
    private static final String T_LIE = "Lie";
    private static final String T_MESSAGE = "Message";
    private static final String T_QUESTION = "Question";
    private static final String T_TRUTH = "Truth";
    private static final String T_WANT_DESIRE = "Want/Desire";
    private static final String FD_DETERIORATE_ERODE = "Deteriorate/Erode";
    private static final String FD_EQUAL = "Equal";
    private static final String FD_EVOLUTION_PROGRESS_SUCCESS = "Evolution/Progress/Success";
    private static final String FD_FAILURE = "Failure";
    private static final String FD_FOLLOW = "Follow";
    private static final String FD_GAIN = "Gain";
    private static final String FD_GROW = "Grow";
    private static final String FD_IMPROVE = "Improve";
    private static final String FD_LEAD = "Lead";
    private static final String FD_LESS = "Less";
    private static final String FD_LOSE_LOSS = "Lose/Loss";
    private static final String FD_MORE = "More";
    private static final String FD_CONTRACT_REDUCE = "Contract/Reduce";
    private static final String TS_AGAIN_REPEAT = "Again/Repeat";
    private static final String TS_CLOSE_CLEAR_ALL = "Close/Clear All";
    private static final String TS_DISTANCE_OUTSIDE = "Distance/Outside";
    private static final String TS_CLOSE_END_FINALITY = "Close/End/Finality";
    private static final String TS_FUTURE = "Future";
    private static final String TS_NEW = "New";
    private static final String TS_INSIDE_NOT = "Inside/Not";
    private static final String TS_NOW_PRESENT = "Now/Present";
    private static final String TS_OLD = "Old";
    private static final String TS_OPEN_ACCEPT = "Open/Accept";
    private static final String TS_OPEN_ALL = "Open-All";
    private static final String TS_PAST = "Past";
    private static final String CE_ALL = "All";
    private static final String CE_BALANCE_PERFECTION = "Balance/Perfection";
    private static final String CE_BARRIER_OBSTACLE = "Barrier/Obstacle";
    private static final String CE_CHAOS_DISORDER = "Chaos/Disorder";
    private static final String CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE = "Civilization/City/Government/Structure";
    private static final String CE_CLEAR = "Clear";
    private static final String CE_COMPLEX = "Complex";
    private static final String CE_CONFLICT = "Conflict";
    private static final String CE_DANGER = "Danger";
    private static final String CE_DIFFICULT = "Difficult";
    private static final String CE_EASY = "Easy";
    private static final String CE_HARM = "Harm";
    private static final String CE_HARMONY_PEACE = "Harmony/Peace";
    private static final String CE_HAVE = "Have";
    private static final String CE_IMPERFECT = "Imperfect";
    private static final String CE_IMPURE = "Impure";
    private static final String CE_NATURE = "Nature";
    private static final String CE_PATH = "Path";
    private static final String CE_POTENTIAL = "Potential";
    private static final String CE_PURE_PURITY = "Pure/Purity";
    private static final String CE_SAFETY = "Safety";
    private static final String CE_SIMPLE = "Simple";
    private static final String CE_STABILITY_STAY = "Stability/Stay";
    private static final String CE_STRONG = "Strong";
    private static final String CE_WEAK = "Weak";
    public static final String C_ALL = "all";
    public static final String C_HUMAN = "human";
    public static final String C_ACTION = "action";
    public static final String C_THOUGHT = "thought";
    public static final String C_FLU_DIRE = "fluctuation/direction";
    public static final String C_TIME_SPACE = "time/space";
    public static final String C_COND_ENV = "condition/environment";
    //new Added 2017.11.18
    public static final String C_NEW_ADDED = "New Added";
    public static final String C_NO_CAREGORY = "Unknown Category";

    private static final String A_ABANDON = "Abandon";
    private static final String TS_BEGIN = "Begin";
    private static final String TS_DESTINATION = "Destination";
    private static final String FD_LEGACY = "Legacy";
    private static final String CE_NZEER = "N'Zeer";
    private static final String T_KNOWLEDGE = "Knowledge";
    private static final String T_PERSPECTIVE = "Perspective";
    private static final String H_US_WE = "Us/We";
    private static final String CE_ADAPT = "Adapt";
    private static final String TS_AFTER = "After";
    private static final String TS_BEFORE = "Before";
    private static final String CE_CONSEQUENCE = "Consequence";
    private static final String H_CREATIVITY = "Creativity";
    //    private static final String H_ENLIGHTENED = "Enlightened";
    private static final String H_I_ME = "I/Me";
    private static final String T_INTELLIGENCE = "Intelligence";
    private static final String A_INTERRUPT = "Interrupt";
    private static final String A_LIVE_AGAIN = "Live Again/Reincarnate";
    private static final String T_MYSTERY = "Mystery";
    private static final String A_PRESENCE = "Presence";
    private static final String A_CHASE = "Chase";
    //    private static final String H_RESIST = "Resist";
    private static final String A_SUSTAIN = "Sustain";
    private static final String A_SUSTAIN_ALL = "Sustain All";
    private static final String T_TECHNOLOGY = "Technology";
    private static final String H_THEM = "Them";
    private static final String CE_UNBOUNDED = "Unbounded";
    private static final String FD_VICTORY = "Victory";
    private static final String FD_WORTH = "Worth";
    private static final String H_OTHER_YOU_YOUR = "Other/You/Your";
    private static final String UNKNOWN_FISH = "Unknown(Fish)";
    private static final String UNKNOWN_BOMB = "Unknown(Bomb)";
    private static final String UNKNOWN_PLOUGH = "Unknown(Plough)";
    private static final String UNKNOWN_SOWN = "Unknown(Sown)";
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
        mBaseGlyphs.put(A_ABANDON, new int[]{3, 2, 1, 7, 8, 9});
        mBaseGlyphs.put(CE_ADAPT, new int[]{6, 7, 1, 10});
        mBaseGlyphs.put(A_ADVANCE, new int[]{8, 5, 4});
        mBaseGlyphs.put(TS_AFTER, new int[]{1, 2, 3, 11, 10, 1});
        mBaseGlyphs.put(TS_AGAIN_REPEAT, new int[]{8, 5, 7, 1, 2, 10});
        mBaseGlyphs.put(CE_ALL, new int[]{4, 6, 8, 9, 11, 3, 4});
        mBaseGlyphs.put(T_ANSWER, new int[]{5, 2, 10, 1});
        mBaseGlyphs.put(A_ATTACK_WAR, new int[]{8, 5, 4, 2, 11});
        mBaseGlyphs.put(A_AVOID, new int[]{6, 4, 2, 3, 10});
        mBaseGlyphs.put(CE_BALANCE_PERFECTION, new int[]{4, 1, 7, 8, 9, 11, 10, 1});
        mBaseGlyphs.put(CE_BARRIER_OBSTACLE, new int[]{4, 1, 10, 11});
        mBaseGlyphs.put(TS_BEFORE, new int[]{6, 5, 1, 7, 8, 6});
        mBaseGlyphs.put(TS_BEGIN, new int[]{4, 7, 9, 10});
        mBaseGlyphs.put(H_BEING_HUMAN, new int[]{9, 7, 5, 2, 10, 9});
        mBaseGlyphs.put(H_BODY_SHELL, new int[]{1, 5, 2, 1});
        mBaseGlyphs.put(A_BREATHE_LIVE, new int[]{6, 5, 1, 2, 3});
        mBaseGlyphs.put(A_CAPTURE, new int[]{3, 10, 1, 7, 8, 9});
        mBaseGlyphs.put(A_CHANGE_MODIFY, new int[]{7, 1, 9, 10});
        mBaseGlyphs.put(CE_CHAOS_DISORDER, new int[]{8, 6, 4, 3, 2, 1, 7, 9});
        mBaseGlyphs.put(A_CHASE, new int[]{8, 7, 5, 1, 4});
        mBaseGlyphs.put(CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, new int[]{6, 5, 7, 10, 2, 3});
        mBaseGlyphs.put(CE_CLEAR, new int[]{4, 1, 9});
        mBaseGlyphs.put(TS_CLOSE_CLEAR_ALL, new int[]{4, 6, 8, 9, 11, 3, 4, 1, 9});
        mBaseGlyphs.put(TS_CLOSE_END_FINALITY, new int[]{4, 1, 9, 10, 3, 4});
        mBaseGlyphs.put(H_COLLECTIVE_SHAPERS, new int[]{8, 7, 5, 4, 2, 10, 11});
        mBaseGlyphs.put(CE_COMPLEX, new int[]{2, 5, 1, 7});
        mBaseGlyphs.put(CE_CONFLICT, new int[]{8, 5, 7, 10, 2, 11});
        mBaseGlyphs.put(CE_CONSEQUENCE, new int[]{6, 5, 7, 10, 11});
        mBaseGlyphs.put(T_CONTEMPLATE, new int[]{4, 3, 11, 9, 7, 5, 1, 2});
        mBaseGlyphs.put(T_COURAGE, new int[]{8, 5, 7, 10});
        mBaseGlyphs.put(A_CREATE_CREATION, new int[]{8, 7, 1, 2, 3,});
        mBaseGlyphs.put(H_CREATIVITY, new int[]{5, 1, 9, 5});
        mBaseGlyphs.put(CE_DANGER, new int[]{4, 5, 1, 9});
        mBaseGlyphs.put(T_DATA, new int[]{4, 2, 1, 7, 9});
//        mBaseGlyphs.put(CE_DEATH, new int[]{8, 7, 1, 10, 11});
        mBaseGlyphs.put(A_DEFEND, new int[]{6, 7, 9, 10, 3});
        mBaseGlyphs.put(TS_DESTINATION, new int[]{3, 11, 9});
        mBaseGlyphs.put(T_DESTINY, new int[]{5, 1, 2, 10, 7, 9});
        mBaseGlyphs.put(FD_DETERIORATE_ERODE, new int[]{5, 1, 7, 8});
        mBaseGlyphs.put(A_DESTROY_DESTRUCTION, new int[]{6, 5, 1, 10, 11});
        mBaseGlyphs.put(A_DIE_DEATH, new int[]{8, 7, 1, 10, 11});
        mBaseGlyphs.put(CE_DIFFICULT, new int[]{7, 1, 10, 2, 3});
        mBaseGlyphs.put(A_DISCOVER, new int[]{3, 11, 9, 8});
        mBaseGlyphs.put(TS_DISTANCE_OUTSIDE, new int[]{8, 6, 4});
        mBaseGlyphs.put(CE_EASY, new int[]{2, 1, 7, 9});
//        mBaseGlyphs.put(H_ENLIGHTENED, new int[]{5, 2, 1, 5, 4, 3, 11, 9, 8});
        mBaseGlyphs.put(H_ENLIGHTENMENT, new int[]{5, 2, 1, 5, 4, 3, 11, 9});
        mBaseGlyphs.put(FD_EQUAL, new int[]{7, 5, 2, 10});
        mBaseGlyphs.put(A_ESCAPE, new int[]{4, 3, 2, 5, 7});
        mBaseGlyphs.put(FD_EVOLUTION_PROGRESS_SUCCESS, new int[]{4, 1, 5, 7});
        mBaseGlyphs.put(FD_FAILURE, new int[]{4, 1, 2, 10});
        mBaseGlyphs.put(T_FEAR, new int[]{5, 2, 10, 3});
        mBaseGlyphs.put(FD_FOLLOW, new int[]{4, 2, 3, 11});
        mBaseGlyphs.put(T_FORGET, new int[]{8, 7});
        mBaseGlyphs.put(TS_FUTURE, new int[]{3, 2, 10, 11});
        mBaseGlyphs.put(FD_GAIN, new int[]{6, 7});
        mBaseGlyphs.put(FD_GROW, new int[]{8, 5, 7});
        mBaseGlyphs.put(CE_HARM, new int[]{1, 2, 4, 5, 1, 10, 11});
        mBaseGlyphs.put(CE_HARMONY_PEACE, new int[]{1, 5, 4, 2, 1, 7, 9, 10, 1});
        mBaseGlyphs.put(CE_HAVE, new int[]{10, 1, 7, 9});
        mBaseGlyphs.put(A_HELP, new int[]{6, 5, 1, 7, 10});
        mBaseGlyphs.put(A_HIDE, new int[]{5, 2, 3, 10, 7});
        mBaseGlyphs.put(H_I_ME, new int[]{5, 2, 9, 5});
        mBaseGlyphs.put(T_IDEA_THOUGHT, new int[]{7, 8, 6, 5, 1, 10, 11, 3, 2});
        mBaseGlyphs.put(T_IGNORE, new int[]{10, 11});
        mBaseGlyphs.put(CE_IMPERFECT, new int[]{2, 1, 7, 5, 1});
        mBaseGlyphs.put(FD_IMPROVE, new int[]{3, 2, 1, 10});
        mBaseGlyphs.put(CE_IMPURE, new int[]{1, 5, 7, 1, 9});
        mBaseGlyphs.put(H_INDIVIDUAL_SELF, new int[]{8, 9, 11});
        mBaseGlyphs.put(TS_INSIDE_NOT, new int[]{5, 2, 10});
        mBaseGlyphs.put(T_INTELLIGENCE, new int[]{8, 7, 5, 1, 2, 3});
        mBaseGlyphs.put(A_INTERRUPT, new int[]{4, 1, 5, 6, 8, 7, 1, 9});
        mBaseGlyphs.put(A_JOURNEY, new int[]{3, 2, 1, 5, 6, 8, 9});
        mBaseGlyphs.put(T_KNOWLEDGE, new int[]{1, 2, 9, 5, 1});
        mBaseGlyphs.put(FD_LEAD, new int[]{4, 6, 8, 7, 9});
        mBaseGlyphs.put(FD_LEGACY, new int[]{8, 7, 5, 6, 4, 3, 2, 10, 11});
        mBaseGlyphs.put(FD_LESS, new int[]{5, 1, 2});
        mBaseGlyphs.put(A_LIBERATE, new int[]{4, 3, 2, 1, 5, 8});
        mBaseGlyphs.put(T_LIE, new int[]{7, 5, 1, 10, 2, 1});
        mBaseGlyphs.put(A_LIVE_AGAIN, new int[]{8, 5, 7, 1, 2, 3});
        mBaseGlyphs.put(FD_LOSE_LOSS, new int[]{10, 3});
        mBaseGlyphs.put(T_MESSAGE, new int[]{8, 5, 1, 10, 3});
        mBaseGlyphs.put(H_MIND, new int[]{9, 1, 5, 7, 9});
        mBaseGlyphs.put(FD_MORE, new int[]{7, 1, 10});
        mBaseGlyphs.put(T_MYSTERY, new int[]{6, 5, 2, 4, 5, 7});
        mBaseGlyphs.put(CE_NATURE, new int[]{8, 7, 5, 2, 10, 11});
        mBaseGlyphs.put(TS_NEW, new int[]{2, 10, 11});
        mBaseGlyphs.put(CE_NZEER, new int[]{9, 1, 2, 4, 1, 5, 4});
        mBaseGlyphs.put(A_NOURISH, new int[]{1, 7, 8, 9, 1});
        mBaseGlyphs.put(TS_NOW_PRESENT, new int[]{5, 7, 10, 2});
        mBaseGlyphs.put(TS_OLD, new int[]{6, 5, 7});
        mBaseGlyphs.put(TS_OPEN_ACCEPT, new int[]{9, 7, 10, 9});
        mBaseGlyphs.put(TS_OPEN_ALL, new int[]{9, 7, 10, 9, 11, 3, 4, 6, 8, 9});
        mBaseGlyphs.put(H_OTHER_YOU_YOUR, new int[]{4, 7, 10, 4});
        mBaseGlyphs.put(TS_PAST, new int[]{6, 5, 7, 8});
        mBaseGlyphs.put(CE_PATH, new int[]{4, 1, 7, 8});
        mBaseGlyphs.put(T_PERSPECTIVE, new int[]{8, 7, 1, 2, 4, 5, 1, 10, 11});
        mBaseGlyphs.put(H_PORTAL, new int[]{6, 8, 7, 10, 11, 3, 2, 5, 6});
        mBaseGlyphs.put(CE_POTENTIAL, new int[]{4, 1, 10, 11, 3});
        mBaseGlyphs.put(A_PRESENCE, new int[]{7, 10, 2, 1, 5, 7, 9, 10});
        mBaseGlyphs.put(CE_PURE_PURITY, new int[]{4, 1, 10, 2, 1});
        mBaseGlyphs.put(A_PURSUE_ASPIRATION, new int[]{6, 5, 4, 2});
        mBaseGlyphs.put(T_QUESTION, new int[]{4, 2, 5, 7});
        mBaseGlyphs.put(A_REACT, new int[]{2, 5, 1, 10, 11});
        mBaseGlyphs.put(A_REBEL, new int[]{6, 7, 1, 2, 3, 11});
        mBaseGlyphs.put(A_RECHARGE_REPAIR, new int[]{4, 6, 5, 1, 4});
        mBaseGlyphs.put(FD_CONTRACT_REDUCE, new int[]{10, 2, 11});
//        mBaseGlyphs.put(H_RESIST, new int[]{2, 5, 4, 1, 9, 10});
        mBaseGlyphs.put(H_RESISTANCE_STRUGGLE, new int[]{2, 5, 4, 1, 9, 7});
        mBaseGlyphs.put(A_RESTRAINT, new int[]{6, 5, 1, 10, 11, 9});
        mBaseGlyphs.put(A_RETREAT, new int[]{4, 2, 11});
        mBaseGlyphs.put(CE_SAFETY, new int[]{8, 5, 2, 11});
        mBaseGlyphs.put(A_SAVE_RESCUE, new int[]{7, 1, 10, 3});
        mBaseGlyphs.put(A_SEARCH_SEEK, new int[]{1, 2, 5, 7, 10});
        mBaseGlyphs.put(A_SEE, new int[]{5, 4});
        mBaseGlyphs.put(A_SEPERATE, new int[]{6, 5, 7, 1, 2, 10, 11});
        mBaseGlyphs.put(A_SHARE, new int[]{9, 8, 7, 10, 11});
        mBaseGlyphs.put(CE_SIMPLE, new int[]{7, 10});
        mBaseGlyphs.put(H_SOUL_SPIRIT, new int[]{9, 1, 2, 10, 9});
        mBaseGlyphs.put(CE_STABILITY_STAY, new int[]{8, 7, 10, 11});
        mBaseGlyphs.put(CE_STRONG, new int[]{5, 7, 10, 2, 5});
        mBaseGlyphs.put(A_SUSTAIN, new int[]{4, 1, 2, 3, 11, 10, 1, 9});
        mBaseGlyphs.put(A_SUSTAIN_ALL, new int[]{4, 1, 2, 3, 11, 10, 1, 9, 8, 6, 4, 3, 11, 9});
        mBaseGlyphs.put(T_TECHNOLOGY, new int[]{3, 2, 1, 7, 5, 1, 10, 11});
        mBaseGlyphs.put(H_THEM, new int[]{4, 7, 10});
        mBaseGlyphs.put(A_TOGETHER, new int[]{1, 5, 2, 1, 7, 8});
        mBaseGlyphs.put(T_TRUTH, new int[]{5, 1, 10, 2, 1, 7, 5});
        mBaseGlyphs.put(CE_UNBOUNDED, new int[]{1, 2, 5, 7, 10, 3, 4, 6, 8, 9, 11});
        mBaseGlyphs.put(H_US_WE, new int[]{5, 2, 9});
        mBaseGlyphs.put(A_USE, new int[]{1, 10, 3});
        mBaseGlyphs.put(FD_VICTORY, new int[]{4, 5, 9, 2, 4});
        mBaseGlyphs.put(T_WANT_DESIRE, new int[]{8, 7, 9, 10});
        mBaseGlyphs.put(CE_WEAK, new int[]{6, 5, 2, 10});
        mBaseGlyphs.put(FD_WORTH, new int[]{6, 7, 1, 10, 3});
        mBaseGlyphs.put(H_XM, new int[]{7, 5, 2, 10, 1, 7});

        mBaseGlyphs.put(UNKNOWN_FISH, new int[]{8, 7, 5, 2, 5, 4, 2, 10, 11, 10, 9, 7});
        mBaseGlyphs.put(UNKNOWN_BOMB, new int[]{4, 2, 10, 11, 9, 8, 7, 5, 4});
        mBaseGlyphs.put(UNKNOWN_PLOUGH, new int[]{6, 4, 1, 7, 8, 9, 11, 3});
        mBaseGlyphs.put(UNKNOWN_SOWN, new int[]{4, 1, 2, 3, 2, 1, 10, 11, 10, 1, 9, 1, 7, 8, 7, 1, 5, 6});
    }

    private void initGlyphCategories() {
        mCategoryGlyphs = new LinkedHashMap<>();
        mCategoryGlyphs.put(C_HUMAN, new String[]{H_BODY_SHELL, H_ENLIGHTENMENT, H_BEING_HUMAN, H_MIND,
                H_RESISTANCE_STRUGGLE, H_PORTAL, H_INDIVIDUAL_SELF, H_COLLECTIVE_SHAPERS, H_SOUL_SPIRIT,
                H_XM, H_CREATIVITY, /*H_ENLIGHTENED,*/ H_I_ME, /*H_RESIST,*/ H_THEM, H_US_WE, H_OTHER_YOU_YOUR});

        mCategoryGlyphs.put(C_ACTION, new String[]{A_ADVANCE, A_ATTACK_WAR, A_AVOID, A_BREATHE_LIVE,
                A_CAPTURE, A_CHANGE_MODIFY, A_CREATE_CREATION, A_DEFEND, A_DESTROY_DESTRUCTION, A_DIE_DEATH,
                A_DISCOVER, A_ESCAPE, A_HIDE, A_JOURNEY, A_LIBERATE, A_NOURISH, A_PURSUE_ASPIRATION,
                A_REACT, A_REBEL, A_RETREAT, A_RECHARGE_REPAIR, A_RESTRAINT, A_SAVE_RESCUE,
                A_SEARCH_SEEK, A_SEE, A_SEPERATE, A_SHARE, A_TOGETHER, A_USE, A_ABANDON, A_INTERRUPT,
                A_LIVE_AGAIN, A_PRESENCE, A_CHASE, A_SUSTAIN, A_SUSTAIN_ALL});

        mCategoryGlyphs.put(C_THOUGHT, new String[]{T_ANSWER, T_CONTEMPLATE, T_COURAGE, T_DATA,
                T_DESTINY, T_FEAR, T_FORGET, T_IDEA_THOUGHT, T_IGNORE, T_LIE, T_MESSAGE, T_QUESTION,
                T_TRUTH, T_WANT_DESIRE, T_INTELLIGENCE, T_KNOWLEDGE, T_MYSTERY, T_PERSPECTIVE, T_TECHNOLOGY});

        mCategoryGlyphs.put(C_FLU_DIRE, new String[]{FD_DETERIORATE_ERODE, FD_EQUAL, FD_EVOLUTION_PROGRESS_SUCCESS,
                FD_FAILURE, FD_FOLLOW, FD_GAIN, FD_GROW, FD_IMPROVE, FD_LEAD, FD_LESS, FD_LOSE_LOSS,
                FD_MORE, FD_CONTRACT_REDUCE, FD_LEGACY, FD_VICTORY, FD_WORTH});

        mCategoryGlyphs.put(C_TIME_SPACE, new String[]{TS_AGAIN_REPEAT, TS_CLOSE_CLEAR_ALL, TS_DISTANCE_OUTSIDE,
                TS_CLOSE_END_FINALITY, TS_FUTURE, TS_NEW, TS_INSIDE_NOT, TS_NOW_PRESENT, TS_OLD, TS_OPEN_ACCEPT,
                TS_OPEN_ALL, TS_PAST, TS_AFTER, TS_BEFORE, TS_BEGIN, TS_DESTINATION});

        mCategoryGlyphs.put(C_COND_ENV, new String[]{CE_ALL, CE_BALANCE_PERFECTION, CE_BARRIER_OBSTACLE,
                CE_CHAOS_DISORDER, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, CE_CLEAR, CE_COMPLEX,
                CE_CONFLICT, CE_DANGER, CE_DIFFICULT, CE_EASY, CE_HARM, CE_HARMONY_PEACE, CE_HAVE,
                A_HELP, CE_IMPERFECT, CE_IMPURE, CE_NATURE, CE_PATH, CE_POTENTIAL, CE_PURE_PURITY,
                CE_SAFETY, CE_SIMPLE, CE_STABILITY_STAY, CE_STRONG, CE_WEAK, CE_ADAPT, CE_CONSEQUENCE,
                CE_NZEER, CE_UNBOUNDED});

        mCategoryGlyphs.put(C_NEW_ADDED, new String[]{A_ABANDON, TS_BEGIN, TS_DESTINATION, FD_LEGACY,
                CE_NZEER, T_KNOWLEDGE, T_PERSPECTIVE, H_US_WE, CE_ADAPT, TS_AFTER, TS_BEFORE,
                CE_CONSEQUENCE, H_CREATIVITY, /*H_ENLIGHTENED,*/ H_I_ME, T_INTELLIGENCE, A_INTERRUPT,
                A_LIVE_AGAIN, T_MYSTERY, A_PRESENCE, A_CHASE, /*H_RESIST,*/ A_SUSTAIN, A_SUSTAIN_ALL,
                T_TECHNOLOGY, H_THEM, CE_UNBOUNDED, FD_VICTORY, FD_WORTH, H_OTHER_YOU_YOUR});

        mCategoryGlyphs.put(C_NO_CAREGORY, new String[]{UNKNOWN_FISH, UNKNOWN_BOMB, UNKNOWN_PLOUGH,
                UNKNOWN_SOWN});
    }

    private void initTwoSequences() {
        mTwoSequences = new LinkedHashMap<>();
        mTwoSequences.put(A_ABANDON, new String[][]{
                {A_ABANDON, T_FEAR}
        });
        mTwoSequences.put(CE_ADAPT, new String[][]{
                {CE_ADAPT, T_TECHNOLOGY}
        });
        mTwoSequences.put(CE_ALL, new String[][]{
                {CE_ALL, CE_CHAOS_DISORDER}
        });
        mTwoSequences.put(A_ATTACK_WAR, new String[][]{
                {A_ATTACK_WAR, CE_CHAOS_DISORDER},
                {A_ATTACK_WAR, CE_DIFFICULT}
        });
        mTwoSequences.put(A_CAPTURE, new String[][]{
                {A_CAPTURE, H_PORTAL}
        });
        mTwoSequences.put(A_CHANGE_MODIFY, new String[][]{
                {A_CHANGE_MODIFY, T_PERSPECTIVE}
        });
        mTwoSequences.put(CE_CLEAR, new String[][]{
                {CE_CLEAR, CE_CONSEQUENCE}
        });
        mTwoSequences.put(CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, new String[][]{
                {CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, CE_CHAOS_DISORDER}
        });
        mTwoSequences.put(A_CREATE_CREATION, new String[][]{
                {A_CREATE_CREATION, CE_DANGER}
        });
        mTwoSequences.put(A_DESTROY_DESTRUCTION, new String[][]{
                {A_DESTROY_DESTRUCTION, A_LIVE_AGAIN}
        });
        mTwoSequences.put(A_DISCOVER, new String[][]{
                {A_DISCOVER, CE_SAFETY},
                {A_DISCOVER, T_LIE},
                {A_DISCOVER, H_PORTAL}
        });
        mTwoSequences.put(TS_FUTURE, new String[][]{
                {TS_FUTURE, TS_DESTINATION}
        });
        mTwoSequences.put(A_HELP, new String[][]{
                {A_HELP, H_THEM}
        });
        mTwoSequences.put(A_INTERRUPT, new String[][]{
                {A_INTERRUPT, FD_EVOLUTION_PROGRESS_SUCCESS}
        });
        mTwoSequences.put(FD_LEAD, new String[][]{
                {FD_LEAD, H_ENLIGHTENMENT}
        });
        mTwoSequences.put(A_LIBERATE, new String[][]{
                {A_LIBERATE, H_XM}
        });
        mTwoSequences.put(A_NOURISH, new String[][]{
                {A_NOURISH, A_JOURNEY}
        });
        mTwoSequences.put(TS_OPEN_ALL, new String[][]{
                {TS_OPEN_ALL, T_TRUTH}
        });
        mTwoSequences.put(H_OTHER_YOU_YOUR, new String[][]{
                {H_OTHER_YOU_YOUR, CE_ADAPT}
        });
        mTwoSequences.put(CE_PATH, new String[][]{
                {CE_PATH, CE_BALANCE_PERFECTION}
        });
        mTwoSequences.put(H_PORTAL, new String[][]{
                {H_PORTAL, A_PRESENCE}
        });
        mTwoSequences.put(CE_PURE_PURITY, new String[][]{
                {CE_PURE_PURITY, T_TRUTH},
                {CE_PURE_PURITY, H_BODY_SHELL},
                {CE_PURE_PURITY, H_BEING_HUMAN}
        });
        mTwoSequences.put(A_PURSUE_ASPIRATION, new String[][]{
                {A_PURSUE_ASPIRATION, H_XM},
                {A_PURSUE_ASPIRATION, T_TRUTH}
        });
        mTwoSequences.put(T_QUESTION, new String[][]{
                {T_QUESTION, A_ATTACK_WAR}
        });
        mTwoSequences.put(A_SEARCH_SEEK, new String[][]{
                {A_SEARCH_SEEK, CE_POTENTIAL},
                {A_SEARCH_SEEK, TS_PAST}
        });
        mTwoSequences.put(A_SEE, new String[][]{
                {A_SEE, CE_UNBOUNDED}
        });
        mTwoSequences.put(A_SEPERATE, new String[][]{
                {A_SEPERATE, A_ATTACK_WAR}
        });
    }

    private void initThreeSequences() {
        mThreeSequences = new LinkedHashMap<>();
        mThreeSequences.put(A_ABANDON, new String[][]{
                {A_ABANDON, T_FEAR, A_TOGETHER}
        });
        mThreeSequences.put(TS_OPEN_ACCEPT, new String[][]{
                {TS_OPEN_ACCEPT, H_BEING_HUMAN, CE_WEAK}
        });
        mThreeSequences.put(A_ADVANCE, new String[][]{
                {A_ADVANCE, CE_PURE_PURITY, T_TRUTH}
        });
        mThreeSequences.put(TS_AGAIN_REPEAT, new String[][]{
                {TS_AGAIN_REPEAT, A_JOURNEY, TS_DISTANCE_OUTSIDE}
        });
        mThreeSequences.put(CE_ALL, new String[][]{
                {CE_ALL, H_XM, A_LIBERATE},
                {CE_ALL, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, CE_CHAOS_DISORDER}
        });
        mThreeSequences.put(T_ANSWER, new String[][]{
                {T_ANSWER, CE_NZEER, T_QUESTION}
        });
        mThreeSequences.put(A_ATTACK_WAR, new String[][]{
                {A_ATTACK_WAR, H_COLLECTIVE_SHAPERS, CE_CHAOS_DISORDER},
                {A_ATTACK_WAR, A_CREATE_CREATION, CE_DANGER},
                {A_ATTACK_WAR, A_DESTROY_DESTRUCTION, TS_FUTURE},
                {A_ATTACK_WAR, CE_DIFFICULT, TS_FUTURE},
                {A_ATTACK_WAR, H_COLLECTIVE_SHAPERS, FD_EVOLUTION_PROGRESS_SUCCESS}
        });
        mThreeSequences.put(A_AVOID, new String[][]{
                {A_AVOID, CE_CHAOS_DISORDER, H_SOUL_SPIRIT},
                {A_AVOID, T_DESTINY, T_LIE},
                {A_AVOID, CE_PURE_PURITY, CE_CHAOS_DISORDER},
                {A_AVOID, CE_COMPLEX, CE_CONFLICT}
        });
        mThreeSequences.put(CE_BALANCE_PERFECTION, new String[][]{
                {CE_BALANCE_PERFECTION, CE_PATH, CE_HARMONY_PEACE}
        });
        mThreeSequences.put(TS_BEGIN, new String[][]{
                {TS_BEGIN, TS_NEW, H_RESISTANCE_STRUGGLE}
        });
        mThreeSequences.put(H_BEING_HUMAN, new String[][]{
                {H_BEING_HUMAN, T_KNOWLEDGE, FD_LEGACY},
                {H_BEING_HUMAN, A_ESCAPE, CE_ALL},
                {H_BEING_HUMAN, T_INTELLIGENCE, CE_UNBOUNDED},
                {H_BEING_HUMAN, FD_EVOLUTION_PROGRESS_SUCCESS, CE_UNBOUNDED},
                {H_BEING_HUMAN, FD_FAILURE, CE_UNBOUNDED}
        });
        mThreeSequences.put(A_CAPTURE, new String[][]{
                {A_CAPTURE, H_COLLECTIVE_SHAPERS, H_PORTAL},
                {A_CAPTURE, FD_VICTORY, A_TOGETHER}
        });
        mThreeSequences.put(A_CHANGE_MODIFY, new String[][]{
                {A_CHANGE_MODIFY, H_BEING_HUMAN, TS_FUTURE},
                {A_CHANGE_MODIFY, T_PERSPECTIVE, T_TECHNOLOGY}
        });
        mThreeSequences.put(CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, new String[][]{
                {CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, A_ATTACK_WAR, CE_CHAOS_DISORDER}
        });
        mThreeSequences.put(CE_CLEAR, new String[][]{
                {CE_CLEAR, H_OTHER_YOU_YOUR, H_MIND}
        });
        mThreeSequences.put(CE_COMPLEX, new String[][]{
                {CE_COMPLEX, A_JOURNEY, TS_FUTURE}
        });
        mThreeSequences.put(CE_CONFLICT, new String[][]{
                {CE_CONFLICT, FD_GROW, A_ATTACK_WAR}
        });
        mThreeSequences.put(T_COURAGE, new String[][]{
                {T_COURAGE, T_DESTINY, A_REBEL}
        });
        mThreeSequences.put(A_CREATE_CREATION, new String[][]{
                {A_CREATE_CREATION, T_TRUTH, FD_LEGACY},
                {A_CREATE_CREATION, TS_NEW, TS_FUTURE}
        });
        mThreeSequences.put(CE_DANGER, new String[][]{
                {CE_DANGER, A_CHANGE_MODIFY, TS_PAST}
        });
        mThreeSequences.put(A_DEFEND, new String[][]{
                {A_DEFEND, H_BEING_HUMAN, T_LIE}
        });
        mThreeSequences.put(A_DESTROY_DESTRUCTION, new String[][]{
                {A_DESTROY_DESTRUCTION, CE_DIFFICULT, CE_BARRIER_OBSTACLE},
                {A_DESTROY_DESTRUCTION, CE_IMPURE, T_TRUTH},
                {A_DESTROY_DESTRUCTION, CE_WEAK, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE},
                {A_DESTROY_DESTRUCTION, T_DESTINY, CE_BARRIER_OBSTACLE}
        });
        mThreeSequences.put(A_DISCOVER, new String[][]{
                {A_DISCOVER, H_RESISTANCE_STRUGGLE, T_TRUTH},
                {A_DISCOVER, H_COLLECTIVE_SHAPERS, H_ENLIGHTENMENT},
                {A_DISCOVER, H_COLLECTIVE_SHAPERS, T_LIE},
                {A_DISCOVER, H_COLLECTIVE_SHAPERS, T_MESSAGE},
                {A_DISCOVER, CE_PURE_PURITY, T_TRUTH}
        });
        mThreeSequences.put(A_ESCAPE, new String[][]{
                {A_ESCAPE, CE_IMPURE, FD_EVOLUTION_PROGRESS_SUCCESS},
                {A_ESCAPE, CE_IMPURE, TS_FUTURE}
        });
        mThreeSequences.put(T_FEAR, new String[][]{
                {T_FEAR, CE_CHAOS_DISORDER, H_XM},
                {T_FEAR, CE_IMPERFECT, T_TECHNOLOGY}
        });
        mThreeSequences.put(FD_FOLLOW, new String[][]{
                {FD_FOLLOW, CE_PURE_PURITY, A_JOURNEY}
        });
        mThreeSequences.put(TS_FUTURE, new String[][]{
                {TS_FUTURE, FD_EQUAL, TS_PAST}
        });
        mThreeSequences.put(FD_GAIN, new String[][]{
                {FD_GAIN, CE_DIFFICULT, CE_BARRIER_OBSTACLE},
                {FD_GAIN, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, CE_HARMONY_PEACE},
                {FD_GAIN, FD_MORE, T_KNOWLEDGE}
        });
        mThreeSequences.put(FD_GROW, new String[][]{
                {FD_GROW, H_COLLECTIVE_SHAPERS, A_PRESENCE}
        });
        mThreeSequences.put(CE_HARM, new String[][]{
                {CE_HARM, CE_DANGER, A_AVOID}
        });
        mThreeSequences.put(A_HELP, new String[][]{
                {A_HELP, H_US_WE, CE_ALL}
        });
        mThreeSequences.put(H_BEING_HUMAN, new String[][]{
                {H_BEING_HUMAN, FD_GAIN, CE_SAFETY},
                {H_BEING_HUMAN, T_KNOWLEDGE, FD_LEGACY}
        });
        mThreeSequences.put(CE_HARMONY_PEACE, new String[][]{
                {CE_HARMONY_PEACE, CE_SIMPLE, A_JOURNEY},
                {CE_HARMONY_PEACE, FD_WORTH, FD_MORE}
        });
        mThreeSequences.put(FD_IMPROVE, new String[][]{
                {FD_IMPROVE, A_ADVANCE, TS_NOW_PRESENT},
                {FD_IMPROVE, H_BEING_HUMAN, H_COLLECTIVE_SHAPERS}
        });
        mThreeSequences.put(TS_INSIDE_NOT, new String[][]{
                {TS_INSIDE_NOT, H_XM, T_TRUTH},
                {TS_INSIDE_NOT, H_MIND, TS_FUTURE}
        });
        mThreeSequences.put(A_INTERRUPT, new String[][]{
                {A_INTERRUPT, H_ENLIGHTENMENT, T_TECHNOLOGY}
        });
        mThreeSequences.put(A_JOURNEY, new String[][]{
                {A_JOURNEY, TS_INSIDE_NOT, H_SOUL_SPIRIT}
        });
        mThreeSequences.put(FD_LEAD, new String[][]{
                {FD_LEAD, H_ENLIGHTENMENT, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE},
                {FD_LEAD, H_RESISTANCE_STRUGGLE, T_QUESTION}
        });
        mThreeSequences.put(A_LIBERATE, new String[][]{
                {A_LIBERATE, H_BEING_HUMAN, TS_FUTURE},
                {A_LIBERATE, H_PORTAL, CE_POTENTIAL}
        });
        mThreeSequences.put(T_LIE, new String[][]{
                {T_LIE, FD_EQUAL, TS_FUTURE}
        });
        mThreeSequences.put(FD_LOSE_LOSS, new String[][]{
                {FD_LOSE_LOSS, A_ATTACK_WAR, A_RETREAT}
        });
        mThreeSequences.put(H_MIND, new String[][]{
                {H_MIND, TS_OPEN_ACCEPT, A_BREATHE_LIVE}
        });
        mThreeSequences.put(CE_NATURE, new String[][]{
                {CE_NATURE, CE_PURE_PURITY, A_DEFEND}
        });
        mThreeSequences.put(CE_NZEER, new String[][]{
                {CE_NZEER, T_TECHNOLOGY, CE_BALANCE_PERFECTION}
        });
        mThreeSequences.put(TS_OPEN_ALL, new String[][]{
                {TS_OPEN_ALL, H_PORTAL, FD_EVOLUTION_PROGRESS_SUCCESS}
        });
        mThreeSequences.put(H_OTHER_YOU_YOUR, new String[][]{
                {H_OTHER_YOU_YOUR, A_HIDE, CE_CHAOS_DISORDER}
        });
        mThreeSequences.put(CE_PATH, new String[][]{
                {CE_PATH, CE_HARMONY_PEACE, CE_DIFFICULT}
        });
        mThreeSequences.put(CE_HARMONY_PEACE, new String[][]{
                {CE_HARMONY_PEACE, FD_WORTH, FD_MORE}
        });
        mThreeSequences.put(A_PURSUE_ASPIRATION, new String[][]{
                {A_PURSUE_ASPIRATION, CE_PURE_PURITY, H_BODY_SHELL}
        });
        mThreeSequences.put(T_QUESTION, new String[][]{
                {T_QUESTION, H_COLLECTIVE_SHAPERS, CE_CHAOS_DISORDER},
                {T_QUESTION, A_HIDE, T_TRUTH},
                {T_QUESTION, CE_POTENTIAL, T_MYSTERY},
                {T_QUESTION, CE_CONFLICT, T_DATA}
        });
        mThreeSequences.put(A_REACT, new String[][]{
                {A_REACT, CE_IMPURE, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE}
        });
        mThreeSequences.put(A_RETREAT, new String[][]{
                {A_RETREAT, A_SEARCH_SEEK, CE_SAFETY}
        });
        mThreeSequences.put(A_SEPERATE, new String[][]{
                {A_SEPERATE, TS_FUTURE, FD_EVOLUTION_PROGRESS_SUCCESS}
        });
        mThreeSequences.put(A_SHARE, new String[][]{
                {A_SHARE, H_ENLIGHTENMENT, T_KNOWLEDGE},
                {A_SHARE, H_RESISTANCE_STRUGGLE, T_MESSAGE}
        });
        mThreeSequences.put(T_TECHNOLOGY, new String[][]{
                {T_TECHNOLOGY, A_CAPTURE, FD_VICTORY}
        });
        mThreeSequences.put(A_TOGETHER, new String[][]{
                {A_TOGETHER, A_PURSUE_ASPIRATION, CE_SAFETY},
                {A_TOGETHER, TS_CLOSE_END_FINALITY, T_MYSTERY}
        });
        mThreeSequences.put(T_TRUTH, new String[][]{
                {T_TRUTH, A_NOURISH, H_SOUL_SPIRIT}
        });
        mThreeSequences.put(H_XM, new String[][]{
                {H_XM, A_NOURISH, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE},
                {H_XM, FD_GROW, H_CREATIVITY}
        });
        mThreeSequences.put(H_OTHER_YOU_YOUR, new String[][]{
                {H_OTHER_YOU_YOUR, A_HIDE, CE_CHAOS_DISORDER}
        });
    }

    private void initFourSequences() {
        mFourSequences = new LinkedHashMap<>();
        mFourSequences.put(A_ABANDON, new String[][]{
                {A_ABANDON, T_FEAR, A_SEE, TS_FUTURE},
                {A_ABANDON, T_FEAR, A_DEFEND, TS_FUTURE}
        });
        mFourSequences.put(CE_ADAPT, new String[][]{
                {CE_ADAPT, T_IDEA_THOUGHT, A_DISCOVER, FD_EVOLUTION_PROGRESS_SUCCESS}
        });
        mFourSequences.put(A_ADVANCE, new String[][]{
                {A_ADVANCE, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, TS_AGAIN_REPEAT, FD_FAILURE}}
        );
        mFourSequences.put(TS_AFTER, new String[][]{
                {TS_AFTER, CE_IMPERFECT, H_BEING_HUMAN, A_PRESENCE}
        });
        mFourSequences.put(CE_ALL, new String[][]{
                {CE_ALL, CE_CHAOS_DISORDER, TS_INSIDE_NOT, H_BODY_SHELL}
        });
        mFourSequences.put(A_ATTACK_WAR, new String[][]{
                {A_ATTACK_WAR, CE_WEAK, H_COLLECTIVE_SHAPERS, T_LIE},
                {A_ATTACK_WAR, H_RESISTANCE_STRUGGLE, A_PURSUE_ASPIRATION, H_ENLIGHTENMENT},
                {A_ATTACK_WAR, H_ENLIGHTENMENT, A_PURSUE_ASPIRATION, H_RESISTANCE_STRUGGLE},
                {A_ATTACK_WAR, TS_INSIDE_NOT, FD_WORTH, CE_CONSEQUENCE}
        });
        mFourSequences.put(A_AVOID, new String[][]{
                {A_AVOID, H_XM, T_MESSAGE, T_LIE}
        });
        mFourSequences.put(CE_BALANCE_PERFECTION, new String[][]{
                {CE_BALANCE_PERFECTION, CE_BALANCE_PERFECTION, CE_SAFETY, CE_ALL}
        });
        mFourSequences.put(TS_BEFORE, new String[][]{
                {TS_BEFORE, T_MYSTERY, TS_AFTER, T_KNOWLEDGE}
        });
        mFourSequences.put(TS_BEGIN, new String[][]{
                {TS_BEGIN, A_JOURNEY, A_BREATHE_LIVE, H_XM},
                {TS_BEGIN, H_BEING_HUMAN, FD_LEGACY, TS_NOW_PRESENT}
        });
        mFourSequences.put(H_BEING_HUMAN, new String[][]{
                {H_BEING_HUMAN, CE_HAVE, CE_IMPURE, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE},
                {H_BEING_HUMAN, H_SOUL_SPIRIT, CE_STRONG, CE_PURE_PURITY},
                {H_BEING_HUMAN, TS_PAST, TS_NOW_PRESENT, TS_FUTURE}
        });
        mFourSequences.put(A_BREATHE_LIVE, new String[][]{
                {A_BREATHE_LIVE, TS_AGAIN_REPEAT, A_JOURNEY, TS_AGAIN_REPEAT},
                {A_BREATHE_LIVE, CE_NATURE, CE_BALANCE_PERFECTION, CE_HARMONY_PEACE}
        });
        mFourSequences.put(A_CAPTURE, new String[][]{
                {A_CAPTURE, T_FEAR, A_DISCOVER, T_COURAGE}
        });
        mFourSequences.put(A_CHANGE_MODIFY, new String[][]{
                {A_CHANGE_MODIFY, H_BEING_HUMAN, CE_POTENTIAL, A_USE},
                {A_CHANGE_MODIFY, TS_FUTURE, A_CAPTURE, T_DESTINY},
                {A_CHANGE_MODIFY, H_BODY_SHELL, FD_IMPROVE, H_BEING_HUMAN},
                {A_CHANGE_MODIFY, T_PERSPECTIVE, TS_BEGIN, TS_NEW}
        });
        mFourSequences.put(CE_CHAOS_DISORDER, new String[][]{
                {CE_CHAOS_DISORDER, CE_BARRIER_OBSTACLE, H_COLLECTIVE_SHAPERS, H_PORTAL}
        });
        mFourSequences.put(CE_CLEAR, new String[][]{
                {CE_CLEAR, H_MIND, TS_OPEN_ACCEPT, H_MIND}
        });
        mFourSequences.put(TS_CLOSE_CLEAR_ALL, new String[][]{
                {TS_CLOSE_CLEAR_ALL, TS_OPEN_ALL, A_DISCOVER, T_TRUTH}
        });
        mFourSequences.put(H_COLLECTIVE_SHAPERS, new String[][]{
                {H_COLLECTIVE_SHAPERS, A_SEE, CE_POTENTIAL, FD_EVOLUTION_PROGRESS_SUCCESS},
                {H_COLLECTIVE_SHAPERS, H_PORTAL, H_MIND, A_RESTRAINT},
                {H_COLLECTIVE_SHAPERS, TS_PAST, TS_NOW_PRESENT, TS_FUTURE},
                {H_COLLECTIVE_SHAPERS, H_MIND, CE_COMPLEX, CE_HARMONY_PEACE},
                {H_COLLECTIVE_SHAPERS, CE_HAVE, CE_STRONG, CE_PATH},
                {H_COLLECTIVE_SHAPERS, CE_CHAOS_DISORDER, CE_PURE_PURITY, CE_HARM},
                {H_COLLECTIVE_SHAPERS, T_MESSAGE, TS_CLOSE_END_FINALITY, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE}
        });
        mFourSequences.put(CE_COMPLEX, new String[][]{
                {CE_COMPLEX, H_COLLECTIVE_SHAPERS, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, CE_STRONG}
        });
        mFourSequences.put(T_CONTEMPLATE, new String[][]{
                {T_CONTEMPLATE, CE_COMPLEX, H_COLLECTIVE_SHAPERS, T_TRUTH},
                {T_CONTEMPLATE, CE_COMPLEX, H_COLLECTIVE_SHAPERS, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE},
                {T_CONTEMPLATE, H_INDIVIDUAL_SELF, H_COLLECTIVE_SHAPERS, T_TRUTH},
                {T_CONTEMPLATE, H_INDIVIDUAL_SELF, CE_PATH, T_TRUTH}
        });
        mFourSequences.put(T_COURAGE, new String[][]{
                {T_COURAGE, A_ATTACK_WAR, H_COLLECTIVE_SHAPERS, TS_FUTURE}
        });
        mFourSequences.put(A_CREATE_CREATION, new String[][]{
                {A_CREATE_CREATION, TS_DISTANCE_OUTSIDE, CE_IMPURE, CE_PATH},
                {A_CREATE_CREATION, TS_FUTURE, TS_INSIDE_NOT, A_ATTACK_WAR},
                {A_CREATE_CREATION, TS_FUTURE, A_CHANGE_MODIFY, T_DESTINY},
                {A_CREATE_CREATION, TS_NEW, H_PORTAL, CE_POTENTIAL}
        });
        mFourSequences.put(A_DEFEND, new String[][]{
                {A_DEFEND, H_BEING_HUMAN, CE_NZEER, T_LIE},
                {A_DEFEND, T_MESSAGE, T_ANSWER, T_IDEA_THOUGHT}
        });
        mFourSequences.put(A_DESTROY_DESTRUCTION, new String[][]{
                {A_DESTROY_DESTRUCTION, T_DESTINY, H_BEING_HUMAN, T_LIE},
                {A_DESTROY_DESTRUCTION, CE_COMPLEX, H_COLLECTIVE_SHAPERS, T_LIE}
        });
        mFourSequences.put(FD_DETERIORATE_ERODE, new String[][]{
                {FD_DETERIORATE_ERODE, H_BEING_HUMAN, CE_WEAK, A_REBEL}
        });
        mFourSequences.put(CE_DIFFICULT, new String[][]{
                {CE_DIFFICULT, T_KNOWLEDGE, A_ADVANCE, H_MIND}
        });
        mFourSequences.put(H_ENLIGHTENMENT, new String[][]{
                {H_ENLIGHTENMENT, A_CAPTURE, FD_VICTORY, A_TOGETHER}
        });
        mFourSequences.put(A_ESCAPE, new String[][]{
                {A_ESCAPE, CE_SIMPLE, H_BEING_HUMAN, TS_FUTURE},
                {A_ESCAPE, TS_BEFORE, CE_PURE_PURITY, A_DIE_DEATH}
        });
        mFourSequences.put(T_FEAR, new String[][]{
                {T_FEAR, CE_IMPERFECT, CE_NZEER, T_TECHNOLOGY}
        });
        mFourSequences.put(FD_FOLLOW, new String[][]{
                {FD_FOLLOW, H_COLLECTIVE_SHAPERS, H_PORTAL, T_MESSAGE}
        });
        mFourSequences.put(T_FORGET, new String[][]{
                {T_FORGET, CE_CONFLICT, TS_OPEN_ACCEPT, A_ATTACK_WAR}
        });
        mFourSequences.put(FD_GAIN, new String[][]{
                {FD_GAIN, H_PORTAL, A_ATTACK_WAR, CE_WEAK}
        });
        mFourSequences.put(CE_HARMONY_PEACE, new String[][]{
                {CE_HARMONY_PEACE, CE_PATH, A_NOURISH, TS_NOW_PRESENT}
        });
        mFourSequences.put(A_HELP, new String[][]{
                {A_HELP, FD_GAIN, A_CREATE_CREATION, A_PURSUE_ASPIRATION},
                {A_HELP, H_COLLECTIVE_SHAPERS, A_CREATE_CREATION, TS_FUTURE},
                {A_HELP, H_US_WE, A_SAVE_RESCUE, H_US_WE},
                {A_HELP, H_ENLIGHTENMENT, CE_STRONG, FD_VICTORY},
                {A_HELP, H_RESISTANCE_STRUGGLE, CE_STRONG, FD_VICTORY}
        });
        mFourSequences.put(A_HIDE, new String[][]{
                {A_HIDE, CE_IMPURE, H_BEING_HUMAN, T_IDEA_THOUGHT},
                {A_HIDE, CE_CHAOS_DISORDER, TS_INSIDE_NOT, H_BODY_SHELL}
        });
        mFourSequences.put(T_IGNORE, new String[][]{
                {T_IGNORE, H_BEING_HUMAN, CE_CHAOS_DISORDER, T_LIE}
        });
        mFourSequences.put(FD_IMPROVE, new String[][]{
                {FD_IMPROVE, H_BODY_SHELL, A_PURSUE_ASPIRATION, A_JOURNEY},
                {FD_IMPROVE, H_BODY_SHELL, H_MIND, H_SOUL_SPIRIT},
                {FD_IMPROVE, H_MIND, A_JOURNEY, TS_INSIDE_NOT}
        });
        mFourSequences.put(TS_INSIDE_NOT, new String[][]{
                {TS_INSIDE_NOT, H_MIND, A_JOURNEY, CE_BALANCE_PERFECTION}
        });
        mFourSequences.put(A_INTERRUPT, new String[][]{
                {A_INTERRUPT, T_MESSAGE, FD_GAIN, A_ADVANCE}
        });
        mFourSequences.put(A_JOURNEY, new String[][]{
                {A_JOURNEY, TS_INSIDE_NOT, FD_IMPROVE, H_SOUL_SPIRIT}
        });
        mFourSequences.put(T_KNOWLEDGE, new String[][]{
                {T_KNOWLEDGE, A_HELP, FD_GAIN, FD_VICTORY}
        });
        mFourSequences.put(FD_LEAD, new String[][]{
                {FD_LEAD, A_PURSUE_ASPIRATION, A_REACT, A_DEFEND}
        });
        mFourSequences.put(FD_LESS, new String[][]{
                {FD_LESS, CE_CHAOS_DISORDER, FD_MORE, CE_STABILITY_STAY},
                {FD_LESS, T_TRUTH, FD_MORE, CE_CHAOS_DISORDER},
                {FD_LESS, H_SOUL_SPIRIT, FD_MORE, H_MIND}
        });
        mFourSequences.put(A_LIBERATE, new String[][]{
                {A_LIBERATE, H_XM, H_PORTAL, A_TOGETHER}
        });
        mFourSequences.put(A_LIVE_AGAIN, new String[][]{
                {A_LIVE_AGAIN, A_DIE_DEATH, TS_AGAIN_REPEAT, FD_EVOLUTION_PROGRESS_SUCCESS}
        });
        mFourSequences.put(FD_LOSE_LOSS, new String[][]{
                {FD_LOSE_LOSS, CE_DANGER, FD_GAIN, CE_SAFETY}
        });
        mFourSequences.put(A_NOURISH, new String[][]{
                {A_NOURISH, H_XM, A_CREATE_CREATION, T_IDEA_THOUGHT}
        });
        mFourSequences.put(CE_NZEER, new String[][]{
                {CE_NZEER, FD_LEGACY, A_CREATE_CREATION, TS_FUTURE},
                {CE_NZEER, H_COLLECTIVE_SHAPERS, H_RESISTANCE_STRUGGLE, T_KNOWLEDGE},
                {CE_NZEER, T_TECHNOLOGY, H_MIND, FD_EVOLUTION_PROGRESS_SUCCESS},
                {CE_NZEER, T_TECHNOLOGY, CE_BALANCE_PERFECTION, T_TRUTH}
        });
        mFourSequences.put(TS_PAST, new String[][]{
                {TS_PAST, TS_AGAIN_REPEAT, TS_NOW_PRESENT, TS_AGAIN_REPEAT}
        });
        mFourSequences.put(CE_PATH, new String[][]{
                {CE_PATH, A_RESTRAINT, CE_STRONG, CE_SAFETY}
        });
        mFourSequences.put(H_PORTAL, new String[][]{
                {H_PORTAL, CE_HAVE, T_TRUTH, T_DATA},
                {H_PORTAL, CE_POTENTIAL, A_CHANGE_MODIFY, TS_FUTURE},
                {H_PORTAL, A_DIE_DEATH, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, A_DIE_DEATH},
                {H_PORTAL, A_CHANGE_MODIFY, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, TS_CLOSE_END_FINALITY}
        });
        mFourSequences.put(T_QUESTION, new String[][]{
                {T_QUESTION, T_TRUTH, FD_GAIN, TS_FUTURE}
        });
        mFourSequences.put(H_RESISTANCE_STRUGGLE, new String[][]{
                {H_RESISTANCE_STRUGGLE, FD_IMPROVE, H_BEING_HUMAN, H_SOUL_SPIRIT},
                {H_RESISTANCE_STRUGGLE, A_DEFEND, H_COLLECTIVE_SHAPERS, CE_DANGER}
        });
        mFourSequences.put(A_RESTRAINT, new String[][]{
                {A_RESTRAINT, CE_PATH, FD_GAIN, CE_HARMONY_PEACE},
                {A_RESTRAINT, T_FEAR, A_AVOID, CE_DANGER}
        });
        mFourSequences.put(A_SEARCH_SEEK, new String[][]{
                {A_SEARCH_SEEK, T_TRUTH, A_SAVE_RESCUE, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE},
                {A_SEARCH_SEEK, H_XM, A_SAVE_RESCUE, H_PORTAL},
                {A_SEARCH_SEEK, T_DATA, A_DISCOVER, CE_PATH},
                {A_SEARCH_SEEK, H_XM, TS_DISTANCE_OUTSIDE, TS_DESTINATION}
        });
        mFourSequences.put(A_SEE, new String[][]{
                {A_SEE, T_TRUTH, A_SEE, TS_FUTURE}
        });
        mFourSequences.put(A_SEPERATE, new String[][]{
                {A_SEPERATE, CE_WEAK, T_IGNORE, T_TRUTH}
        });
        mFourSequences.put(CE_SIMPLE, new String[][]{
                {CE_SIMPLE, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, CE_IMPURE, CE_WEAK},
                {CE_SIMPLE, T_TRUTH, A_BREATHE_LIVE, CE_NATURE},
                {CE_SIMPLE, T_MESSAGE, CE_COMPLEX, T_IDEA_THOUGHT}
        });
        mFourSequences.put(H_SOUL_SPIRIT, new String[][]{
                {H_SOUL_SPIRIT, A_REBEL, H_BEING_HUMAN, A_DIE_DEATH}
        });
        mFourSequences.put(CE_STABILITY_STAY, new String[][]{
                {CE_STABILITY_STAY, A_TOGETHER, A_DEFEND, T_TRUTH}
        });
        mFourSequences.put(CE_STRONG, new String[][]{
                {CE_STRONG, T_IDEA_THOUGHT, A_PURSUE_ASPIRATION, T_TRUTH},
                {CE_STRONG, A_TOGETHER, A_AVOID, A_ATTACK_WAR},
                {CE_STRONG, H_RESISTANCE_STRUGGLE, A_CAPTURE, H_PORTAL}
        });
        mFourSequences.put(T_TECHNOLOGY, new String[][]{
                {T_TECHNOLOGY, T_INTELLIGENCE, A_SEE, CE_ALL},
                {T_TECHNOLOGY, T_INTELLIGENCE, FD_GROW, CE_UNBOUNDED}
        });
        mFourSequences.put(A_TOGETHER, new String[][]{
                {A_TOGETHER, A_DISCOVER, CE_HARMONY_PEACE, FD_EQUAL}
        });
        mFourSequences.put(T_TRUTH, new String[][]{
                {T_TRUTH, T_IDEA_THOUGHT, A_DISCOVER, H_XM}
        });
//        mFourSequences.put(UNITY,new String[][]{
//                {UNITY,A_TOGETHER,TS_CLOSE_END_FINALITY,T_MYSTERY}
//        });
        mFourSequences.put(H_XM, new String[][]{
                {H_XM, CE_HAVE, H_MIND, A_JOURNEY},
                {H_XM, A_DIE_DEATH, CE_CHAOS_DISORDER, A_BREATHE_LIVE},
                {H_XM, A_NOURISH, CE_STRONG, T_IDEA_THOUGHT},
                {H_XM, A_SHARE, H_OTHER_YOU_YOUR, T_IDEA_THOUGHT}
        });
    }

    private void initFiveSequences() {
        mFiveSequences = new LinkedHashMap<>();
        mFiveSequences.put(A_ABANDON, new String[][]{
                {A_ABANDON, CE_ALL, T_TECHNOLOGY, A_SAVE_RESCUE, H_US_WE},
                {A_ABANDON, T_FEAR, A_DEFEND, TS_FUTURE, A_TOGETHER},
                {A_ABANDON, T_FEAR, A_SEE, TS_FUTURE, TS_DESTINATION}
        });
        mFiveSequences.put(CE_ADAPT, new String[][]{
                {CE_ADAPT, T_IDEA_THOUGHT, A_DISCOVER, CE_SIMPLE, FD_EVOLUTION_PROGRESS_SUCCESS}
        });
        mFiveSequences.put(A_ADVANCE, new String[][]{
                {A_ADVANCE, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, A_PURSUE_ASPIRATION, H_COLLECTIVE_SHAPERS, CE_PATH}
        });
        mFiveSequences.put(TS_AFTER, new String[][]{
                {TS_AFTER, CE_IMPERFECT, H_BEING_HUMAN, A_PRESENCE, CE_CONSEQUENCE}
        });
        mFiveSequences.put(T_ANSWER, new String[][]{
                {T_ANSWER, T_QUESTION, A_DISCOVER, CE_DIFFICULT, T_TRUTH},
                {T_ANSWER, CE_NZEER, T_QUESTION, CE_POTENTIAL, T_KNOWLEDGE}
        });
        mFiveSequences.put(A_AVOID, new String[][]{
                {A_AVOID, CE_CHAOS_DISORDER, A_RECHARGE_REPAIR, CE_POTENTIAL, A_ATTACK_WAR},
                {A_AVOID, CE_BALANCE_PERFECTION, CE_STABILITY_STAY, H_BEING_HUMAN, H_INDIVIDUAL_SELF},
                {A_AVOID, CE_CHAOS_DISORDER, A_AVOID, H_COLLECTIVE_SHAPERS, T_LIE}
        });
        mFiveSequences.put(TS_BEGIN, new String[][]{
                {TS_BEGIN, A_JOURNEY, A_BREATHE_LIVE, H_XM, FD_EVOLUTION_PROGRESS_SUCCESS}
        });
        mFiveSequences.put(H_BEING_HUMAN, new String[][]{
                {H_BEING_HUMAN, H_COLLECTIVE_SHAPERS, A_TOGETHER, A_CREATE_CREATION, T_DESTINY},
                {H_BEING_HUMAN, TS_INSIDE_NOT, A_TOGETHER, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, FD_DETERIORATE_ERODE},
                {H_BEING_HUMAN, FD_LEGACY, A_ABANDON, TS_OLD, T_KNOWLEDGE},
                {H_BEING_HUMAN, FD_LEGACY, CE_HAVE, A_ABANDON, TS_NOW_PRESENT}
        });
        mFiveSequences.put(A_BREATHE_LIVE, new String[][]{
                {A_BREATHE_LIVE, TS_INSIDE_NOT, H_XM, FD_LOSE_LOSS, H_INDIVIDUAL_SELF}
        });
        mFiveSequences.put(A_CAPTURE, new String[][]{
                {A_CAPTURE, H_PORTAL, A_DEFEND, H_PORTAL, T_COURAGE}
        });
        mFiveSequences.put(A_CHANGE_MODIFY, new String[][]{
                {A_CHANGE_MODIFY, T_PERSPECTIVE, TS_BEGIN, TS_NEW, H_RESISTANCE_STRUGGLE}
        });
        mFiveSequences.put(CE_CHAOS_DISORDER, new String[][]{
                {CE_CHAOS_DISORDER, A_ATTACK_WAR, CE_CONFLICT, A_DISCOVER, CE_HARMONY_PEACE}
        });
        mFiveSequences.put(CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, new String[][]{
                {CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, A_DIE_DEATH, TS_NEW, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, TS_BEGIN}
        });
        mFiveSequences.put(CE_CLEAR, new String[][]{
                {CE_CLEAR, H_MIND, A_LIBERATE, CE_BARRIER_OBSTACLE, H_BODY_SHELL},
                {CE_CLEAR, H_OTHER_YOU_YOUR, H_MIND, T_IDEA_THOUGHT, FD_GROW},
                {CE_CLEAR, H_OTHER_YOU_YOUR, H_MIND, FD_MORE, CE_BALANCE_PERFECTION}
        });
        mFiveSequences.put(TS_CLOSE_CLEAR_ALL, new String[][]{
                {TS_CLOSE_CLEAR_ALL, T_IDEA_THOUGHT, TS_PAST, TS_NOW_PRESENT, TS_FUTURE}
        });
        mFiveSequences.put(TS_CLOSE_END_FINALITY, new String[][]{
                {TS_CLOSE_END_FINALITY, TS_OLD, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, A_CREATE_CREATION, TS_NEW}
        });
        mFiveSequences.put(H_COLLECTIVE_SHAPERS, new String[][]{
                {H_COLLECTIVE_SHAPERS, H_PORTAL, T_DATA, A_REACT, CE_CHAOS_DISORDER},
                {H_COLLECTIVE_SHAPERS, H_PORTAL, T_MESSAGE, A_DESTROY_DESTRUCTION, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE},
                {H_COLLECTIVE_SHAPERS, T_WANT_DESIRE, H_BEING_HUMAN, H_MIND, TS_FUTURE},
                {H_COLLECTIVE_SHAPERS, FD_LEAD, H_BEING_HUMAN, CE_COMPLEX, A_JOURNEY},
                {H_COLLECTIVE_SHAPERS, A_SEE, CE_COMPLEX, CE_PATH, T_DESTINY}
        });
        mFiveSequences.put(T_CONTEMPLATE, new String[][]{
                {T_CONTEMPLATE, TS_FUTURE, TS_INSIDE_NOT, H_COLLECTIVE_SHAPERS, CE_PATH},
                {T_CONTEMPLATE, A_RESTRAINT, A_DISCOVER, FD_MORE, T_COURAGE}
        });
        mFiveSequences.put(T_COURAGE, new String[][]{
                {T_COURAGE, A_ATTACK_WAR, H_COLLECTIVE_SHAPERS, H_PORTAL, A_TOGETHER}
        });
        mFiveSequences.put(A_CREATE_CREATION, new String[][]{
                {A_CREATE_CREATION, TS_NEW, TS_FUTURE, A_SEE, CE_ALL},
                {A_CREATE_CREATION, TS_NEW, H_PORTAL, CE_POTENTIAL, TS_FUTURE},
                {A_CREATE_CREATION, CE_PURE_PURITY, TS_FUTURE, TS_INSIDE_NOT, A_ATTACK_WAR},
                {A_CREATE_CREATION, CE_PURE_PURITY, TS_FUTURE, H_BEING_HUMAN, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE},
                {A_CREATE_CREATION, A_SEPERATE, CE_PATH, TS_CLOSE_END_FINALITY, A_JOURNEY}
        });
        mFiveSequences.put(A_DEFEND, new String[][]{
                {A_DEFEND, T_DESTINY, A_DEFEND, H_BEING_HUMAN, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE},
                {A_DEFEND, H_BEING_HUMAN, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, H_XM, T_MESSAGE},
                {A_DEFEND, H_BEING_HUMAN, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, H_PORTAL, T_DATA},
                {A_DEFEND, H_BEING_HUMAN, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, H_COLLECTIVE_SHAPERS, T_LIE},
                {A_DEFEND, H_BEING_HUMAN, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, H_COLLECTIVE_SHAPERS, H_PORTAL}
        });
        mFiveSequences.put(A_DESTROY_DESTRUCTION, new String[][]{
                {A_DESTROY_DESTRUCTION, T_LIE, TS_INSIDE_NOT, FD_GAIN, H_SOUL_SPIRIT},
                {A_DESTROY_DESTRUCTION, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, TS_CLOSE_END_FINALITY, CE_CONFLICT, A_ATTACK_WAR}
        });
        mFiveSequences.put(TS_DISTANCE_OUTSIDE, new String[][]{
                {TS_DISTANCE_OUTSIDE, H_INDIVIDUAL_SELF, A_AVOID, H_BEING_HUMAN, T_LIE}
        });
        mFiveSequences.put(CE_EASY, new String[][]{
                {CE_EASY, CE_PATH, TS_FUTURE, FD_FOLLOW, H_COLLECTIVE_SHAPERS}
        });
        mFiveSequences.put(A_ESCAPE, new String[][]{
                {A_ESCAPE, H_BODY_SHELL, A_JOURNEY, TS_DISTANCE_OUTSIDE, TS_NOW_PRESENT},
                {A_ESCAPE, H_BODY_SHELL, H_MIND, H_INDIVIDUAL_SELF, T_WANT_DESIRE},
                {A_ESCAPE, TS_BEFORE, A_DIE_DEATH, TS_CLOSE_END_FINALITY, CE_ALL}
        });
        mFiveSequences.put(T_FORGET, new String[][]{
                {T_FORGET, TS_PAST, A_SEE, TS_NOW_PRESENT, CE_DANGER},
                {T_FORGET, A_ATTACK_WAR, A_SEE, TS_DISTANCE_OUTSIDE, CE_HARMONY_PEACE}
        });
        mFiveSequences.put(FD_GAIN, new String[][]{
                {FD_GAIN, T_TRUTH, TS_OPEN_ACCEPT, H_BEING_HUMAN, H_SOUL_SPIRIT}
        });
        mFiveSequences.put(FD_GROW, new String[][]{
                {FD_GROW, CE_UNBOUNDED, A_CREATE_CREATION, TS_NEW, TS_FUTURE}
        });
        mFiveSequences.put(CE_HAVE, new String[][]{
                {CE_HAVE, CE_HARMONY_PEACE, A_TOGETHER, TS_CLOSE_END_FINALITY, T_MYSTERY}
        });
        mFiveSequences.put(A_HELP, new String[][]{
                {A_HELP, H_ENLIGHTENMENT, A_CAPTURE, CE_ALL, H_PORTAL},
                {A_HELP, H_RESISTANCE_STRUGGLE, A_CAPTURE, CE_ALL, H_PORTAL},
                {A_HELP, H_BEING_HUMAN, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, A_PURSUE_ASPIRATION, T_DESTINY},
                {A_HELP, FD_GAIN, T_KNOWLEDGE, TS_INSIDE_NOT, H_RESISTANCE_STRUGGLE},
                {A_HELP, H_US_WE, FD_EVOLUTION_PROGRESS_SUCCESS, CE_STRONG, FD_VICTORY},
                {A_HELP, H_US_WE, A_SAVE_RESCUE, H_US_WE, CE_ALL},
                {A_HELP, H_US_WE, A_SAVE_RESCUE, H_US_WE, A_DESTROY_DESTRUCTION}
        });
        mFiveSequences.put(A_HIDE, new String[][]{
                {A_HIDE, H_RESISTANCE_STRUGGLE, A_ADVANCE, CE_STRONG, A_TOGETHER},
                {A_HIDE, H_THEM, TS_INSIDE_NOT, CE_COMPLEX, T_INTELLIGENCE}
        });
        mFiveSequences.put(CE_IMPERFECT, new String[][]{
                {CE_IMPERFECT, T_TRUTH, TS_OPEN_ACCEPT, CE_COMPLEX, T_ANSWER},
                {CE_IMPERFECT, T_MESSAGE, TS_BEGIN, H_BEING_HUMAN, CE_CHAOS_DISORDER},
                {CE_IMPERFECT, H_XM, T_MESSAGE, H_BEING_HUMAN, CE_CHAOS_DISORDER}
        });
        mFiveSequences.put(TS_INSIDE_NOT, new String[][]{
                {TS_INSIDE_NOT, H_MIND, TS_INSIDE_NOT, H_SOUL_SPIRIT, CE_HARMONY_PEACE}
        });
        mFiveSequences.put(A_INTERRUPT, new String[][]{
                {A_INTERRUPT, H_ENLIGHTENMENT, T_TECHNOLOGY, A_CAPTURE, FD_VICTORY},
                {A_INTERRUPT, T_MESSAGE, FD_LOSE_LOSS, H_COLLECTIVE_SHAPERS, CE_POTENTIAL}
        });
        mFiveSequences.put(A_LIBERATE, new String[][]{
                {A_LIBERATE, H_PORTAL, A_LIBERATE, H_BEING_HUMAN, H_MIND},
                {A_LIBERATE, H_INDIVIDUAL_SELF, A_LIBERATE, H_BEING_HUMAN, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE}
        });
        mFiveSequences.put(A_LIVE_AGAIN, new String[][]{
                {A_LIVE_AGAIN, A_DIE_DEATH, TS_AGAIN_REPEAT, FD_EVOLUTION_PROGRESS_SUCCESS, TS_FUTURE}
        });
        mFiveSequences.put(FD_LOSE_LOSS, new String[][]{
                {FD_LOSE_LOSS, H_COLLECTIVE_SHAPERS, T_MESSAGE, FD_GAIN, CE_CHAOS_DISORDER}
        });
        mFiveSequences.put(H_MIND, new String[][]{
                {H_MIND, H_BODY_SHELL, H_SOUL_SPIRIT, CE_PURE_PURITY, H_BEING_HUMAN},
                {H_MIND, T_TECHNOLOGY, A_CAPTURE, H_BEING_HUMAN, H_SOUL_SPIRIT}
        });
        mFiveSequences.put(FD_MORE, new String[][]{
                {FD_MORE, T_DATA, FD_GAIN, H_PORTAL, A_ADVANCE}
        });
        mFiveSequences.put(T_MYSTERY, new String[][]{
                {T_MYSTERY, TS_BEFORE, CE_PURE_PURITY, T_KNOWLEDGE, TS_AFTER}
        });
        mFiveSequences.put(TS_NOW_PRESENT, new String[][]{
                {TS_NOW_PRESENT, CE_CHAOS_DISORDER, A_CREATE_CREATION, TS_FUTURE, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE},
                {TS_NOW_PRESENT, H_RESISTANCE_STRUGGLE, FD_WORTH, CE_STRONG, FD_VICTORY}
        });
        mFiveSequences.put(CE_NZEER, new String[][]{
                {CE_NZEER, A_HIDE, H_US_WE, FD_EQUAL, H_THEM}
        });
        mFiveSequences.put(TS_OLD, new String[][]{
                {TS_OLD, CE_NATURE, FD_LESS, CE_STRONG, TS_NOW_PRESENT}
        });
        mFiveSequences.put(H_OTHER_YOU_YOUR, new String[][]{
                {H_OTHER_YOU_YOUR, A_HIDE, CE_CHAOS_DISORDER, TS_INSIDE_NOT, H_BODY_SHELL}
        });
        mFiveSequences.put(TS_PAST, new String[][]{
                {TS_PAST, CE_CHAOS_DISORDER, A_CREATE_CREATION, TS_FUTURE, CE_HARMONY_PEACE},
                {TS_PAST, CE_PATH, A_CREATE_CREATION, TS_FUTURE, A_JOURNEY}
        });
        mFiveSequences.put(H_PORTAL, new String[][]{
                {H_PORTAL, A_CREATE_CREATION, CE_DANGER, A_PURSUE_ASPIRATION, CE_SAFETY},
                {H_PORTAL, CE_POTENTIAL, A_HELP, H_BEING_HUMAN, TS_FUTURE},
                {H_PORTAL, CE_BARRIER_OBSTACLE, A_DEFEND, H_BEING_HUMAN, H_COLLECTIVE_SHAPERS},
                {H_PORTAL, FD_IMPROVE, H_BEING_HUMAN, TS_FUTURE, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE}
        });
        mFiveSequences.put(A_PRESENCE, new String[][]{
                {A_PRESENCE, T_LIE, FD_EQUAL, TS_FUTURE, CE_CONSEQUENCE}
        });
        mFiveSequences.put(CE_PURE_PURITY, new String[][]{
                {CE_PURE_PURITY, H_BEING_HUMAN, FD_FAILURE, TS_NOW_PRESENT, CE_CHAOS_DISORDER}
        });
        mFiveSequences.put(A_PURSUE_ASPIRATION, new String[][]{
                {A_PURSUE_ASPIRATION, CE_PATH, TS_DISTANCE_OUTSIDE, H_COLLECTIVE_SHAPERS, T_LIE},
                {A_PURSUE_ASPIRATION, CE_CONFLICT, A_ATTACK_WAR, A_ADVANCE, CE_CHAOS_DISORDER}
        });
        mFiveSequences.put(T_QUESTION, new String[][]{
                {T_QUESTION, FD_LESS, T_FORGET, CE_ALL, T_LIE},
                {T_QUESTION, CE_POTENTIAL, T_MYSTERY, A_JOURNEY, FD_GROW}
        });
        mFiveSequences.put(A_REACT, new String[][]{
                {A_REACT, A_REBEL, T_QUESTION, H_COLLECTIVE_SHAPERS, T_LIE}
        });
        mFiveSequences.put(A_REBEL, new String[][]{
                {A_REBEL, T_IDEA_THOUGHT, FD_EVOLUTION_PROGRESS_SUCCESS, T_DESTINY, TS_NOW_PRESENT}
        });
        mFiveSequences.put(A_RECHARGE_REPAIR, new String[][]{
                {A_RECHARGE_REPAIR, TS_NOW_PRESENT, A_RECHARGE_REPAIR, H_BEING_HUMAN, H_SOUL_SPIRIT},
                {A_RECHARGE_REPAIR, H_SOUL_SPIRIT, FD_LESS, H_BEING_HUMAN, CE_HARM}
        });
        mFiveSequences.put(A_SAVE_RESCUE, new String[][]{
                {A_SAVE_RESCUE, H_BEING_HUMAN, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, A_DESTROY_DESTRUCTION, H_PORTAL}
        });
        mFiveSequences.put(A_SEARCH_SEEK, new String[][]{
                {A_SEARCH_SEEK, T_DESTINY, A_CREATE_CREATION, CE_PURE_PURITY, TS_FUTURE}
        });
        mFiveSequences.put(A_SEE, new String[][]{
                {A_SEE, T_TRUTH, A_SEE, TS_FUTURE, TS_BEGIN}
        });
        mFiveSequences.put(A_SEPERATE, new String[][]{
                {A_SEPERATE, H_MIND, H_BODY_SHELL, A_DISCOVER, H_ENLIGHTENMENT},
                {A_SEPERATE, T_TRUTH, T_LIE, H_COLLECTIVE_SHAPERS, TS_FUTURE}
        });
        mFiveSequences.put(CE_SIMPLE, new String[][]{
                {CE_SIMPLE, T_TRUTH, H_COLLECTIVE_SHAPERS, A_DESTROY_DESTRUCTION, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE},
                {CE_SIMPLE, T_TRUTH, T_FORGET, CE_EASY, FD_EVOLUTION_PROGRESS_SUCCESS},
                {CE_SIMPLE, TS_OLD, T_TRUTH, A_JOURNEY, TS_INSIDE_NOT}
        });
        mFiveSequences.put(CE_STABILITY_STAY, new String[][]{
                {CE_STABILITY_STAY, CE_STRONG, A_TOGETHER, A_DEFEND, H_RESISTANCE_STRUGGLE}
        });
        mFiveSequences.put(CE_STRONG, new String[][]{
                {CE_STRONG, A_TOGETHER, A_ATTACK_WAR, A_TOGETHER, T_DESTINY},
                {CE_STRONG, A_TOGETHER, A_ATTACK_WAR, A_TOGETHER, CE_CHAOS_DISORDER}
        });
        mFiveSequences.put(T_TECHNOLOGY, new String[][]{
                {T_TECHNOLOGY, T_INTELLIGENCE, A_SEE, CE_ALL, CE_UNBOUNDED}
        });
        mFiveSequences.put(A_TOGETHER, new String[][]{
                {A_TOGETHER, FD_EVOLUTION_PROGRESS_SUCCESS, A_RECHARGE_REPAIR, TS_NOW_PRESENT, A_JOURNEY}
        });
        mFiveSequences.put(A_USE, new String[][]{
                {A_USE, A_RESTRAINT, FD_FOLLOW, CE_EASY, CE_PATH},
                {A_USE, H_MIND, A_USE, T_COURAGE, A_CHANGE_MODIFY}
        });
        mFiveSequences.put(T_WANT_DESIRE, new String[][]{
                {T_WANT_DESIRE, TS_NEW, TS_DESTINATION, T_IGNORE, CE_CONSEQUENCE},
                {T_WANT_DESIRE, T_TRUTH, A_PURSUE_ASPIRATION, CE_DIFFICULT, CE_PATH}
        });
        mFiveSequences.put(CE_WEAK, new String[][]{
                {CE_WEAK, H_BEING_HUMAN, T_DESTINY, A_DESTROY_DESTRUCTION, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE}
        });
        mFiveSequences.put(H_XM, new String[][]{
                {H_XM, CE_PATH, TS_FUTURE, T_DESTINY, CE_HARMONY_PEACE},
                {H_XM, A_CREATE_CREATION, CE_COMPLEX, H_BEING_HUMAN, T_DESTINY},
                {H_XM, H_PORTAL, A_SHARE, H_OTHER_YOU_YOUR, T_IDEA_THOUGHT}
        });
        mFiveSequences.put(H_OTHER_YOU_YOUR, new String[][]{
                {H_OTHER_YOU_YOUR, A_HIDE, CE_CHAOS_DISORDER, TS_INSIDE_NOT, H_BODY_SHELL},
                {H_OTHER_YOU_YOUR, T_MESSAGE, CE_CLEAR, A_USE, CE_CHAOS_DISORDER}
        });
    }

    private void initGlyphPairs() {
        mGlyphPairs = new String[][]{
                {TS_BEFORE, TS_AFTER},
                {FD_GAIN, FD_LOSE_LOSS},
                {A_DESTROY_DESTRUCTION, A_CREATE_CREATION},
                {A_ADVANCE, A_RETREAT},
                {TS_OLD, TS_NEW},
                {A_BREATHE_LIVE, A_DIE_DEATH},
                {A_DEFEND, A_ATTACK_WAR},
                {A_PURSUE_ASPIRATION, T_WANT_DESIRE},
                {CE_PATH, CE_BARRIER_OBSTACLE},
                {T_COURAGE, T_FEAR},
                {CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, CE_NATURE},
                {A_LIBERATE, A_CAPTURE},
                {FD_LESS, FD_MORE},
                {H_MIND, H_SOUL_SPIRIT},
                {T_FORGET, T_IGNORE},
                {CE_PURE_PURITY, CE_IMPURE},
                {FD_EVOLUTION_PROGRESS_SUCCESS, FD_FAILURE},
                {FD_DETERIORATE_ERODE, FD_IMPROVE},
                {T_MESSAGE, T_DATA},
                {A_INTERRUPT, A_SUSTAIN},
                {H_US_WE, H_THEM},
                {H_I_ME, H_OTHER_YOU_YOUR},
                {FD_GROW, FD_CONTRACT_REDUCE},
                {TS_DISTANCE_OUTSIDE, TS_DESTINATION},
                {TS_PAST, TS_NOW_PRESENT, TS_FUTURE},
                {CE_ALL, TS_CLOSE_CLEAR_ALL, TS_OPEN_ALL}};
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

    @StringDef({H_BODY_SHELL, H_ENLIGHTENMENT, H_BEING_HUMAN, H_MIND, H_RESISTANCE_STRUGGLE, H_PORTAL,
            H_INDIVIDUAL_SELF, H_COLLECTIVE_SHAPERS, H_SOUL_SPIRIT, H_XM, A_ADVANCE, A_ATTACK_WAR,
            A_AVOID, A_BREATHE_LIVE, A_CAPTURE, A_CHANGE_MODIFY, A_CREATE_CREATION, A_DEFEND,
            A_DESTROY_DESTRUCTION, A_DIE_DEATH, A_DISCOVER, A_ESCAPE, A_HIDE, A_JOURNEY, A_LIBERATE,
            A_NOURISH, A_PURSUE_ASPIRATION, A_REACT, A_REBEL, A_RETREAT, A_RECHARGE_REPAIR, A_RESTRAINT,
            A_SAVE_RESCUE, A_SEARCH_SEEK, A_SEE, A_SEPERATE, A_SHARE, A_TOGETHER, A_USE, T_ANSWER,
            T_CONTEMPLATE, T_COURAGE, T_DATA, T_DESTINY, T_FEAR, T_FORGET, T_IDEA_THOUGHT, T_IGNORE,
            T_LIE, T_MESSAGE, T_QUESTION, T_TRUTH, T_WANT_DESIRE, FD_DETERIORATE_ERODE, FD_EQUAL,
            FD_EVOLUTION_PROGRESS_SUCCESS, FD_FAILURE, FD_FOLLOW, FD_GAIN, FD_GROW, FD_IMPROVE,
            FD_LEAD, FD_LESS, FD_LOSE_LOSS, FD_MORE, FD_CONTRACT_REDUCE, TS_AGAIN_REPEAT, TS_CLOSE_CLEAR_ALL,
            TS_DISTANCE_OUTSIDE, TS_CLOSE_END_FINALITY, TS_FUTURE, TS_NEW, TS_INSIDE_NOT, TS_NOW_PRESENT,
            TS_OLD, TS_OPEN_ACCEPT, TS_OPEN_ALL, TS_PAST, CE_ALL, CE_BALANCE_PERFECTION, CE_BARRIER_OBSTACLE,
            CE_CHAOS_DISORDER, CE_CIVILIZATION_CITY_GOVERNMENT_STRUCTURE, CE_CLEAR, CE_COMPLEX,
            CE_CONFLICT, CE_DANGER, CE_DIFFICULT, CE_EASY, CE_HARM, CE_HARMONY_PEACE, CE_HAVE, A_HELP,
            CE_IMPERFECT, CE_IMPURE, CE_NATURE, CE_PATH, CE_POTENTIAL, CE_PURE_PURITY, CE_SAFETY,
            CE_SIMPLE, CE_STABILITY_STAY, CE_STRONG, CE_WEAK, A_ABANDON, TS_BEGIN, TS_DESTINATION,
            FD_LEGACY, CE_NZEER, T_KNOWLEDGE, T_PERSPECTIVE, H_US_WE, CE_ADAPT, TS_AFTER, TS_BEFORE,
            CE_CONSEQUENCE, H_CREATIVITY, /*H_ENLIGHTENED, */H_I_ME, T_INTELLIGENCE, A_INTERRUPT,
            A_LIVE_AGAIN, T_MYSTERY, A_PRESENCE, A_CHASE, /*H_RESIST,*/ A_SUSTAIN, A_SUSTAIN_ALL,
            T_TECHNOLOGY, H_THEM, CE_UNBOUNDED, FD_VICTORY, FD_WORTH, H_OTHER_YOU_YOUR, UNKNOWN_FISH,
            UNKNOWN_BOMB, UNKNOWN_PLOUGH, UNKNOWN_SOWN})
    public @interface GlyphName {

    }

    @StringDef({C_ALL, C_HUMAN, C_ACTION, C_THOUGHT, C_FLU_DIRE, C_TIME_SPACE, C_COND_ENV, C_NEW_ADDED,
            C_NO_CAREGORY})
    public @interface Category {

    }

    private static final class GlyphDataHolder {
        private static final BaseGlyphData HOLDER = new BaseGlyphData();
    }
}
