package com.example.entity;

import java.util.List;

/**
 * Site entity containing a list of Feedback.
 */
public class Site {
    private String siteId;
    private String name;
    private String location;
    private List<Feedback> feedbackList; // Added to satisfy requirement Description

    public Site() {
    }

    public Site(String siteId, String name, String location, List<Feedback> feedbackList) {
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

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }
}