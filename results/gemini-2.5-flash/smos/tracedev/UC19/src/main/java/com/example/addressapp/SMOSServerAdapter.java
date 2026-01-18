package com.example.addressapp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Adapter for connecting to the SMOS Server and fetching data.
 * Added to satisfy requirement EX2.
 */
public class SMOSServerAdapter {

    private boolean connected;
    private Random random = new Random();
    private Map<String, Address> mockExternalData = new HashMap<>();

    public SMOSServerAdapter() {
        // Populate with some mock data
        mockExternalData.put("101", new Address("101", "Home Address", "123 Main St", "Anytown", "10001", "USA", LocalDateTime.now()));
        mockExternalData.put("102", new Address("102", "Work Address", "456 Oak Ave", "Sometown", "20002", "Canada", LocalDateTime.now()));
        mockExternalData.put("103", new Address("103", "Vacation Home", "789 Pine Ln", "Resortville", "30003", "Mexico", LocalDateTime.now()));
    }

    /**
     * Simulates connecting to the SMOS server.
     * @return true if connection is successful, false otherwise.
     */
    public boolean connect() {
        // Simulate connection success/failure
        // Let's make it fail randomly 20% of the time
        connected = random.nextDouble() > 0.2;
        if (!connected) {
            System.out.println("SMOSServerAdapter: Connection attempt failed.");
        } else {
            System.out.println("SMOSServerAdapter: Connected successfully.");
        }
        return connected;
    }

    /**
     * Simulates fetching data from the SMOS server based on a query.
     * @param query The query string (e.g., "addressId=101").
     * @return A list of objects (e.g., Address entities) or an empty list if not found or connection failed.
     */
    public List<Object> fetchData(String query) {
        if (!connected) {
            System.err.println("SMOSServerAdapter: Cannot fetch data, not connected.");
            return new ArrayList<>(); // Return empty list or throw an exception indicating connection issue
        }

        System.out.println("SMOSServerAdapter: Fetching data with query: " + query);
        // Simple parsing for "addressId=..."
        if (query.startsWith("addressId=")) {
            String addressId = query.substring("addressId=".length());
            Address address = mockExternalData.get(addressId);
            if (address != null) {
                List<Object> result = new ArrayList<>();
                result.add(address);
                return result;
            }
        }
        return new ArrayList<>(); // No data found
    }
}