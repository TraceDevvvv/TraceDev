package com.example.model;

/**
 * Represents the search criteria entered by the user.
 */
public class SearchCriteria {
    private String keyword;
    private String category;
    private Integer maxDistance; // in kilometers, optional

    public SearchCriteria(String keyword, String category, Integer maxDistance) {
        this.keyword = keyword;
        this.category = category;
        this.maxDistance = maxDistance;
    }

    // Getters and setters
    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Integer getMaxDistance() { return maxDistance; }
    public void setMaxDistance(Integer maxDistance) { this.maxDistance = maxDistance; }
}