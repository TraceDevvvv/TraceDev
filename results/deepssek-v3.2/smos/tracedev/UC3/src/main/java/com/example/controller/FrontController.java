package com.example.controller;

import com.example.model.HttpRequest;
import com.example.view.LoginForm;

/**
 * Front controller that routes HTTP requests to appropriate handlers.
 */
public class FrontController {
    private RequestHandler requestHandler; // Could be used for other requests

    /**
     * Routes an incoming HTTP request based on its path and method.
     * @param request the HTTP request to route.
     */
    public void routeRequest(HttpRequest request) {
        System.out.println("FrontController routing request: " + request.method + " " + request.path);
        if ("POST".equals(request.method) && "/logout".equals(request.path)) {
            handleLogoutClick();
        } else {
            System.out.println("FrontController: No handler for request.");
        }
    }

    /**
     * Internal handling of logout click as per sequence diagram.
     */
    public void handleLogoutClick() {
        System.out.println("FrontController handling logout click.");
        LogoutController logoutController = new LogoutController();
        logoutController.handleLogoutRequest();
        // After logout, display the login form again
        LoginForm form = new LoginForm();
        form.display();
        // Return login form displayed to user as per sequence diagram
        loginFormDisplayed();
    }

    /**
     * Simulates returning login form displayed to user as per sequence diagram.
     */
    public void loginFormDisplayed() {
        System.out.println("FrontController: login form displayed.");
    }
}