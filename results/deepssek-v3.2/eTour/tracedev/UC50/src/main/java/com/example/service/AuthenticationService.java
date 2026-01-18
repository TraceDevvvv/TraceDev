package com.example.service;

/**
 * Authentication service interface.
 * Added <<security>> stereotype to satisfy requirement Quality Requirement.
 */
public interface AuthenticationService {
    boolean isAuthenticated(String userId);
}