package com.etour.adapters;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a user interface form for entering search criteria.
 * Converts form data into a SearchQuery object.
 */
public class SearchForm {
    private String keywords;
    private String objectType;
    private String location;

    public SearchForm() {
        // default constructor
    }

    // Getters
    public String getKeywords() {
        return keywords;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getLocation() {
        return location;
    }

    // Setters
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Simulates form activation and display (as per sequence diagram step 2).
     * In a real UI, this would display the form.
     */
    public void activateSearch() {
        System.out.println("Search form activated and displayed.");
    }

    /**
     * Simulates user filling the form (step 3).
     */
    public void fillForm(String keywords, String type, String location) {
        this.keywords = keywords;
        this.objectType = type;
        this.location = location;
        System.out.println("Form filled with keywords: " + keywords + ", type: " + type + ", location: " + location);
    }

    /**
     * Converts form data into a SearchQuery object (step 4).
     * Called when the user submits the form.
     * @return a SearchQuery populated with form data
     */
    public SearchQuery submit() {
        // Parse keywords string into list (assuming comma or space separated)
        List<String> keywordList = parseKeywords(this.keywords);
        SearchQuery query = new SearchQuery(keywordList, this.objectType, this.location);
        System.out.println("Form submitted, query created: " + query);
        return query;
    }

    private List<String> parseKeywords(String keywordString) {
        if (keywordString == null || keywordString.trim().isEmpty()) {
            return List.of();
        }
        // Simple splitting by commas and spaces, trimming whitespace
        return Arrays.stream(keywordString.split("[,\\s]+"))
                     .filter(word -> !word.isEmpty())
                     .toList();
    }
}