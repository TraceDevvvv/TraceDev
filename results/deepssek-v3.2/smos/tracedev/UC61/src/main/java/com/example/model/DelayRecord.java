package com.example.model;

import java.util.Date;

/**
 * Represents a delay/ tardiness record for a child.
 */
public class DelayRecord {
    private int delayId;
    private int childId;
    private Date date;
    private int durationMinutes;
    private boolean justified;

    public DelayRecord(int delayId, int childId, Date date, int durationMinutes, boolean justified) {
        this.delayId = delayId;
        this.childId = childId;
        this.date = date;
        this.durationMinutes = durationMinutes;
        this.justified = justified;
    }

    public int getDelayId() {
        return delayId;
    }

    public int getChildId() {
        return childId;
    }

    public Date getDate() {
        return date;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public boolean isJustified() {
        return justified;
    }
}