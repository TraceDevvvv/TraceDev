package com.example.convention;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Represents an external ETOUR System.
 * This class simulates an external service that provides convention data.
 * Marked as External System in Class Diagram.
 */
public class EtourSystem {

    private static final Random random = new Random();
    private boolean simulateConnectionFailure = false;

    /**
     * Simulates requesting convention data from the ETOUR system for a given Point of Rest ID.
     *
     * @param pointOfRestId The ID of the Point of Rest for which to request convention data.
     * @return A list of {@link Convention} objects.
     * @throws EtourConnectionException if the connection is simulated to fail.
     */
    public List<Convention> requestConventionData(String pointOfRestId) {
        System.out.println("ETOUR System: Receiving request for convention data for pointOfRestId: " + pointOfRestId);

        if (simulateConnectionFailure) {
            // Simulate a network or external system error
            System.err.println("ETOUR System: Simulating connection interruption for " + pointOfRestId);
            throw new EtourConnectionException("Failed to connect to ETOUR server.");
        }

        // Simulate fetching data based on pointOfRestId
        // In a real scenario, this would involve API calls, database queries, etc.
        List<Convention> conventions = new ArrayList<>();
        if ("POR-123".equals(pointOfRestId)) {
            conventions.addAll(Arrays.asList(
                new Convention("CONV-001", "Summer Retreat 2022", new Date(1657000000000L), "Annual summer gathering.", "POR-123"),
                new Convention("CONV-002", "Winter Summit 2023", new Date(1673000000000L), "Biennial winter conference.", "POR-123"),
                new Convention("CONV-003", "Spring Meetup 2023", new Date(1679000000000L), "Local community meetup.", "POR-123")
            ));
        } else if ("POR-456".equals(pointOfRestId)) {
            conventions.addAll(Arrays.asList(
                new Convention("CONV-004", "Global Forum 2021", new Date(1633000000000L), "International discussion panel.", "POR-456"),
                new Convention("CONV-005", "TechFest 2022", new Date(1666000000000L), "Technology exhibition.", "POR-456")
            ));
        } else {
            // For unknown IDs, return an empty list or some default data
            System.out.println("ETOUR System: No convention data found for " + pointOfRestId);
        }

        System.out.println("ETOUR System: Sending " + conventions.size() + " conventions for " + pointOfRestId);
        return conventions;
    }

    /**
     * Sets the simulation mode for connection failure.
     * @param simulateConnectionFailure true to simulate connection failure, false otherwise.
     */
    public void setSimulateConnectionFailure(boolean simulateConnectionFailure) {
        this.simulateConnectionFailure = simulateConnectionFailure;
    }
}