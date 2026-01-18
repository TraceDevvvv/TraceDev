package com.example.turista.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Adapter for interacting with the external ETOUR server.
 * This class simulates communication with an external system to fetch raw Turista data.
 */
public class ETOURServerAdapter {
    // - serverEndpoint : String
    private String serverEndpoint;

    // --- Simulation states for testing purposes ---
    private boolean connectionUp = true;
    private String dataSimulationMode = "SUCCESS"; // "SUCCESS", "NOT_FOUND", "CONNECTION_ERROR"

    /**
     * Constructor for ETOURServerAdapter.
     * @param serverEndpoint The URL or identifier for the ETOUR server.
     */
    public ETOURServerAdapter(String serverEndpoint) {
        this.serverEndpoint = serverEndpoint;
        // Assumption: serverEndpoint is used for actual connection in a real system.
        // For this simulation, it just initializes the adapter.
        System.out.println("ETOURServerAdapter initialized with endpoint: " + serverEndpoint);
    }

    /**
     * Retrieves raw data for a Turista from the ETOUR server.
     * Simulates fetching data, potentially throwing exceptions or returning null/empty map
     * based on internal simulation settings.
     *
     * @param turistaId The ID of the turista to fetch.
     * @return A map containing raw turista data, or null/empty if not found (depending on simulation).
     * @throws TuristaDataAccessException if connection issues or other server-side errors occur.
     * note right of ETOURServerAdapter::fetchDataFromETOUR: Retrieves raw data from ETOUR server (R8)
     */
    public Map<String, String> fetchDataFromETOUR(String turistaId) throws TuristaDataAccessException {
        System.out.println("  ETOURAdapter: Attempting to fetch data for Turista ID: " + turistaId + " from " + serverEndpoint);

        if (!connectionUp || Objects.equals(dataSimulationMode, "CONNECTION_ERROR")) {
            System.err.println("  ETOURAdapter: Simulating connection interruption to ETOUR server.");
            throw new TuristaDataAccessException("ETOUR server connection interrupted."); // Traces ConnectionError from sequence diagram
        }

        if (Objects.equals(dataSimulationMode, "NOT_FOUND") || "102".equals(turistaId)) { // Simulate specific ID for not found
            System.out.println("  ETOURAdapter: Simulating Turista data not found for ID: " + turistaId);
            // As per sequence diagram, "DataNotFound" is an exception, but it could also be an empty map.
            // Let's make it throw an exception if explicitly set to NOT_FOUND mode.
            // If it's a specific ID "102", we return null to signify not found in a softer way for `buildTuristaFromRawData` to handle.
            if (Objects.equals(dataSimulationMode, "NOT_FOUND")) {
                throw new TuristaDataAccessException("Turista data not found on ETOUR server for ID: " + turistaId);
            } else {
                return null; // A null map can indicate data not found in some scenarios
            }
        }

        // Simulate successful data retrieval
        Map<String, String> rawData = new HashMap<>();
        rawData.put("id", turistaId);
        rawData.put("firstName", "John");
        rawData.put("lastName", "Doe");
        rawData.put("dob", "1990-01-15"); // Assuming YYYY-MM-DD format for Date parsing
        rawData.put("nationality", "American");
        rawData.put("contactEmail", "john.doe@example.com");
        rawData.put("contactPhone", "+1-555-123-4567");
        System.out.println("  ETOURAdapter: Successfully fetched raw data for ID: " + turistaId);
        return rawData;
    }

    /**
     * Checks the connection status of the ETOUR server.
     * @return true if the connection is up, false otherwise.
     * note right of ETOURServerAdapter::checkConnection: Aids in detecting ETOUR server connection issues (R10)
     */
    public boolean checkConnection() {
        System.out.println("  ETOURAdapter: Checking ETOUR server connection...");
        if (!connectionUp) {
            System.out.println("  ETOURAdapter: ETOUR server is NOT reachable.");
        } else {
            System.out.println("  ETOURAdapter: ETOUR server is reachable.");
        }
        return connectionUp; // This will return false if connectionUp is set to false
    }

    // --- Simulation helper methods ---
    public void simulateConnectionDown() {
        this.connectionUp = false;
        this.dataSimulationMode = "CONNECTION_ERROR"; // Ensure data fetch also fails if connection is down
        System.out.println("  ETOURAdapter: SIMULATION: Connection set to DOWN.");
    }

    public void simulateConnectionUp() {
        this.connectionUp = true;
        this.dataSimulationMode = "SUCCESS";
        System.out.println("  ETOURAdapter: SIMULATION: Connection set to UP.");
    }

    public void simulateDataNotFound() {
        this.dataSimulationMode = "NOT_FOUND";
        System.out.println("  ETOURAdapter: SIMULATION: Data not found mode activated.");
    }

    public void simulateSuccess() {
        this.dataSimulationMode = "SUCCESS";
        System.out.println("  ETOURAdapter: SIMULATION: Success mode activated.");
    }
}