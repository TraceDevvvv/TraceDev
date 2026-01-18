package com.example.application;

import java.util.List;

/**
 * Data Transfer Object for search results.
 */
public class SearchResultDTO {
    private List<SiteDTO> results;
    private long searchTime;
    private int totalResults;
    private String responseStatus;

    // Getters and setters
    public List<SiteDTO> getResults() {
        return results;
    }

    public void setResults(List<SiteDTO> results) {
        this.results = results;
    }

    public long getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(long searchTime) {
        this.searchTime = searchTime;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}