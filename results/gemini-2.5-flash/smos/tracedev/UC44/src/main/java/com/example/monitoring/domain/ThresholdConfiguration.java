package com.example.monitoring.domain;

/**
 * Domain Layer: Represents a Value Object for threshold configurations.
 * This object holds specific values for monitoring thresholds (e.g., for absences, notes).
 * It is immutable, meaning its values are set at creation and cannot be changed.
 */
public class ThresholdConfiguration {
    private final int absenceThreshold;
    private final int notesThreshold;

    /**
     * Constructor for ThresholdConfiguration.
     * @param absenceThreshold The maximum allowed absences before a student is flagged.
     * @param notesThreshold The maximum allowed notes before a student is flagged.
     */
    public ThresholdConfiguration(int absenceThreshold, int notesThreshold) {
        // Basic validation can be added, e.g., thresholds must be non-negative.
        if (absenceThreshold < 0 || notesThreshold < 0) {
            throw new IllegalArgumentException("Thresholds cannot be negative.");
        }
        this.absenceThreshold = absenceThreshold;
        this.notesThreshold = notesThreshold;
    }

    /**
     * @return The configured absence threshold.
     */
    public int getAbsenceThreshold() {
        return absenceThreshold;
    }

    /**
     * @return The configured notes threshold.
     */
    public int getNotesThreshold() {
        return notesThreshold;
    }

    // As a Value Object, it should ideally override equals() and hashCode() for proper comparison.
    // This is omitted for brevity but is a good practice for Value Objects.
}