'''
Model class representing a Banner.
It stores information about a single banner, including its ID, the path to its image,
and a flag indicating if the image is considered valid.
'''
package com.chatdev.bannerapp.model;
public class Banner {
    private String id;
    private String imagePath;
    private boolean isValidImage;
    /**
     * Constructs a new Banner instance.
     *
     * @param id The unique identifier for the banner.
     * @param imagePath The file path to the banner image.
     * @param isValidImage A boolean indicating if the image associated with the banner is valid.
     */
    public Banner(String id, String imagePath, boolean isValidImage) {
        this.id = id;
        this.imagePath = imagePath;
        this.isValidImage = isValidImage;
    }
    /**
     * Gets the ID of the banner.
     * @return The banner ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the ID of the banner.
     * @param id The new banner ID.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Gets the image path of the banner.
     * @return The path to the banner image.
     */
    public String getImagePath() {
        return imagePath;
    }
    /**
     * Sets the image path of the banner.
     * @param imagePath The new path to the banner image.
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    /**
     * Checks if the banner image is valid.
     * @return True if the image is valid, false otherwise.
     */
    public boolean isValidImage() {
        return isValidImage;
    }
    /**
     * Sets the validity status of the banner image.
     * @param validImage True if the image is valid, false otherwise.
     */
    public void setValidImage(boolean validImage) {
        isValidImage = validImage;
    }
    @Override
    public String toString() {
        return "Banner ID: " + id + ", Path: " + imagePath + ", Valid: " + isValidImage;
    }
}