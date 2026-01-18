package com.example.application;

/**
 * Input data for tourist (use case layer).
 * Contains tourist ID and tourist card ID (requirement 4).
 */
public class TouristData {
    private String touristId;
    private String touristCardId;

    public TouristData(String touristId, String touristCardId) {
        this.touristId = touristId;
        this.touristCardId = touristCardId;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getTouristCardId() {
        return touristCardId;
    }
}