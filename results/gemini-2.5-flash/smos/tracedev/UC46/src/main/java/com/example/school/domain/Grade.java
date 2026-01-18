package com.example.school.domain;

/**
 * Represents a single grade obtained by a student for a subject in a report card.
 */
public class Grade {
    public String id;
    public String subjectId;
    public double score;
    public String reportCardId; // Added for relationship tracking

    /**
     * Constructs a new Grade.
     * @param id The unique identifier for the grade entry.
     * @param subjectId The ID of the subject for which the grade is given.
     * @param score The score obtained for the subject.
     */
    public Grade(String id, String subjectId, double score) {
        this.id = id;
        this.subjectId = subjectId;
        this.score = score;
    }

    /**
     * Constructs a new Grade with report card ID.
     * @param id The unique identifier for the grade entry.
     * @param subjectId The ID of the subject for which the grade is given.
     * @param score The score obtained for the subject.
     * @param reportCardId The ID of the report card this grade belongs to.
     */
    public Grade(String id, String subjectId, double score, String reportCardId) {
        this(id, subjectId, score);
        this.reportCardId = reportCardId;
    }

    public String getId() {
        return id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public double getScore() {
        return score;
    }

    public String getReportCardId() {
        return reportCardId;
    }

    /**
     * Validates if the grade data is sensible.
     * @return true if the grade is valid (e.g., score within a reasonable range), false otherwise.
     */
    public boolean isValid() {
        // Example validation: score should be between 0 and 100
        return score >= 0 && score <= 100;
    }
}