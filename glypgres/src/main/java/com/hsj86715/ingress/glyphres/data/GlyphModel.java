package com.hsj86715.ingress.glyphres.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hsj86715.ingress.glyphres.data.DataOpenHelper.GlyphBaseColumn;
import com.hsj86715.ingress.glyphres.data.DataOpenHelper.GlyphPairsColumn;
import com.hsj86715.ingress.glyphres.data.DataOpenHelper.NameColumn;
import com.hsj86715.ingress.glyphres.data.DataOpenHelper.SequenceColumn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hushujun on 2017/11/21.
 */

public class GlyphModel {
    static DataOpenHelper sHelper;
    static Map<String, GlyphBean> sGlyphCaches;

    private static synchronized void initHelper(Context context) {
        if (sHelper == null) {
            sHelper = new DataOpenHelper(context);
        }
    }

    private static String[] getGlyphBaseColumns() {
        return new String[]{GlyphBaseColumn._ID, GlyphBaseColumn.PATH, NameColumn.NAME, NameColumn.ALIAS,
                NameColumn.ALIAS1, NameColumn.ALIAS2, NameColumn.ALIAS3};
    }

    private static String[] getGlyphPairsColumns() {
        return new String[]{GlyphPairsColumn.PAIR_WITH, GlyphPairsColumn.PAIR_WITH1,
                GlyphPairsColumn.PAIR_WITH2, GlyphPairsColumn.PAIR_WITH3};
    }

    private static String[] getGlyphSequencesColumns() {
        return new String[]{SequenceColumn.HEAD, SequenceColumn.SEQUENCE_FIRST,
                SequenceColumn.SEQUENCE_SECOND, SequenceColumn.SEQUENCE_THIRD,
                SequenceColumn.SEQUENCE_FOURTH, SequenceColumn.SEQUENCE_FIFTH};
    }

    public static void clearCache() {
        if (sGlyphCaches != null) {
            sGlyphCaches.clear();
            sGlyphCaches = null;
        }
    }

    public static List<GlyphBean> getGlyphBase(Context context, String category) {
        initHelper(context);
        SQLiteDatabase db = sHelper.getReadableDatabase();
        String selection = null;
        if (!TextUtils.isEmpty(category)) {
            selection = DataOpenHelper.GlyphBaseColumn.CATEGORY + " = " + category;
        }
        Cursor cursor = db.query(DataOpenHelper.TABLE_GLYPH, getGlyphBaseColumns(), selection,
                null, null, null, NameColumn.NAME);
        if (cursor != null && cursor.getCount() > 0) {
            List<GlyphBean> glyphs = new ArrayList<>();
            while (cursor.moveToNext()) {
                glyphs.add(parcelGlyphBean(cursor));
            }
            cursor.close();
            return glyphs;
        }
        return null;
    }

    public static List<GlyphBean[]> getGlyphPairs(Context context) {
        return getGlyphPairs(context, null);
    }

    private static List<GlyphBean[]> getGlyphPairs(Context context, String name) {
        initHelper(context);
        SQLiteDatabase db = sHelper.getReadableDatabase();
        String selection = null;
        if (!TextUtils.isEmpty(name)) {
            selection = GlyphPairsColumn.PAIR_WITH + " = " + name + " or " + GlyphPairsColumn.PAIR_WITH1
                    + " = " + name + " or " + GlyphPairsColumn.PAIR_WITH2 + " = " + name + " or "
                    + GlyphPairsColumn.PAIR_WITH3 + " = " + name;
        }
        Cursor cursor = db.query(DataOpenHelper.TABLE_PAIRS, getGlyphPairsColumns(), selection,
                null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            List<GlyphBean[]> pairs = new ArrayList<>();
            if (sGlyphCaches == null) {
                sGlyphCaches = new HashMap<>();
            }
            while (cursor.moveToNext()) {
                String pair = cursor.getString(cursor.getColumnIndex(GlyphPairsColumn.PAIR_WITH));
                String pair1 = cursor.getString(cursor.getColumnIndex(GlyphPairsColumn.PAIR_WITH1));
                String pair2 = cursor.getString(cursor.getColumnIndex(GlyphPairsColumn.PAIR_WITH2));
                String pair3 = cursor.getString(cursor.getColumnIndex(GlyphPairsColumn.PAIR_WITH3));
                pairs.add(parcelGlyph(db, new String[]{pair, pair1, pair2, pair3}));
            }
            cursor.close();
            return pairs;
        }
        return null;
    }


