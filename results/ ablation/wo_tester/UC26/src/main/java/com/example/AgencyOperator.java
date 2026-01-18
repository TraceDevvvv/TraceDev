package com.example;

/**
 * Represents an agency operator who can log in and perform operations.
 */
public class AgencyOperator {
    private String id;
    private String name;

    public AgencyOperator(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Logs in the operator.
     * @return true if login is successful (simplified for this implementation)
     */
    public boolean login() {
        // In a real system, this would validate credentials.
        // For simplicity, assume login always succeeds.
        return true;
    }
}