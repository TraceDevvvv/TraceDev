package com.example.etour.model;

/**
 * Represents a Banner associated with a refreshment point.
 */
public class Banner {
    private int id;
    private String name;
    private String imageUrl;
    private int refreshmentPointId;

    public Banner() {}

    public Banner(int id, String name, String imageUrl, int refreshmentPointId) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.refreshmentPointId = refreshmentPointId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public int getRefreshmentPointId() { return refreshmentPointId; }
    public void setRefreshmentPointId(int refreshmentPointId) { this.refreshmentPointId = refreshmentPointId; }

    @Override
    public String toString() {
        return "Banner [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + "]";
    }
}