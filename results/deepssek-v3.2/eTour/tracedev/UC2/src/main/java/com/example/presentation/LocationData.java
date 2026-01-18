package com.example.presentation;

/**
 * Represents location data from a form.
 */
public class LocationData {
    private String address;
    private double latitude;
    private double longitude;
    
    public LocationData(String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public String getAddress() {
        return address;
    }
    
    public double getLatitude() {
        return latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
}