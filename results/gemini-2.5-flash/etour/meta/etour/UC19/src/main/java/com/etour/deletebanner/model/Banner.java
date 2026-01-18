package com.etour.deletebanner.model;

import java.util.Objects;

/**
 * Represents a banner ad associated with a refreshment point.
 */
public class Banner {
    private String id;
    private String name;
    private String refreshmentPointId;

    public Banner(String id, String name, String refreshmentPointId) {
        this.id = id;
        this.name = name;
        this.refreshmentPointId = refreshmentPointId;
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

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public void setRefreshmentPointId(String refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
    }

    @Override
    public String toString() {
        return name; // Display name in UI components like ListView
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banner banner = (Banner) o;
        return Objects.equals(id, banner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}