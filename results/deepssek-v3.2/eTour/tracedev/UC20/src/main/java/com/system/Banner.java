package com.system;

import java.util.Date;
import java.time.Duration;

/**
 * Represents a banner that is displayed at a refreshment point.
 */
public class Banner {
    private String id;
    private Image image;
    private Date createdAt;
    private String createdBy;
    private Duration validityPeriod;
    private RefreshmentPoint point; // Association to RefreshmentPoint

    public Banner(String id, Image image, RefreshmentPoint point) {
        this.id = id;
        this.image = image;
        this.point = point;
        this.createdAt = new Date(); // Set to current time
        this.createdBy = "System";   // Default creator, could be injected
        this.validityPeriod = Duration.ofDays(30); // Default validity
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public Duration getValidityPeriod() { return validityPeriod; }
    public void setValidityPeriod(Duration validityPeriod) { this.validityPeriod = validityPeriod; }
    public RefreshmentPoint getPoint() { return point; }
    public void setPoint(RefreshmentPoint point) { this.point = point; }

    /**
     * Validates the banner's data (simplified).
     * @return true if the banner is valid
     */
    public boolean validate() {
        return image != null && point != null && id != null && !id.isEmpty();
    }
}