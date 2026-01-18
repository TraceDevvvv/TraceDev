package com.example.service;

import java.io.Serializable;

/**
 * Represents an Agency Operator.
 * This class is a simple placeholder for demonstration purposes.
 * It might contain user details, roles, etc. in a real application.
 */
public class AgencyOperator implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String role;

    public AgencyOperator(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    // You might add more fields like name, email, etc.
}