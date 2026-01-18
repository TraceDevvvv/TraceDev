package com.example.school.domain;

/**
 * Represents a Parent in the domain model.
 */
public class Parent {
    private String parentId;
    private String name;

    /**
     * Constructs a new Parent object.
     *
     * @param parentId The unique identifier for the parent.
     * @param name The name of the parent.
     */
    public Parent(String parentId, String name) {
        this.parentId = parentId;
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }
}