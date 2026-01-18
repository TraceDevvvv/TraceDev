package com.example.attendancetracker.external;

/**
 * Represents an external SMOS (Student Management and Operations System) server.
 * Simulates its availability.
 * <<External>> system.
 */
public class SMOSServer {
    // Static flag to simulate server availability.
    // Can be changed to simulate connection interruptions.
    private static boolean isAvailable = true;

    /**
     * Checks if the SMOS server is currently available.
     * @return true if available, false otherwise.
     */
    public static boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability status of the SMOS server.
     * @param available New availability status.
     */
    public static void setAvailable(boolean available) {
        isAvailable = available;
        System.out.println("[SMOSServer] Availability set to: " + (available ? "AVAILABLE" : "UNAVAILABLE"));
    }
}