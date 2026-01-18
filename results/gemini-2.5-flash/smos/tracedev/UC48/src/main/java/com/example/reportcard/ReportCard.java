package com.example.reportcard;

/**
 * Represents a Report Card in the system.
 * Includes attributes like ID, student ID, course name, grade, and a deletion status.
 */
public class ReportCard {
    private String id;
    private String studentId;
    private String courseName;
    private String grade;
    private boolean isDeleted; // Logical deletion flag

    /**
     * Constructs a new ReportCard.
     *
     * @param id The unique identifier for the report card.
     * @param studentId The ID of the student associated with this report card.
     * @param courseName The name of the course.
     * @param grade The grade received in the course.
     */
    public ReportCard(String id, String studentId, String courseName, String grade) {
        this.id = id;
        this.studentId = studentId;
        this.courseName = courseName;
        this.grade = grade;
        this.isDeleted = false; // Initially not deleted
    }

    // Getters for attributes

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getGrade() {
        return grade;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Marks this report card as logically deleted.
     * This method fulfills the sequence diagram step "reportCard.markAsDeleted()".
     */
    public void markAsDeleted() {
        this.isDeleted = true;
        System.out.println("[ReportCard] Report Card '" + id + "' marked as logically deleted.");
    }

    @Override
    public String toString() {
        return "ReportCard{" +
               "id='" + id + '\'' +
               ", studentId='" + studentId + '\'' +
               ", courseName='" + courseName + '\'' +
               ", grade='" + grade + '\'' +
               ", isDeleted=" + isDeleted +
               '}';
    }
}