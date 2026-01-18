package com.example.school.domain;

/**
 * Represents a subject taught in the school (e.g., Math, Science).
 */
public class Subject {
    public String id;
    public String name;

    /**
     * Constructs a new Subject.
     * @param id The unique identifier for the subject.
     * @param name The name of the subject.
     */
    public Subject(String id, String name) {
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