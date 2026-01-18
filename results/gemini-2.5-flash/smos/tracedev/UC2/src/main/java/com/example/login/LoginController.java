package com.example.login;

/**
 * Controller class that handles the login process.
 * It orchestrates interactions between the LoginView and AuthenticationService.
 */
public class LoginController {
    // --- Relationships from Class Diagram: LoginController uses LoginView and AuthenticationService ---
    private final LoginView loginView;
    private final AuthenticationService authenticationService;

    /**
     * Constructor for LoginController, injecting its dependencies.
     *
     * @param loginView The view component for displaying UI.
     * @param authenticationService The service for user authentication.
     */
    public LoginController(LoginView loginView, AuthenticationService authenticationService) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
    }

    /**
     * Initiates the display of the login form.
     * --- Implements: LoginController.showLoginForm() from Class Diagram ---
     */
    public void showLoginForm() {
        System.out.println("LoginController: Initiating display of login form.");
        loginView.displayLoginForm();
    }

    /**
     * Processes a user's login attempt by interacting with the authentication service.
     * If authentication fails, it delegates to handleAuthenticationError.
     * --- Implements: LoginController.processLoginAttempt(username : String, password : String) from Class Diagram ---
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     */
    public void processLoginAttempt(String username, String password) {
        System.out.println("\nLoginController: Processing login attempt for user: " + username);

        boolean isAuthenticated = authenticationService.authenticate(username, password);

        if (isAuthenticated) {
            System.out.println("LoginController: Authentication successful! Welcome, " + username + ".");
            // In a real app, navigate to main application screen.
        } else {
            // If authentication fails, call the error handler
            // This is how handleAuthenticationError would typically be invoked in a real scenario.
            handleAuthenticationError("Incorrect username or password. Please try again.");
        }
    }

    /**
     * Handles authentication errors by displaying the error message and then re-displaying the login form.
     * --- Implements: LoginController.handleAuthenticationError(errorMessage : String) from Class Diagram and Sequence Diagram ---
     * --- This method directly implements the behavior specified in the "Registered user informed of an authentication error" sequence diagram. ---
     *
     * @param errorMessage The error message to be shown to the user.
     */
    public void handleAuthenticationError(String errorMessage) {
        // --- Sequence Diagram: Entry Condition: Authentication data IS incorrect ---
        System.out.println("\nLoginController: handleAuthenticationError(" + errorMessage + ")");
        System.out.println("LoginController: Orchestrates error display and form re-display.");

        // --- Sequence Diagram: LoginController -> LoginView : displayAuthenticationError(...) ---
        loginView.displayAuthenticationError(errorMessage);
        // --- Sequence Diagram: LoginView --> LoginController : //error message displayed// ---

        // --- Sequence Diagram: LoginController -> LoginView : displayLoginForm() ---
        loginView.displayLoginForm();
        // --- Sequence Diagram: LoginView --> LoginController : //login form displayed// ---

        // --- Sequence Diagram: User is informed and can try to log in again (Exit Condition). ---
        System.out.println("LoginController: Error handled. User can now re-attempt login.");
    }
}