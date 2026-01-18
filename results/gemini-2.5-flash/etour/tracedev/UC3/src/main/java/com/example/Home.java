package com.example;

/**
 * Configuration class to hold application-wide constants like RMI host.
 */
public class Home {
    // Assuming RMI server is running on localhost and default RMI port
    public static final String HOST = "localhost";
    public static final int RMI_PORT = 1099; // Default RMI registry port
    public static final String RMI_SERVICE_NAME = "CulturalHeritageAgencyManager";

    // Private constructor to prevent instantiation
    private Home() {
        // Configuration class
    }
}