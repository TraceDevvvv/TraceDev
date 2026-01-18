package com.example.model;

import java.util.Date;

/**
 * Represents a news article.
 */
public class News {
    private String id;
    private String title;
    private String content;
    private String author;
    private Date createdDate;
    private Date modifiedDate;

    /**
     * Constructor for News.
     * @param id The unique identifier.
     * @param title The title of the news.
     * @param content The content of the news.
     * @param author The author of the news.
     */
    public News(String id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdDate = new Date();
        this.modifiedDate = new Date();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        updateModifiedDate();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        updateModifiedDate();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        updateModifiedDate();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Updates the modified date to the current date and time.
     */
    public void updateModifiedDate() {
        this.modifiedDate = new Date();
    }
}