    private static GlyphBean parcelGlyphBean(Cursor cursor) {
        GlyphBean bean = new GlyphBean();
        bean.setId(cursor.getInt(cursor.getColumnIndex(GlyphBaseColumn._ID)));
        bean.setName(cursor.getString(cursor.getColumnIndex(NameColumn.NAME)));
        bean.setAlias(cursor.getString(cursor.getColumnIndex(NameColumn.ALIAS)));
        bean.setAlias1(cursor.getString(cursor.getColumnIndex(NameColumn.ALIAS1)));
        bean.setAlias2(cursor.getString(cursor.getColumnIndex(NameColumn.ALIAS2)));
        bean.setAlias3(cursor.getString(cursor.getColumnIndex(NameColumn.ALIAS3)));
        String path = cursor.getString(cursor.getColumnIndex(DataOpenHelper.GlyphBaseColumn.PATH));
        bean.setPath(path.split(","));
        return bean;
    }

    private static GlyphBean[] parcelGlyph(SQLiteDatabase db, String[] names) {
        int length = 2;
        if (!TextUtils.isEmpty(names[2])) {
            length++;
        }
        if (!TextUtils.isEmpty(names[3])) {
            length++;
        }
        GlyphBean[] pairs = new GlyphBean[length];
        for (int i = 0; i < length; i++) {
            pairs[i] = findGlyphByName(db, names[i]);
        }
        return pairs;
    }

    public static Multimap<GlyphBean, GlyphBean[]> getGlyphSequences(Context context, int length) {
        initHelper(context);
        SQLiteDatabase db = sHelper.getReadableDatabase();
        Cursor cursor = db.query(DataOpenHelper.TABLE_SEQUENCES, getGlyphSequencesColumns(),
                SequenceColumn.LENGTH + " = " + length, null, null,
                null, null);
        if (cursor != null && cursor.getCount() > 0) {
            Multimap<GlyphBean, GlyphBean[]> hackSequences = ArrayListMultimap.create();
            if (sGlyphCaches == null) {
                sGlyphCaches = new HashMap<>();
            }
            while (cursor.moveToNext()) {
                String head, first, second, third = null, fourth = null, fifth = null;
                switch (length) {
                    case 5:
                        fifth = cursor.getString(cursor.getColumnIndex(SequenceColumn.SEQUENCE_FIFTH));
                    case 4:
                        fourth = cursor.getString(cursor.getColumnIndex(SequenceColumn.SEQUENCE_FOURTH));
                    case 3:
                        third = cursor.getString(cursor.getColumnIndex(SequenceColumn.SEQUENCE_THIRD));
                    case 2:
                    default:
                        second = cursor.getString(cursor.getColumnIndex(SequenceColumn.SEQUENCE_SECOND));
                        first = cursor.getString(cursor.getColumnIndex(SequenceColumn.SEQUENCE_FIRST));
                        head = cursor.getString(cursor.getColumnIndex(SequenceColumn.HEAD));
                        parcelSequences(db, hackSequences, length, new String[]{head, first, second, third, fourth, fifth});
                }
            }
            return hackSequences;
        }
        return null;
    }


    private static void parcelSequences(SQLiteDatabase db, Multimap<GlyphBean, GlyphBean[]> hackSequences,
                                        int length, String[] names) {
        GlyphBean head;
        if (sGlyphCaches.containsKey(names[0])) {
            head = sGlyphCaches.get(names[0]);
        } else {
            head = findGlyphByName(db, names[0]);
        }
        if (head == null) {
            return;
        }
        GlyphBean[] sequences = new GlyphBean[length];
        for (int i = 1; i < length + 1; i++) {
            GlyphBean sequence;
            if (sGlyphCaches.containsKey(names[i])) {
                sequence = sGlyphCaches.get(names[i]);
            } else {
                sequence = findGlyphByName(db, names[i]);
            }
            sequences[i - 1] = sequence;
        }
        hackSequences.put(head, sequences);
    }

