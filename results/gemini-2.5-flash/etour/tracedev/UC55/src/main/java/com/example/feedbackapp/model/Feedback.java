package com.example.feedbackapp.model;

/**
 * Represents a Feedback entity.
 * This is a simple Plain Old Java Object (POJO) class.
 */
public class Feedback {
    private String id;
    private String siteId;
    private String content;

    /**
     * Constructs a new Feedback instance.
     *
     * @param id The unique identifier for the feedback.
     * @param siteId The ID of the site this feedback is for.
     * @param content The actual content of the feedback.
     */
    public Feedback(String id, String siteId, String content) {
        this.id = id;
        this.siteId = siteId;
        this.content = content;
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getContent() {
        return content;
    }

    // --- Setters (optional, depending on immutability needs) ---
    public void setId(String id) {
        this.id = id;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Feedback{" +
               "id='" + id + '\'' +
               ", siteId='" + siteId + '\'' +
               ", content='" + content + '\'' +
               '}';
    }
}