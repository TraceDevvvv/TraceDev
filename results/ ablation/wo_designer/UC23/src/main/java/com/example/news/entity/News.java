package com.example.news.entity;

import java.time.LocalDateTime;

/**
 * Represents a news article entity.
 */
public class News {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime publishDate;
    private LocalDateTime lastModified;

    // Constructors
    public News() {}

    public News(Long id, String title, String content, String author, LocalDateTime publishDate, LocalDateTime lastModified) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.publishDate = publishDate;
        this.lastModified = lastModified;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "News [id=" + id + ", title=" + title + ", author=" + author + ", publishDate=" + publishDate + "]";
    }
}