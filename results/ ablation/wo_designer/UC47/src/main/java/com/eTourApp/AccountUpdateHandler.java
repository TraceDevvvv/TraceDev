import java.util.Scanner;

/**
 * Handles the flow for updating tourist account information.
 * Implements the steps described in the use case.
 */
public class AccountUpdateHandler {
    private TouristService touristService;
    private Scanner scanner;
    private String currentUser;

    /**
     * Constructor initializes service and scanner.
     */
    public AccountUpdateHandler() {
        touristService = new TouristService();
        scanner = new Scanner(System.in);
        currentUser = null;
    }

    /**
     * Main method to run the account update flow.
     * This method orchestrates the entire process from authentication to update.
     */
    public void runUpdateFlow() {
        System.out.println("=== Tourist Account Update System ===");
        
        // Simulate authentication (Entry Conditions)
        if (!authenticateUser()) {
            System.out.println("Authentication failed. Exiting.");
            return;
        }

        // Step 1: Tourist accesses the functionality
        System.out.println("\nAccessing account modification functionality...");

        // Step 2 & 3: System loads and displays data
        Tourist currentTourist = touristService.getTourist(currentUser);
        if (currentTourist == null) {
            System.out.println("Error: Tourist data not found.");
            return;
        }
        displayCurrentData(currentTourist);

        // Step 4: Tourist edits fields
        Tourist updatedTourist = editTouristData(currentTourist);
        if (updatedTourist == null) {
            System.out.println("Operation cancelled by user.");
            return;
        }

        // Step 5: Tourist submits form (implicitly done in editTouristData)
        // Step 6: System checks modified information
        if (!validateUpdatedData(updatedTourist)) {
            System.out.println("Validation failed. Please correct the data.");
            return;
        }

        // Step 7: System asks for confirmation
        if (!confirmChanges()) {
            System.out.println("Update cancelled by user.");
            return;
        }

        // Step 8: Tourist confirms (handled in confirmChanges)
        // Step 9: System stores the modified data
        boolean success = touristService.updateTouristInfo(currentUser, updatedTourist);
        
        // Exit Conditions
        if (success) {
            System.out.println("\n✅ Success: Account information updated successfully!");
            System.out.println("Updated details:");
            displayCurrentData(updatedTourist);
        } else {
            System.out.println("\n❌ Error: Failed to update account information.");
        }
        
        scanner.close();
    }

    /**
     * Handles user authentication.
     * @return true if authentication successful, false otherwise.
     */
    private boolean authenticateUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine(); // In real application, use secure input
        
        boolean authenticated = touristService.authenticate(username, password);
        if (authenticated) {
            currentUser = username;
            System.out.println("Authentication successful. Welcome, " + username + "!");
        }
        return authenticated;
    }

    /**
     * Displays current tourist data.
     * @param tourist The Tourist object to display.
     */
    private void displayCurrentData(Tourist tourist) {
        System.out.println("\nCurrent Account Information:");
        System.out.println("Full Name: " + tourist.getFullName());
        System.out.println("Email: " + tourist.getEmail());
        System.out.println("Phone Number: " + tourist.getPhoneNumber());
        System.out.println("(Password: ********)");
    }

    /**
     * Guides the tourist through editing their data.
     * @param currentData The current tourist data.
     * @return Updated Tourist object, or null if cancelled.
     */
    private Tourist editTouristData(Tourist currentData) {
        System.out.println("\nEdit your information (press Enter to keep current value):");
        
        Tourist updated = new Tourist(
            currentData.getUsername(),
            "", // Password handled separately
            currentData.getFullName(),
            currentData.getEmail(),
            currentData.getPhoneNumber()
        );

        // Edit full name
        System.out.print("Full Name [" + currentData.getFullName() + "]: ");
        String input = scanner.nextLine();
        if (!input.trim().isEmpty()) {
            updated.setFullName(input);
        }

        // Edit email
        System.out.print("Email [" + currentData.getEmail() + "]: ");
        input = scanner.nextLine();
        if (!input.trim().isEmpty()) {
            updated.setEmail(input);
        }

        // Edit phone number
        System.out.print("Phone Number [" + currentData.getPhoneNumber() + "]: ");
        input = scanner.nextLine();
        if (!input.trim().isEmpty()) {
            updated.setPhoneNumber(input);
        }

        // Edit password (optional)
        System.out.print("New Password (leave empty to keep current): ");
        input = scanner.nextLine();
        if (!input.trim().isEmpty()) {
            updated.setPassword(input);
        } else {
            updated.setPassword(currentData.getPassword());
        }

        System.out.println("\nReview your changes:");
        displayCurrentData(updated);
        System.out.print("\nDo you want to submit these changes? (yes/no): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("yes")) {
            return updated;
        } else {
            return null;
        }
    }

    /**
     * Validates the updated tourist data.
     * @param tourist The Tourist object to validate.
     * @return true if data is valid, false otherwise.
     */
    private boolean validateUpdatedData(Tourist tourist) {
        // Basic validation rules
        if (tourist.getFullName() == null || tourist.getFullName().trim().isEmpty()) {
            System.out.println("Error: Full name cannot be empty.");
            return false;
        }
        
        if (tourist.getEmail() == null || !tourist.getEmail().contains("@")) {
            System.out.println("Error: Invalid email address.");
            return false;
        }
        
        if (tourist.getPhoneNumber() == null || tourist.getPhoneNumber().trim().isEmpty()) {
            System.out.println("Error: Phone number cannot be empty.");
            return false;
        }
        
        if (tourist.getPassword() == null || tourist.getPassword().length() < 6) {
            System.out.println("Error: Password must be at least 6 characters.");
            return false;
        }
        
        return true;
    }

    /**
     * Asks for final confirmation before saving changes.
     * @return true if confirmed, false otherwise.
     */
    private boolean confirmChanges() {
        System.out.print("\n⚠️  Confirm you want to save these changes? (yes/no): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    /**
     * Main method to run the application.
     */
    public static void main(String[] args) {
        AccountUpdateHandler handler = new AccountUpdateHandler();
        handler.runUpdateFlow();
    }
}