package com.example;

// Represents the domain model for a Class.
public class Class {
    private String id;
    private String name;
    private String address;
    private String academicYear;

    /**
     * Constructor for creating a new Class domain object.
     *
     * @param id The unique identifier for the class.
     * @param name The name of the class.
     * @param address The address associated with the class.
     * @param academicYear The academic year for the class.
     */
    public Class(String id, String name, String address, String academicYear) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.academicYear = academicYear;
    }

    // Getters for all attributes
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    /**
     * Updates the details of the class.
     *
     * @param name The new name for the class.
     * @param address The new address for the class.
     * @param academicYear The new academic year for the class.
     */
    public void updateDetails(String name, String address, String academicYear) {
        this.name = name;
        this.address = address;
        this.academicYear = academicYear;
        System.out.println("Class " + this.id + " updated with new details.");
    }

    @Override
    public String toString() {
        return "Class [id=" + id + ", name=" + name + ", address=" + address + ", academicYear=" + academicYear + "]";
    }
}