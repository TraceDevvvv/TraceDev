package com.example.service;

import com.example.domain.User;

/**
 * Service for authentication operations.
 * Added to satisfy requirement REQ-4.
 */
public interface AuthenticationService {
    boolean isUserAuthenticated(String userId);
    User getCurrentUser();
}