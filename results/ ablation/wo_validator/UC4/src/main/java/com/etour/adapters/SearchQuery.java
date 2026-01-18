package com.etour.adapters;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a search query containing criteria for filtering cultural objects.
 * Part of the Interface Adapters layer.
 */
public class SearchQuery {
    private List<String> keywords;
    private String objectType;
    private String location;

    public SearchQuery() {
        this.keywords = new ArrayList<>();
    }

    public SearchQuery(List<String> keywords, String objectType, String location) {
        this.keywords = keywords != null ? keywords : new ArrayList<>();
        this.objectType = objectType;
        this.location = location;
    }

    // Getters
    public List<String> getKeywords() {
        return keywords;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getLocation() {
        return location;
    }

    // Setters
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords != null ? keywords : new ArrayList<>();
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Utility method to add a keyword
    public void addKeyword(String keyword) {
        if (keywords == null) {
            keywords = new ArrayList<>();
        }
        keywords.add(keyword);
    }

    @Override
    public String toString() {
        return "SearchQuery{" +
                "keywords=" + keywords +
                ", objectType='" + objectType + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}