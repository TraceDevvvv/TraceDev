```java
package com.example.editabsence;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main application entry point for the EditAbsence use case.
 * This class simulates the UI interaction and orchestrates the other components
 * (Administrator, Student, Absence, AbsenceManager, NotificationService, SMOSServerConnection)
 * to demonstrate the use case flow.
 */
public class EditAbsenceApp {

    private AbsenceManager absenceManager;
    private NotificationService notificationService;
    private SMOSServerConnection smosServerConnection;
    private Administrator loggedInAdmin;
    private Scanner scanner;

    /**
     * Constructor to initialize the application components.
     */
    public EditAbsenceApp() {
        this.absenceManager = new AbsenceManager();
        this.notificationService = new NotificationService();
        this.smosServerConnection = new SMOSServerConnection();
        this.scanner = new Scanner(System.in);
        initializeData(); // Setup some initial data for demonstration
    }

    /**
     * Initializes dummy data for administrators and students with some absences.
     */
    private void initializeData() {
        // Create a dummy administrator
        Administrator admin = new Administrator("admin", "admin123", true);
        // In a real system, administrators would be managed by a separate user service.
        // For this simulation, we just have one.

        // Create dummy students
        Student student1 = new Student("S001", "Alice Smith", "alice.parent@example.com");
        Student student2 = new Student("S002", "Bob Johnson", "bob.parent@example.com");

        absenceManager.addStudent(student1);
        absenceManager.addStudent(student2);

        // Add some initial absences for student1
        absenceManager.addAbsenceRecord("S001", LocalDate.of(2023, 10, 26), Absence.AbsenceType.UNJUSTIFIED, "Forgot to come");
        absenceManager.addAbsenceRecord("S001", LocalDate.of(2023, 10, 27), Absence.AbsenceType.SICK_LEAVE, "Fever");
        absenceManager.addAbsenceRecord("S002", LocalDate.of(2023, 10, 26), Absence.AbsenceType.JUSTIFIED, "Family event");

        System.out.println("--- Initial Data Setup Complete ---");
        System.out.println("Available Students: S001 (Alice Smith), S002 (Bob Johnson)");
        System.out.println("-----------------------------------");
    }

    /**
     * Simulates the administrator login process.
     *
     * @return true if login is successful, false otherwise.
     */
    public boolean simulateLogin() {
        System.out.println("\n--- Administrator Login ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // For this simulation, we have a hardcoded admin
        Administrator admin = new Administrator("admin", "admin123", true);

        if (admin.authenticate(username, password)) {
            this.loggedInAdmin = admin;
            System.out.println("Login successful. Welcome, " + loggedInAdmin.getUsername() + "!");
            return true;
        } else {
            System.out.println("Login failed. Invalid credentials or not an administrator.");
            return false;
        }
    }

    /**
     * Simulates the administrator selecting a date and a student.
     * This fulfills the "Preconditions" step of the use case.
     *
     * @return An Optional containing the selected Student object if successful, empty otherwise.
     */
    public Optional<Student> simulateSelectDateAndStudent() {
        if (loggedInAdmin == null) {
            System.out.println("Error: No administrator logged in.");
            return Optional.empty();
        }

        System.out.println("\n--- Select Student and Date for Absence Editing ---");
        String studentId;
        Optional<Student> selectedStudent;
        do {
            System.out.print("Enter Student ID (e.g., S001, S002): ");
            studentId = scanner.nextLine();
            selectedStudent = absenceManager.getStudent(studentId);
            if (selectedStudent.isEmpty()) {
                System.out.println("Student with ID '" + studentId + "' not found. Please try again.");
            }
        } while (selectedStudent.isEmpty());

        System.out.print("Enter Date (YYYY-MM-DD) to view/edit absences: ");
        String dateString = scanner.nextLine();
        LocalDate selectedDate;
        try {
            selectedDate = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD. Operation cancelled.");
            return Optional.empty();
        }

        // Store selected date for later use in the UI simulation
        // In a real UI, this would be part of the screen state.
        System.out.println("Selected Student: " + selectedStudent.get().getName() + " (ID: " + selectedStudent.get().getStudentId() + ")");
        System.out.println("Selected Date: " + selectedDate);

        // Step 1: Update the screen based on the selected date
        displayAbsencesForDate(selectedStudent.get().getStudentId(), selectedDate);

        return selectedStudent;
    }

    /**
     * Displays the current absences for a given student on a specific date.
     * This simulates the "Update the screen based on the selected date" system event.
     *
     * @param studentId The ID of the student.
     * @param date      The date to display absences for.
     */
    private void displayAbsencesForDate(String studentId, LocalDate date) {
        System.out.println("\n--- Absences for " + studentId + " on " + date + " ---");
        List<Absence> absences = absenceManager.getAbsencesForStudentOnDate(studentId, date);
        if (absences.isEmpty()) {
            System.out.println("No absences recorded for this student on this date.");
        } else {
            absences.forEach(System.out::println);
        }
        System.out.println("-------------------------------------------------");
    }

    /**
     * Simulates the administrator changing absences (insert or delete).
     * This covers the "Change the absence (insert or delete)" user event.
     *
     * @param student    The student whose absences are being edited.
     * @param targetDate The date for which absences are being edited.
     * @return true if changes were made, false otherwise.
     */
    public boolean performAbsenceEdit(Student student, LocalDate targetDate) {
        boolean changesMade = false;
        String studentId = student.getStudentId();

        System.out.println("\n--- Editing Absences for " + student.getName() + " on " + targetDate + " ---");
        System.out.println("Options: [A]dd absence, [R]emove absence, [S]ave changes, [C]ancel operation");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice) {
            case "A":
                System.out.print("Enter Absence Type (JUSTIFIED, UNJUSTIFIED, SICK_LEAVE, OTHER): ");
                String typeString = scanner.nextLine().trim().toUpperCase();
                Absence.AbsenceType type;
                try {
                    type = Absence.AbsenceType.valueOf(typeString);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid absence type. Please choose from: JUSTIFIED, UNJUSTIFIED, SICK_LEAVE, OTHER.");
                    return false; // No changes made
                }
                System.out.print("Enter Reason (optional): ");
                String reason = scanner.nextLine();

                if (absenceManager.addAbsenceRecord(studentId, targetDate, type, reason)) {
                    System.out.println("Absence added successfully.");
                    changesMade = true;
                } else {
                    System.out.println("Failed to add absence. It might already exist or student not found.");
                }
                break;

            case "R":
                System.out.print("Enter Absence Type to remove (JUSTIFIED, UNJUSTIFIED, SICK_LEAVE, OTHER): ");
                String typeToRemoveString = scanner.nextLine().trim().toUpperCase();
                Absence.AbsenceType typeToRemove;
                try {
                    typeToRemove = Absence.AbsenceType.valueOf(typeToRemoveString);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid absence type. Please choose from: JUSTIFIED, UNJUSTIFIED, SICK_LEAVE, OTHER.");
                    return false; // No changes made
                }

                if (absenceManager.removeAbsenceRecord(studentId, targetDate, typeToRemove)) {
                    System.out.println("Absence removed successfully.");
                    changesMade = true;
                } else {
                    System.out.println("Failed to remove absence. It might not exist or student not found.");
                }
                break;

            case "S":
                System.out.println("Changes will be saved. (This option will trigger saveChanges method)");
                changesMade = true; // Indicate that the user intends to save
                break;

            case "C":
                System.out.println("Operation cancelled by administrator. No changes will be saved.");
                // Postcondition: The administrator interrupts the operation
                return false; // No changes made, and user explicitly cancelled
            default:
                System.out.println("Invalid choice. No changes made.");
                break;
        }
        // After an edit, display the updated state
        displayAbsencesForDate(studentId, targetDate);
        return changesMade;
    }

    /**
     * Simulates clicking "Save" and sending modified data to the server,
     * then sending a notification email.
     * This covers "Click 'Save'", "Send modified data to the server",
     * and "The system sends a grinding e-mail to the parent of the student" events.
     *
     * @param student    The student whose absences were modified.
     * @param targetDate The date for which absences were modified.
     * @return true if save and notification were successful, false otherwise.
     */
    public boolean saveChanges(Student student, LocalDate targetDate) {
        System.out.println("\n--- Saving Changes ---");

        // Step 3: Send modified data to the server.
        // In a real application, this would involve serializing the changes or the entire student record.
        // For simulation, we'll just send a string representation of the student's current absences.
        String dataToSend = "Absence changes for student " + student.getStudentId() + " on " + targetDate + ": " +
                            absenceManager.getAbsencesForStudentOnDate(student.getStudentId(), targetDate);

        if (!smosServerConnection.isConnected()) {
            System.out.println("SMOS server not connected. Attempting to connect...");
            if (!smosServerConnection.connect()) {
                System.err.println("Failed to connect to SMOS server. Changes not saved to server.");
                // Postcondition: Connection to the SMOS server interrupted (or failed to establish)
                return false;
            }
        }

        if (!smosServerConnection.sendDataToServer(dataToSend)) {
            System.err.println("Failed to send data to SMOS server. Changes not fully synchronized.");
            // Postcondition: Connection to the SMOS server interrupted
            return false;
        }

        // The system sends a grinding e-mail to the parent of the student.
        String subject = "Absence Update for " + student.getName() + " on " + targetDate;
        String body = generateAbsenceNotificationBody(student, targetDate);
        notificationService.sendEmail(student.getParentEmail(), subject, body);

        System.out.println("Changes saved and parent notified.");
        // Postcondition: The system has changed the selected absence and sent parents a modification notification.
        // Postcondition: The system remains on the registry screen (simulated by displaying current state).
        displayAbsencesForDate(student.getStudentId(), targetDate);
        return true;
    }

    /**
     * Generates the body of the email notification for parents.
     *
     * @param student    The student object.
     * @param targetDate The date of the absence changes.
     * @return A string containing the email body.
     */
    private String generateAbsenceNotificationBody(Student student, LocalDate targetDate) {
        StringBuilder body = new StringBuilder();
        body.append("Dear Parent of ").append(student.getName()).append(",\n\n");
        body.append("This is an automated notification regarding your child's absence record.\n");
        body.append("Changes have been made to the absences for ").append(targetDate.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE)).append(".\n\n");
        body.append("Current absences for ").append(targetDate.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE)).append(":\n");

        List<Absence> currentAbsences = absenceManager.getAbsencesForStudentOnDate(student.getStudentId(), targetDate);
        if (currentAbsences.isEmpty()) {
            body.append("  No absences recorded.\n");
        } else {
            for (Absence absence : currentAbsences) {
                body.append("  - Date: ").append(absence.getDate())
                    .append(", Type: ").append(absence.getType())
                    .append(", Reason: ").append(absence.getReason().isEmpty() ? "N/A" : absence.getReason())
                    .append("\n");
            }
        }
        body.append("\nIf you have any questions, please contact the school administration.\n\n");
        body.append("Sincerely,\nThe School Administration");
        return body.toString();
    }

    /**
     * Runs the main simulation of the EditAbsence use case.
     */
    public void run() {
        System.out.println("--- Starting EditAbsence Use Case Simulation ---");

        // Precondition: The user must be logged in to the system as an administrator
        if (!simulateLogin()) {
            System.out.println("Application terminated due to failed login.");
            return;
        }

        // Precondition: The user selects the date on which you want to change an absence
        Optional<Student> selectedStudentOpt = simulateSelectDateAndStudent();
        if (selectedStudentOpt.isEmpty()) {
            System.out.println("Application terminated due to invalid student/date selection.");
            return;
        }
        Student student = selectedStudentOpt.get();
        // In a real UI, the selected date would be part of the screen state.
        // For this simulation, let