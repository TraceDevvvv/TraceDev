package com.example.adapters;

/**
 * Example implementation of FeedbackForm for web.
 */
public class WebFeedbackForm implements FeedbackForm {
    private String siteId;
    private int vote;
    private String comment;

    public WebFeedbackForm(String siteId, int vote, String comment) {
        this.siteId = siteId;
        this.vote = vote;
        this.comment = comment;
    }

    @Override
    public String getSiteId() {
        return siteId;
    }

    @Override
    public int getVote() {
        return vote;
    }

    @Override
    public String getComment() {
        return comment;
    }
}