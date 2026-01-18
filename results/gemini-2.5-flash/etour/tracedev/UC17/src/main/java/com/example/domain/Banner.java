package com.example.domain;

import java.util.Date;

/**
 * Represents a Banner displayed at a Turning Point.
 * Part of the Domain Layer.
 */
public class Banner {
    private String id;
    private String name;
    private Image currentImage; // Association: Banner "1" -- "1" Image : displays
    private Date lastModifiedDate; // REQ-CD-002

    /**
     * Constructor for Banner.
     * @param id The unique identifier for the banner.
     * @param name The name of the banner.
     * @param currentImage The initial image displayed by the banner. Can be null initially.
     */
    public Banner(String id, String name, Image currentImage) {
        this.id = id;
        this.name = name;
        this.currentImage = currentImage;
        this.lastModifiedDate = new Date(); // Initialize with current date as per REQ-CD-002
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return currentImage;
    }

    public Date getLastModifiedDate() { // REQ-CD-002
        return lastModifiedDate;
    }

    /**
     * Updates the banner's image and sets the last modified date.
     * REQ-CD-008: Updated signature to include Date.
     * @param newImage The new image to be displayed.
     * @param updatedDate The date and time of the update.
     */
    public void updateImage(Image newImage, Date updatedDate) { // REQ-CD-008
        System.out.println("Banner: Updating image for banner ID: " + this.id + " from " +
                (this.currentImage != null ? this.currentImage.getUrl() : "no image") +
                " to " + (newImage != null ? newImage.getUrl() : "no image"));
        this.currentImage = newImage;
        this.lastModifiedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", currentImageId=" + (currentImage != null ? currentImage.getId() : "null") +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}