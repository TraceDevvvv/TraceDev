package com.example.domain;

import java.util.Arrays;

/**
 * Represents an image that can be displayed on a banner.
 * Part of the Domain Layer.
 */
public class Image {
    private String id;
    private String url;
    private String format; // e.g., "png", "jpeg"
    private int width;
    private int height;
    private byte[] imageData; // REQ-CD-001

    /**
     * Constructor for Image.
     * @param id The unique identifier for the image.
     * @param url The URL where the image can be accessed (after storage).
     * @param format The format of the image (e.g., "png", "jpeg").
     * @param width The width of the image in pixels.
     * @param height The height of the image in pixels.
     * @param imageData The raw byte data of the image. REQ-CD-001
     */
    public Image(String id, String url, String format, int width, int height, byte[] imageData) {
        this.id = id;
        this.url = url;
        this.format = format;
        this.width = width;
        this.height = height;
        this.imageData = imageData; // REQ-CD-001
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public byte[] getImageData() {
        // Return a copy to prevent external modification of the internal array
        return Arrays.copyOf(imageData, imageData.length);
    }

    /**
     * Checks if the image is considered valid based on some internal criteria.
     * (Placeholder implementation)
     * @return True if the image is valid, false otherwise.
     */
    public boolean isValid() {
        // Example: An image is valid if it has data, a URL, and positive dimensions.
        return imageData != null && imageData.length > 0 && url != null && !url.isEmpty() && width > 0 && height > 0;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", format='" + format + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", imageDataSize=" + (imageData != null ? imageData.length : 0) +
                '}';
    }
}