package com.example.model;

/**
 * Represents an Address entity.
 */
public class Address {
    private Long id;
    private String name;

    // Constructors
    public Address() {
    }

    public Address(String name) {
        this.name = name;
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
}