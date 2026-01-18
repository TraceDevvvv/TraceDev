package com.example.entity;

import com.example.dto.AbsenceNotificationDTO;
import java.util.Date;

/**
 * Represents a student who is absent on a given date.
 */
public class AbsentStudent {
    private String studentId;
    private Date date;
    private String reason;

    public AbsentStudent() {}

    public AbsentStudent(String studentId, Date date, String reason) {
        this.studentId = studentId;
        this.date = date;
        this.reason = reason;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Converts this absent student to a notification DTO.
     * In a real application, you would need Student details (name, parent email) to populate.
     */
    public AbsenceNotificationDTO toNotificationData() {
        // This is a simplified conversion. Normally you would fetch student details.
        AbsenceNotificationDTO dto = new AbsenceNotificationDTO();
        dto.date = this.date;
        dto.message = "Your child was absent. Reason: " + (reason != null ? reason : "Not specified");
        return dto;
    }
}