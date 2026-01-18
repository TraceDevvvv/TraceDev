package com.example.domain;

/**
 * Domain entity representing a Teacher.
 */
public class Teacher {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}