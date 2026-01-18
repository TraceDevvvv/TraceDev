package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a student's report card in the domain layer.
 * Maps Subject ID to grade score.
 */
public class ReportCard {
    private String id;
    private String studentId; // Added for consistency with findByStudentId
    private Map<String, Integer> grades; // Maps Subject ID to grade score

    /**
     * Constructs a new ReportCard.
     * @param id The unique identifier for the report card.
     * @param studentId The ID of the student this report card belongs to.
     * @param initialGrades An initial map of grades (Subject ID -> Score). Can be null.
     */
    public ReportCard(String id, String studentId, Map<String, Integer> initialGrades) {
        this.id = id;
        this.studentId = studentId;
        this.grades = (initialGrades != null) ? new HashMap<>(initialGrades) : new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the grade for a specific subject.
     * @param subjectId The ID of the subject.
     * @return The grade score, or null if the subject is not found.
     */
    public Integer getGrade(String subjectId) {
        return grades.get(subjectId);
    }

    /**
     * Updates the grade for a specific subject.
     * @param subjectId The ID of the subject to update.
     * @param newScore The new score for the subject.
     */
    public void updateGrade(String subjectId, int newScore) {
        if (grades.containsKey(subjectId)) {
            grades.put(subjectId, newScore);
            System.out.println("DEBUG: ReportCard " + id + ": Grade for " + subjectId + " updated to " + newScore);
        } else {
            System.err.println("WARNING: Subject " + subjectId + " not found in report card " + id + ". Cannot update grade.");
        }
    }

    /**
     * Adds a new grade for a subject. If the subject already exists, it updates the grade.
     * @param subjectId The ID of the subject.
     * @param score The score for the subject.
     */
    public void addGrade(String subjectId, int score) {
        grades.put(subjectId, score);
        System.out.println("DEBUG: ReportCard " + id + ": Grade for " + subjectId + " added/updated to " + score);
    }

    /**
     * Returns an unmodifiable map of all grades.
     * @return A map where keys are Subject IDs and values are integer grades.
     */
    public Map<String, Integer> getGrades() {
        return Collections.unmodifiableMap(grades);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportCard that = (ReportCard) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ReportCard{" +
               "id='" + id + '\'' +
               ", studentId='" + studentId + '\'' +
               ", grades=" + grades +
               '}';
    }
}