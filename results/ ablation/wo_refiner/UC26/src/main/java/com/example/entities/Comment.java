package com.example.entities;

import java.time.LocalDateTime;

/**
 * Represents a comment entity.
 */
public class Comment {
    private int id;
    private String content;
    private int feedbackId;
    private LocalDateTime lastModified;

    public Comment(int id, String content, int feedbackId) {
        this.id = id;
        this.content = content;
        this.feedbackId = feedbackId;
        this.lastModified = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.lastModified = LocalDateTime.now();
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
}