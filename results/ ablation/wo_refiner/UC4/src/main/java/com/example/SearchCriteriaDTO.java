package com.example;

import java.util.Date;

// Data Transfer Object for search criteria
public class SearchCriteriaDTO {
    private String name;
    private String category;
    private DateRange dateRange;

    public SearchCriteriaDTO() {
    }

    public SearchCriteriaDTO(String name, String category, DateRange dateRange) {
        this.name = name;
        this.category = category;
        this.dateRange = dateRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }
}