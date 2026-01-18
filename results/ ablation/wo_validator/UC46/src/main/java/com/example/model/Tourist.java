package com.example.model;

/**
 * Represents a Tourist in the system.
 * According to the class diagram, Tourist has attributes userId, name, and method authenticate.
 */
public class Tourist {
    private String userId;
    private String name;

    public Tourist() {
    }

    public Tourist(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Authenticates the tourist.
     * In a real scenario, this would involve checking credentials.
     * For simplicity, we assume it returns true.
     */
    public boolean authenticate() {
        // Placeholder authentication logic
        return true;
    }
}