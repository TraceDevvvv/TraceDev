package com.example.application.command;

import java.util.UUID;

/**
 * Command object for the insert justification use case.
 */
public class InsertJustificationCommand {
    private String date;
    private String reason;
    private UUID absenceId;
    private UUID administratorId;

    public InsertJustificationCommand(String date, String reason, UUID absenceId, UUID administratorId) {
        this.date = date;
        this.reason = reason;
        this.absenceId = absenceId;
        this.administratorId = administratorId;
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

    public UUID getAdministratorId() {
        return administratorId;
    }
}