package com.example.agency;

import java.util.Date;

/**
 * Entity representing a news article.
 */
public class News {
    private int id;
    private String title;
    private String content;
    private String author;
    private Date publishDate;
    private Date lastModified;

    public News() {}

    public News(int id, String title, String content, String author, Date publishDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.publishDate = publishDate;
        this.lastModified = publishDate;
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

    public void updateLastModified() {
        this.lastModified = new Date();
    }
}