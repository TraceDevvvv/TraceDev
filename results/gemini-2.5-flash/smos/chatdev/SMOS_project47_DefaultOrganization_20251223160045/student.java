/*
student.java
Represents a student with an ID, name, and their associated ReportCard.
*/
package model;
/**
 * Represents a student in the system, identified by an ID and name,
 * and associated with a report card.
 */
public class Student {
    private String studentId;     // Unique identifier for the student
    private String name;          // Full name of the student
    private ReportCard reportCard; // The student's report card
    /**
     * Constructs a new Student object.
     * @param studentId A unique identifier for the student.
     * @param name The full name of the student.
     */
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.reportCard = new ReportCard(); // Initialize with an empty report card
    }
    /**
     * Constructs a new Student object with an existing report card.
     * @param studentId A unique identifier for the student.
     * @param name The full name of the student.
     * @param reportCard The student's report card.
     */
    public Student(String studentId, String name, ReportCard reportCard) {
        this.studentId = studentId;
        this.name = name;
        this.reportCard = reportCard;
    }
    /**
     * Gets the student's ID.
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }
    /**
     * Sets the student's ID.
     * @param studentId The new student ID.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    /**
     * Gets the student's name.
     * @return The student's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the student's name.
     * @param name The new student's name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the student's report card.
     * @return The ReportCard object for this student.
     */
    public ReportCard getReportCard() {
        return reportCard;
    }
    /**
     * Sets the student's report card.
     * @param reportCard The new ReportCard object for this student.
     */
    public void setReportCard(ReportCard reportCard) {
        this.reportCard = reportCard;
    }
    /**
     * Returns a string representation of the Student object.
     * @return A string containing the student's ID and name.
     */
    @Override
    public String toString() {
        return "ID: " + studentId + ", Name: " + name;
    }
}