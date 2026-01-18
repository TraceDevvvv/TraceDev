package com.example.dto;

import com.example.entity.AttendanceLog;
import com.example.entity.Student;
import java.util.Date;
import java.util.List;

/**
 * Data object for attendance display information.
 */
public class AttendanceDisplayData {
    public Date date;
    public List<AttendanceLog> previousRecords;
    public List<Student> availableStudents;

    public AttendanceDisplayData() {}

    public AttendanceDisplayData(Date date, List<AttendanceLog> previousRecords, List<Student> availableStudents) {
        this.date = date;
        this.previousRecords = previousRecords;
        this.availableStudents = availableStudents;
    }
}