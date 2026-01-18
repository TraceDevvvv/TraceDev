package com.example.school.service;

/**
 * Service responsible for student authentication.
 * As per requirement EC-1, this service handles authentication checks.
 */
public class AuthenticationService {

    /**
     * Checks if a student is authenticated.
     * For this simulation, it always returns true for a specific student ID to allow flow.
     * In a real application, this would involve checking user sessions, tokens, etc.
     *
     * @param studentId The ID of the student to authenticate.
     * @return true if the student is authenticated, false otherwise.
     */
    public boolean isAuthenticated(String studentId) {
        System.out.println("AuthenticationService: Checking if student '" + studentId + "' is authenticated.");
        // Assume student S1001 is always authenticated for demonstration purposes
        // In a real system, this would involve a robust authentication mechanism.
        if ("S1001".equals(studentId) || "S1002".equals(studentId)) {
            System.out.println("AuthenticationService: Student '" + studentId + "' is authenticated.");
            return true;
        } else {
            System.out.println("AuthenticationService: Student '" + studentId + "' is NOT authenticated.");
            return false;
        }
    }
}