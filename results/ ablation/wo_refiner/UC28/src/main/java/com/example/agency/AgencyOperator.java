package com.example.agency;

/**
 * Represents the logged-in agency operator who performs tag management operations.
 * Added to satisfy requirement REQ-003, REQ-004
 */
public class AgencyOperator {
    private Long id;
    private String name;

    public AgencyOperator() {
    }

    public AgencyOperator(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the operator is authenticated.
     * @return true if authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        // Simplified: assume authentication is based on presence of id and name
        return id != null && name != null && !name.trim().isEmpty();
    }
}