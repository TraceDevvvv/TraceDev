import java.util.Scanner;

/**
 * Main class for the ViewTeachingsList use case.
 * This program simulates administrator interaction to view the list of teachings from an archive.
 * It handles the administrator's actions and simulates SMOS server connection interruption.
 * 
 * The program flow:
 * 1. Simulate administrator login and role verification
 * 2. Wait for administrator to click "Management Management" button
 * 3. Look for archive and teacher screen
 * 4. Load and display teachings from archive
 * 5. Handle connection interruption and edge cases
 * 
 * @author Administrator System
 * @version 1.0
 */
public class ViewTeachingsList {
    
    /**
     * Main method - entry point of the program.
     * Simulates the complete ViewTeachingsList use case.
     * 
     * @param args command line arguments (optional: archive filename)
     */
    public static void main(String[] args) {
        System.out.println("=== ADMINISTRATOR SYSTEM - VIEW TEACHINGS LIST ===\n");
        
        // Step 1: Verify administrator is logged in
        if (!verifyAdministratorLogin()) {
            System.out.println("Access denied. Only administrators can access this feature.");
            System.exit(1);
        }
        
        // Step 2: Simulate clicking the 'Management Management' button
        System.out.println("Administrator logged in successfully.");
        System.out.println("Clicking 'Management Management' button...\n");
        simulateButtonClick();
        
        // Step 3: Look for archive and teacher screen
        System.out.println("Looking for archive and teacher screen...");
        
        // Get archive filename from args or use default
        String archiveFilename = "teachings_archive.txt";
        if (args.length > 0) {
            archiveFilename = args[0];
            System.out.println("Using custom archive file: " + archiveFilename);
        } else {
            System.out.println("Using default archive file: " + archiveFilename);
        }
        
        // Step 4: Create teaching archive and load teachings
        TeachingArchive archive = new TeachingArchive(archiveFilename);
        
        // Display loading status
        System.out.println("\nLoading teachings from archive...");
        System.out.println("Connecting to SMOS server...");
        
        boolean loadSuccess = archive.loadTeachings();
        
        // Step 5: Handle postconditions - show list of teachings and check connection interruption
        System.out.println("\n=== TEACHINGS MANAGEMENT VIEW ===");
        
        if (loadSuccess) {
            System.out.println("Successfully connected to SMOS server.");
            System.out.println("Teachings archive loaded successfully.\n");
            
            // Display the list of teachings
            archive.displayTeachings();
            
            // Show statistics
            System.out.println("Total teachings found: " + archive.getTeachingCount());
            
            // Check if connection was interrupted during loading (shouldn't happen if load succeeded)
            if (archive.isConnectionInterrupted()) {
                System.out.println("Warning: SMOS server connection was interrupted during loading.");
            }
        } else {
            System.out.println("\nERROR: Failed to load teachings from archive.");
            
            if (archive.isConnectionInterrupted()) {
                System.out.println("Postcondition triggered: Connection to the SMOS server interrupted.");
                System.out.println("This is expected behavior according to the use case postconditions.");
            } else {
                System.out.println("Possible reasons:");
                System.out.println("- Archive file not found: " + archiveFilename);
                System.out.println("- Archive file is empty or corrupted");
                System.out.println("- Invalid data format in archive");
            }
            
            // Try to load again with more verbose output
            System.out.println("\nAttempting to diagnose the issue...");
            diagnoseArchiveLoading(archiveFilename);
        }
        
        // Step 6: Provide administrator options
        provideAdministratorOptions(archive);
        
        System.out.println("\n=== ViewTeachingsList use case completed ===");
        System.out.println("Thank you for using the Administrator System.\n");
    }
    
    /**
     * Simulates administrator login verification.
     * In a real system, this would check credentials and user role.
     * 
     * @return true if user is authenticated as administrator, false otherwise
     */
    private static boolean verifyAdministratorLogin() {
        // Simulate checking administrator role
        // In production, this would query a user database or authentication service
        System.out.println("Verifying administrator credentials...");
        
        // For simulation purposes, we'll assume the user is logged in as administrator
        // according to the preconditions in the use case
        return true;
    }
    
    /**
     * Simulates the button click action with a small delay.
     */
    private static void simulateButtonClick() {
        try {
            System.out.print("Simulating button click ");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
            Thread.sleep(500);
            System.out.println(" Done!\n");
        } catch (InterruptedException e) {
            System.out.println("Button click simulation interrupted.");
        }
    }
    
