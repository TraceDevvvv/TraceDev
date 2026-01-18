package com.example.service;

/**
 * Service for checking registration completion.
 * This satisfies requirement R4.
 */
public class RegistrationService {
    /**
     * Checks if the SplitTaTtAlTeloregistration use case has been completed for a student.
     * @param studentId the student identifier
     * @return true if registration is completed, false otherwise
     */
    public boolean isRegistrationCompleted(String studentId) {
        // In a real implementation, this would query a registration system
        // For demonstration, assume studentId "registered" is completed
        return "registered".equals(studentId);
    }
}