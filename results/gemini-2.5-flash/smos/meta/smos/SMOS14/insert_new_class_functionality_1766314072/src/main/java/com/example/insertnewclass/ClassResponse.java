package com.example.insertnewclass;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for responding with class details after creation or retrieval.
 * This object carries data from the server to the client, typically after a successful operation.
 */
public class ClassResponse {

    /**
     * The unique identifier of the class.
     */
    private Long id;

    /**
     * The name of the class.
     */
    private String name;

    /**
     * The address or location of the class.
     */
    private String address;

    /**
     * The academic year for the class.
     */
    private String academicYear;

    /**
     * Default constructor.
     */
    public ClassResponse() {
    }

    /**
     * Constructor to create a new ClassResponse.
     *
     * @param id The unique identifier of the class.
     * @param name The name of the class.
     * @param address The address of the class.
     * @param academicYear The academic year of the class.
     */
    public ClassResponse(Long id, String name, String address, String academicYear) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.academicYear = academicYear;
    }

    /**
     * Retrieves the unique identifier of the class.
     * @return The ID of the class.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the class.
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the class.
     * @return The name of the class.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the class.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the address of the class.
     * @return The address of the class.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the class.
     * @param address The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the academic year of the class.
     * @return The academic year of the class.
     */
    public String getAcademicYear() {
        return academicYear;
    }

    /**
     * Sets the academic year of the class.
     * @param academicYear The academic year to set.
     */
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    /**
     * Compares this ClassResponse to the specified object. The result is true if and only if
     * the argument is not null and is a ClassResponse object that has the same id, name, address,
     * and academic year as this object.
     * @param o The object to compare this ClassResponse against.
     * @return true if the given object represents a ClassResponse equivalent to this ClassResponse, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassResponse that = (ClassResponse) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(address, that.address) &&
               Objects.equals(academicYear, that.academicYear);
    }

    /**
     * Returns a hash code value for the object.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, academicYear);
    }

    /**
     * Returns a string representation of the object.
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "ClassResponse{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", academicYear='" + academicYear + '\'' +
               '}';
    }
}