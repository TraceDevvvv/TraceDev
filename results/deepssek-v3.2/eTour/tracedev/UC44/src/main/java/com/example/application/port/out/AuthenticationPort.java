package com.example.application.port.out;

import com.example.domain.UserContext;

/**
 * Output port for authentication operations.
 */
public interface AuthenticationPort {
    boolean isAuthenticated(String userId);
    UserContext getCurrentUser();
}