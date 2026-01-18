package com.example;

import java.util.Date;

/**
 * Entity representing a feedback entry.
 */
public class Feedback {
    private String id;
    private String siteId;
    private String comment;
    private String status;
    private Date createdAt;
    private Date modifiedAt;

    public Feedback(String id, String siteId, String comment, String status) {
        this.id = id;
        this.siteId = siteId;
        this.comment = comment;
        this.status = status;
        this.createdAt = new Date();
        this.modifiedAt = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void updateComment(String newComment) {
        this.comment = newComment;
        this.modifiedAt = new Date();
    }

    public boolean isValidComment() {
        return comment != null && !comment.trim().isEmpty();
    }
}