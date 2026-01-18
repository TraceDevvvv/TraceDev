package com.example.uml;

import java.util.Date;

/**
 * Represents an advertising banner.
 */
public class Banner {
    private String bannerId;
    private String contentUrl;
    private Date creationDate;
    private boolean isActive;

    public Banner(String bannerId, String contentUrl) {
        this.bannerId = bannerId;
        this.contentUrl = contentUrl;
        this.creationDate = new Date(); // default to current date
        this.isActive = true; // default to active
    }

    public String getBannerId() {
        return bannerId;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void deactivate() {
        this.isActive = false;
        System.out.println("Banner " + bannerId + " deactivated.");
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}