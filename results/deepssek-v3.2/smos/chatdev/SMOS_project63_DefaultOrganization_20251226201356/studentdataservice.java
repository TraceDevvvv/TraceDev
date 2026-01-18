/**
 * Simulates data service for student monitoring.
 * In a real system, this would connect to a database or external SMOS server.
 * 
 * Implements:
 * - Dummy data generation for demonstration
 * - Threshold-based filtering of students
 * - Simulated server failure for testing error handling
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
class StudentDataService {
    private List<Student> dummyStudents;
    private Random random = new Random(123); // Fixed seed for reproducibility
    private boolean simulateServerFailure = false; // Set to true to test server error
    public StudentDataService() {
        generateDummyData();
    }
    /**
     * Generates dummy student data for demonstration.
     * Creates 10 students with random notes (0-14) and absences (0-9).
     */
    private void generateDummyData() {
        dummyStudents = new ArrayList<>();
        String[] names = {"Alice Johnson", "Bob Smith", "Charlie Brown", "Diana Prince", "Ethan Hunt",
                          "Fiona Gallagher", "George Lee", "Hannah Davis", "Ian Chen", "Julia Martinez"};
        for (int i = 1; i <= 10; i++) {
            int notes = random.nextInt(15); // 0-14 notes
            int absences = random.nextInt(10); // 0-9 absences
            dummyStudents.add(new Student(i, names[i-1], notes, absences));
        }
    }
    /**
     * Returns students whose notes and absences exceed given thresholds.
     * Simulates server failure if flag is set (for testing error handling).
     * 
     * @param notesThreshold Minimum notes count (inclusive)
     * @param absencesThreshold Minimum absences count (inclusive)
     * @return List of students meeting both criteria
     * @throws RuntimeException if server connection is interrupted
     */
    public List<Student> fetchStudentsAboveThreshold(int notesThreshold, int absencesThreshold) {
        if (simulateServerFailure) {
            throw new RuntimeException("SMOS server connection interrupted");
        }
        List<Student> result = new ArrayList<>();
        for (Student student : dummyStudents) {
            if (student.getNotesCount() >= notesThreshold && student.getAbsencesCount() >= absencesThreshold) {
                result.add(student);
            }
        }
        return result;
    }
    /**
     * Fallback method to return all dummy data (for server error scenario).
     * @return All available dummy student data
     */
    public List<Student> getDummyStudents() {
        return new ArrayList<>(dummyStudents);
    }
}