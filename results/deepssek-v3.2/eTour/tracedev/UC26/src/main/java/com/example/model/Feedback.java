package com.example.model;

/**
 * Represents a Feedback entity.
 */
public class Feedback {
    private String id;
    private String siteId;
    private String comment;

    // Constructor
    public Feedback(String id, String siteId, String comment) {
        this.id = id;
        this.siteId = siteId;
        this.comment = comment;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getComment() {
        return comment;
    }

    /**
     * Updates the comment of this feedback.
     * @param newComment the new comment text
     */
    public void updateComment(String newComment) {
        this.comment = newComment;
    }
}