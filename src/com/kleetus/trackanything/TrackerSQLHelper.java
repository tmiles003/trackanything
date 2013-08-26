package com.kleetus.trackanything;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TrackerSQLHelper extends SQLiteOpenHelper {
    public static final String TAG = "TrackerSWLHelper";
    public static final String DBNAME = "Tracker";
    public static final int VERSION = 1;

    public TrackerSQLHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table trackers (_id integer primary key autoincrement, name text not null, date_created integer not null);");
        db.execSQL("create table kvp_definitions (_id integer primary key autoincrement, tracker_id integer not null, date_created integer not null, key text not null, value_type text not null, color text not null);");
        db.execSQL("create table actual (_id integer primary key autoincrement, kvp_definition_id integer not null, date_created integer not null, data blob not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + "to " + newVersion + ", which will destroy all data!");
        db.execSQL("drop table if exists trackers");
        db.execSQL("drop table if exists kvp_definitions");
        db.execSQL("drop table if exists actual");

        onCreate(db);
    }
}
