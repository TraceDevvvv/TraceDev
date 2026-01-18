package com.etour.registration.domain;

/**
 * Command object containing data for creating a new account.
 */
public class CreateAccountCommand {
    private String username;
    private String email;
    private String password;

    public CreateAccountCommand(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}