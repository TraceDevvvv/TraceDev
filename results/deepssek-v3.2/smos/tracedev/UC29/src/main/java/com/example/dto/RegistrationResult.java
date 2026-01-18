package com.example.dto;

import com.example.entity.AttendanceRecord;

/**
 * Result of an attendance registration attempt.
 */
public class RegistrationResult {
    public boolean success;
    public String message;
    public AttendanceRecord savedRecord;
    public int notificationsSent;

    public RegistrationResult() {}

    public RegistrationResult(boolean success, String message, AttendanceRecord savedRecord, int notificationsSent) {
        this.success = success;
        this.message = message;
        this.savedRecord = savedRecord;
        this.notificationsSent = notificationsSent;
    }
}