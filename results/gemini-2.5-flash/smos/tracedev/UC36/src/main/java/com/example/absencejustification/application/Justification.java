package com.example.absencejustification.application;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a Justification entity in the domain model.
 * It holds details about an absence justification, including its unique ID,
 * the date of justification, and the associated absence ID.
 */
public class Justification {
    private final String id;
    private final LocalDate date;
    private final String absenceId;

    /**
     * Constructs a new Justification instance.
     * A unique ID is automatically generated for each justification.
     * @param date The date of the justification.
     * @param absenceId The ID of the absence this justification is for.
     */
    public Justification(LocalDate date, String absenceId) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID for the justification
        this.date = Objects.requireNonNull(date, "Justification date cannot be null.");
        this.absenceId = Objects.requireNonNull(absenceId, "Absence ID cannot be null.");
        System.out.println("[Justification] New Justification created. ID: " + id + ", Date: " + date + ", Absence ID: " + absenceId);
    }

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getAbsenceId() {
        return absenceId;
    }

    @Override
    public String toString() {
        return "Justification{" +
               "id='" + id + '\'' +
               ", date=" + date +
               ", absenceId='" + absenceId + '\'' +
               '}';
    }
}