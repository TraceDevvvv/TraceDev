package com.example.dto;

import java.util.Date;

/**
 * DTO for search form data.
 * Added date and rating fields to satisfy requirement Flow of Events: 3
 */
public class SearchFormDTO {
    private String siteNameKeyword;
    private String siteTypeFilter;
    private double radiusFilter;
    private Date startDate;
    private Date endDate;
    private double minRating;

    public String getSiteNameKeyword() {
        return siteNameKeyword;
    }

    public void setSiteNameKeyword(String keyword) {
        this.siteNameKeyword = keyword;
    }

    public String getSiteTypeFilter() {
        return siteTypeFilter;
    }

    public void setSiteTypeFilter(String filter) {
        this.siteTypeFilter = filter;
    }

    public double getRadiusFilter() {
        return radiusFilter;
    }

    public void setRadiusFilter(double radius) {
        this.radiusFilter = radius;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date date) {
        this.endDate = date;
    }

    public double getMinRating() {
        return minRating;
    }

    public void setMinRating(double rating) {
        this.minRating = rating;
    }

    @Override
    public String toString() {
        return "SearchFormDTO{" +
                "siteNameKeyword='" + siteNameKeyword + '\'' +
                ", siteTypeFilter='" + siteTypeFilter + '\'' +
                ", radiusFilter=" + radiusFilter +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", minRating=" + minRating +
                '}';
    }
}