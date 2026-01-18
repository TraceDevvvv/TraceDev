package com.example.application;

import com.example.dtos.CredentialsDTO;

/**
 * Command object for login data (CQRS pattern).
 */
public class LoginCommand {
    private String username;
    private String password;

    public LoginCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Maps this command to a CredentialsDTO.
     *
     * @return a CredentialsDTO with the same data
     */
    public CredentialsDTO toCredentialsDTO() {
        return new CredentialsDTO(username, password);
    }
}