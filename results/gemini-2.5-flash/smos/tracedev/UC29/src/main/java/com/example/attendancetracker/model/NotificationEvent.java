package com.example.attendancetracker.model;

import java.util.Date;
import java.util.UUID;

/**
 * Represents an event to notify a parent about a student's absence.
 */
public class NotificationEvent {
    private String eventId;
    private String studentId;
    private String parentEmail;
    private Date date;

    public NotificationEvent(String studentId, String parentEmail, Date date) {
        this.eventId = UUID.randomUUID().toString(); // Generate unique event ID
        this.studentId = studentId;
        this.parentEmail = parentEmail;
        this.date = date;
    }

    // Getters for private fields
    public String getEventId() {
        return eventId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "NotificationEvent{" +
               "eventId='" + eventId.substring(0,4) + '\'' +
               ", studentId='" + studentId + '\'' +
               ", parentEmail='" + parentEmail + '\'' +
               ", date=" + date.toInstant().toString().substring(0,10) +
               '}';
    }
}