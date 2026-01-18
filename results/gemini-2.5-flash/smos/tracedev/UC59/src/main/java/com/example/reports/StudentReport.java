package com.example.reports;

import java.util.Date;
import java.util.List;
import java.util.Collections; // For unmodifiable list

/**
 * Represents a comprehensive report for a student, containing multiple report cards.
 */
public class StudentReport {
    private String reportId;
    private String studentId;
    private Date generationDate;
    private List<ReportCard> reportCards;

    /**
     * Constructs a new StudentReport.
     *
     * @param reportId       A unique identifier for this student report.
     * @param studentId      The ID of the student this report belongs to.
     * @param generationDate The date when the report was generated.
     * @param reportCards    A list of individual report cards included in this report.
     */
    public StudentReport(String reportId, String studentId, Date generationDate, List<ReportCard> reportCards) {
        this.reportId = reportId;
        this.studentId = studentId;
        this.generationDate = generationDate;
        // Defensive copy to ensure immutability of the list from external modifications
        this.reportCards = reportCards != null ? List.copyOf(reportCards) : Collections.emptyList();
    }

    /**
     * Gets the unique identifier for this student report.
     * @return The report ID.
     */
    public String getReportId() {
        return reportId;
    }

    /**
     * Gets the ID of the student this report belongs to.
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the date when the report was generated.
     * @return The generation date.
     */
    public Date getGenerationDate() {
        // Return a defensive copy to prevent external modification of the internal Date object
        return (Date) generationDate.clone();
    }

    /**
     * Gets an unmodifiable list of report cards included in this report.
     * @return A list of ReportCard objects.
     */
    public List<ReportCard> getReportCards() {
        return reportCards; // Already defensive copied in constructor
    }
}