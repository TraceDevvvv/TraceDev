package com.example.domain;

/**
 * Domain entity representing an Agency Operator.
 */
public class AgencyOperator {
    private String username;

    public AgencyOperator(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean isLoggedIn() {
        // Simplified logic: assume logged in if username is not null or empty.
        return username != null && !username.isEmpty();
    }

    public void accessSearch() {
        // This method corresponds to the sequence diagram message "accesses search functionality".
        // In a real implementation, it would trigger the UI to show the search form.
        System.out.println("Agency Operator " + username + " accesses search functionality.");
    }
}