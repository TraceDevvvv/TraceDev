package com.example.insertdelayadmin.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Custom implementation of Spring Security's {@link UserDetails} interface.
 * This class wraps the {@link com.example.insertdelayadmin.model.Administrator} entity
 * to provide the necessary user information for Spring Security's authentication and authorization processes.
 */
public class CustomUserDetails implements UserDetails {

    private String id;
    private String username;
    private String password; // This will store the password hash
    private String role;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructor for CustomUserDetails.
     *
     * @param id The unique identifier of the administrator.
     * @param username The username of the administrator.
     * @param passwordHash The hashed password of the administrator.
     * @param role The role of the administrator (e.g., "ADMIN").
     */
    public CustomUserDetails(String id, String username, String passwordHash, String role) {
        this.id = id;
        this.username = username;
        this.password = passwordHash;
        this.role = role;
        // Roles in Spring Security typically start with "ROLE_"
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
    }

    /**
     * Returns the unique identifier of the administrator.
     * This is a custom field not part of the standard UserDetails interface,
     * but useful for retrieving the administrator's ID after authentication.
     *
     * @return The administrator's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the authorities granted to the user.
     *
     * @return A collection of {@link GrantedAuthority} objects.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Returns the password used to authenticate the user.
     * In this case, it's the hashed password.
     *
     * @return The user's password (hashed).
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username used to authenticate the user.
     *
     * @return The user's username.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Indicates whether the user's account has expired.
     * An expired account cannot be authenticated.
     *
     * @return true if the user's account is valid (non-expired), false otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // For simplicity, accounts do not expire in this example
    }

    /**
     * Indicates whether the user is locked or unlocked.
     * A locked user cannot be authenticated.
     *
     * @return true if the user is not locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; // For simplicity, accounts are not locked in this example
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     * Expired credentials prevent authentication.
     *
     * @return true if the user's credentials are valid (non-expired), false otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // For simplicity, credentials do not expire in this example
    }

    /**
     * Indicates whether the user is enabled or disabled.
     * A disabled user cannot be authenticated.
     *
     * @return true if the user is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true; // For simplicity, users are always enabled in this example
    }
}