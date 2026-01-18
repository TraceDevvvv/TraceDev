package com.example.tagsystem.presentation;

import com.example.tagsystem.application.InsertTagService;
import com.example.tagsystem.infrastructure.ETOURSystem;
import com.example.tagsystem.errorhandling.InvalidTagDataException;
import com.example.tagsystem.errorhandling.TagAlreadyExistsException;

/**
 * Handles user interactions related to tag management.
 * Acts as the entry point for the "Insert new tag" use case from the Presentation Layer.
 */
public class TagController {
    // Dependency: InsertTagService, responsible for application logic
    public InsertTagService insertTagService;
    // Dependency: ETOURSystem for integration purposes
    public ETOURSystem etourSystem;

    /**
     * Constructor for TagController.
     * @param insertTagService The service to handle tag insertion logic.
     * @param etourSystem The external system to interact with.
     */
    public TagController(InsertTagService insertTagService, ETOURSystem etourSystem) {
        this.insertTagService = insertTagService;
        this.etourSystem = etourSystem;
    }

    /**
     * Simulates accessing and displaying the tag creation form.
     * This method is an entry point from the user interface.
     */
    public void accessAndDisplayTagForm() {
        System.out.println("\n[TagController] Agency Operator accesses tag form.");
        displayTagForm();
    }

    /**
     * Simulates displaying the tag form to the user.
     * In a real application, this would render a UI component.
     */
    private void displayTagForm() {
        System.out.println("[TagController] Displaying tag form to Agency Operator.");
        // Placeholder for UI rendering logic
    }

    /**
     * Handles the submission of the tag form.
     * This method orchestrates the call to the application service and handles potential exceptions.
     * @param tagName The name of the tag to be inserted.
     */
    public void submitTagForm(String tagName) {
        System.out.println("[TagController] Received tag submission for: '" + tagName + "'");
        try {
            // Delegate the tag insertion to the application service
            insertTagService.insertTag(tagName);
            // If successful, display a success notification and interrupt connection
            displaySuccessNotification("Tag '" + tagName + "' added successfully.");
            // As per sequence diagram, interrupt connection after successful operation
            etourSystem.interruptConnection();
        } catch (InvalidTagDataException e) {
            // Handle invalid data scenario
            System.err.println("[TagController] Error: " + e.getMessage());
            displayErrorNotification("Invalid tag data.");
        } catch (TagAlreadyExistsException e) {
            // Handle tag already exists scenario
            System.err.println("[TagController] Error: " + e.getMessage());
            displayErrorNotification("Tag already exists.");
        } catch (Exception e) {
            // Catch any unexpected exceptions
            System.err.println("[TagController] An unexpected error occurred: " + e.getMessage());
            displayErrorNotification("An unexpected error occurred during tag submission.");
        }
    }

    /**
     * Displays a success notification to the user.
     * In a real application, this would update a UI component.
     * @param message The success message to display.
     */
    public void displaySuccessNotification(String message) {
        System.out.println("[TagController] SUCCESS: " + message);
        // Placeholder for UI success notification logic
    }

    /**
     * Displays an error notification to the user.
     * In a real application, this would update a UI component.
     * @param message The error message to display.
     */
    public void displayErrorNotification(String message) {
        System.err.println("[TagController] ERROR: " + message);
        // Placeholder for UI error notification logic
    }
}