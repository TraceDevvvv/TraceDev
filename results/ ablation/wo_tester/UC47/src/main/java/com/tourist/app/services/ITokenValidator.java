package com.tourist.app.serv;

/**
 * Interface for token validation.
 * This is an assumption because the UML mentions ITokenValidator but does not define it.
 */
public interface ITokenValidator {
    /**
     * Validates the token for the given user id.
     * @param userId the user id
     * @return true if token is valid, false otherwise
     */
    boolean isValid(String userId);
}