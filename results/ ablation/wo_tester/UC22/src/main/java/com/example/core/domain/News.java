package com.example.core.domain;

import java.time.LocalDateTime;

/**
 * Represents a News entity in the system.
 */
public class News {
    private final String id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    /**
     * Constructor for News.
     *
     * @param id        Unique identifier
     * @param title     Title of the news
     * @param content   Content of the news
     * @param createdAt Creation timestamp
     */
    public News(String id, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}