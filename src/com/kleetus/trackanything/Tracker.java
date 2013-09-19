package com.kleetus.trackanything;


public class Tracker {
    String name;
    long id;
    KvpDefinition kvp;

    public Tracker(KvpDefinition kvp) {

        this.kvp = kvp;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }


}
