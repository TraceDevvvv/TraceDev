package com.example.schoolreports;

/**
 * Represents raw detailed report card data received directly from the SMOS system.
 * This is a data transfer object (DTO) specific to the external system.
 */
public class SMOSRawReportCardData {
    private String rawReportCardId;
    private String rawStudentId;
    private String rawReportDetailsJson; // Represents a JSON string from SMOS

    /**
     * Constructs a new SMOSRawReportCardData instance.
     *
     * @param rawReportCardId The report card ID as provided by SMOS.
     * @param rawStudentId The student ID associated with this report card.
     * @param rawReportDetailsJson A JSON string containing detailed information of the report card.
     */
    public SMOSRawReportCardData(String rawReportCardId, String rawStudentId, String rawReportDetailsJson) {
        this.rawReportCardId = rawReportCardId;
        this.rawStudentId = rawStudentId;
        this.rawReportDetailsJson = rawReportDetailsJson;
    }

    // --- Getters ---
    public String getRawReportCardId() {
        return rawReportCardId;
    }

    public String getRawStudentId() {
        return rawStudentId;
    }

    public String getRawReportDetailsJson() {
        return rawReportDetailsJson;
    }
}