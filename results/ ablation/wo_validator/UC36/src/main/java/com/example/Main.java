package com.example;

import com.example.handler.LoginErrorHandler;
import com.example.service.AuthenticationService;
import com.example.service.NotificationService;
import com.example.model.Session;
import com.example.model.LoginCredentials;

/**
 * Main class to demonstrate the incorrect login handling flow.
 */
public class Main {
    public static void main(String[] args) {
        // Create the serv and handler.
        Session session = new Session("session123");
        AuthenticationService authService = new AuthenticationService(session);
        NotificationService notificationService = new NotificationService();
        LoginErrorHandler handler = new LoginErrorHandler(authService, notificationService);

        // Create invalid credentials (empty username and password).
        LoginCredentials invalidCredentials = new LoginCredentials("", "");

        // Trigger the incorrect login handling.
        System.out.println("Starting incorrect login handling...");
        handler.handleIncorrectLogin(invalidCredentials);
        System.out.println("Incorrect login handling completed.");
    }
}