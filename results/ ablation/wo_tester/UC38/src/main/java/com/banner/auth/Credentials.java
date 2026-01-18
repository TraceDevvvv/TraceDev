package com.banner.auth;

/**
 * Credentials DTO for authentication.
 */
public class Credentials {
    private String userId;
    private String token;

    public Credentials(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}