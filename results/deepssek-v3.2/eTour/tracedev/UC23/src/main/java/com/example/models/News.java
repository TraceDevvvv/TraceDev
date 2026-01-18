package com.example.models;

import java.util.Date;

/**
 * Represents a News entity.
 */
public class News {
    private Long id;
    private String title;
    private String content;
    private Date lastModified;

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

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date date) {
        this.lastModified = date;
    }
}