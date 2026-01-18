package com.example.model;

/**
 * Tourist actor class.
 * Represents the Tourist in the sequence diagram.
 */
public class Tourist {
    private final User user;

    public Tourist(User user) {
        this.user = user;
    }

    public void selectSite(String siteId) {
        System.out.println("Tourist: selecting site " + siteId);
        // The actual action is performed by the views (ResearchResultsView, etc.)
    }
}