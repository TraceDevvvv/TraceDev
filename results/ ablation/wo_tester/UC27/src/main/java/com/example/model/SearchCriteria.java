package com.example.model;

import java.util.Map;

/**
 * Represents search criteria for site search.
 */
public class SearchCriteria {
    private String siteName;
    private String location;
    private Map<String, String> metadataFilters;

    public SearchCriteria() {
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, String> getMetadataFilters() {
        return metadataFilters;
    }

    public void setMetadataFilters(Map<String, String> metadataFilters) {
        this.metadataFilters = metadataFilters;
    }
}