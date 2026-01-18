package com.example.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for position information.
 */
public class PositionDTO {
    private double latitude;
    private double longitude;
    private double accuracy;
    private LocalDateTime timestamp;

    /**
     * Constructor for PositionDTO.
     * @param latitude the latitude coordinate
     * @param longitude the longitude coordinate
     * @param accuracy the accuracy of the position
     * @param timestamp the timestamp of the position reading
     */
    public PositionDTO(double latitude, double longitude, double accuracy, LocalDateTime timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy;
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

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}