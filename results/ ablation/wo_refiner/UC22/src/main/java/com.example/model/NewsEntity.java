package com.example.model;

import java.util.Date;

/**
 * Represents a news article entity in the system.
 */
public class NewsEntity {
    private int id;
    private String title;
    private String content;
    private Date createdDate;
    private Date lastModified;

    public NewsEntity(int id, String title, String content, Date createdDate, Date lastModified) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModified() {
        return lastModified;
    }
}