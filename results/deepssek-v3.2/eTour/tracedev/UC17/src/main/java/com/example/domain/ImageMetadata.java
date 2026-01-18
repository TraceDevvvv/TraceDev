package com.example.domain;

/**
 * Value object representing metadata of an image.
 */
public class ImageMetadata {
    private String path;
    private int width;
    private int height;
    private long size;
    private String format;

    public ImageMetadata(String path, int width, int height, long size, String format) {
        this.path = path;
        this.width = width;
        this.height = height;
        this.size = size;
        this.format = format;
    }

    public String getPath() {
        return path;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getSize() {
        return size;
    }

    public String getFormat() {
        return format;
    }
}