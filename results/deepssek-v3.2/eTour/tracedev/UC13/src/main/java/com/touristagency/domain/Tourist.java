package com.touristagency.domain;

/**
 * Represents a Tourist entity in the domain layer.
 * Encapsulates tourist data and provides methods to activate/deactivate the account.
 */
public class Tourist {
    private final String id;
    private final String name;
    private final String email;
    private boolean isActive;

    /**
     * Constructor for creating a Tourist.
     * New tourists are assumed to be inactive by default.
     * @param id unique identifier
     * @param name full name
     * @param email email address
     */
    public Tourist(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isActive = false; // default inactive
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean getIsActive() {
        return isActive;
    }

    /**
     * Activates the tourist account.
     */
    public void activate() {
        this.isActive = true;
    }

    /**
     * Deactivates the tourist account.
     */
    public void deactivate() {
        this.isActive = false;
    }

    @Override
    public String toString() {
        return "Tourist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}