package com.system.auth;

import com.system.entity.Absence;

/**
 * Represents the current user's session.
 * Tracks login state, roles, and active use cases.
 */
public class UserSession {
    private String userId;
    private String role;
    private boolean loggedIn;
    // For simplicity, we assume the user can click on an absence (as per requirement)
    private Absence clickedAbsence;

    public UserSession() {
        this.loggedIn = false;
    }

    public UserSession(String userId, String role, boolean loggedIn) {
        this.userId = userId;
        this.role = role;
        this.loggedIn = loggedIn;
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

    /**
     * Checks if the user is logged in.
     * @return true if the user is logged in.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * Checks if the user has the specified role.
     * @param roleName The role to check.
     * @return true if the user has the role.
     */
    public boolean hasRole(String roleName) {
        return roleName != null && roleName.equals(this.role);
    }

    /**
     * Checks if the user has an active use case.
     * For simplicity, we assume they always have the use case if logged in.
     * @param useCaseId The use case identifier.
     * @return true if the use case is active.
     */
    public boolean hasActiveUseCase(String useCaseId) {
        // Simplified: logged-in users have active use cases.
        return loggedIn && useCaseId != null;
    }

    public Absence getClickedAbsence() {
        return clickedAbsence;
    }

    public void setClickedAbsence(Absence clickedAbsence) {
        this.clickedAbsence = clickedAbsence;
    }
}