package com.newsapp.dto;

import java.util.Date;

/**
 * Data Transfer Object for news form data.
 * Corresponds to the NewsFormData DTO in the class diagram.
 */
public class NewsFormData {
    public String title;
    public String content;
    public String author;
    public Date publicationDate;

    // Default constructor
    public NewsFormData() {}

    // Parameterized constructor
    public NewsFormData(String title, String content, String author, Date publicationDate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    // Getters and Setters
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
}