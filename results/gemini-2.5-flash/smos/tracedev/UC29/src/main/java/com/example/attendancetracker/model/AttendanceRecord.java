package com.example.attendancetracker.model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents an attendance record for a student on a specific date.
 */
public class AttendanceRecord {
    private String recordId;
    private String studentId;
    public Date recordDate;
    public AttendanceStatus status;
    public boolean isNotified;

    public AttendanceRecord(String studentId, Date recordDate, AttendanceStatus status) {
        this.recordId = UUID.randomUUID().toString(); // Generate unique ID
        this.studentId = studentId;
        this.recordDate = recordDate;
        this.status = status;
        this.isNotified = false; // Default to false
    }

    // Constructor for loading existing records with a given ID and notification status
    public AttendanceRecord(String recordId, String studentId, Date recordDate, AttendanceStatus status, boolean isNotified) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.recordDate = recordDate;
        this.status = status;
        this.isNotified = isNotified;
    }

    // Getters for private fields
    public String getRecordId() {
        return recordId;
    }

    public String getStudentId() {
        return studentId;
    }

    // Custom equals and hashCode for proper comparison, especially useful in mock repositories
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceRecord that = (AttendanceRecord) o;
        // Two records are considered equal if they refer to the same student on the same date
        // (recordId is for unique instances, but for "business key" this is more relevant)
        return studentId.equals(that.studentId) &&
               recordDate.equals(that.recordDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, recordDate);
    }

    @Override
    public String toString() {
        return "Record{" +
               "id='" + recordId.substring(0,4) + '\'' +
               ", studentId='" + studentId + '\'' +
               ", date=" + recordDate.toInstant().toString().substring(0,10) +
               ", status=" + status +
               ", notified=" + isNotified +
               '}';
    }
}