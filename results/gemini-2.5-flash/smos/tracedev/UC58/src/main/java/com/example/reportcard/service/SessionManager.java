package com.example.reportcard.service;

/**
 * Manages the current user session, specifically the logged-in professor's ID.
 */
public class SessionManager {
    private String currentProfessorId;

    public SessionManager() {
        // Simulate a logged-in professor for the example
        this.currentProfessorId = "P001"; // Default professor ID for demonstration
        System.out.println("[SessionManager] Initialized with currentProfessorId: " + currentProfessorId);
    }

    /**
     * Retrieves the ID of the currently logged-in professor.
     * @return The professor's ID.
     */
    public String getProfessorId() {
        // In a real application, this would retrieve from an authenticated session.
        // For this example, it's a fixed value.
        return currentProfessorId;
    }

    /**
     * For testing purposes: set the current professor ID.
     * @param professorId The ID of the professor to set.
     */
    public void setProfessorId(String professorId) {
        this.currentProfessorId = professorId;
        System.out.println("[SessionManager] Current professor ID set to: " + currentProfessorId);
    }
}