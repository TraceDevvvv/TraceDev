package com.example.domain;

/**
 * Represents an agency with an ID, name, and password.
 */
public class Agency {
    private String id;
    private String name;
    private Password password; // composition relationship: Agency contains Password

    public Agency(String id, String name, Password password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Delegates to the Password object for hash management
    public String getPasswordHash() {
        return password.getHash();
    }

    public void setPasswordHash(String newHash) {
        password.setHash(newHash);
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}