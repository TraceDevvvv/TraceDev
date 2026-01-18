package com.banner.model;

/**
 * Represents an image file with metadata.
 */
public class ImageFile {
    private String contentType;
    private long size;
    private Dimensions dimensions;
    private byte[] data;

    public ImageFile(String contentType, long size, Dimensions dimensions, byte[] data) {
        this.contentType = contentType;
        this.size = size;
        this.dimensions = dimensions;
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public long getSize() {
        return size;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public byte[] getData() {
        return data;
    }
}