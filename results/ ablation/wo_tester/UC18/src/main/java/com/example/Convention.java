package com.example;

/**
 * Represents a convention that defines the maximum banners per refreshment point.
 */
public class Convention {
    private String id;
    private String name;
    private int maxBannersPerPoint;

    /**
     * Constructor for Convention.
     * @param id The unique identifier of the convention.
     * @param name The name of the convention.
     * @param maxBannersPerPoint The maximum banners allowed per refreshment point.
     */
    public Convention(String id, String name, int maxBannersPerPoint) {
        this.id = id;
        this.name = name;
        this.maxBannersPerPoint = maxBannersPerPoint;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxBannersPerPoint() {
        return maxBannersPerPoint;
    }
}