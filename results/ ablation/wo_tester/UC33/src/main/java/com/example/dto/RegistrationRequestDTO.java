package com.example.dto;

/**
 * Data Transfer Object for registration requests.
 */
public class RegistrationRequestDTO {
    private String username;
    private String email;
    private String password;
    private String passwordConfirmation;

    // Constructors
    public RegistrationRequestDTO() {}

    public RegistrationRequestDTO(String username, String email, String password, String passwordConfirmation) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}