package com.example.convention;

import java.util.List;
import java.util.Collections;

/**
 * Controller layer class responsible for handling requests related to conventions.
 * Handles REQ4: Agency Operator accesses display.
 * Ensures REQ6: System displays history.
 * Utilizes ConventionDTO for REQ8 (accuracy).
 */
public class ConventionController {
    private final ConventionService conventionService;

    /**
     * Constructs a ConventionController with a dependency on ConventionService.
     *
     * @param conventionService The service used to access convention business logic.
     */
    public ConventionController(ConventionService conventionService) {
        this.conventionService = conventionService;
    }

    /**
     * Handles the request to view convention history for a specific Point of Rest.
     * Precondition for execution is REQ3: Point of Rest IS selected.
     *
     * @param pointOfRestId The ID of the Point of Rest for which to view history.
     * @return A list of {@link ConventionDTO} objects representing the history,
     *         or an empty list if an error occurs.
     */
    public List<ConventionDTO> viewConventionHistory(String pointOfRestId) {
        System.out.println("\nController: Received request to view convention history for pointOfRestId: " + pointOfRestId);
        // Entry Condition: Point of Rest IS selected. (REQ3)
        // Agency Operator accesses display of historical conventions. (REQ4)

        if (pointOfRestId == null || pointOfRestId.trim().isEmpty()) {
            System.err.println("Controller: Error: Point of Rest ID cannot be empty.");
            // Controller --x AO: Display Error
            return Collections.emptyList();
        }

        try {
            // Sequence diagram step: Controller -> Service: getConventionHistory(pointOfRestId)
            List<ConventionDTO> history = conventionService.getConventionHistory(pointOfRestId);
            System.out.println("Controller: Successfully retrieved " + history.size() + " convention DTOs.");
            // Exit Condition: System displays history accurately and promptly. (REQ6, REQ8)
            // Sequence diagram step: Controller --> AO: displayConventionHistory(List<ConventionDTO>)
            return history;
        } catch (EtourConnectionException e) {
            // Sequence diagram step: Controller --x AO: Display Error: "ETOUR server unavailable"
            System.err.println("Controller: Error: ETOUR server unavailable. " + e.getMessage());
            return Collections.emptyList(); // Return empty list or specific error DTO for UI
        } catch (Exception e) {
            System.err.println("Controller: An unexpected error occurred: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}