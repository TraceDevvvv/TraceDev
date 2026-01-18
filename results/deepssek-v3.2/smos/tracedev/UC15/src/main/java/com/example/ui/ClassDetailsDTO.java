package com.example.ui;

/**
 * Data Transfer Object for class details.
 * Used to transfer class data between layers.
 */
public class ClassDetailsDTO {
    public String id;
    public String name;
    public String address;
    public String schoolYear;

    /**
     * Constructor for ClassDetailsDTO.
     * @param id The unique identifier of the class.
     * @param name The name of the class.
     * @param address The address of the class.
     * @param schoolYear The school year of the class.
     */
    public ClassDetailsDTO(String id, String name, String address, String schoolYear) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.schoolYear = schoolYear;
    }
}