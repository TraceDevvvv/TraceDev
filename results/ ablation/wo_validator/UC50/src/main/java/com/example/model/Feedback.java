package com.example.model;

/**
 * Represents feedback given by a tourist for a site.
 */
public class Feedback {
    private int id;
    private int touristId;
    private int siteId;
    private String comment;
    
    public Feedback(int id, int touristId, int siteId, String comment) {
        this.id = id;
        this.touristId = touristId;
        this.siteId = siteId;
        this.comment = comment;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getTouristId() {
        return touristId;
    }
    
    public void setTouristId(int touristId) {
        this.touristId = touristId;
    }
    
    public int getSiteId() {
        return siteId;
    }
    
    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
}