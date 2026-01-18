
package com.example.smos.ui;

import com.example.smos.model.Teaching;
import com.example.smos.service.TeachingArchiveService;
import com.example.smos.exception.SMOSConnectionException;
// The AuthenticationService import is removed as it's not found and not used by the existing logic.

import java.util.List;

/**
 * Represents the user interface component for interacting with the SMOS system.
 * It acts as the entry point for user actions and displays results/errors.
 * Corresponds to the 'UserInterface' class in the UML Class Diagram.
 */
public class UserInterface {

    private final TeachingArchiveService teachingArchiveService;
    // The AuthenticationService field is removed as it's not found and not used by the existing logic.

    /**
     * Constructor for UserInterface, injecting necessary service dependencies.
     * @param teachingArchiveService The service to handle teaching archive operations.
     */
    public UserInterface(TeachingArchiveService teachingArchiveService) {
        this.teachingArchiveService = teachingArchiveService;
        // The assignment of authenticationService is removed as the field and parameter are removed.
    }

    /**
     * Simulates an administrator requesting to delete a teaching.
     * This method orchestrates the elimination process, interacting with the service layer
     * and handling potential errors.
     * Corresponds to the 'requestDeleteTeaching' message in the Sequence Diagram.
     * @param teachingId The ID of the teaching to be deleted.
     */
    public void requestDeleteTeaching(String teachingId) {
        System.out.println("\nUI: Administrator requests to eliminate teaching with ID: " + teachingId);
        System.out.println("UI: Calling TeachingArchiveService.eliminateTeaching...");
        try {
            // Call the service layer to perform the elimination
            List<Teaching> updatedList = teachingArchiveService.eliminateTeaching(teachingId);
            // If no exception, deletion was successful, and we have the updated list
            showUpdatedTeachings(updatedList);
        } catch (SMOSConnectionException e) {
            // Handle the SMOSConnectionException for the alternative flow
            System.out.println("UI: Caught SMOSConnectionException from service. Displaying error message.");
            showErrorMessage("Connection to SMOS server interrupted. Please try again later."); // Message from Service
            showErrorMessage("Failed to delete teaching due to server connection issue."); // Message to Admin
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.err.println("UI: An unexpected error occurred: " + e.getMessage());
            showErrorMessage("An unexpected error occurred. Please contact support.");
        }
    }

    /**
     * Displays the updated list of teachings to the user.
     * Corresponds to the 'showUpdatedTeachings' message in the Sequence Diagram.
     * @param updatedTeachingList The list of teachings to display.
     */
    public void showUpdatedTeachings(List<Teaching> updatedTeachingList) {
        System.out.println("\nUI: Displaying updated teaching list:");
        if (updatedTeachingList == null || updatedTeachingList.isEmpty()) {
            System.out.println("    No teachings available in the archive.");
        } else {
            for (Teaching teaching : updatedTeachingList) {
                System.out.println("    - " + teaching);
            }
        }
        System.out.println("UI: The selected teaching has been permanently removed (if it existed).");
    }

    /**
     * Displays an error message to the user.
     * Corresponds to the 'showErrorMessage' message in the Sequence Diagram.
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.err.println("UI Error: " + message);
    }

    /**
     * Displays details of a single teaching.
     * (Added based on audit requirement REQ-CD-001, but not directly used in this sequence diagram flow).\n     * @param teaching The teaching object to display.\n     */
    public void displayTeachingDetails(Teaching teaching) {
        if (teaching != null) {
            System.out.println("\nUI: Displaying Teaching Details:");
            System.out.println("    ID: " + teaching.getId());
            System.out.println("    Title: " + teaching.getTitle());
            System.out.println("    Author: " + teaching.getAuthor());
            System.out.println("    Description: " + teaching.getDescription());
        } else {
            System.out.println("\nUI: No teaching details to display.");
        }
    }
}
