package com.example.app;

/**
 * Handles logout requests, orchestrating the logout process by interacting with
 * the authentication service and updating the view.
 */
public class LogoutController {
    // - authenticationService : AuthenticationService
    private AuthenticationService authenticationService;

    /**
     * Constructs a LogoutController with a dependency on AuthenticationService.
     *
     * @param authenticationService The AuthenticationService instance to use for authentication operations.
     */
    public LogoutController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Handles a logout request for a specific user.
     * It instructs the authentication service to terminate the user's session
     * and then tells the view to display the login form.
     *
     * @param userId The ID of the user requesting to log out.
     * @param logoutView The LogoutView instance to interact with for UI updates.
     */
    public void handleLogoutRequest(String userId, LogoutView logoutView) {
        System.out.println("LogoutController: Received logout request for user: " + userId);
        // Controller -> AuthService: terminateUserSession(userId)
        boolean sessionTerminated = authenticationService.terminateUserSession(userId);

        if (sessionTerminated) {
            System.out.println("LogoutController: User session successfully terminated.");
        } else {
            System.out.println("LogoutController: Failed to terminate user session.");
        }

        // Controller -> View: displayLoginForm()
        logoutView.displayLoginForm();
    }
}