package com.example.dto;

import java.util.Objects;

/**
 * Data Transfer Object for Class information.
 */
public class ClassDTO {
    public int id;
    public String name;
    public int academicYear;

    public ClassDTO() {}

    public ClassDTO(int id, String name, int academicYear) {
        this.id = id;
        this.name = name;
        this.academicYear = academicYear;
    }

    /**
     * Generates a unique key for report cards for this class.
     * @return a key string combining class id and academic year.
     */
    public String getReportCardsKey() {
        return id + "-" + academicYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassDTO classDTO = (ClassDTO) o;
        return id == classDTO.id && academicYear == classDTO.academicYear;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, academicYear);
    }

    @Override
    public String toString() {
        return "ClassDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", academicYear=" + academicYear +
                '}';
    }
}