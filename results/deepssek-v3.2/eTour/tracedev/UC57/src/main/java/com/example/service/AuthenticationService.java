package com.example.service;

import com.example.model.Tourist;

/**
 * Interface for authentication serv.
 */
public interface AuthenticationService {
    boolean authenticate(Tourist user);
}