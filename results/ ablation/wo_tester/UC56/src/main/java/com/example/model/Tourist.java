package com.example.model;

/**
 * Represents a tourist user.
 */
public class Tourist {
    private String userId;
    private String name;

    public Tourist(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    /**
     * Initiates a basic search.
     */
    public void beginSearch() {
        System.out.println("Tourist " + name + " begins basic search.");
    }

    /**
     * Initiates an advanced search.
     */
    public void beginAdvancedSearch() {
        System.out.println("Tourist " + name + " begins advanced search.");
    }
    
    /**
     * Unified method for begin search / advanced search as per sequence diagram.
     */
    public void beginSearchOrAdvancedSearch() {
        System.out.println("Tourist " + name + " begins search/advanced search.");
    }
}