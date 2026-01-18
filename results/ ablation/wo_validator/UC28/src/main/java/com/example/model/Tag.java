package com.example.model;

import java.time.LocalDateTime;

/**
 * Represents a Tag entity.
 */
public class Tag {
    private String id;
    private String name;
    private String description;
    private LocalDateTime createdAt;

    // Constructors
    public Tag() {
        this.createdAt = LocalDateTime.now();
    }

    public Tag(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Deletes this tag (simulates deletion logic).
     * In a real application, this might set a deleted flag or perform cleanup.
     */
    public void delete() {
        System.out.println("Tag with ID " + id + " is marked for deletion.");
        // Additional cleanup logic can be added here
    }
}