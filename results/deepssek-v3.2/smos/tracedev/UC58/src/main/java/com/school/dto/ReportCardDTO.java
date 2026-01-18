package com.school.dto;

import java.util.Map;
import java.util.Objects;

/**
 * Data Transfer Object for ReportCard.
 */
public class ReportCardDTO {
    public String StudentName;
    public int Quarter;
    public Map<String, String> Grades;
    public String Comments;

    public ReportCardDTO() {}

    public ReportCardDTO(String studentName, int quarter, Map<String, String> grades, String comments) {
        this.StudentName = studentName;
        this.Quarter = quarter;
        this.Grades = grades;
        this.Comments = comments;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public int getQuarter() {
        return Quarter;
    }

    public void setQuarter(int quarter) {
        Quarter = quarter;
    }

    public Map<String, String> getGrades() {
        return Grades;
    }

    public void setGrades(Map<String, String> grades) {
        Grades = grades;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportCardDTO that = (ReportCardDTO) o;
        return Quarter == that.Quarter && Objects.equals(StudentName, that.StudentName) && Objects.equals(Grades, that.Grades) && Objects.equals(Comments, that.Comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StudentName, Quarter, Grades, Comments);
    }

    @Override
    public String toString() {
        return "ReportCardDTO{" +
                "StudentName='" + StudentName + '\'' +
                ", Quarter=" + Quarter +
                ", Grades=" + Grades +
                ", Comments='" + Comments + '\'' +
                '}';
    }
}