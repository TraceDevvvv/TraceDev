package com.school.model;

import java.util.Map;

/**
 * Domain entity representing a report card.
 */
public class ReportCard {
    private int _id;
    private int _studentId;
    private int _quarter;
    private Map<String, String> _grades;
    private String _comments;

    public ReportCard() {}

    public ReportCard(int id, int studentId, int quarter, Map<String, String> grades, String comments) {
        this._id = id;
        this._studentId = studentId;
        this._quarter = quarter;
        this._grades = grades;
        this._comments = comments;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public int getStudentId() {
        return _studentId;
    }

    public void setStudentId(int studentId) {
        this._studentId = studentId;
    }

    public int getQuarter() {
        return _quarter;
    }

    public void setQuarter(int quarter) {
        this._quarter = quarter;
    }

    public Map<String, String> getGrades() {
        return _grades;
    }

    public void setGrades(Map<String, String> grades) {
        this._grades = grades;
    }

    public String getComments() {
        return _comments;
    }

    public void setComments(String comments) {
        this._comments = comments;
    }

    @Override
    public String toString() {
        return "ReportCard{" +
                "_id=" + _id +
                ", _studentId=" + _studentId +
                ", _quarter=" + _quarter +
                ", _grades=" + _grades +
                ", _comments='" + _comments + '\'' +
                '}';
    }
}