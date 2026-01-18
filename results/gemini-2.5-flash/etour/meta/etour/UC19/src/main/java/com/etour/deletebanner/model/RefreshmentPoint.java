package com.etour.deletebanner.model;

import java.util.Objects;

/**
 * Represents a refreshment point where banners can be displayed.
 */
public class RefreshmentPoint {
    private String id;
    private String name;

    public RefreshmentPoint(String id, String name) {
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

    @Override
    public String toString() {
        return name; // Display name in UI components like ComboBox
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshmentPoint that = (RefreshmentPoint) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}