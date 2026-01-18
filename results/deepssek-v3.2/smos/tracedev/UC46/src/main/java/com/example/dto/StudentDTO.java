package com.example.dto;

import java.util.Objects;

/**
 * Data Transfer Object for Student information.
 */
public class StudentDTO {
    public int id;
    public String fullName;
    public int classId;

    public StudentDTO() {}

    public StudentDTO(int id, String fullName, int classId) {
        this.id = id;
        this.fullName = fullName;
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", classId=" + classId +
                '}';
    }
}