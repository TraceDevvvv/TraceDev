package com.example.attendancesystem;

import java.time.LocalDate;

/**
 * Represents a single attendance record for a student on a specific date.
 * Defined in the Domain layer of the Class Diagram.
 * Attributes are private with public getters for encapsulation.
 */
public class AttendanceRecord {
    private String recordId;
    private LocalDate date;
    private String studentId;
    private AttendanceStatus status;

    /**
     * Constructs a new AttendanceRecord instance.
     * @param recordId The unique identifier for this attendance record.
     * @param date The date of the attendance record.
     * @param studentId The ID of the student this record belongs to.
     * @param status The attendance status (PRESENT or ABSENT).
     */
    public AttendanceRecord(String recordId, LocalDate date, String studentId, AttendanceStatus status) {
        this.recordId = recordId;
        this.date = date;
        this.studentId = studentId;
        this.status = status;
    }

    /**
     * Gets the unique identifier of the attendance record.
     * @return The record ID.
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * Gets the date of the attendance record.
     * @return The date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the ID of the student associated with this record.
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the attendance status for this record.
     * @return The attendance status.
     */
    public AttendanceStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "AttendanceRecord{" +
               "recordId='" + recordId + '\'' +
               ", date=" + date +
               ", studentId='" + studentId + '\'' +
               ", status=" + status +
               '}';
    }
}