package com.newsagency.dto;

import com.newsagency.model.News;
import java.util.Date;

/**
 * Data Transfer Object for News information.
 * Converts to News entity when needed.
 */
public class NewsDTO {
    private String title;
    private String content;
    private String author;
    private Date publicationDate;
    private String category;
    private String status;
    
    public NewsDTO() {
        this.publicationDate = new Date();
        this.status = "DRAFT";
    }
    
    public NewsDTO(String title, String content, String author, String category) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.publicationDate = new Date();
        this.status = "DRAFT";
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
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * Converts this DTO to a News entity.
     * @return News entity with data from this DTO
     */
    public News toNews() {
        News news = new News();
        news.setTitle(this.title);
        news.setContent(this.content);
        news.setAuthor(this.author);
        news.setPublicationDate(this.publicationDate);
        news.setCategory(this.category);
        news.setStatus(this.status);
        return news;
    }
}