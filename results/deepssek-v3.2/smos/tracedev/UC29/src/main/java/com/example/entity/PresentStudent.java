package com.example.entity;

import java.util.Date;

/**
 * Represents a student who is present on a given date.
 */
public class PresentStudent {
    private String studentId;
    private Date date;
    private Date checkInTime;

    public PresentStudent() {}

    public PresentStudent(String studentId, Date date, Date checkInTime) {
        this.studentId = studentId;
        this.date = date;
        this.checkInTime = checkInTime;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }
}