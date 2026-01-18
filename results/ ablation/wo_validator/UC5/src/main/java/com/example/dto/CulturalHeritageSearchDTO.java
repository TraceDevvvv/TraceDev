package com.example.dto;

/**
 * Data Transfer Object for cultural heritage search results.
 */
public class CulturalHeritageSearchDTO {

    private String id;
    private String name;
    private String briefInfo;

    // Getters and setters
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

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }
}