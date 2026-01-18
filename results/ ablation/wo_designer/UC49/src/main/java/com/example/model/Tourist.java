package com.example.model;

/**
 * Represents a Tourist user in the system.
 */
public class Tourist {
    private String id;
    private String username;

    public Tourist(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}