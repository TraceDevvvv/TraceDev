package com.example.app;

/**
 * Represents the user interface for logout operations, displaying buttons
 * and login forms.
 */
public class LogoutView {
    // This view "uses" LogoutController, so it needs a reference to it.
    private LogoutController logoutController;

    /**
     * Constructs a LogoutView with a dependency on LogoutController.
     *
     * @param logoutController The LogoutController instance to handle user actions.
     */
    public LogoutView(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    /**
     * Displays a logout button.
     * This method also simulates a user clicking the button, triggering the logout process.
     *
     * @param userId The ID of the user who is currently logged in.
     */
    public void displayLogoutButton(String userId) {
        System.out.println("\n--- Logout View ---");
        System.out.println("Displaying 'Logout' button.");
        // Note right of View: User is logged in (Entry Condition)
        System.out.println("User '" + userId + "' is currently logged in."); // m2: User is logged in (Entry Condition)
        System.out.println("User: clicks 'Logout' button"); // m1: clicks 'Logout' button

        // Simulate user interaction leading to a controller call
        // View -> Controller: handleLogoutRequest(userId)
        System.out.println("LogoutView: Calling controller to handle logout for user: " + userId);
        logoutController.handleLogoutRequest(userId, this); // Pass 'this' view instance to controller
    }

    /**
     * Displays the login form, typically after a successful logout.
     */
    public void displayLoginForm() {
        System.out.println("\n--- Logout View ---");
        System.out.println("Displaying login form."); // m12: displays login form
        // Note right of User: User can re-log in (Exit Condition)
        System.out.println("User can now re-log in."); // m13: User can re-log in (Exit Condition)
    }
}