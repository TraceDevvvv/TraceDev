package com.example.app.domain;

import com.example.app.Domain;
import java.util.Date;

/**
 * Represents a Student in the domain.
 */
public class Student implements Domain {
    public String id;
    public String name;
    public Date dob;

 
    public Student(String id, String name, Date dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    // Getters for public fields
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return dob;
    }
}