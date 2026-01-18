package com.etour.tags;

import java.time.LocalDateTime;

/**
 * Represents a tag entity.
 */
public class Tag {
    private String id;
    private String name;
    private LocalDateTime createdAt;

    public Tag(String id, String name, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}