package com.example.model;

import java.util.List;

/**
 * Represents a student entity with attendance records.
 * Mapped from Student in UML class diagram.
 */
public class Student {
    private String studentId;
    private String studentName;
    private List<Attendance> attendanceRecords;

    public Student(String studentId, String studentName, List<Attendance> attendanceRecords) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.attendanceRecords = attendanceRecords;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<Attendance> getAttendanceRecords() {
        return attendanceRecords;
    }

    public void setAttendanceRecords(List<Attendance> attendanceRecords) {
        this.attendanceRecords = attendanceRecords;
    }
}