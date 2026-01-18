package com.example.app;

/**
 * Represents a Class entity in the system.
 * This class corresponds to the 'Class' class in the UML Class Diagram.
 */
public class Class {
    private String id;
    private String name;
    private String address;
    private String schoolYear;

    /**
     * Constructs a new Class instance.
     *
     * @param id The unique identifier of the class.
     * @param name The name of the class.
     * @param address The address where the class is held.
     * @param schoolYear The academic school year for the class.
     */
    public Class(String id, String name, String address, String schoolYear) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.schoolYear = schoolYear;
    }

    /**
     * Gets the unique identifier of the class.
     * @return The class ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the class.
     * @param id The new class ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the class.
     * @return The class name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the class.
     * @param name The new class name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the address where the class is held.
     * @return The class address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address where the class is held.
     * @param address The new class address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the academic school year for the class.
     * @return The school year.
     */
    public String getSchoolYear() {
        return schoolYear;
    }

    /**
     * Sets the academic school year for the class.
     * @param schoolYear The new school year.
     */
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public String toString() {
        return "Class{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", schoolYear='" + schoolYear + '\'' +
               '}';
    }
}