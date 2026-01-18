/**
 * StudentMonitoringService.java
 * This class handles the business logic for monitoring students based on 
 * absences and disciplinary notes thresholds. It simulates database operations
 * and provides methods to search for students who exceed predetermined thresholds.
 * Implements the functionality described in the DoStudentsMonitoring use case.
 */
import java.util.ArrayList;
import java.util.List;

public class StudentMonitoringService {
    
    // Default threshold values for absences and notes
    private static final int DEFAULT_ABSENCES_THRESHOLD = 5;
    private static final int DEFAULT_NOTES_THRESHOLD = 3;
    
    // Current school year for filtering
    private String currentSchoolYear;
    
    /**
     * Constructor that initializes with the current school year.
     * 
     * @param currentSchoolYear The current school year (e.g., "2023-2024")
     */
    public StudentMonitoringService(String currentSchoolYear) {
        this.currentSchoolYear = currentSchoolYear;
    }
    
    /**
     * Default constructor that uses a default current school year.
     * The default is "2023-2024" but can be customized based on system date.
     */
    public StudentMonitoringService() {
        this.currentSchoolYear = "2023-2024"; // Default school year
    }
    
    /**
     * Gets the current school year being used for filtering.
     * 
     * @return current school year
     */
    public String getCurrentSchoolYear() {
        return currentSchoolYear;
    }
    
    /**
     * Sets the current school year for filtering operations.
     * 
     * @param currentSchoolYear the school year to set
     */
    public void setCurrentSchoolYear(String currentSchoolYear) {
        this.currentSchoolYear = currentSchoolYear;
    }
    
    /**
     * Simulates searching in the archive/database for students who exceed
     * the predetermined thresholds for absences and notes in the current school year.
     * This method mimics database query operations and handles edge cases.
     * 
     * @param absencesThreshold Minimum absences count threshold (must be >= 0)
     * @param notesThreshold Minimum notes count threshold (must be >= 0)
     * @return List of students who exceed both thresholds in the current school year
     * @throws IllegalArgumentException if thresholds are negative
     */
    public List<Student> searchStudentsExceedingThresholds(int absencesThreshold, int notesThreshold) {
        // Validate threshold values
        if (absencesThreshold < 0 || notesThreshold < 0) {
            throw new IllegalArgumentException("Threshold values cannot be negative");
        }
        
        // Simulate retrieving all students from the database/archive
        List<Student> allStudents = simulateDatabaseRetrieval();
        
        // Filter students based on thresholds and current school year
        List<Student> exceedingStudents = new ArrayList<>();
        
        for (Student student : allStudents) {
            // Check if student is in the current school year
            if (student.getSchoolYear().equals(currentSchoolYear)) {
                // Check if student exceeds both thresholds
                if (student.exceedsThresholds(absencesThreshold, notesThreshold)) {
                    exceedingStudents.add(student);
                }
            }
        }
        
        return exceedingStudents;
    }
    
    /**
     * Searches for students exceeding thresholds using default values.
     * This is a convenience method that uses the default threshold values.
     * 
     * @return List of students who exceed default thresholds
     */
    public List<Student> searchStudentsExceedingDefaultThresholds() {
        return searchStudentsExceedingThresholds(DEFAULT_ABSENCES_THRESHOLD, DEFAULT_NOTES_THRESHOLD);
    }
    
    /**
     * Counts the number of students exceeding thresholds.
     * This is useful for reporting and analytics.
     * 
     * @param absencesThreshold Minimum absences threshold
     * @param notesThreshold Minimum notes threshold
     * @return Count of students exceeding thresholds
     */
    public int countStudentsExceedingThresholds(int absencesThreshold, int notesThreshold) {
        return searchStudentsExceedingThresholds(absencesThreshold, notesThreshold).size();
    }
    
    /**
     * Simulates retrieving student data from a database or archive.
     * In a real system, this would connect to a database, but here we simulate it
     * with predefined test data. This method handles the "Connection to the 
     * interrupted SMOS server" mentioned in postconditions by simulating
     * potential connection issues and recovery.
     * 
     * @return List of simulated student records
     */
    private List<Student> simulateDatabaseRetrieval() {
        List<Student> students = new ArrayList<>();
        
        try {
            // Simulate potential SMOS server connection issues
            // In a real implementation, this would handle database exceptions
            simulateSMOSConnection();
            
            // Create simulated student data for the current school year
            // These represent students in the archive for testing
            
            // Students with high absences and notes (should be flagged)
            students.add(new Student("S001", "John Smith", 8, 4, currentSchoolYear));
            students.add(new Student("S002", "Jane Doe", 10, 5, currentSchoolYear));
            students.add(new Student("S003", "Robert Johnson", 6, 4, currentSchoolYear));
            
            // Students with only high absences or only high notes (should NOT be flagged)
            students.add(new Student("S004", "Emily Davis", 8, 2, currentSchoolYear));  // Only high absences
            students.add(new Student("S005", "Michael Brown", 3, 5, currentSchoolYear)); // Only high notes
            
            // Students below thresholds (should NOT be flagged)
            students.add(new Student("S006", "Sarah Wilson", 2, 1, currentSchoolYear));
            students.add(new Student("S007", "David Miller", 4, 2, currentSchoolYear));
            
            // Students from previous school years (should NOT be flagged)
            students.add(new Student("S008", "Lisa Taylor", 9, 6, "2022-2023")); // Previous year
            students.add(new Student("S009", "James Anderson", 7, 5, "2021-2022")); // Older year
            
            // Students with zero values
            students.add(new Student("S010", "Olivia Martinez", 0, 0, currentSchoolYear));
            
            // Simulate edge cases
            students.add(new Student("S011", "Thomas White", 100, 50, currentSchoolYear)); // Very high values
            students.add(new Student("", "Anonymous", 5, 3, currentSchoolYear)); // Empty ID
            
        } catch (SimulatedConnectionException e) {
            // Handle simulated SMOS server connection interruption
            // In a real system, this would log the error and attempt recovery
            System.err.println("Warning: Simulated SMOS server connection issue - " + e.getMessage());
            System.err.println("Using cached data or default values...");
            
            // Return minimal dataset when connection fails
            if (students.isEmpty()) {
                students.add(new Student("S999", "Default Student (Connection Failed)", 0, 0, currentSchoolYear));
            }
        }
        
        return students;
    }
    
