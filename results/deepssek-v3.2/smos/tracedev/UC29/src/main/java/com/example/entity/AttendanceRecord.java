package com.example.entity;

import com.example.dto.AttendanceStats;
import java.util.Date;
import java.util.List;

/**
 * Represents an attendance record for a specific date.
 */
public class AttendanceRecord {
    private String id;
    private Date date;
    private List<AbsentStudent> absentStudents;
    private List<PresentStudent> presentStudents;
    private Date timestamp;

    public AttendanceRecord() {}

    public AttendanceRecord(String id, Date date, List<AbsentStudent> absentStudents,
                            List<PresentStudent> presentStudents, Date timestamp) {
        this.id = id;
        this.date = date;
        this.absentStudents = absentStudents;
        this.presentStudents = presentStudents;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<AbsentStudent> getAbsentStudents() {
        return absentStudents;
    }

    public void setAbsentStudents(List<AbsentStudent> absentStudents) {
        this.absentStudents = absentStudents;
    }

    public List<PresentStudent> getPresentStudents() {
        return presentStudents;
    }

    public void setPresentStudents(List<PresentStudent> presentStudents) {
        this.presentStudents = presentStudents;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Calculates attendance statistics.
     * For simplicity, returns a dummy object.
     */
    public AttendanceStats calculateStats() {
        int total = (absentStudents != null ? absentStudents.size() : 0) +
                    (presentStudents != null ? presentStudents.size() : 0);
        int present = presentStudents != null ? presentStudents.size() : 0;
        int absent = absentStudents != null ? absentStudents.size() : 0;
        return new AttendanceStats(total, present, absent);
    }
}