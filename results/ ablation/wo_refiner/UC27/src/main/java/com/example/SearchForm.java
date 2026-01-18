package com.example;

import java.util.Date;

/**
 * Data class representing a search form.
 */
public class SearchForm {
    private String siteName;
    private String location;
    private Date searchDate;

    public SearchForm() {
        this.searchDate = new Date();
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String name) {
        this.siteName = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date date) {
        this.searchDate = date;
    }

    public boolean isValid() {
        // Basic validation: at least one field should be filled
        return (siteName != null && !siteName.trim().isEmpty()) ||
               (location != null && !location.trim().isEmpty());
    }
}