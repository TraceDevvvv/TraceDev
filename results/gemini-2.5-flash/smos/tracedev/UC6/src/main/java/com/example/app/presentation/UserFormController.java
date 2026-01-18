package com.example.app.presentation;

import com.example.app.application.AuthenticationService;
import com.example.app.application.UserCreationService;
import com.example.app.dtos.UserCreationRequestDTO;
import com.example.app.dtos.UserCreationResultDTO;
import com.example.app.infrastructure.ConnectionMonitor;
import com.example.app.infrastructure.ConnectionStatus;

import java.util.List;

/**
 * Controller for the UserFormView.
 * Acts as an intermediary between the UserFormView and the application serv.
 */
public class UserFormController {
    private final UserFormView view;
    private final UserCreationService userCreationService;
    private final AuthenticationService authenticationService; // Added to satisfy R3
    private final ConnectionMonitor connectionMonitor; // Added to satisfy R14
    private UserListView userListView; // Dependency added to satisfy R6

    public UserFormController(UserFormView view, UserCreationService userCreationService,
                              AuthenticationService authenticationService, ConnectionMonitor connectionMonitor) {
        this.view = view;
        this.userCreationService = userCreationService;
        this.authenticationService = authenticationService;
        this.connectionMonitor = connectionMonitor;
        this.view.setController(this); // Inject controller into view
    }

    /**
     * Sets the UserListView. This is done separately to avoid circular dependency
     * in constructor if UserListView also needs UserFormController,
     * or if they are created in different parts of the application startup.
     *
     * @param userListView The UserListView instance to be managed.
     */
    public void setUserListView(UserListView userListView) {
        this.userListView = userListView;
    }

    /**
     * Handles the event when the 'New User' button is clicked, typically from UserListView.
     * Initiates the display of an empty user creation form.
     */
    public void handleNewUserClick() {
        System.out.println("\n[UserFormController] Handling new user click.");
        // R3: Authentication check (not explicitly shown in sequence for this step, but implied for admin actions)
        if (!authenticationService.isAuthenticated(authenticationService.getCurrentAdminLogin())) {
            System.out.println("[UserFormController] Admin not authenticated. Cannot display new user form.");
            // In a real app, redirect to login or show error.
            return;
        }
        view.displayEmptyForm();
    }

    /**
     * Handles the event when the 'Save' button is clicked on the UserFormView.
     * This method orchestrates the user creation process, including validation,
     * interaction with the application service, and feedback to the view.
     *
     * @param request The DTO containing the user input data from the form. This is
     *                passed here for simulation convenience, effectively representing
     *                the data collected by `view.getUserInput()` in the sequence diagram.
     */
    public void handleSaveButtonClick(UserCreationRequestDTO request) {
        System.out.println("\n[UserFormController] Handling save button click.");

        // R3: Authentication check for save operation
        if (!authenticationService.isAuthenticated(authenticationService.getCurrentAdminLogin())) {
            System.out.println("[UserFormController] Admin not authenticated. Cannot save user.");
            // In a real app, redirect to login or show error.
            view.displayValidationErrors(List.of("Authentication required to save user."));
            return;
        }

        // Simulate UFC -> UEF : getUserInput() and UEF --> UFC : userCreationRequest
        // The 'request' parameter effectively is the result of UEF.getUserInput() here.
        System.out.println("[UserFormController] Retrieved user input from view. (m10 trace)"); // m10 trace
        System.out.println("[UserFormController] Retrieves data from UserEntryForm. (m12)"); // m12 trace

        // R14: Check connection status before proceeding with heavy operations
        ConnectionStatus status = connectionMonitor.checkConnectionStatus();
        if (status == ConnectionStatus.INTERRUPTED) {
            System.out.println("[UserFormController] Connection is interrupted. Notifying view.");
            view.displayConnectionLostNotification();
            // The sequence diagram ends here for an interrupted connection scenario.
            return;
        }

        // Connection is stable, proceed with user creation
        UserCreationResultDTO result = userCreationService.createUser(request);

        if (result.success) {
            view.displaySuccessMessage("User " + result.createdUserLogin + " created successfully.");
            // After successful creation, refresh the user list in UserListView
            if (userListView != null) {
                userListView.refreshUserList();
            } else {
                System.out.println("[UserFormController] UserListView not set, cannot refresh list.");
            }
        } else {
            view.displayValidationErrors(result.validationErrors);
        }
    }
}