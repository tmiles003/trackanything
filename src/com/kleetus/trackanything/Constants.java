package com.kleetus.trackanything;


import android.net.Uri;

public class Constants {
    public static final String DBNAME = "Tracker";
    public static final int VERSION = 1;

    public static final String TABLE_TRACKER = "trackers";
    public static final String TABLE_KVP_DEFS = "kvp_definitions";
    public static final String TABLE_KVP_DATA = "kvp_data";

    public static final String COL_DATE_CREATED = "date_created";
    public static final String COL_ID = "_id";

    public static final String COL_TRACKER_NAME = "name";
    public static final String COL_TRACKER_ID = "tracker_id";
    public static final String COL_KVP_KEY = "key";
    public static final String COL_KVP_VALUE_TYPE = "value_type";
    public static final String COL_KVP_COLOR = "color";
    public static final String COL_KVP_DEF_ID = "kvp_def_id";
    public static final String COL_KVP_DATA = "data";
    public static final int MAIN_LOADER = 0;

    public static final String METHOD = "method";
    public static final String ID = "id";

    public static final String DELETE = "delete";

    public static final String TRACKER_NAME_STORAGE_PREFIX = "tracker";
    public static final String BLANK_PLACEHOLDER = "";

    public static final String AUTHORITY = "com.kleetus.trackanything.MainContentProvider";
    public static final String TRACKER_BASE_PATH = "trackers";
    public static final String KVP_DEFINITION_BASE_PATH = "kvp_definition";
    public static final String KVP_DATA_BASE_PATH = "kvp_data";

    public static final int TRACKERS = 1;
    public static final int KVP_DEFINITION = 2;
    public static final int KVP_DATA = 3;

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TRACKER_BASE_PATH);

}
