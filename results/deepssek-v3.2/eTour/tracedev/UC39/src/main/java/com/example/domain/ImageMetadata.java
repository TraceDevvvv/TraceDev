package com.example.domain;

/**
 * Metadata for an uploaded image.
 */
public class ImageMetadata {
    private String imageKey;
    private String url;
    private long size;
    private String format;

    public ImageMetadata(String imageKey, String url, long size, String format) {
        this.imageKey = imageKey;
        this.url = url;
        this.size = size;
        this.format = format;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}