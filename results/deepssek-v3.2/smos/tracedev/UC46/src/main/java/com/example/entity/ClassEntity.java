package com.example.entity;

/**
 * Entity class representing a Class in the system.
 */
public class ClassEntity {
    private int id;
    private String name;
    private int academicYear;

    public ClassEntity() {}

    public ClassEntity(int id, String name, int academicYear) {
        this.id = id;
        this.name = name;
        this.academicYear = academicYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    @Override
    public String toString() {
        return "ClassEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", academicYear=" + academicYear +
                '}';
    }
}