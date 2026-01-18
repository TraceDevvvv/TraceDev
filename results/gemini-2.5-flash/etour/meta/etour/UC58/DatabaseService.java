package com.etour.data;

import com.etour.site.Site;
import com.etour.exceptions.ETOURConnectionException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Simulates a database service for retrieving site information.
 * This class mimics database interactions, including potential connection issues.
 */
public class DatabaseService {

    // A mock database to store site information
    private static final Map<String, Site> mockDatabase = new HashMap<>();
    private static final Random random = new Random();
    private static final double CONNECTION_FAILURE_RATE = 0.2; // 20% chance of connection failure

    static {
        // Initialize mock database with some sample sites
        mockDatabase.put("S001", new Site("S001", "Eiffel Tower", "An iconic wrought-iron lattice tower on the Champ de Mars in Paris, France.", "Paris, France", 48.8584, 2.2945, "http://example.com/eiffel.jpg", 4.7, 150000));
        mockDatabase.put("S002", new Site("S002", "Colosseum", "An oval amphitheatre in the centre of the city of Rome, Italy.", "Rome, Italy", 41.8902, 12.4922, "http://example.com/colosseum.jpg", 4.8, 120000));
        mockDatabase.put("S003", new Site("S003", "Great Wall of China", "A series of fortifications made of stone, brick, tamped earth, wood, and other materials.", "Huairou, China", 40.4319, 116.5704, "http://example.com/greatwall.jpg", 4.6, 90000));
        mockDatabase.put("S004", new Site("S004", "Machu Picchu", "An ancient Inca citadel located in the Eastern Cordillera of southern Peru.", "Cusco Region, Peru", -13.1631, -72.5450, "http://example.com/machupicchu.jpg", 4.9, 75000));
        mockDatabase.put("S005", new Site("S005", "Pyramids of Giza", "Ancient pyramids located on the Giza plateau in the outskirts of Cairo, Egypt.", "Giza, Egypt", 29.9792, 31.1342, "http://example.com/giza.jpg", 4.7, 110000));
    }

    /**
     * Retrieves site details from the mock database based on the site ID.
     * This method simulates network latency and potential connection failures.
     *
     * @param siteId The unique identifier of the site to retrieve.
     * @return The Site object if found.
     * @throws ETOURConnectionException If there is a simulated interruption in the connection to the ETOUR server.
     * @throws IllegalArgumentException If the siteId is null or empty.
     */
    public Site getSiteDetails(String siteId) throws ETOURConnectionException {
        if (siteId == null || siteId.trim().isEmpty()) {
            throw new IllegalArgumentException("Site ID cannot be null or empty.");
        }

        // Simulate network latency
        try {
            Thread.sleep(random.nextInt(500) + 100); // Simulate 100-600ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Database service thread interrupted: " + e.getMessage());
        }

        // Simulate connection interruption to the ETOUR server
        if (random.nextDouble() < CONNECTION_FAILURE_RATE) {
            throw new ETOURConnectionException("Connection to ETOUR server interrupted while fetching site details for ID: " + siteId);
        }

        // Retrieve site from mock database
        Site site = mockDatabase.get(siteId);

        if (site == null) {
            System.out.println("Site with ID '" + siteId + "' not found in the database.");
        }
        return site;
    }
}