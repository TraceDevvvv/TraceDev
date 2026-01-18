package com.example.account;

/**
 * Data Transfer Object (DTO) for account registration requests.
 * Carries data from the UI to the use case for creating a new account.
 */
public class RegistrationRequestDTO {
    private String username;
    private String email;
    private String password;

    /**
     * Constructs a new RegistrationRequestDTO.
     *
     * @param username The desired username.
     * @param email The user's email address.
     * @param password The chosen password.
     */
    public RegistrationRequestDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters for the properties
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters (if needed, though DTOs are often immutable for requests)
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}