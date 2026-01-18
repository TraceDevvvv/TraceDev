package com.example.app.domain;

import java.util.UUID;

/**
 * Represents a User in the domain model.
 */
public class User {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String cell;
    private String login;
    private String hashedPassword;

    public User(UUID id, String name, String surname, String email, String cell, String login, String hashedPassword) {
        if (id == null) throw new IllegalArgumentException("User ID cannot be null");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        if (surname == null || surname.trim().isEmpty()) throw new IllegalArgumentException("Surname cannot be empty");
        if (email == null || email.trim().isEmpty()) throw new IllegalArgumentException("Email cannot be empty");
        if (login == null || login.trim().isEmpty()) throw new IllegalArgumentException("Login cannot be empty");
        if (hashedPassword == null || hashedPassword.trim().isEmpty()) throw new IllegalArgumentException("Hashed password cannot be empty");

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cell = cell; // Cell can be optional, no check needed here.
        this.login = login;
        this.hashedPassword = hashedPassword;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    /**
     * Verifies if a given raw password matches the user's hashed password.
     * This method uses a PasswordHasher service to perform the actual comparison,
     * ensuring that the User entity doesn't contain hashing logic itself.
     *
     * @param rawPassword The plain text password to check.
     * @param passwordHasher The PasswordHasher service to use for comparison.
     * @return true if the passwords match, false otherwise.
     */
    public boolean verifyPassword(String rawPassword, PasswordHasher passwordHasher) {
        if (passwordHasher == null) {
            throw new IllegalArgumentException("PasswordHasher cannot be null for verification.");
        }
        return passwordHasher.checkPassword(rawPassword, this.hashedPassword);
    }

    // Standard getters for all fields, if needed for other parts of the system.
    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getCell() {
        return cell;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}