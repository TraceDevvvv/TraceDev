package com.system;

import java.awt.Dimension;

/**
 * Represents an image file.
 */
public class Image {
    private byte[] data;
    private String format;
    private long size;
    private Dimension dimensions;

    public Image(byte[] data) {
        this.data = data;
        this.size = data != null ? data.length : 0;
        // Assume defaults; real implementation would parse metadata
        this.format = "JPEG";
        this.dimensions = new Dimension(800, 600);
    }

    public byte[] getData() { return data; }
    public void setData(byte[] data) { this.data = data; }
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    public long getSize() { return size; }
    public void setSize(long size) { this.size = size; }
    public Dimension getDimensions() { return dimensions; }
    public void setDimensions(Dimension dimensions) { this.dimensions = dimensions; }

    /**
     * Checks if the image format is valid (simplified).
     * @return true if format is JPEG or PNG
     */
    public boolean isValidFormat() {
        return "JPEG".equalsIgnoreCase(format) || "PNG".equalsIgnoreCase(format);
    }

    /**
     * Checks if the image size is within a reasonable limit (1MB).
     * @return true if size <= 1,048,576 bytes
     */
    public boolean isValidSize() {
        return size <= 1_048_576L; // 1 MB
    }
}