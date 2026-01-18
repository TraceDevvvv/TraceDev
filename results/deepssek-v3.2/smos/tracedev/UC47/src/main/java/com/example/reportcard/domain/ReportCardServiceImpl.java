package com.example.reportcard.domain;

import java.util.Map;

/**
 * Implementation of ReportCardService enforcing data integrity.
 */
public class ReportCardServiceImpl implements ReportCardService {
    @Override
    public ReportCard validateAndUpdate(ReportCard reportCard, ReportCardDTO modifiedData) {
        // Validate modified data grades are within 0–100 range
        for (int grade : modifiedData.getSubjectGrades().values()) {
            if (grade < 0 || grade > 100) {
                throw new IllegalArgumentException("Invalid grade values");
            }
        }
        // Update the report card with new grades
        for (Map.Entry<String, Integer> entry : modifiedData.getSubjectGrades().entrySet()) {
            reportCard.modifyGrade(entry.getKey(), entry.getValue());
        }
        return reportCard;
    }
    
    public ReportCard validateDataIntegrity(ReportCard reportCard) {
        // ensure grades are within range, etc.
        if (reportCard.validate()) {
            return reportCard;
        } else {
            throw new IllegalArgumentException("Data integrity validation failed");
        }
    }
}