package com.banner.ui;

/**
 * UI form for selecting an image.
 * In a real system this would be a GUI component.
 */
public class ImageSelectionForm {
    private byte[] imageData;
    private String imageFormat;

    public ImageSelectionForm() {}

    /**
     * Display the form (simulated).
     */
    public void display() {
        System.out.println("ImageSelectionForm displayed.");
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    // Implement sequence diagram message m7: selectImage(imageData, format)
    public void selectImage(byte[] imageData, String format) {
        this.imageData = imageData;
        this.imageFormat = format;
        System.out.println("Image selected with format: " + format);
    }
}