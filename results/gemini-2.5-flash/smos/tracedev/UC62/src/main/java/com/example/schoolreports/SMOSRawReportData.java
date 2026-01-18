package com.example.schoolreports;

/**
 * Represents raw report summary data received directly from the SMOS system.
 * This is a data transfer object (DTO) specific to the external system.
 */
public class SMOSRawReportData {
    private String rawStudentId;
    private String rawStudentName;
    private String rawReportSummaryJson; // Represents a JSON string from SMOS

    /**
     * Constructs a new SMOSRawReportData instance.
     *
     * @param rawStudentId The student ID as provided by SMOS.
     * @param rawStudentName The student name as provided by SMOS.
     * @param rawReportSummaryJson A JSON string containing summary details of the report.
     */
    public SMOSRawReportData(String rawStudentId, String rawStudentName, String rawReportSummaryJson) {
        this.rawStudentId = rawStudentId;
        this.rawStudentName = rawStudentName;
        this.rawReportSummaryJson = rawReportSummaryJson;
    }

    // --- Getters ---
    public String getRawStudentId() {
        return rawStudentId;
    }

    public String getRawStudentName() {
        return rawStudentName;
    }

    public String getRawReportSummaryJson() {
        return rawReportSummaryJson;
    }
}