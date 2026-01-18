package com.example;

import java.util.List;

/**
 * Response object containing search results, status, and message.
 */
public class SiteSearchResponse {
    private List<Site> sites;
    private SearchStatus status;
    private String message;

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    public SearchStatus getStatus() {
        return status;
    }

    public void setStatus(SearchStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}