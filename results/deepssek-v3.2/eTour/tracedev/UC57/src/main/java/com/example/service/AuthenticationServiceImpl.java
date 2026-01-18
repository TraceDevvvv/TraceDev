package com.example.service;

import com.example.model.Tourist;

/**
 * Implementation of AuthenticationService.
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public boolean authenticate(Tourist user) {
        // Simple mock authentication: any tourist with non‑null ID is authenticated
        return user != null && user.getId() != null;
    }
}