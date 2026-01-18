package com.example.etour;

/**
 * Service interface for user-related operations.
 */
public interface UserService {
    UserDTO findByUsername(String username);
    Boolean validateCredentials(String username, String password);
}