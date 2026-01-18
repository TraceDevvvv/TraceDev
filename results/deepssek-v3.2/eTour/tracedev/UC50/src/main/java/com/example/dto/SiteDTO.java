package com.example.dto;

import java.util.List;

/**
 * DTO for Site with Feedback list.
 */
public class SiteDTO {
    public String siteId;
    public String name;
    public String location;
    public List<FeedbackDTO> feedbackList; // Added to satisfy requirement Description

    public SiteDTO() {
    }

    public SiteDTO(String siteId, String name, String location, List<FeedbackDTO> feedbackList) {
        this.siteId = siteId;
        this.name = name;
        this.location = location;
        this.feedbackList = feedbackList;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<FeedbackDTO> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<FeedbackDTO> feedbackList) {
        this.feedbackList = feedbackList;
    }
}