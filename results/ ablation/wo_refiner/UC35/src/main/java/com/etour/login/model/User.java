package com.etour.login.model;

import com.etour.login.security.PasswordEncoder;
import java.util.List;

/**
 * Domain entity representing a user.
 * Contains user id, username, encrypted password and privileges.
 */
public class User {
    private String userId;
    private String username;
    private String encryptedPassword;
    private List<String> privileges;

    public User() {
    }

    public User(String userId, String username, String encryptedPassword, List<String> privileges) {
        this.userId = userId;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.privileges = privileges;
    }

    /**
     * Validates the input password against the stored encrypted password.
     *
     * @param inputPassword the raw password input
     * @param encoder the password encoder to use for matching
     * @return true if password matches, false otherwise
     */
    public boolean validatePassword(String inputPassword, PasswordEncoder encoder) {
        return encoder.matches(inputPassword, this.encryptedPassword);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public List<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<String> privileges) {
        this.privileges = privileges;
    }
}