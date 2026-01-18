import java.util.List;
import java.util.Scanner;

/**
 * Main class to demonstrate the Delete Teaching use case.
 * This program simulates the deletion of a teaching from the archive by an administrator.
 * It follows the sequence of events described in the use case.
 */
public class Main {

    /**
     * Main entry point of the application.
     * Simulates the complete flow of the delete teaching use case.
     */
    public static void main(String[] args) {
        System.out.println("=== Delete Teaching Use Case Demo ===");
        System.out.println();
        
        // Initialize server connection
        SMOSServer server = new SMOSServer();
        server.connect();
        
        // Create archive with sample teachings
        TeachingArchive archive = new TeachingArchive();
        initializeArchiveWithSampleData(archive);
        
        // Display initial list of teachings
        System.out.println("Initial list of teachings:");
        displayTeachings(archive.getAllTeachings());
        System.out.println();
        
        // Simulate administrator login
        Administrator admin = new Administrator();
        boolean isLoggedIn = admin.login();
        
        if (!isLoggedIn) {
            System.out.println("Error: Administrator login failed. Cannot proceed with deletion.");
            return;
        }
        
        System.out.println("Administrator login successful.");
        System.out.println();
        
        // Administrator needs to view detailed information before deleting
        // This simulates the precondition of viewing detailed information
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the teaching to view details and delete (1-3): ");
        int teachingId = scanner.nextInt();
        
        // Display detailed information (simulating the 'displaydeddailsignment' use case)
        displayDetailedTeachingInformation(archive, teachingId);
        
        // Simulate clicking the "Delete" button
        System.out.println();
        System.out.println("Clicking the 'Delete' button...");
        System.out.println();
        
        try {
            // Step 1: Eliminate teaching from the archive
            System.out.println("[Step 1] Eliminating teaching from the archive...");
            boolean deletionSuccessful = archive.deleteTeaching(teachingId, admin);
            
            if (deletionSuccessful) {
                System.out.println("Teaching with ID " + teachingId + " has been successfully deleted.");
                
                // Step 2: Display the list of updated teachings
                System.out.println("[Step 2] Displaying the list of updated teachings:");
                displayTeachings(archive.getAllTeachings());
                
                // Postcondition: Connection to the SMOS server interrupted
                System.out.println();
                System.out.println("[Postcondition] Interrupting connection to SMOS server...");
                server.disconnect();
                
                System.out.println();
                System.out.println("=== Use Case Completed Successfully ===");
                System.out.println("Postconditions met:");
                System.out.println("1. The user has eliminated a teaching");
                System.out.println("2. Connection to the SMOS server interrupted");
            } else {
                System.out.println("Failed to delete teaching with ID " + teachingId + ".");
                System.out.println("Teaching might not exist or administrator doesn't have permission.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("Security Error: " + e.getMessage());
        }
        
        scanner.close();
    }
    
    /**
     * Initialize the archive with some sample teaching data.
     * 
     * @param archive The TeachingArchive to populate with sample data
     */
    private static void initializeArchiveWithSampleData(TeachingArchive archive) {
        Teaching teaching1 = new Teaching(1, "Mathematics 101", "Dr. Smith", "Introduction to Calculus", "2024-09-01", "2024-12-15");
        Teaching teaching2 = new Teaching(2, "Physics 201", "Prof. Johnson", "Classical Mechanics", "2024-09-15", "2024-12-20");
        Teaching teaching3 = new Teaching(3, "Computer Science 101", "Dr. Williams", "Introduction to Programming", "2024-08-25", "2024-12-10");
        
        archive.addTeaching(teaching1);
        archive.addTeaching(teaching2);
        archive.addTeaching(teaching3);
        
        System.out.println("Teaching archive initialized with " + archive.getAllTeachings().size() + " teachings.");
    }
    
    /**
     * Display all teachings in a formatted manner.
     * 
     * @param teachings List of teachings to display
     */
    private static void displayTeachings(List<Teaching> teachings) {
        if (teachings.isEmpty()) {
            System.out.println("No teachings available.");
            return;
        }
        
        System.out.println("+----+----------------------+----------------+--------------------------------+-------------+");
        System.out.println("| ID | Course Name          | Instructor     | Description                    | Start Date  |");
        System.out.println("+----+----------------------+----------------+--------------------------------+-------------+");
        
        for (Teaching teaching : teachings) {
            System.out.printf("| %-2d | %-20s | %-14s | %-30s | %-11s |%n",
                teaching.getId(),
                teaching.getCourseName(),
                teaching.getInstructor(),
                teaching.getDescription().substring(0, Math.min(teaching.getDescription().length(), 30)),
                teaching.getStartDate());
        }
        
        System.out.println("+----+----------------------+----------------+--------------------------------+-------------+");
        System.out.println("Total teachings: " + teachings.size());
    }
    
    /**
     * Display detailed information for a specific teaching.
     * Simulates the 'displaydeddailsignment' use case mentioned in preconditions.
     * 
     * @param archive The TeachingArchive containing teachings
     * @param teachingId The ID of the teaching to display
     */
    private static void displayDetailedTeachingInformation(TeachingArchive archive, int teachingId) {
        Teaching teaching = archive.getTeachingById(teachingId);
        
        if (teaching == null) {
            System.out.println("Teaching with ID " + teachingId + " not found.");
            return;
        }
        
        System.out.println("=== Detailed Teaching Information ===");
        System.out.println("Teaching ID: " + teaching.getId());
        System.out.println("Course Name: " + teaching.getCourseName());
        System.out.println("Instructor: " + teaching.getInstructor());
        System.out.println("Description: " + teaching.getDescription());
        System.out.println("Start Date: " + teaching.getStartDate());
        System.out.println("End Date: " + teaching.getEndDate());
        System.out.println("=====================================");
    }
}