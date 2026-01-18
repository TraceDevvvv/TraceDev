package com.example.tourist;

/**
 * Represents the agency operator actor.
 * Manages login state and interacts with TouristCardView.
 * Traceability: R3 - Participating Actor, R4 - Authentication required.
 */
public class AgencyOperator {
    private boolean loggedIn = false;

    /**
     * Simulates operator login.
     */
    public void login() {
        this.loggedIn = true;
        System.out.println("Agency operator logged in.");
    }

    /**
     * Checks if operator is logged in.
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Logs out the operator.
     */
    public void logout() {
        this.loggedIn = false;
        System.out.println("Agency operator logged out.");
    }

    /**
     * Returns login required message as per sequence diagram m26
     * @return "Login required" message
     */
    public String getLoginRequiredMessage() {
        return "Login required";
    }
}