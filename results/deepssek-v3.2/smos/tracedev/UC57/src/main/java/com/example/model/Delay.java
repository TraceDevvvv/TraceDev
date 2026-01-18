package com.example.model;

import java.util.Date;

/**
 * Represents a student's delay.
 */
public class Delay {
    private String studentName;
    private int delayMinutes;
    private Date date;

    public Delay(String studentName, int delayMinutes, Date date) {
        this.studentName = studentName;
        this.delayMinutes = delayMinutes;
        this.date = date;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(int delayMinutes) {
        this.delayMinutes = delayMinutes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}