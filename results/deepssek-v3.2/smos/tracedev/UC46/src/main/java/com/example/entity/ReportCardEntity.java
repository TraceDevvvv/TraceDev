package com.example.entity;

import java.util.Date;
import java.util.Map;

/**
 * Entity class representing a Report Card in the system.
 */
public class ReportCardEntity {
    private int id;
    private int studentId;
    private int classId;
    private int academicYear;
    private Map<String, Float> grades;
    private Date createdDate;

    public ReportCardEntity() {}

    public ReportCardEntity(int studentId, int classId, int academicYear, Map<String, Float> grades) {
        this.studentId = studentId;
        this.classId = classId;
        this.academicYear = academicYear;
        this.grades = grades;
        this.createdDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "ReportCardEntity{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", classId=" + classId +
                ", academicYear=" + academicYear +
                ", grades=" + grades +
                ", createdDate=" + createdDate +
                '}';
    }
}