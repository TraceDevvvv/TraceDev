/*
 * Represents a grade obtained in a specific course.
 */
package model;
public class CourseGrade {
    private String courseName;
    private String grade; // e.g., "A", "B+", "C" or percentages as strings
    /**
     * Constructs a new CourseGrade.
     * @param courseName The name of the course.
     * @param grade The grade received in the course.
     */
    public CourseGrade(String courseName, String grade) {
        this.courseName = courseName;
        this.grade = grade;
    }
    /**
     * Returns the name of the course.
     * @return The course name.
     */
    public String getCourseName() {
        return courseName;
    }
    /**
     * Returns the grade received in the course.
     * @return The grade.
     */
    public String getGrade() {
        return grade;
    }
}