package com.hsj86715.ingress.glyph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hushujun on 16/5/17.
 */
public class GLYPH {
    private String name;
    private int[] positionPath;

    private static List<GLYPH> sGlyphs;

    private GLYPH(String name, int[] positionPath) {
        this.name = name;
        this.positionPath = positionPath;
    }

    public static List<GLYPH> getGlyphs() {
        if (sGlyphs == null || sGlyphs.isEmpty()) {
            initGlyphs();
            return sGlyphs;
        } else {
            return sGlyphs;
        }
    }

    private static void initGlyphs() {
        sGlyphs = new ArrayList<>();
        //human
        sGlyphs.add(new GLYPH("Mind", new int[]{9, 1, 5, 7, 9}));
        sGlyphs.add(new GLYPH("Soul", new int[]{9, 1, 2, 10, 9}));
        sGlyphs.add(new GLYPH("Body", new int[]{1, 5, 2, 1}));
        sGlyphs.add(new GLYPH("Human", new int[]{9, 7, 5, 2, 10, 9}));
        sGlyphs.add(new GLYPH("Enlightenment", new int[]{5, 2, 1, 5, 4, 3, 11, 9}));
        sGlyphs.add(new GLYPH("Resistance Struggle", new int[]{2, 5, 4, 1, 9, 7}));
        sGlyphs.add(new GLYPH("Portal", new int[]{6, 8, 7, 10, 11, 3, 2, 5, 6}));
        sGlyphs.add(new GLYPH("Shapers", new int[]{8, 7, 5, 4, 2, 10, 11}));
        sGlyphs.add(new GLYPH("XM", new int[]{7, 5, 2, 10, 1, 7}));
        sGlyphs.add(new GLYPH("Self", new int[]{8, 9, 11}));
        //action
        sGlyphs.add(new GLYPH("Attack War", new int[]{8, 5, 4, 2, 11}));
        sGlyphs.add(new GLYPH("Defend", new int[]{6, 7, 9, 10, 3}));
        sGlyphs.add(new GLYPH("Breathe Live", new int[]{6, 5, 1, 2, 3}));
        sGlyphs.add(new GLYPH("Die", new int[]{8, 7, 1, 10, 11}));
        sGlyphs.add(new GLYPH("Advance", new int[]{8, 5, 4}));
        sGlyphs.add(new GLYPH("Retreat", new int[]{4, 2, 11}));
        sGlyphs.add(new GLYPH("Create", new int[]{8, 7, 1, 2, 3,}));
        sGlyphs.add(new GLYPH("Destroy", new int[]{6, 5, 1, 10, 11}));
        sGlyphs.add(new GLYPH("Capture", new int[]{3, 10, 1, 7, 8, 9}));
        sGlyphs.add(new GLYPH("Liberate", new int[]{4, 3, 2, 1, 5, 8}));
        sGlyphs.add(new GLYPH("Search", new int[]{1, 2, 5, 7, 10}));
        sGlyphs.add(new GLYPH("Hide", new int[]{5, 2, 3, 10, 7}));
        sGlyphs.add(new GLYPH("Discover", new int[]{3, 11, 9, 8}));
        sGlyphs.add(new GLYPH("Journey", new int[]{3, 2, 1, 5, 6, 8, 9}));
        sGlyphs.add(new GLYPH("Escape", new int[]{4, 3, 2, 5, 7}));
        sGlyphs.add(new GLYPH("React", new int[]{2, 5, 1, 10, 11}));
        sGlyphs.add(new GLYPH("Change", new int[]{7, 1, 9, 10}));
        sGlyphs.add(new GLYPH("See", new int[]{5, 4}));
        sGlyphs.add(new GLYPH("Rebel", new int[]{6, 7, 1, 2, 3, 11}));
        sGlyphs.add(new GLYPH("Recharge Repair", new int[]{4, 6, 5, 1, 4}));
        sGlyphs.add(new GLYPH("Separate", new int[]{6, 5, 7, 1, 2, 10, 11}));
        sGlyphs.add(new GLYPH("Pursue", new int[]{6, 5, 4, 2}));
        sGlyphs.add(new GLYPH("Together", new int[]{1, 5, 2, 1, 7, 8}));
        sGlyphs.add(new GLYPH("Use", new int[]{1, 10, 3}));
        sGlyphs.add(new GLYPH("Avoid", new int[]{6, 4, 2, 3, 10}));
        sGlyphs.add(new GLYPH("Share", new int[]{9, 8, 7, 10, 11}));
        //thought
        sGlyphs.add(new GLYPH("Courage", new int[]{8, 5, 7, 10}));
        sGlyphs.add(new GLYPH("Fear", new int[]{5, 2, 10, 3}));
        sGlyphs.add(new GLYPH("Forget", new int[]{8, 7}));
        sGlyphs.add(new GLYPH("Ignore", new int[]{10, 11}));
        sGlyphs.add(new GLYPH("Lie", new int[]{7, 5, 1, 10, 2, 1}));
        sGlyphs.add(new GLYPH("Truth", new int[]{5, 1, 10, 2, 1, 7, 5}));
        sGlyphs.add(new GLYPH("Idea Thought", new int[]{7, 8, 6, 5, 1, 10, 11, 3, 2}));
        sGlyphs.add(new GLYPH("Contemplate", new int[]{4, 3, 11, 9, 7, 5, 1, 2}));
        sGlyphs.add(new GLYPH("Want", new int[]{8, 7, 9, 10}));
        sGlyphs.add(new GLYPH("Question", new int[]{4, 2, 5, 7}));
        sGlyphs.add(new GLYPH("Answer", new int[]{5, 2, 10, 1}));
        sGlyphs.add(new GLYPH("Data", new int[]{4, 2, 1, 7, 9}));
        sGlyphs.add(new GLYPH("Message", new int[]{8, 5, 1, 10, 3}));
        sGlyphs.add(new GLYPH("Destiny", new int[]{5, 1, 2, 10, 7, 9}));
        //fluctuation/direction
        sGlyphs.add(new GLYPH("Lead", new int[]{4, 6, 8, 7, 9}));
        sGlyphs.add(new GLYPH("Follow", new int[]{4, 2, 3, 11}));
        sGlyphs.add(new GLYPH("Gain", new int[]{6, 7}));
        sGlyphs.add(new GLYPH("Lose", new int[]{10, 3}));
        sGlyphs.add(new GLYPH("Improve", new int[]{3, 2, 1, 10}));
        sGlyphs.add(new GLYPH("Deteriorate", new int[]{5, 1, 7, 8}));
        sGlyphs.add(new GLYPH("Grow", new int[]{8, 5, 7}));
        sGlyphs.add(new GLYPH("Reduce", new int[]{10, 2, 11}));
        sGlyphs.add(new GLYPH("More", new int[]{7, 1, 10}));
        sGlyphs.add(new GLYPH("Less", new int[]{5, 1, 2}));
        sGlyphs.add(new GLYPH("Evolution Success", new int[]{4, 1, 5, 7}));
        sGlyphs.add(new GLYPH("Failure", new int[]{4, 1, 2, 10}));
        sGlyphs.add(new GLYPH("Equal", new int[]{7, 5, 2, 10}));
        //time/space
        sGlyphs.add(new GLYPH("Past", new int[]{6, 5, 7, 8}));
        sGlyphs.add(new GLYPH("Now Present", new int[]{5, 7, 10, 2}));
        sGlyphs.add(new GLYPH("Future", new int[]{3, 2, 10, 11}));
        sGlyphs.add(new GLYPH("Old", new int[]{6, 5, 7}));
        sGlyphs.add(new GLYPH("New", new int[]{2, 10, 11}));
        sGlyphs.add(new GLYPH("Again Repeat", new int[]{8, 5, 7, 1, 2, 10}));
        sGlyphs.add(new GLYPH("End", new int[]{4, 1, 9, 10, 3, 4}));
        sGlyphs.add(new GLYPH("Distance OutSide", new int[]{8, 6, 4}));
        sGlyphs.add(new GLYPH("Not Inside", new int[]{5, 2, 10}));
        sGlyphs.add(new GLYPH("Close All", new int[]{4, 6, 8, 9, 11, 3, 4, 1, 9}));
        sGlyphs.add(new GLYPH("Open Accept", new int[]{9, 7, 10, 9}));
        sGlyphs.add(new GLYPH("Open All", new int[]{9, 7, 10, 9, 11, 3, 4, 6, 8, 9}));
        //condition/environment
        sGlyphs.add(new GLYPH("Harmony Peace", new int[]{1, 5, 4, 2, 1, 7, 9, 10, 1}));
        sGlyphs.add(new GLYPH("Harm", new int[]{1, 2, 4, 5, 1, 10, 11}));
        sGlyphs.add(new GLYPH("Chaos", new int[]{8, 6, 4, 3, 2, 1, 7, 9}));
        sGlyphs.add(new GLYPH("Stability Stay", new int[]{8, 7, 10, 11}));
        sGlyphs.add(new GLYPH("Simple", new int[]{7, 10}));
        sGlyphs.add(new GLYPH("Complex", new int[]{2, 5, 1, 7}));
        sGlyphs.add(new GLYPH("Perfection Balance", new int[]{4, 1, 7, 8, 9, 11, 10, 1}));
        sGlyphs.add(new GLYPH("Imperfect", new int[]{2, 1, 7, 5, 1}));
        sGlyphs.add(new GLYPH("Civilization", new int[]{6, 5, 7, 10, 2, 3}));
        sGlyphs.add(new GLYPH("Nature", new int[]{8, 7, 5, 2, 10, 11}));
        sGlyphs.add(new GLYPH("Barrier", new int[]{4, 1, 10, 11}));
        sGlyphs.add(new GLYPH("Path", new int[]{4, 1, 7, 8}));
        sGlyphs.add(new GLYPH("Easy", new int[]{2, 1, 7, 9}));
        sGlyphs.add(new GLYPH("Difficult", new int[]{7, 1, 10, 2, 3}));
        sGlyphs.add(new GLYPH("Pure", new int[]{4, 1, 10, 2, 1}));
        sGlyphs.add(new GLYPH("Impure", new int[]{1, 5, 7, 1, 9}));
        sGlyphs.add(new GLYPH("Strong", new int[]{5, 7, 10, 2, 5}));
        sGlyphs.add(new GLYPH("Weak", new int[]{6, 5, 2, 10}));
        sGlyphs.add(new GLYPH("Safety", new int[]{8, 5, 2, 11}));
        sGlyphs.add(new GLYPH("Danger", new int[]{4, 5, 1, 9}));
        sGlyphs.add(new GLYPH("Have", new int[]{10, 1, 7, 9}));
        sGlyphs.add(new GLYPH("Potential", new int[]{4, 1, 10, 11, 3}));
        sGlyphs.add(new GLYPH("All", new int[]{4, 6, 8, 9, 11, 3, 4}));
        sGlyphs.add(new GLYPH("Conflict", new int[]{8, 5, 7, 10, 2, 11}));
        sGlyphs.add(new GLYPH("Clear", new int[]{4, 1, 9}));
    }

    public String getName() {
        return name;
    }

    public int[] getPositionPath() {
        return positionPath;
    }
}
