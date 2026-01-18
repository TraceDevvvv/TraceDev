package com.example.model;

import java.util.List;

/**
 * Represents a Site entity.
 */
public class Site {
    private String id;
    private String name;
    private List<Feedback> feedbackList;

    // Constructor
    public Site(String id, String name, List<Feedback> feedbackList) {
        this.id = id;
        this.name = name;
        this.feedbackList = feedbackList;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }
}