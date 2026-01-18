package com.example.culturalassets.usecase;

/**
 * A simple placeholder class representing search criteria for cultural assets.
 * In a real application, this would contain various fields like keyword, type, location, etc.
 */
public class SearchCriteria {
    // This class is a placeholder. For now, it doesn't need specific attributes
    // as it's not detailed in the sequence diagram.
    // We can add dummy attributes or methods if needed in the future.
    private String keyword;

    public SearchCriteria(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
               "keyword='" + keyword + '\'' +
               '}';
    }
}