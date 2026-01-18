package com.example.smos;

import java.util.HashMap;
import java.util.Map;

/**
 * Simulates a database access layer for the SMOS system.
 * Responsible for fetching raw teaching records.
 */
public class SMOSDatabase {
    private boolean simulateConnectionFailure; // Flag to simulate connection interruptions

    /**
     * Constructor for SMOSDatabase.
     * @param simulateConnectionFailure If true, fetchTeachingRecord will throw a ConnectionException.
     */
    public SMOSDatabase(boolean simulateConnectionFailure) {
        this.simulateConnectionFailure = simulateConnectionFailure;
    }

    /**
     * Fetches a teaching record from the simulated database.
     * @param teachingId The ID of the teaching to fetch.
     * @return A Map representing the teaching record, or null if not found.
     * @throws ConnectionException if the database connection is interrupted.
     */
    public Map<String, Object> fetchTeachingRecord(String teachingId) {
        if (simulateConnectionFailure) {
            // Simulate connection interruption as per sequence diagram
            throw new ConnectionException("SMOS Database: Connection interrupted during fetch operation.");
        }

        // Simulate database lookup
        Map<String, Object> record = new HashMap<>();
        switch (teachingId) {
            case "T001":
                record.put("id", "T001");
                record.put("name", "Introduction to Java");
                record.put("courseCode", "CS101");
                record.put("teacherId", "EMP001");
                record.put("teacherName", "Dr. Alice Smith"); // Stored in DB, but DTO also has it
                record.put("semester", "Fall 2023");
                break;
            case "T002":
                record.put("id", "T002");
                record.put("name", "Advanced Algorithms");
                record.put("courseCode", "CS405");
                record.put("teacherId", "EMP002");
                record.put("teacherName", "Prof. Bob Johnson");
                record.put("semester", "Spring 2024");
                break;
            default:
                return null; // Teaching not found
        }
        System.out.println("SMOSDatabase: Fetched record for ID " + teachingId);
        return record;
    }

    /**
     * Sets the flag to simulate connection failure.
     * @param simulateConnectionFailure true to simulate failure, false otherwise.
     */
    public void setSimulateConnectionFailure(boolean simulateConnectionFailure) {
        this.simulateConnectionFailure = simulateConnectionFailure;
    }
}