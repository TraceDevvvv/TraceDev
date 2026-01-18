package com.example.app.dto;

import com.example.app.DTO;
import com.example.app.domain.JustificationStatus;

/**
 * DTO for displaying justification information.
 */
public class JustificationDTO implements DTO {
    public String id;
    public String studentName;
    public String reason;
    public JustificationStatus status;

    /**
     * Constructs a new JustificationDTO.
     * @param id The ID of the justification.
     * @param studentName The name of the student who provided the justification.
     * @param reason The reason for the justification.
     * @param status The status of the justification.
     */
    public JustificationDTO(String id, String studentName, String reason, JustificationStatus status) {
        this.id = id;
        this.studentName = studentName;
        this.reason = reason;
        this.status = status;
    }

    // Getters for public fields
    public String getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getReason() {
        return reason;
    }

    public JustificationStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("  Justification ID: %s, Student: %s, Reason: '%s', Status: %s",
                id, studentName, reason, status);
    }
}