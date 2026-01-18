package com.example;

/**
 * MenuController class as the controller for menu operations.
 * Orchestrates the flow of deleting a daily menu, including form display,
 * day selection, confirmation, and cancellation.
 */
public class MenuController {
    private MenuService menuService;
    private AuthenticationService authService;
    private UI ui;

    /**
     * Constructor that initializes MenuController with required serv.
     * @param menuService The MenuService to handle business logic.
     * @param authService The AuthenticationService for authentication checks.
     * @param ui The UI boundary component.
     */
    public MenuController(MenuService menuService, AuthenticationService authService, UI ui) {
        this.menuService = menuService;
        this.authService = authService;
        this.ui = ui;
    }

    /**
     * Displays the week selection form.
     * In a real application, this would interact with a UI component.
     */
    public void displayWeekForm() {
        System.out.println("Displaying week selection form.");
        renderWeekSelectionForm();
    }

    /**
     * Renders week selection form to UI.
     * Corresponds to message m5 in sequence diagram.
     */
    public void renderWeekSelectionForm() {
        ui.renderWeekSelectionForm();
    }

    /**
     * Handles the selection of a day by the operator.
     * Initiates the deletion request flow.
     * @param day The selected day of the week.
     */
    public void handleDaySelection(String day) {
        System.out.println("Handling selection for day: " + day);
        // Request deletion from MenuService.
        ConfirmationDTO confirmation = menuService.requestDeletion(day);
        if (confirmation.isRequiresConfirmation()) {
            System.out.println("Confirmation required: " + confirmation.getMessage());
            showConfirmationDialog(confirmation.getMessage());
        } else {
            // If no menu found, show error
            showErrorNotification(confirmation.getMessage());
        }
    }

    /**
     * Confirms the deletion of the daily menu for a given day.
     * Called when the operator confirms the operation in the UI.
     * @param day The day for which the menu should be deleted.
     */
    public void confirmDeletion(String day) {
        System.out.println("Confirming deletion for day: " + day);
        ResultDTO result = menuService.deleteDailyMenu(day);
        if (result.isSuccess()) {
            successNotification(result.getMessage());
        } else {
            errorNotification(result.getMessage());
        }
    }

    /**
     * Cancels the ongoing operation.
     * Called when the operator cancels the deletion.
     */
    public void cancelOperation() {
        System.out.println("Operation cancelled by operator.");
        cancellationConfirmed();
    }

    /**
     * Shows error notification to UI.
     * Corresponds to messages m15 and m34 in sequence diagram.
     */
    public void errorNotification(String message) {
        ui.showErrorNotification(message);
    }

    /**
     * Shows success notification to UI.
     * Corresponds to message m43 in sequence diagram.
     */
    public void successNotification(String message) {
        ui.showSuccessNotification(message);
    }

    /**
     * Shows confirmation dialog to UI.
     * Corresponds to message m24 in sequence diagram.
     */
    public void showConfirmationDialog(String message) {
        ui.showConfirmationDialog(message);
    }

    /**
     * Confirms cancellation to UI.
     * Corresponds to message m49 in sequence diagram.
     */
    public void cancellationConfirmed() {
        ui.showCancellationConfirmed();
    }
}