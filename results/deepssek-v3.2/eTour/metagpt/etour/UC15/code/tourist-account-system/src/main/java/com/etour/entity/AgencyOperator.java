package com.etour.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing an Agency Operator who can manage tourist accounts.
 * Maps to the 'agency_operators' table in the database.
 * Includes authentication and authorization functionality.
 */
@Entity
@Table(name = "agency_operators")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgencyOperator {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;
    
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;
    
    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;
    
    @Column(name = "first_name", length = 100)
    private String firstName;
    
    @Column(name = "last_name", length = 100)
    private String lastName;
    
    @Column(name = "role", nullable = false, length = 50)
    private String role;
    
    @Column(name = "is_active")
    private Boolean active;
    
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @ElementCollection
    @CollectionTable(name = "operator_permissions", joinColumns = @JoinColumn(name = "operator_id"))
    @Column(name = "permission")
    @Builder.Default
    private Set<String> permissions = new HashSet<>();
    
    /**
     * Authenticates the operator by checking the provided password.
     * Uses BCrypt for secure password hashing and comparison.
     * 
     * @param password The plain text password to check
     * @return true if authentication succeeds, false otherwise
     */
    public boolean authenticate(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, this.passwordHash);
    }
    
    /**
     * Sets the password with secure hashing.
     * 
     * @param password The plain text password to hash and set
     */
    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.passwordHash = passwordEncoder.encode(password);
    }
    
    /**
     * Checks if the operator has a specific permission.
     * 
     * @param permission The permission to check (e.g., "TOURIST_MODIFY", "TOURIST_VIEW")
     * @return true if the operator has the permission, false otherwise
     */
    public boolean hasPermission(String permission) {
        if (permission == null || permission.trim().isEmpty()) {
            return false;
        }
        // Check specific permissions first
        if (permissions.contains(permission)) {
            return true;
        }
        // Check role-based permissions
        return hasRolePermission(permission);
    }
    
    /**
     * Checks role-based permissions.
     * ADMIN role has all permissions.
     * 
     * @param permission The permission to check
     * @return true if the role grants the permission
     */
    private boolean hasRolePermission(String permission) {
        if ("ADMIN".equals(role)) {
            return true; // Admin has all permissions
        }
        // Define role-based permission mappings
        if ("AGENCY_MANAGER".equals(role)) {
            return permission.startsWith("TOURIST_");
        }
        if ("AGENCY_OPERATOR".equals(role)) {
            return "TOURIST_MODIFY".equals(permission) || 
                   "TOURIST_VIEW".equals(permission);
        }
        return false;
    }
    
    /**
     * Updates the last login timestamp.
     */
    public void updateLastLogin() {
        this.lastLogin = LocalDateTime.now();
    }
    
    /**
     * Returns the full name of the operator.
     * 
     * @return Full name as "firstName lastName" or username if names are not set
     */
    public String getFullName() {
        if (firstName != null && lastName != null) {
            return firstName + " " + lastName;
        } else if (firstName != null) {
            return firstName;
        } else if (lastName != null) {
            return lastName;
        }
        return username;
    }
    
    /**
     * Validates the operator entity.
     * 
     * @return true if valid, false otherwise
     */
    public boolean validate() {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        if (passwordHash == null || passwordHash.trim().isEmpty()) {
            return false;
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            return false;
        }
        if (role == null || role.trim().isEmpty()) {
            return false;
        }
        if (active == null) {
            this.active = true; // Default to active
        }
        return true;
    }
    
    /**
     * Pre-persist hook for validation and default values.
     */
    @PrePersist
    public void prePersist() {
        if (active == null) {
            active = true;
        }
        validate();
    }
    
    /**
     * Pre-update hook for validation.
     */
    @PreUpdate
    public void preUpdate() {
        validate();
    }
}