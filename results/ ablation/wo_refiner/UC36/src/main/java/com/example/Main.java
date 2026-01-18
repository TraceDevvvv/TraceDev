package com.example;

import com.example.controller.AuthenticationController;
import com.example.facade.NotificationFacade;
import com.example.model.LoginRequest;
import com.example.service.LoginService;
import com.example.service.StateManager;
import com.example.service.ValidationModule;

/**
 * Main class to demonstrate the login error flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        ValidationModule validationModule = new ValidationModule();
        StateManager stateManager = new StateManager();
        LoginService loginService = new LoginService(validationModule, stateManager);
        NotificationFacade notificationFacade = new NotificationFacade();
        AuthenticationController controller = new AuthenticationController(loginService, notificationFacade, stateManager);

        // Simulate user submitting incorrect login data (entry condition REQ‑004)
        LoginRequest invalidRequest = new LoginRequest("wrong", "wrong");
        System.out.println("=== Login Error Flow ===");
        System.out.println("Entry condition: incorrect data supplied");
        controller.submitLoginCredentials(invalidRequest);

        // Simulate valid login (alternative path)
        System.out.println("\n=== Successful Login Flow ===");
        LoginRequest validRequest = new LoginRequest("admin", "password123");
        controller.submitLoginCredentials(validRequest);
    }
}