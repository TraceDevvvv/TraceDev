/**
 * Main application class that implements the EnterNewAddress use case.
 * This is a console-based simulation of the administrator's workflow.
 * The program handles preconditions, events sequence, and postconditions
 * as specified in the use case.
 */
import java.util.Scanner;
import java.util.List;
public class AddressManagementApp {
    private static boolean isLoggedInAsAdmin = false;
    private static boolean hasViewedAddresses = false;
    private static Scanner scanner = new Scanner(System.in);
    private static ArchiveManager archiveManager = ArchiveManager.getInstance();
    public static void main(String[] args) {
        System.out.println("=== Address Management System ===");
        System.out.println("Use Case: EnterNewAddress");
        // Simulate login precondition
        login();
        // Check if user is logged in as administrator
        if (!isLoggedInAsAdmin) {
            System.out.println("Error: You must be logged in as administrator to continue.");
            scanner.close();
            System.exit(0);
        }
        // Main interaction loop
        boolean continueRunning = true;
        while (continueRunning) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. View addresses (Precondition for 'New address')");
            System.out.println("2. Click 'New address' button");
            System.out.println("3. Search addresses");
            System.out.println("4. View system statistics");
            System.out.println("5. Logout and Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    // Fulfill precondition: View addresses before adding new one
                    viewAddresses();
                    hasViewedAddresses = true;
                    break;
                case "2":
                    if (!hasViewedAddresses) {
                        System.out.println("Error: You must first view addresses before adding a new one.");
                        System.out.println("Please select option 1 first.");
                    } else {
                        enterNewAddress();
                    }
                    break;
                case "3":
                    searchAddresses();
                    break;
                case "4":
                    showStatistics();
                    break;
                case "5":
                    continueRunning = false;
                    System.out.println("Logging out and exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
    /**
     * Simulates administrator login.
     * For demonstration purposes, uses a simple username/password check.
     */
    private static void login() {
        System.out.println("\n=== Login ===");
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();
            // Simple authentication - in real application, this would be more secure
            if ("admin".equals(username) && "admin123".equals(password)) {
                isLoggedInAsAdmin = true;
                System.out.println("Login successful. Welcome, Administrator!");
                return;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("Login failed. " + attempts + " attempts remaining.");
                }
            }
        }
        System.out.println("Maximum login attempts reached.");
    }
    /**
     * Displays the list of addresses (simulates "ViewingLenchindirizzi" case).
     * This is a precondition for the EnterNewAddress use case.
     */
    private static void viewAddresses() {
        System.out.println("\n=== List of Addresses ===");
        List<String> addresses = archiveManager.getAllAddresses();
        if (addresses.isEmpty()) {
            System.out.println("No addresses in the archive.");
        } else {
            System.out.println("Total addresses: " + addresses.size());
            for (int i = 0; i < addresses.size(); i++) {
                System.out.println((i + 1) + ". " + addresses.get(i));
            }
        }
    }
    /**
     * Implements the main EnterNewAddress use case flow.
     * Handles the events sequence: show form, fill form, save, validate, insert.
     */
    private static void enterNewAddress() {
        System.out.println("\n=== Enter New Address ===");
        // 1. The system shows the form to be completed with: address name
        System.out.println("Please fill out the form:");
        // 2. User fills out the form
        System.out.print("Enter address name (2-100 characters, alphanumeric and basic symbols only): ");
        String addressName = scanner.nextLine();
        // Handle user interruption
        if (addressName.equalsIgnoreCase("cancel")) {
            System.out.println("Operation cancelled by administrator.");
            return;
        }
        // 3. User clicks on the "Save" button
        System.out.println("\nSaving address...");
        // 4. System checks validity and inserts new address
        // This activates the "Errodati" use case if data is not valid
        boolean success = archiveManager.insertAddress(addressName);
        // Handle postconditions
        if (success) {
            System.out.println("\nPostconditions:");
            System.out.println("✓ The user has entered an address");
            System.out.println("✓ Address successfully saved to archive");
        } else {
            System.out.println("\nPostconditions:");
            System.out.println("✗ Operation failed");
            System.out.println("✗ Activated: 'Errodati' use case - Error in data entry");
        }
    }
    /**
     * Search for addresses based on search term.
     */
    private static void searchAddresses() {
        System.out.println("\n=== Search Addresses ===");
        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine().trim();
        if (searchTerm.isEmpty()) {
            System.out.println("Search term cannot be empty.");
            return;
        }
        List<String> results = archiveManager.searchAddresses(searchTerm);
        if (results.isEmpty()) {
            System.out.println("No addresses found matching '" + searchTerm + "'");
        } else {
            System.out.println("Found " + results.size() + " address(es):");
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
        }
    }
    /**
     * Display system statistics.
     */
    private static void showStatistics() {
        System.out.println("\n=== System Statistics ===");
        System.out.println("Total addresses in archive: " + archiveManager.getAddressCount());
        System.out.println("Current user: " + (isLoggedInAsAdmin ? "Administrator" : "Guest"));
        System.out.println("Has viewed addresses: " + (hasViewedAddresses ? "Yes" : "No"));
    }
}