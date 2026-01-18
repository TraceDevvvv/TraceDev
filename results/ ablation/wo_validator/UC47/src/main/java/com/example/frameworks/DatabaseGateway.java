package com.example.frameworks;

import java.util.Map;

/**
 * Gateway to the database. In a real application, this would be a JDBC or JPA implementation.
 */
public class DatabaseGateway {
    public Map<String, String> loadUserData(String touristId) {
        // Simulate loading from database
        System.out.println("Loading user data for ID: " + touristId);
        Map<String, String> data = new java.util.HashMap<>();
        data.put("username", "john_doe");
        data.put("email", "john@example.com");
        data.put("phone", "+1234567890");
        return data;
    }

    public boolean saveUserData(String touristId, Map<String, String> data) {
        // Simulate saving to database
        System.out.println("Saving user data for ID: " + touristId + " with data: " + data);
        return true; // success
    }
}