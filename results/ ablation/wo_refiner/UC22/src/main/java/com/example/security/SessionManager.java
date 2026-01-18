package com.example.security;

import com.example.model.AgencyOperator;

/**
 * Manages user session and authentication.
 * Added to satisfy requirement REQ-004.
 */
public class SessionManager {
    public boolean isLoggedIn(AgencyOperator operator) {
        // In a real application, this would check session or token validity
        // For this example, we assume the operator is always logged in
        return operator != null;
    }
}