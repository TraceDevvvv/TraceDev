package com.example.model;

/**
 * Represents search criteria for cultural heritage.
 */
public class HeritageSearchCriteria {

    private String keyword;
    private String periodFilter;
    private String locationFilter;

    // Getters and setters
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPeriodFilter() {
        return periodFilter;
    }

    public void setPeriodFilter(String periodFilter) {
        this.periodFilter = periodFilter;
    }

    public String getLocationFilter() {
        return locationFilter;
    }

    public void setLocationFilter(String locationFilter) {
        this.locationFilter = locationFilter;
    }
}