    /**
     * Simulates SMOS server connection with potential interruptions.
     * This simulates the "Connection to the interrupted SMOS server" mentioned
     * in postconditions by occasionally throwing a simulated exception.
     * 
     * @throws SimulatedConnectionException if simulated connection fails
     */
    private void simulateSMOSConnection() throws SimulatedConnectionException {
        // Simulate occasional connection failures (10% chance)
        if (Math.random() < 0.1) {
            throw new SimulatedConnectionException("SMOS server connection interrupted");
        }
        // In a real system, this would contain actual connection logic
    }
    
    /**
     * Generates a summary report of students exceeding thresholds.
     * This provides formatted output for display purposes.
     * 
     * @param absencesThreshold Threshold for absences
     * @param notesThreshold Threshold for notes
     * @return Formatted report string
     */
    public String generateThresholdReport(int absencesThreshold, int notesThreshold) {
        List<Student> exceedingStudents = searchStudentsExceedingThresholds(absencesThreshold, notesThreshold);
        
        StringBuilder report = new StringBuilder();
        report.append("STUDENT MONITORING REPORT\n");
        report.append("==========================\n");
        report.append("School Year: ").append(currentSchoolYear).append("\n");
        report.append("Absences Threshold: ").append(absencesThreshold).append("\n");
        report.append("Notes Threshold: ").append(notesThreshold).append("\n");
        report.append("Total Students Exceeding Thresholds: ").append(exceedingStudents.size()).append("\n");
        report.append("\n");
        
        if (exceedingStudents.isEmpty()) {
            report.append("No students exceed the specified thresholds.\n");
        } else {
            report.append("Students Exceeding Thresholds:\n");
            report.append("-----------------------------\n");
            
            for (int i = 0; i < exceedingStudents.size(); i++) {
                Student student = exceedingStudents.get(i);
                report.append(String.format("%d. ID: %s, Name: %s, Absences: %d, Notes: %d, Total Score: %d\n",
                    i + 1,
                    student.getStudentId(),
                    student.getFullName(),
                    student.getAbsencesCount(),
                    student.getNotesCount(),
                    student.getTotalMonitoringScore()));
            }
        }
        
        return report.toString();
    }
    
    /**
     * Calculates statistics for monitoring data.
     * Provides additional analytics for reporting.
     * 
     * @return Statistics summary string
     */
    public String calculateStatistics() {
        List<Student> allStudentsInCurrentYear = new ArrayList<>();
        List<Student> allStudents = simulateDatabaseRetrieval();
        
        // Filter for current school year only
        for (Student student : allStudents) {
            if (student.getSchoolYear().equals(currentSchoolYear)) {
                allStudentsInCurrentYear.add(student);
            }
        }
        
        if (allStudentsInCurrentYear.isEmpty()) {
            return "No student data available for the current school year.";
        }
        
        int totalAbsences = 0;
        int totalNotes = 0;
        int maxAbsences = 0;
        int maxNotes = 0;
        
        for (Student student : allStudentsInCurrentYear) {
            totalAbsences += student.getAbsencesCount();
            totalNotes += student.getNotesCount();
            maxAbsences = Math.max(maxAbsences, student.getAbsencesCount());
            maxNotes = Math.max(maxNotes, student.getNotesCount());
        }
        
        double avgAbsences = (double) totalAbsences / allStudentsInCurrentYear.size();
        double avgNotes = (double) totalNotes / allStudentsInCurrentYear.size();
        
        return String.format(
            "Statistics for School Year %s:\n" +
            "  Total Students: %d\n" +
            "  Average Absences: %.2f\n" +
            "  Average Notes: %.2f\n" +
            "  Maximum Absences: %d\n" +
            "  Maximum Notes: %d\n" +
            "  Total Absences: %d\n" +
            "  Total Notes: %d",
            currentSchoolYear,
            allStudentsInCurrentYear.size(),
            avgAbsences,
            avgNotes,
            maxAbsences,
            maxNotes,
            totalAbsences,
            totalNotes
        );
    }
}

/**
 * Custom exception to simulate SMOS server connection issues.
 * This represents the "Connection to the interrupted SMOS server" mentioned
 * in postconditions.
 */
class SimulatedConnectionException extends Exception {
    public SimulatedConnectionException(String message) {
        super(message);
    }
}