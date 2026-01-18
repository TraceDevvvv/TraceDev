package JustificationViewer_1766393399;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class for the Justification Viewer.
 * This class simulates the user interface and interaction flow for an administrator
 * to view a student's absences and their justification status.
 */
public class JustificationViewerApp {

    // ANSI escape codes for console text colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private final JustificationService justificationService;
    private final Scanner scanner;

    /**
     * Constructs a new JustificationViewerApp.
     * Initializes the JustificationService and a Scanner for user input.
     */
    public JustificationViewerApp() {
        this.justificationService = new JustificationService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Sets up initial data for demonstration purposes.
     * Adds students, records some absences, and justifies a few.
     */
    private void setupSampleData() {
        System.out.println(ANSI_BLUE + "\n--- Setting up Sample Data ---" + ANSI_RESET);

        // Add students
        justificationService.addStudent(new Student("S001", "Alice Smith"));
        justificationService.addStudent(new Student("S002", "Bob Johnson"));
        justificationService.addStudent(new Student("S003", "Charlie Brown"));

        // Record absences for Alice Smith (S001)
        justificationService.recordAbsence("S001", LocalDate.of(2023, 10, 1));
        justificationService.recordAbsence("S001", LocalDate.of(2023, 10, 5));
        justificationService.recordAbsence("S001", LocalDate.of(2023, 10, 10));
        justificationService.recordAbsence("S001", LocalDate.of(2023, 10, 15));
        justificationService.recordAbsence("S001", LocalDate.of(2023, 10, 20));

        // Record absences for Bob Johnson (S002)
        justificationService.recordAbsence("S002", LocalDate.of(2023, 10, 2));
        justificationService.recordAbsence("S002", LocalDate.of(2023, 10, 6));

        // Justify some absences for Alice Smith (S001)
        justificationService.justifyAbsence("S001", LocalDate.of(2023, 10, 1),
                "Medical appointment", "Admin A", LocalDate.of(2023, 10, 2));
        justificationService.justifyAbsence("S001", LocalDate.of(2023, 10, 10),
                "Family emergency", "Admin B", LocalDate.of(2023, 10, 11));

        System.out.println(ANSI_BLUE + "--- Sample Data Setup Complete ---\n" + ANSI_RESET);
    }

    /**
     * Simulates the administrator login process.
     * This is a prerequisite for viewing justifications.
     *
     * @return true if login is successful, false otherwise.
     */
    private boolean performLogin() {
        System.out.println(ANSI_YELLOW + "--- Administrator Login ---" + ANSI_RESET);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine(); // In a real app, this would be masked

        // For this simulation, any non-empty credentials work.
        // The JustificationService handles the actual login logic.
        if (justificationService.loginAdministrator(username, password)) {
            System.out.println(ANSI_GREEN + "Login successful!" + ANSI_RESET);
            return true;
        } else {
            System.out.println(ANSI_RED + "Login failed. Please try again." + ANSI_RESET);
            return false;
        }
    }

    /**
     * Displays the list of absences for a given student,
     * highlighting justified absences in green and unjustified in red.
     * This fulfills the core use case requirement.
     *
     * @param studentId The ID of the student whose absences are to be displayed.
     */
    private void displayStudentAbsences(String studentId) {
        // Precondition check: Administrator must be logged in.
        if (!justificationService.isAdministratorLoggedIn()) {
            System.out.println(ANSI_RED + "Error: Administrator not logged in. Cannot view absences." + ANSI_RESET);
            return;
        }

        Student student = justificationService.getStudentById(studentId);
        if (student == null) {
            System.out.println(ANSI_RED + "Error: Student with ID " + studentId + " not found." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_BLUE + "\n--- Absences for Student: " + student.getName() + " (ID: " + student.getStudentId() + ") ---" + ANSI_RESET);

        List<Absence> absences = justificationService.getAbsencesForStudent(studentId);

        if (absences.isEmpty()) {
            System.out.println(ANSI_YELLOW + "No absences recorded for this student." + ANSI_RESET);
            return;
        }

        // Sort absences by date for better readability
        absences.sort((a1, a2) -> a1.getAbsenceDate().compareTo(a2.getAbsenceDate()));

        for (Absence absence : absences) {
            String statusColor = absence.isJustified() ? ANSI_GREEN : ANSI_RED;
            String statusText = absence.isJustified() ? "Justified" : "Unjustified";
            String justificationDetails = "";

            if (absence.isJustified()) {
                Justification j = absence.getJustification();
                if (j != null) {
                    justificationDetails = " (Reason: '" + j.getReason() + "', Admin: " + j.getAdministratorName() + ", Date: " + j.getJustificationDate() + ")";
                }
            }

            System.out.println(statusColor +
                               "Date: " + absence.getAbsenceDate() +
                               " | Status: " + statusText +
                               justificationDetails +
                               ANSI_RESET);
        }
        System.out.println(ANSI_BLUE + "----------------------------------------------------" + ANSI_RESET);
    }

    /**
     * Simulates the "SplitTaTtAlTeloregistration" use case where an administrator
     * selects a student and clicks a "justification" button.
     */
    private void simulateStudentSelectionAndJustificationButton() {
        System.out.println(ANSI_YELLOW + "\n--- Simulating 'SplitTaTtAlTeloregistration' Use Case ---" + ANSI_RESET);
        System.out.println("Available Students:");
        // In a real UI, this would be a list to select from.
        // For simulation, we'll just pick one.
        System.out.println("S001 - Alice Smith");
        System.out.println("S002 - Bob Johnson");
        System.out.println("S003 - Charlie Brown");

        System.out.print("Enter Student ID to view justifications (e.g., S001): ");
        String selectedStudentId = scanner.nextLine();

        // This simulates clicking the "justification" button related to the student
        displayStudentAbsences(selectedStudentId);
    }

    /**
     * Runs the main application flow.
     */
    public void run() {
        setupSampleData();

        // Precondition: User must be logged in as an administrator.
        if (!performLogin()) {
            System.out.println(ANSI_RED + "Application terminated due to failed login." + ANSI_RESET);
            scanner.close();
            return;
        }

        // Simulate the event sequence: User selects a student and clicks "justification" button.
        simulateStudentSelectionAndJustificationButton();

        // Postcondition: The administrator interrupts the connection to the SMOS server.
        // This is simulated by logging out.
        System.out.println(ANSI_YELLOW + "\n--- Administrator Session End ---" + ANSI_RESET);
        justificationService.logoutAdministrator();
        System.out.println(ANSI_BLUE + "Connection to SMOS server interrupted (Administrator logged out)." + ANSI_RESET);

        scanner.close();
    }

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        JustificationViewerApp app = new JustificationViewerApp();
        app.run();
    }
}