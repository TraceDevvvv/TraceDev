package com.example.infrastructure;

import com.example.domain.Location;
import com.example.exceptions.ETOURServiceException;

import java.util.Map;
import java.util.Random;

/**
 * Concrete implementation of ILocationService that simulates interaction with an ETOUR external system.
 * Implements the throwing of ETOURServiceException as per R11.
 */
public class ETOURLocationService implements ILocationService {

    private static final Random RANDOM = new Random();
    // Simulate connection interruption for a specific context value or randomly.
    private static final String FAILED_CONTEXT_KEY = "simulateError";
    private static final String FAILED_CONTEXT_VALUE = "true";

    /**
     * Retrieves the user's current location based on the provided context.
     * This implementation simulates calling an external ETOUR system.
     * It can throw an ETOURServiceException based on specific context or a random chance.
     *
     * @param touristEventContext A map containing context information, e.g., session ID, device ID.
     * @return The user's {@link Location}.
     * @throws ETOURServiceException if there is an issue connecting to the ETOUR external system (R11).
     */
    @Override
    public Location requestLocationData(Map<String, String> touristEventContext) throws ETOURServiceException {
        System.out.println("ETOURLocationService: Requesting location data from ETOUR for context: " + touristEventContext);

        // Simulate connection interruption or error based on R11
        if (touristEventContext != null && FAILED_CONTEXT_VALUE.equals(touristEventContext.get(FAILED_CONTEXT_KEY))) {
            System.err.println("ETOURLocationService: Simulating ETOUR connection timeout/error as requested.");
            throw new ETOURServiceException("ETOUR connection interrupted: Could not retrieve location data.");
        }

        // Simulate random failure (e.g., 20% chance)
        if (RANDOM.nextInt(100) < 20) { // 20% chance of failure
            System.err.println("ETOURLocationService: Simulating random ETOUR service failure.");
            throw new ETOURServiceException("ETOUR service temporarily unavailable.");
        }

        // Simulate successful connection and data retrieval
        // For demonstration, return a dummy location based on some context or a default
        double lat = 34.0522; // Default: Los Angeles latitude
        double lon = -118.2437; // Default: Los Angeles longitude

        if (touristEventContext != null && touristEventContext.containsKey("userId")) {
            // Example: Vary location slightly based on user ID for realism
            try {
                int userIdHash = touristEventContext.get("userId").hashCode();
                lat += (userIdHash % 100) / 1000.0;
                lon += (userIdHash % 100) / 1000.0;
            } catch (NumberFormatException e) {
                // Ignore, use default
            }
        }

        Location userLocation = new Location(lat, lon);
        System.out.println("ETOURLocationService: Returning user location: " + userLocation);
        return userLocation;
    }
}