package com.example.dto;

import com.example.domain.DateRange;
import java.util.Map;

/**
 * Data Transfer Object for search form data.
 * Used to transfer search criteria from the presentation layer.
 */
public class SearchFormDTO {
    private String criteria;
    private String filterType;
    private DateRange dateRange;

    public SearchFormDTO() {
        // Default constructor
    }

    /**
     * Constructor that creates a SearchFormDTO from a map of form data.
     * Flow of Events 3.
     */
    public SearchFormDTO(Map<String, Object> formData) {
        this.criteria = (String) formData.getOrDefault("criteria", "");
        this.filterType = (String) formData.getOrDefault("filterType", "");
        this.dateRange = (DateRange) formData.getOrDefault("dateRange", null);
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }
}