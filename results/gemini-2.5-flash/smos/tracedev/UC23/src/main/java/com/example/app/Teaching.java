package com.example.app;

/**
 * Teaching class represents a teaching entity with an ID and a name.
 */
public class Teaching {
    private String id;   // Unique identifier for the teaching
    private String name; // Name of the teaching

    /**
     * Constructs a new Teaching instance.
     *
     * @param id   The unique ID of the teaching.
     * @param name The name of the teaching.
     */
    public Teaching(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the name of the teaching.
     *
     * @return The name of the teaching.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the ID of the teaching.
     *
     * @return The ID of the teaching.
     */
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Teaching{id='" + id + "', name='" + name + "'}";
    }
}