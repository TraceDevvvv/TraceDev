package com.example.domain.entities;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a Tag entity in the system.
 */
public class Tag {
    private String id;
    private String name;
    private String description;
    private Date createdAt;
    private boolean isNotified; // Added to satisfy requirement: Exit Condition - ETOUR connection interrupted

    public Tag(String id, String name, String description, Date createdAt, boolean isNotified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.isNotified = isNotified;
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isNotified() {
        return isNotified;
    }

    public void setNotified(boolean notified) {
        isNotified = notified;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Tag tag = (Tag) other;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}