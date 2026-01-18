package com.example.entity;

import java.util.Objects;

/**
 * Banner entity class.
 * Represents a banner advertisement for a point of restaurant.
 */
public class Banner {
    private String id;
    private String name;
    private String imageUrl;
    private String pointOfRestId;

    public Banner() {}

    public Banner(String id, String name, String imageUrl, String pointOfRestId) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.pointOfRestId = pointOfRestId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPointOfRestId() {
        return pointOfRestId;
    }

    public void setPointOfRestId(String pointOfRestId) {
        this.pointOfRestId = pointOfRestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banner banner = (Banner) o;
        return Objects.equals(id, banner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", pointOfRestId='" + pointOfRestId + '\'' +
                '}';
    }
}