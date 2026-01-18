package com.example.dto;

import java.util.Date;

/**
 * Data Transfer Object for Bookmark.
 */
public class BookmarkDTO {
    private String id;
    private String name;
    private String url;
    private Date createdDate;
    
    /**
     * Default constructor.
     */
    public BookmarkDTO() {
    }
    
    /**
     * Constructor with parameters.
     * @param id the bookmark ID
     * @param name the bookmark name
     * @param url the bookmark URL
     * @param createdDate the creation date
     */
    public BookmarkDTO(String id, String name, String url, Date createdDate) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.createdDate = createdDate;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}