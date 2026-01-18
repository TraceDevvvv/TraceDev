package com.school.dto;

import java.util.Objects;

/**
 * Data Transfer Object for Student.
 */
public class StudentDTO {
    public int Id;
    public String Name;

    public StudentDTO() {}

    public StudentDTO(int id, String name) {
        this.Id = id;
        this.Name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return Id == that.Id && Objects.equals(Name, that.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Name);
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                '}';
    }
}