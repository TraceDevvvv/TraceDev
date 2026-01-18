package com.newsagency.model;

import java.util.Date;

/**
 * Entity class representing a news article in the system.
 */
public class News {
    private String newsId;
    private String title;
    private String content;
    private String author;
    private Date publicationDate;
    private String category;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    
    public News() {
        this.newsId = java.util.UUID.randomUUID().toString();
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.status = "DRAFT";
    }
    
    public String getNewsId() {
        return newsId;
    }
    
    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = new Date();
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
        this.updatedAt = new Date();
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
        this.updatedAt = new Date();
    }
    
    public Date getPublicationDate() {
        return publicationDate;
    }
    
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
        this.updatedAt = new Date();
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
        this.updatedAt = new Date();
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = new Date();
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    /**
     * Publishes the news article by changing its status to PUBLISHED.
     */
    public void publish() {
        this.status = "PUBLISHED";
        this.publicationDate = new Date();
        this.updatedAt = new Date();
    }
}