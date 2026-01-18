package com.example.model;

/**
 * Metadata for an image.
 * Added to satisfy requirement REQ-013
 */
public class ImageMetadata {
    private String fileName;
    private String contentType;
    private long size;

    public ImageMetadata(String fileName, String contentType, long size) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public long getSize() {
        return size;
    }
}