import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Represents the status of a student in the system.
 */
enum StudentStatus {
    PENDING_ENROLLMENT, // Student has submitted an enrollment request, awaiting approval
    ACTIVE,             // Student's enrollment has been accepted
    INACTIVE,           // Student is no longer active (e.g., graduated, withdrawn)
    REJECTED            // Student's enrollment request was rejected
}

/**
 * Represents a student in the system.
 */
class Student {
    private final String studentId;
    private String name;
    private String email;
    private StudentStatus status;

    /**
     * Constructs a new Student object.
     *
     * @param studentId The unique identifier for the student.
     * @param name      The name of the student.
     * @param email     The email address of the student.
     * @param status    The initial status of the student.
     */
    public Student(String studentId, String name, String email, StudentStatus status) {
        this.studentId = Objects.requireNonNull(studentId, "Student ID cannot be null");
        this.name = Objects.requireNonNull(name, "Student name cannot be null");
        this.email = Objects.requireNonNull(email, "Student email cannot be null");
        this.status = Objects.requireNonNull(status, "Student status cannot be null");
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public StudentStatus getStatus() {
        return status;
    }

    // Setters
    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Student name cannot be null");
    }

    public void setEmail(String email) {
        this.email = Objects.requireNonNull(email, "Student email cannot be null");
    }

    public void setStatus(StudentStatus status) {
        this.status = Objects.requireNonNull(status, "Student status cannot be null");
    }

    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", status=" + status +
               '}'
;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}

/**
 * Represents an enrollment request made by a student.
 */
class EnrollmentRequest {
    private final String requestId;
    private final Student student;
    private final LocalDateTime requestDate;

    /**
     * Constructs a new EnrollmentRequest object.
     *
     * @param requestId   The unique identifier for the enrollment request.
     * @param student     The student associated with this request.
     * @param requestDate The date and time when the request was made.
     */
    public EnrollmentRequest(String requestId, Student student, LocalDateTime requestDate) {
        this.requestId = Objects.requireNonNull(requestId, "Request ID cannot be null");
        this.student = Objects.requireNonNull(student, "Student cannot be null");
        this.requestDate = Objects.requireNonNull(requestDate, "Request date cannot be null");
    }

    // Getters
    public String getRequestId() {
        return requestId;
    }

    public Student getStudent() {
        return student;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    @Override
    public String toString() {
        return "EnrollmentRequest{" +
               "requestId='" + requestId + '\'' +
               ", studentId='" + student.getStudentId() + '\'' +
               ", studentName='" + student.getName() + '\'' +
               ", requestDate=" + requestDate +
               '}'
;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentRequest that = (EnrollmentRequest) o;
        return requestId.equals(that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId);
    }
}

/**
 * Manages student enrollment requests and student activation.
 * This class acts as the core system component for the "AcceptEnrollmentStudent" use case.
 */
class EnrollmentService {
    // Stores all students, indexed by student ID, for quick lookup.
    private final Map<String, Student> allStudents;
    // Stores pending enrollment requests, indexed by request ID.
    private final Map<String, EnrollmentRequest> pendingRequests;

    /**
     * Constructs a new EnrollmentService.
     * Initializes the data structures for students and requests.
     */
    public EnrollmentService() {
        this.allStudents = new ConcurrentHashMap<>(); // Using ConcurrentHashMap for thread-safety if needed
        this.pendingRequests = new ConcurrentHashMap<>();
    }

    /**
     * Simulates a student submitting an enrollment request.
     * Creates a new student with PENDING_ENROLLMENT status and a corresponding request.
     *
     * @param studentName  The name of the student.
     * @param studentEmail The email of the student.
     * @return The created EnrollmentRequest, or null if a student with the same email already exists.
     */
    public EnrollmentRequest submitEnrollmentRequest(String studentName, String studentEmail) {
        // Check if a student with this email already exists (simple uniqueness check)
        Optional<Student> existingStudent = allStudents.values().stream()
                                                      .filter(s -> s.getEmail().equalsIgnoreCase(studentEmail))
                                                      .findFirst();
        if (existingStudent.isPresent()) {
            System.out.println("Error: A student with email " + studentEmail + " already exists.");
            return null;
        }

        String studentId = "STU-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Student newStudent = new Student(studentId, studentName, studentEmail, StudentStatus.PENDING_ENROLLMENT);
        allStudents.put(studentId, newStudent);

        String requestId = "REQ-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        EnrollmentRequest request = new EnrollmentRequest(requestId, newStudent, LocalDateTime.now());
        pendingRequests.put(requestId, request);

        System.out.println("New enrollment request submitted: " + request);
        return request;
    }

    /**
     * Retrieves a list of all pending enrollment requests.
     * This fulfills the "View Registration Requests" precondition.
     *
     * @return An unmodifiable list of pending enrollment requests.
     */
    public List<EnrollmentRequest> getPendingEnrollmentRequests() {
        // Return a new ArrayList to prevent external modification of the internal map
        return Collections.unmodifiableList(new ArrayList<>(pendingRequests.values()));
    }

