import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Main class to demonstrate the "Assign/Remove Students to a Parent" use case.
 * This class simulates the interaction of an Administrator with the Student Management System
 * to manage student-parent associations.
 */
public class Main {

    public static void main(String[] args) {
        // Initialize the Student Management System
        StudentManagementSystem sms = new StudentManagementSystem();

        // --- Setup Sample Data ---
        // Create some students
        Student s1 = new Student("S001", "Alice Smith");
        Student s2 = new Student("S002", "Bob Johnson");
        Student s3 = new Student("S003", "Charlie Brown");
        Student s4 = new Student("S004", "Diana Prince");
        Student s5 = new Student("S005", "Eve Adams");

        // Add students to the system
        sms.addStudent(s1);
        sms.addStudent(s2);
        sms.addStudent(s3);
        sms.addStudent(s4);
        sms.addStudent(s5);

        // Create some parents
        Parent p1 = new Parent("P001", "John Doe");
        Parent p2 = new Parent("P002", "Jane Doe");

        // Add parents to the system
        sms.addParent(p1);
        sms.addParent(p2);

        // Initially associate some students with p1
        p1.addAssociatedStudent(s1);
        p1.addAssociatedStudent(s2);
        System.out.println("\n--- Initial State ---");
        sms.viewParentDetails(p1.getParentId());
        System.out.println("---------------------\n");

        // Create an Administrator
        Administrator admin = new Administrator("admin", "adminpass");
        Scanner scanner = new Scanner(System.in);

        // --- Preconditions Simulation ---
        System.out.println("Simulating Administrator Login...");
        boolean loggedIn = admin.login("admin", "adminpass");

        if (loggedIn) {
            System.out.println("\n--- Precondition Met: Administrator is logged in. ---");

            // Simulate "viewdetTailsente" and "click on the 'Parentela' button"
            String targetParentId = p1.getParentId(); // Let's work with parent P001
            Parent currentParent = sms.viewParentDetails(targetParentId);

            if (currentParent != null) {
                System.out.println("\n--- System: Displays the child management form for " + currentParent.getName() + " ---");
                sms.displayAllAvailableStudents(); // Show all students for selection

                // --- User: Select students to be assigned or removed ---
                // Scenario 1: Assign new students
                System.out.println("\n--- Scenario 1: Assigning Students ---");
                System.out.println("Enter student IDs to assign to " + currentParent.getName() + " (comma-separated, e.g., S003,S004):");
                String studentsToAssignInput = scanner.nextLine();
                Set<String> studentIdsToAssign = new HashSet<>();
                if (!studentsToAssignInput.trim().isEmpty()) {
                    Arrays.stream(studentsToAssignInput.split(","))
                          .map(String::trim)
                          .filter(s -> !s.isEmpty())
                          .forEach(studentIdsToAssign::add);
                }

                // --- User: Click on the "Send" button (for assignment) ---
                System.out.println("\n--- User: Clicking 'Send' to assign students ---");
                try {
                    sms.assignStudentsToParent(admin, targetParentId, studentIdsToAssign);
                    System.out.println("\n--- Postcondition: Student associations updated. ---");
                    sms.viewParentDetails(targetParentId); // Display updated details
                } catch (IllegalStateException e) {
                    System.err.println("Operation failed: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.err.println("Input error: " + e.getMessage());
                }


                // Scenario 2: Remove students
                System.out.println("\n--- Scenario 2: Removing Students ---");
                System.out.println("Enter student IDs to remove from " + currentParent.getName() + " (comma-separated, e.g., S001):");
                String studentsToRemoveInput = scanner.nextLine();
                Set<String> studentIdsToRemove = new HashSet<>();
                if (!studentsToRemoveInput.trim().isEmpty()) {
                    Arrays.stream(studentsToRemoveInput.split(","))
                          .map(String::trim)
                          .filter(s -> !s.isEmpty())
                          .forEach(studentIdsToRemove::add);
                }

                // --- User: Click on the "Send" button (for removal) ---
                System.out.println("\n--- User: Clicking 'Send' to remove students ---");
                try {
                    sms.removeStudentsFromParent(admin, targetParentId, studentIdsToRemove);
                    System.out.println("\n--- Postcondition: Student associations updated. ---");
                    sms.viewParentDetails(targetParentId); // Display updated details
                } catch (IllegalStateException e) {
                    System.err.println("Operation failed: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.err.println("Input error: " + e.getMessage());
                }

            } else {
                System.out.println("Could not find parent with ID: " + targetParentId + ". Cannot proceed with student management.");
            }

            // --- Postcondition: Administrator interrupts the connection to the SMOS server ---
            System.out.println("\n--- Postcondition: Administrator logging out. ---");
            admin.logout();
        } else {
            System.out.println("Administrator login failed. Cannot proceed with student management.");
        }

        scanner.close();
        System.out.println("\nDemonstration finished.");
    }
}