'''
Provides simulated data access and business logic for student records.
It manages a list of students and offers a method to filter them based on
specified thresholds for notes and absences.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class StudentService {
    private List<Student> students;
    /**
     * Constructs a StudentService and initializes a dummy list of student data.
     * In a real application, this data would typically come from a database.
     */
    public StudentService() {
        this.students = new ArrayList<>();
        // Populate with some dummy data for demonstration
        students.add(new Student("Alice Smith", 2, 3));
        students.add(new Student("Bob Johnson", 5, 7));
        students.add(new Student("Charlie Brown", 1, 1));
        students.add(new Student("Diana Miller", 8, 10));
        students.add(new Student("Eve Davis", 3, 4));
        students.add(new Student("Frank White", 6, 8));
        students.add(new Student("Grace Green", 0, 0));
        students.add(new Student("Henry Black", 4, 2));
    }
    /**
     * Retrieves a list of students whose number of notes and absences
     * are superior to (greater than) the given thresholds.
     *
     * @param notesThreshold The minimum number of notes a student must have to be included.
     * @param absencesThreshold The minimum number of absences a student must have to be included.
     * @return A list of Student objects matching the criteria.
     */
    public List<Student> getStudentsExceedingThresholds(int notesThreshold, int absencesThreshold) {
        if (notesThreshold < 0 || absencesThreshold < 0) {
            // In a real system, more sophisticated error handling or validation might be needed.
            // For this use case, we assume valid non-negative thresholds are passed.
            System.err.println("Warning: Thresholds cannot be negative. Please enter non-negative values for accurate results.");
            // Returning an empty list if thresholds are negative, as per the current error handling in the App class.
            return new ArrayList<>(); 
        }
        // Use Java Streams to efficiently filter the list of students.
        // It filters based on both notesCount > notesThreshold AND absencesCount > absencesThreshold.
        return students.stream()
                .filter(student -> student.getNotesCount() > notesThreshold &&
                                   student.getAbsencesCount() > absencesThreshold)
                .collect(Collectors.toList()); // Collect the filtered students into a new list.
    }
}