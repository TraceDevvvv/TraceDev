package com.example.domain;

/**
 * Represents a Parent in the domain model.
 */
public class Parent {
    private final String id;
    private final String name; // Class Diagram: attribute 'name'

    public Parent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // For simplicity, no setters are provided, assuming ID and name are immutable after creation.
}