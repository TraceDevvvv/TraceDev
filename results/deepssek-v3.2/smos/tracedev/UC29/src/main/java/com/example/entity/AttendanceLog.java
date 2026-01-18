package com.example.entity;

/**
 * Log entry for an attendance record.
 */
public class AttendanceLog {
    private String logId;
    private AttendanceRecord record;
    private String displayData;

    public AttendanceLog() {}

    public AttendanceLog(String logId, AttendanceRecord record, String displayData) {
        this.logId = logId;
        this.record = record;
        this.displayData = displayData;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public AttendanceRecord getRecord() {
        return record;
    }

    public void setRecord(AttendanceRecord record) {
        this.record = record;
    }

    public String getDisplayData() {
        return displayData;
    }

    public void setDisplayData(String displayData) {
        this.displayData = displayData;
    }

    /**
     * Returns a formatted log string.
     */
    public String getFormattedLog() {
        if (displayData != null && !displayData.isEmpty()) {
            return displayData;
        }
        return String.format("Log %s: Attendance record for %s", logId,
                record != null && record.getDate() != null ? record.getDate().toString() : "unknown date");
    }
}