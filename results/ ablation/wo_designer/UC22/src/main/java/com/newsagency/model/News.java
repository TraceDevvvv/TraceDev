package com.newsagency.model;

import java.time.LocalDateTime;

/**
 * Represents a News entity in the system.
 */
public class News {
    private int id;
    private String title;
    private String content;
    private LocalDateTime publicationDate;
    private boolean active;

    public News() {}

    public News(int id, String title, String content, LocalDateTime publicationDate, boolean active) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publicationDate = publicationDate;
        this.active = active;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "News [id=" + id + ", title=" + title + ", publicationDate=" + publicationDate + ", active=" + active + "]";
    }
}