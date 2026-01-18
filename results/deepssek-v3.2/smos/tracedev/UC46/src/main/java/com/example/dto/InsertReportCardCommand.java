package com.example.dto;

import java.util.Map;
import java.util.Objects;

/**
 * Command object for inserting a report card.
 */
public class InsertReportCardCommand {
    private int studentId;
    private int classId;
    private int academicYear;
    private Map<String, Float> grades;

    public InsertReportCardCommand() {}

    public InsertReportCardCommand(int studentId, int classId, int academicYear, Map<String, Float> grades) {
        this.studentId = studentId;
        this.classId = classId;
        this.academicYear = academicYear;
        this.grades = grades;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    public Map<String, Float> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, Float> grades) {
        this.grades = grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsertReportCardCommand that = (InsertReportCardCommand) o;
        return studentId == that.studentId && classId == that.classId && academicYear == that.academicYear;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, classId, academicYear);
    }

    @Override
    public String toString() {
        return "InsertReportCardCommand{" +
                "studentId=" + studentId +
                ", classId=" + classId +
                ", academicYear=" + academicYear +
                ", grades=" + grades +
                '}';
    }
}