package com.touristGuide.model;

import java.util.List;

public class SearchResult {
    private List<Site> sites;
    private long queryTime; // in milliseconds

    public SearchResult(List<Site> sites, long queryTime) {
        this.sites = sites;
        this.queryTime = queryTime;
    }

    public List<Site> getSites() {
        return sites;
    }

    public long getQueryTime() {
        return queryTime;
    }

    /**
     * <<trace>> Check if query completed within 15 seconds (requirement 13).
     */
    public boolean isWithinTimeLimit() {
        return queryTime <= 15000; // 15 seconds
    }
}