'''
ClassDetails.java
A simple data class representing the details of a school class.
It holds properties such as the class name, its address, and the school year it belongs to.
'''
package com.chatdev.smos.model;
public class ClassDetails {
    private String name;
    private String address;
    private String schoolYear;
    /**
     * Constructs a new ClassDetails object.
     *
     * @param name The name of the class (e.g., "5th Grade - A").
     * @param address The physical address associated with the class, if any (e.g., "Room 301, Main Building").
     * @param schoolYear The academic year for which the class is active (e.g., "2023-2024").
     */
    public ClassDetails(String name, String address, String schoolYear) {
        this.name = name;
        this.address = address;
        this.schoolYear = schoolYear;
    }
    /**
     * Retrieves the name of the class.
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
     * Retrieves the address associated with the class.
     * @return The class address.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Sets the address associated with the class.
     * @param address The new class address.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Retrieves the school year of the class.
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
    /**
     * Provides a string representation of the ClassDetails object.
     * @return A formatted string with class name, address, and school year.
     */
    @Override
    public String toString() {
        return "Class Name: " + name + "\n" +
               "Address: " + address + "\n" +
               "School Year: " + schoolYear;
    }
}