package com.example.reports;

/**
 * Represents a single report card entry within a student report,
 * typically for a specific subject and grade.
 */
public class ReportCard {
    private String cardId;
    private String subject;
    private String grade;
    private String comments;

    /**
     * Constructs a new ReportCard.
     *
     * @param cardId   A unique identifier for the report card.
     * @param subject  The subject name (e.g., "Mathematics", "Science").
     * @param grade    The grade received (e.g., "A", "B+", "Pass").
     * @param comments Any additional comments for the subject.
     */
    public ReportCard(String cardId, String subject, String grade, String comments) {
        this.cardId = cardId;
        this.subject = subject;
        this.grade = grade;
        this.comments = comments;
    }

    /**
     * Gets the unique identifier for this report card.
     * @return The card ID.
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * Gets the subject name for this report card.
     * @return The subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Gets the grade received for the subject.
     * @return The grade.
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Gets any additional comments for the subject.
     * @return The comments.
     */
    public String getComments() {
        return comments;
    }
}