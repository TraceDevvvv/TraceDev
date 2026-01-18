/**
 * A simple Plain Old Java Object (POJO) representing a student's report card.
 * Stores grades for various subjects in a map.
 */
import java.util.HashMap;
import java.util.Map;
public class ReportCard {
    private String studentId;
    private String academicYear;
    private Map<String, Double> grades; // Key: subject name, Value: grade
    public ReportCard(String studentId, String academicYear) {
        this.studentId = studentId;
        this.academicYear = academicYear;
        this.grades = new HashMap<>();
    }
    // Getters
    public String getStudentId() {
        return studentId;
    }
    public String getAcademicYear() {
        return academicYear;
    }
    public Map<String, Double> getGrades() {
        return new HashMap<>(grades); // Return a copy to prevent external modification
    }
    /**
     * Sets or updates a grade for a specific subject.
     * @param subject The name of the subject.
     * @param grade The numerical grade.
     */
    public void setGrade(String subject, Double grade) {
        this.grades.put(subject, grade);
    }
    /**
     * Retrieves the grade for a specific subject.
     * @param subject The name of the subject.
     * @return The grade for the subject, or null if not found.
     */
    public Double getGrade(String subject) {
        return grades.get(subject);
    }
}