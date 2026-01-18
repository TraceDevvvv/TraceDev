package com.example.interfaces;

import com.example.dtos.CredentialsDTO;
import com.example.dtos.AuthToken;

/**
 * Port for authentication service in Hexagonal Architecture.
 */
public interface IAuthenticatorService {
    /**
     * Authenticates the given credentials.
     *
     * @param credentials the credentials to authenticate
     * @return an authentication token upon success
     */
    AuthToken authenticate(CredentialsDTO credentials);

    /**
     * Logs out the user associated with the given token.
     *
     * @param token the authentication token to invalidate
     */
    void logout(String token);
}