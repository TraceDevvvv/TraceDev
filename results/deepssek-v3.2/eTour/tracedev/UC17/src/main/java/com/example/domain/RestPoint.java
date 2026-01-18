package com.example.domain;

/**
 * Domain entity representing a Rest Point.
 */
public class RestPoint {
    private String restPointId;
    private String name;

    public RestPoint(String restPointId, String name) {
        this.restPointId = restPointId;
        this.name = name;
    }

    public String getRestPointId() {
        return restPointId;
    }

    public String getName() {
        return name;
    }
}