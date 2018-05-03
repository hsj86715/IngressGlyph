package com.hsj86715.ingress.glyphres.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import cn.com.farmcode.utility.tools.Logger;

/**
 * Created by hushujun on 2017/11/21.
 *
 * @author hushujun
 * @date 2018/02/13
 */

class DataOpenHelper extends SQLiteOpenHelper {
    protected static final String TABLE_NAMES = "glyph_name";
    protected static final String TABLE_BASE = "glyph_base";
    protected static final String TABLE_CATEGORY = "glyph_category";
    protected static final String TABLE_PATH = "glyph_path";
    protected static final String TABLE_PAIRS = "glyph_pairs";
    protected static final String TABLE_LIST = "hack_list";
    protected static final String TABLE_GLYPH = "glyph_view";
    protected static final String TABLE_EDIT_TEMP = "edit_temp";
    private static final String TAG = "DataOpenHelper";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "glyph_sequence.db";

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
        createCategoryTable(db);
        createPathTable(db);

        createPairTable(db);
        createGlyphBaseTable(db);
        createHackListTable(db);

        createEditTempTable(db);
        createGlyphView(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.e(TAG, "onDowngrade: oldVersion=" + oldVersion + ", newVersion=" + newVersion);
    }

    private void createNameTable(SQLiteDatabase db) {
        Logger.i(TAG, "createNameTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAMES + " (" +
                NameColumn._ID + " INTEGER PRIMARY KEY, " +
                NameColumn.NAME + " TEXT NOT NULL, " +
                NameColumn.ALIAS + " TEXT, " +
                NameColumn.ALIAS1 + " TEXT, " +
                NameColumn.ALIAS2 + " TEXT, " +
                NameColumn.ALIAS3 + " TEXT)");
    }

    private void createCategoryTable(SQLiteDatabase db) {
        Logger.i(TAG, "createCategoryTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORY + " ( " +
                CategoryColumn._ID + " INTEGER PRIMARY KEY, " +
                CategoryColumn.CATEGORY + " TEXT NOT NULL )");
    }

    private void createPathTable(SQLiteDatabase db) {
        Logger.i(TAG, "createPathTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PATH + " ( " +
                PathColumn._ID + " INTEGER PRIMARY KEY, " +
                PathColumn.PATH + " TEXT NOT NULL, " +
                PathColumn.PATH1 + " TEXT, " +
                PathColumn.PATH2 + " TEXT, " +
                PathColumn.PATH3 + " TEXT, " +
                PathColumn.PATH4 + " TEXT )");
    }

    private void createGlyphBaseTable(SQLiteDatabase db) {
        Logger.i(TAG, "createGlyphBaseTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_BASE + " (" +
                GlyphBaseColumn._ID + " INTEGER PRIMARY KEY, " +
                GlyphBaseColumn.NAME_ID + " INTEGER NOT NULL, " +
                GlyphBaseColumn.CATEGORY_ID + " INTEGER, " +
                GlyphBaseColumn.PATH_ID + " INTEGER NOT NULL, " +
                GlyphBaseColumn.LEARN_COUNT + " INTEGER DEFAULT 0, " +
                GlyphBaseColumn.PRACTISE_COUNT + " INTEGER DEFAULT 0," +
                GlyphBaseColumn.PRACTISE_CORRECT + " INTEGER DEFAULT 0)");
    }

    private void createPairTable(SQLiteDatabase db) {
        Logger.i(TAG, "createPairTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PAIRS + " (" +
                PairsColumn._ID + " INTEGER PRIMARY KEY, " +
                PairsColumn.WITH + " INTEGER NOT NULL, " +
                PairsColumn.WITH1 + " INTEGER NOT NULL, " +
                PairsColumn.WITH2 + " INTEGER, " +
                PairsColumn.WITH3 + " INTEGER, " +
                PairsColumn.LENGTH + " INTEGER, " +
                PairsColumn.LEARN_COUNT + " INTEGER DEFAULT 0, " +
                PairsColumn.PRACTISE_COUNT + " INTEGER DEFAULT 0," +
                PairsColumn.PRACTISE_CORRECT + " INTEGER DEFAULT 0)");
    }

    private void createHackListTable(SQLiteDatabase db) {
        Logger.i(TAG, "createListTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_LIST + " ( " +
                HackListColumn._ID + " INTEGER PRIMARY KEY, " +
                HackListColumn.HEAD + " INTEGER NOT NULL, " +
                HackListColumn.LENGTH + " INTEGER, " +
                HackListColumn.FIRST + " INTEGER NOT NULL, " +
                HackListColumn.SECOND + " INTEGER NOT NULL, " +
                HackListColumn.THIRD + " INTEGER, " +
                HackListColumn.FOURTH + " INTEGER, " +
                HackListColumn.FIFTH + " INTEGER, " +
                HackListColumn.LEARN_COUNT + " INTEGER DEFAULT 0," +
                HackListColumn.PRACTISE_COUNT + " INTEGER DEFAULT 0, " +
                HackListColumn.PRACTISE_CORRECT + " INTEGER DEFAULT 0)");
    }

    private void createEditTempTable(SQLiteDatabase db) {
        Logger.i(TAG, "createEditTempTable");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_EDIT_TEMP + " (" +
                EditTempColumn._ID + " INTEGER PRIMARY KEY, " +
                EditTempColumn.GLYPH_ID + " INTEGER NOT NULL, " +
                EditTempColumn.ALIAS + " TEXT, " +
                EditTempColumn.ALIAS1 + " TEXT, " +
                EditTempColumn.ALIAS2 + " TEXT, " +
                EditTempColumn.ALIAS3 + " TEXT, " +
                EditTempColumn.CAT_NAME + " TEXT NOT NULL, " +
                EditTempColumn.PATH1 + " TEXT, " +
                EditTempColumn.PATH2 + " TEXT, " +
                EditTempColumn.PATH3 + " TEXT, " +
                EditTempColumn.PATH4 + " TEXT )");
    }

    private void createGlyphView(SQLiteDatabase db) {
        Logger.i(TAG, "createGlyphView");
        db.execSQL("CREATE VIEW " + TABLE_GLYPH +
                " AS SELECT " +
                TABLE_BASE + "." + GlyphBaseColumn._ID + ", " +
                TABLE_NAMES + "." + NameColumn.NAME + ", " +
                TABLE_NAMES + "." + NameColumn._ID + " AS " + GlyphInfoColumn.NAME_ID + ", " +
                TABLE_CATEGORY + "." + CategoryColumn.CATEGORY + ", " +
                TABLE_PATH + "." + PathColumn.PATH + ", " +
                TABLE_PATH + "." + PathColumn._ID + " AS " + GlyphInfoColumn.PATH_ID + ", " +
                TABLE_BASE + "." + GlyphBaseColumn.LEARN_COUNT + ", " +
                TABLE_BASE + "." + GlyphBaseColumn.PRACTISE_COUNT + ", " +
                TABLE_BASE + "." + GlyphBaseColumn.PRACTISE_CORRECT +
                " FROM " +
                TABLE_BASE + ", " + TABLE_NAMES + ", " + TABLE_CATEGORY + ", " + TABLE_PATH +
                " WHERE " +
                TABLE_BASE + "." + GlyphBaseColumn.NAME_ID + "=" + TABLE_NAMES + "." + NameColumn._ID + " AND " +
                TABLE_BASE + "." + GlyphBaseColumn.CATEGORY_ID + "=" + TABLE_CATEGORY + "." + CategoryColumn._ID + " AND " +
                TABLE_BASE + "." + GlyphBaseColumn.PATH_ID + "=" + TABLE_PATH + "." + PathColumn._ID);
    }

    protected class PractiseColumn implements BaseColumns {
        protected static final String LEARN_COUNT = "learn_count";
        protected static final String PRACTISE_COUNT = "practise_count";
        protected static final String PRACTISE_CORRECT = "practise_correct";
    }

    protected class NameColumn implements BaseColumns {
        protected static final String NAME = "name";
        protected static final String ALIAS = "alias";
        protected static final String ALIAS1 = "alias1";
        protected static final String ALIAS2 = "alias2";
        protected static final String ALIAS3 = "alias3";
    }

    protected class PathColumn implements BaseColumns {
        protected static final String PATH = "path";
        protected static final String PATH1 = "path1";
        protected static final String PATH2 = "path2";
        protected static final String PATH3 = "path3";
        protected static final String PATH4 = "path4";
    }

    protected class CategoryColumn implements BaseColumns {
        protected static final String CATEGORY = "category";
    }

    protected class GlyphBaseColumn extends PractiseColumn {
        protected static final String NAME_ID = "name_id";
        protected static final String CATEGORY_ID = "category_id";
        protected static final String PATH_ID = "path_id";
    }

    protected class PairsColumn extends PractiseColumn {
        protected static final String WITH = "pair_with";
        protected static final String WITH1 = "pair_with1";
        protected static final String WITH2 = "pair_with2";
        protected static final String WITH3 = "pair_with3";
        protected static final String LENGTH = "pair_length";
    }

    protected class HackListColumn extends PractiseColumn {
        protected static final String HEAD = "head";
        protected static final String LENGTH = "length";
        protected static final String FIRST = "first";
        protected static final String SECOND = "second";
        protected static final String THIRD = "third";
        protected static final String FOURTH = "fourth";
        protected static final String FIFTH = "fifth";
    }

    protected class EditTempColumn implements BaseColumns {
        protected static final String GLYPH_ID = "glyph_id";
        protected static final String ALIAS = NameColumn.ALIAS;
        protected static final String ALIAS1 = NameColumn.ALIAS1;
        protected static final String ALIAS2 = NameColumn.ALIAS2;
        protected static final String ALIAS3 = NameColumn.ALIAS3;
        protected static final String CAT_NAME = GlyphInfoColumn.CATEGORY;
        protected static final String PATH1 = PathColumn.PATH1;
        protected static final String PATH2 = PathColumn.PATH2;
        protected static final String PATH3 = PathColumn.PATH3;
        protected static final String PATH4 = PathColumn.PATH4;
    }

    protected class GlyphInfoColumn {
        protected static final String ID = GlyphBaseColumn._ID;
        protected static final String NAME = NameColumn.NAME;
        protected static final String NAME_ID = "name_id";
        protected static final String CATEGORY = CategoryColumn.CATEGORY;
        protected static final String PATH = PathColumn.PATH;
        protected static final String PATH_ID = "path_id";
        protected static final String LEARN_COUNT = GlyphBaseColumn.LEARN_COUNT;
        protected static final String PRACTISE_COUNT = GlyphBaseColumn.PRACTISE_COUNT;
        protected static final String PRACTISE_CORRECT = GlyphBaseColumn.PRACTISE_CORRECT;
    }
}
