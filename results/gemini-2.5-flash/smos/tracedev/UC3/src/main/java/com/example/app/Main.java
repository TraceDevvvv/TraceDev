package com.example.app;

/**
 * Main class to set up the application context and demonstrate the logout sequence.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Application starting...");

        // 1. Initialize core serv
        SessionManager sessionManager = new SessionManager();
        AuthenticationService authenticationService = new AuthenticationService(sessionManager);

        // 2. Initialize controller (passing necessary serv)
        // Note: LogoutController needs to interact with LogoutView to display the login form.
        // To avoid circular dependency in constructor for this simple example,
        // we'll pass the LogoutView instance directly to the handleLogoutRequest method.
        LogoutController logoutController = new LogoutController(authenticationService);

        // 3. Initialize view (passing its controller)
        LogoutView logoutView = new LogoutView(logoutController);

        // --- Simulate the Use Case: Registered User logs out ---
        String loggedInUserId = "user123";
        System.out.println("\n--- Simulating Logout Use Case ---");

        // User -> View: clicks 'Logout' button
        logoutView.displayLogoutButton(loggedInUserId);

        System.out.println("\n--- Logout Use Case Simulation Complete ---");
    }
}