    /**
     * Provides additional diagnostic information when archive loading fails.
     * 
     * @param filename the name of the archive file to diagnose
     */
    private static void diagnoseArchiveLoading(String filename) {
        System.out.println("\n--- DIAGNOSTIC INFORMATION ---");
        
        // Check if file exists
        java.io.File file = new java.io.File(filename);
        if (!file.exists()) {
            System.out.println("❌ Archive file does not exist: " + filename);
            System.out.println("   Current directory: " + System.getProperty("user.dir"));
            System.out.println("   Creating a sample archive file for testing...");
            
            // Create a sample file for testing
            createSampleArchiveFile(filename);
            System.out.println("   Sample file created. Please run the program again.");
            return;
        }
        
        // Check file size
        long fileSize = file.length();
        System.out.println("✅ File exists: " + filename);
        System.out.println("   File size: " + fileSize + " bytes");
        
        if (fileSize == 0) {
            System.out.println("⚠️  File is empty. No teachings to display.");
            System.out.println("   Add teaching entries in the format: subject|teacher|date|description");
            return;
        }
        
        // Try to read and display file content
        System.out.println("\n   File content preview (first 5 lines):");
        try (java.util.Scanner fileScanner = new java.util.Scanner(file)) {
            int lineCount = 0;
            while (fileScanner.hasNextLine() && lineCount < 5) {
                String line = fileScanner.nextLine();
                System.out.println("   Line " + (lineCount + 1) + ": " + 
                                 (line.length() > 50 ? line.substring(0, 47) + "..." : line));
                lineCount++;
            }
            
            if (lineCount == 0) {
                System.out.println("   (File appears to be empty)");
            }
        } catch (java.io.IOException e) {
            System.out.println("   Error reading file: " + e.getMessage());
        }
        
        System.out.println("--------------------------------\n");
    }
    
    /**
     * Creates a sample archive file for testing purposes.
     * 
     * @param filename the name of the file to create
     */
    private static void createSampleArchiveFile(String filename) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(filename)) {
            writer.println("Mathematics|Dr. Smith|2024-01-15|Introduction to Calculus");
            writer.println("Physics|Prof. Johnson|2024-01-22|Classical Mechanics Lecture");
            writer.println("Computer Science|Dr. Lee|2024-01-29|Data Structures and Algorithms");
            writer.println("History|Prof. Davis|2024-02-05|World History: 20th Century");
            writer.println("Literature|Dr. Miller|2024-02-12|Modern American Poetry");
            System.out.println("   Sample archive file created with 5 sample teachings.");
        } catch (java.io.IOException e) {
            System.out.println("   Error creating sample file: " + e.getMessage());
        }
    }
    
    /**
     * Provides additional options for the administrator after viewing teachings.
     * 
     * @param archive the TeachingArchive instance
     */
    private static void provideAdministratorOptions(TeachingArchive archive) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n=== ADMINISTRATOR OPTIONS ===");
        System.out.println("1. Reload teachings from archive");
        System.out.println("2. Display teachings count");
        System.out.println("3. Search for teachings by subject");
        System.out.println("4. Exit");
        System.out.print("\nEnter your choice (1-4): ");
        
        try {
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    // Reload teachings
                    System.out.println("\nReloading teachings...");
                    archive.resetConnectionStatus();
                    if (archive.loadTeachings()) {
                        System.out.println("Teachings reloaded successfully!");
                        archive.displayTeachings();
                    } else {
                        System.out.println("Failed to reload teachings.");
                        if (archive.isConnectionInterrupted()) {
                            System.out.println("SMOS server connection interrupted.");
                        }
                    }
                    break;
                    
                case 2:
                    // Display count
                    System.out.println("\nTotal teachings in archive: " + archive.getTeachingCount());
                    break;
                    
                case 3:
                    // Search by subject
                    scanner.nextLine(); // Consume newline
                    System.out.print("\nEnter subject to search: ");
                    String searchSubject = scanner.nextLine().trim().toLowerCase();
                    
                    System.out.println("\nSearch results for subject containing: " + searchSubject);
                    System.out.println("----------------------------------------");
                    
                    boolean found = false;
                    for (Teaching teaching : archive.getTeachings()) {
                        if (teaching.getSubject().toLowerCase().contains(searchSubject)) {
                            System.out.println("• " + teaching);
                            found = true;
                        }
                    }
                    
                    if (!found) {
                        System.out.println("No teachings found for subject: " + searchSubject);
                    }
                    break;
                    
                case 4:
                    System.out.println("Exiting administrator options...");
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please enter 1-4.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        } finally {
            scanner.close();
        }
    }
}