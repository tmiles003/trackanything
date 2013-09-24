package com.kleetus.trackanything;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class MainContentProvider extends ContentProvider {

    TrackerSQLHelper db;
    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(Constants.AUTHORITY, Constants.TRACKER_BASE_PATH, Constants.TRACKERS);
        uriMatcher.addURI(Constants.AUTHORITY, Constants.KVP_DEFINITION_BASE_PATH, Constants.KVP_DEFINITION);
        uriMatcher.addURI(Constants.AUTHORITY, Constants.KVP_DATA_BASE_PATH, Constants.KVP_DATA);
    }

    @Override
    public boolean onCreate() {

        db = new TrackerSQLHelper(getContext());
        return true;

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        int uriType = uriMatcher.match(uri);

        switch (uriType) {
            case Constants.TRACKERS:
                queryBuilder.setTables(Constants.TABLE_TRACKER);
                break;
            case Constants.KVP_DEFINITION:
                queryBuilder.setTables(Constants.TABLE_KVP_DEFS);
                break;
            case Constants.KVP_DATA:
                queryBuilder.setTables(Constants.TABLE_KVP_DATA);
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

        int uriType = uriMatcher.match(uri);

        switch (uriType) {
            case Constants.TRACKERS:
                long tracker_id = insertTracker(values);
                uri = Uri.parse(Constants.AUTHORITY + "/" + Constants.TRACKER_BASE_PATH + "/" + tracker_id);
                break;
            case Constants.KVP_DEFINITION:
                long kvp_def_id = insertKvpDefinition(values);
                uri = Uri.parse(Constants.AUTHORITY + "/" + Constants.KVP_DEFINITION_BASE_PATH + "/" + kvp_def_id);
                break;
            default:
                throw new IllegalArgumentException("Unknown API");
        }

        return uri;

    }

    private long insertKvpDefinition(ContentValues values) {

        values = markTime(values);

        long id = db.getWritableDatabase().insert(Constants.TABLE_KVP_DEFS, null, values);
        db.close();
        return id;

     }

    private long insertTracker(ContentValues values) {

        if (values.get(Constants.COL_TRACKER_NAME).equals(Constants.BLANK_PLACEHOLDER)) {
            values.put(Constants.COL_TRACKER_NAME, Constants.TRACKER_NAME_STORAGE_PREFIX + Integer.toString(findLastTrackerId()));
        }

        values = markTime(values);

        long id = db.getWritableDatabase().insert(Constants.TABLE_TRACKER, null, values);
        db.close();
        return id;

    }

    private int findLastTrackerId() {

        int ret = 0;

        String[] projection = {Constants.COL_ID, Constants.COL_TRACKER_NAME};
        String selection = Constants.COL_TRACKER_NAME + " like '" + Constants.TRACKER_NAME_STORAGE_PREFIX + "%'";

        Cursor cursor = queryBuilder.query(db.getReadableDatabase(), projection, selection, null, null, null, Constants.COL_ID);

        if (null == cursor || cursor.getCount() == 0) {
            return ret;
        }

        cursor.moveToLast();
        String lastName = cursor.getString(1);
        String[] lastNumber = lastName.split(Constants.TRACKER_NAME_STORAGE_PREFIX);

        if (lastNumber.length > 1) {
            try {
                ret = Integer.parseInt(lastNumber[lastNumber.length - 1]);
                ret++;
            } catch (NumberFormatException e) {
            }
        }
        return ret;
    }

    private ContentValues markTime(ContentValues values) {

        values.put(Constants.COL_DATE_CREATED, MainApplication.getTime());
        return values;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int count = db.getWritableDatabase().delete(Constants.TABLE_TRACKER, selection, selectionArgs);
        db.close();
        return count;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        return 0;

    }
}
