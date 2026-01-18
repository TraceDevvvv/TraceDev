import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Report class representing a student's report card.
 * Contains report metadata, grades, comments, and subject-specific grades.
 * This class follows the JavaBean pattern with getters and setters.
 */
public class Report {
    
    // Report metadata
    private String reportId;
    private String title;
    private String semester;
    private double overallGrade;
    private String comments;
    private Map<String, Double> subjectGrades;
    private Date generationDate;
    private String studentId;
    
    /**
     * Default constructor.
     * Generates a unique report ID and sets generation date to current time.
     */
    public Report() {
        this.reportId = UUID.randomUUID().toString();
        this.generationDate = new Date();
        this.subjectGrades = new HashMap<>();
    }
    
    /**
     * Parameterized constructor for creating a report with basic information.
     * 
     * @param title The title/name of the report
     * @param semester The academic semester (e.g., "Fall 2023")
     * @param overallGrade The overall grade percentage
     * @param comments General comments about the report
     * @param subjectGrades Map of subject names to grades
     */
    public Report(String title, String semester, double overallGrade, 
                  String comments, Map<String, Double> subjectGrades) {
        this();
        this.title = title;
        this.semester = semester;
        this.overallGrade = overallGrade;
        this.comments = comments;
        if (subjectGrades != null) {
            this.subjectGrades = new HashMap<>(subjectGrades);
        }
    }
    
    /**
     * Full parameterized constructor including student ID.
     * 
     * @param reportId Unique identifier for the report (if null, generates new)
     * @param title The title/name of the report
     * @param semester The academic semester
     * @param overallGrade The overall grade percentage
     * @param comments General comments about the report
     * @param subjectGrades Map of subject names to grades
     * @param generationDate Date when report was generated
     * @param studentId ID of the student this report belongs to
     */
    public Report(String reportId, String title, String semester, double overallGrade,
                  String comments, Map<String, Double> subjectGrades, 
                  Date generationDate, String studentId) {
        this.reportId = (reportId != null) ? reportId : UUID.randomUUID().toString();
        this.title = title;
        this.semester = semester;
        this.overallGrade = overallGrade;
        this.comments = comments;
        this.subjectGrades = (subjectGrades != null) ? new HashMap<>(subjectGrades) : new HashMap<>();
        this.generationDate = (generationDate != null) ? new Date(generationDate.getTime()) : new Date();
        this.studentId = studentId;
    }
    
    // Getters and Setters
    
    /**
     * @return Unique report identifier
     */
    public String getReportId() {
        return reportId;
    }
    
    /**
     * Sets the report ID. If null, generates a new UUID.
     * 
     * @param reportId The report ID to set
     */
    public void setReportId(String reportId) {
        this.reportId = (reportId != null) ? reportId : UUID.randomUUID().toString();
    }
    
    /**
     * @return Report title/name
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @param title Report title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * @return Academic semester
     */
    public String getSemester() {
        return semester;
    }
    
    /**
     * @param semester Semester to set
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    /**
     * @return Overall grade percentage
     */
    public double getOverallGrade() {
        return overallGrade;
    }
    
    /**
     * Sets the overall grade with validation.
     * 
     * @param overallGrade Grade to set (0-100)
     * @throws IllegalArgumentException if grade is outside valid range
     */
    public void setOverallGrade(double overallGrade) {
        if (overallGrade < 0 || overallGrade > 100) {
            throw new IllegalArgumentException("Overall grade must be between 0 and 100");
        }
        this.overallGrade = overallGrade;
    }
    
    /**
     * @return General comments about the report
     */
    public String getComments() {
        return comments;
    }
    
    /**
     * @param comments Comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    /**
     * @return Map of subject names to grades (defensive copy)
     */
    public Map<String, Double> getSubjectGrades() {
        return new HashMap<>(subjectGrades);
    }
    
    /**
     * Sets the subject grades map.
     * 
     * @param subjectGrades Map to set (creates defensive copy)
     */
    public void setSubjectGrades(Map<String, Double> subjectGrades) {
        this.subjectGrades = (subjectGrades != null) ? new HashMap<>(subjectGrades) : new HashMap<>();
    }
    
