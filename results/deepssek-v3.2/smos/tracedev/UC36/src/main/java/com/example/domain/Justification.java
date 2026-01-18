package com.example.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Domain entity representing a Justification for an absence.
 */
public class Justification {
    private UUID id;
    private LocalDate date;
    private String reason;
    private UUID absenceId;
    private LocalDateTime createdDate;

    public Justification(LocalDate date, String reason, UUID absenceId) {
        this.id = UUID.randomUUID();
        this.date = date;
        this.reason = reason;
        this.absenceId = absenceId;
        this.createdDate = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }

    public UUID getAbsenceId() {
        return absenceId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Validates the justification data.
     * Assumption: reason must not be empty, date must not be in the future.
     */
    public boolean validate() {
        if (reason == null || reason.trim().isEmpty()) {
            return false;
        }
        if (date == null || date.isAfter(LocalDate.now())) {
            return false;
        }
        return true;
    }
}