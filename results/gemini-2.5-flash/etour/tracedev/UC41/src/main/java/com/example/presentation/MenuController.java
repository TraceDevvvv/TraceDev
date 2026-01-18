package com.example.presentation;

import com.example.application.AuthenticationService;
import com.example.application.ErrorHandlingService;
import com.example.application.MenuMapper;
import com.example.application.MenuService;
import com.example.domain.Credentials;
import com.example.domain.DailyMenu;
import com.example.domain.DayOfWeek;
import com.example.domain.NetworkConnectionException;
import com.example.domain.MenuItem;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner; // For simulating user input

/**
 * Controller in the MVC pattern for managing menu operations.
 * Orchestrates interactions between the view, service, and mappers.
 */
public class MenuController {
    private MenuService menuService;
    private MenuMapper menuMapper; // Added to satisfy requirement R12, R16
    private ErrorHandlingService errorHandlingService; // Added to satisfy requirement R19
    private AuthenticationService authenticationService; // Added to satisfy requirement R3

    // Views managed by this controller
    private DailyMenuFormView dailyMenuFormView;
    private MenuEditFormView menuEditFormView;
    private ConfirmationView confirmationView;
    private NotificationView notificationView;

    // State maintained by the controller
    private DayOfWeek currentSelectedDay;
    private MenuViewModel currentMenuViewModel; // Stores the menu data being edited/confirmed

    public MenuController(MenuService menuService, MenuMapper menuMapper,
                          ErrorHandlingService errorHandlingService,
                          AuthenticationService authenticationService,
                          DailyMenuFormView dailyMenuFormView,
                          MenuEditFormView menuEditFormView,
                          ConfirmationView confirmationView,
                          NotificationView notificationView) {
        this.menuService = menuService;
        this.menuMapper = menuMapper;
        this.errorHandlingService = errorHandlingService;
        this.authenticationService = authenticationService;
        this.dailyMenuFormView = dailyMenuFormView;
        this.menuEditFormView = menuEditFormView;
        this.confirmationView = confirmationView;
        this.notificationView = notificationView;
    }

    /**
     * Authenticates the operator.
     * This is the initial step as per R3.
     * @param credentials Operator's credentials.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticate(Credentials credentials) {
        System.out.println("[Controller] Authenticating operator...");
        boolean authResult = authenticationService.authenticate(credentials);
        if (!authResult) {
            onErrorDisplayed("Authentication failed. Invalid credentials.");
        }
        return authResult;
    }

    /**
     * Activates the menu editing process by displaying the day selection form.
     * Renamed from displayDaySelectionForm() to satisfy R4, R5.
     */
    public void activateMenuEditing() {
        System.out.println("[Controller] Activating menu editing.");
        dailyMenuFormView.display(new MenuViewModel()); // Display an empty view model for initial display
        onDaySelectionFormDisplayed(); // Callback from view
        formDisplayed(); // Call for sequence diagram traceability m4
    }

    /**
     * Callback method invoked when the day selection form has been displayed.
     * Satisfies R5.
     */
    public void onDaySelectionFormDisplayed() {
        System.out.println("[Controller] Day selection form is now displayed to the operator.");
        // Logic for handling operator input for day selection would go here in a real app.
        // For this simulation, the main method will call selectDay() directly.
    }

    /**
     * Traceability method for sequence diagram message m4: DaySelectionView -> Controller: formDisplayed().
     * This method conceptually represents the view signaling it has been displayed.
     * The actual internal processing logic is handled by onDaySelectionFormDisplayed().
     */
    public void formDisplayed() {
        System.out.println("[Controller] Traceability: Day selection form displayed (SD: formDisplayed()).");
        // Delegate to the established internal callback.
        onDaySelectionFormDisplayed();
    }


    /**
     * Processes the operator's selection of a day.
     * Satisfies R6, R7.
     * @param dayOfWeek The selected day.
     */
    public void selectDay(DayOfWeek dayOfWeek) {
        System.out.println("[Controller] Operator selected day: " + dayOfWeek);
        this.currentSelectedDay = dayOfWeek;
        onDaySelected(dayOfWeek); // Callback from view
    }

    /**
     * Traceability method for sequence diagram message m8: DaySelectionView -> Controller: daySelected(selectedDay).
     * This method conceptually represents the view signaling a day has been selected.
     * The operator's action is primarily handled by selectDay(DayOfWeek dayOfWeek).
     * To strictly match the diagram's message name, this method is added and delegates to the main logic.
     */
    public void daySelected(DayOfWeek selectedDay) {
        System.out.println("[Controller] Traceability: Day selected in view (SD: daySelected()): " + selectedDay);
        // The existing `selectDay` method is the primary handler for this input.
        selectDay(selectedDay);
    }

