package com.example.service;

import com.example.dto.AuthenticationCredentials;
import com.example.dto.AuthenticationResult;

/**
 * Interface for authentication operations.
 * Added to satisfy requirement Entry Conditions.
 */
public interface IAuthenticationService {
    AuthenticationResult authenticate(AuthenticationCredentials credentials);
}