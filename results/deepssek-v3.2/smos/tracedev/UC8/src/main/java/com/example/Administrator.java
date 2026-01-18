package com.example;

/**
 * Actor representing an Administrator.
 */
public class Administrator {
    private String id;
    private String name;

    public Administrator(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}