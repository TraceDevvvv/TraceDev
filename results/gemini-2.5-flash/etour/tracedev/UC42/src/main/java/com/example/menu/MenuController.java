package com.example.menu;

import java.util.List;
import java.util.Objects;

/**
 * The controller for menu-related operations, particularly menu deletion.
 * It mediates between the MenuView and the MenuDeletionService.
 * Handles user input events from the view and orchestrates application logic.
 */
public class MenuController {
    private final MenuDeletionService menuDeletionService;
    private final MenuView menuView;
    private final AuthenticationService authenticationService; // Added for REQ-R3
    private DayOfWeek selectedDay; // Stores the selected day across view interactions

    /**
     * Constructs a new MenuController.
     *
     * @param menuDeletionService The service responsible for handling menu deletion logic.
     * @param menuView The view responsible for displaying information and receiving input.
     * @param authenticationService The service responsible for user authentication.
     */
    public MenuController(MenuDeletionService menuDeletionService, MenuView menuView, AuthenticationService authenticationService) {
        this.menuDeletionService = Objects.requireNonNull(menuDeletionService, "MenuDeletionService cannot be null");
        this.menuView = Objects.requireNonNull(menuView, "MenuView cannot be null");
        this.authenticationService = Objects.requireNonNull(authenticationService, "AuthenticationService cannot be null");
        // It's crucial for the view to have a reference back to the controller for callbacks.
        // If not set in the MenuView constructor, set it here.
        this.menuView.setController(this);
    }

    /**
     * Handles the initiation of the delete menu process.
     * REQ-R3: First checks if the operator is authenticated.
     */
    public void handleDeleteMenuInitiation() {
        System.out.println("[MenuController] Initiating delete menu process.");
        // REQ-R3: Check authentication before proceeding.
        boolean authenticated = authenticationService.isAuthenticated();
        if (authenticated) {
            System.out.println("[MenuController] User authenticated. Proceeding with menu deletion.");
            // REQ-R5: Get all DayOfWeek values to display in the form.
            List<DayOfWeek> allDays = DayOfWeek.getValues();
            menuView.displayDeleteMenuForm(allDays);
        } else {
            // REQ-R3: Display error if authentication fails.
            menuView.displayErrorMessage("Authentication required or failed. Please log in.");
            System.err.println("[MenuController] Authentication failed. Aborting menu deletion initiation.");
        }
    }

    /**
     * REQ-R6: Handles the event when a day is selected in the view.
     * Stores the selected day for subsequent operations.
     *
     * @param dayOfWeek The DayOfWeek that was selected by the user.
     */
    public void handleDaySelection(DayOfWeek dayOfWeek) {
        this.selectedDay = dayOfWeek;
        System.out.println("[MenuController] Received day selection: " + dayOfWeek);
        // No direct view update here, as the view will then prompt for next action (submit/cancel)
    }

    /**
     * REQ-R7: Handles the submission of a delete request from the view.
     * Triggers the display of a confirmation prompt.
     *
     * @param dayOfWeek The DayOfWeek for which the delete request was submitted.
     */
    public void handleSubmitDeleteRequest(DayOfWeek dayOfWeek) {
        // Renamed from handleDeleteMenuRequest to improve clarity // REQ-R7
        System.out.println("[MenuController] Received delete request submission for " + dayOfWeek);
        // Ensure the selectedDay matches the day being processed
        if (!Objects.equals(this.selectedDay, dayOfWeek)) {
            menuView.displayErrorMessage("Error: Selected day changed unexpectedly. Please re-select.");
            return;
        }
        menuView.displayConfirmationPrompt(dayOfWeek);
    }

    /**
     * Handles the confirmation of a deletion operation.
     * Delegates the actual deletion to the MenuDeletionService.
     *
     * @param dayOfWeek The DayOfWeek confirmed for deletion.
     */
    public void handleConfirmDeletion(DayOfWeek dayOfWeek) {
        // REQ-R9
        System.out.println("[MenuController] Received confirmation for deletion of " + dayOfWeek);
        if (!Objects.equals(this.selectedDay, dayOfWeek)) {
            menuView.displayErrorMessage("Error: Confirmed day changed unexpectedly. Please restart the process.");
            return;
        }

        // Call the service to perform the deletion
        MenuDeletionService.DeletionResult result = menuDeletionService.deleteDailyMenu(dayOfWeek); // Updated to use DeletionResult

        if (result.success()) { // Check success from the result object
            menuView.displaySuccessMessage("Daily menu for " + dayOfWeek + " deleted successfully.");
        } else {
            // REQ-R13: Error message if deletion failed, likely due to NetworkConnectionException.
            String errorMessage = result.errorMessage() != null ? result.errorMessage() : "An unknown error occurred.";
            menuView.displayErrorMessage("Failed to delete daily menu for " + dayOfWeek + ". Error: " + errorMessage);
        }
        this.selectedDay = null; // Clear selected day after operation
    }

    /**
     * REQ-R12: Handles the cancellation of an operation from the view.
     */
    public void handleCancelOperation() {
        System.out.println("[MenuController] Operation was cancelled.");
        menuView.displayErrorMessage("Deletion operation cancelled.");
        this.selectedDay = null; // Clear selected day
    }
}