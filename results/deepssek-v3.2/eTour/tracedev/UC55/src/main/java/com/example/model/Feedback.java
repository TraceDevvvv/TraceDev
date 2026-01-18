package com.example.model;

import java.util.Date;

/**
 * Domain model for Feedback.
 */
public class Feedback {
    private String id;
    private String siteId;
    private String userId;
    private String content;
    private Date createdAt;

    public Feedback(String id, String siteId, String userId, String content, Date createdAt) {
        this.id = id;
        this.siteId = siteId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}