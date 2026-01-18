package com.example;

/**
 * Represents a banner associated with a refreshment point.
 * Includes attributes such as id, name, imagePath, and refreshmentPointId.
 */
public class Banner {
    private int id;
    private String name;
    private String imagePath;
    private int refreshmentPointId;

    public Banner(int id, String name, String imagePath, int refreshmentPointId) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.refreshmentPointId = refreshmentPointId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getRefreshmentPointId() {
        return refreshmentPointId;
    }
}