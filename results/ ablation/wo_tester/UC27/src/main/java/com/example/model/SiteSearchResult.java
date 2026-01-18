package com.example.model;

import java.util.List;

/**
 * Represents the result of a site search operation.
 */
public class SiteSearchResult {
    private List<Site> sites;
    private long executionTime;
    private boolean hasError;
    private String errorMessage;

    public SiteSearchResult(List<Site> sites, long executionTime) {
        this.sites = sites;
        this.executionTime = executionTime;
        this.hasError = false;
        this.errorMessage = "";
    }

    public SiteSearchResult(String errorMessage) {
        this.sites = null;
        this.executionTime = 0;
        this.hasError = true;
        this.errorMessage = errorMessage;
    }

    public List<Site> getSites() {
        return sites;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public boolean hasError() {
        return hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Checks if the execution time is within the given time limit.
     *
     * @param timeLimit the time limit in milliseconds
     * @return true if within limit, false otherwise
     */
    public boolean isWithinTimeLimit(long timeLimit) {
        return executionTime <= timeLimit;
    }
}