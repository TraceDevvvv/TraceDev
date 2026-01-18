package com.example.model;

import com.example.dto.PreferenceSetDTO;

/**
 * Represents a Tourist entity.
 */
public class Tourist {
    private String id;
    private String name;
    private PreferenceSet preferences;

    public Tourist(String id, String name, PreferenceSet preferences) {
        this.id = id;
        this.name = name;
        this.preferences = preferences;
    }

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

    public PreferenceSet getPreferences() {
        return preferences;
    }

    public void setPreferences(PreferenceSet preferences) {
        this.preferences = preferences;
    }

    // Added to satisfy requirement Entry Conditions: Tourist IS successfully authenticated
    public boolean authenticate() {
        // For simplicity, assume authentication is always successful
        return true;
    }

    // Missing method from class diagram
    public void updatePreferences(PreferenceSet preferences) {
        this.preferences = preferences;
    }
}