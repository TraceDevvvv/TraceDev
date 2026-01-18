package com.example.reportcard.domain;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Represents an academic year in the domain model.
 */
public class AcademicYear {
    public String id;
    public String year; // e.g., "2023-2024"
    public Date startDate;
    public Date endDate;

    /**
     * Constructs a new AcademicYear.
     *
     * @param id The unique identifier for the academic year.
     * @param year The year string (e.g., "2023-2024").
     * @param startDateString The start date of the academic year in "yyyy-MM-dd" format.
     * @param endDateString The end date of the academic year in "yyyy-MM-dd" format.
     */
    public AcademicYear(String id, String year, String startDateString, String endDateString) {
        this.id = id;
        this.year = year;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.startDate = formatter.parse(startDateString);
            this.endDate = formatter.parse(endDateString);
        } catch (ParseException e) {
            System.err.println("Error parsing date for AcademicYear " + id + ": " + e.getMessage());
            this.startDate = null;
            this.endDate = null;
        }
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "AcademicYear{" +
               "id='" + id + '\'' +
               ", year='" + year + '\'' +
               '}';
    }
}