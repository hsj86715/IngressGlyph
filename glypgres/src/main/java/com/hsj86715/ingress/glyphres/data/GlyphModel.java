package com.hsj86715.ingress.glyphres.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.hsj86715.ingress.glyphres.data.DataOpenHelper.CategoryColumn;
import com.hsj86715.ingress.glyphres.data.DataOpenHelper.GlyphBaseColumn;
import com.hsj86715.ingress.glyphres.data.DataOpenHelper.GlyphInfoColumn;
import com.hsj86715.ingress.glyphres.data.DataOpenHelper.HackListColumn;
import com.hsj86715.ingress.glyphres.data.DataOpenHelper.NameColumn;
import com.hsj86715.ingress.glyphres.data.DataOpenHelper.PairsColumn;
import com.hsj86715.ingress.glyphres.data.DataOpenHelper.PathColumn;
import com.hsj86715.ingress.glyphres.tools.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.com.farmcode.utility.tools.Logger;

/**
 * Created by hushujun on 2017/11/21.
 */

public class GlyphModel {
    private static GlyphModel sInstance;
    private DataOpenHelper mHelper;

    private GlyphModel(Context context) {
        mHelper = new DataOpenHelper(context.getApplicationContext());
    }

    public static GlyphModel getInstance(Context context) {
        synchronized (GlyphModel.class) {
            if (sInstance == null) {
                sInstance = new GlyphModel(context);
            }
            return sInstance;
        }
    }

    public void initBaseData() {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        Cursor cursor = query(database, DataOpenHelper.TABLE_BASE, new String[]{GlyphBaseColumn._ID}, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                return;
            }
        }
        Logger.w("initBaseData START");
        BaseGlyphData glyphData = new BaseGlyphData();

