package com.example.dtos;

/**
 * Data transfer object for user credentials.
 */
public class CredentialsDTO {
    private String username;
    private String password;

    public CredentialsDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}