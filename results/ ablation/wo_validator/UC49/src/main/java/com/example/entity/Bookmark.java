package com.example.entity;

import java.util.Date;

/**
 * Entity class representing a Bookmark.
 */
public class Bookmark {
    private String id;
    private String name;
    private String url;
    private Date createdDate;

    /**
     * Constructor for Bookmark.
     * @param id the bookmark ID
     * @param name the bookmark name
     * @param url the bookmark URL
     * @param createdDate the creation date
     */
    public Bookmark(String id, String name, String url, Date createdDate) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}