    private static GlyphBean findGlyphByName(SQLiteDatabase db, String name) {
        Cursor cursor = db.query(DataOpenHelper.TABLE_GLYPH, getGlyphBaseColumns(), NameColumn.NAME
                + " = " + name + " or " + NameColumn.ALIAS + " = " + name + " or " + NameColumn.ALIAS1
                + " = " + name + " or " + NameColumn.ALIAS2 + " = " + name + " or " + NameColumn.ALIAS3
                + " = " + name, null, null, null, null);
        GlyphBean bean = null;
        if (cursor != null && cursor.moveToFirst()) {
            bean = parcelGlyphBean(cursor);
            bean.setName(name);
            sGlyphCaches.put(name, bean);
            cursor.close();
        }
        return bean;
    }

    public static List<GlyphBean[]> searchGlyphByName(Context context, String name) {
        initHelper(context);
        SQLiteDatabase db = sHelper.getReadableDatabase();
        //first, search base Glyph
        GlyphBean bean = findGlyphByName(db, name);
        if (bean == null) {
            return null;
        }
        List<GlyphBean[]> result = new ArrayList<>();
        result.add(new GlyphBean[]{bean});
        //second, search glyph pairs
        result.addAll(getGlyphPairs(context, name));
        //third, search glyph sequences
        result.addAll(findSequencesByName(db, name));
        return result;
    }

    private static List<GlyphBean[]> findSequencesByName(SQLiteDatabase db, String name) {
        Cursor cursor = db.query(DataOpenHelper.TABLE_SEQUENCES, getGlyphSequencesColumns(),
                SequenceColumn.SEQUENCE_FIRST + " = " + name + " or " + SequenceColumn.SEQUENCE_SECOND
                        + " = " + name + " or " + SequenceColumn.SEQUENCE_THIRD + " = " + name + " or "
                        + SequenceColumn.SEQUENCE_FOURTH + " = " + name + " or " + SequenceColumn.SEQUENCE_FIFTH
                        + " = " + name, null, null, null, SequenceColumn.LENGTH);
        if (cursor != null && cursor.getCount() > 0) {
            List<GlyphBean[]> sequences = new ArrayList<>();
            while (cursor.moveToNext()) {
                String first = cursor.getString(cursor.getColumnIndex(SequenceColumn.SEQUENCE_FIRST));
                String second = cursor.getString(cursor.getColumnIndex(SequenceColumn.SEQUENCE_SECOND));
                String third = cursor.getString(cursor.getColumnIndex(SequenceColumn.SEQUENCE_THIRD));
                String fourth = cursor.getString(cursor.getColumnIndex(SequenceColumn.SEQUENCE_FOURTH));
                String fifth = cursor.getString(cursor.getColumnIndex(SequenceColumn.SEQUENCE_FIFTH));
                sequences.add(parcelSequences(db, new String[]{first, second, third, fourth, fifth}));
            }
            return sequences;
        }
        return null;
    }

    private static GlyphBean[] parcelSequences(SQLiteDatabase db, String[] names) {
        int length = 2;
        if (!TextUtils.isEmpty(names[2])) {
            length++;
        }
        if (!TextUtils.isEmpty(names[3])) {
            length++;
        }
        if (!TextUtils.isEmpty(names[4])) {
            length++;
        }
        GlyphBean[] sequences = new GlyphBean[length];
        for (int i = 0; i < length; i++) {
            GlyphBean sequence;
            if (sGlyphCaches.containsKey(names[i])) {
                sequence = sGlyphCaches.get(names[i]);
            } else {
                sequence = findGlyphByName(db, names[i]);
            }
            sequences[i] = sequence;
        }
        return sequences;
    }
}
