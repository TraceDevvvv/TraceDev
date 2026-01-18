package com.example;

/**
 * Main class to demonstrate the login flow as per the sequence diagram.
 * Simulates the interactions between components.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Login Demo ===\n");

        // Create components as per class diagram
        LoginForm loginForm = new LoginForm();
        AuthenticationStrategy authStrategy = new BCryptAuthenticationStrategy();
        UserRepository userRepository = new DatabaseUserRepository();
        LoginController controller = new LoginController(userRepository, authStrategy);
        UserWorkArea workArea = new UserWorkArea();

        // Step 1: User activates login feature
        System.out.println("1. User activates login feature.");
        loginForm.display();

        // Step 2: User fills out and submits form (simulated)
        System.out.println("\n2. User fills out username & password and submits form.");
        String username = "john_doe";
        String password = "password123";
        loginForm.submitForm(username, password);

        // Step 3: Form calls controller's handleLoginRequest
        System.out.println("\n3. Form passes credentials to LoginController.");
        AuthenticationResult result = controller.handleLoginRequest(
                loginForm.getUsername(),
                loginForm.getPassword()
        );

        // Step 4: Process result
        if (result.isSuccess()) {
            System.out.println("\n4. Authentication successful.");
            // Form receives successful result and displays work area
            workArea.display(result.getUser());
        } else {
            System.out.println("\n4. Authentication failed.");
            // Form receives error result and shows error message
            loginForm.showErrorMessage(result.getErrorMessage());
            loginForm.clearFields();
        }

        System.out.println("\n=== Login Demo Complete ===");
    }
}