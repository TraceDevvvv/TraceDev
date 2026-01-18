package com.example.domain;

/**
 * Domain entity representing a location, created from a Place.
 */
public class Location {
    private String placeId;
    private String coordinates;

    public Location(String placeId, String coordinates) {
        this.placeId = placeId;
        this.coordinates = coordinates;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getCoordinates() {
        return coordinates;
    }

    /**
     * Creates a Location from a Place.
     * Added to satisfy requirement Flow of Events 4,5.
     */
    public static Location createFromPlace(Place place) {
        // Assumption: coordinates are derived from place id or name.
        // In a real scenario, we might fetch coordinates from another service.
        String coords = "coords-for-" + place.getId();
        return new Location(place.getId(), coords);
    }
}