package com.example.model;

/**
 * Represents a relationship between an academic year and a class.
 */
public class ClassYear {
    private String id;
    private String yearId;
    private String classId;

    public ClassYear() {
    }

    public ClassYear(String id, String yearId, String classId) {
        this.id = id;
        this.yearId = yearId;
        this.classId = classId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}