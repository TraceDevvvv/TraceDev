package com.example.culturalobjects;

/**
 * Data Class: Represents the structured criteria used for searching cultural objects.
 * This is a domain-specific object, distinct from the UI-centric SearchFormDTO.
 */
public class SearchCriteria {
    private String keywords;
    private String category;
    private String location;

    /**
     * Constructor for SearchCriteria.
     * @param keywords Keywords for search.
     * @param category Category of cultural object.
     * @param location Location of cultural object.
     */
    public SearchCriteria(String keywords, String category, String location) {
        this.keywords = keywords;
        this.category = category;
        this.location = location;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
               "keywords='" + keywords + '\'' +
               ", category='" + category + '\'' +
               ", location='" + location + '\'' +
               '}';
    }
}