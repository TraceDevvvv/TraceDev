package com.example.login;

/**
 * Handles login requests, acting as an intermediary between the LoginView
 * and the UserService. It orchestrates the authentication flow.
 */
public class LoginController {
    private LoginView loginView;
    private UserService userService;

    /**
     * Constructs a LoginController.
     * Dependencies (LoginView, UserService) are set via constructor injection.
     * @param loginView The view responsible for user interaction.
     * @param userService The service responsible for user authentication.
     */
    public LoginController(LoginView loginView, UserService userService) {
        this.loginView = loginView;
        this.userService = userService;
        // The view needs a reference back to the controller for callbacks.
        this.loginView.setLoginController(this);
    }

    /**
     * Handles a login submission request from the view.
     * Renamed from handleLoginRequest to satisfy REQ-4.
     *
     * @param credentials The login credentials provided by the user.
     */
    public void submitLogin(LoginCredentials credentials) {
        System.out.println("[LoginController] Submitting login request for user: " + credentials.getUsername());
        try {
            boolean isAuthenticated = userService.authenticateUser(credentials);

            if (isAuthenticated) {
                loginView.displayLoginSuccess("Login successful!");
                loginView.displayWorkArea();
            } else {
                loginView.displayLoginError("Invalid username or password.");
                loginView.showLoginErrorPage();
            }
        } catch (ConnectionException e) {
            // Handle connection errors as per REQ-ETOUR
            handleConnectionError(e);
        }
    }

    /**
     * Handles a cancellation request from the view.
     */
    public void cancelLoginRequest() {
        System.out.println("[LoginController] Login cancellation request received.");
        loginView.showCancellationConfirmation();
    }

    /**
     * Handles connection errors that occur during the login process.
     * As per REQ-ETOUR.
     * @param exception The ConnectionException that occurred.
     */
    public void handleConnectionError(ConnectionException exception) {
        System.err.println("[LoginController] Connection error handled: " + exception.getMessage());
        loginView.displayLoginError("Connection failed. Please try again.");
        loginView.showLoginErrorPage();
    }
}