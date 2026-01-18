package com.activeconvention.data;

import com.activeconvention.model.Convention;
import com.activeconvention.model.ConventionRequest;
import com.activeconvention.util.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * ConventionRepository class for data access operations related to conventions.
 * This class simulates interaction with a "point of rest" (e.g., a database or external API)
 * to retrieve and store convention-related data.
 * It acts as the 'Data Access Layer' in the architecture.
 */
public class ConventionRepository {

    // Simulate a database or external data source for ConventionRequests
    // Key: requestId, Value: ConventionRequest
    private final ConcurrentHashMap<String, ConventionRequest> pendingConventionRequests = new ConcurrentHashMap<>();

    // Simulate a database or external data source for Convention details
    // Key: conventionId, Value: Convention
    private final ConcurrentHashMap<String, Convention> allConventions = new ConcurrentHashMap<>();

    /**
     * Constructor for ConventionRepository.
     * Initializes with some dummy data for demonstration purposes.
     */
    public ConventionRepository() {
        // Populate with dummy data
        // Dummy Convention 1
        Convention conv1 = new Convention("CONV001", "Grand Hotel", "2023-10-26", "2023-10-28",
                "Standard terms and conditions apply. Includes accommodation and meals.", "Pending");
        allConventions.put(conv1.getId(), conv1);
        pendingConventionRequests.put("REQ001", new ConventionRequest("REQ001", "CONV001", "Grand Hotel", new Date(), "Pending Review"));

        // Dummy Convention 2
        Convention conv2 = new Convention("CONV002", "City Conference Center", "2023-11-15", "2023-11-17",
                "Premium package with advanced AV equipment. Payment due 7 days prior.", "Pending");
        allConventions.put(conv2.getId(), conv2);
        pendingConventionRequests.put("REQ002", new ConventionRequest("REQ002", "CONV002", "City Conference Center", new Date(), "Pending Review"));

        // Dummy Convention 3 (already activated, should not appear in pending list)
        Convention conv3 = new Convention("CONV003", "Lakeside Resort", "2023-09-01", "2023-09-05",
                "Outdoor activities included. Weather dependent.", "Activated");
        allConventions.put(conv3.getId(), conv3);
        // No pending request for CONV003 as it's already activated

        Logger.logInfo("ConventionRepository initialized with dummy data.");
    }

    /**
     * Retrieves a list of all pending convention requests.
     * In a real system, this would query a database or an external service.
     *
     * @return A list of ConventionRequest objects with "Pending Review" status.
     */
    public List<ConventionRequest> getPendingConventionRequests() {
        Logger.logInfo("Retrieving pending convention requests from simulated data source.");
        // Filter requests that are still pending review
        return pendingConventionRequests.values().stream()
                .filter(req -> "Pending Review".equals(req.getStatus()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a Convention object by its ID.
     *
     * @param id The unique identifier of the convention.
     * @return The Convention object if found, otherwise null.
     */
    public Convention getConventionById(String id) {
        Logger.logInfo("Retrieving convention by ID: " + id);
        return allConventions.get(id);
    }

    /**
     * Saves the activated convention. This typically means updating its status
     * and potentially removing it from the pending requests list.
     *
     * @param convention The Convention object that has been activated.
     */
    public void saveActivatedConvention(Convention convention) {
        Logger.logInfo("Saving activated convention: " + convention.getId());
        // Update the status in the main conventions store
        allConventions.put(convention.getId(), convention);

        // Remove the corresponding request from the pending list
        // Find the request associated with this convention ID and remove it
        Optional<String> requestIdToRemove = pendingConventionRequests.entrySet().stream()
                .filter(entry -> entry.getValue().getConventionId().equals(convention.getId()))
                .map(entry -> entry.getKey())
                .findFirst();

        requestIdToRemove.ifPresent(pendingConventionRequests::remove);

        Logger.logInfo("Convention " + convention.getId() + " status updated to 'Activated' and removed from pending requests.");
    }
}