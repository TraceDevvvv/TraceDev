package com.example.infrastructure;

import java.util.Map;

/**
 * Simulates a data source that fetches class data from an external SMOS server.
 */
public class SMOSDataSource {
    /**
     * Fetches class data from the SMOS server.
     * @param classId The ID of the class to fetch.
     * @return A map containing the class data.
     */
    public Map<String, String> fetchClassData(String classId) {
        // In a real implementation, this would make an external API call.
        // For this example, we simulate returning some data.
        return Map.of(
            "id", classId,
            "name", "Class " + classId,
            "address", "Address for " + classId,
            "schoolYear", "2024-2025"
        );
    }

    /**
     * Simulates a connection error.
     */
    public void connectionError() {
        throw new RuntimeException("Connection error from SMOS Server");
    }
}