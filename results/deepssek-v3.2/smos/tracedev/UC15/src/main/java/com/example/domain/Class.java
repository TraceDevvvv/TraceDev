package com.example.domain;

/**
 * Core business entity representing a class.
 * This class holds the class details such as id, name, address, and school year.
 */
public class Class {
    public String id;
    public String name;
    public String address;
    public String schoolYear;

    /**
     * Constructor for Class.
     * @param id The unique identifier of the class.
     * @param name The name of the class.
     * @param address The address of the class.
     * @param schoolYear The school year of the class.
     */
    public Class(String id, String name, String address, String schoolYear) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.schoolYear = schoolYear;
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

    public String getSchoolYear() {
        return schoolYear;
    }
}