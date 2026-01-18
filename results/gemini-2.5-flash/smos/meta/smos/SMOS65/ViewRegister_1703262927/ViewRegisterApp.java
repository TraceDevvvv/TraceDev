import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Main application class for the ViewRegister use case.
 * This class simulates the user interface and interaction flow for viewing
 * and managing class register details.
 * It handles connecting to the SMOS server, fetching register data,
 * displaying student information, and allowing management of justifications
 * and disciplinary notes.
 */
public class ViewRegisterApp {

    private static Scanner scanner = new Scanner(System.in);
    private static final String ACADEMIC_YEAR = "2023-2024"; // Hardcoded for simulation

    public static void main(String[] args) {
        System.out.println("--- Welcome to the Class Register Viewer ---");

        // Precondition: User is logged in and selected an academic year.
        simulateLoginAndAcademicYearSelection();

        // Attempt to connect to the SMOS server
        if (!SMOSServer.connect()) {
            System.err.println("Failed to connect to the SMOS server. Exiting application.");
            scanner.close();
            return;
        }

        Map<LocalDate, Register> academicYearRegisters = null;
        try {
            // Fetch registers for the selected academic year
            academicYearRegisters = SMOSServer.getRegistersForAcademicYear(ACADEMIC_YEAR);
        } catch (IllegalStateException e) {
            System.err.println("Error fetching registers: " + e.getMessage());
            SMOSServer.disconnect(); // Ensure disconnection on error
            scanner.close();
            return;
        }

        if (academicYearRegisters.isEmpty()) {
            System.out.println("No registers found for academic year " + ACADEMIC_YEAR + ".");
            SMOSServer.disconnect();
            scanner.close();
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\n--- Academic Year " + ACADEMIC_YEAR + " Registers ---");
            // Display available register dates and get user selection
            Register selectedRegister = selectRegisterDate(academicYearRegisters);

            if (selectedRegister == null) {
                // User chose to exit or invalid input
                running = false;
            } else {
                // Display details for the selected register
                displayRegisterDetails(selectedRegister);

                // Offer to manage student notes
                System.out.print("\nDo you want to manage notes for a student in this register? (yes/no): ");
                String manageChoice = scanner.nextLine().trim().toLowerCase();
                if ("yes".equals(manageChoice)) {
                    manageStudentNotesInteraction(selectedRegister);
                }
            }
        }

        // Postcondition: Connection to the SMOS server interrupted (disconnected)
        SMOSServer.disconnect();
        System.out.println("Application closed. SMOS server connection terminated.");
        scanner.close();
    }

    /**
     * Simulates the user login and academic year selection process.
     * In a real application, this would involve authentication and UI elements.
     */
    private static void simulateLoginAndAcademicYearSelection() {
        System.out.println("Simulating user login...");
        System.out.println("User 'Direction' logged in successfully.");
        System.out.println("User navigated to 'viewelcoregistri'.");
        System.out.println("System displaying list of registers for academic year: " + ACADEMIC_YEAR);
        System.out.println("User clicks 'Register' button associated with a class.");
    }

