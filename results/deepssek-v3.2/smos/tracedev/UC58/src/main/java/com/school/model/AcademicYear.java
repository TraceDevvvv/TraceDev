package com.school.model;

/**
 * Domain entity representing an academic year.
 */
public class AcademicYear {
    private int _id;
    private String _year;

    public AcademicYear() {}

    public AcademicYear(int id, String year) {
        this._id = id;
        this._year = year;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getYear() {
        return _year;
    }

    public void setYear(String year) {
        this._year = year;
    }

    @Override
    public String toString() {
        return "AcademicYear{" +
                "_id=" + _id +
                ", _year='" + _year + '\'' +
                '}';
    }
}