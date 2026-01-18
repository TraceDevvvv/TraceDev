package com.example.dto;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.model.ValidationResult;

/**
 * Data Transfer Object for News.
 * Used for transferring data between layers.
 */
public class NewsDTO {
    private int id;
    private String title;
    private String content;
    private String author;
    private Date publishDate;
    private Date lastModified;

    public NewsDTO() {
        // default constructor
    }

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
     * Validates the DTO fields.
     * Similar to entity validation but without business rules.
     * @return ValidationResult
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
}