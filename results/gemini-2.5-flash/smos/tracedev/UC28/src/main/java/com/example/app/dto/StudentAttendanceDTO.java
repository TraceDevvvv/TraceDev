package com.example.app.dto;

import com.example.app.DTO;
import com.example.app.domain.AttendanceStatus;

/**
 * DTO for representing a student's attendance details for a specific date.
 */
public class StudentAttendanceDTO implements DTO {
    public String studentId;
    public String studentName;
    public AttendanceStatus attendanceStatus;
    public boolean isLate;
    public boolean hasJustification;

    /**
     * Constructs a new StudentAttendanceDTO.
     * @param studentId The ID of the student.
     * @param studentName The name of the student.
     * @param attendanceStatus The attendance status (e.g., PRESENT, ABSENT).
     * @param isLate True if the student was late, false otherwise.
     * @param hasJustification True if there's an associated justification.
     */
    public StudentAttendanceDTO(String studentId, String studentName, AttendanceStatus attendanceStatus, boolean isLate, boolean hasJustification) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.attendanceStatus = attendanceStatus;
        this.isLate = isLate;
        this.hasJustification = hasJustification;
    }

    // Getters for public fields
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public AttendanceStatus getAttendanceStatus() {
        return attendanceStatus;
    }

    public boolean isLate() {
        return isLate;
    }

    public boolean hasJustification() {
        return hasJustification;
    }

    @Override
    public String toString() {
        return String.format("  Student: %s (ID: %s), Status: %s, Late: %b, Justified: %b",
                studentName, studentId, attendanceStatus, isLate, hasJustification);
    }
}