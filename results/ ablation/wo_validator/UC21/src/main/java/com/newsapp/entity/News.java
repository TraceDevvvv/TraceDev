package com.newsapp.entity;

import java.util.Date;

/**
 * Entity class representing a news article.
 * Corresponds to the News entity in the class diagram.
 */
public class News {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Date publicationDate;
    private String status;

    /**
     * Constructor as per class diagram.
     */
    public News(String title, String content, String author, Date publicationDate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.publicationDate = publicationDate;
        this.status = "DRAFT"; // default status
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Validate the news entity.
     * @return true if valid, false otherwise.
     */
    public boolean validate() {
        // Basic validation: title and content should not be empty
        if (title == null || title.trim().isEmpty()) {
            return false;
        }
        if (content == null || content.trim().isEmpty()) {
            return false;
        }
        if (author == null || author.trim().isEmpty()) {
            return false;
        }
        if (publicationDate == null) {
            return false;
        }
        return true;
    }
}