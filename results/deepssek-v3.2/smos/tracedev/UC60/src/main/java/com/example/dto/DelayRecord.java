package com.example.dto;

import java.util.Date;

/**
 * Represents a delay record for a student.
 */
public class DelayRecord {
    private Date date;
    private int durationMinutes;

    public DelayRecord() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}