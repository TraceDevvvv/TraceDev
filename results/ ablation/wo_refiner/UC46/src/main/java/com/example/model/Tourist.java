package com.example.model;

import java.util.Objects;

/**
 * Represents a Tourist entity.
 * According to the class diagram, a Tourist has an id, name, and methods to get and set preferences.
 */
public class Tourist {
    private String id;
    private String name;
    private SearchPreference preferences;

    public Tourist() {}

    public Tourist(String id, String name) {
        this.id = id;
        this.name = name;
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

    public SearchPreference getPreferences() {
        return preferences;
    }

    public void setPreferences(SearchPreference preferences) {
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
        return "Tourist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}