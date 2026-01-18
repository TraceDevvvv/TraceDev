package com.example.infrastructure;

import com.example.domain.RefreshmentPoint;
import com.example.domain.Coordinates;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Client for communicating with the external ETOUR server.
 * Simulates fetching data and checking connection.
 */
public class ETOURServerClient {
    private String baseUrl;

    public ETOURServerClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Fetches refreshment points from the external server based on criteria.
     * @param criteria search criteria as key-value pairs
     * @return list of refreshment point entities
     */
    public List<RefreshmentPoint> fetchPoints(Map<String, Object> criteria) {
        System.out.println("Fetching points from ETOUR server at " + baseUrl + " with criteria: " + criteria);
        // Simulate fetching data; in reality, this would be an HTTP call.
        List<RefreshmentPoint> points = new ArrayList<>();
        points.add(new RefreshmentPoint("1", "Coffee Corner", "Cafe",
                new Coordinates(40.7128, -74.0060), List.of("WiFi", "Outdoor Seating")));
        points.add(new RefreshmentPoint("2", "Quick Bite", "Fast Food",
                new Coordinates(40.7589, -73.9851), List.of("Takeaway")));
        return points;
    }

    /**
     * Checks if connection to the server is alive.
     * @return true if connection is successful, false otherwise
     */
    public boolean checkConnection() {
        // Simulate connection check; in reality, this might be a ping/health endpoint.
        // For demonstration, we assume connection is usually alive.
        boolean connected = Math.random() > 0.3; // 70% chance of success
        System.out.println("Connection check: " + (connected ? "Alive" : "Failed"));
        return connected;
    }
}