package com.example.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents an academic year (e.g., 2023–2024).
 */
public class AcademicYear {
    private final String yearId;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs an AcademicYear.
     * @param yearId unique identifier
     * @param startDate start date of the academic year
     * @param endDate end date of the academic year
     */
    public AcademicYear(String yearId, LocalDate startDate, LocalDate endDate) {
        this.yearId = Objects.requireNonNull(yearId);
        this.startDate = Objects.requireNonNull(startDate);
        this.endDate = Objects.requireNonNull(endDate);
    }

    public String getYearId() {
        return yearId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Checks whether this academic year is currently active.
     * An academic year is active if today's date is between startDate (inclusive)
     * and endDate (inclusive).
     * @return true if active, false otherwise.
     */
    public boolean isActive() {
        LocalDate today = LocalDate.now();
        return !today.isBefore(startDate) && !today.isAfter(endDate);
    }

    @Override
    public String toString() {
        return "AcademicYear{" +
                "yearId='" + yearId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}