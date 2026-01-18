package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Domain entity representing a User.
 * Attributes and methods as per the class diagram.
 */
public class User {
    private String userId;
    private String username;
    private String email;
    private boolean isActive;

    public User(String userId, String username, String email, boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.isActive = isActive;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Returns a map of user details as per the class diagram.
     * @return Map containing key user attributes.
     */
    public Map<String, Object> getUserDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("userId", userId);
        details.put("username", username);
        details.put("email", email);
        details.put("isActive", isActive);
        return details;
    }
}