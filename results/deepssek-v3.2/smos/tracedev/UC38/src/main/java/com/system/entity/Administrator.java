package com.system.entity;

/**
 * Class representing an Administrator, as per class diagram.
 * Contains attributes and methods exactly as specified.
 */
public class Administrator {
    public String userId;
    public String role;

    public Administrator() {
    }

    public Administrator(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public boolean isLoggedIn() {
        // This method should check some session or context.
        // For simplicity, we assume a static check; in reality would depend on a session.
        return true;
    }

    public boolean hasRole(String roleName) {
        return roleName != null && roleName.equals(this.role);
    }

    public boolean hasActiveUseCase(String useCaseId) {
        // Simplified: if logged in, assume active use case.
        return isLoggedIn() && useCaseId != null;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}