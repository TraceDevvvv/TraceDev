package com.etour.presentation;

/**
 * Service for authenticating tourists.
 */
public interface AuthenticationService {
    boolean authenticate(String touristId);
}