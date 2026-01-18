package com.example;

import com.example.ui.LoginUI;
import com.example.controller.LoginController;
import com.example.usecase.LoginInteractor;
import com.example.repository.InMemoryUserRepository;
import com.example.service.PasswordValidator;
import com.example.connection.ServerConnection;
import com.example.manager.SessionManager;
import com.example.dto.LoginRequest;
import com.example.dto.LoginResponse;

/**
 * Main class to demonstrate the login flow.
 * Simulates the interactions from the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        InMemoryUserRepository repository = new InMemoryUserRepository();
        PasswordValidator validator = new PasswordValidator();
        ServerConnection connection = new ServerConnection("http://smos.example.com");
        SessionManager sessionManager = new SessionManager();
        LoginInteractor interactor = new LoginInteractor(repository, validator, connection, sessionManager);
        LoginController controller = new LoginController(interactor, connection, sessionManager);
        LoginUI ui = new LoginUI(sessionManager);

        System.out.println("=== Login System Demo ===\n");

        // Simulate user accessing login page
        ui.displayLoginForm();

        // Simulate user login attempts
        testLogin(controller, ui, "alice", "password123"); // Should succeed
        testLogin(controller, ui, "bob", "wrongpass");     // Should fail (wrong password)
        testLogin(controller, ui, "short", "pw");          // Should fail (short credentials)
        testLogin(controller, ui, "unknown", "password");  // Should fail (user not found)

        // Simulate server connection failure (toggle connection state)
        connection.checkConnection(); // Toggles to false
        testLogin(controller, ui, "charlie", "test123");   // Should fail (connection error)
    }

    private static void testLogin(LoginController controller, LoginUI ui, String username, String password) {
        System.out.println("\n--- Attempting login for: " + username + " ---");
        LoginRequest request = new LoginRequest(username, password);
        LoginResponse response = controller.handleLoginRequest(request);

        if (response.isSuccess()) {
            System.out.println("Login successful for user: " + response.getUser().getUsername());
            ui.displayWorkspace(response.getUser());
        } else {
            ui.displayError(response.getMessage());
        }
    }
}