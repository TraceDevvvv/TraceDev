package com.example.feedbackapp.model;

/**
 * Represents a Site entity.
 * This is a simple Plain Old Java Object (POJO) class.
 * It's included to satisfy the class diagram but not directly involved in the sequence diagram.
 */
public class Site {
    private String id;
    private String name;

    /**
     * Constructs a new Site instance.
     *
     * @param id The unique identifier for the site.
     * @param name The name of the site.
     */
    public Site(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // --- Setters (optional, depending on immutability needs) ---
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Site{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}