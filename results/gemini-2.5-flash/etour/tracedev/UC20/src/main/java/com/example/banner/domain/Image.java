package com.example.banner.domain;

import java.util.UUID;

/**
 * Represents an image that can be used for a banner.
 * Contains raw data, format, and size information.
 */
public class Image {
    /** Unique identifier for the image. */
    public String id;
    /** Raw binary data of the image. */
    public byte[] data;
    /** Format of the image (e.g., "png", "jpeg"). */
    public String format; // CD: +format: String
    /** Size of the image in bytes. */
    public long size; // CD: +size: long

    /**
     * Constructor for Image.
     *
     * @param data Raw binary data of the image.
     * @param format Format of the image (e.g., "png", "jpeg").
     */
    public Image(byte[] data, String format) {
        this.id = UUID.randomUUID().toString();
        this.data = data;
        this.format = format;
        this.size = data != null ? data.length : 0;
    }

    // Getters and Setters (omitted for brevity as fields are public in diagram, but good practice to include)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public byte[] getData() { return data; }
    public void setData(byte[] data) { this.data = data; }
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    public long getSize() { return size; }
    public void setSize(long size) { this.size = size; }

    @Override
    public String toString() {
        return "Image{" +
               "id='" + id + '\'' +
               ", format='" + format + '\'' +
               ", size=" + size +
               '}';
    }
}