    /**
     * Displays the list of available register dates and prompts the user to select one.
     *
     * @param academicYearRegisters A map of LocalDate to Register objects for the academic year.
     * @return The selected Register object, or null if the user chooses to exit or enters invalid input.
     */
    private static Register selectRegisterDate(Map<LocalDate, Register> academicYearRegisters) {
        // Sort dates for consistent display
        List<LocalDate> sortedDates = academicYearRegisters.keySet().stream()
                .sorted(Comparator.reverseOrder()) // Show most recent first
                .collect(Collectors.toList());

        System.out.println("Available Register Dates:");
        for (int i = 0; i < sortedDates.size(); i++) {
            System.out.println((i + 1) + ". " + sortedDates.get(i));
        }
        System.out.println("0. Exit Application");

        int choice = -1;
        while (choice < 0 || choice > sortedDates.size()) {
            System.out.print("Enter the number corresponding to the register date you want to view (or 0 to exit): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    return null; // User chose to exit
                }
                if (choice < 1 || choice > sortedDates.size()) {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + sortedDates.size() + ", or 0 to exit.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return academicYearRegisters.get(sortedDates.get(choice - 1));
    }

    /**
     * Displays the detailed information for a given Register object.
     * This includes student attendance, justifications, and disciplinary notes.
     *
     * @param register The Register object to display.
     */
    private static void displayRegisterDetails(Register register) {
        System.out.println("\n--- Register Details for " + register.getRegisterDate() + " ---");
        if (register.getAllStudents().isEmpty()) {
            System.out.println("No students found in this register.");
            return;
        }

        // Sort students by name for consistent display
        List<Student> sortedStudents = register.getAllStudents().values().stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());

        for (Student student : sortedStudents) {
            System.out.println("\nStudent ID: " + student.getStudentId() + ", Name: " + student.getName());
            System.out.println("  Attendance: " + student.getAttendanceStatus());
            if (!student.getJustification().isEmpty()) {
                System.out.println("  Justification: " + student.getJustification());
            }
            if (!student.getDisciplinaryNotes().isEmpty()) {
                System.out.println("  Disciplinary Notes:");
                for (String note : student.getDisciplinaryNotes()) {
                    System.out.println("    - " + note);
                }
            } else {
                System.out.println("  No disciplinary notes.");
            }
        }
        System.out.println("------------------------------------------");
    }

    /**
     * Facilitates user interaction to manage (view/add) justifications and disciplinary notes
     * for a student within a selected register.
     *
     * @param register The Register object containing the students.
     */
    private static void manageStudentNotesInteraction(Register register) {
        System.out.println("\n--- Manage Student Notes for " + register.getRegisterDate() + " ---");
        System.out.print("Enter the Student ID to manage notes for (or 'back' to return): ");
        String studentId = scanner.nextLine().trim();

        if ("back".equalsIgnoreCase(studentId)) {
            return;
        }

        Student studentToManage = register.getStudentById(studentId);
        if (studentToManage == null) {
            System.out.println("Student with ID '" + studentId + "' not found in this register.");
            return;
        }

        System.out.println("\nManaging notes for: " + studentToManage.getName() + " (ID: " + studentToManage.getStudentId() + ")");
        System.out.println("Current Justification: " + (studentToManage.getJustification().isEmpty() ? "None" : studentToManage.getJustification()));
        System.out.println("Current Disciplinary Notes: " + (studentToManage.getDisciplinaryNotes().isEmpty() ? "None" : String.join("; ", studentToManage.getDisciplinaryNotes())));

        boolean managing = true;
        while (managing) {
            System.out.println("\nOptions:");
            System.out.println("1. Update Justification");
            System.out.println("2. Add Disciplinary Note");
            System.out.println("3. View Current Details");
            System.out.println("4. Back to Register View");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Enter new justification (leave empty to clear): ");
                        String newJustification = scanner.nextLine().trim();
                        studentToManage.setJustification(newJustification);
                        System.out.println("Justification updated.");
                        break;
                    case 2:
                        System.out.print("Enter new disciplinary note: ");
                        String newNote = scanner.nextLine().trim();
                        if (!newNote.isEmpty()) {
                            studentToManage.addDisciplinaryNote(newNote);
                            System.out.println("Disciplinary note added.");
                        } else {
                            System.out.println("Note cannot be empty.");
                        }
                        break;
                    case 3:
                        System.out.println("\n--- Current Details for " + studentToManage.getName() + " ---");
                        System.out.println("  Attendance: " + studentToManage.getAttendanceStatus());
                        System.out.println("  Justification: " + (studentToManage.getJustification().isEmpty() ? "None" : studentToManage.getJustification()));
                        System.out.println("  Disciplinary Notes: " + (studentToManage.getDisciplinaryNotes().isEmpty() ? "None" : String.join("; ", studentToManage.getDisciplinaryNotes())));
                        System.out.println("-------------------------------------------------");
                        break;
                    case 4:
                        managing = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}