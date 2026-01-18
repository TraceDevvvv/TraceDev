package com.example.insertnewclass;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for creating a new class.
 * This object carries data from the client to the server for class creation.
 * It includes validation annotations to ensure data integrity as per PRD.
 */
public class ClassCreateRequest {

    /**
     * The name of the class.
     * Must not be blank, must be alphanumeric (allowing spaces and some special characters),
     * and have a maximum length of 100 characters.
     */
    @NotBlank(message = "Class name cannot be blank.")
    @Size(max = 100, message = "Class name cannot exceed 100 characters.")
    // Pattern allows alphanumeric, spaces, and common punctuation like hyphens, apostrophes, commas, periods.
    // This is a more flexible interpretation of "alphanumeric" to allow realistic class names.
    @Pattern(regexp = "^[a-zA-Z0-9\\s.,'\\-()&]*$", message = "Class name contains invalid characters.")
    private String name;

    /**
     * The address or location of the class.
     * Must not be blank and have a maximum length of 200 characters.
     * Allows alphanumeric characters, spaces, and common punctuation.
     */
    @NotBlank(message = "Class address cannot be blank.")
    @Size(max = 200, message = "Class address cannot exceed 200 characters.")
    // Pattern allows alphanumeric, spaces, and common punctuation for addresses.
    @Pattern(regexp = "^[a-zA-Z0-9\\s.,'\\-#/]*$", message = "Class address contains invalid characters.")
    private String address;

    /**
     * The academic year for the class.
     * Must not be blank and must follow the YYYY-YYYY format.
     */
    @NotBlank(message = "Academic year cannot be blank.")
    @Pattern(regexp = "^(19|20)\\d{2}-(19|20)\\d{2}$", message = "Academic year must be in YYYY-YYYY format.")
    private String academicYear;

    /**
     * Default constructor.
     */
    public ClassCreateRequest() {
    }

    /**
     * Constructor to create a new ClassCreateRequest.
     *
     * @param name The name of the class.
     * @param address The address of the class.
     * @param academicYear The academic year of the class.
     */
    public ClassCreateRequest(String name, String address, String academicYear) {
        this.name = name;
        this.address = address;
        this.academicYear = academicYear;
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
     * Compares this ClassCreateRequest to the specified object. The result is true if and only if
     * the argument is not null and is a ClassCreateRequest object that has the same name, address,
     * and academic year as this object.
     * @param o The object to compare this ClassCreateRequest against.
     * @return true if the given object represents a ClassCreateRequest equivalent to this ClassCreateRequest, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassCreateRequest that = (ClassCreateRequest) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(address, that.address) &&
               Objects.equals(academicYear, that.academicYear);
    }

    /**
     * Returns a hash code value for the object.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, address, academicYear);
    }

    /**
     * Returns a string representation of the object.
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "ClassCreateRequest{" +
               "name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", academicYear='" + academicYear + '\'' +
               '}';
    }
}