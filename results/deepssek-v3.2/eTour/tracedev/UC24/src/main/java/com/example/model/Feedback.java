package com.example.model;

import java.util.Date;

/**
 * Entity class representing a feedback record.
 */
public class Feedback {
    private String id;
    private String siteId;
    private String content;
    private int rating;
    private Date timestamp;

    public Feedback(String id, String siteId, String content, int rating, Date timestamp) {
        this.id = id;
        this.siteId = siteId;
        this.content = content;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}