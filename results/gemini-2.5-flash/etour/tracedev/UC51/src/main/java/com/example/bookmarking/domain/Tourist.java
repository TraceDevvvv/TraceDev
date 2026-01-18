package com.example.bookmarking.domain;

/**
 * Represents a Tourist in the domain model.
 */
public class Tourist {
    /** Unique identifier for the tourist. */
    public String id;
    /** Name of the tourist. */
    public String name;

    /**
     * Constructs a new Tourist instance.
     * @param id The unique identifier for the tourist.
     * @param name The name of the tourist.
     */
    public Tourist(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Retrieves the tourist's unique identifier.
     * @return The tourist's ID.
     */
    public String getTouristId() {
        return id;
    }

    @Override
    public String toString() {
        return "Tourist{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}