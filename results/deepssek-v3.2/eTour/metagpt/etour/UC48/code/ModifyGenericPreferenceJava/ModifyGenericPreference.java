package com.etour.preference;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Main application class for the ModifyGenericPreference use case.
 * Simulates the flow for a tourist to edit their generic personal preferences.
 */
public class ModifyGenericPreference {
    
    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);
    
    /**
     * Main method - entry point of the application.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== ETOUR Personal Preference Modification System ===\n");
        
        try {
            // Step 0: Check authentication (simulated as entry condition)
            Tourist tourist = authenticateTourist();
            
            // Step 1: Access modification functionality
            System.out.println("\n=== Step 1: Accessing preference modification functionality ===");
            
            // Step 2: Load existing preferences into form
            System.out.println("=== Step 2: Loading current preferences ===");
            Preference currentPreferences = PreferenceService.loadPreferences(tourist);
            
            // Create form with current preferences
            PreferenceForm form = new PreferenceForm(currentPreferences);
            form.displayForm();
            
            // Step 3: Edit fields and submit
            System.out.println("\n=== Step 3: Editing preferences ===");
            boolean changesMade = form.editFields();
            
            if (!changesMade) {
                System.out.println("No changes were made. Operation cancelled.");
                return;
            }
            
            // Step 4: Ask for confirmation
            System.out.println("\n=== Step 4: Confirmation ===");
            boolean confirmed = form.confirmChanges();
            
            if (!confirmed) {
                System.out.println("Changes were not confirmed. Operation cancelled.");
                return;
            }
            
            // Step 5: Confirm the operation (already done in previous step)
            System.out.println("\n=== Step 5: Final confirmation received ===");
            
            // Step 6: Store changed preferences
            System.out.println("\n=== Step 6: Storing updated preferences ===");
            Preference updatedPreferences = form.getUpdatedPreferences();
            PreferenceService.savePreferences(tourist, updatedPreferences);
            
            // Success notification
            System.out.println("\n✅ SUCCESS: Generic personal preferences have been successfully modified!");
            System.out.println("Updated preferences:");
            System.out.println(updatedPreferences);
            
        } catch (OperationCanceledException e) {
            System.out.println("\n⚠️ Operation cancelled by user: " + e.getMessage());
        } catch (ConnectionInterruptedException e) {
            System.out.println("\n❌ Connection error: " + e.getMessage());
            System.out.println("Please check your internet connection and try again.");
        } catch (Exception e) {
            System.out.println("\n❌ An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
            System.out.println("\n=== Thank you for using ETOUR Preference System ===");
        }
    }
    
    /**
     * Simulates tourist authentication.
     * In a real system, this would connect to an authentication service.
     * @return authenticated Tourist object
     * @throws OperationCanceledException if authentication fails or is cancelled
     */
    private static Tourist authenticateTourist() throws OperationCanceledException {
        System.out.println("=== Tourist Authentication ===");
        
        // Simulate authentication process
        int attempts = 0;
        final int maxAttempts = 3;
        
        while (attempts < maxAttempts) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();
            
            // Simple validation (in real system, this would verify against database)
            if (!username.isEmpty() && !password.isEmpty()) {
                System.out.println("✓ Authentication successful!");
                return new Tourist(username);
            } else {
                attempts++;
                System.out.println("✗ Authentication failed. Attempt " + attempts + " of " + maxAttempts);
                
                if (attempts < maxAttempts) {
                    System.out.print("Try again? (yes/no): ");
                    String response = scanner.nextLine().trim().toLowerCase();
                    if (!response.equals("yes")) {
                        throw new OperationCanceledException("Authentication cancelled by user.");
                    }
                }
            }
        }
        
        throw new OperationCanceledException("Maximum authentication attempts exceeded.");
    }
    
    /**
     * Helper method to simulate server availability check.
     * @return true if server is available, false otherwise
     */
    private static boolean isServerAvailable() {
        // In a real application, this would ping the ETOUR server
        // For simulation, we'll randomly determine availability
        return Math.random() > 0.1; // 90% chance of being available
    }
}

/**
 * Custom exception for when the user cancels an operation.
 */
class OperationCanceledException extends Exception {
    public OperationCanceledException(String message) {
        super(message);
    }
}

/**
 * Custom exception for when the connection to ETOUR server is interrupted.
 */
class ConnectionInterruptedException extends Exception {
    public ConnectionInterruptedException(String message) {
        super(message);
    }
}