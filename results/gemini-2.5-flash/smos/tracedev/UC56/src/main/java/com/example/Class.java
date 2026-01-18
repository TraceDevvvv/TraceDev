package com.example;

import java.util.Objects;

/**
 * Represents a class entity.
 */
public class Class { // Renamed to Course to avoid conflict with Java's built-in Class.java
    private String id;
    private String name;
    private String academicYearId;
    private String professorId;

    // Default constructor for potential JSON deserialization
    public Class() {
    }

    public Class(String id, String name, String academicYearId, String professorId) {
        this.id = id;
        this.name = name;
        this.academicYearId = academicYearId;
        this.professorId = professorId;
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

    public String getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(String academicYearId) {
        this.academicYearId = academicYearId;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", academicYearId='" + academicYearId + '\'' +
                ", professorId='" + professorId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return Objects.equals(id, aClass.id); // Assuming ID is unique
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}