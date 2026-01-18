package com.example;

/**
 * Mock Authentication Service to provide the logged-in professor's ID.
 */
public class AuthenticationService {

    private String loggedInProfessorId;
    private boolean loggedIn;

    public AuthenticationService() {
        // Simulate a logged-in user by default for the scenario
        this.loggedInProfessorId = "prof123";
        this.loggedIn = true;
        System.out.println("AuthenticationService initialized. User 'prof123' logged in by default.");
    }

    /**
     * Retrieves the ID of the currently logged-in professor.
     *
     * @return The professor's ID, or null if no one is logged in.
     */
    public String getLoggedInProfessorId() {
        return loggedIn ? loggedInProfessorId : null;
    }

    /**
     * Checks if a user is currently logged in.
     *
     * @return True if a user is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Sets the logged-in state and ID for testing.
     * @param loggedInProfessorId The ID of the professor to log in.
     * @param loggedIn The login status.
     */
    public void setLoginStatus(String loggedInProfessorId, boolean loggedIn) {
        this.loggedInProfessorId = loggedInProfessorId;
        this.loggedIn = loggedIn;
        if (loggedIn) {
            System.out.println("AuthenticationService: User " + loggedInProfessorId + " is now logged in.");
        } else {
            System.out.println("AuthenticationService: User is now logged out.");
        }
    }
}