    /**
     * Callback method invoked when a day has been selected by the operator.
     * Satisfies R6, R7.
     * @param dayOfWeek The selected day.
     */
    public void onDaySelected(DayOfWeek dayOfWeek) {
        System.out.println("[Controller] Callback: Day " + dayOfWeek + " has been selected. Fetching menu data...");
        try {
            DailyMenu dailyMenu = menuService.getMenuForDay(dayOfWeek); // Step 5
            currentMenuViewModel = menuMapper.toMenuViewModel(dailyMenu); // R12, R16
            currentMenuViewModel.setSelectedDay(dayOfWeek); // Ensure day is set in VM
            menuEditFormView.display(currentMenuViewModel); // Step 6
            onMenuEditFormDisplayed(); // Callback from view
            editFormDisplayed(); // Call for sequence diagram traceability m17
        } catch (NetworkConnectionException e) {
            String errorMessage = errorHandlingService.handleNetworkError(e); // R19
            notificationView.displayError(errorMessage);
            onErrorDisplayed(errorMessage); // R14, R19
            errorDisplayed(); // Call for sequence diagram traceability m47
        }
    }

    /**
     * Callback method invoked when the menu edit form has been displayed.
     * Satisfies R9.
     */
    public void onMenuEditFormDisplayed() {
        System.out.println("[Controller] Menu edit form for " + currentSelectedDay + " is now displayed to the operator.");
        // Logic for handling operator edits would be here.
        // For this simulation, the main method will call submitDailyMenu() directly.
    }

    /**
     * Traceability method for sequence diagram message m17: MenuEditView -> Controller: editFormDisplayed().
     * This method conceptually represents the view signaling it has been displayed.
     * The actual internal processing logic is handled by onMenuEditFormDisplayed().
     */
    public void editFormDisplayed() {
        System.out.println("[Controller] Traceability: Menu edit form displayed (SD: editFormDisplayed()).");
        onMenuEditFormDisplayed();
    }

    /**
     * Submits the updated daily menu data from the view.
     * @param menuData The updated menu data as a ViewModel.
     */
    public void submitDailyMenu(MenuViewModel menuData) {
        System.out.println("[Controller] Operator submitted updated menu data for: " + menuData.getSelectedDay());
        this.currentMenuViewModel = menuData; // Update controller's state with latest view data

        DailyMenu dailyMenuToUpdate = menuMapper.toDailyMenu(menuData); // R12, R16

        // Step 9: System verifies data.
        boolean isValid = menuService.updateMenu(dailyMenuToUpdate);

        if (isValid) {
            confirmationView.display(currentMenuViewModel); // Step 10
            onConfirmationDisplayed(); // R13
            confirmationDisplayed(); // Call for sequence diagram traceability m30
        } else {
            String errorMessage = errorHandlingService.handleValidationFailure();
            notificationView.displayError(errorMessage); // Step 11
            onErrorDisplayed(errorMessage); // R14
            errorDisplayed(); // Call for sequence diagram traceability m47
        }
    }

    /**
     * Callback method invoked when the confirmation view has been displayed.
     * Satisfies R13.
     */
    public void onConfirmationDisplayed() {
        System.out.println("[Controller] Confirmation view is now displayed. Awaiting operator confirmation...");
        // Logic for handling operator confirmation/cancellation would be here.
        // For this simulation, the main method will call confirmOperation() or cancelOperation().
    }

    /**
     * Traceability method for sequence diagram message m30: ConfirmationView -> Controller: confirmationDisplayed().
     * This method conceptually represents the view signaling it has been displayed.
     * The actual internal processing logic is handled by onConfirmationDisplayed().
     */
    public void confirmationDisplayed() {
        System.out.println("[Controller] Traceability: Confirmation view displayed (SD: confirmationDisplayed()).");
        onConfirmationDisplayed();
    }


    /**
     * Confirms the menu operation, leading to saving the changes.
     */
    public void confirmOperation() {
        System.out.println("[Controller] Operator confirmed the operation.");
        // Step 12: Operator confirms.

        DailyMenu dailyMenuToSave = menuMapper.toDailyMenu(currentMenuViewModel); // R12, R16

        try {
            // Step 13: System saves changes.
            boolean saveSuccessful = menuService.saveConfirmedMenu(dailyMenuToSave);
            if (saveSuccessful) {
                notificationView.displaySuccess("Menu successfully updated");
                onSuccessDisplayed("Menu successfully updated"); // R17
                successDisplayed(); // Call for sequence diagram traceability m42
            } else {
                String errorMessage = "Failed to save menu changes.";
                notificationView.displayError(errorMessage);
                onErrorDisplayed(errorMessage); // R14
                errorDisplayed(); // Call for sequence diagram traceability m47
            }
        } catch (NetworkConnectionException e) {
            String errorMessage = errorHandlingService.handleNetworkError(e); // R19
            notificationView.displayError(errorMessage);
            onErrorDisplayed(errorMessage); // R14, R19
            errorDisplayed(); // Call for sequence diagram traceability m47
        }
    }