    /**
     * Activates a student's enrollment based on a given request ID.
     * This is the core action of the "AcceptEnrollmentStudent" use case.
     *
     * @param requestId The unique identifier of the enrollment request to accept.
     * @return true if the enrollment was successfully accepted and the student activated, false otherwise.
     */
    public boolean acceptEnrollmentRequest(String requestId) {
        EnrollmentRequest request = pendingRequests.get(requestId);

        if (request == null) {
            System.out.println("Error: Enrollment request with ID '" + requestId + "' not found.");
            return false;
        }

        Student studentToActivate = request.getStudent();

        // Ensure the student is in a pending state before activation
        if (studentToActivate.getStatus() != StudentStatus.PENDING_ENROLLMENT) {
            System.out.println("Error: Student " + studentToActivate.getName() +
                               " (ID: " + studentToActivate.getStudentId() +
                               ") is not in PENDING_ENROLLMENT status. Current status: " +
                               studentToActivate.getStatus());
            return false;
        }

        // 1. Activate the new user in the system.
        studentToActivate.setStatus(StudentStatus.ACTIVE);
        // Remove the request from the pending list as it has been processed
        pendingRequests.remove(requestId);

        System.out.println("Successfully accepted enrollment request '" + requestId +
                           "'. Student '" + studentToActivate.getName() +
                           "' (ID: " + studentToActivate.getStudentId() + ") is now ACTIVE.");
        return true;
    }

    /**
     * Retrieves a list of all currently active students.
     *
     * @return An unmodifiable list of active students.
     */
    public List<Student> getActivatedStudents() {
        return Collections.unmodifiableList(
                allStudents.values().stream()
                        .filter(s -> s.getStatus() == StudentStatus.ACTIVE)
                        .collect(Collectors.toList())
        );
    }
}

/**
 * Main class to demonstrate the AcceptEnrollmentStudent use case.
 * This simulates the Administrator's interaction with the system.
 */
public class AcceptEnrollmentStudent {

    public static void main(String[] args) {
        System.out.println("--- Starting AcceptEnrollmentStudent Use Case Simulation ---");

        // Initialize the Enrollment Service
        EnrollmentService enrollmentService = new EnrollmentService();

        // --- Simulate Preconditions ---
        // Precondition 1: The user has already done the case of use "View Registration Requests"
        // Simulate students submitting enrollment requests
        System.out.println("\n--- Simulating Student Enrollment Requests ---");
        EnrollmentRequest req1 = enrollmentService.submitEnrollmentRequest("Alice Smith", "alice.smith@example.com");
        EnrollmentRequest req2 = enrollmentService.submitEnrollmentRequest("Bob Johnson", "bob.j@example.com");
        EnrollmentRequest req3 = enrollmentService.submitEnrollmentRequest("Charlie Brown", "charlie.b@example.com");

        // Precondition 2: The user clicks on the "Accept" button associated with a registration request
        // This is simulated by calling acceptEnrollmentRequest with a request ID.

        // Display pending requests (simulating "View Registration Requests")
        System.out.println("\n--- Current Pending Enrollment Requests (Before Acceptance) ---");
        List<EnrollmentRequest> pending = enrollmentService.getPendingEnrollmentRequests();
        if (pending.isEmpty()) {
            System.out.println("No pending requests.");
        } else {
            pending.forEach(System.out::println);
        }

        // --- Simulate Events Sequence ---
        // User clicks "Accept" for Alice's request
        System.out.println("\n--- Administrator accepts Alice Smith's enrollment request ---");
        if (req1 != null) {
            enrollmentService.acceptEnrollmentRequest(req1.getRequestId());
        }

        // User clicks "Accept" for Bob's request
        System.out.println("\n--- Administrator accepts Bob Johnson's enrollment request ---");
        if (req2 != null) {
            enrollmentService.acceptEnrollmentRequest(req2.getRequestId());
        }

        // Attempt to accept a non-existent request (edge case)
        System.out.println("\n--- Attempting to accept a non-existent request ---");
        enrollmentService.acceptEnrollmentRequest("NON_EXISTENT_REQ");

        // Attempt to accept Charlie's request, but simulate an interruption (edge case)
        System.out.println("\n--- Administrator attempts to accept Charlie Brown's request (simulating interruption) ---");
        // In a real system, an interruption would prevent the state change. Here, we'll just show it's still pending.
        System.out.println("Charlie Brown's request is still pending due to simulated interruption.");

        // --- Simulate Postconditions ---
        // Display remaining pending requests
        System.out.println("\n--- Current Pending Enrollment Requests (After Acceptance Attempts) ---");
        pending = enrollmentService.getPendingEnrollmentRequests();
        if (pending.isEmpty()) {
            System.out.println("No pending requests.");
        } else {
            pending.forEach(System.out::println);
        }

        // Display activated students
        System.out.println("\n--- Currently Activated Students ---");
        List<Student> activeStudents = enrollmentService.getActivatedStudents();
        if (activeStudents.isEmpty()) {
            System.out.println("No active students.");
        }
        else {
            activeStudents.forEach(System.out::println);
        }

        System.out.println("\n--- AcceptEnrollmentStudent Use Case Simulation Finished ---");
    }
}