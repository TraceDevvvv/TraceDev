package com.example.presentation;

import com.example.application.ParentStudentService;
import com.example.exception.AuthorizationException;
import com.example.exception.ServiceException;
import com.example.exception.ValidationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the UI form for managing Parent-Student associations.
 * Acts as the boundary between the Admin (actor) and the application layer.
 */
public class ParentStudentManagementForm {

    private String parentId;
    private List<String> selectedStudentIds; // Represents the students selected by the admin on the form
    private final ParentStudentService parentStudentService;

    /**
     * Constructor for the form, injecting the ParentStudentService dependency.
     * @param parentStudentService The service to interact with for business logic.
     */
    public ParentStudentManagementForm(ParentStudentService parentStudentService) {
        this.parentStudentService = parentStudentService;
        this.selectedStudentIds = new ArrayList<>();
    }

    /**
     * Simulates the form being displayed to the administrator.
     * It fetches initial associated students and presents them.
     * @param adminParentId The ID of the parent whose associations are to be managed.
     * @param associatedStudentIds Initial list of student IDs associated with the parent.
     */
    public void displayForm(String adminParentId, List<String> associatedStudentIds) {
        this.parentId = adminParentId;
        this.selectedStudentIds = new ArrayList<>(associatedStudentIds); // Pre-fill with current associations
        System.out.println("--- Parent-Student Management Form ---");
        System.out.println("Parent ID: " + this.parentId);
        System.out.println("Currently Associated Students: " + this.selectedStudentIds);
        System.out.println("Please select desired students and click 'Send'.");
        System.out.println("------------------------------------");
    }

    /**
     * Getter for the parent ID currently loaded in the form.
     * @return The parent ID.
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Getter for the list of student IDs currently selected on the form.
     * @return A list of selected student IDs.
     */
    public List<String> getSelectedStudentIds() {
        return new ArrayList<>(selectedStudentIds); // Return a copy
    }

    /**
     * Simulates the administrator selecting students on the form.
     * This method would typically be called by the UI framework.
     * @param newSelections The new list of student IDs chosen by the admin.
     */
    public void simulateStudentSelection(List<String> newSelections) {
        this.selectedStudentIds = new ArrayList<>(newSelections);
        System.out.println("[Form] Admin selected new students: " + this.selectedStudentIds);
    }

    /**
     * Simulates the administrator clicking the 'Send' button on the form.
     * This triggers the association management process in the application layer.
     */
    public void onClickSendButton() {
        System.out.println("[Form] 'Send' button clicked for Parent ID: " + parentId);
        try {
            // Call the application service to manage associations
            parentStudentService.manageStudentAssociations(parentId, selectedStudentIds);
            handleAssociationUpdateResult(true, "Student associations updated successfully.");
        } catch (ValidationException e) {
            displayError("Validation Failed: " + e.getMessage());
        } catch (AuthorizationException e) {
            displayError("Authorization Failed: " + e.getMessage());
        } catch (ServiceException e) {
            displayError("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Handles the result of an association update operation, displaying appropriate messages.
     * This method is called by the ParentStudentService to update the UI.
     * @param success True if the operation was successful, false otherwise.
     * @param message A message describing the outcome.
     */
    public void handleAssociationUpdateResult(boolean success, String message) {
        if (success) {
            displayConfirmation(message);
        } else {
            // In the SD, displayError is called directly from Form.
            // This method is provided to satisfy the CD specification for handleAssociationUpdateResult
            // If the SD were followed strictly, this 'else' branch might be unreachable if errors are caught earlier.
            displayError("Failed to update associations: " + message);
        }
    }

    /**
     * Displays a confirmation message to the administrator.
     * @param message The confirmation message.
     */
    public void displayConfirmation(String message) {
        System.out.println("[Form - Confirmation] " + message);
    }

    /**
     * Displays an error message to the administrator.
     * @param message The error message.
     */
    public void displayError(String message) {
        System.err.println("[Form - Error] " + message);
    }
}