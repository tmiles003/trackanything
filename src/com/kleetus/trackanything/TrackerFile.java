package com.kleetus.trackanything;


public class TrackerFile {

    //each tracker will have its own yaml file.
    //the generic structure for this file will be:

//    ---
//    tracker_name: foo tracker
//      kvp_list:
//        - keyname: somestring
//          keyid: someintegerid
//          value: somethingvalue
//          valuetype: sometypeforthevalue
//          color: somecolor
//        - keyname: someotherkeyname
//          keyid: someintegerid
//          value: someothervalue
//          valuetype: sometypeforthevalue
//          color: somecolor
//
//    ...

}


//tables needed
//
//trackers
//kvp_definitions
//actual
//
//
//
//
//trackers
//        primary_key (integer)
//        tracker_name (string)
//        date_created (date)
//
//
//
//kvp_definitions
//        primary_key (integer)
//        tracker_name_id (foreign key, integer)
//        date_created (date)
//        key (string)
//        value_type (type as a string, hopefully I can dynamically cast using this string??)
//        color (string)
//
//actual
//        primary_key (integer)
//        kvp_definitions_id (foreign key, integer)
//        date_created (date)
//        data (binary, this will be casted into the proper type based on the "value type" string value)




