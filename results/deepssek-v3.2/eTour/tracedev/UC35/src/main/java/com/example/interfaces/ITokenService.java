package com.example.interfaces;

import com.example.dtos.AuthToken;
import com.example.domain.User;

/**
 * Interface for token service.
 */
public interface ITokenService {
    AuthToken generateToken(User user);
    boolean validateToken(String token);
    String extractUserId(String token);
}