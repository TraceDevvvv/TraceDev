package com.example.model;

import java.util.Date;

/**
 * Represents a delay entry for a student in a class.
 * Mapped from Delay in UML class diagram.
 */
public class Delay {
    private String delayId;
    private String classId;
    private String studentId;
    private Date date;
    private int delayTime;

    public Delay(String delayId, String classId, String studentId, Date date, int delayTime) {
        this.delayId = delayId;
        this.classId = classId;
        this.studentId = studentId;
        this.date = date;
        this.delayTime = delayTime;
    }

    public String getDelayId() {
        return delayId;
    }

    public void setDelayId(String delayId) {
        this.delayId = delayId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }
}