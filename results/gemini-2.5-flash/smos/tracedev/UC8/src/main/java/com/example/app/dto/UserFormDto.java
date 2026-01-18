package com.example.app.dto;

/**
 * Data Transfer Object (DTO) for user form input.
 * This class corresponds to the 'UserFormDto' in the UML Class Diagram.
 * It's used to transfer data from the UI layer to the service layer.
 */
public class UserFormDto {
    // Attributes for form input
    public String id;
    public String username;
    public String email;
    public String firstName;
    public String lastName;

    /**
     * Constructor for creating a UserFormDto.
     * @param id The ID of the user being edited (can be null for new users, but here for updates).
     * @param username The new username.
     * @param email The new email address.
     * @param firstName The new first name.
     * @param lastName The new last name.
     */
    public UserFormDto(String id, String username, String email, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "UserFormDto{" +
               "id='" + id + '\'' +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               '}';
    }
}