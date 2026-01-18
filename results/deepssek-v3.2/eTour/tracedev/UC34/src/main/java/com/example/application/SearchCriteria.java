package com.example.application;

import java.util.Date;

/**
 * Search criteria for cultural site queries.
 */
public class SearchCriteria {
    private String keywords;
    private String siteType;
    private double userLatitude;
    private double userLongitude;
    private double searchRadiusKm;
    private Date startDate;
    private Date endDate;
    private double minRating;

    public SearchCriteria(String keywords, String siteType, double lat, double lon,
                          double radius, Date startDate, Date endDate, double minRating) {
        this.keywords = keywords;
        this.siteType = siteType;
        this.userLatitude = lat;
        this.userLongitude = lon;
        this.searchRadiusKm = radius;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minRating = minRating;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getSiteType() {
        return siteType;
    }

    public double getUserLatitude() {
        return userLatitude;
    }

    public double getUserLongitude() {
        return userLongitude;
    }

    public double getSearchRadiusKm() {
        return searchRadiusKm;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getMinRating() {
        return minRating;
    }
}