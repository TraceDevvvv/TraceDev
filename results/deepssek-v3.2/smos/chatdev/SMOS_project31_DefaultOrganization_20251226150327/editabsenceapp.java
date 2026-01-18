import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
/**
 * Main application class for EditAbsence use case.
 * This simulates the administrator interface for changing absences.
 */
public class EditAbsenceApp {
    public static void main(String[] args) {
        System.out.println("=== Edit Absence System ===\n");
        // Simulate administrator login
        System.out.println("Administrator logged in successfully.\n");
        // Initialize components
        ServerConnection server = new ServerConnection();
        // Use demo email service (no actual SMTP required)
        EmailService emailService = new EmailService("smtp.example.com", "587", 
                                                   "admin@school.edu", "demo123");
        AbsenceManager manager = new AbsenceManager(server, emailService);
        // Add sample data
        manager.addSampleAbsences();
        Scanner scanner = new Scanner(System.in);
        try {
            // Step 1: Select date
            System.out.print("Enter date to view absences (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine();
            LocalDate selectedDate;
            try {
                selectedDate = LocalDate.parse(dateInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Using today's date.");
                selectedDate = LocalDate.now();
            }
            // Update screen based on selected date
            System.out.println("\n=== Absences for " + selectedDate + " ===");
            List<Absence> absencesForDate = manager.getAbsencesForDate(selectedDate);
            if (absencesForDate.isEmpty()) {
                System.out.println("No absences found for selected date.");
                System.out.println("Adding new absence record...");
                System.out.print("Enter student ID: ");
                String studentId = scanner.nextLine();
                System.out.print("Enter student name: ");
                String studentName = scanner.nextLine();
                System.out.print("Enter parent email: ");
                String parentEmail = scanner.nextLine();
                Student newStudent = new Student(studentId, studentName, parentEmail);
                Absence newAbsence = new Absence(newStudent, selectedDate, true);
                System.out.print("\nMark student as (1) Present or (2) Absent? ");
                int choice = Integer.parseInt(scanner.nextLine());
                boolean newStatus = (choice == 1);
                // Step 2 & 3: Change absence and save
                manager.changeAbsence(newAbsence, newStatus);
            } else {
                // Display existing absences
                for (int i = 0; i < absencesForDate.size(); i++) {
                    Absence absence = absencesForDate.get(i);
                    Student student = absence.getStudent();
                    System.out.println((i + 1) + ". " + student.getName() + 
                                     " (" + student.getStudentId() + "): " + 
                                     (absence.isPresent() ? "Present" : "Absent"));
                }
                System.out.print("\nSelect student number to modify (1-" + 
                               absencesForDate.size() + "): ");
                int studentIndex = Integer.parseInt(scanner.nextLine()) - 1;
                if (studentIndex >= 0 && studentIndex < absencesForDate.size()) {
                    Absence selectedAbsence = absencesForDate.get(studentIndex);
                    System.out.print("\nChange to (1) Present or (2) Absent? ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    boolean newStatus = (choice == 1);
                    // Step 2 & 3: Change absence and save
                    manager.changeAbsence(selectedAbsence, newStatus);
                } else {
                    System.out.println("Invalid selection.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter a valid number.");
        } catch (RuntimeException e) {
            System.out.println("Operation interrupted: " + e.getMessage());
            System.out.println("Returning to registry screen...");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            System.out.println("\n=== System remains on registry screen ===");
            System.out.println("Ready for next operation...");
            scanner.close();
        }
    }
}