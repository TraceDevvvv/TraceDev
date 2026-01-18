package com.example.domain;

/**
 * Domain entity representing a Tourist.
 */
public class Tourist {
    private String id;
    private Preferences preferences;

    /**
     * Constructs a Tourist with given id and preferences.
     * @param id the tourist identifier
     * @param preferences the tourist's preferences
     */
    public Tourist(String id, Preferences preferences) {
        this.id = id;
        this.preferences = preferences;
    }

    public String getId() {
        return id;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    /**
     * Updates the tourist's preferences.
     * @param newPreferences the new preferences
     */
    public void updatePreferences(Preferences newPreferences) {
        this.preferences = newPreferences;
    }
}