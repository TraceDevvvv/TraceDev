package com.example.application;

import com.example.domain.AbsenceStatus;

/**
 * Command object for changing an absence.
 */
public class ChangeAbsenceCommand {
    private String absenceId;
    private AbsenceStatus newStatus;
    private String reason;

    public ChangeAbsenceCommand(String absenceId, AbsenceStatus newStatus, String reason) {
        this.absenceId = absenceId;
        this.newStatus = newStatus;
        this.reason = reason;
    }

    public String getAbsenceId() {
        return absenceId;
    }

    public AbsenceStatus getNewStatus() {
        return newStatus;
    }

    public String getReason() {
        return reason;
    }
}