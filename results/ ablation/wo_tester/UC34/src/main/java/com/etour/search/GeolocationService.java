package com.etour.search;

/**
 * Utility class that provides geolocation serv.
 */
public class GeolocationService {
    
    public GeolocationService() {
        // Constructor
    }

    /**
     * Gets the user's current location.
     * @return Location object with latitude and longitude.
     */
    public Location getUserLocation() {
        // In a real implementation, this would use device GPS or IP geolocation
        // For simulation, return a default location
        return new Location(45.4642, 9.1900); // Default to Milan coordinates
    }

    /**
     * Checks if geolocation serv are available.
     * @return true if available, false otherwise.
     */
    public boolean isGeolocationAvailable() {
        // In a real implementation, this would check device permissions and capabilities
        return true; // Assume available for simulation
    }

    /**
     * Returns location to SearchService.
     * @return location:Location
     */
    public Location getLocation() {
        Location location = getUserLocation();
        System.out.println("Returning location: " + location.getLatitude() + ", " + location.getLongitude());
        return location;
    }
}