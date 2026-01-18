package com.example.attendancetracker.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Model class for the Attendance feature, holding data to be displayed or processed.
 * Used by AttendanceController and AttendanceView.
 */
public class AttendanceModel {
    public Date selectedDate;
    public List<Student> studentsForForm;
    public List<AttendanceRecord> currentAttendanceRecords; // Records for the selected date
    public List<AttendanceRecord> updatedLogData; // Records after a save operation

    public AttendanceModel() {
        this.studentsForForm = new ArrayList<>();
        this.currentAttendanceRecords = new ArrayList<>();
        this.updatedLogData = new ArrayList<>();
    }

    /**
     * Initializes the model with a selected date and existing attendance records.
     * @param selectedDate The date for which attendance is being managed.
     * @param students The list of all students to display in the form.
     * @param existingRecords The attendance records already present for the selected date.
     */
    public AttendanceModel(Date selectedDate, List<Student> students, List<AttendanceRecord> existingRecords) {
        this(); // Call default constructor to initialize lists
        this.selectedDate = selectedDate;
        this.studentsForForm = students;
        this.currentAttendanceRecords = existingRecords;
        this.updatedLogData = existingRecords; // Initially, updated data is the same as current
    }

    /**
     * Updates the model with new log data after a save operation.
     * @param updatedRecords The list of attendance records after a save operation.
     */
    public void setUpdatedLogData(List<AttendanceRecord> updatedRecords) {
        this.updatedLogData = updatedRecords;
    }

    // For simplicity, getters/setters for public fields are omitted unless specific logic is required.
    // They are accessed directly in this example.
}