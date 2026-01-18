package com.example.domain;

/**
 * Represents a User entity in the domain.
 * Implements SearchableEntity to allow searching.
 */
public class User implements SearchableEntity {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String searchableContent;

    // Constructors
    public User() {
    }

    public User(Long id, String username, String email, String firstName, String lastName, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.searchableContent = generateSearchableContent();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        this.searchableContent = generateSearchableContent();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        this.searchableContent = generateSearchableContent();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        this.searchableContent = generateSearchableContent();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.searchableContent = generateSearchableContent();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.searchableContent = generateSearchableContent();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        this.searchableContent = generateSearchableContent();
    }

    // SearchableEntity implementation
    @Override
    public String getSearchableContent() {
        if (searchableContent == null) {
            searchableContent = generateSearchableContent();
        }
        return searchableContent;
    }

    /**
     * Generates the searchable content string from relevant fields.
     * @return concatenated searchable fields.
     */
    private String generateSearchableContent() {
        return (username != null ? username : "") + " " +
               (email != null ? email : "") + " " +
               (firstName != null ? firstName : "") + " " +
               (lastName != null ? lastName : "") + " " +
               (role != null ? role : "");
    }
}