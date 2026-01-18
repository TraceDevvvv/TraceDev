import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Represents a student with their ID, name, number of absences, and number of notes.
 */
class Student {
    private final String studentId;
    private final String name;
    private final int absences;
    private final int notes;

    /**
     * Constructs a new Student object.
     *
     * @param studentId The unique identifier for the student.
     * @param name The name of the student.
     * @param absences The total number of absences for the student.
     * @param notes The total number of notes (e.g., disciplinary notes) for the student.
     */
    public Student(String studentId, String name, int absences, int notes) {
        this.studentId = studentId;
        this.name = name;
        this.absences = absences;
        this.notes = notes;
    }

    /**
     * Gets the student's ID.
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the student's name.
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of absences for the student.
     * @return The number of absences.
     */
    public int getAbsences() {
        return absences;
    }

    /**
     * Gets the number of notes for the student.
     * @return The number of notes.
     */
    public int getNotes() {
        return notes;
    }

    /**
     * Returns a string representation of the Student object.
     * @return A formatted string containing student details.
     */
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Absences: %d, Notes: %d",
                studentId, name, absences, notes);
    }
}

/**
 * Simulates a data repository for students. In a real application, this would interact with a database.
 */
class StudentRepository {
    private final List<Student> students;

    /**
     * Initializes the repository with some sample student data.
     */
    public StudentRepository() {
        this.students = new ArrayList<>();
        // Add sample students
        students.add(new Student("S001", "Alice Smith", 5, 2));
        students.add(new Student("S002", "Bob Johnson", 12, 5));
        students.add(new Student("S003", "Charlie Brown", 3, 1));
        students.add(new Student("S004", "Diana Prince", 8, 3));
        students.add(new Student("S005", "Eve Adams", 15, 7));
        students.add(new Student("S006", "Frank White", 10, 4));
        students.add(new Student("S007", "Grace Lee", 2, 0));
    }

    /**
     * Retrieves all students from the repository.
     * @return A list of all students.
     */
    public List<Student> getAllStudents() {
        // Return a new ArrayList to prevent external modification of the internal list
        return new ArrayList<>(students);
    }
}

/**
 * Provides serv for monitoring students based on specified thresholds for absences and notes.
 */
class StudentMonitoringService {
    private final StudentRepository studentRepository;

    /**
     * Constructs a StudentMonitoringService with a given StudentRepository.
     *
     * @param studentRepository The repository to fetch student data from.
     */
    public StudentMonitoringService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Filters students who have a number of absences and notes higher than the specified thresholds.
     *
     * @param absenceThreshold The minimum number of absences a student must have to be included.
     * @param noteThreshold The minimum number of notes a student must have to be included.
     * @return A list of students exceeding both thresholds.
     */
    public List<Student> getStudentsExceedingThresholds(int absenceThreshold, int noteThreshold) {
        // Retrieve all students from the repository
        List<Student> allStudents = studentRepository.getAllStudents();

        // Filter students based on the provided thresholds
        return allStudents.stream()
                .filter(student -> student.getAbsences() > absenceThreshold) // Filter by absences
                .filter(student -> student.getNotes() > noteThreshold)     // Filter by notes
                .collect(Collectors.toList());                             // Collect the filtered students into a new list
    }
}

/**
 * Main application class for the Student Monitoring System.
 * This class simulates the user interaction for the "Direction" actor.
 */
public class StudentMonitoringApp {

    /**
     * The main method to run the student monitoring application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Initialize components
        StudentRepository studentRepository = new StudentRepository();
        StudentMonitoringService monitoringService = new StudentMonitoringService(studentRepository);
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Student Monitoring System ---");

        // Precondition: User is logged in as 'Direction' and clicks "Student Monitoring" button.
        // For this simulation, we assume these preconditions are met.
        System.out.println("Welcome, Direction! Initiating Student Monitoring...");

        int absenceThreshold = -1;
        int noteThreshold = -1;
        boolean validInput = false;

        // Loop to get valid integer input for absence threshold
        while (!validInput) {
            try {
                System.out.print("Enter minimum absence threshold: ");
                absenceThreshold = scanner.nextInt();
                if (absenceThreshold < 0) {
                    System.out.println("Threshold cannot be negative. Please enter a non-negative number.");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number for absence threshold.");
                scanner.next(); // Consume the invalid input
            }
        }

        validInput = false; // Reset for the next input
        // Loop to get valid integer input for note threshold
        while (!validInput) {
            try {
                System.out.print("Enter minimum note threshold: ");
                noteThreshold = scanner.nextInt();
                if (noteThreshold < 0) {
                    System.out.println("Threshold cannot be negative. Please enter a non-negative number.");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number for note threshold.");
                scanner.next(); // Consume the invalid input
            }
        }

        System.out.printf("\nSearching for students with more than %d absences AND more than %d notes...\n",
                absenceThreshold, noteThreshold);

        // 1. Look for students with a number of absences and notes superior to the established threshold.
        List<Student> monitoredStudents = monitoringService.getStudentsExceedingThresholds(absenceThreshold, noteThreshold);

        // 2. Displays the recovered information.
        System.out.println("\n--- Monitored Students ---");
        if (monitoredStudents.isEmpty()) {
            System.out.println("No students found exceeding both thresholds.");
        } else {
            for (Student student : monitoredStudents) {
                System.out.println(student);
            }
        }
        System.out.println("--------------------------");

        // Postcondition: The information collected in the data collected were displayed.
        // This is already handled by the print statements above.

        // Postcondition: Connect to the interrupted SMOS server were displayed.
        // This simulates a system message indicating a connection attempt or status.
        System.out.println("\nAttempting to connect to SMOS server for logging/reporting...");
        System.out.println("SMOS server connection status: [Simulated: Connected successfully / Logged monitoring event].");

        scanner.close(); // Close the scanner to release system resources
        System.out.println("Student Monitoring session ended.");
    }
}