package com.example.justification.service;

import com.example.justification.model.Justification;
import com.example.justification.model.JustificationStatus;
import com.example.justification.exception.JustificationNotFoundException;
import com.example.justification.exception.SMOSServerException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Service layer responsible for managing Justification entities,
 * including operations like eliminating a justification.
 * This class encapsulates the business logic for justification management.
 */
public class JustificationService {

    // In-memory data store to simulate a database for Justification objects.
    // In a real application, this would be replaced by a repository interacting with a database.
    private final Map<String, Justification> justificationStore = new HashMap<>();

    /**
     * Constructor for JustificationService.
     * Initializes the service with some sample data for demonstration purposes.
     */
    public JustificationService() {
        // Populate with some initial justifications for testing purposes.
        // These justifications are assumed to be "viewed" by the administrator.
        justificationStore.put("J001", new Justification("J001", "Employee sick leave request for John Doe", JustificationStatus.ACTIVE));
        justificationStore.put("J002", new Justification("J002", "Project delay due to external vendor issues", JustificationStatus.ACTIVE));
        justificationStore.put("J003", new Justification("J003", "Budget overrun approval for Q3 marketing campaign", JustificationStatus.PENDING));
        justificationStore.put("J004", new Justification("J004", "Travel expense report for Jane Smith", JustificationStatus.ACTIVE));
    }

    /**
     * Retrieves a justification by its ID.
     * This method is useful for the "viewdetalticaustifica" precondition.
     *
     * @param id The unique identifier of the justification.
     * @return An Optional containing the Justification if found, or empty if not.
     */
    public Optional<Justification> getJustificationById(String id) {
        return Optional.ofNullable(justificationStore.get(id));
    }

    /**
     * Eliminates a justification from the system by marking its status as DELETED.
     * This operation directly implements the "Eliminate Justification" use case.
     *
     * Preconditions (assumed to be handled by calling layers):
     * - The user is logged in as an administrator.
     * - The system is currently viewing the details of the justification identified by {@code justificationId}.
     * - The "Delete" action has been triggered by the administrator.
     *
     * Postconditions:
     * - The justification's status is successfully changed to {@link JustificationStatus#DELETED}.
     * - If an external system (like SMOS) interaction is required, it's simulated here.
     * - The method returns the updated Justification object.
     *
     * Edge Cases / Error Handling:
     * - Throws {@link JustificationNotFoundException} if no justification with the given ID is found.
     * - Throws {@link SMOSServerException} if there's an issue connecting to or communicating with the SMOS server
     *   during the elimination process, simulating an "interrupted connection".
     *
     * @param justificationId The unique identifier of the justification to be eliminated.
     * @return The updated Justification object with its status set to {@link JustificationStatus#DELETED}.
     * @throws JustificationNotFoundException If no justification with the given ID exists in the system.
     * @throws SMOSServerException If an error occurs during communication with the SMOS server.
     */
    public Justification eliminateJustification(String justificationId) throws JustificationNotFoundException, SMOSServerException {
        // 1. Attempt to retrieve the justification from the store.
        Justification justification = justificationStore.get(justificationId);

        // Check if the justification exists.
        if (justification == null) {
            // If not found, throw a specific exception as per the use case's error handling.
            throw new JustificationNotFoundException("Justification with ID '" + justificationId + "' not found. Cannot eliminate.");
        }

        // 2. Simulate interaction with an external system, e.g., SMOS server.
        // The use case mentions "Connection to the SMOS server interrupted" as a postcondition
        // if the administrator interrupts the operation. This simulates such a scenario.
        // For demonstration, we'll simulate a SMOS server failure for a specific justification ID ("J003").
        // In a real application, this would involve actual network calls to an external service.
        if ("J003".equals(justificationId)) {
            System.err.println("Simulating SMOS server connection interruption for justification ID: " + justificationId);
            throw new SMOSServerException("Failed to communicate with SMOS server for justification ID '" + justificationId + "'. Connection interrupted.");
        }

        // 3. Update the justification's status to DELETED.
        // This marks the justification as eliminated in the system.
        justification.setStatus(JustificationStatus.DELETED);

        // In a real application, this change would be persisted to a database.
        // For this in-memory store, updating the object in the map is sufficient to reflect the change.
        justificationStore.put(justificationId, justification); // Ensure the updated object is stored.

        System.out.println("Justification '" + justificationId + "' successfully eliminated (status set to DELETED).");

        // Return the updated justification object.
        return justification;
    }

    /**
     * Adds a new justification to the in-memory store.
     * This is a utility method primarily for setting up test data or initial state,
     * and is not directly part of the "Eliminate Justification" use case flow.
     *
     * @param justification The Justification object to add.
     */
    public void addJustification(Justification justification) {
        if (justification != null && justification.getId() != null) {
            justificationStore.put(justification.getId(), justification);
        }
    }
}