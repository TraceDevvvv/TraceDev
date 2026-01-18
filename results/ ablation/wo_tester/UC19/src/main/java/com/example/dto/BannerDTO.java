package com.example.dto;

import java.util.Date;

/**
 * Data Transfer Object for Banner.
 */
public class BannerDTO {
    private String bannerId;
    private String contentUrl;
    private Date startDate;
    private Date endDate;

    public BannerDTO() {
    }

    public BannerDTO(String bannerId, String contentUrl, Date startDate, Date endDate) {
        this.bannerId = bannerId;
        this.contentUrl = contentUrl;
        this.startDate = startDate;
        this.endDate = endDate;
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
}