package com.example.app;

/**
 * Data Transfer Object (DTO) for displaying class details.
 * This class corresponds to the 'ClassDetailsDTO' class in the UML Class Diagram.
 */
public class ClassDetailsDTO {
    public String name;
    public String address;
    public String schoolYear;

    /**
     * Constructs a new ClassDetailsDTO instance.
     *
     * @param name The name of the class.
     * @param address The address of the class.
     * @param schoolYear The school year of the class.
     */
    public ClassDetailsDTO(String name, String address, String schoolYear) {
        this.name = name;
        this.address = address;
        this.schoolYear = schoolYear;
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
     * Gets the address of the class.
     * @return The class address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the class.
     * @param address The new class address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the school year of the class.
     * @return The school year.
     */
    public String getSchoolYear() {
        return schoolYear;
    }

    /**
     * Sets the school year of the class.
     * @param schoolYear The new school year.
     */
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public String toString() {
        return "ClassDetailsDTO{" +
               "name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", schoolYear='" + schoolYear + '\'' +
               '}';
    }
}