package com.school.model;

/**
 * Domain entity representing a class.
 */
public class Class {
    private int _id;
    private String _name;
    private int _academicYearId;

    public Class() {}

    public Class(int id, String name, int academicYearId) {
        this._id = id;
        this._name = name;
        this._academicYearId = academicYearId;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public int getAcademicYearId() {
        return _academicYearId;
    }

    public void setAcademicYearId(int academicYearId) {
        this._academicYearId = academicYearId;
    }

    @Override
    public String toString() {
        return "Class{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _academicYearId=" + _academicYearId +
                '}';
    }
}