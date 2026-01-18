package com.example.domain;

/**
 * Core domain entity representing a Banner.
 */
public class Banner {
    private String id;
    private String name;
    private String imageUrl;
    private String refreshmentPointId;
    
    public Banner(String id, String name, String imageUrl, String refreshmentPointId) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.refreshmentPointId = refreshmentPointId;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public String getAssociatedRefreshmentPointId() {
        return refreshmentPointId;
    }
}