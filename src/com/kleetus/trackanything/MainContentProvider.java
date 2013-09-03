package com.kleetus.trackanything;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class MainContentProvider extends ContentProvider {
    TrackerSQLHelper db;
    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

    private static final String AUTHORITY = "com.kleetus.trackanything.MainContentProvider";
    private static final String TRACKER_BASE_PATH = "trackers";

    public static final int TRACKERS = 100;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TRACKER_BASE_PATH);

    public static final Uri DELETE_TRACKER_URI = Uri.parse("content://" + AUTHORITY + "/" + TRACKER_BASE_PATH);

    public static final String CONTENT_TYPE_ITEM = ContentResolver.CURSOR_ITEM_BASE_TYPE;
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, TRACKER_BASE_PATH, TRACKERS);
    }

    @Override
    public boolean onCreate() {
        db = new TrackerSQLHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        queryBuilder.setTables(Constants.TABLE_TRACKER);
        int uriType = uriMatcher.match(uri);

        switch (uriType) {
            case TRACKERS:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }

        Cursor cursor = queryBuilder.query(db.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
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
        int count =  db.getWritableDatabase().delete(Constants.TABLE_TRACKER, selection, selectionArgs);
        db.close();
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
