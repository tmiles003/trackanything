package com.kleetus.trackanything;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TrackerSQLHelper extends SQLiteOpenHelper {
    Context ctx;

    public TrackerSQLHelper(Context context) {

        super(context, Constants.DBNAME, null, Constants.VERSION);

        this.ctx = context;

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        createTableTrackers(db);
        createTableKvpDefs(db);
        createTableKvpData(db);
    }

    private void createTableKvpData(SQLiteDatabase db) {
        db.execSQL("create table " +
                Constants.TABLE_KVP_DATA +
                " (" +
                Constants.COL_ID +
                " integer primary key autoincrement, " +
                Constants.COL_KVP_DEF_ID +
                " integer not null, " +
                Constants.COL_KVP_DATA +
                " blob not null, " +
                Constants.COL_DATE_CREATED +
                " integer not null);");
    }

    private void createTableKvpDefs(SQLiteDatabase db) {
        db.execSQL("create table " +
                Constants.TABLE_KVP_DEFS +
                " (" +
                Constants.COL_ID +
                " integer primary key autoincrement, " +
                Constants.COL_TRACKER_ID +
                " integer not null, " +
                Constants.COL_KVP_COLOR +
                " text not null, " +
                Constants.COL_KVP_KEY +
                " text not null, " +
                Constants.COL_KVP_VALUE_TYPE +
                " text not null, " +
                Constants.COL_KVP_GRAPH +
                " text not null, " +
                Constants.COL_DATE_CREATED +
                " integer not null);");
    }

    private void createTableTrackers(SQLiteDatabase db) {
        db.execSQL("create table " +
                Constants.TABLE_TRACKER +
                " (" +
                Constants.COL_ID +
                " integer primary key autoincrement, " +
                Constants.COL_TRACKER_NAME +
                " text not null, " +
                Constants.COL_DATE_CREATED +
                " integer not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TrackerSQLHelper.class.getName(), "Upgrading database from version " + oldVersion + "to " + newVersion + ", which will destroy all data!");

        dropTableTrackers(db);
        dropTableKvpDefs(db);
        dropTableKvpData(db);

        onCreate(db);
    }

    private void dropTableKvpData(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_KVP_DATA);
    }

    private void dropTableKvpDefs(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_KVP_DEFS);
    }

    private void dropTableTrackers(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_TRACKER);
    }
}
