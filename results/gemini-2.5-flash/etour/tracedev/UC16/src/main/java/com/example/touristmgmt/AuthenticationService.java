package com.example.touristmgmt;

/**
 * Service for handling user authentication.
 * Added to satisfy requirement R3 (AuthenticationService).
 */
public class AuthenticationService {

    /**
     * Checks if an operator is currently logged in.
     *
     * @param operatorId The ID of the operator to check.
     * @return true if the operator is logged in, false otherwise.
     */
    public boolean isLoggedIn(String operatorId) {
        // For demonstration purposes, always return true.
        // In a real application, this would involve checking session or token validity.
        System.out.println("AuthenticationService: Checking if operator '" + operatorId + "' is logged in. (Simulated: true)");
        return true;
    }
}