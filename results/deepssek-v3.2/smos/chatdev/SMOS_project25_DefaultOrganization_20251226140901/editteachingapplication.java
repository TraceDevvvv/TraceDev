/**
 * Main application class for the Edit Teaching use case.
 * Simulates administrator workflow for editing teaching details.
 * Handles data validation, server connection, and operation interruption.
 */
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class EditTeachingApplication {
    private static List<Teaching> teachingArchive = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean serverConnected = true;
    private static boolean operationInterrupted = false;
    public static void main(String[] args) {
        initializeArchive();
        System.out.println("=== Edit Teaching Application ===");
        System.out.println("Administrator logged in.");
        // Simulate server connection status randomly
        simulateServerConnection();
        try {
            displayTeachingList();
            System.out.print("Enter teaching index to edit (0-" + (teachingArchive.size() - 1) + ") or 'abort' to interrupt: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("abort")) {
                handleInterruption();
                return;
            }
            int index;
            try {
                index = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input format. Operation terminated.");
                return;
            }
            if (index >= 0 && index < teachingArchive.size()) {
                Teaching teaching = teachingArchive.get(index);
                displayTeachingDetails(teaching);
                editTeachingDetails(teaching);
            } else {
                System.out.println("Invalid index selected. Operation terminated.");
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    private static void initializeArchive() {
        teachingArchive.add(new Teaching("Dr. Smith", "Mathematics", 40));
        teachingArchive.add(new Teaching("Prof. Johnson", "Physics", 35));
        teachingArchive.add(new Teaching("Ms. Williams", "Chemistry", 30));
    }
    private static void simulateServerConnection() {
        // Randomly disconnect server with 20% probability for simulation
        if (ThreadLocalRandom.current().nextInt(1, 101) <= 20) {
            serverConnected = false;
            System.out.println("Warning: Connection to the SMOS server is interrupted.");
        }
    }
    private static void displayTeachingList() {
        System.out.println("\nCurrent Teachings:");
        for (int i = 0; i < teachingArchive.size(); i++) {
            System.out.println(i + ": " + teachingArchive.get(i));
        }
    }
    private static void displayTeachingDetails(Teaching teaching) {
        System.out.println("\n=== Teaching Details ===");
        System.out.println("Teacher: " + teaching.getTeacher());
        System.out.println("Subject: " + teaching.getSubject());
        System.out.println("Hours: " + teaching.getHours());
        System.out.println("=====================\n");
    }
    private static void editTeachingDetails(Teaching teaching) {
        System.out.println("Edit teaching details (press Enter to keep current value, type 'abort' to interrupt):");
        System.out.print("Teacher [" + teaching.getTeacher() + "]: ");
        String teacherInput = readLineWithInterruption();
        if (operationInterrupted) return;
        if (!teacherInput.trim().isEmpty()) {
            teaching.setTeacher(teacherInput);
        }
        System.out.print("Subject [" + teaching.getSubject() + "]: ");
        String subjectInput = readLineWithInterruption();
        if (operationInterrupted) return;
        if (!subjectInput.trim().isEmpty()) {
            teaching.setSubject(subjectInput);
        }
        System.out.print("Hours [" + teaching.getHours() + "]: ");
        String hoursInput = readLineWithInterruption();
        if (operationInterrupted) return;
        if (!hoursInput.trim().isEmpty()) {
            try {
                int hours = Integer.parseInt(hoursInput);
                teaching.setHours(hours);
            } catch (NumberFormatException e) {
                System.out.println("Invalid hours format. Keeping original value.");
            }
        }
        System.out.print("Click 'Save' button? (yes/no/abort): ");
        String saveChoice = readLineWithInterruption();
        if (operationInterrupted) return;
        if (saveChoice.equalsIgnoreCase("yes")) {
            saveTeachingChanges(teaching);
        } else if (saveChoice.equalsIgnoreCase("abort")) {
            handleInterruption();
        } else {
            System.out.println("Operation interrupted by administrator.");
        }
    }
    private static String readLineWithInterruption() {
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("abort")) {
            handleInterruption();
        }
        return input;
    }
    private static void handleInterruption() {
        operationInterrupted = true;
        System.out.println("Operation interrupted by the administrator.");
    }
    private static void saveTeachingChanges(Teaching teaching) {
        if (!serverConnected) {
            System.out.println("Error: Connection to the SMOS server interrupted.");
            return;
        }
        try {
            if (!validateTeachingData(teaching)) {
                handleDataError();
                return;
            }
            System.out.println("\nValidating and saving changes...");
            System.out.println("Teaching updated successfully!");
            System.out.println("\n=== Updated Teachings List ===");
            displayTeachingList();
        } catch (Exception e) {
            System.out.println("Error during save operation: " + e.getMessage());
        }
    }
    private static boolean validateTeachingData(Teaching teaching) {
        System.out.println("Validating teaching data...");
        if (teaching.getTeacher() == null || teaching.getTeacher().trim().isEmpty()) {
            System.out.println("Validation Error: Teacher name cannot be empty.");
            return false;
        }
        if (teaching.getSubject() == null || teaching.getSubject().trim().isEmpty()) {
            System.out.println("Validation Error: Subject cannot be empty.");
            return false;
        }
        if (teaching.getHours() <= 0) {
            System.out.println("Validation Error: Hours must be a positive integer.");
            return false;
        }
        return true;
    }
    private static void handleDataError() {
        System.out.println("=== ErrorDati Use Case Activated ===");
        System.out.println("The user is notified about data errors.");
        System.out.println("Please correct the invalid data and try again.");
    }
}