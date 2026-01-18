package com.example.entity;

/**
 * Represents a restaurant entity.
 */
public class Restaurant {
    private Long id;
    private String name;
    private Long operatorId;

    // Constructor
    public Restaurant(String name, Long operatorId) {
        this.name = name;
        this.operatorId = operatorId;
    }

    // Getters and setters
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

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
}