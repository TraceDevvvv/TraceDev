package com.example.classregistry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Custom exception for SMOS server-related errors.
 * This helps in distinguishing server-specific issues from other application errors.
 */
class SMOSServerException extends Exception {
    public SMOSServerException(String message) {
        super(message);
    }

    public SMOSServerException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Simulates the SMOS (Student Management and Operations System) server.
 * This class provides methods to connect, disconnect, and retrieve class register data.
 * It acts as a data source for the ClassRegistryView.
 */
public class SMOSServer {
    // Stores register entries, mapped by class ID.
    // Each class ID maps to a list of RegisterEntry objects.
    private final Map<String, List<RegisterEntry>> classRegisters;
    private boolean isConnected; // Simulates the connection state to the server

    /**
     * Constructs a new SMOSServer instance.
     * Initializes the internal data structures and sets the connection state to disconnected.
     */
    public SMOSServer() {
        this.classRegisters = new HashMap<>();
        this.isConnected = false;
    }

    /**
     * Simulates establishing a connection to the SMOS server.
     * In a real application, this would involve network calls, authentication, etc.
     *
     * @throws SMOSServerException if the server is already connected or a simulated connection error occurs.
     */
    public void connect() throws SMOSServerException {
        if (isConnected) {
            throw new SMOSServerException("SMOS server is already connected.");
        }
        // Simulate a delay or potential failure for connection
        // For this simulation, we'll always succeed unless explicitly overridden for testing
        this.isConnected = true;
        System.out.println("SMOS Server: Connection established.");
    }

    /**
     * Simulates disconnecting from the SMOS server.
     * This fulfills the postcondition "Connection to the interrupted SMOS server."
     */
    public void disconnect() {
        if (isConnected) {
            this.isConnected = false;
            System.out.println("SMOS Server: Connection interrupted (closed).");
        } else {
            System.out.println("SMOS Server: No active connection to close.");
        }
    }

    /**
     * Retrieves the register entries for a given class ID from the simulated server.
     *
     * @param classId The unique identifier of the class.
     * @return An unmodifiable list of RegisterEntry objects for the specified class.
     *         Returns an empty list if no entries are found or if the class ID is invalid.
     * @throws SMOSServerException if the server is not connected or an error occurs during data retrieval.
     */
    public List<RegisterEntry> getRegisterEntries(String classId) throws SMOSServerException {
        if (!isConnected) {
            throw new SMOSServerException("SMOS server is not connected. Cannot retrieve data.");
        }
        Objects.requireNonNull(classId, "Class ID cannot be null.");

        // Simulate data retrieval
        List<RegisterEntry> entries = classRegisters.getOrDefault(classId, new ArrayList<>());
        System.out.println("SMOS Server: Retrieved " + entries.size() + " entries for class ID " + classId + ".");
        return Collections.unmodifiableList(entries);
    }

    /**
     * Helper method to add a single register entry for a specific class.
     * This is used to populate dummy data for the simulation.
     *
     * @param classId The ID of the class to which the entry belongs.
     * @param entry The RegisterEntry object to add.
     */
    public void addRegisterEntry(String classId, RegisterEntry entry) {
        this.classRegisters.computeIfAbsent(classId, k -> new ArrayList<>()).add(entry);
    }

    /**
     * Helper method to add a list of register entries for a specific class.
     * This is used to populate dummy data for the simulation.
     *
     * @param classId The ID of the class to which the entries belong.
     * @param entries The list of RegisterEntry objects to add.
     */
    public void addRegisterEntry(String classId, List<RegisterEntry> entries) {
        this.classRegisters.computeIfAbsent(classId, k -> new ArrayList<>()).addAll(entries);
    }
}