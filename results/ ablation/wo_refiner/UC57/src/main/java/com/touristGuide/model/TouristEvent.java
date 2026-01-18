package com.touristGuide.model;

import java.util.Date;

public class TouristEvent {
    private String id;
    private String type;
    private String siteId;
    private Date startDate;
    private Date endDate;

    public TouristEvent(String id, String type, String siteId, Date startDate, Date endDate) {
        this.id = id;
        this.type = type;
        this.siteId = siteId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSiteId() {
        return siteId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}