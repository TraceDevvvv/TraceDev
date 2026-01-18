package com.example.notessystem.service;

import java.util.Objects;

/**
 * Service Layer for Authentication (Implicitly used as a precondition).
 * Added to satisfy requirement R1 from the class diagram notes.
 * This is a placeholder for actual authentication logic.
 */
public class AuthenticationService {

    /**
     * Simulates checking if a user is authenticated.
     * @param userId The ID of the user to check.
     * @return true if authenticated, false otherwise.
     */
    public boolean isAuthenticated(String userId) {
        // Mock implementation: always true for a specific user, false otherwise
        System.out.println("AuthService: Checking authentication for user: " + userId);
        return "admin".equals(userId) || "teacher".equals(userId); // Assuming 'admin' or 'teacher' are authenticated roles
    }

    /**
     * Simulates a login operation.
     * @param username The username.
     * @param password The password.
     * @return A UserSession object if login is successful, null otherwise.
     */
    public UserSession login(String username, String password) {
        System.out.println("AuthService: Attempting login for username: " + username);
        // Mock implementation: simple username/password check
        if ("admin".equals(username) && "password".equals(password)) {
            return new UserSession(username, "admin", System.currentTimeMillis());
        }
        return null;
    }

    // You can add other authentication methods as needed, e.g., logout, changePassword, etc.

    /**
     * A simple mock class representing a user session after successful login.
     */
    public static class UserSession {
        private String userId;
        private String role;
        private long loginTime;

        public UserSession(String userId, String role, long loginTime) {
            this.userId = userId;
            this.role = role;
            this.loginTime = loginTime;
        }

        public String getUserId() {
            return userId;
        }

        public String getRole() {
            return role;
        }

        public long getLoginTime() {
            return loginTime;
        }

        @Override
        public String toString() {
            return "UserSession{" +
                   "userId='" + userId + '\'' +
                   ", role='" + role + '\'' +
                   ", loginTime=" + loginTime +
                   '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserSession that = (UserSession) o;
            return Objects.equals(userId, that.userId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId);
        }
    }
}