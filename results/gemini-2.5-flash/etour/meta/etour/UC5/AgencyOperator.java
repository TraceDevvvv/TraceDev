// AgencyOperator.java
package com.example.culturalheritage;

/**
 * Represents an agency operator who can log in and perform actions within the system.
 * This class manages the login status of the operator.
 */
public class AgencyOperator {
    private String username;
    private boolean loggedIn;

    /**
     * Constructs a new AgencyOperator with a given username.
     * Initially, the operator is not logged in.
     *
     * @param username The username of the agency operator.
     */
    public AgencyOperator(String username) {
        this.username = username;
        this.loggedIn = false; // Operator is not logged in by default
    }

    /**
     * Returns the username of the agency operator.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Checks if the agency operator is currently logged in.
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Logs in the agency operator.
     * Sets the loggedIn status to true.
     */
    public void login() {
        this.loggedIn = true;
        System.out.println(username + " has logged in.");
    }

    /**
     * Logs out the agency operator.
     * Sets the loggedIn status to false.
     */
    public void logout() {
        this.loggedIn = false;
        System.out.println(username + " has logged out.");
    }

    /**
     * Provides a string representation of the AgencyOperator object.
     * @return A formatted string containing the operator's username and login status.
     */
    @Override
    public String toString() {
        return "AgencyOperator{" +
               "username='" + username + '\'' +
               ", loggedIn=" + loggedIn +
               '}';
    }
}