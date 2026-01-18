/**
 * Manages student registration data and business logic.
 * This class simulates interaction with a database or backend system.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
public class RegistrationService {
    // A list to simulate a database or data store for student records.
    private List<Student> students;
    /**
     * Constructor for RegistrationService.
     * Initializes with some dummy data for demonstration purposes.
     */
    public RegistrationService() {
        this.students = new ArrayList<>();
        // Add some initial pending student registrations
        students.add(new Student("S001", "Alice Smith", Student.Status.PENDING));
        students.add(new Student("S002", "Bob Johnson", Student.Status.PENDING));
        students.add(new Student("S003", "Charlie Brown", Student.Status.PENDING));
        // Add an already active student to show it's not listed as pending
        students.add(new Student("S004", "Diana Prince", Student.Status.ACTIVE));
    }
    /**
     * Retrieves a list of all student registrations that are currently pending.
     *
     * @return A list of Student objects with PENDING status.
     */
    public List<Student> getPendingRegistrations() {
        // Use Java Streams to filter students by their PENDING status
        return students.stream()
                       .filter(s -> s.getStatus() == Student.Status.PENDING)
                       .collect(Collectors.toList());
    }
    /**
     * Activates a student's system enrollment based on their ID.
     *
     * @param studentId The unique ID of the student to activate.
     * @throws IllegalArgumentException If no student with the given ID is found
     *                                  or if the student is already active.
     */
    public void activateStudent(String studentId) {
        // Find the student by ID. Optional is used for safe handling of null results.
        Optional<Student> studentOptional = students.stream()
                                                    .filter(s -> s.getId().equals(studentId))
                                                    .findFirst();
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            // Check if the student is already active
            if (student.getStatus() == Student.Status.ACTIVE) {
                throw new IllegalArgumentException("Student " + student.getName() + " (ID: " + studentId + ") is already active.");
            }
            // Set the student's status to ACTIVE
            student.setStatus(Student.Status.ACTIVE);
            System.out.println("Student " + student.getName() + " (ID: " + studentId + ") activated successfully.");
        } else {
            // Throw an exception if no student is found with the given ID
            // IMPORTANT REVISION: Changed error message for clarity, as per comment.
            // If studentOptional is empty, it means no such student ID exists at all,
            // not just no pending registration with that ID.
            throw new IllegalArgumentException("No student found with ID: " + studentId);
        }
    }
}