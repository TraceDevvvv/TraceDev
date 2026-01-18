package com.example.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a user account in the system.
 * This class includes a static factory method for creation and a method to activate the user.
 */
public class User {
    private String userId;
    private String username; // Often an email or unique identifier
    private String passwordHash; // Placeholder for a real password hash
    private boolean isActive;
    private List<String> roles; // e.g., ["STUDENT", "ADMIN"]

    /**
     * Private constructor to enforce creation via the static factory method.
     * @param userId The unique ID for the user.
     * @param username The username for the account (e.g., email).
     * @param studentId The ID of the student associated with this user (if applicable).
     *                  This is used to initialize `passwordHash` and `roles`.
     */
    private User(String userId, String username, String studentId) {
        this.userId = userId;
        this.username = username;
        // In a real system, password would be hashed and stored securely.
        // For this example, using a simple placeholder based on studentId.
        this.passwordHash = "hashed_password_for_" + studentId;
        this.isActive = false; // Users are inactive by default until explicitly activated
        this.roles = new ArrayList<>();
        this.roles.add("STUDENT"); // All users created this way are students
    }

    /**
     * Static factory method to create a new User instance.
     * The sequence diagram specifies `create(generateUserId(), newStudent.getEmail(), newStudent.getStudentId())`.
     * The class diagram specifies `create(userId : String, username : String, studentId : String)`.
     * @param userId The unique ID for the user.
     * @param username The username (e.g., student's email).
     * @param studentId The ID of the associated student.
     * @return A new User object.
     */
    public static User create(String userId, String username, String studentId) {
        System.out.println("Creating new User: ID=" + userId + ", Username=" + username + ", associated with Student ID=" + studentId);
        return new User(userId, username, studentId);
    }

    /**
     * Activates the user account.
     */
    public void activate() {
        this.isActive = true;
        System.out.println("User " + userId + " activated.");
    }

    // --- Getters ---

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<String> getRoles() {
        return roles;
    }

    // For debugging or logging purposes
    @Override
    public String toString() {
        return "User{" +
               "userId='" + userId + '\'' +
               ", username='" + username + '\'' +
               ", isActive=" + isActive +
               ", roles=" + roles +
               '}';
    }
}