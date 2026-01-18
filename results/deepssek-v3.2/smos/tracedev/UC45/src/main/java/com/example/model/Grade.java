package com.example.model;

/**
 * Entity representing a grade for a subject.
 */
public class Grade {
    private String subject;
    private double score;
    private String comment;

    public Grade() {}

    public Grade(String subject, double score, String comment) {
        this.subject = subject;
        this.score = score;
        this.comment = comment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}