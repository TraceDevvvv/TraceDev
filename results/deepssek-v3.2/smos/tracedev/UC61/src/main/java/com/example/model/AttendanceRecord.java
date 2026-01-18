package com.example.model;

import java.util.Date;

/**
 * Represents an attendance record for a child.
 * Type field distinguishes absence records from others.
 */
public class AttendanceRecord {
    private int recordId;
    private int childId;
    private Date date;
    private String type;
    private boolean justified;

    public AttendanceRecord(int recordId, int childId, Date date, String type, boolean justified) {
        this.recordId = recordId;
        this.childId = childId;
        this.date = date;
        this.type = type;
        this.justified = justified;
    }

    public int getRecordId() {
        return recordId;
    }

    public int getChildId() {
        return childId;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public boolean isJustified() {
        return justified;
    }
}