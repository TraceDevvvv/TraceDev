package com.etour.search;

import java.util.Date;
import java.util.List;

/**
 * Value object containing search results and metadata.
 */
public class SearchResult {
    private List<Site> sites;
    private Date timestamp;
    private int resultCount;

    public SearchResult(List<Site> sites) {
        this.sites = sites;
        this.timestamp = new Date();
        this.resultCount = sites != null ? sites.size() : 0;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
        this.resultCount = sites != null ? sites.size() : 0;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getResultCount() {
        return resultCount;
    }

    /**
     * Checks if the search result is empty.
     * @return true if no sites were found, false otherwise.
     */
    public boolean isEmpty() {
        return sites == null || sites.isEmpty();
    }

    /**
     * Gets the count of sites in the result.
     * @return Number of sites.
     */
    public int getSiteCount() {
        return resultCount;
    }
}