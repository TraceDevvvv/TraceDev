package com.example.domain;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Domain entity representing an Absence.
 */
public class Absence {
    private UUID id;
    private UUID employeeId;
    private LocalDate date;
    private String status;

    public Absence(UUID id, UUID employeeId, LocalDate date, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    /**
     * Checks if absence is pending (status is "pending").
     * Assumption: pending status is represented by the string "pending".
     */
    public boolean isPending() {
        return "pending".equalsIgnoreCase(status);
    }
}