package com.feedback.management;

import java.util.Date;

public class Feedback {
    private String id;
    private String siteId;
    private String userId;
    private String content;
    private Date timestamp;

    public Feedback(String id, String siteId, String userId, String content, Date timestamp) {
        this.id = id;
        this.siteId = siteId;
        this.userId = userId;
        this.content = content;
        this.timestamp = timestamp;
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

    public Date getTimestamp() {
        return timestamp;
    }
}