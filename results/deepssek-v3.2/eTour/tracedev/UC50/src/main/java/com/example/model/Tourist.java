package com.example.model;

/**
 * Tourist model (simplified for this example).
 */
public class Tourist {
    public String userId;
    public String name;

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
}