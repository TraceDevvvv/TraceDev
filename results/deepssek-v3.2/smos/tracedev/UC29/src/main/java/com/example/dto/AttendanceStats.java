package com.example.dto;

/**
 * Simple statistics for attendance.
 * This class is referenced by AttendanceRecord.calculateStats().
 */
public class AttendanceStats {
    private int totalStudents;
    private int presentCount;
    private int absentCount;

    public AttendanceStats(int totalStudents, int presentCount, int absentCount) {
        this.totalStudents = totalStudents;
        this.presentCount = presentCount;
        this.absentCount = absentCount;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public int getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(int presentCount) {
        this.presentCount = presentCount;
    }

    public int getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(int absentCount) {
        this.absentCount = absentCount;
    }
}