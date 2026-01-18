package com.etour.domain;

/**
 * Domain entity representing feedback for a site, linked to a comment.
 */
public class Feedback {
    private String id;
    private int rating;
    private String commentId;
    private String siteId;
    private String touristId;

    public Feedback(String id, int rating, String commentId, String siteId, String touristId) {
        this.id = id;
        this.rating = rating;
        this.commentId = commentId;
        this.siteId = siteId;
        this.touristId = touristId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }
}