package com.example.dto;

import java.util.Date;

/**
 * Data Transfer Object for absence notification.
 */
public class AbsenceNotificationDTO {
    public String parentEmail;
    public String studentName;
    public Date date;
    public String message;

    public AbsenceNotificationDTO() {}

    public AbsenceNotificationDTO(String parentEmail, String studentName, Date date, String message) {
        this.parentEmail = parentEmail;
        this.studentName = studentName;
        this.date = date;
        this.message = message;
    }

    /**
     * Generates a message for the notification.
     * If message is not set, creates a default one.
     */
    public String generateMessage() {
        if (this.message != null && !this.message.isEmpty()) {
            return this.message;
        }
        return String.format("Dear Parent, your child %s was absent on %s.", studentName, date);
    }
}