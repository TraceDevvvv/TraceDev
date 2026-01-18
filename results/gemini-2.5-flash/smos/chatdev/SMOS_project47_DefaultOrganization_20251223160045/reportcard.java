/*
reportcard.java
Aggregates a list of SubjectGrade objects for a student, forming their report card.
*/
package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Represents a student's report card, containing a list of SubjectGrade objects.
 */
public class ReportCard {
    private List<SubjectGrade> grades; // List of grades for different subjects
    /**
     * Constructs a new, empty ReportCard.
     */
    public ReportCard() {
        this.grades = new ArrayList<>();
    }
    /**
     * Constructs a ReportCard with an initial list of grades.
     * @param grades A list of SubjectGrade objects.
     */
    public ReportCard(List<SubjectGrade> grades) {
        this.grades = new ArrayList<>(grades); // Create a defensive copy
    }
    /**
     * Adds a SubjectGrade to the report card.
     * If a grade for the same subject already exists, it updates the existing one.
     * @param subjectGrade The SubjectGrade to add or update.
     */
    public void addOrUpdateGrade(SubjectGrade subjectGrade) {
        for (int i = 0; i < grades.size(); i++) {
            if (grades.get(i).getSubjectName().equals(subjectGrade.getSubjectName())) {
                grades.set(i, subjectGrade); // Update existing grade
                return;
            }
        }
        grades.add(subjectGrade); // Add new grade if not found
    }
    /**
     * Retrieves the grade for a specific subject.
     * @param subjectName The name of the subject.
     * @return The SubjectGrade object if found, otherwise null.
     */
    public SubjectGrade getGradeForSubject(String subjectName) {
        for (SubjectGrade sg : grades) {
            if (sg.getSubjectName().equals(subjectName)) {
                return sg;
            }
        }
        return null;
    }
    /**
     * Returns an unmodifiable list of all subject grades in the report card.
     * @return An unmodifiable List of SubjectGrade objects.
     */
    public List<SubjectGrade> getGrades() {
        return Collections.unmodifiableList(grades); // Return an unmodifiable view for encapsulation
    }
    /**
     * Sets the entire list of grades for the report card.
     * @param newGrades The new list of SubjectGrade objects.
     */
    public void setGrades(List<SubjectGrade> newGrades) {
        // Create a defensive copy to prevent external modification of the internal list
        this.grades = new ArrayList<>(newGrades);
    }
    /**
     * Returns a string representation of the ReportCard, listing all subjects and grades.
     * @return A formatted string showing all grades.
     */
    @Override
    public String toString() {
        if (grades.isEmpty()) {
            return "No grades available.";
        }
        StringBuilder sb = new StringBuilder("Report Card:\n");
        for (SubjectGrade sg : grades) {
            sb.append("\t").append(sg.toString()).append("\n");
        }
        return sb.toString();
    }
}