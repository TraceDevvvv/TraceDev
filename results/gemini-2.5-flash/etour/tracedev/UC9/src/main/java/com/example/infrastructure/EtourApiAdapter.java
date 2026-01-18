package com.example.infrastructure;

import com.example.dto.PointOfRestDto;
import com.example.exceptions.EtourConnectionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Adapter for interacting with the external ETOUR API.
 * Translates application requests into API calls and raw API responses into DTOs.
 */
public class EtourApiAdapter {

    private final Random random = new Random();
    private boolean simulateConnectionError = false; // For testing error path

    /**
     * Fetches points of rest from the ETOUR external service based on query parameters.
     * Corresponds to the 'getPointsOfRest(params)' message in the sequence diagram.
     *
     * @param queryParams A map of parameters for the API call.
     * @return A list of PointOfRestDto objects received from the ETOUR API.
     * @throws EtourConnectionException if there's a problem connecting to or receiving from ETOUR.
     */
    public List<PointOfRestDto> getPointsOfRest(Map<String, String> queryParams) throws EtourConnectionException {
        System.out.println("Adapter: Calling ETOUR with query params: " + queryParams);

        // Simulate network latency and potential connection errors
        try {
            Thread.sleep(random.nextInt(200) + 50); // Simulate network latency between 50ms and 250ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simulate connection interruption (20% chance or forced by simulateConnectionError flag)
        if (simulateConnectionError || random.nextInt(100) < 20) {
            throw new EtourConnectionException("Connection to ETOUR server interrupted or timed out.");
        }

        // Simulate successful data retrieval
        List<PointOfRestDto> dtos = new ArrayList<>();
        String keyword = queryParams.getOrDefault("keyword", "any");
        String type = queryParams.getOrDefault("type", "tourist_spot");

        // Generate some dummy DTOs based on criteria
        for (int i = 1; i <= 3; i++) {
            dtos.add(new PointOfRestDto(
                    "ETOUR_" + i + "_" + keyword.substring(0, Math.min(keyword.length(), 3)),
                    "ETOUR " + keyword + " Location " + i,
                    "City " + i + ", Country " + i,
                    "A nice " + type + " featuring " + keyword + ".",
                    type
            ));
        }
        System.out.println("Adapter: Received " + dtos.size() + " DTOs from ETOUR.");
        return dtos;
    }

    /**
     * Setter to forcefully simulate a connection error for testing purposes.
     * @param simulateConnectionError True to force connection errors.
     */
    public void setSimulateConnectionError(boolean simulateConnectionError) {
        this.simulateConnectionError = simulateConnectionError;
    }
}