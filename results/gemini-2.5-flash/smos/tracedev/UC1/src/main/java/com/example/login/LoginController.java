package com.example.login;

/**
 * Presentation Layer: Handles login requests from the LoginForm.
 * Acts as a bridge between the view (LoginForm) and the application logic (LoginService).
 */
public class LoginController {
    private LoginService loginService;

    /**
     * Constructs a new LoginController with a given LoginService.
     * @param service The LoginService to be used for authentication.
     */
    public LoginController(LoginService service) {
        this.loginService = service;
    }

    /**
     * Handles the login request initiated from the LoginForm.
     * Orchestrates the authentication process by interacting with LoginService.
     * // R6, R7: LoginForm passes itself, Controller extracts data.
     * @param form The LoginForm instance containing user input.
     * @return A message indicating the result of the login attempt (e.g., success, error page).
     */
    public String handleLoginRequest(LoginForm form) {
        String username = form.getUsername();
        String password = form.getPassword();

        try {
            // Delegate authentication to the LoginService
            User authenticatedUser = loginService.authenticate(username, password);
            System.out.println("LoginController: User " + authenticatedUser.getUsername() + " authenticated successfully.");

            // If successful, display the workspace view
            WorkspaceView workspaceView = new WorkspaceView();
            return workspaceView.display(authenticatedUser);

        } catch (InvalidCredentialsException e) {
            // R8, R9, R10: Display error for invalid credentials
            System.out.println("LoginController: Authentication failed due to invalid credentials.");
            form.displayError("Invalid username or password.");
            return "Login Failed: Invalid credentials";
        } catch (SystemErrorException e) {
            // R13: Handle system-level errors like connection issues
            System.out.println("LoginController: System error during authentication: " + e.getMessage());
            return displaySystemErrorPage("Unable to connect to authentication server.");
        }
    }

    /**
     * Displays a generic system error page to the user.
     * // Added to satisfy requirement R13
     * @param message The error message to display on the system error page.
     * @return A string representing the system error page content.
     */
    public String displaySystemErrorPage(String message) {
        System.err.println("--- SYSTEM ERROR PAGE ---");
        System.err.println("Message: " + message);
        System.err.println("Please try again later.");
        System.err.println("-------------------------");
        return "System Error Page: " + message;
    }
}