package com.example.app;

/**
 * TeachingData is a simple data transfer object (DTO) used for input when creating a new teaching.
 * It primarily holds the name of the teaching.
 */
public class TeachingData {
    // The diagram specifies 'public name : String'. While often private with a getter,
    // we adhere strictly to the diagram for this attribute's visibility.
    public String name;

    /**
     * Constructs a new TeachingData instance with the given name.
     *
     * @param name The name of the teaching.
     */
    public TeachingData(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TeachingData{name='" + name + "'}";
    }
}