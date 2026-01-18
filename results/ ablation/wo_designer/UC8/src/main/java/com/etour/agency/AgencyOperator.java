// Represents the logged-in Agency Operator
package com.etour.agency;

public class AgencyOperator {
    private String username;
    private boolean loggedIn;

    public AgencyOperator(String username) {
        this.username = username;
        this.loggedIn = true; // Assume logged in as per entry condition
    }

    public String getUsername() {
        return username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}