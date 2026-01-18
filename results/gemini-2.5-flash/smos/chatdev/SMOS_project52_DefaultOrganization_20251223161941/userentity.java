/**
 * Represents a 'user' entity in the system.
 */
package com.chatdev.entities;
public class UserEntity {
    private String userId;
    private String username;
    private String role;
    private String email;
    // Constructor
    public UserEntity(String userId, String username, String role, String email) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.email = email;
    }
    // Getters
    public String getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    public String getRole() {
        return role;
    }
    public String getEmail() {
        return email;
    }
    // Override toString() for display in UI components
    @Override
    public String toString() {
        return "User ID: " + userId + ", Username: " + username + ", Role: " + role + ", Email: " + email;
    }
}