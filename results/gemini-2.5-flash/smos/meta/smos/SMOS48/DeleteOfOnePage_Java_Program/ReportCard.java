/**
 * Represents a student's report card.
 * Each report card is uniquely identified by a report card ID and contains
 * information about the student, course, and grade.
 */
public class ReportCard {
    private String reportCardId; // Unique identifier for the report card
    private String studentId;    // Identifier for the student
    private String studentName;  // Name of the student
    private String courseName;   // Name of the course
    private double grade;        // Grade obtained in the course

    /**
     * Constructs a new ReportCard instance.
     *
     * @param reportCardId The unique ID of the report card.
     * @param studentId The ID of the student.
     * @param studentName The name of the student.
     * @param courseName The name of the course.
     * @param grade The grade obtained by the student in the course.
     */
    public ReportCard(String reportCardId, String studentId, String studentName, String courseName, double grade) {
        this.reportCardId = reportCardId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.grade = grade;
    }

    /**
     * Returns the unique ID of the report card.
     *
     * @return The report card ID.
     */
    public String getReportCardId() {
        return reportCardId;
    }

    /**
     * Returns the ID of the student.
     *
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the name of the student.
     *
     * @return The student name.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Returns the name of the course.
     *
     * @return The course name.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Returns the grade obtained in the course.
     *
     * @return The grade.
     */
    public double getGrade() {
        return grade;
    }

    /**
     * Sets the grade for the report card.
     *
     * @param grade The new grade to set.
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * Provides a string representation of the ReportCard object.
     *
     * @return A formatted string containing the report card details.
     */
    @Override
    public String toString() {
        return "ReportCard ID: " + reportCardId +
               ", Student ID: " + studentId +
               ", Student Name: " + studentName +
               ", Course: " + courseName +
               ", Grade: " + String.format("%.2f", grade);
    }
}