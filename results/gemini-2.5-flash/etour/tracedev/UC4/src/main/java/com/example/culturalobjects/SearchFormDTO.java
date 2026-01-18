package com.example.culturalobjects;

/**
 * Data Transfer Object (DTO): Represents the data collected from a search form on the UI.
 * This object is used to transfer data between the View and the Controller.
 * Modified to satisfy requirement REQ-006, indicating its use in form submission.
 */
public class SearchFormDTO {
    private String keywords;
    private String category;
    private String location;

    /**
     * Constructor for SearchFormDTO.
     * @param keywords Keywords entered by the user.
     * @param category Category selected by the user.
     * @param location Location entered by the user.
     */
    public SearchFormDTO(String keywords, String category, String location) {
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
        return "SearchFormDTO{" +
               "keywords='" + keywords + '\'' +
               ", category='" + category + '\'' +
               ", location='" + location + '\'' +
               '}';
    }
}