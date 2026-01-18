package com.example.model;

import java.util.Objects;

/**
 * Represents a Tourist entity in the system.
 * Each tourist has a unique ID, username, email, and preferences.
 */
public class Tourist {
    private String id;
    private String username;
    private String email;
    private Preference preferences;

    // Constructors
    public Tourist() {}

    public Tourist(String id, String username, String email, Preference preferences) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.preferences = preferences;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Preference getPreferences() {
        return preferences;
    }

    public void setPreferences(Preference preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourist tourist = (Tourist) o;
        return Objects.equals(id, tourist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Tourist{id='" + id + "', username='" + username + "', email='" + email + "'}";
    }
}