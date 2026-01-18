package com.example.culturalheritage;

/**
 * Defines the parameters for searching cultural objects.
 * This class encapsulates various criteria that a user can specify
 * to filter cultural objects. All fields are optional, allowing for
 * flexible search queries.
 */
public class SearchCriteria {
    private String keyword;
    private String category;
    private String location;
    private Integer yearFrom; // Using Integer to allow null for optional fields
    private Integer yearTo;   // Using Integer to allow null for optional fields

    /**
     * Constructs a new SearchCriteria object with all possible search parameters.
     *
     * @param keyword A general keyword to search within the name or description of cultural objects. Can be null.
     * @param category The specific category of cultural objects to search for (e.g., "Painting", "Sculpture"). Can be null.
     * @param location The location where the cultural object is found or originated. Can be null.
     * @param yearFrom The starting year for a date range search. Cultural objects created in or after this year will be included. Can be null.
     * @param yearTo The ending year for a date range search. Cultural objects created in or before this year will be included. Can be null.
     */
    public SearchCriteria(String keyword, String category, String location, Integer yearFrom, Integer yearTo) {
        this.keyword = keyword;
        this.category = category;
        this.location = location;
        this.yearFrom = yearFrom;
        this.yearTo = yearTo;
    }

    /**
     * Returns the keyword used for searching.
     * @return The keyword, or null if not specified.
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Sets the keyword for searching.
     * @param keyword The keyword to set.
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns the category used for searching.
     * @return The category, or null if not specified.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category for searching.
     * @param category The category to set.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the location used for searching.
     * @return The location, or null if not specified.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location for searching.
     * @param location The location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the starting year for the search range.
     * @return The starting year, or null if not specified.
     */
    public Integer getYearFrom() {
        return yearFrom;
    }

    /**
     * Sets the starting year for the search range.
     * @param yearFrom The starting year to set.
     */
    public void setYearFrom(Integer yearFrom) {
        this.yearFrom = yearFrom;
    }

    /**
     * Returns the ending year for the search range.
     * @return The ending year, or null if not specified.
     */
    public Integer getYearTo() {
        return yearTo;
    }

    /**
     * Sets the ending year for the search range.
     * @param yearTo The ending year to set.
     */
    public void setYearTo(Integer yearTo) {
        this.yearTo = yearTo;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
               "keyword='" + keyword + '\'' +
               ", category='" + category + '\'' +
               ", location='" + location + '\'' +
               ", yearFrom=" + yearFrom +
               ", yearTo=" + yearTo +
               '}';
    }
}