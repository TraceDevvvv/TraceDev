package com.example.app.domain;

import com.example.app.Domain;
import java.util.Date;

/**
 * Represents an attendance record for a student in a specific register on a given date.
 */
public class AttendanceRecord implements Domain {
    public String id;
    public String studentId;
    public String registerId;
    public Date date;
    public AttendanceStatus status;
    public boolean isLate;
    public String justificationId; // Nullable, refers to a Justification if present

    /**
     * Constructs a new AttendanceRecord.
     * @param id The unique identifier for the attendance record.
     * @param studentId The ID of the student.
     * @param registerId The ID of the register.
     * @param date The date of the attendance.
     * @param status The attendance status (e.g., PRESENT, ABSENT).
     * @param isLate True if the student was late, false otherwise.
     * @param justificationId The ID of the associated justification, if any.
     */
    public AttendanceRecord(String id, String studentId, String registerId, Date date, AttendanceStatus status, boolean isLate, String justificationId) {
        this.id = id;
        this.studentId = studentId;
        this.registerId = registerId;
        this.date = date;
        this.status = status;
        this.isLate = isLate;
        this.justificationId = justificationId;
    }

    // Getters for public fields
    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getRegisterId() {
        return registerId;
    }

    public Date getDate() {
        return date;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public boolean isLate() {
        return isLate;
    }

    public String getJustificationId() {
        return justificationId;
    }
}