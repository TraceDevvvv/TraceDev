package com.example.application;

/**
 * Criteria for searching tourists.
 */
public class SearchCriteria {
    private String nameFilter;
    private String emailFilter;
    private String otherFilter;

    public SearchCriteria() {
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String filter) {
        this.nameFilter = filter;
    }

    public String getEmailFilter() {
        return emailFilter;
    }

    public void setEmailFilter(String filter) {
        this.emailFilter = filter;
    }

    public String getOtherFilter() {
        return otherFilter;
    }

    public void setOtherFilter(String filter) {
        this.otherFilter = filter;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "nameFilter='" + nameFilter + '\'' +
                ", emailFilter='" + emailFilter + '\'' +
                ", otherFilter='" + otherFilter + '\'' +
                '}';
    }
}