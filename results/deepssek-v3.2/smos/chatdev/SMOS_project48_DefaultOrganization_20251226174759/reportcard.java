/**
 * Represents a report card for a student.
 * Contains student details and grades.
 */
import java.util.*;
public class ReportCard {
    private int studentId;
    private String studentName;
    private String className;
    private Map<String, Double> grades; // Subject -> Grade
    /**
     * Constructs a ReportCard with specified details.
     * @param studentId Unique identifier for the student.
     * @param studentName Name of the student.
     * @param className Name of the class.
     * @param grades Map of subjects and corresponding grades.
     */
    public ReportCard(int studentId, String studentName, String className, Map<String, Double> grades) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.grades = new HashMap<>(grades);
    }
    // Getters
    public int getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public String getClassName() { return className; }
    public Map<String, Double> getGrades() { return new HashMap<>(grades); }
    /**
     * Returns a string representation of the report card.
     * @return Formatted string with student and grade details.
     */
    @Override
    public String toString() {
        return String.format("ReportCard{studentId=%d, studentName='%s', className='%s', grades=%s}", 
                            studentId, studentName, className, grades);
    }
}