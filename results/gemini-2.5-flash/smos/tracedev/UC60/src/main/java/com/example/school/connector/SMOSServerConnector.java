package com.example.school.connector;

import com.example.school.service.SecurityService; // Relationship from CD
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Simulates a connector to an external SMOS (Student Management Online System) server.
 * This class handles connectivity and data fetching from the simulated external system.
 */
public class SMOSServerConnector {

    // Dependency injection for SecurityService
    private final SecurityService securityService;

    // Simulate server availability
    private boolean available = true;
    private Random random = new Random();

    /**
     * Constructor for SMOSServerConnector.
     *
     * @param securityService The security service to use, mainly for compliance with CD.
     */
    public SMOSServerConnector(SecurityService securityService) {
        this.securityService = securityService;
        // Assume connect is called upon instantiation, for simplicity
        connect();
    }

    /**
     * Simulates connecting to the SMOS server.
     * In a real application, this would involve network calls, authentication, etc.
     *
     * @return true if connection is successful, false otherwise.
     */
    public boolean connect() {
        System.out.println("SMOSServerConnector: Attempting to connect to SMOS server...");
        // Simulate a potential connection failure
        this.available = random.nextBoolean(); // 50% chance of being available
        if (this.available) {
            System.out.println("SMOSServerConnector: Successfully connected to SMOS server.");
        } else {
            System.err.println("SMOSServerConnector: Failed to connect to SMOS server.");
        }
        return this.available;
    }

    /**
     * Simulates disconnecting from the SMOS server.
     */
    public void disconnect() {
        System.out.println("SMOSServerConnector: Disconnecting from SMOS server.");
        this.available = false;
    }

    /**
     * Checks if the SMOS server is currently available.
     *
     * @return true if available, false otherwise.
     */
    public boolean isAvailable() {
        return this.available;
    }

    /**
     * Simulates fetching raw class registry data from the SMOS server for a given student.
     * The data is returned as a list of maps, mimicking a generic JSON or database record structure.
     *
     * @param studentId The ID of the student for whom to fetch data.
     * @return A list of maps, where each map represents a raw class registry record.
     *         Returns an empty list if the server is unavailable or no data is found.
     */
    public List<Map<String, Object>> fetchClassRegistryData(String studentId) {
        // As per SD, we assume this is called after isAvailable() returns true.
        // If not available, should ideally throw an exception or return null.
        if (!isAvailable()) {
            System.err.println("SMOSServerConnector: Cannot fetch data, SMOS server is unavailable.");
            return new ArrayList<>();
        }

        System.out.println("SMOSServerConnector: Fetching class registry data for student: " + studentId);

        // Simulate fetching data. In a real scenario, this would be a complex query.
        List<Map<String, Object>> rawDataList = new ArrayList<>();

        if ("S1001".equals(studentId)) {
            // Sample data for S1001
            Map<String, Object> entry1 = new HashMap<>();
            entry1.put("id", "CRE001");
            entry1.put("studentId", "S1001");
            entry1.put("date", "2023-01-15");
            entry1.put("absences", 1);
            entry1.put("disciplinaryNotes", "Late for class");
            entry1.put("delays", 1);
            entry1.put("justification", "Traffic jam");
            rawDataList.add(entry1);

            Map<String, Object> entry2 = new HashMap<>();
            entry2.put("id", "CRE002");
            entry2.put("studentId", "S1001");
            entry2.put("date", "2023-01-20");
            entry2.put("absences", 0);
            entry2.put("disciplinaryNotes", "Excellent participation");
            entry2.put("delays", 0);
            entry2.put("justification", "N/A");
            rawDataList.add(entry2);

            Map<String, Object> entry3 = new HashMap<>();
            entry3.put("id", "CRE003");
            entry3.put("studentId", "S1001");
            entry3.put("date", "2023-02-01");
            entry3.put("absences", 1);
            entry3.put("disciplinaryNotes", "Skipped afternoon session");
            entry3.put("delays", 0);
            entry3.put("justification", ""); // No justification provided
            rawDataList.add(entry3);

        } else if ("S1002".equals(studentId)) {
            // Sample data for S1002
            Map<String, Object> entry1 = new HashMap<>();
            entry1.put("id", "CRE004");
            entry1.put("studentId", "S1002");
            entry1.put("date", "2023-01-10");
            entry1.put("absences", 0);
            entry1.put("disciplinaryNotes", "Good conduct");
            entry1.put("delays", 0);
            entry1.put("justification", "N/A");
            rawDataList.add(entry1);
        } else {
            // No data for other students
            System.out.println("SMOSServerConnector: No data found for student " + studentId);
        }

        // Apply security encryption if applicable, as per CD relationship
        // For simplicity, we just log here. In a real scenario, this would encrypt the data content itself.
        System.out.println("SMOSServerConnector: Using SecurityService for data handling (e.g., encryption) during fetch.");
        String dummyEncryptedData = securityService.encryptData("Some sensitive data from SMOS");
        System.out.println("SMOSServerConnector: Dummy encrypted data: " + dummyEncryptedData);

        return rawDataList;
    }
}