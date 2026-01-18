package com.etour.search;

import java.util.Date;

/**
 * Value object representing a search request from a user.
 */
public class SearchRequest {
    private SearchCriteria criteria;
    private String userId;
    private Date timestamp;

    public SearchRequest(SearchCriteria criteria, String userId) {
        this.criteria = criteria;
        this.userId = userId;
        this.timestamp = new Date();
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Validates the search request.
     * @return true if the request is valid, false otherwise.
     */
    public boolean isValid() {
        if (userId == null || userId.trim().isEmpty()) {
            return false;
        }
        if (criteria == null) {
            return false;
        }
        return criteria.validate();
    }
}