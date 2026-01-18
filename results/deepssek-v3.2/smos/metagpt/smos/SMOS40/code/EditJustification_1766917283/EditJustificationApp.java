import java.time.LocalDate;
import java.util.Scanner;

/**
 * Main application class for EditJustification use case.
 * Demonstrates the complete flow of editing a justification by an administrator.
 */
public class EditJustificationApp {
    
    public static void main(String[] args) {
        System.out.println("=== Edit Justification System ===");
        
        // Create SMOS server connection
        SMOSServerConnection serverConnection = new SMOSServerConnection();
        
        // Create administrator
        Administrator admin = new Administrator("admin1", "Admin User");
        
        // Simulate administrator login (precondition)
        if (!admin.login()) {
            System.out.println("Login failed. Exiting system.");
            return;
        }
        
        // Simulate viewing justification details (precondition)
        System.out.println("\n--- Viewing Justification Details ---");
        Justification justification = new Justification(101, LocalDate.of(2023, 12, 15), 
                                                      "Original justification text for case #123");
        justification.displayDetails();
        
        // Create edit form with current justification
        EditJustificationForm editForm = new EditJustificationForm(justification);
        
        // Display form for editing
        System.out.println("\n--- Edit Justification Form ---");
        editForm.displayForm();
        
        // Simulate user changing fields (Event 1)
        System.out.println("\n--- Changing Fields ---");
        
        // In a real application, this would come from GUI input
        // For demonstration, we'll simulate input changes
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter new date (YYYY-MM-DD) or press Enter to keep current: ");
        String newDateStr = scanner.nextLine();
        if (!newDateStr.trim().isEmpty()) {
            try {
                LocalDate newDate = LocalDate.parse(newDateStr);
                editForm.setDate(newDate);
                System.out.println("Date updated to: " + newDate);
            } catch (Exception e) {
                System.out.println("Invalid date format. Keeping current date.");
            }
        }
        
        System.out.print("Enter new justification text or press Enter to keep current: ");
        String newText = scanner.nextLine();
        if (!newText.trim().isEmpty()) {
            editForm.setJustificationText(newText);
            System.out.println("Justification text updated.");
        }
        
        // Simulate clicking "Save" (Event 2)
        System.out.println("\n--- Saving Changes ---");
        System.out.print("Click 'Save'? (yes/no): ");
        String saveChoice = scanner.nextLine();
        
        if (saveChoice.equalsIgnoreCase("yes")) {
            boolean saved = editForm.saveChanges();
            if (saved) {
                System.out.println("✓ Justification saved successfully!");
                
                // Display updated justification
                System.out.println("\n--- Updated Justification ---");
                justification.displayDetails();
                
                // Postcondition: Return to registry screen
                System.out.println("\n--- Returning to Registry Screen ---");
                System.out.println("Displaying registry screen with all justifications...");
                
                // Postcondition: Interrupt SMOS server connection
                System.out.println("\n--- Interrupting SMOS Server Connection ---");
                serverConnection.interruptConnection();
                
                // Simulate registry screen display
                displayRegistryScreen();
            } else {
                System.out.println("✗ Failed to save justification.");
            }
        } else {
            System.out.println("Save cancelled. No changes made.");
        }
        
        scanner.close();
        System.out.println("\n=== System Exiting ===");
    }
    
    /**
     * Simulates displaying the registry screen.
     * In a real application, this would show a list of all justifications.
     */
    private static void displayRegistryScreen() {
        System.out.println("\n[Registry Screen]");
        System.out.println("========================================");
        System.out.println("ID  | Date       | Description");
        System.out.println("----------------------------------------");
        System.out.println("101 | 2023-12-28 | Updated justification");
        System.out.println("102 | 2023-12-20 | Pending review");
        System.out.println("103 | 2023-12-10 | Approved");
        System.out.println("========================================");
        System.out.println("Total: 3 justifications");
    }
}