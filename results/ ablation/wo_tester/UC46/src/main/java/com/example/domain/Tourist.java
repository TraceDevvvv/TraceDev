package com.example.domain;

/**
 * Represents a Tourist in the domain.
 * Contains personal information and associated search preferences.
 */
public class Tourist {
    private String id;
    private String name;
    private String email;
    private SearchPreferences preferences;

    public Tourist(String id, String name, String email, SearchPreferences preferences) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Returns the tourist's search preferences
    public SearchPreferences getPreferences() {
        return preferences;
    }

    // Updates the tourist's search preferences
    public void updatePreferences(SearchPreferences preferences) {
        this.preferences = preferences;
    }
}