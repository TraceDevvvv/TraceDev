package com.example.model;

import java.util.Date;

public class Register {
    private String registerId;
    private int academicYear;
    private String classId;
    private Date dateCreated;

    public Register() {}

    public Register(String registerId, int academicYear, String classId, Date dateCreated) {
        this.registerId = registerId;
        this.academicYear = academicYear;
        this.classId = classId;
        this.dateCreated = dateCreated;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}