package com.hsj86715.ingress.glyph;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hushujun on 16/5/17.
 */
public class GLYPH {
    public static final String C_ALL = "all";
    public static final String C_HUMAN = "human";
    public static final String C_ACTION = "action";
    public static final String C_THOUGHT = "thought";
    public static final String C_FLU_DIRE = "fluctuation/direction";
    public static final String C_TIME_SPACE = "time/space";
    public static final String C_COND_ENV = "condition/environment";
    private static Map<String, List<GLYPH>> sGlyphs;
    private String name;
    private int[] path;

    private GLYPH(String name, int[] path) {
        this.name = name;
        this.path = path;
    }

    private static void initPlyphs() {
        sGlyphs = new HashMap<>();
        //human
        List<GLYPH> human = new ArrayList<>();
        human.add(new GLYPH("Body", new int[]{1, 5, 2, 1}));
        human.add(new GLYPH("Enlightenment", new int[]{5, 2, 1, 5, 4, 3, 11, 9}));
        human.add(new GLYPH("Human", new int[]{9, 7, 5, 2, 10, 9}));
        human.add(new GLYPH("Mind", new int[]{9, 1, 5, 7, 9}));
        human.add(new GLYPH("Resistance Struggle", new int[]{2, 5, 4, 1, 9, 7}));
        human.add(new GLYPH("Portal", new int[]{6, 8, 7, 10, 11, 3, 2, 5, 6}));
        human.add(new GLYPH("Self", new int[]{8, 9, 11}));
        human.add(new GLYPH("Shapers", new int[]{8, 7, 5, 4, 2, 10, 11}));
        human.add(new GLYPH("Soul", new int[]{9, 1, 2, 10, 9}));
        human.add(new GLYPH("XM", new int[]{7, 5, 2, 10, 1, 7}));
        sGlyphs.put(C_HUMAN, human);
        //action
        List<GLYPH> action = new ArrayList<>();
        action.add(new GLYPH("Advance", new int[]{8, 5, 4}));
        action.add(new GLYPH("Attack War", new int[]{8, 5, 4, 2, 11}));
        action.add(new GLYPH("Avoid", new int[]{6, 4, 2, 3, 10}));
        action.add(new GLYPH("Breathe Live", new int[]{6, 5, 1, 2, 3}));
        action.add(new GLYPH("Capture", new int[]{3, 10, 1, 7, 8, 9}));
        action.add(new GLYPH("Change", new int[]{7, 1, 9, 10}));
        action.add(new GLYPH("Create", new int[]{8, 7, 1, 2, 3,}));
        action.add(new GLYPH("Defend", new int[]{6, 7, 9, 10, 3}));
        action.add(new GLYPH("Destroy", new int[]{6, 5, 1, 10, 11}));
        action.add(new GLYPH("Die", new int[]{8, 7, 1, 10, 11}));
        action.add(new GLYPH("Discover", new int[]{3, 11, 9, 8}));
        action.add(new GLYPH("Escape", new int[]{4, 3, 2, 5, 7}));
        action.add(new GLYPH("Hide", new int[]{5, 2, 3, 10, 7}));
        action.add(new GLYPH("Journey", new int[]{3, 2, 1, 5, 6, 8, 9}));
        action.add(new GLYPH("Liberate", new int[]{4, 3, 2, 1, 5, 8}));
        action.add(new GLYPH("Pursue", new int[]{6, 5, 4, 2}));
        action.add(new GLYPH("React", new int[]{2, 5, 1, 10, 11}));
        action.add(new GLYPH("Rebel", new int[]{6, 7, 1, 2, 3, 11}));
        action.add(new GLYPH("Retreat", new int[]{4, 2, 11}));
        action.add(new GLYPH("Recharge Repair", new int[]{4, 6, 5, 1, 4}));
        action.add(new GLYPH("Search", new int[]{1, 2, 5, 7, 10}));
        action.add(new GLYPH("See", new int[]{5, 4}));
        action.add(new GLYPH("Separate", new int[]{6, 5, 7, 1, 2, 10, 11}));
        action.add(new GLYPH("Share", new int[]{9, 8, 7, 10, 11}));
        action.add(new GLYPH("Together", new int[]{1, 5, 2, 1, 7, 8}));
        action.add(new GLYPH("Use", new int[]{1, 10, 3}));
        sGlyphs.put(C_ACTION, action);
        //thought
        List<GLYPH> thought = new ArrayList<>();
        thought.add(new GLYPH("Answer", new int[]{5, 2, 10, 1}));
        thought.add(new GLYPH("Contemplate", new int[]{4, 3, 11, 9, 7, 5, 1, 2}));
        thought.add(new GLYPH("Courage", new int[]{8, 5, 7, 10}));
        thought.add(new GLYPH("Data", new int[]{4, 2, 1, 7, 9}));
        thought.add(new GLYPH("Destiny", new int[]{5, 1, 2, 10, 7, 9}));
        thought.add(new GLYPH("Fear", new int[]{5, 2, 10, 3}));
        thought.add(new GLYPH("Forget", new int[]{8, 7}));
        thought.add(new GLYPH("Idea Thought", new int[]{7, 8, 6, 5, 1, 10, 11, 3, 2}));
        thought.add(new GLYPH("Ignore", new int[]{10, 11}));
        thought.add(new GLYPH("Lie", new int[]{7, 5, 1, 10, 2, 1}));
        thought.add(new GLYPH("Message", new int[]{8, 5, 1, 10, 3}));
        thought.add(new GLYPH("Question", new int[]{4, 2, 5, 7}));
        thought.add(new GLYPH("Truth", new int[]{5, 1, 10, 2, 1, 7, 5}));
        thought.add(new GLYPH("Want", new int[]{8, 7, 9, 10}));
        sGlyphs.put(C_THOUGHT, thought);
        //fluctuation/direction
        List<GLYPH> fluDire = new ArrayList<>();
        fluDire.add(new GLYPH("Deteriorate", new int[]{5, 1, 7, 8}));
        fluDire.add(new GLYPH("Equal", new int[]{7, 5, 2, 10}));
        fluDire.add(new GLYPH("Evolution Success", new int[]{4, 1, 5, 7}));
        fluDire.add(new GLYPH("Failure", new int[]{4, 1, 2, 10}));
        fluDire.add(new GLYPH("Follow", new int[]{4, 2, 3, 11}));
        fluDire.add(new GLYPH("Gain", new int[]{6, 7}));
        fluDire.add(new GLYPH("Grow", new int[]{8, 5, 7}));
        fluDire.add(new GLYPH("Improve", new int[]{3, 2, 1, 10}));
        fluDire.add(new GLYPH("Lead", new int[]{4, 6, 8, 7, 9}));
        fluDire.add(new GLYPH("Less", new int[]{5, 1, 2}));
        fluDire.add(new GLYPH("Lose", new int[]{10, 3}));
        fluDire.add(new GLYPH("More", new int[]{7, 1, 10}));
        fluDire.add(new GLYPH("Reduce", new int[]{10, 2, 11}));
        sGlyphs.put(C_FLU_DIRE, fluDire);
        //time/space
        List<GLYPH> ts = new ArrayList<>();
        ts.add(new GLYPH("Again Repeat", new int[]{8, 5, 7, 1, 2, 10}));
        ts.add(new GLYPH("Close All", new int[]{4, 6, 8, 9, 11, 3, 4, 1, 9}));
        ts.add(new GLYPH("Distance OutSide", new int[]{8, 6, 4}));
        ts.add(new GLYPH("End", new int[]{4, 1, 9, 10, 3, 4}));
        ts.add(new GLYPH("Future", new int[]{3, 2, 10, 11}));
        ts.add(new GLYPH("New", new int[]{2, 10, 11}));
        ts.add(new GLYPH("Not Inside", new int[]{5, 2, 10}));
        ts.add(new GLYPH("Now Present", new int[]{5, 7, 10, 2}));
        ts.add(new GLYPH("Old", new int[]{6, 5, 7}));
        ts.add(new GLYPH("Open Accept", new int[]{9, 7, 10, 9}));
        ts.add(new GLYPH("Open All", new int[]{9, 7, 10, 9, 11, 3, 4, 6, 8, 9}));
        ts.add(new GLYPH("Past", new int[]{6, 5, 7, 8}));
        sGlyphs.put(C_TIME_SPACE, ts);
        //condition/environment
        List<GLYPH> conEnv = new ArrayList<>();
        conEnv.add(new GLYPH("All", new int[]{4, 6, 8, 9, 11, 3, 4}));
        conEnv.add(new GLYPH("Barrier", new int[]{4, 1, 10, 11}));
        conEnv.add(new GLYPH("Chaos", new int[]{8, 6, 4, 3, 2, 1, 7, 9}));
        conEnv.add(new GLYPH("Civilization", new int[]{6, 5, 7, 10, 2, 3}));
        conEnv.add(new GLYPH("Clear", new int[]{4, 1, 9}));
        conEnv.add(new GLYPH("Complex", new int[]{2, 5, 1, 7}));
        conEnv.add(new GLYPH("Conflict", new int[]{8, 5, 7, 10, 2, 11}));
        conEnv.add(new GLYPH("Danger", new int[]{4, 5, 1, 9}));
        conEnv.add(new GLYPH("Difficult", new int[]{7, 1, 10, 2, 3}));
        conEnv.add(new GLYPH("Easy", new int[]{2, 1, 7, 9}));
        conEnv.add(new GLYPH("Harm", new int[]{1, 2, 4, 5, 1, 10, 11}));
        conEnv.add(new GLYPH("Harmony Peace", new int[]{1, 5, 4, 2, 1, 7, 9, 10, 1}));
        conEnv.add(new GLYPH("Have", new int[]{10, 1, 7, 9}));
        conEnv.add(new GLYPH("Imperfect", new int[]{2, 1, 7, 5, 1}));
        conEnv.add(new GLYPH("Impure", new int[]{1, 5, 7, 1, 9}));
        conEnv.add(new GLYPH("Nature", new int[]{8, 7, 5, 2, 10, 11}));
        conEnv.add(new GLYPH("Path", new int[]{4, 1, 7, 8}));
        conEnv.add(new GLYPH("Perfection Balance", new int[]{4, 1, 7, 8, 9, 11, 10, 1}));
        conEnv.add(new GLYPH("Potential", new int[]{4, 1, 10, 11, 3}));
        conEnv.add(new GLYPH("Pure", new int[]{4, 1, 10, 2, 1}));
        conEnv.add(new GLYPH("Safety", new int[]{8, 5, 2, 11}));
        conEnv.add(new GLYPH("Simple", new int[]{7, 10}));
        conEnv.add(new GLYPH("Stability Stay", new int[]{8, 7, 10, 11}));
        conEnv.add(new GLYPH("Strong", new int[]{5, 7, 10, 2, 5}));
        conEnv.add(new GLYPH("Weak", new int[]{6, 5, 2, 10}));
        sGlyphs.put(C_COND_ENV, conEnv);
    }

    public static List<GLYPH> getGlyphs(String category) {
        if (TextUtils.isEmpty(category)) {
            return new ArrayList<>();
        } else {
            if (sGlyphs == null) {
                initPlyphs();
            }
            if (category.equals(C_ALL)) {
                List<GLYPH> all = new ArrayList<>();
                all.addAll(sGlyphs.get(C_HUMAN));
                all.addAll(sGlyphs.get(C_ACTION));
                all.addAll(sGlyphs.get(C_THOUGHT));
                all.addAll(sGlyphs.get(C_FLU_DIRE));
                all.addAll(sGlyphs.get(C_TIME_SPACE));
                all.addAll(sGlyphs.get(C_COND_ENV));
                Collections.sort(all, new Comparator<GLYPH>() {
                    @Override
                    public int compare(GLYPH o1, GLYPH o2) {
                        return o1.name.compareTo(o2.name);
                    }
                });
                return all;
            } else {
                return sGlyphs.get(category);
            }
        }
    }

    public String getName() {
        return name;
    }

    public int[] getPath() {
        return path;
    }
}
