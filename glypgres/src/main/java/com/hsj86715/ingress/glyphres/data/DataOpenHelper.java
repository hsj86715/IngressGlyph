package com.hsj86715.ingress.glyphres.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hushujun on 2017/11/21.
 */

class DataOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "DataOpenHelper";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "glyph_sequence.db";

    private static final String TABLE_NAMES = "glyph_name";
    private static final String TABLE_BASE = "glyph_base";
    protected static final String TABLE_GLYPH = "glyph_info";
    protected static final String TABLE_PAIRS = "glyph_pairs";
    protected static final String TABLE_SEQUENCES = "hack_sequences";

    protected class NameColumn implements BaseColumns {
        protected static final String NAME = "name";
        protected static final String ALIAS = "alias";
        protected static final String ALIAS1 = "alias1";
        protected static final String ALIAS2 = "alias2";
        protected static final String ALIAS3 = "alias3";
    }

    protected class GlyphBaseColumn implements BaseColumns {
        protected static final String PATH = "path";
        protected static final String PATH1 = "path1";
        protected static final String PATH2 = "path2";
        protected static final String PATH3 = "path3";
        protected static final String NAME_ID = "name_id";
        protected static final String CATEGORY = "category";
        protected static final String IS_NEW = "is_new";
        protected static final String LEARN_COUNT = "learn_count";
        protected static final String PRACTISE_COUNT = "practise_count";
        protected static final String PRACTISE_CORRECT = "practise_correct";
    }

    protected class GlyphPairsColumn implements BaseColumns {
        protected static final String PAIR_WITH = "pair_with";
        protected static final String PAIR_WITH1 = "pair_with1";
        protected static final String PAIR_WITH2 = "pair_with2";
        protected static final String PAIR_WITH3 = "pair_with3";
    }

    protected class SequenceColumn implements BaseColumns {
        protected static final String HEAD = "head";
        protected static final String LENGTH = "length";
        protected static final String SEQUENCE_FIRST = "sequence_first";
        protected static final String SEQUENCE_SECOND = "sequence_second";
        protected static final String SEQUENCE_THIRD = "sequence_third";
        protected static final String SEQUENCE_FOURTH = "sequence_fourth";
        protected static final String SEQUENCE_FIFTH = "sequence_fifth";
        protected static final String PRACTISE_COUNT = "practise_count";
        protected static final String PRACTISE_CORRECT = "practise_correct";
    }

    public DataOpenHelper(Context context) {
        this(context, null);
    }

    public DataOpenHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        this(context, factory, null);
    }

    public DataOpenHelper(Context context, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        super(context, DB_NAME, factory, DB_VERSION, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createNameTable(db);
        createGlyphBaseTable(db);
        createGlyphPairTable(db);
        createSequenceTable(db);
        createTableViews(db);

        insertGlyphBase(db);
//        updateGlyphCategory(db);
//        insertGlyphSequences(db);
//        insertGlyphPairs(db);
//        BaseGlyphData.getInstance().clearData();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    private void createNameTable(SQLiteDatabase db) {
        Log.i(TAG, "createNameTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAMES + " (" +
                NameColumn._ID + " INTEGER PRIMARY KEY, " +
                NameColumn.NAME + " TEXT NOT NULL, " +
                NameColumn.ALIAS + " TEXT, " +
                NameColumn.ALIAS1 + " TEXT, " +
                NameColumn.ALIAS2 + " TEXT, " +
                NameColumn.ALIAS3 + " TEXT)");
    }

    private void createGlyphBaseTable(SQLiteDatabase db) {
        Log.i(TAG, "createGlyphBaseTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_BASE + " (" +
                GlyphBaseColumn._ID + " INTEGER PRIMARY KEY, " +
                GlyphBaseColumn.NAME_ID + " INTEGER NOT NULL, " +
                GlyphBaseColumn.PATH + " TEXT NOT NULL, " +
                GlyphBaseColumn.PATH1 + " TEXT, " +
                GlyphBaseColumn.PATH2 + " TEXT, " +
                GlyphBaseColumn.PATH3 + " TEXT, " +
                GlyphBaseColumn.CATEGORY + " TEXT, " +
                GlyphBaseColumn.IS_NEW + " BLOB, " +
                GlyphBaseColumn.LEARN_COUNT + " INTEGER DEFAULT 0, " +
                GlyphBaseColumn.PRACTISE_COUNT + " INTEGER DEFAULT 0," +
                GlyphBaseColumn.PRACTISE_CORRECT + " INTEGER DEFAULT 0)");
    }

    private void createGlyphPairTable(SQLiteDatabase db) {
        Log.i(TAG, "createGlyphPairTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PAIRS + " (" +
                GlyphPairsColumn._ID + " INTEGER PRIMARY KEY, " +
                GlyphPairsColumn.PAIR_WITH + " TEXT NOT NULL, " +
                GlyphPairsColumn.PAIR_WITH1 + " TEXT NOT NULL, " +
                GlyphPairsColumn.PAIR_WITH2 + " TEXT, " +
                GlyphPairsColumn.PAIR_WITH3 + " TEXT)");
    }

    private void createSequenceTable(SQLiteDatabase db) {
        Log.i(TAG, "createSequenceTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_SEQUENCES + " ( " +
                SequenceColumn._ID + " INTEGER PRIMARY KEY, " +
                SequenceColumn.HEAD + " INTEGER NOT NULL, " +
                SequenceColumn.LENGTH + " INTEGER, " +
                SequenceColumn.SEQUENCE_FIRST + " TEXT NOT NULL, " +
                SequenceColumn.SEQUENCE_SECOND + " TEXT NOT NULL, " +
                SequenceColumn.SEQUENCE_THIRD + " TEXT, " +
                SequenceColumn.SEQUENCE_FOURTH + " TEXT, " +
                SequenceColumn.SEQUENCE_FIFTH + " TEXT, " +
                SequenceColumn.PRACTISE_COUNT + " INTEGER DEFAULT 0, " +
                SequenceColumn.PRACTISE_CORRECT + " INTEGER DEFAULT 0)");
    }

    private void createTableViews(SQLiteDatabase db) {
        Log.i(TAG, "createTableViews");
        db.execSQL("CREATE VIEW " + TABLE_GLYPH + " AS SELECT * FROM " + TABLE_BASE + "  LEFT OUTER JOIN "
                + TABLE_NAMES + " ON " + TABLE_BASE + "." + GlyphBaseColumn.NAME_ID + " = "
                + TABLE_NAMES + "." + NameColumn._ID);
    }

    private void insertGlyphBase(SQLiteDatabase db) {
        Log.i(TAG, "insertGlyphBase");
        Map<String, int[]> allGlyph = BaseGlyphData.getInstance().getGlyphByCategory(BaseGlyphData.C_ALL);
        Iterator<String> iterator = allGlyph.keySet().iterator();
        db.beginTransaction();
        try {
            while (iterator.hasNext()) {
                String key = iterator.next();
                ContentValues values = new ContentValues();
                if (key.contains("/")) {
                    String[] names = key.split("/");
                    values.put(NameColumn.NAME, names[0]);
                    values.put(NameColumn.ALIAS, names[1]);
                    if (names.length > 4) {
                        values.put(NameColumn.ALIAS3, names[4]);
                    } else if (names.length > 3) {
                        values.put(NameColumn.ALIAS2, names[3]);
                    } else if (names.length > 2) {
                        values.put(NameColumn.ALIAS1, names[2]);
                    }
                } else {
                    values.put(NameColumn.NAME, key);
                }
                long nameId = db.insert(TABLE_NAMES, null, values);
//                values = new ContentValues();
//                values.put(GlyphBaseColumn.NAME_ID, nameId);
//                values.put(GlyphBaseColumn.PATH, Arrays.toString(allGlyph.get(key)).replace("[", "")
//                        .replace("]", ""));
                db.execSQL("INSERT INTO " + TABLE_BASE + "(" + GlyphBaseColumn.NAME_ID + ","
                        + GlyphBaseColumn.PATH + ") VALUES (" + nameId + ","
                        + Arrays.toString(allGlyph.get(key)).replace("[", "").replace("]", "") + ")");
//                db.insert(TABLE_BASE, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    private void updateGlyphCategory(SQLiteDatabase db) {
        Log.i(TAG, "updateGlyphCategory");
        String[] categoryNames = new String[]{BaseGlyphData.C_ACTION, BaseGlyphData.C_COND_ENV,
                BaseGlyphData.C_FLU_DIRE, BaseGlyphData.C_HUMAN, BaseGlyphData.C_THOUGHT,
                BaseGlyphData.C_TIME_SPACE};
        db.beginTransaction();
        try {
            for (String category : categoryNames) {
                ContentValues values = new ContentValues();
                values.put(GlyphBaseColumn.CATEGORY, category);
                String[] categoryGlyph = BaseGlyphData.getInstance().getCategoryGlyph(category);
                for (String glyph : categoryGlyph) {
                    if (glyph.contains("/")) {
                        glyph = glyph.split("/")[0];
                    }
                    Cursor cursor = db.query(TABLE_NAMES, new String[]{NameColumn._ID}, NameColumn.NAME
                            + " = ?", new String[]{glyph}, null, null, null);
                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            int id = cursor.getInt(cursor.getColumnIndex(NameColumn._ID));
                            db.update(TABLE_BASE, values, GlyphBaseColumn.NAME_ID
                                    + " = " + id, null);
                        }
                        cursor.close();
                    }
                }
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    private void insertGlyphSequences(SQLiteDatabase db) {
        Log.i(TAG, "insertGlyphSequences");
        db.beginTransaction();
        try {
            insertGlyphSequences(db, BaseGlyphData.getInstance().getTwoSequences());
            insertGlyphSequences(db, BaseGlyphData.getInstance().getThreeSequences());
            insertGlyphSequences(db, BaseGlyphData.getInstance().getFourSequences());
            insertGlyphSequences(db, BaseGlyphData.getInstance().getFiveSequences());
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    private void insertGlyphSequences(SQLiteDatabase db, Map<String, String[][]> hackSequences) {
        Iterator<String> iterator = hackSequences.keySet().iterator();
        while (iterator.hasNext()) {
            String head = iterator.next();
            ContentValues values = new ContentValues();
            values.put(SequenceColumn.HEAD, head);
            String[][] sequences = hackSequences.get(head);
            for (String[] glyphSequences : sequences) {
                int length = glyphSequences.length;
                values.put(SequenceColumn.LENGTH, length);
                switch (length) {
                    case 5:
                        values.put(SequenceColumn.SEQUENCE_FIFTH, glyphSequences[4]);
                    case 4:
                        values.put(SequenceColumn.SEQUENCE_FOURTH, glyphSequences[3]);
                    case 3:
                        values.put(SequenceColumn.SEQUENCE_THIRD, glyphSequences[2]);
                    case 2:
                    default:
                        values.put(SequenceColumn.SEQUENCE_SECOND, glyphSequences[1]);
                        values.put(SequenceColumn.SEQUENCE_FIRST, glyphSequences[0]);
                        break;
                }
                db.insert(TABLE_SEQUENCES, null, values);
            }
        }
    }

    private void insertGlyphPairs(SQLiteDatabase db) {
        Log.i(TAG, "insertGlyphPairs");
        String[][] glyphPairs = BaseGlyphData.getInstance().getGlyphPairs();
        db.beginTransaction();
        try {
            for (String[] pairs : glyphPairs) {
                int length = pairs.length;
                ContentValues values = new ContentValues();
                switch (length) {
                    case 4:
                        values.put(GlyphPairsColumn.PAIR_WITH3, pairs[3]);
                    case 3:
                        values.put(GlyphPairsColumn.PAIR_WITH2, pairs[2]);
                    case 2:
                    default:
                        values.put(GlyphPairsColumn.PAIR_WITH1, pairs[1]);
                        values.put(GlyphPairsColumn.PAIR_WITH, pairs[0]);
                        break;
                }
                db.insert(TABLE_PAIRS, null, values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }
}
