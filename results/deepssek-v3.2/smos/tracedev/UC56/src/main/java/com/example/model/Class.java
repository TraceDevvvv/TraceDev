package com.example.model;

import com.example.dto.ClassDTO;

/**
 * Represents a class entity.
 */
public class Class {
    private String id;
    private String name;
    private String professorId;
    private String academicYearId;

    public Class() {
    }

    public Class(String id, String name, String professorId, String academicYearId) {
        this.id = id;
        this.name = name;
        this.professorId = professorId;
        this.academicYearId = academicYearId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(String academicYearId) {
        this.academicYearId = academicYearId;
    }

    /**
     * Transforms this entity to a DTO.
     * Note: professorName is not stored in Class entity; we assume it will be retrieved elsewhere.
     * For simplicity, we set professorName as empty string here.
     * @return ClassDTO
     */
    public ClassDTO toDTO() {
        return new ClassDTO(this.id, this.name, ""); // professorName is empty; could be fetched from Professor service
    }
}