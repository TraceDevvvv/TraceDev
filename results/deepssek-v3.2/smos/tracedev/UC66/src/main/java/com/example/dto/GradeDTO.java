package com.example.dto;

import java.util.Objects;

/**
 * Data Transfer Object for a single grade.
 */
public class GradeDTO {
    private final String subject;
    private final String grade;
    private final String comments;

    public GradeDTO(String subject, String grade, String comments) {
        this.subject = Objects.requireNonNull(subject);
        this.grade = Objects.requireNonNull(grade);
        this.comments = Objects.requireNonNull(comments);
    }

    public String getSubject() {
        return subject;
    }

    public String getGrade() {
        return grade;
    }

    public String getComments() {
        return comments;
    }

    /**
     * Simple validation for this grade DTO.
     * @return true if subject and grade are non‑empty
     */
    public boolean validate() {
        return !subject.trim().isEmpty() && !grade.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "GradeDTO{" +
                "subject='" + subject + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}