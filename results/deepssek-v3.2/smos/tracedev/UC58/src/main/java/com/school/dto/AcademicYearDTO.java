package com.school.dto;

import java.util.Objects;

/**
 * Data Transfer Object for AcademicYear.
 */
public class AcademicYearDTO {
    public int Id;
    public String Year;

    public AcademicYearDTO() {}

    public AcademicYearDTO(int id, String year) {
        this.Id = id;
        this.Year = year;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYearDTO that = (AcademicYearDTO) o;
        return Id == that.Id && Objects.equals(Year, that.Year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Year);
    }

    @Override
    public String toString() {
        return "AcademicYearDTO{" +
                "Id=" + Id +
                ", Year='" + Year + '\'' +
                '}';
    }
}