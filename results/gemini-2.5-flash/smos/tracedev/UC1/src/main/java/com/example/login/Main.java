package com.example.login;

/**
 * Main class to demonstrate the login use case.
 * Wires together all components and simulates different login scenarios.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Initialize Infrastructure/Dependencies
        ExternalSMOSServer smosServer = new ExternalSMOSServer();
        DatabaseUserRepository userRepository = new DatabaseUserRepository(smosServer);
        CredentialValidator credentialValidator = new CredentialValidator();
        UserSessionManager sessionManager = new UserSessionManager();

        // 2. Initialize Application Layer (LoginService)
        LoginService loginService = new LoginService(credentialValidator, userRepository, sessionManager);

        // 3. Initialize Presentation Layer (LoginController)
        LoginController loginController = new LoginController(loginService);

        System.out.println("--- Starting Login Use Case Demonstrations ---");

        // Scenario 1: Successful Login
        System.out.println("\n=== Scenario 1: Successful Login ===");
        LoginForm successForm = new LoginForm("testuser", "password123");
        String result1 = loginController.handleLoginRequest(successForm);
        System.out.println("Login Result: " + result1);
        System.out.println("Current session user: " + (sessionManager.getCurrentUser() != null ? sessionManager.getCurrentUser().getUsername() : "None"));

        // Clear session for next scenario
        sessionManager.logout();

        // Scenario 2: Invalid Username Format (too short)
        System.out.println("\n=== Scenario 2: Invalid Username Format (length < 5) ===");
        LoginForm invalidUsernameForm = new LoginForm("user", "password123");
        String result2 = loginController.handleLoginRequest(invalidUsernameForm);
        System.out.println("Login Result: " + result2);
        System.out.println("Current session user: " + (sessionManager.getCurrentUser() != null ? sessionManager.getCurrentUser().getUsername() : "None"));

        // Scenario 3: Invalid Password Format (too short)
        System.out.println("\n=== Scenario 3: Invalid Password Format (length < 5) ===");
        LoginForm invalidPasswordForm = new LoginForm("testuser", "pass");
        String result3 = loginController.handleLoginRequest(invalidPasswordForm);
        System.out.println("Login Result: " + result3);
        System.out.println("Current session user: " + (sessionManager.getCurrentUser() != null ? sessionManager.getCurrentUser().getUsername() : "None"));

        // Scenario 4: User Not Found / Password Mismatch
        System.out.println("\n=== Scenario 4: User Not Found / Password Mismatch ===");
        LoginForm wrongCredentialsForm = new LoginForm("unknownuser", "wrongpassword");
        String result4 = loginController.handleLoginRequest(wrongCredentialsForm);
        System.out.println("Login Result: " + result4);
        System.out.println("Current session user: " + (sessionManager.getCurrentUser() != null ? sessionManager.getCurrentUser().getUsername() : "None"));

        // Scenario 5: Connection Interruption (to SMOS server, as per sequence diagram)
        System.out.println("\n=== Scenario 5: Connection Interruption to SMOS Server ===");
        // The DatabaseUserRepository is configured to throw ConnectionException if username is "network_down"
        LoginForm connectionErrorForm = new LoginForm("network_down", "anypass"); // Password won't matter as connection fails
        String result5 = loginController.handleLoginRequest(connectionErrorForm);
        System.out.println("Login Result: " + result5);
        System.out.println("Current session user: " + (sessionManager.getCurrentUser() != null ? sessionManager.getCurrentUser().getUsername() : "None"));

        System.out.println("\n--- Login Use Case Demonstrations Complete ---");
    }
}