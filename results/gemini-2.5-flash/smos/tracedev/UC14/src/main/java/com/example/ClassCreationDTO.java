package com.example;

import java.util.UUID;

// Data Transfer Object for creating a new Class.
public class ClassCreationDTO {
    public String name;
    public String address;
    public String academicYear;

    /**
     * Constructor for ClassCreationDTO.
     *
     * @param name The name of the class.
     * @param address The address of the class.
     * @param academicYear The academic year of the class.
     */
    public ClassCreationDTO(String name, String address, String academicYear) {
        this.name = name;
        this.address = address;
        this.academicYear = academicYear;
    }

    /**
     * Converts this DTO into a domain Class object.
     * A unique ID is generated for the new Class.
     *
     * @return A new Class domain object.
     */
    public Class toDomainClass() {
        // Generate a unique ID for the new Class
        String id = UUID.randomUUID().toString();
        System.out.println("DTO converting to domain Class with generated ID: " + id);
        return new Class(id, name, address, academicYear);
    }
}