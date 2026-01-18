package com.example.presentation.dto;

import java.util.UUID;

/**
 * Data Transfer Object for inserting a justification via REST API.
 */
public class InsertJustificationDTO {
    private String date;
    private String reason;
    private UUID absenceId;

    public InsertJustificationDTO(String date, String reason, UUID absenceId) {
        this.date = date;
        this.reason = reason;
        this.absenceId = absenceId;
    }

    public String getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }

    public UUID getAbsenceId() {
        return absenceId;
    }
}