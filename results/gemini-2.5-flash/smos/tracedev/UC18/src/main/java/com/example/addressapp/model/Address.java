package com.example.addressapp.model;

import java.util.UUID;

/**
 * Represents an Address entity in the system.
 * This class holds the data for an address, including its unique ID and name.
 */
public class Address {
    private String id; // Unique identifier for the address
    private String name; // The name of the address

    /**
     * Constructs a new Address with a given name and generates a random ID.
     * @param name The name of the address.
     */
    public Address(String name) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID upon creation
        this.name = name;
    }

    /**
     * Constructs a new Address with a specific ID and name.
     * This constructor is useful when loading an address from a persistent store.
     * @param id The unique ID of the address.
     * @param name The name of the address.
     */
    public Address(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Sets the name of the address.
     * @param name The new name for the address.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the address.
     * @return The name of the address.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the unique ID of the address.
     * @param id The new ID for the address.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the unique ID of the address.
     * @return The unique ID of the address.
     */
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Address{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}