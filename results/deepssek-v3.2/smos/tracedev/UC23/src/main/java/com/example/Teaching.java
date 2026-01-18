package com.example;

/**
 * Represents a Teaching entity with an ID and name.
 */
public class Teaching {
    private int id;
    private String name;

    /**
     * Constructor for Teaching.
     * @param name the name of the teaching
     */
    public Teaching(String name) {
        this.name = name;
        this.id = generateId(); // Assumption: ID is auto-generated
    }

    /**
     * Gets the teaching's name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the teaching's name.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Validates the teaching data.
     * Assumption: Valid if name is not null and not empty.
     * @return true if data is valid
     */
    public boolean validate() {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Simulates ID generation.
     * @return a generated ID
     */
    private int generateId() {
        return (int) (Math.random() * 1000);
    }

    /**
     * Gets the teaching's ID.
     * @return the ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the teaching's ID.
     * @param id the new ID
     */
    public void setId(int id) {
        this.id = id;
    }
}