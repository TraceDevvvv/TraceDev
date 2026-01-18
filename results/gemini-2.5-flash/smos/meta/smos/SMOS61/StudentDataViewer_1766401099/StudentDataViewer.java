```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

/**
 * Represents a Student in the system.
 * Contains basic information about a student.
 */
class Student {
    private final String studentId;
    private final String name;

    /**
     * Constructs a new Student.
     * @param studentId The unique identifier for the student.
     * @param name The full name of the student.
     */
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
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
     * @return The student name.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}

/**
 * Represents a single record of a student's daily activities and status.
 * Includes date, absences, disciplinary notes, delays, and justification.
 */
class StudentRecord {
    private final LocalDate date;
    private final int absences;
    private final String disciplinaryNotes;
    private final int delays;
    private final String justification;

    /**
     * Constructs a new StudentRecord.
     * @param date The date for which this record applies.
     * @param absences The number of absences for the day.
     * @param disciplinaryNotes Any disciplinary notes for the day.
     * @param delays The number of delays for the day.
     * @param justification Any justification provided for absences or delays.
     */
    public StudentRecord(LocalDate date, int absences, String disciplinaryNotes, int delays, String justification) {
        this.date = date;
        this.absences = absences;
        this.disciplinaryNotes = disciplinaryNotes;
        this.delays = delays;
        this.justification = justification;
    }

    /**
     * Gets the date of the record.
     * @return The record date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the number of absences.
     * @return The count of absences.
     */
    public int getAbsences() {
        return absences;
    }

    /**
     * Gets the disciplinary notes.
     * @return The disciplinary notes string.
     */
    public String getDisciplinaryNotes() {
        return disciplinaryNotes;
    }

    /**
     * Gets the number of delays.
     * @return The count of delays.
     */
    public int getDelays() {
        return delays;
    }

    /**
     * Gets the justification for absences or delays.
     * @return The justification string.
     */
    public String getJustification() {
        return justification;
    }

    @Override
    public String toString() {
        return "StudentRecord{" +
               "date=" + date +
               ", absences=" + absences +
               ", disciplinaryNotes='" + disciplinaryNotes + '\'' +
               ", delays=" + delays +
               ", justification='" + justification + '\'' +
               '}';
    }
}

/**
 * Represents a Parent in the system.
 * Contains parent's information and a list of IDs of their children.
 */
class Parent {
    private final String parentId;
    private final String name;
    private final List<String> childrenIds; // List of student IDs associated with this parent

    /**
     * Constructs a new Parent.
     * @param parentId The unique identifier for the parent.
     * @param name The full name of the parent.
     * @param childrenIds A list of student IDs that are children of this parent.
     */
    public Parent(String parentId, String name, List<String> childrenIds) {
        this.parentId = parentId;
        this.name = name;
        this.childrenIds = new ArrayList<>(childrenIds); // Defensive copy
    }

    /**
     * Gets the parent's ID.
     * @return The parent ID.
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Gets the parent's name.
     * @return The parent name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets an unmodifiable list of the IDs of children associated with this parent.
     * @return An unmodifiable list of children's student IDs.
     */
    public List<String> getChildrenIds() {
        return Collections.unmodifiableList(childrenIds);
    }

    @Override
    public String toString() {
        return "Parent{" +
               "parentId='" + parentId + '\'' +
               ", name='" + name + '\'' +
               ", childrenIds=" + childrenIds +
               '}';
    }
}

/**
 * Simulates the SMOS (School Management and Operations System) server.
 * This class provides mock data and methods to retrieve student and parent information.
 * In a real application, this would interact with a database or external service.
 */
class SMOSServerEmulator {
    // Mock data storage
    private final Map<String, Parent> parents;
    private final Map<String, Student> students;
    private final Map<String, List<StudentRecord>> studentRecords; // studentId -> List of records

    /**
     * Initializes the SMOSServerEmulator with dummy data.
     */
    public SMOSServerEmulator() {
        parents = new HashMap<>();
        students = new HashMap<>();
        studentRecords = new HashMap<>();

        // Populate dummy students
        Student student1 = new Student("S001", "Alice Smith");
        Student student2 = new Student("S002", "Bob Johnson");
        Student student3 = new Student("S003", "Charlie Brown");
        Student student4 = new Student("S004", "Diana Prince");

        students.put(student1.getStudentId(), student1);
        students.put(student2.getStudentId(), student2);
        students.put(student3.getStudentId(), student3);
        students.put(student4.getStudentId(), student4);

        // Populate dummy parents
        Parent parent1 = new Parent("P001", "John Smith", Arrays.asList("S001", "S002"));
        Parent parent2 = new Parent("P002", "Jane Doe", Arrays.asList("S003"));
        Parent parent3 = new Parent("P003", "Clark Kent", Arrays.asList("S004"));

        parents.put(parent1.getParentId(), parent1);
        parents.put(parent2.getParentId(), parent2);
        parents.put(parent3.getParentId(), parent3);

        // Populate dummy student records
        studentRecords.put("S001", Arrays.asList(
            new StudentRecord(LocalDate.of(2023, 10, 26), 0, "Excellent behavior.", 0, "N/A"),
            new StudentRecord(LocalDate.of(2023, 10, 27), 1, "Fever, doctor's note submitted.", 0, "Medical reason"),
            new StudentRecord(LocalDate.of(2023, 10, 28), 0, "Participated actively in class.", 1, "Traffic delay")
        ));
        studentRecords.put("S002", Arrays.asList(
            new StudentRecord(LocalDate.of(2023, 10, 26), 0, "Good performance.", 0, "N/A"),
            new StudentRecord(LocalDate.of(2023, 10, 27), 0, "Minor disruption during lunch.", 0, "N/A"),
            new StudentRecord(LocalDate.of(2023, 10, 28), 0, "Completed all assignments.", 0, "N/A")
        ));
        studentRecords.put("S003", Arrays.asList(
            new StudentRecord(LocalDate.of(2023, 10, 26), 0, "Showed great improvement.", 0, "N/A"),
            new StudentRecord(LocalDate.of(2023, 10, 27), 0, "N/A", 2, "Overslept"),
            new StudentRecord(LocalDate.of(2023, 10, 28), 1, "Family emergency.", 0, "Family matter")
        ));
        studentRecords.put("S004", Arrays.asList(
            new StudentRecord(LocalDate.of(2023, 10, 26), 0, "Outstanding leadership.", 0, "N/A")
        ));
    }

    /**
     * Simulates parent authentication.
     * For this use case, it simply checks if the parent ID exists.
     * @param parentId The ID of the parent attempting to log in.
     * @param password The password (ignored in this simulation).
     * @return An Optional containing the Parent object if authentication is successful,
     *         otherwise an empty Optional.
     */
    public Optional<Parent> authenticateParent(String parentId, String password) {
        // In a real system, this would involve password hashing and verification.
        // For this simulation, we just check if the parentId exists.
        if (parents.containsKey(parentId)) {
            System.out.println("SMOS Server: Parent '" + parentId + "' authenticated successfully.");
            return Optional.of(parents.get(parentId));
        }
        System.out.println("SMOS Server: Authentication failed for parent '" + parentId + "'.");
        return Optional.empty();
    }

    /**
     * Retrieves a list of Student objects for a given parent.
     * @param parentId The ID of the parent.
     * @return A list of Student objects associated with the parent. Returns an empty list if parent not found or has no children.
     */
    public List<Student> getParentChildren(String parentId) {
        Parent parent = parents.get(parentId);
        if (parent == null) {
            return Collections.emptyList(); // Parent not found
        }

        List<Student> children = new ArrayList<>();
        for (String studentId : parent.getChildrenIds()) {
            Student child = students.get(studentId);
            if (child != null) {
                children.add(child);
            }
        }
        return children;
    }

    /**
     * Retrieves a specific Student object by their ID.
     * @param studentId The ID of the student.
     * @return An Optional containing the Student object if found, otherwise an empty Optional.
     */
    public Optional<Student> getStudentById(String studentId) {
        return Optional.ofNullable(students.get(studentId));
    }

    /**
     * Retrieves a list of StudentRecord objects for a given student.
     * @param studentId The ID of the student.
     * @return A list of StudentRecord objects for the student. Returns an empty list if student not found or has no records.
     */
    public List<StudentRecord> getStudentRecords(String studentId) {
        return studentRecords.getOrDefault(studentId, Collections.emptyList());
    }
}

/**
 * Main class for the Student Data Viewer application.
 * This class orchestrates the interaction between the user (simulated) and the SMOS server emulator.
 * It handles user login, child selection, and displaying student data.
 */
public class StudentDataViewer {

    private final SMOSServerEmulator smosServer;
    private Parent loggedInParent;
    private final Scanner scanner;

    /**
     * Constructs a new StudentDataViewer.
     * Initializes the SMOS server emulator and a scanner for user input.
     */
    public StudentDataViewer() {
        this.smosServer = new SMOSServerEmulator();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the parent login process.
     * Prompts the user for a parent ID and attempts to authenticate.
     * @return true if login is successful, false otherwise.
     */
    private boolean loginParent() {
        System.out.println("--- Parent Login ---");
        System.out.print("Enter Parent ID: ");
        String parentId = scanner.nextLine();
        // For simplicity, password is not used in this simulation but would be required in a real system.
        String password = "dummy_password"; // Placeholder

        Optional<Parent> authenticatedParent = smosServer.authenticateParent(parentId, password);

        if (authenticatedParent.isPresent()) {
            this.loggedInParent = authenticatedParent.get();
            System.out.println("Welcome, " + loggedInParent.getName() + "!");
            return true;
        } else {
            System.out.println("Login failed.