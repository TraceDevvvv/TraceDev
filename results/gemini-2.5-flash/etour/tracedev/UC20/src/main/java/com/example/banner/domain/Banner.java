package com.example.banner.domain;

import java.util.Date;
import java.util.UUID;

/**
 * Represents a banner, a displayable advertisement associated with a specific point of rest.
 */
public class Banner {
    /** Unique identifier for the banner. */
    public String id;
    /** URL or path to the banner image. (Simulated as a simple string for now) */
    public String imageUrl; // CD: +imageUrl: String
    /** Current status of the banner (e.g., ACTIVE, PENDING). */
    public BannerStatus status; // CD: +status: BannerStatus
    /** The ID of the PointOfRest this banner is associated with. */
    public String pointOfRestId; // CD: +pointOfRestId: String
    /** Timestamp when the banner was created. */
    public Date createdAt; // CD: +createdAt: Date
    /** Timestamp of the last modification to the banner. */
    public Date lastModified; // CD: +lastModified: Date

    /**
     * Default constructor for Banner.
     * Initializes ID, createdAt, and lastModified.
     */
    public Banner() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Date();
        this.lastModified = new Date();
        this.status = BannerStatus.PENDING; // Default status
    }

    /**
     * Constructor to create a banner with initial details.
     *
     * @param imageUrl The URL of the banner image.
     * @param pointOfRestId The ID of the point of rest where the banner will be displayed.
     * @param status The initial status of the banner.
     */
    public Banner(String imageUrl, String pointOfRestId, BannerStatus status) {
        this(); // Call default constructor for ID and dates
        this.imageUrl = imageUrl;
        this.pointOfRestId = pointOfRestId;
        this.status = status;
    }

    /**
     * Checks if the banner is considered valid.
     * This is a placeholder for more complex validation logic if needed.
     *
     * @return True if the banner is valid, false otherwise.
     */
    public boolean isValid() { // CD: +isValid(): boolean
        // Placeholder for actual validation logic (e.g., checking image URL, content, etc.)
        return this.imageUrl != null && !this.imageUrl.isEmpty() && this.pointOfRestId != null;
    }

    // Getters and Setters (omitted for brevity as fields are public in diagram, but good practice to include)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public BannerStatus getStatus() { return status; }
    public void setStatus(BannerStatus status) { this.status = status; }
    public String getPointOfRestId() { return pointOfRestId; }
    public void setPointOfRestId(String pointOfRestId) { this.pointOfRestId = pointOfRestId; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getLastModified() { return lastModified; }
    public void setLastModified(Date lastModified) { this.lastModified = lastModified; }

    @Override
    public String toString() {
        return "Banner{" +
               "id='" + id + '\'' +
               ", imageUrl='" + imageUrl + '\'' +
               ", status=" + status +
               ", pointOfRestId='" + pointOfRestId + '\'' +
               ", createdAt=" + createdAt +
               ", lastModified=" + lastModified +
               '}';
    }
}