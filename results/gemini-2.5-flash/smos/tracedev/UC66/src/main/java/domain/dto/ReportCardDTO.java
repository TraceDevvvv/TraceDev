package domain.dto;

import java.util.Map;
import java.util.HashMap;

/**
 * Data Transfer Object for a comprehensive Report Card.
 * Contains all necessary details to display a student's report card.
 */
public class ReportCardDTO {
    public String studentName;
    public String className;
    public String academicYear;
    public String quadrimestre;
    public Map<String, String> grades;
    public String comments;

    /**
     * Constructs a new ReportCardDTO.
     * @param studentName The name of the student.
     * @param className The name of the class the student belongs to.
     * @param academicYear The academic year for this report card.
     * @param quadrimestre The quadrimestre for this report card.
     * @param grades A map where keys are subject names and values are grades.
     * @param comments Any additional comments for the report card.
     */
    public ReportCardDTO(String studentName, String className, String academicYear, String quadrimestre, Map<String, String> grades, String comments) {
        this.studentName = studentName;
        this.className = className;
        this.academicYear = academicYear;
        this.quadrimestre = quadrimestre;
        this.grades = grades != null ? grades : new HashMap<>();
        this.comments = comments;
    }

    // Getters
    public String getStudentName() {
        return studentName;
    }

    public String getClassName() {
        return className;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getQuadrimestre() {
        return quadrimestre;
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "ReportCardDTO{" +
               "studentName='" + studentName + '\'' +
               ", className='" + className + '\'' +
               ", academicYear='" + academicYear + '\'' +
               ", quadrimestre='" + quadrimestre + '\'' +
               ", grades=" + grades +
               ", comments='" + comments + '\'' +
               '}';
    }
}