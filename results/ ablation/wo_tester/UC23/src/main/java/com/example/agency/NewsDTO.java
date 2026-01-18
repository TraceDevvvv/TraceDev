package com.example.agency;

import java.util.Date;

/**
 * Data Transfer Object for News entity.
 */
public class NewsDTO {
    private int id;
    private String title;
    private String content;
    private String author;
    private Date publishDate;

    public NewsDTO() {}

    public NewsDTO(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.content = news.getContent();
        this.author = news.getAuthor();
        this.publishDate = news.getPublishDate();
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

    /**
     * Converts DTO back to News entity.
     */
    public News toNews() {
        News news = new News();
        news.setId(this.id);
        news.setTitle(this.title);
        news.setContent(this.content);
        news.setAuthor(this.author);
        news.setPublishDate(this.publishDate);
        news.updateLastModified();
        return news;
    }
}