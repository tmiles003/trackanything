package com.kleetus.trackanything;


public class KvpDefinition {

    long id;
    long tracker_id;
    String color;
    String key;
    String valueType;
    KvpData data;



    public KvpDefinition(KvpData data) {

        this.data = data;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTracker_id() {
        return tracker_id;
    }

    public void setTracker_id(long tracker_id) {
        this.tracker_id = tracker_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }



}
