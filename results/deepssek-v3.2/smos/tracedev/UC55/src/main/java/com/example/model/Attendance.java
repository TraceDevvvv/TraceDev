package com.example.model;

import java.util.Date;

/**
 * Represents an attendance record for a student on a specific date.
 * Contains delay time information.
 */
public class Attendance {
    private Date date;
    private int delayTime;

    public Attendance(Date date, int delayTime) {
        this.date = date;
        this.delayTime = delayTime;
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