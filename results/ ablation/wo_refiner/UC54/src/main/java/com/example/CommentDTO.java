package com.example;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Comment.
 */
public class CommentDTO {
    private String id;
    private String text;
    private LocalDateTime lastModified;

    public CommentDTO(String id, String text) {
        this.id = id;
        this.text = text;
        this.lastModified = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime timestamp) {
        this.lastModified = timestamp;
    }
}