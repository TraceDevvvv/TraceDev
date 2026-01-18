package com.example.insertdelayadmin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * Represents an Administrator entity in the system.
 * Administrators are users who can log in and register student delays.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrator {
    /**
     * Unique identifier for the administrator.
     */
    @Id
    private String id;

    /**
     * The username used by the administrator for logging in.
     * Must be unique.
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * The hashed password of the administrator.
     * Stored as a hash for security reasons.
     */
    @Column(nullable = false)
    private String passwordHash;

    /**
     * The role of the administrator (e.g., "ADMIN").
     * Used for authorization purposes.
     */
    private String role;

    /**
     * A list of delay records entered by this administrator.
     * This establishes a one-to-many relationship where one administrator can enter multiple delay records.
     * CascadeType.ALL ensures that if an administrator is deleted, their associated delay records are also deleted.
     * FetchType.LAZY means the delay records are loaded only when accessed, improving performance.
     */
    @OneToMany(mappedBy = "administrator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DelayRecord> enteredDelays;

    /**
     * Simulates the authentication process for an administrator.
     * In a real application, this would involve comparing the provided password
     * with the stored password hash using a secure hashing algorithm (e.g., BCrypt).
     * For this example, it's a simplified comparison.
     *
     * @param username The username provided by the user.
     * @param password The raw password provided by the user.
     * @return true if the username and password match, false otherwise.
     */
    public boolean authenticate(String username, String password) {
        // This is a placeholder. Actual authentication involves password hashing and comparison.
        // Spring Security will handle the actual password encoding and comparison.
        return this.username.equals(username) && this.passwordHash.equals(password); // Simplified for example
    }
}