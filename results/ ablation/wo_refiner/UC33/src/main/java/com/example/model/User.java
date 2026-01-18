package com.example.model;

import java.util.Date;
import java.util.UUID;

/**
 * Represents a user in the system.
 */
public class User {
    
    private String userId;
    private String username;
    private String email;
    private String passwordHash;
    private Date createdAt;
    
    /**
     * Constructor for User.
     * @param username the username
     * @param email the email
     * @param passwordHash the hashed password
     */
    public User(String username, String email, String passwordHash) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = new Date();
    }
    
    // Getters as per class diagram
    public String getUserId() {
        return userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPasswordHash() {
        return passwordHash;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    // Setters (not in class diagram but useful)
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}