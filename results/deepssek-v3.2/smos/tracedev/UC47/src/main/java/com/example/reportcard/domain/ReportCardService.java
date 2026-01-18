package com.example.reportcard.domain;

/**
 * Service interface for data integrity operations during report card updates.
 */
public interface ReportCardService {
    /**
     * Validates the modified data and updates the report card.
     * Stereotype: <<data integrity>>.
     */
    ReportCard validateAndUpdate(ReportCard reportCard, ReportCardDTO modifiedData);
    
    /**
     * Validates data integrity (ensure grades are within range, etc.)
     */
    ReportCard validateDataIntegrity(ReportCard reportCard);
}