package com.example.dto;

import java.util.Map;
import java.util.Objects;

/**
 * Data Transfer Object for Report Card information.
 */
public class ReportCardDTO {
    public int studentId;
    public int classId;
    public Map<String, Float> grades;

    public ReportCardDTO() {}

    public ReportCardDTO(int studentId, int classId, Map<String, Float> grades) {
        this.studentId = studentId;
        this.classId = classId;
        this.grades = grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportCardDTO that = (ReportCardDTO) o;
        return studentId == that.studentId && classId == that.classId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, classId);
    }

    @Override
    public String toString() {
        return "ReportCardDTO{" +
                "studentId=" + studentId +
                ", classId=" + classId +
                ", grades=" + grades +
                '}';
    }
}