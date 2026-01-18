package com.example.interfaces;

import com.example.dtos.CredentialsDTO;
import com.example.dtos.AuthenticationResult;

/**
 * Interface for authentication strategies.
 * Follows the Strategy pattern to allow different authentication methods.
 */
public interface IAuthenticationStrategy {
    /**
     * Authenticates the given credentials.
     *
     * @param credentials the credentials to authenticate
     * @return the authentication result containing success status and user details
     */
    AuthenticationResult authenticate(CredentialsDTO credentials);
}