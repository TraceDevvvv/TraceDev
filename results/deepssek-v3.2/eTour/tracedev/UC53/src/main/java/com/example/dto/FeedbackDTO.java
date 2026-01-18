package com.example.dto;

/**
 * Data Transfer Object for Feedback data.
 */
public class FeedbackDTO {
    private String touristId;
    private String siteId;
    private int vote;
    private String comment;

    public FeedbackDTO() {
    }

    public FeedbackDTO(String touristId, String siteId, int vote, String comment) {
        this.touristId = touristId;
        this.siteId = siteId;
        this.vote = vote;
        this.comment = comment;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}