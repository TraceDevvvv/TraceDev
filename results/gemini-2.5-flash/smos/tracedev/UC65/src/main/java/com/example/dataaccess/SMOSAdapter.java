package com.example.dataaccess;

import java.util.Random;

/**
 * Adapter for interacting with the external SMOS (School Management Operating System) service.
 * Simulates fetching raw data and potential connection issues.
 */
public class SMOSAdapter {

    private final Random random = new Random();

    /**
     * Fetches raw registry data from the SMOS system for a given class ID.
     *
     * @param classId The ID of the class to fetch data for.
     * @return An SMOSData object containing raw string data.
     * @throws SMOSConnectionException if a connection error occurs.
     */
    public SMOSData fetchRegistryData(String classId) {
        System.out.println("[SMOSAdapter] Attempting to fetch registry data for classId: " + classId);

        // Simulate network latency and potential connection errors (20% chance of error)
        if (random.nextInt(10) < 2) { // 20% chance of throwing an error
            System.err.println("[SMOSAdapter] Simulated SMOS server connection error!");
            throw new SMOSConnectionException("Failed to connect to SMOS server for classId: " + classId);
        }

        // --- Assumption: Return dummy raw data for simulation. ---
        String rawRegistryInfo = "Class: " + classId + ", Academic Year: 2023-2024";
        String rawStudentStatuses = "StudentA:Present, StudentB:Delayed, StudentC:Absent";
        String rawJustifications = "StudentB:Medical Note; StudentC:No Justification";
        String rawDisciplinaryNotes = "StudentB:Talking during class";

        SMOSData smosData = new SMOSData(rawRegistryInfo, rawStudentStatuses, rawJustifications, rawDisciplinaryNotes);
        System.out.println("[SMOSAdapter] Successfully fetched raw data for classId: " + classId);
        return smosData;
    }

    /**
     * Disconnects from the SMOS system.
     * (Placeholder for actual connection closing logic).
     */
    public void disconnect() {
        System.out.println("[SMOSAdapter] Disconnecting from SMOS.");
        // Implement actual disconnection logic here
    }
}