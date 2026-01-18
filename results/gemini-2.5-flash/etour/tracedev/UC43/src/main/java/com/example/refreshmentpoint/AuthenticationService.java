package com.example.refreshmentpoint;

/**
 * Simulates an authentication service.
 * Used for precondition check as per requirement EC1.
 */
public class AuthenticationService {
    /**
     * Checks if the given operator ID is authenticated.
     * For simulation purposes, only a specific ID is considered authenticated.
     *
     * @param operatorId The ID of the operator.
     * @return true if the operator is authenticated, false otherwise.
     */
    public boolean isAuthenticated(String operatorId) {
        // EC1: Precondition: Operator IS AUTHENTICATED
        // For this example, let's assume "admin" is authenticated.
        boolean authenticated = "admin".equals(operatorId);
        if (!authenticated) {
            System.out.println("AuthenticationService: Operator '" + operatorId + "' is NOT authenticated.");
        }
        return authenticated;
    }
}