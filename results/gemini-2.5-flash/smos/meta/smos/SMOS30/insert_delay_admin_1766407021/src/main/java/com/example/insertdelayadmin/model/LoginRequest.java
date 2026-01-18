package com.example.insertdelayadmin.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Data Transfer Object (DTO) for encapsulating login credentials.
 * Used when an administrator attempts to log in to the system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    /**
     * The username provided by the administrator for authentication.
     */
    private String username;

    /**
     * The password provided by the administrator for authentication.
     */
    private String password;
}