    /**
     * Cancels the current menu operation.
     */
    public void cancelOperation() {
        System.out.println("[Controller] Operator cancelled the operation.");
        String message = "Operation cancelled by operator.";
        notificationView.displayMessage(message);
        onMessageDisplayed(message); // R18
        messageDisplayed(); // Call for sequence diagram traceability m52
        // Optionally, reset controller state or navigate to a default view
        currentSelectedDay = null;
        currentMenuViewModel = null;
        activateMenuEditing(); // Go back to day selection or main menu
    }

    /**
     * Callback method invoked when a success message has been displayed.
     * Satisfies R17.
     * @param message The success message.
     */
    public void onSuccessDisplayed(String message) {
        System.out.println("[Controller] Callback: Success message displayed: '" + message + "'");
        // Exit Condition: Successful modification.
        // Potentially navigate back to day selection or main screen.
        activateMenuEditing(); // For simulation, go back to day selection.
    }

    /**
     * Traceability method for sequence diagram message m42: NotificationView -> Controller: successDisplayed().
     * This method conceptually represents the view signaling a success notification has been displayed.
     * The actual internal processing logic is handled by onSuccessDisplayed(String message).
     */
    public void successDisplayed() {
        System.out.println("[Controller] Traceability: Success notification displayed (SD: successDisplayed()).");
        // The existing logic expects a message, so pass a generic one for traceability purposes.
        onSuccessDisplayed("Operation successful for traceability.");
    }

    /**
     * Callback method invoked when an error message has been displayed.
     * Satisfies R14, R19.
     * @param message The error message.
     */
    public void onErrorDisplayed(String message) {
        System.err.println("[Controller] Callback: Error message displayed: '" + message + "'");
        // Exit Condition: Error, flow terminates (or allows retry depending on error type).
        // For simulation, we might stop or prompt for retry.
        // For validation errors, the form might remain visible for corrections.
        // For critical errors like network, usually return to a safe state.
        if (currentMenuViewModel != null && message.contains("Validation failed")) {
            // If it was a validation error, show the edit form again with the error message
            currentMenuViewModel.setMessage(message);
            menuEditFormView.display(currentMenuViewModel);
            onMenuEditFormDisplayed();
            editFormDisplayed(); // Also trace this
        } else {
            // For network errors or other critical issues, restart the flow or show a general error screen
            activateMenuEditing();
        }
    }

    /**
     * Traceability method for sequence diagram message m47: NotificationView -> Controller: errorDisplayed().
     * This method conceptually represents the view signaling an error notification has been displayed.
     * The actual internal processing logic is handled by onErrorDisplayed(String message).
     */
    public void errorDisplayed() {
        System.err.println("[Controller] Traceability: Error notification displayed (SD: errorDisplayed()).");
        // The existing logic expects a message, so pass a generic one for traceability purposes.
        onErrorDisplayed("An error occurred for traceability.");
    }

    /**
     * Callback method invoked when a general message has been displayed.
     * Satisfies R18.
     * @param message The general message.
     */
    public void onMessageDisplayed(String message) {
        System.out.println("[Controller] Callback: General message displayed: '" + message + "'");
        // Exit Condition: Operator cancels.
        // Potentially navigate back to a default screen.
    }

    /**
     * Traceability method for sequence diagram message m52: NotificationView -> Controller: messageDisplayed().
     * This method conceptually represents the view signaling a general message notification has been displayed.
     * The actual internal processing logic is handled by onMessageDisplayed(String message).
     */
    public void messageDisplayed() {
        System.out.println("[Controller] Traceability: General message notification displayed (SD: messageDisplayed()).");
        // The existing logic expects a message, so pass a generic one for traceability purposes.
        onMessageDisplayed("A general message for traceability.");
    }

    // Helper for simulating user input for the main application class
    public MenuViewModel createSampleMenuViewModel(DayOfWeek day, String message, boolean emptyMenu) {
        List<MenuItem> items;
        if (emptyMenu) {
            items = Arrays.asList();
        } else {
            items = Arrays.asList(
                    new MenuItem("Updated Burger", "Gourmet burger with cheese", 15.00, true),
                    new MenuItem("Fries", "Crispy golden fries", 4.00, true)
            );
        }
        return new MenuViewModel(day, items, message);
    }
}