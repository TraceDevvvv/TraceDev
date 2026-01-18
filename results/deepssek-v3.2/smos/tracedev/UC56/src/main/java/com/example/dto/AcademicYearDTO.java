package com.example.dto;

/**
 * Data Transfer Object for Academic Year.
 */
public class AcademicYearDTO {
    private String id;
    private String yearLabel;

    public AcademicYearDTO() {
    }

    public AcademicYearDTO(String id, String yearLabel) {
        this.id = id;
        this.yearLabel = yearLabel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYearLabel() {
        return yearLabel;
    }

    public void setYearLabel(String yearLabel) {
        this.yearLabel = yearLabel;
    }
}