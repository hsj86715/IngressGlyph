package com.hsj86715.ingress.glyphres.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hsj86715.ingress.glyphres.data.DataOpenHelper.GlyphInfoColumn;
import com.hsj86715.ingress.glyphres.data.DataOpenHelper.HackListColumn;
import com.hsj86715.ingress.glyphres.data.DataOpenHelper.NameColumn;
import com.hsj86715.ingress.glyphres.data.DataOpenHelper.PairsColumn;
import com.hsj86715.ingress.glyphres.data.DataOpenHelper.PathColumn;

/**
 * @author hushujun
 */
public class IngressProvider extends ContentProvider {
    private static final String CONTENT_AUTHORITY = "cn.com.farmcode.ingress.sequence";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    private static final int MATCH_GLYPHS = 0;
    private static final int MATCH_PAIRS = 1;
    private static final int MATCH_NAME = 2;
    private static final int MATCH_PATH = 3;
    private static final int MATCH_LIST = 4;
    private static final int MATCH_CATEGORIES = 5;

    private static UriMatcher sMatcher;

    public static final class GlyphInfoEntry {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(DataOpenHelper.TABLE_GLYPH).build();

        public static Uri buildUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static final String ID = GlyphInfoColumn.ID;
        public static final String NAME = GlyphInfoColumn.NAME;
        public static final String NAME_ID = GlyphInfoColumn.NAME_ID;
        public static final String CATEGORY = GlyphInfoColumn.CATEGORY;
        public static final String PATH = GlyphInfoColumn.PATH;
        public static final String PATH_ID = GlyphInfoColumn.PATH_ID;
        public static final String LEARN_COUNT = GlyphInfoColumn.LEARN_COUNT;
        public static final String PRACTISE_COUNT = GlyphInfoColumn.PRACTISE_COUNT;
        public static final String PRACTISE_CORRECT = GlyphInfoColumn.PRACTISE_CORRECT;
    }

    public static final class PairsEntry {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(DataOpenHelper.TABLE_PAIRS).build();

        public static final String WITH = PairsColumn.WITH;
        public static final String WITH1 = PairsColumn.WITH1;
        public static final String WITH2 = PairsColumn.WITH2;
        public static final String WITH3 = PairsColumn.WITH3;
        public static final String LENGTH = PairsColumn.LENGTH;
    }

    public static final class HackListEntry {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(DataOpenHelper.TABLE_LIST).build();

        public static final String HEAD = HackListColumn.HEAD;
        public static final String LENGTH = HackListColumn.LENGTH;
        public static final String FIRST = HackListColumn.FIRST;
        public static final String SECOND = HackListColumn.SECOND;
        public static final String THIRD = HackListColumn.THIRD;
        public static final String FOURTH = HackListColumn.FOURTH;
        public static final String FIFTH = HackListColumn.FIFTH;
        public static final String PRACTISE_COUNT = HackListColumn.PRACTISE_COUNT;
        public static final String PRACTISR_CORRECT = HackListColumn.PRACTISE_CORRECT;
    }

    public static final class NameEntry {
        private static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(DataOpenHelper.TABLE_NAMES).build();

        public static Uri buildUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static final String ID = NameColumn._ID;
        public static final String NAME = NameColumn.NAME;
        public static final String ALISA = NameColumn.ALIAS;
        public static final String ALISA1 = NameColumn.ALIAS1;
        public static final String ALISA2 = NameColumn.ALIAS2;
        public static final String ALISA3 = NameColumn.ALIAS3;
    }

    public static final class PathEntry {
        private static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(DataOpenHelper.TABLE_PATH).build();

        public static Uri buildUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static final String PATH = PathColumn.PATH;
        public static final String PATH1 = PathColumn.PATH1;
        public static final String PATH2 = PathColumn.PATH2;
        public static final String PATH3 = PathColumn.PATH3;
        public static final String PATH4 = PathColumn.PATH4;
    }

    static {
        sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sMatcher.addURI(CONTENT_AUTHORITY, DataOpenHelper.TABLE_GLYPH, MATCH_GLYPHS);
        sMatcher.addURI(CONTENT_AUTHORITY, DataOpenHelper.TABLE_CATEGORY, MATCH_CATEGORIES);
        sMatcher.addURI(CONTENT_AUTHORITY, DataOpenHelper.TABLE_LIST, MATCH_LIST);
        sMatcher.addURI(CONTENT_AUTHORITY, DataOpenHelper.TABLE_NAMES, MATCH_NAME);
        sMatcher.addURI(CONTENT_AUTHORITY, DataOpenHelper.TABLE_PAIRS, MATCH_PAIRS);
        sMatcher.addURI(CONTENT_AUTHORITY, DataOpenHelper.TABLE_PATH, MATCH_PATH);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        // TODO: 2018/5/2 Not support this option, do nothing
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        // TODO: 2018/5/2 Not support this option, do nothing
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
