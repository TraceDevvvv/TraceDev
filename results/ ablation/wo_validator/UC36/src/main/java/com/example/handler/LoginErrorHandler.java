package com.example.handler;

import com.example.service.AuthenticationService;
import com.example.service.NotificationService;
import com.example.model.LoginCredentials;
import com.example.command.NotifyUserCommand;
import com.example.command.ConfirmNotificationCommand;
import com.example.command.RecoverStateCommand;

/**
 * Handles the incorrect login scenario as per the sequence diagram.
 */
public class LoginErrorHandler {
    private AuthenticationService authenticationService;
    private NotificationService notificationService;

    public LoginErrorHandler(AuthenticationService authenticationService, NotificationService notificationService) {
        this.authenticationService = authenticationService;
        this.notificationService = notificationService;
    }

    /**
     * Handles the incorrect login scenario.
     * This method implements the flow described in the sequence diagram.
     *
     * @param credentials the login credentials to validate
     */
    public void handleIncorrectLogin(LoginCredentials credentials) {
        // Step 1: Validate the login credentials.
        boolean isValid = authenticationService.validateLoginCredentials(credentials);
        if (!isValid) {
            // Step 2: Notify the user.
            notifyUser();

            // Step 3: Confirm notification read.
            boolean confirmed = confirmNotificationRead();
            if (confirmed) {
                // Step 4: Recover the previous state.
                recoverPreviousState();
            }
        }
        // Exit Condition: return control to the user.
    }

    /**
     * Notifies the user about the invalid login.
     * This corresponds to the notifyUser() private method in the class diagram.
     */
    private void notifyUser() {
        // Create and execute the NotifyUserCommand.
        NotifyUserCommand notifyUserCommand = new NotifyUserCommand(notificationService, "Invalid credentials");
        notifyUserCommand.execute();
    }

    /**
     * Confirms that the user has read the notification.
     * This corresponds to the confirmNotificationRead() private method.
     *
     * @return true if the user confirms, false otherwise
     */
    private boolean confirmNotificationRead() {
        // Create and execute the ConfirmNotificationCommand.
        ConfirmNotificationCommand confirmCommand = new ConfirmNotificationCommand(notificationService, "Please confirm");
        confirmCommand.execute();
        return confirmCommand.isConfirmed();
    }

    /**
     * Recovers the previous session state.
     * This corresponds to the recoverPreviousState() private method.
     */
    private void recoverPreviousState() {
        // Create and execute the RecoverStateCommand.
        RecoverStateCommand recoverCommand = new RecoverStateCommand(authenticationService);
        recoverCommand.execute();
    }
}