package com.example.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a site with associated feedback.
 */
public class Site {
    private int siteId;
    private String name;
    private String location;
    private List<Feedback> feedbacks = new ArrayList<>();

    public Site(int siteId, String name, String location) {
        this.siteId = siteId;
        this.name = name;
        this.location = location;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
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

    /**
     * Returns list of feedbacks for this site.
     * @return List of Feedback objects.
     */
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    @Override
    public String toString() {
        return "Site{" +
                "siteId=" + siteId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", feedbacksCount=" + feedbacks.size() +
                '}';
    }
}