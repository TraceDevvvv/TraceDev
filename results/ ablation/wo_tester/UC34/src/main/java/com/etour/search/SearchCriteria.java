package com.etour.search;

import java.util.Date;

/**
 * Value object containing search criteria parameters.
 */
public class SearchCriteria {
    private String keywords;
    private String category;
    private double range;
    private Date minDate;
    private Date maxDate;

    public SearchCriteria() {
        this.keywords = "";
        this.category = "ARCHAEOLOGICAL";
        this.range = 10.0; // Default 10km range
        this.minDate = new Date(System.currentTimeMillis() - 365L * 24 * 60 * 60 * 1000); // 1 year ago
        this.maxDate = new Date();
    }

    public SearchCriteria(String keywords, String category, double range, Date minDate, Date maxDate) {
        this.keywords = keywords;
        this.category = category;
        this.range = range;
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    /**
     * Generates a search string representation of the criteria.
     * @return String representation of search criteria.
     */
    public String getSearchString() {
        return String.format("keywords='%s', category='%s', range=%.2fkm", 
                keywords, category, range);
    }

    /**
     * Validates the search criteria.
     * @return true if criteria is valid, false otherwise.
     */
    public boolean validate() {
        if (keywords == null || keywords.trim().isEmpty()) {
            return false;
        }
        if (range < 0) {
            return false;
        }
        if (minDate != null && maxDate != null && minDate.after(maxDate)) {
            return false;
        }
        return true;
    }
}