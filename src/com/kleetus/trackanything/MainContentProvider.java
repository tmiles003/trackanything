package com.kleetus.trackanything;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MainContentProvider extends ContentProvider {
    TrackerSQLHelper db;

    private static final String AUTHORITY = "com.kleetus.trackanything.MainContentProvider";
    private static final String TRACKER_BASE_PATH = "trackers";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TRACKER_BASE_PATH);
    public static final String CONTENT_TYPE_ITEM = ContentResolver.CURSOR_ITEM_BASE_TYPE;
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        //uriMatcher.addURI();
    }

    @Override
    public boolean onCreate() {
        db = new TrackerSQLHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
