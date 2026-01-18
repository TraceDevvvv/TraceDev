package com.example.reportcard.dto;

/**
 * DTO for transferring Academic Year information.
 * Used for displaying academic years to the user.
 */
public class AcademicYearDTO {
    private String id;
    private String year;

    public AcademicYearDTO(String id, String year) {
        this.id = id;
        this.year = year;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    // Setters (if needed, but DTOs are often immutable or only modified during creation)
    public void setId(String id) {
        this.id = id;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "AcademicYearDTO{" +
               "id='" + id + '\'' +
               ", year='" + year + '\'' +
               '}';
    }
}