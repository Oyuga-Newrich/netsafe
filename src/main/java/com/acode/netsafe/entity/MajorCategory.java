package com.acode.netsafe.entity;

public class MajorCategory {
    private String id;
    private String major;

    public MajorCategory(String id, String major) {
        this.id = id;
        this.major = major;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
