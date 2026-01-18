package com.loginapp;

import com.loginapp.service.AuthService;
import com.loginapp.model.User;
import com.loginapp.view.LoginView;
import com.loginapp.exception.LoginError;

import java.util.Optional;
import java.util.Scanner;

/**
 * Main class to run the login application.
 * This class orchestrates the login process by interacting with the view and service layers.
 */
public class LoginApp {

    private final LoginView loginView;
    private final AuthService authService;
    private final Scanner scanner;

    /**
     * Constructor for LoginApp.
     * Initializes the view, authentication service, and scanner.
     */
    public LoginApp() {
        this.scanner = new Scanner(System.in);
        this.loginView = new LoginView(scanner);
        this.authService = new AuthService();
    }

    /**
     * Activates the login feature and handles the login flow.
     * This method continuously prompts the user for login credentials until successful
     * or the user chooses to cancel.
     */
    public void activateLoginFeature() {
        boolean loggedIn = false;
        while (!loggedIn) {
            // 1. Activate the login feature (implicitly done by calling this method)
            // 2. Displays the corresponding form.
            loginView.displayLoginForm();

            // 3. Fill out the form with login information and submit.
            Optional<User> credentials = loginView.getLoginCredentials();

            if (credentials.isEmpty()) {
                // User chose to cancel the operation.
                loginView.displayMessage("Login operation cancelled.");
                break; // Exit the login loop
            }

            User userAttempt = credentials.get();
            try {
                // 4. Check the correctness of the data, if not corrected on the use case LoginError.
                authService.authenticate(userAttempt.getUsername(), userAttempt.getPassword());
                loggedIn = true;
                // Exit conditions: The system displays the area of work registered.
                loginView.displayMessage("Login successful! Welcome, " + userAttempt.getUsername() + ".");
                loginView.displayMessage("Accessing registered work area...");
                // In a real application, this would navigate to the main application screen.
            } catch (LoginError e) {
                // Handle login errors (e.g., invalid credentials)
                loginView.displayErrorMessage("Login failed: " + e.getMessage());
                // The loop continues, prompting the user to try again.
            } catch (Exception e) {
                // Handle unexpected errors, potentially including "Interruption of the connection to the server ETOUR."
                // For this console app, we'll simulate a generic server error.
                loginView.displayErrorMessage("An unexpected error occurred: " + e.getMessage());
                loginView.displayErrorMessage("Please try again later or contact support.");
                // In a real system, this might involve logging the error and potentially exiting or offering retry.
                // For simplicity, we'll let the user retry.
            }
        }
        scanner.close(); // Close the scanner when the application finishes.
    }

    /**
     * Main method to start the Login Application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        LoginApp app = new LoginApp();
        app.activateLoginFeature();
    }
}