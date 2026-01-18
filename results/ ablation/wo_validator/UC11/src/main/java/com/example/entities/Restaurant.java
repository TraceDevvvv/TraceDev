package com.example.entities;

/**
 * Restaurant entity as per class diagram.
 * Convention has a many-to-one association with Restaurant.
 */
public class Restaurant {
    private String id;
    private String name;
    private String address;

    public Restaurant(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}