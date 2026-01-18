package com.newsagency.system.entity;

import java.util.Date;

/**
 * Entity representing a news article.
 */
public class News {
    private Long id;
    private String title;
    private String content;
    private Date createdAt;
    private String author;

    /**
     * Constructor used when creating a new news entity (without ID).
     * @param title   news title
     * @param content news content
     * @param author  author name
     */
    public News(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = new Date(); // set current date/time
    }

    // Full constructor for repository use (with ID)
    public News(Long id, String title, String content, Date createdAt, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
    }

    // Getters and setters
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}