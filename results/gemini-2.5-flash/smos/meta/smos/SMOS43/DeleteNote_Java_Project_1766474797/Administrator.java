package com.system.school.admin;

/**
 * Represents an Administrator actor in the school management system.
 * This class holds basic information about an administrator.
 */
public class Administrator {
    private String adminId;
    private String username;
    private String email;

    /**
     * Constructs a new Administrator instance.
     *
     * @param adminId The unique identifier for the administrator.
     * @param username The username used by the administrator to log in.
     * @param email The email address of the administrator.
     */
    public Administrator(String adminId, String username, String email) {
        if (adminId == null || adminId.trim().isEmpty()) {
            throw new IllegalArgumentException("Admin ID cannot be null or empty.");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        this.adminId = adminId;
        this.username = username;
        this.email = email;
    }

    /**
     * Returns the unique identifier of the administrator.
     *
     * @return The administrator's ID.
     */
    public String getAdminId() {
        return adminId;
    }

    /**
     * Returns the username of the administrator.
     *
     * @return The administrator's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the email address of the administrator.
     *
     * @return The administrator's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the administrator.
     *
     * @param email The new email address.
     */
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        this.email = email;
    }

    /**
     * Provides a string representation of the Administrator object.
     *
     * @return A string containing the administrator's ID, username, and email.
     */
    @Override
    public String toString() {
        return "Administrator{" +
               "adminId='" + adminId + '\'' +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}