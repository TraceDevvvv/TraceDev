package com.example.domain;

/**
 * Domain object representing a location.
 */
public class Location {
    private String address;
    private GeoPoint coordinates;
    
    public Location(String address, GeoPoint coordinates) {
        this.address = address;
        this.coordinates = coordinates;
    }
    
    public String getAddress() {
        return address;
    }
    
    public GeoPoint getCoordinates() {
        return coordinates;
    }
}