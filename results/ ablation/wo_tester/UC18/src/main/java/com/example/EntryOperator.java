package com.example;

/**
 * Represents the entry operator who interacts with the system.
 * This class is a representation of an actor in the UML diagrams.
 */
public class EntryOperator {
    private String id;
    private String name;

    /**
     * Constructor for EntryOperator.
     * @param id The unique identifier of the operator.
     * @param name The name of the operator.
     */
    public EntryOperator(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Additional methods can be added as needed.
}