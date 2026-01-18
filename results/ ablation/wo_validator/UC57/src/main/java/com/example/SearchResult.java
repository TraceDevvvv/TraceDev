package com.example;

import java.util.List;

/**
 * Represents the result of a search operation.
 */
public class SearchResult {
    private List<Site> sites;
    private int totalCount;
    private long executionTime;

    /**
     * Constructor for SearchResult.
     * @param sites the list of sites found.
     * @param totalCount the total number of sites.
     * @param executionTime the execution time in milliseconds.
     */
    public SearchResult(List<Site> sites, int totalCount, long executionTime) {
        this.sites = sites;
        this.totalCount = totalCount;
        this.executionTime = executionTime;
    }

    /**
     * Gets the list of sites.
     * @return the sites.
     */
    public List<Site> getSites() {
        return sites;
    }

    /**
     * Sets the list of sites.
     * @param sites the sites.
     */
    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    /**
     * Gets the total count of sites.
     * @return the total count.
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * Sets the total count.
     * @param totalCount the total count.
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * Gets the execution time.
     * @return execution time in milliseconds.
     */
    public long getExecutionTime() {
        return executionTime;
    }

    /**
     * Sets the execution time.
     * @param executionTime the execution time in milliseconds.
     */
    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
}