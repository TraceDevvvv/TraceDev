package com.example.school.domain;

import java.util.Date;

/**
 * Represents an academic year in the school system.
 */
public class AcademicYear {
    public String id;
    public int year;
    public Date startDate;
    public Date endDate;

    /**
     * Constructs a new AcademicYear.
     * @param id The unique identifier for the academic year.
     * @param year The year number (e.g., 2023 for 2023-2024).
     * @param startDate The start date of the academic year.
     * @param endDate The end date of the academic year.
     */
    public AcademicYear(String id, int year, Date startDate, Date endDate) {
        this.id = id;
        this.year = year;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}