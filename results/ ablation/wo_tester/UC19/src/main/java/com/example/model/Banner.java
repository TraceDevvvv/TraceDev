package com.example.model;

import java.util.Date;

/**
 * Represents a banner advertisement with its metadata and associated refreshment point.
 */
public class Banner {
    private String bannerId;
    private String contentUrl;
    private Date startDate;
    private Date endDate;
    private boolean isActive;
    private RefreshmentPoint refreshmentPoint;
    private String refreshmentPointId; // Added to satisfy requirement Flow of Events 4

    public Banner() {
    }

    public Banner(String bannerId, String contentUrl, Date startDate, Date endDate, boolean isActive,
                  RefreshmentPoint refreshmentPoint, String refreshmentPointId) {
        this.bannerId = bannerId;
        this.contentUrl = contentUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.refreshmentPoint = refreshmentPoint;
        this.refreshmentPointId = refreshmentPointId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public RefreshmentPoint getRefreshmentPoint() {
        return refreshmentPoint;
    }

    public void setRefreshmentPoint(RefreshmentPoint refreshmentPoint) {
        this.refreshmentPoint = refreshmentPoint;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public void setRefreshmentPointId(String refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
    }

    public void setActiveStatus(boolean status) {
        this.isActive = status;
    }
}