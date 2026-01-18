package com.example.agency;

/**
 * Represents an Agency Operator who has already logged in.
 * According to the requirement Entry Conditions: "Agency Operator HAS already logged in"
 */
public class AgencyOperator {
    private String username;
    private String password;

    public AgencyOperator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}