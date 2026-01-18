package com.example.tourist;

/**
 * Represents a tourist who can begin a search.
 */
public class Tourist {
    private String name;

    public Tourist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void beginSearch(TouristSearch searchType) {
        System.out.println("Tourist " + name + " has begun a " + searchType.toString().toLowerCase().replace('_', ' ') + ".");
    }
}