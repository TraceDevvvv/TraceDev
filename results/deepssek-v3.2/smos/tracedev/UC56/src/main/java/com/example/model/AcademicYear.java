package com.example.model;

import java.util.List;
import com.example.dto.AcademicYearDTO;

/**
 * Represents an academic year entity.
 */
public class AcademicYear {
    private String id;
    private String yearLabel;
    private List<ClassYear> classYears; // contains relationship

    public AcademicYear() {
    }

    public AcademicYear(String id, String yearLabel, List<ClassYear> classYears) {
        this.id = id;
        this.yearLabel = yearLabel;
        this.classYears = classYears;
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

    public List<ClassYear> getClassYears() {
        return classYears;
    }

    public void setClassYears(List<ClassYear> classYears) {
        this.classYears = classYears;
    }

    /**
     * Transforms this entity to a DTO.
     * @return AcademicYearDTO
     */
    public AcademicYearDTO toDTO() {
        return new AcademicYearDTO(this.id, this.yearLabel);
    }
}