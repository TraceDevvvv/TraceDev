package com.example;

/**
 * Data Transfer Object for Feedback.
 */
public class FeedbackDTO {
    private String id;
    private String comment;
    private String status;

    public FeedbackDTO(String id, String comment, String status) {
        this.id = id;
        this.comment = comment;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}