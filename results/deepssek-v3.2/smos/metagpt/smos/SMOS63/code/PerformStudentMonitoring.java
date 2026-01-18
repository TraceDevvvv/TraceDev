import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Student with name, number of notes, and number of absences.
 */
class Student {
    private String name;
    private int notesCount;
    private int absencesCount;

    /**
     * Constructor to create a Student object.
     *
     * @param name          Student's name
     * @param notesCount    Number of notes for the student
     * @param absencesCount Number of absences for the student
     * @throws IllegalArgumentException if name is null or empty, or counts are negative
     */
    public Student(String name, int notesCount, int absencesCount) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        if (notesCount < 0) {
            throw new IllegalArgumentException("Notes count cannot be negative.");
        }
        if (absencesCount < 0) {
            throw new IllegalArgumentException("Absences count cannot be negative.");
        }
        this.name = name.trim();
        this.notesCount = notesCount;
        this.absencesCount = absencesCount;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getNotesCount() {
        return notesCount;
    }

    public int getAbsencesCount() {
        return absencesCount;
    }

    /**
     * Returns a string representation of the student.
     *
     * @return formatted string with student details
     */
    @Override
    public String toString() {
        return String.format("Student{name='%s', notesCount=%d, absencesCount=%d}",
                name, notesCount, absencesCount);
    }
}

/**
 * Main class for the Student Monitoring system.
 * This program simulates the "Student Monitoring" functionality where the direction
 * can view students with notes and absences above a specified threshold.
 */
public class PerformStudentMonitoring {
    // Simulated database of students. In a real application, this would come from a database.
    private static final List<Student> ALL_STUDENTS = initializeStudents();

    /**
     * Initializes a sample list of students for demonstration.
     *
     * @return List of Student objects
     */
    private static List<Student> initializeStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice Johnson", 5, 3));
        students.add(new Student("Bob Smith", 2, 1));
        students.add(new Student("Charlie Brown", 8, 6));
        students.add(new Student("Diana Prince", 4, 7));
        students.add(new Student("Edward Norton", 1, 0));
        students.add(new Student("Fiona Green", 9, 4));
        students.add(new Student("George Lee", 3, 2));
        students.add(new Student("Hannah Davis", 6, 8));
        return students;
    }

    /**
     * Filters students whose notes count AND absences count are both greater than the threshold.
     * Both conditions must be satisfied (logical AND).
     *
     * @param students  List of all students
     * @param threshold The minimum value (exclusive) for both notes and absences
     * @return List of students meeting the criteria
     * @throws IllegalArgumentException if students list is null
     */
    public static List<Student> filterStudents(List<Student> students, int threshold) {
        if (students == null) {
            throw new IllegalArgumentException("Students list cannot be null.");
        }

        List<Student> filtered = new ArrayList<>();
        for (Student student : students) {
            // Check if both notes and absences are strictly greater than threshold
            if (student.getNotesCount() > threshold && student.getAbsencesCount() > threshold) {
                filtered.add(student);
            }
        }
        return filtered;
    }

    /**
     * Displays the list of students in a formatted manner.
     *
     * @param students List of students to display
     */
    public static void displayStudents(List<Student> students) {
        if (students == null) {
            System.out.println("Error: Cannot display null student list.");
            return;
        }

        if (students.isEmpty()) {
            System.out.println("No students found matching the criteria.");
            return;
        }

        System.out.println("\n=== Students with notes and absences above threshold ===");
        System.out.printf("%-20s %-12s %-12s%n", "Name", "Notes", "Absences");
        System.out.println("--------------------------------------------------------");
        for (Student student : students) {
            System.out.printf("%-20s %-12d %-12d%n",
                    student.getName(),
                    student.getNotesCount(),
                    student.getAbsencesCount());
        }
        System.out.println("--------------------------------------------------------");
        System.out.println("Total students: " + students.size());
    }

    /**
     * Main method - entry point of the program.
     * Simulates the "Student Monitoring" button click by the direction.
     * Steps:
     * 1. Get threshold input from user
     * 2. Filter students based on threshold
     * 3. Display filtered students
     *
     * Edge cases handled:
     * - Invalid input (non-integer)
     * - Negative threshold (allowed, but will filter appropriately)
     * - Empty/null student lists
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("========================================================");
        System.out.println("        STUDENT MONITORING SYSTEM - DIRECTION");
        System.out.println("========================================================");
        System.out.println("Precondition: User logged in as direction.");
        System.out.println("Action: User clicked 'Student Monitoring' button.");
        System.out.println("\n");

        Scanner scanner = new Scanner(System.in);
        int threshold = 0;
        boolean validInput = false;

        // Get threshold from user with input validation
        while (!validInput) {
            try {
                System.out.print("Enter the threshold for notes and absences (integer): ");
                String input = scanner.nextLine().trim();
                threshold = Integer.parseInt(input);
                validInput = true;
                System.out.println("Threshold set to: " + threshold);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            }
        }

        System.out.println("\nProcessing: Looking for students with notes > " + threshold +
                " AND absences > " + threshold + "...");

        try {
            // Step 1: Filter students based on threshold
            List<Student> filteredStudents = filterStudents(ALL_STUDENTS, threshold);

            // Step 2: Display the recovered information
            displayStudents(filteredStudents);

            // Postcondition: Information collected is displayed
            System.out.println("\nPostcondition: Information collected has been displayed.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error during filtering: " + e.getMessage());
        } finally {
            scanner.close();
        }

        System.out.println("\n========================================================");
        System.out.println("                PROCESS COMPLETED");
        System.out.println("========================================================");
    }
}