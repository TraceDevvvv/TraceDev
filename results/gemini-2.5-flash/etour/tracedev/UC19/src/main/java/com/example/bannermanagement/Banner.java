package com.example.bannermanagement;

import java.util.Objects;

/**
 * Represents a Banner Ad associated with a Refreshment Point.
 * This class corresponds to the 'Banner' entity in the UML Class Diagram.
 */
public class Banner {
    public String id;
    public String refreshmentPointId;
    public String contentUrl;
    public boolean isActive;

    /**
     * Constructs a new Banner.
     * @param id The unique identifier for the banner.
     * @param refreshmentPointId The ID of the refreshment point where this banner is displayed.
     * @param contentUrl The URL pointing to the banner's content (e.g., image, video).
     * @param isActive True if the banner is currently active and displayed, false otherwise.
     */
    public Banner(String id, String refreshmentPointId, String contentUrl, boolean isActive) {
        this.id = id;
        this.refreshmentPointId = refreshmentPointId;
        this.contentUrl = contentUrl;
        this.isActive = isActive;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setRefreshmentPointId(String refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Banner{" +
               "id='" + id + '\'' +
               ", refreshmentPointId='" + refreshmentPointId + '\'' +
               ", contentUrl='" + contentUrl + '\'' +
               ", isActive=" + isActive +
               '}';
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