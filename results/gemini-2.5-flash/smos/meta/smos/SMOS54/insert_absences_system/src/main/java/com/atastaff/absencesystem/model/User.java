package com.atastaff.absencesystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA Entity for User, representing a user (e.g., ATA staff) in the system.
 * This class maps to a 'users' table in the database.
 * It is used for authentication and authorization purposes.
 */
@Entity
@Table(name = "users")
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class User {

    /**
     * Unique identifier for the user, typically the username.
     * Marked as the primary key.
     */
    @Id
    private String username;

    /**
     * The hashed password of the user.
     * Stored securely, typically using BCrypt.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The role of the user (e.g., "ATA_STAFF").
     * Used for authorization to determine what actions the user can perform.
     */
    @Column(nullable = false)
    private String role;
}