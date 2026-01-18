package com.example.statistics;

/**
 * Domain Layer Entity
 * Represents an operator in the system.
 */
public class Operator {
    private String id;
    private String name;

    /**
     * Constructor for Operator.
     * @param id The unique ID of the operator.
     * @param name The name of the operator.
     */
    public Operator(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Standard setters could be added if mutable, but for domain entities, they are often immutable or modified via specific methods.
    // For this example, we'll keep them read-only.
}