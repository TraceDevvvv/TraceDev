/*
subjectgrade.java
Represents a single subject and the corresponding grade received by a student.
*/
package model;
/**
 * Represents a subject and the grade received by a student for that subject.
 */
public class SubjectGrade {
    private String subjectName; // The name of the subject
    private double grade;       // The grade obtained in the subject
    /**
     * Constructs a new SubjectGrade object.
     * @param subjectName The name of the subject.
     * @param grade The grade obtained in the subject.
     */
    public SubjectGrade(String subjectName, double grade) {
        this.subjectName = subjectName;
        this.grade = grade;
    }
    /**
     * Gets the name of the subject.
     * @return The subject name.
     */
    public String getSubjectName() {
        return subjectName;
    }
    /**
     * Sets the name of the subject.
     * @param subjectName The new subject name.
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    /**
     * Gets the grade for the subject.
     * @return The grade.
     */
    public double getGrade() {
        return grade;
    }
    /**
     * Sets the grade for the subject.
     * @param grade The new grade.
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }
    /**
     * Returns a string representation of the SubjectGrade object.
     * @return A string in the format "SubjectName: Grade".
     */
    @Override
    public String toString() {
        return subjectName + ": " + grade;
    }
}