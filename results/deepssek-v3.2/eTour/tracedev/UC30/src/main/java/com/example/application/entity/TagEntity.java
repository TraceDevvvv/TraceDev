package com.example.application.entity;

import java.time.LocalDateTime;

/**
 * Tag entity representing a tag in the system.
 */
public class TagEntity {
    private Long id;
    private String tag;
    private LocalDateTime createdAt;

    // Constructor as specified in class diagram
    public TagEntity(String tag, String operatorId, LocalDateTime timestamp) {
        this.tag = tag;
        this.createdAt = timestamp;
        // operatorId can be stored as a separate field if needed,
        // but class diagram only shows tag and createdAt.
        // For simplicity, we assume operatorId is not stored in entity.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}