        //insert category/name/glyph base
        Logger.i("initBaseData insert category/name/glyph base");
        String[] categories = {Constants.C_ACTION, Constants.C_COND_ENV, Constants.C_FLU_DIRE, Constants.C_HUMAN,
                Constants.C_THOUGHT, Constants.C_TIME_SPACE};
        for (String cat : categories) {
            ContentValues values = new ContentValues();
            values.put(CategoryColumn.CATEGORY, cat);
            long catId = database.insert(DataOpenHelper.TABLE_CATEGORY, null, values);

            Map<String, int[]> catGlyphs = glyphData.getGlyphByCategory(cat);
            Set<String> keySet = catGlyphs.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String name = iterator.next();
                ContentValues nameValue = new ContentValues();
                String[] names;
                if (name.contains("/")) {
                    names = name.split("/");
                } else {
                    names = new String[]{name};
                }
                String[] columns = {NameColumn.NAME, NameColumn.ALIAS, NameColumn.ALIAS1, NameColumn.ALIAS2, NameColumn.ALIAS3};
                for (int i = 0; i < names.length; i++) {
                    nameValue.put(columns[i], names[i]);
                }
                long nameId = database.insert(DataOpenHelper.TABLE_NAMES, null, nameValue);

                int[] path = catGlyphs.get(name);
                ContentValues pathValue = new ContentValues();
                pathValue.put(PathColumn.PATH, Utils.arrayToString(path));
                long pathId = database.insert(DataOpenHelper.TABLE_PATH, null, pathValue);

                ContentValues glyphValue = new ContentValues();
                glyphValue.put(GlyphBaseColumn.CATEGORY_ID, catId);
                glyphValue.put(GlyphBaseColumn.NAME_ID, nameId);
                glyphValue.put(GlyphBaseColumn.PATH_ID, pathId);
                database.insert(DataOpenHelper.TABLE_BASE, null, glyphValue);
            }
        }
        Runtime.getRuntime().gc();

        Map<String, Long> nameIds = new HashMap<>();

        //insert pairs
        Logger.i("initBaseData insert pairs");
        String[][] pairs = glyphData.getGlyphPairs();
        for (String[] pair : pairs) {
            ContentValues values = new ContentValues();
            long glyphId = -1;
            String[] columns = {PairsColumn.WITH, PairsColumn.WITH1, PairsColumn.WITH2, PairsColumn.WITH3};
            for (int i = 0; i < pair.length; i++) {
                if (nameIds.containsKey(pair[i])) {
                    glyphId = nameIds.get(pair[i]);
                } else {
                    glyphId = queryGlyphIdByName(database, pair[i]);
                    nameIds.put(pair[i], glyphId);
                }
                values.put(columns[i], glyphId);
            }
            values.put(PairsColumn.LENGTH, pair.length);
            database.insert(DataOpenHelper.TABLE_PAIRS, null, values);
        }
        Runtime.getRuntime().gc();

        //insert hack sequences
        Logger.w("initBaseData insert hack sequences");
        for (int i = 2; i <= 5; i++) {
            Map<String, String[][]> lengthSequences;
            if (i == 2) {
                lengthSequences = glyphData.getTwoSequences();
            } else if (i == 3) {
                lengthSequences = glyphData.getThreeSequences();
            } else if (i == 4) {
                lengthSequences = glyphData.getFourSequences();
            } else {
                lengthSequences = glyphData.getFiveSequences();
            }
            Logger.i("initBaseData insert hack sequences: " + i);
            Set<String> heads = lengthSequences.keySet();
            Iterator<String> headIt = heads.iterator();
            while (headIt.hasNext()) {
                String headName = headIt.next();
                long headId = -1;
                if (nameIds.containsKey(headName)) {
                    headId = nameIds.get(headName);
                } else {
                    headId = queryGlyphIdByName(database, headName);
                    nameIds.put(headName, headId);
                }
                String[] columns = {HackListColumn.FIRST, HackListColumn.SECOND, HackListColumn.THIRD,
                        HackListColumn.FOURTH, HackListColumn.FIFTH};
                String[][] sequences = lengthSequences.get(headName);
                for (String[] sequence : sequences) {
                    ContentValues values = new ContentValues();
                    values.put(HackListColumn.LENGTH, i);
                    values.put(HackListColumn.HEAD, headId);
                    for (int j = 0; j < sequence.length; j++) {
                        long sequenceId = -1;
                        if (nameIds.containsKey(sequence[j])) {
                            sequenceId = nameIds.get(sequence[j]);
                        } else {
                            sequenceId = queryGlyphIdByName(database, sequence[j]);
                            nameIds.put(sequence[j], sequenceId);
                        }
                        values.put(columns[j], sequenceId);
                    }
                    database.insert(DataOpenHelper.TABLE_LIST, null, values);
                }
            }
            Runtime.getRuntime().gc();
        }
        Logger.w("initBaseData END");
    }

    private long queryGlyphIdByName(SQLiteDatabase database, String nameStr) {
        String name;
        if (nameStr.contains("/")) {
            name = nameStr.split("/")[0];
        } else {
            name = nameStr;
        }
        Cursor cursor = query(database, DataOpenHelper.TABLE_NAMES, new String[]{NameColumn._ID},
                NameColumn.NAME + "=?", new String[]{name});
        if (cursor != null) {
            long nameId = -1;
            if (cursor.moveToFirst()) {
                nameId = cursor.getLong(0);
            }
            cursor.close();
            if (nameId >= 0) {
                cursor = query(database, DataOpenHelper.TABLE_BASE, new String[]{GlyphBaseColumn._ID},
                        GlyphBaseColumn.NAME_ID + "=?", new String[]{String.valueOf(nameId)});
                long glyphId = -1;
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        glyphId = cursor.getLong(0);
                    }
                    cursor.close();
                }
                return glyphId;
            }
        }
        return -1;
    }

    private Cursor query(SQLiteDatabase database, String table, String selection, String[] selectionArgs) {
        return query(database, table, null, selection, selectionArgs);
    }

    private Cursor query(SQLiteDatabase database, String table, String[] columns, String selection, String[] selectionArgs) {
        return database.query(table, columns, selection, selectionArgs, null, null, null);
    }

    public List<GlyphInfo> getGlyphInfoByCategory(@Constants.Category String category) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        List<GlyphInfo> categoryGlyphs = null;
        Cursor cursor = null;
        if (TextUtils.equals(Constants.C_ALL, category)) {
            cursor = query(database, DataOpenHelper.TABLE_GLYPH_INFO, null, null);
        } else {
            cursor = query(database, DataOpenHelper.TABLE_GLYPH_INFO, GlyphInfoColumn.CATEGORY + "=?",
                    new String[]{category});
        }
        if (cursor != null) {
            categoryGlyphs = new ArrayList<>();
            while (cursor.moveToNext()) {
                categoryGlyphs.add(packageGlyph(cursor));
            }
            cursor.close();
        }
        return categoryGlyphs;
    }

    private GlyphInfo packageGlyph(Cursor cursor) {
        GlyphInfo info = new GlyphInfo();
        info.setId(cursor.getInt(cursor.getColumnIndex(GlyphInfoColumn.ID)));
        info.setCategory(cursor.getString(cursor.getColumnIndex(GlyphInfoColumn.CATEGORY)));
        info.setName(cursor.getString(cursor.getColumnIndex(GlyphInfoColumn.NAME)));
        info.setNameId(cursor.getInt(cursor.getColumnIndex(GlyphInfoColumn.NAME_ID)));
        info.setPath(Utils.stringToArray(cursor.getString(cursor.getColumnIndex(GlyphInfoColumn.PATH))));
        info.setPathId(cursor.getInt(cursor.getColumnIndex(GlyphInfoColumn.PATH_ID)));
        info.setLearnCount(cursor.getInt(cursor.getColumnIndex(GlyphInfoColumn.LEARN_COUNT)));
        info.setPractiseCount(cursor.getInt(cursor.getColumnIndex(GlyphInfoColumn.PRACTISE_COUNT)));
        info.setPractiseCorrect(cursor.getInt(cursor.getColumnIndex(GlyphInfoColumn.PRACTISE_CORRECT)));
        return info;
    }

    private Map<Integer, GlyphInfo> getGlyphMap(SQLiteDatabase database) {
        Map<Integer, GlyphInfo> glyphInfoMap = null;
        Cursor cursor = query(database, DataOpenHelper.TABLE_GLYPH_INFO, null, null);
        if (cursor != null) {
            glyphInfoMap = new HashMap<>();
            while (cursor.moveToNext()) {
                GlyphInfo info = packageGlyph(cursor);
                glyphInfoMap.put(info.getId(), info);
            }
            cursor.close();
        }
        return glyphInfoMap;
    }

    public List<GlyphInfo[]> getGlyphPairs() {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Map<Integer, GlyphInfo> glyphInfoMap = getGlyphMap(database);
        List<GlyphInfo[]> pairGlyphs = null;
        Cursor cursor = query(database, DataOpenHelper.TABLE_PAIRS, null, null);
        if (cursor != null) {
            pairGlyphs = new ArrayList<>();
            while (cursor.moveToNext()) {
                int length = cursor.getInt(cursor.getColumnIndex(PairsColumn.LENGTH));
                int[] pairArray = new int[length];
                pairArray[0] = cursor.getInt(cursor.getColumnIndex(PairsColumn.WITH));
                pairArray[1] = cursor.getInt(cursor.getColumnIndex(PairsColumn.WITH1));
                if (length > 2) {
                    int pair3 = cursor.getInt(cursor.getColumnIndex(PairsColumn.WITH2));
                    pairArray = Arrays.copyOf(pairArray, 3);
                    pairArray[2] = pair3;
                }
                if (length > 3) {
                    int pair4 = cursor.getInt(cursor.getColumnIndex(PairsColumn.WITH3));
                    pairArray = Arrays.copyOf(pairArray, 4);
                    pairArray[3] = pair4;
                }
                GlyphInfo[] infos = new GlyphInfo[pairArray.length];
                for (int i = 0; i < pairArray.length; i++) {
                    infos[i] = glyphInfoMap.get(pairArray[i]);
                }
                pairGlyphs.add(infos);
            }
            cursor.close();
        }
        return pairGlyphs;
    }

    public List<HackList> getHackList(int length) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Map<Integer, GlyphInfo> glyphInfoMap = getGlyphMap(database);
        List<HackList> hackLists = null;
        Cursor cursor = query(database, DataOpenHelper.TABLE_LIST, HackListColumn.LENGTH + "=?",
                new String[]{String.valueOf(length)});
        if (cursor != null) {
            hackLists = new ArrayList<>();
            String[] columns = {HackListColumn.FIRST, HackListColumn.SECOND, HackListColumn.THIRD,
                    HackListColumn.FOURTH, HackListColumn.FIFTH};
            while (cursor.moveToNext()) {
                HackList hackList = new HackList();
                hackList.setLength(length);
                int headId = cursor.getInt(cursor.getColumnIndex(HackListColumn.HEAD));
                hackList.setHead(glyphInfoMap.get(headId));
                GlyphInfo[] infos = new GlyphInfo[length];
                int sequenceId;
                for (int i = 0; i < length; i++) {
                    sequenceId = cursor.getInt(cursor.getColumnIndex(columns[i]));
                    infos[i] = glyphInfoMap.get(sequenceId);
                }
                hackList.setSequences(infos);
                hackLists.add(hackList);
            }
            cursor.close();
        }
        return hackLists;
    }

    public Path getGlyphPaths(int pathId) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = query(database, DataOpenHelper.TABLE_PATH, PathColumn._ID + "=?",
                new String[]{String.valueOf(pathId)});
        Path path = null;
        if (cursor != null) {
            path = new Path();
            if (cursor.moveToFirst()) {
                path.setId(cursor.getInt(cursor.getColumnIndex(PathColumn._ID)));
                path.setPath(Utils.stringToArray(cursor.getString(cursor.getColumnIndex(PathColumn.PATH))));
                path.setPath1(Utils.stringToArray(cursor.getString(cursor.getColumnIndex(PathColumn.PATH1))));
                path.setPath2(Utils.stringToArray(cursor.getString(cursor.getColumnIndex(PathColumn.PATH2))));
                path.setPath3(Utils.stringToArray(cursor.getString(cursor.getColumnIndex(PathColumn.PATH3))));
                path.setPath4(Utils.stringToArray(cursor.getString(cursor.getColumnIndex(PathColumn.PATH4))));
            }
            cursor.close();
        }
        return path;
    }

    public Name getGlyphNames(int nameId) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = query(database, DataOpenHelper.TABLE_NAMES, NameColumn._ID + "=?",
                new String[]{String.valueOf(nameId)});
        Name name = null;
        if (cursor != null) {
            name = new Name();
            if (cursor.moveToFirst()) {
                name.setId(cursor.getInt(cursor.getColumnIndex(NameColumn._ID)));
                name.setAlias(cursor.getString(cursor.getColumnIndex(NameColumn.ALIAS)));
                name.setAlias1(cursor.getString(cursor.getColumnIndex(NameColumn.ALIAS1)));
                name.setAlias2(cursor.getString(cursor.getColumnIndex(NameColumn.ALIAS2)));
                name.setAlias3(cursor.getString(cursor.getColumnIndex(NameColumn.ALIAS3)));
            }
            cursor.close();
        }
        return name;
    }
}
