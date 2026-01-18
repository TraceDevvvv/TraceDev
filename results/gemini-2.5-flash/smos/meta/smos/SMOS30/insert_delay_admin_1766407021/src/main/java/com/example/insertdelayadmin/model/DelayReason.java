package com.example.insertdelayadmin.model;

/**
 * Enum representing the possible reasons for a student's delay.
 * This provides a standardized set of reasons for tracking and reporting.
 */
public enum DelayReason {
    /**
     * Student was delayed due to a late bus.
     */
    LATE_BUS,
    /**
     * Student was delayed due to an appointment (e.g., doctor, dentist).
     */
    APPOINTMENT,
    /**
     * Student was delayed due to sickness.
     */
    SICKNESS,
    /**
     * Student was delayed for a reason not covered by the other categories.
     */
    OTHER
}