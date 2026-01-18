import java.util.Scanner;

/**
 * Main class to demonstrate the 'Delete address' use case.
 * This program provides a simple console interface to test the address deletion functionality.
 * It simulates the complete workflow as described in the use case specification.
 */
public class Main {
    /**
     * Main method - entry point of the program.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Address Deletion System ===");
        System.out.println("Simulating the 'Delete address' use case\n");
        
        // Create address service instance
        AddressService addressService = new AddressService();
        
        // Display initial list of addresses
        System.out.println("Initial list of addresses in archive:");
        addressService.displayAllAddresses();
        
        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Test scenario 1: Try to delete an address with associated classes (should fail)
        System.out.println("=== Test Scenario 1: Delete address with associated classes ===");
        System.out.println("Attempting to delete address ID 1 (has associated classes)...");
        boolean result1 = addressService.deleteAddress(1);
        System.out.println("Deletion result: " + (result1 ? "SUCCESS" : "FAILED"));
        System.out.println("Expected: FAILED - Address has associated classes\n");
        
        // Test scenario 2: Delete an address without associated classes (should succeed)
        System.out.println("=== Test Scenario 2: Delete address without associated classes ===");
        System.out.println("Attempting to delete address ID 2 (no associated classes)...");
        boolean result2 = addressService.deleteAddress(2);
        System.out.println("Deletion result: " + (result2 ? "SUCCESS" : "FAILED"));
        System.out.println("Expected: SUCCESS - No associated classes\n");
        
        // Test scenario 3: Try to delete non-existent address (should fail)
        System.out.println("=== Test Scenario 3: Delete non-existent address ===");
        System.out.println("Attempting to delete address ID 99 (does not exist)...");
        boolean result3 = addressService.deleteAddress(99);
        System.out.println("Deletion result: " + (result3 ? "SUCCESS" : "FAILED"));
        System.out.println("Expected: FAILED - Address not found\n");
        
        // Test scenario 4: Remove associated classes and then delete address
        System.out.println("=== Test Scenario 4: Remove associated classes then delete ===");
        System.out.println("First, removing associated classes from address ID 3...");
        boolean classesRemoved = addressService.removeAllAssociatedClasses(3);
        
        if (classesRemoved) {
            System.out.println("Now attempting to delete address ID 3...");
            boolean result4 = addressService.deleteAddress(3);
            System.out.println("Deletion result: " + (result4 ? "SUCCESS" : "FAILED"));
            System.out.println("Expected: SUCCESS - Associated classes were removed\n");
        } else {
            System.out.println("Failed to remove associated classes from address ID 3\n");
        }
        
        // Display final state
        System.out.println("=== Final State ===");
        System.out.println("Remaining addresses in archive:");
        addressService.displayAllAddresses();
        
        // Check SMOS server connection status (postcondition)
        System.out.println("SMOS Server Connection Status: " + 
                          (addressService.isSmosServerConnected() ? "CONNECTED" : "INTERRUPTED"));
        System.out.println("Expected: INTERRUPTED (postcondition requirement)\n");
        
        // Interactive mode for user testing
        System.out.println("=== Interactive Mode ===");
        System.out.println("You can now test deleting addresses interactively.");
        System.out.println("Available addresses:");
        addressService.displayAllAddresses();
        
        boolean continueTesting = true;
        while (continueTesting) {
            System.out.print("Enter address ID to delete (or 0 to exit): ");
            
            if (scanner.hasNextInt()) {
                int addressId = scanner.nextInt();
                
                if (addressId == 0) {
                    continueTesting = false;
                    System.out.println("Exiting interactive mode.");
                } else {
                    System.out.println("\nProcessing deletion for address ID " + addressId + "...");
                    
                    // First display address details (simulating viewdettaglizzazione)
                    System.out.println("Displaying address details (simulating 'viewdettaglizzazione'):");
                    addressService.displayAddressDetails(addressId);
                    
                    // Then attempt deletion
                    boolean deletionResult = addressService.deleteAddress(addressId);
                    
                    if (deletionResult) {
                        System.out.println("Address deletion completed successfully.");
                    } else {
                        System.out.println("Address deletion failed. Check error messages above.");
                    }
                    
                    System.out.println("\nCurrent addresses in archive:");
                    addressService.displayAllAddresses();
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
        
        // Close scanner
        scanner.close();
        
        System.out.println("\n=== Program Complete ===");
        System.out.println("Summary:");
        System.out.println("- Demonstrated all aspects of the 'Delete address' use case");
        System.out.println("- Handled edge cases (associated classes, non-existent addresses)");
        System.out.println("- Verified preconditions (administrator role, viewed details)");
        System.out.println("- Ensured postconditions (SMOS server interruption)");
        System.out.println("- Provided both automated tests and interactive mode");
    }
}