package com.example.model;

import java.util.Objects;

/**
 * Represents a teaching (subject/course) that can be associated with an address.
 * Based on the UML class diagram.
 */
public class Teaching {
    private String teachingId;
    private String code;
    private String title;
    private String description;

    public Teaching(String teachingId, String code, String title, String description) {
        this.teachingId = teachingId;
        this.code = code;
        this.title = title;
        this.description = description;
    }

    public String getTeachingId() {
        return teachingId;
    }

    public String getDetails() {
        return code + ": " + title + " - " + description;
    }

    // Additional getters and setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public void setTeachingId(String teachingId) { this.teachingId = teachingId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teaching teaching = (Teaching) o;
        return Objects.equals(teachingId, teaching.teachingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teachingId);
    }
}