package com.example.app.dtos;

/**
 * DTO for user creation requests.
 * Carries data from the presentation layer to the application layer.
 */
public class UserCreationRequestDTO {
    public String name;
    public String surname;
    public String email;
    public String cell;
    public String login;
    public String password;
    public String confirmPassword;

    public UserCreationRequestDTO(String name, String surname, String email, String cell, String login, String password, String confirmPassword) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cell = cell;
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    // Getters are typically added for DTOs for immutability or specific access patterns.
    // For simplicity in this example, public fields are used directly as per DTO convention.
}