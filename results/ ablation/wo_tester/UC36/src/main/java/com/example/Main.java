package com.example;

import com.example.presenter.WebLoginPresenter;
import com.example.service.LoginService;

/**
 * Main class to simulate the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting invalid login scenario...");
        // Create serv
        LoginService loginService = new LoginService();
        // Create presenter with login service
        WebLoginPresenter presenter = new WebLoginPresenter(loginService);
        // Simulate user submitting login with incorrect data (as per entry conditions)
        presenter.submitLogin("testUser", "wrongPassword");
        System.out.println("Invalid login scenario completed.");
    }
}