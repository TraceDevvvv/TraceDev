package com.example;

import java.util.Date;

/**
 * Domain entity representing user feedback.
 * Stereotype: Entity
 */
public class Feedback {
    private String id;
    private String siteId;
    private String content;
    private Date creationDate;

    // Assuming ID generation is handled elsewhere, we'll generate a simple UUID for demo.
    public Feedback(String siteId, String content) {
        this.id = java.util.UUID.randomUUID().toString();
        this.siteId = siteId;
        this.content = content;
        this.creationDate = new Date(); // Current date/time
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

    public Date getCreationDate() {
        return creationDate;
    }
}