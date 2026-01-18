package com.example.domain;

import java.time.LocalDateTime;

/**
 * Domain entity representing a Teaching.
 * Contains id, title, description, and schedule.
 */
public class Teaching {
    private final String id;
    private String title;
    private String description;
    private LocalDateTime schedule;

    public Teaching(String id, String title, String description, LocalDateTime schedule) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.schedule = schedule;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getSchedule() {
        return schedule;
    }

    public void setSchedule(LocalDateTime schedule) {
        this.schedule = schedule;
    }

    /**
     * Validates the teaching entity.
     * This is a simple validation; could be extended with business rules.
     */
    public boolean isValid() {
        return id != null && !id.trim().isEmpty() &&
               title != null && !title.trim().isEmpty() &&
               schedule != null;
    }
}