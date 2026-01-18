package com.example.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object representing position data.
 * Contains latitude, longitude and timestamp.
 */
public class PositionData {
    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;

    public PositionData(double latitude, double longitude, LocalDateTime timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "PositionData{latitude=" + latitude + ", longitude=" + longitude + ", timestamp=" + timestamp + "}";
    }
}