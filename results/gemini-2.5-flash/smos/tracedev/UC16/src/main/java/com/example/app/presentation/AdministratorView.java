package com.example.app.presentation;

import com.example.app.application.DeleteClassUseCaseController;
import com.example.app.application.DeletionFailedException;
import com.example.app.domain.ArchivedClass;
import com.example.app.infrastructure.ConnectionInterruptedException;

import java.util.List;

/**
 * Represents the Administrator's view for managing classes, specifically for deletion.
 * This class interacts with the user (simulated via console) and the application layer.
 */
public class AdministratorView {
    private final DeleteClassUseCaseController deleteClassUseCaseController;
    private String selectedClassId; // Simulates the ID of the class currently selected by the admin

    /**
     * Constructs an AdministratorView with a dependency on DeleteClassUseCaseController.
     * Dependency injection is used here.
     *
     * @param deleteClassUseCaseController The controller for the delete class use case.
     */
    public AdministratorView(DeleteClassUseCaseController deleteClassUseCaseController) {
        this.deleteClassUseCaseController = deleteClassUseCaseController;
    }

    /**
     * Sets the ID of the class currently selected by the administrator.
     * In a real UI, this would be set when an item in a list is clicked.
     *
     * @param selectedClassId The ID of the class to be selected.
     */
    public void setSelectedClassId(String selectedClassId) {
        this.selectedClassId = selectedClassId;
    }

    /**
     * Displays detailed information about a single class.
     * In a real UI, this would render class details on the screen.
     *
     * @param classObj The class object whose details are to be displayed.
     */
    public void displayClassDetails(ArchivedClass classObj) {
        if (classObj != null) {
            System.out.println("\\nUI: Displaying Class Details:");
            System.out.println("  ID: " + classObj.id);
            System.out.println("  Name: " + classObj.name);
            System.out.println("  Description: " + classObj.description);
            System.out.println("  Created: " + classObj.createdDate);
            System.out.println("  Last Modified: " + classObj.lastModifiedDate);
        } else {
            System.out.println("\\nUI: No class selected or details unavailable.");
        }
    }

    /**
     * Simulates the action when the delete button is clicked by the administrator.
     * This method triggers the deletion use case and handles its outcomes.
     */
    public void onDeleteButtonClick() {
        System.out.println("\\nAdmin -> UI: onDeleteButtonClick()");
        if (selectedClassId == null || selectedClassId.isEmpty()) {
            displayErrorMessage("Please select a class to delete.");
            return;
        }

        // Traceability: m3 (Retrieves selectedClassId from UI context)
        System.out.println("UI: Retrieves selectedClassId from UI context: " + selectedClassId);

        try {
            List<ArchivedClass> updatedClassList = deleteClassUseCaseController.deleteClass(selectedClassId);
            // If deleteClass did not throw an exception, it means deletion was successful
            // and an updated list (or empty list if findAll failed) was returned.
            displayUpdatedClassList(updatedClassList);
            // Traceability: m20 (Exit Condition: The selected class IS deleted from the archive.)
            System.out.println("Admin: Selected class is deleted from the archive. (Exit Condition)");
        } catch (DeletionFailedException e) {
            // REQ-002: Handle deletion failure due to data integrity
            displayErrorMessage("Failed to delete class due to data integrity issue. (UI -> Admin: Class deletion failed: integrity check failed.)");
        } catch (ConnectionInterruptedException e) {
            // REQ-001: Handle connection interruption
            // This catches both delete() and findAll() connection errors
            if (e.getMessage().contains("while retrieving updated list")) {
                displayErrorMessage("Deletion succeeded, but failed to retrieve updated list due to server connection loss. (UI -> Admin: Class list refresh failed: server connection lost.)");
            } else {
                displayErrorMessage("Failed to connect to archive server for deletion. (UI -> Admin: Deletion failed: server connection lost.)");
            }
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            displayErrorMessage("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Displays the updated list of classes.
     * In a real UI, this would refresh a list or table component.
     *
     * @param classList The list of classes to display.
     */
    public void displayUpdatedClassList(List<ArchivedClass> classList) {
        System.out.println("\\nUI: Displaying updated class list:");
        if (classList == null || classList.isEmpty()) {
            System.out.println("  (No classes available or list is empty)");
        } else {
            for (ArchivedClass cls : classList) {
                System.out.println("  - " + cls.name + " (ID: " + cls.id + ")");
            }
        }
    }

    /**
     * Displays an error message to the administrator.
     * Satisfies requirement REQ-001 and REQ-002.
     * In a real UI, this would show a dialog or an alert.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\\nUI: ERROR: " + message);
    }
}