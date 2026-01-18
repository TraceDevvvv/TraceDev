package com.agency.domain;

/**
 * Domain entity representing an Agency Operator.
 */
public class AgencyOperator {
    private String userId;
    private String username;
    private String hashedPassword;
    private String email;

    public AgencyOperator(String userId, String username, String hashedPassword, String email) {
        this.userId = userId;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Changes the operator's password.
     * @param newHashedPassword The new hashed password.
     * @return true if the password was changed (always true in this implementation).
     */
    public boolean changePassword(String newHashedPassword) {
        this.hashedPassword = newHashedPassword;
        return true;
    }

    /**
     * Verifies a plain password against the stored hashed password using the given strategy.
     * @param password Plain password to verify.
     * @param strategy Password strategy to use for verification.
     * @return true if the password matches, false otherwise.
     */
    public boolean verifyPassword(String password, IPasswordStrategy strategy) {
        return strategy.verifyPassword(password, this.hashedPassword);
    }
}