package com.example.entity;

import com.example.application.dto.InsertUserRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a User in the domain.
 * Contains user details and business validation logic.
 */
public class User {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String cell;
    private String login;
    private String hashedPassword;

    // Errors list for validation feedback
    private List<String> errors = new ArrayList<>();

    /**
     * Constructor that creates a User from an InsertUserRequest.
     * As per class diagram and sequence diagram.
     *
     * @param request the InsertUserRequest containing user data.
     */
    public User(InsertUserRequest request) {
        this.name = request.getName();
        this.surname = request.getSurname();
        this.email = request.getEmail();
        this.cell = request.getCell();
        this.login = request.getLogin();
        // In a real system, password should be hashed.
        this.hashedPassword = hashPassword(request.getPassword());
    }

    /**
     * Validates the user entity for integrity.
     * Implements the integrity check as per class diagram and sequence diagram.
     *
     * @return true if the user is valid, false otherwise.
     */
    public boolean validate() {
        errors.clear();
        if (name == null || name.trim().isEmpty()) {
            errors.add("Name is required.");
        }
        if (surname == null || surname.trim().isEmpty()) {
            errors.add("Surname is required.");
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            errors.add("Email is invalid.");
        }
        if (login == null || login.trim().isEmpty()) {
            errors.add("Login is required.");
        }
        if (hashedPassword == null || hashedPassword.trim().isEmpty()) {
            errors.add("Password is required.");
        }
        return errors.isEmpty();
    }

    /**
     * Returns the list of validation errors.
     *
     * @return a List of error messages.
     */
    public List<String> getErrors() {
        return errors;
    }

    // Simulate password hashing
    private String hashPassword(String plainPassword) {
        // In a real application, use a proper hashing algorithm (e.g., BCrypt).
        return "HASHED_" + plainPassword;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}