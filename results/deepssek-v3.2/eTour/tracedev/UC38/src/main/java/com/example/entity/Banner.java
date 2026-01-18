package com.example.entity;

import java.util.Date;

/**
 * Represents a banner that can be displayed at a point of restaurant.
 */
public class Banner {
    private String bannerId;
    private String pointId;
    private byte[] imageData;
    private Date creationDate;
    private boolean isActive;

    public Banner(String bannerId, String pointId, byte[] imageData) {
        this.bannerId = bannerId;
        this.pointId = pointId;
        this.imageData = imageData;
        this.creationDate = new Date();
        this.isActive = true;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}