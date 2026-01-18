package com.example.etour;

/**
 * Main class to simulate the login flow.
 */
public class Main {
    public static void main(String[] args) {
        // Simulate the sequence diagram flow.
        System.out.println("=== Starting Login Sequence ===");

        // User activates login.
        LoginForm form = new LoginForm();
        form.activateLogin();

        // System checks server status and displays form.
        form.displayLoginForm();

        // User fills and submits form.
        form.fillForm("john_doe", "password123");
        form.submit();

        // Simulate cancellation (optional flow).
        form.cancelLogin();

        // Simulate connection error (exception flow).
        System.out.println("\n=== Simulating Connection Error ===");
        LoginController controller = new LoginController();
        LoginDTO loginData = new LoginDTO();
        loginData.username = "jane_doe";
        loginData.password = "wrongpass";
        // Simulate connection error by causing an exception in repository.
        // For simplicity, we'll just call the controller with a username that triggers null.
        AuthResultDTO result = controller.handleLoginRequest(loginData);
        System.out.println("Result: " + result.success + " - " + result.message);

        System.out.println("\n=== Login Sequence Completed ===");
    }
}