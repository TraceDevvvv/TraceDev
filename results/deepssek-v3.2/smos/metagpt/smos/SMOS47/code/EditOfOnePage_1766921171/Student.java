import java.util.Objects;

/**
 * Student class representing a student with ID, name, and report card
 * This class stores student information and their academic report card
 */
public class Student {
    private String studentId;
    private String name;
    private ReportCard reportCard;
    
    /**
     * Default constructor for Student class
     * Initializes an empty student with a default report card
     */
    public Student() {
        this.studentId = "";
        this.name = "";
        this.reportCard = new ReportCard();
    }
    
    /**
     * Parameterized constructor for Student class
     * 
     * @param studentId Unique identifier for the student
     * @param name Full name of the student
     * @param reportCard ReportCard object containing student's grades
     */
    public Student(String studentId, String name, ReportCard reportCard) {
        this.studentId = studentId;
        this.name = name;
        this.reportCard = reportCard;
    }
    
    /**
     * Constructor for Student with basic information
     * Creates a new empty report card for the student
     * 
     * @param studentId Unique identifier for the student
     * @param name Full name of the student
     */
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.reportCard = new ReportCard();
    }
    
    /**
     * Get the student's unique ID
     * 
     * @return Student ID as a string
     */
    public String getStudentId() {
        return studentId;
    }
    
    /**
     * Set the student's unique ID
     * 
     * @param studentId New student ID to set
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    /**
     * Get the student's full name
     * 
     * @return Student's name as a string
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set the student's full name
     * 
     * @param name New name to set for the student
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the student's report card
     * 
     * @return ReportCard object containing student's grades
     */
    public ReportCard getReportCard() {
        return reportCard;
    }
    
    /**
     * Set the student's report card
     * 
     * @param reportCard New ReportCard object to assign to the student
     */
    public void setReportCard(ReportCard reportCard) {
        this.reportCard = reportCard;
    }
    
    /**
     * String representation of the Student object
     * Shows student ID, name, and a summary of their report card
     * 
     * @return Formatted string with student information
     */
    @Override
    public String toString() {
        return String.format("Student ID: %s, Name: %s, %s", 
                           studentId, name, reportCard.toString());
    }
    
    /**
     * Checks if this student is equal to another object
     * Two students are equal if they have the same student ID
     * 
     * @param obj Object to compare with
     * @return true if students have the same ID, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Objects.equals(studentId, student.studentId);
    }
    
    /**
     * Generates hash code based on student ID
     * 
     * @return Hash code for the student
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}