package com.example.disciplinarynote.application;

/**
 * Service for handling user authentication.
 * Added to satisfy requirement R3.
 */
public class AuthenticationService {

    /**
     * Checks if the current user is logged in.
     * For demonstration purposes, this will return true for successful authentication.
     * In a real application, this would involve checking session, token, etc.
     *
     * @return true if the user is considered logged in, false otherwise.
     */
    public boolean checkLoggedIn() {
        System.out.println("[Application] AuthenticationService: Checking if user is logged in.");
        // Simulate a successful login for the happy path
        // For testing, this could be configurable.
        return true; // Assume always logged in for now, can be changed for different scenarios.
    }

    /**
     * Method to simulate a failed login attempt for testing purposes.
     * @return false always.
     */
    public boolean checkLoggedInFail() {
        System.out.println("[Application] AuthenticationService: Simulating failed login.");
        return false;
    }
}