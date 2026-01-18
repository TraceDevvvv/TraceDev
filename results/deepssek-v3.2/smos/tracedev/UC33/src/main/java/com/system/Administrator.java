package com.system;

/**
 * Represents an Administrator user who interacts with the Delay Elimination system.
 */
public class Administrator {
    private String userId;
    private String username;

    public Administrator(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Simulates administrator login.
     * @return true if login successful.
     */
    public boolean login() {
        // In a real system, credentials would be validated.
        System.out.println("Administrator " + username + " logged in.");
        return true;
    }

    /**
     * Simulates administrator logout.
     */
    public void logout() {
        System.out.println("Administrator " + username + " logged out.");
    }
}