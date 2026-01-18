package com.example.insertdelayadmin.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Data Transfer Object (DTO) for encapsulating the response after a successful login.
 * Contains the authentication token, username, and role of the logged-in administrator.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    /**
     * The JSON Web Token (JWT) issued upon successful authentication.
     * This token will be used for subsequent authenticated requests.
     */
    private String token;

    /**
     * The username of the successfully authenticated administrator.
     */
    private String username;

    /**
     * The role of the successfully authenticated administrator (e.g., "ADMIN").
     * Used by the client for UI rendering or basic authorization checks.
     */
    private String role;
}