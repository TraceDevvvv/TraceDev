package com.example.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a tourist Site.
 */
public class Site {
    private String siteId;
    private String name;
    private List<Feedback> feedbacks = new ArrayList<>();
    private List<VisitedSite> visitedSites = new ArrayList<>();

    public Site(String siteId, String name) {
        this.siteId = siteId;
        this.name = name;
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

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    /**
     * Adds feedback to this site.
     */
    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }

    public List<VisitedSite> getVisitedSites() {
        return visitedSites;
    }

    public void addVisitedSite(VisitedSite visitedSite) {
        visitedSites.add(visitedSite);
    }
}