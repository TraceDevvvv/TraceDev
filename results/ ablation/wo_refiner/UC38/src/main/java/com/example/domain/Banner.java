package com.example.domain;

import java.util.Date;

/**
 * Entity representing a Banner.
 */
public class Banner {
    private String id;
    private String pointOfRestId;
    private byte[] imageData;
    private String imageFormat;
    private Date creationDate;

    public Banner(String id, String pointOfRestId, byte[] imageData, String imageFormat) {
        this.id = id;
        this.pointOfRestId = pointOfRestId;
        this.imageData = imageData;
        this.imageFormat = imageFormat;
        this.creationDate = new Date(); // automatically set creation date
    }

    public String getId() {
        return id;
    }

    public String getPointOfRestId() {
        return pointOfRestId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String format) {
        this.imageFormat = format;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}