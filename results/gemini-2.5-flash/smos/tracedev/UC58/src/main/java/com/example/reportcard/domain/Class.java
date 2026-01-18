package com.example.reportcard.domain;

/**
 * Domain entity representing a class.
 */
public class Class {
    private String id;
    private String name; // e.g., "Mathematics 101"
    private String academicYearId;
    private String professorId;

    public Class(String id, String name, String academicYearId, String professorId) {
        this.id = id;
        this.name = name;
        this.academicYearId = academicYearId;
        this.professorId = professorId;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }

    public String getProfessorId() {
        return professorId;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAcademicYearId(String academicYearId) {
        this.academicYearId = academicYearId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }
}