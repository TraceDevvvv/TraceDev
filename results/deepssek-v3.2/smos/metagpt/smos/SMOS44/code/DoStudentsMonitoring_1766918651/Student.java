/**
 * Student.java
 * Represents a student with attributes for monitoring absences and notes.
 * This class models a student entity with identification information and
 * tracking data for absences and disciplinary notes.
 */
public class Student {
    private String studentId;
    private String fullName;
    private int absencesCount;
    private int notesCount;
    private String schoolYear;
    
    /**
     * Default constructor for Student class.
     * Initializes with default values.
     */
    public Student() {
        this.studentId = "";
        this.fullName = "";
        this.absencesCount = 0;
        this.notesCount = 0;
        this.schoolYear = "";
    }
    
    /**
     * Parameterized constructor for Student class.
     * 
     * @param studentId     Unique identifier for the student
     * @param fullName      Full name of the student
     * @param absencesCount Number of absences for the student
     * @param notesCount    Number of disciplinary notes for the student
     * @param schoolYear    Current school year (e.g., "2023-2024")
     */
    public Student(String studentId, String fullName, int absencesCount, int notesCount, String schoolYear) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.absencesCount = absencesCount;
        this.notesCount = notesCount;
        this.schoolYear = schoolYear;
    }
    
    // Getters and setters for all attributes
    
    /**
     * Gets the student's unique identifier.
     * 
     * @return student ID
     */
    public String getStudentId() {
        return studentId;
    }
    
    /**
     * Sets the student's unique identifier.
     * 
     * @param studentId the student ID to set
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    /**
     * Gets the student's full name.
     * 
     * @return student's full name
     */
    public String getFullName() {
        return fullName;
    }
    
    /**
     * Sets the student's full name.
     * 
     * @param fullName the full name to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    /**
     * Gets the count of absences for the student.
     * 
     * @return number of absences
     */
    public int getAbsencesCount() {
        return absencesCount;
    }
    
    /**
     * Sets the count of absences for the student.
     * Validates that absences count is not negative.
     * 
     * @param absencesCount the number of absences to set
     * @throws IllegalArgumentException if absences count is negative
     */
    public void setAbsencesCount(int absencesCount) {
        if (absencesCount < 0) {
            throw new IllegalArgumentException("Absences count cannot be negative");
        }
        this.absencesCount = absencesCount;
    }
    
    /**
     * Gets the count of disciplinary notes for the student.
     * 
     * @return number of notes
     */
    public int getNotesCount() {
        return notesCount;
    }
    
    /**
     * Sets the count of disciplinary notes for the student.
     * Validates that notes count is not negative.
     * 
     * @param notesCount the number of notes to set
     * @throws IllegalArgumentException if notes count is negative
     */
    public void setNotesCount(int notesCount) {
        if (notesCount < 0) {
            throw new IllegalArgumentException("Notes count cannot be negative");
        }
        this.notesCount = notesCount;
    }
    
    /**
     * Gets the student's current school year.
     * 
     * @return school year (e.g., "2023-2024")
     */
    public String getSchoolYear() {
        return schoolYear;
    }
    
    /**
     * Sets the student's current school year.
     * 
     * @param schoolYear the school year to set
     */
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
    
    /**
     * Checks if this student exceeds both thresholds for absences and notes.
     * 
     * @param absencesThreshold the minimum absences count to be considered excessive
     * @param notesThreshold the minimum notes count to be considered excessive
     * @return true if absences > absencesThreshold AND notes > notesThreshold
     */
    public boolean exceedsThresholds(int absencesThreshold, int notesThreshold) {
        return this.absencesCount > absencesThreshold && this.notesCount > notesThreshold;
    }
    
    /**
     * Returns a string representation of the student.
     * 
     * @return formatted string with student details
     */
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", absencesCount=" + absencesCount +
                ", notesCount=" + notesCount +
                ", schoolYear='" + schoolYear + '\'' +
                '}';
    }
    
    /**
     * Calculates the total monitoring score (absences + notes).
     * This can be used for ranking or additional analysis.
     * 
     * @return sum of absences and notes counts
     */
    public int getTotalMonitoringScore() {
        return this.absencesCount + this.notesCount;
    }
}