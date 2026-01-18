package com.example.app.model;

/**
 * Represents a User entity in the system.
 * This class corresponds to the 'User' class in the UML Class Diagram.
 */
public class User {
    // Attributes as specified in the UML Class Diagram
    public String id;
    public String username;
    public String email;
    public String firstName;
    public String lastName;
    private String passwordHash; // Sensitive data, private

    /**
     * Constructor for creating a new User.
     * @param id Unique identifier for the user.
     * @param username User's chosen username.
     * @param email User's email address.
     * @param firstName User's first name.
     * @param lastName User's last name.
     * @param passwordHash Hashed password for security.
     */
    public User(String id, String username, String email, String firstName, String lastName, String passwordHash) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
    }

    // --- Getters and Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "User{" +
               "id='" + id + '\'' +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               // Do not include passwordHash in toString for security
               '}';
    }
}