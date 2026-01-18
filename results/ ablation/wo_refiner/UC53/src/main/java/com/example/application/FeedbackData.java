package com.example.application;

/**
 * Input data for feedback submission (use case layer).
 */
public class FeedbackData {
    private String siteId;
    private int vote;
    private String comment;

    public FeedbackData(String siteId, int vote, String comment) {
        this.siteId = siteId;
        this.vote = vote;
        this.comment = comment;
    }

    public String getSiteId() {
        return siteId;
    }

    public int getVote() {
        return vote;
    }

    public String getComment() {
        return comment;
    }
}