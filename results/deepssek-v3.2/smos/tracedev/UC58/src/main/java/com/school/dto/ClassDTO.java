package com.school.dto;

import java.util.Objects;

/**
 * Data Transfer Object for Class.
 */
public class ClassDTO {
    public int Id;
    public String Name;
    public int AcademicYearId;

    public ClassDTO() {}

    public ClassDTO(int id, String name, int academicYearId) {
        this.Id = id;
        this.Name = name;
        this.AcademicYearId = academicYearId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAcademicYearId() {
        return AcademicYearId;
    }

    public void setAcademicYearId(int academicYearId) {
        AcademicYearId = academicYearId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassDTO classDTO = (ClassDTO) o;
        return Id == classDTO.Id && AcademicYearId == classDTO.AcademicYearId && Objects.equals(Name, classDTO.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Name, AcademicYearId);
    }

    @Override
    public String toString() {
        return "ClassDTO{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", AcademicYearId=" + AcademicYearId +
                '}';
    }
}