    /**
     * Adds or updates a subject grade with validation.
     * 
     * @param subjectName Name of the subject
     * @param grade Grade for the subject (0-100)
     * @throws IllegalArgumentException if grade is outside valid range
     */
    public void addSubjectGrade(String subjectName, double grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Subject grade must be between 0 and 100");
        }
        subjectGrades.put(subjectName, grade);
    }
    
    /**
     * Removes a subject grade.
     * 
     * @param subjectName Name of the subject to remove
     * @return The removed grade, or null if subject wasn't found
     */
    public Double removeSubjectGrade(String subjectName) {
        return subjectGrades.remove(subjectName);
    }
    
    /**
     * Gets a specific subject grade.
     * 
     * @param subjectName Name of the subject
     * @return The grade, or null if subject not found
     */
    public Double getSubjectGrade(String subjectName) {
        return subjectGrades.get(subjectName);
    }
    
    /**
     * @return Date when report was generated
     */
    public Date getGenerationDate() {
        return new Date(generationDate.getTime());
    }
    
    /**
     * Sets the generation date.
     * 
     * @param generationDate Date to set (defensive copy)
     */
    public void setGenerationDate(Date generationDate) {
        this.generationDate = (generationDate != null) ? new Date(generationDate.getTime()) : new Date();
    }
    
    /**
     * @return Student ID this report belongs to
     */
    public String getStudentId() {
        return studentId;
    }
    
    /**
     * @param studentId Student ID to set
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    /**
     * Calculates the average of all subject grades.
     * 
     * @return Average of subject grades, or 0 if no subjects
     */
    public double calculateSubjectAverage() {
        if (subjectGrades.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (Double grade : subjectGrades.values()) {
            sum += grade;
        }
        return sum / subjectGrades.size();
    }
    
    /**
     * Gets the highest grade among all subjects.
     * 
     * @return Highest subject grade, or 0 if no subjects
     */
    public double getHighestSubjectGrade() {
        if (subjectGrades.isEmpty()) {
            return 0.0;
        }
        
        double highest = Double.MIN_VALUE;
        for (Double grade : subjectGrades.values()) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }
    
    /**
     * Gets the lowest grade among all subjects.
     * 
     * @return Lowest subject grade, or 0 if no subjects
     */
    public double getLowestSubjectGrade() {
        if (subjectGrades.isEmpty()) {
            return 0.0;
        }
        
        double lowest = Double.MAX_VALUE;
        for (Double grade : subjectGrades.values()) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }
    
    /**
     * Checks if the report has any failing grades (below 60).
     * 
     * @return true if any subject grade is below 60, false otherwise
     */
    public boolean hasFailingGrades() {
        for (Double grade : subjectGrades.values()) {
            if (grade < 60.0) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gets the grade letter based on overall grade.
     * 
     * @return Grade letter (A, B, C, D, F)
     */
    public String getGradeLetter() {
        if (overallGrade >= 90) return "A";
        if (overallGrade >= 80) return "B";
        if (overallGrade >= 70) return "C";
        if (overallGrade >= 60) return "D";
        return "F";
    }
    
    /**
     * Returns a string representation of the report.
     * 
     * @return Formatted string with report details
     */
    @Override
    public String toString() {
        return String.format("Report[ID=%s, Title=%s, Semester=%s, Overall=%.1f, GradeLetter=%s, Subjects=%d]", 
                reportId, title, semester, overallGrade, getGradeLetter(), subjectGrades.size());
    }
    
    /**
     * Creates a deep copy of this report.
     * 
     * @return A new Report object with the same data
     */
    public Report copy() {
        return new Report(
            this.reportId,
            this.title,
            this.semester,
            this.overallGrade,
            this.comments,
            this.subjectGrades,
            this.generationDate,
            this.studentId
        );
    }
    
    /**
     * Validates the report data.
     * 
     * @return true if report is valid, false otherwise
     */
    public boolean isValid() {
        if (title == null || title.trim().isEmpty()) {
            return false;
        }
        if (semester == null || semester.trim().isEmpty()) {
            return false;
        }
        if (overallGrade < 0 || overallGrade > 100) {
            return false;
        }
        for (Double grade : subjectGrades.values()) {
            if (grade < 0 || grade > 100) {
                return false;
            }
        }
        return true;
    }
}