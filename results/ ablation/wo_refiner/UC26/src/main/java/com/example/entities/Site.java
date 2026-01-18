package com.example.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a site entity containing feedbacks.
 */
public class Site {
    private int id;
    private String name;
    private List<Feedback> feedbackList;

    public Site(int id, String name) {
        this.id = id;
        this.name = name;
        this.feedbackList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Feedback> getFeedbackList() {
        return new ArrayList<>(feedbackList);
    }

    public void addFeedback(Feedback feedback) {
        feedbackList.add(feedback);
    }
}