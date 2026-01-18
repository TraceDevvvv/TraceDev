package com.example.domain;

/**
 * Domain entity representing a Student.
 */
public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}