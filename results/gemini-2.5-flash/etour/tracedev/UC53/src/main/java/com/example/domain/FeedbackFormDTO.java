package com.example.domain;

/**
 * Data Transfer Object (DTO) for collecting feedback form input.
 * Used to transfer data from the Presentation Layer to the Application Layer.
 */
public class FeedbackFormDTO {
    public String siteId;
    public int vote;
    public String comment;

    // Constructor
    public FeedbackFormDTO(String siteId, int vote, String comment) {
        this.siteId = siteId;
        this.vote = vote;
        this.comment = comment;
    }

    // Getters for all attributes
    public String getSiteId() {
        return siteId;
    }

    public int getVote() {
        return vote;
    }

    public String getComment() {
        return comment;
    }

    // Setters (if mutable DTOs are preferred, though for input DTOs, immutability is often good)
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "FeedbackFormDTO{" +
               "siteId='" + siteId + '\'' +
               ", vote=" + vote +
               ", comment='" + comment + '\'' +
               '}';
    }
}