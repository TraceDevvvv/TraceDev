package com.example.domain.entity;

/**
 * Core Domain Entity: Tourist
 */
public class Tourist {
    private String touristId;

    public Tourist(String touristId) {
        this.touristId = touristId;
    }

    public String getTouristId() {
        return touristId;
    }
}