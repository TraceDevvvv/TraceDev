package com.smos.server;

import com.smos.model.Administrator;
import com.smos.model.Note;
import com.smos.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Simulates the backend system for the School Management and Operations System (SMOS).
 * This server handles user authentication, student management, and note management.
 * It provides methods for administrators to log in and view student notes.
 */
public class SMOSServer {
    // In-memory storage for demonstration purposes. In a real application, this would be a database.
    private final Map<String, Administrator> administrators;
    private final Map<String, Student> students;
    private final Map<String, List<Note>> studentNotes; // Key: studentId, Value: List of Notes

    // Represents the currently logged-in administrator.
    private Administrator loggedInAdministrator;

    /**
     * Constructs a new SMOSServer and initializes it with some dummy data.
     */
    public SMOSServer() {
        this.administrators = new HashMap<>();
        this.students = new HashMap<>();
        this.studentNotes = new HashMap<>();
        this.loggedInAdministrator = null; // No one logged in initially

        // Initialize with dummy data
        initializeDummyData();
    }

    /**
     * Initializes the server with some predefined students, notes, and administrators.
     * This simulates a pre-populated system.
     */
    private void initializeDummyData() {
        // Add dummy administrators
        Administrator admin1 = new Administrator("admin001", "admin", "adminpass");
        Administrator admin2 = new Administrator("admin002", "john.doe", "securepass");
        addAdministrator(admin1);
        addAdministrator(admin2);

        // Add dummy students
        Student student1 = new Student("S001", "Alice Smith");
        Student student2 = new Student("S002", "Bob Johnson");
        Student student3 = new Student("S003", "Charlie Brown");
        addStudent(student1);
        addStudent(student2);
        addStudent(student3);

        // Add dummy notes for students
        addNote(new Note("N001", "S001", "Excellent participation in class discussions.", LocalDate.of(2023, 9, 15)));
        addNote(new Note("N002", "S001", "Needs to improve on homework submission deadlines.", LocalDate.of(2023, 10, 1)));
        addNote(new Note("N003", "S002", "Showed great progress in mathematics this quarter.", LocalDate.of(2023, 11, 10)));
        addNote(new Note("N004", "S001", "Consistently performs well in group projects.", LocalDate.of(2023, 12, 5)));
        addNote(new Note("N005", "S003", "Struggling with reading comprehension, recommend extra support.", LocalDate.of(2024, 1, 20)));
    }

    /**
     * Adds an administrator to the server's data store.
     *
     * @param administrator The Administrator object to add.
     * @throws IllegalArgumentException if the administrator is null or an administrator with the same username already exists.
     */
    public void addAdministrator(Administrator administrator) {
        if (administrator == null) {
            throw new IllegalArgumentException("Administrator cannot be null.");
        }
        if (administrators.containsKey(administrator.getUsername())) {
            throw new IllegalArgumentException("Administrator with username '" + administrator.getUsername() + "' already exists.");
        }
        administrators.put(administrator.getUsername(), administrator);
    }

    /**
     * Adds a student to the server's data store.
     *
     * @param student The Student object to add.
     * @throws IllegalArgumentException if the student is null or a student with the same ID already exists.
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (students.containsKey(student.getStudentId())) {
            throw new IllegalArgumentException("Student with ID '" + student.getStudentId() + "' already exists.");
        }
        students.put(student.getStudentId(), student);
    }

    /**
     * Adds a note to the server's data store, associating it with a student.
     *
     * @param note The Note object to add.
     * @throws IllegalArgumentException if the note is null or the associated student does not exist.
     */
    public void addNote(Note note) {
        if (note == null) {
            throw new IllegalArgumentException("Note cannot be null.");
        }
        if (!students.containsKey(note.getStudentId())) {
            throw new IllegalArgumentException("Student with ID '" + note.getStudentId() + "' not found for this note.");
        }
        studentNotes.computeIfAbsent(note.getStudentId(), k -> new ArrayList<>()).add(note);
    }

    /**
     * Attempts to authenticate an administrator with the provided username and password.
     * If successful, the administrator is set as logged in.
     *
     * @param username The username of the administrator.
     * @param password The password of the administrator.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticate(String username, String password) {
        Administrator admin = administrators.get(username);
        if (admin != null && admin.getPassword().equals(password)) {
            this.loggedInAdministrator = admin;
            System.out.println("Administrator '" + username + "' logged in successfully.");
            return true;
        }
        System.out.println("Authentication failed for username: " + username);
        return false;
    }

    /**
     * Checks if an administrator is currently logged into the system.
     *
     * @return true if an administrator is logged in, false otherwise.
     */
    public boolean isAuthenticated() {
        return loggedInAdministrator != null;
    }

    /**
     * Logs out the current administrator.
     */
    public void logout() {
        if (loggedInAdministrator != null) {
            System.out.println("Administrator '" + loggedInAdministrator.getUsername() + "' logged out.");
            loggedInAdministrator = null;
        }
    }

    /**
     * Retrieves a list of all notes for a specific student.
     * This operation requires an administrator to be logged in.
     *
     * @param studentId The ID of the student whose notes are to be retrieved.
     * @return A list of Note objects for the specified student, or an empty list if no notes are found
     *         or the student does not exist. Returns null if no administrator is logged in.
     */
    public List<Note> getStudentNotes(String studentId) {
        // Precondition: The user must be logged in as an administrator.
        if (!isAuthenticated()) {
            System.err.println("Error: No administrator is logged in. Cannot retrieve student notes.");
            return null; // Or throw an UnauthorizedAccessException
        }

        // Validate studentId
        if (studentId == null || studentId.trim().isEmpty()) {
            System.err.println("Error: Student ID cannot be null or empty.");
            return new ArrayList<>(); // Return empty list for invalid input
        }

        // Check if the student exists
        if (!students.containsKey(studentId)) {
            System.out.println("No student found with ID: " + studentId);
            return new ArrayList<>(); // Return empty list if student not found
        }

        // Retrieve notes for the student
        List<Note> notes = studentNotes.getOrDefault(studentId, new ArrayList<>());
        System.out.println("Retrieved " + notes.size() + " notes for student ID: " + studentId);
        return new ArrayList<>(notes); // Return a copy to prevent external modification
    }

    /**
     * Retrieves a student by their ID.
     *
     * @param studentId The ID of the student to retrieve.
     * @return The Student object if found, otherwise null.
     */
    public Student getStudentById(String studentId) {
        return students.get(studentId);
    }

    /**
     * Simulates interrupting the connection to the SMOS server.
     * In a real application, this might involve closing database connections,
     * shutting down network listeners, etc. For this simulation, it just
     * logs a message and clears the logged-in administrator state.
     */
    public void interruptConnection() {
        System.out.println("SMOS server connection interrupted.");
        this.loggedInAdministrator = null; // Ensure no one is logged in after interruption
        // In a real scenario, this might also clear session data, etc.
    }
}