package JustificationViewer_1766393399;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service class responsible for managing student data, absences, and justifications.
 * It acts as a central repository and provides methods for interacting with these entities.
 */
public class JustificationService {
    // Stores students, mapped by their unique student ID.
    private final Map<String, Student> students;
    // Stores absences, mapped by student ID, with each student having a list of their absences.
    private final Map<String, List<Absence>> studentAbsences;
    // Simulates an administrator login state. In a real system, this would involve session management.
    private boolean administratorLoggedIn;

    /**
     * Constructs a new JustificationService.
     * Initializes the data structures for students and absences.
     */
    public JustificationService() {
        this.students = new HashMap<>();
        this.studentAbsences = new HashMap<>();
        this.administratorLoggedIn = false; // Initially, no administrator is logged in.
    }

    /**
     * Simulates an administrator logging into the system.
     * For this example, any non-empty username and password will be considered valid.
     * In a real application, this would involve authentication against a user database.
     *
     * @param username The administrator's username.
     * @param password The administrator's password.
     * @return true if login is successful, false otherwise.
     */
    public boolean loginAdministrator(String username, String password) {
        // Basic validation for demonstration purposes.
        // In a real system, this would involve secure authentication.
        if (username != null && !username.trim().isEmpty() &&
            password != null && !password.trim().isEmpty()) {
            this.administratorLoggedIn = true;
            System.out.println("Administrator '" + username + "' logged in successfully.");
            return true;
        }
        System.out.println("Administrator login failed. Invalid username or password.");
        return false;
    }

    /**
     * Simulates an administrator logging out of the system.
     */
    public void logoutAdministrator() {
        this.administratorLoggedIn = false;
        System.out.println("Administrator logged out.");
    }

    /**
     * Checks if an administrator is currently logged in.
     *
     * @return true if an administrator is logged in, false otherwise.
     */
    public boolean isAdministratorLoggedIn() {
        return administratorLoggedIn;
    }

    /**
     * Adds a new student to the system.
     *
     * @param student The Student object to add.
     * @throws IllegalArgumentException if the student is null or a student with the same ID already exists.
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (students.containsKey(student.getStudentId())) {
            throw new IllegalArgumentException("Student with ID " + student.getStudentId() + " already exists.");
        }
        students.put(student.getStudentId(), student);
        studentAbsences.put(student.getStudentId(), new ArrayList<>()); // Initialize an empty list for absences
        System.out.println("Student added: " + student.getName() + " (ID: " + student.getStudentId() + ")");
    }

    /**
     * Records an absence for a specific student on a given date.
     *
     * @param studentId The ID of the student.
     * @param absenceDate The date of the absence.
     * @throws IllegalArgumentException if the student does not exist or absenceDate is null.
     */
    public void recordAbsence(String studentId, LocalDate absenceDate) {
        if (!students.containsKey(studentId)) {
            throw new IllegalArgumentException("Student with ID " + studentId + " not found.");
        }
        if (absenceDate == null) {
            throw new IllegalArgumentException("Absence date cannot be null.");
        }

        List<Absence> absences = studentAbsences.get(studentId);
        // Check if an absence for this student on this date already exists to prevent duplicates
        boolean alreadyExists = absences.stream()
                                        .anyMatch(a -> a.getAbsenceDate().equals(absenceDate));
        if (alreadyExists) {
            System.out.println("Absence for student " + studentId + " on " + absenceDate + " already recorded.");
            return;
        }

        Absence newAbsence = new Absence(studentId, absenceDate);
        absences.add(newAbsence);
        System.out.println("Absence recorded for student " + studentId + " on " + absenceDate);
    }

    /**
     * Justifies a specific absence for a student.
     *
     * @param studentId The ID of the student.
     * @param absenceDate The date of the absence to justify.
     * @param reason The reason for the justification.
     * @param administratorName The name of the administrator performing the justification.
     * @param justificationDate The date the justification was made.
     * @return true if the absence was successfully justified, false if the absence or student was not found.
     * @throws IllegalStateException if no administrator is logged in.
     * @throws IllegalArgumentException if any justification detail is null or empty.
     */
    public boolean justifyAbsence(String studentId, LocalDate absenceDate, String reason, String administratorName, LocalDate justificationDate) {
        if (!administratorLoggedIn) {
            throw new IllegalStateException("Administrator must be logged in to justify an absence.");
        }
        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Justification reason cannot be null or empty.");
        }
        if (administratorName == null || administratorName.trim().isEmpty()) {
            throw new IllegalArgumentException("Administrator name cannot be null or empty.");
        }
        if (justificationDate == null) {
            throw new IllegalArgumentException("Justification date cannot be null.");
        }

        List<Absence> absences = studentAbsences.get(studentId);
        if (absences == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return false;
        }

        Optional<Absence> absenceToJustify = absences.stream()
                                                    .filter(a -> a.getAbsenceDate().equals(absenceDate))
                                                    .findFirst();

        if (absenceToJustify.isPresent()) {
            Absence absence = absenceToJustify.get();
            if (absence.isJustified()) {
                System.out.println("Absence for student " + studentId + " on " + absenceDate + " is already justified.");
                return false;
            }
            Justification justification = new Justification(reason, administratorName, justificationDate);
            absence.setJustification(justification);
            System.out.println("Absence for student " + studentId + " on " + absenceDate + " justified.");
            return true;
        } else {
            System.out.println("Absence for student " + studentId + " on " + absenceDate + " not found.");
            return false;
        }
    }

    /**
     * Retrieves all absences for a given student.
     *
     * @param studentId The ID of the student.
     * @return A list of Absence objects for the specified student. Returns an empty list if the student
     *         is not found or has no absences.
     * @throws IllegalStateException if no administrator is logged in.
     */
    public List<Absence> getAbsencesForStudent(String studentId) {
        if (!administratorLoggedIn) {
            throw new IllegalStateException("Administrator must be logged in to view absences.");
        }
        if (!students.containsKey(studentId)) {
            System.out.println("Student with ID " + studentId + " not found.");
            return new ArrayList<>(); // Return empty list if student not found
        }
        // Return a defensive copy to prevent external modification of the internal list
        return new ArrayList<>(studentAbsences.getOrDefault(studentId, new ArrayList<>()));
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
}