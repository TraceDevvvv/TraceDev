import java.util.ArrayList;
import java.util.List;
/**
 * Simulates a database of student records
 * In a real application, this would connect to an actual database
 */
public class StudentDatabase {
    private List<Student> students;
    /**
     * Constructor initializes with sample student data
     */
    public StudentDatabase() {
        students = new ArrayList<>();
        initializeSampleData();
    }
    /**
     * Initialize with sample student records for demonstration
     */
    private void initializeSampleData() {
        // Add sample students with varying absences and grades
        students.add(new Student("S001", "John Smith", 5, 85.5));
        students.add(new Student("S002", "Emma Johnson", 12, 92.0));  // High absences, good grade
        students.add(new Student("S003", "Michael Brown", 3, 75.0));  // Low grade
        students.add(new Student("S004", "Sarah Davis", 8, 96.5));    // Good grade
        students.add(new Student("S005", "Robert Wilson", 15, 65.0)); // High absences, low grade
        students.add(new Student("S006", "Lisa Miller", 2, 88.0));
        students.add(new Student("S007", "David Taylor", 10, 98.0));  // High absences, excellent grade
        students.add(new Student("S008", "Jennifer Lee", 4, 72.5));   // Low grade
        students.add(new Student("S009", "Thomas Moore", 20, 55.0));  // Very high absences, very low grade
        students.add(new Student("S010", "Amanda White", 1, 94.5));   // Excellent grade
    }
    /**
     * Search for students exceeding monitoring thresholds
     * @param absenceThreshold Students who have absences above this value are flagged
     * @param gradeThreshold   Students who have average grade below this value are flagged
     * @return List of students exceeding absence threshold OR falling below grade threshold
     */
    public List<Student> searchStudentsAboveThreshold(int absenceThreshold, double gradeThreshold) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.exceedsThreshold(absenceThreshold, gradeThreshold)) {
                result.add(student);
            }
        }
        return result;
    }
    /**
     * Get all students in the database
     * @return List of all students
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    /**
     * Simulate connection to SMOS server
     * @return true if connection successful, false if interrupted
     */
    public boolean connectToSMOSServer() {
        // Simulate server connection
        // In a real implementation, this would establish actual network connection
        System.out.println("Connecting to SMOS server...");
        // Simulate occasional interruption
        double random = Math.random();
        if (random < 0.2) { // 20% chance of interruption
            System.out.println("Connection to SMOS server interrupted!");
            return false;
        }
        System.out.println("Connected to SMOS server successfully.");
        return true;
    }
}