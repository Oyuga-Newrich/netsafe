package com.acode.netsafe.entity;

public class Minorcategory {
    private String id;
    private String minor;

    public Minorcategory(String id, String minor) {
        this.id = id;
        this.minor = minor;
    }

    public String getId() {
        return id;
    }

    public void setId(String major) {
        this.id = id;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }
}
