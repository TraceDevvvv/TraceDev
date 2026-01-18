package com.example.domain;

import java.util.Objects;

/**
 * Represents a semester within an academic year.
 */
public class Semester {
    private final String semesterId;
    private final int semesterNumber;
    private final AcademicYear academicYear;

    public Semester(String semesterId, int semesterNumber, AcademicYear academicYear) {
        this.semesterId = Objects.requireNonNull(semesterId);
        this.semesterNumber = semesterNumber;
        this.academicYear = Objects.requireNonNull(academicYear);
    }

    public String getSemesterId() {
        return semesterId;
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    /**
     * Returns a descriptive name for this semester.
     * @return a string like "Semester 1 2023‑2024"
     */
    public String getName() {
        return String.format("Semester %d (%s)", semesterNumber, academicYear.getYearId());
    }

    @Override
    public String toString() {
        return "Semester{" +
                "semesterId='" + semesterId + '\'' +
                ", name='" + getName() + '\'' +
                '}';
    }
}