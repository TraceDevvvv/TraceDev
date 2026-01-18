package com.example.service;

import com.example.repository.UserRepository;

/**
 * Implementation of authentication service.
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isLoggedIn(String operatorId) {
        // In a real implementation, this would check session or token
        System.out.println("Checking login status for operator: " + operatorId);
        return true; // Assume logged in for simplicity
    }
}