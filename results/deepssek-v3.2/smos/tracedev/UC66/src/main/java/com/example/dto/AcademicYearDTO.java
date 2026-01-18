package com.example.dto;

import java.util.Objects;

/**
 * Data Transfer Object for academic year, used by the view layer.
 */
public class AcademicYearDTO {
    private final String yearId;
    private final String displayName;

    public AcademicYearDTO(String yearId, String displayName) {
        this.yearId = Objects.requireNonNull(yearId);
        this.displayName = Objects.requireNonNull(displayName);
    }

    public String getYearId() {
        return yearId;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}