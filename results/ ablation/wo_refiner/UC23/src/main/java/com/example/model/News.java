package com.example.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a News item.
 * Implements business logic and validation as per domain requirements.
 */
public class News {
    private int id;
    private String title;
    private String content;
    private String author;
    private Date publishDate;
    private Date lastModified;

    public News(int id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.publishDate = new Date(); // set current date as publish date
        this.lastModified = new Date();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        updateLastModified();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        updateLastModified();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        updateLastModified();
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Updates the lastModified timestamp to current time.
     */
    public void updateLastModified() {
        this.lastModified = new Date();
    }

    /**
     * Validates the News entity.
     * Checks for non-empty title and content.
     * @return ValidationResult containing validation status and errors.
     */
    public ValidationResult validate() {
        List<String> errors = new ArrayList<>();
        if (title == null || title.trim().isEmpty()) {
            errors.add("Title cannot be empty.");
        }
        if (content == null || content.trim().isEmpty()) {
            errors.add("Content cannot be empty.");
        }
        boolean isValid = errors.isEmpty();
        return new ValidationResult(isValid, errors);
    }

    /**
     * Validates if the given operatorId matches the author.
     * For simplicity, we assume author field stores the operatorId.
     * @param operatorId the operator ID to check
     * @return true if operatorId matches author, false otherwise.
     */
    public boolean validateAuthorRole(String operatorId) {
        return author != null && author.equals(operatorId);
    }
}