import java.util.Random;

/**
 * RegistrationSystem class orchestrates the complete registration flow
 * according to the use case requirements. It manages the registration process,
 * error handling, and system interactions.
 */
public class RegistrationSystem {
    private RegistrationForm registrationForm;
    private boolean serverConnected;
    private Random random;
    
    /**
     * Constructor for RegistrationSystem.
     * Initializes the system with a new registration form and default state.
     */
    public RegistrationSystem() {
        this.registrationForm = new RegistrationForm();
        this.serverConnected = true; // Assume server is initially connected
        this.random = new Random();
    }
    
    /**
     * Simulate server connection interruption.
     * According to exit condition: "Interruption of the connection to the server ETOUR."
     * This method simulates random server disconnections for testing.
     * 
     * @return true if server is connected, false if interrupted
     */
    private boolean checkServerConnection() {
        // Simulate 10% chance of server interruption for demonstration
        if (random.nextInt(100) < 10) {
            serverConnected = false;
            System.out.println("[SYSTEM] Server connection interrupted (ETOUR)");
            return false;
        }
        return true;
    }
    
    /**
     * Reconnect to the server.
     * Attempts to restore server connection after interruption.
     * 
     * @return true if reconnection successful, false otherwise
     */
    private boolean reconnectToServer() {
        System.out.println("[SYSTEM] Attempting to reconnect to server...");
        // Simulate reconnection attempt
        try {
            Thread.sleep(1000); // Simulate network delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Simulate 80% chance of successful reconnection
        if (random.nextInt(100) < 80) {
            serverConnected = true;
            System.out.println("[SYSTEM] Successfully reconnected to server");
            return true;
        } else {
            System.out.println("[SYSTEM] Failed to reconnect to server");
            return false;
        }
    }
    
    /**
     * Activate the Errored use case.
     * According to use case step 4: "Where the data is invalid or insufficient,
     * the system activates the use case Errored."
     * This method handles error scenarios and provides appropriate feedback.
     * 
     * @param errorMessage description of the error that occurred
     */
    private void activateErroredUseCase(String errorMessage) {
        System.out.println("\n=== ERRORED USE CASE ACTIVATED ===");
        System.out.println("Error: " + errorMessage);
        System.out.println("Please contact system administrator or try again later.");
        System.out.println("====================================\n");
    }
    
    /**
     * Handle registration cancellation by the user.
     * According to exit condition: "Guest User cancels the operation."
     * 
     * @param reason the reason for cancellation
     */
    private void handleUserCancellation(String reason) {
        System.out.println("\n=== REGISTRATION CANCELLED ===");
        System.out.println("Reason: " + reason);
        System.out.println("Registration process has been cancelled by the user.");
        System.out.println("================================\n");
    }
    
    /**
     * Notify successful operation.
     * According to exit condition: "The system shall notify the successful operation of recording."
     * 
     * @param account the created Account object
     */
    private void notifySuccess(Account account) {
        System.out.println("\n=== REGISTRATION SUCCESSFUL ===");
        System.out.println("Congratulations! Your account has been successfully created.");
        System.out.println("Account ID: " + account.getAccountId());
        System.out.println("Username: " + account.getUser().getUsername());
        System.out.println("Email: " + account.getUser().getEmail());
        System.out.println("Full Name: " + account.getUser().getFullName());
        System.out.println("Account Status: " + account.getAccountStatus());
        System.out.println("Creation Date: " + account.getAccountCreationDate());
        System.out.println("==================================\n");
        
        System.out.println("You can now log in to the system using your credentials.");
    }
    
    /**
     * Execute the complete registration flow.
     * This method implements all steps from the use case:
     * 1. Enable logging feature
     * 2. View registration form
     * 3. Fill out form and submit
     * 4. Verify data and ask for confirmation
     * 5. Confirm operation
     * 6. Create new account
     * 
     * @return true if registration was successful, false otherwise
     */
    public boolean executeRegistrationFlow() {
        System.out.println("=== REGISTRATION SYSTEM STARTED ===");
        System.out.println("Welcome Guest User! Starting registration process...\n");
        
        // Step 1: Enable logging feature
        System.out.println("[STEP 1] Enabling logging feature...");
        registrationForm.setLoggingEnabled(true);
        
        // Check server connection before proceeding
        if (!checkServerConnection()) {
            activateErroredUseCase("Server connection interrupted (ETOUR)");
            System.out.println("Attempting to handle server interruption...");
            
            // Try to reconnect
            if (!reconnectToServer()) {
                System.out.println("Unable to proceed due to server connection issues.");
                return false;
            }
        }
        
        // Step 2: View registration form
        System.out.println("\n[STEP 2] Displaying registration form...");
        registrationForm.displayForm();
        
        // Check server connection again
        if (!checkServerConnection()) {
            activateErroredUseCase("Server connection lost while displaying form");
            return false;
        }
        
        // Step 3: Fill out form and submit
        System.out.println("\n[STEP 3] Please fill out the registration form:");
        registrationForm.collectFormData();
        
        // Check if user wants to cancel (simulate by checking for "cancel" input)
        if (registrationForm.getUsername().equalsIgnoreCase("cancel") ||
            registrationForm.getEmail().equalsIgnoreCase("cancel")) {
            handleUserCancellation("User entered 'cancel' during form filling");
            return false;
        }
        
        // Check server connection after data collection
        if (!checkServerConnection()) {
            activateErroredUseCase("Server connection lost during data submission");
            return false;
        }
        
        // Step 4: Verify data entered and ask for confirmation
        System.out.println("\n[STEP 4] Verifying entered data...");
        RegistrationForm.ValidationResult validationResult = registrationForm.validateFormData();
        
        if (!validationResult.isValid()) {
            // Data is invalid or insufficient - activate Errored use case
            System.out.println("Data validation failed!");
            validationResult.displayErrors();
            activateErroredUseCase("Invalid or insufficient data entered");
            return false;
        }
        
        // Check server connection after validation
        if (!checkServerConnection()) {
            activateErroredUseCase("Server connection lost during validation");
            return false;
        }
        
        // Ask for confirmation
        System.out.println("\n[STEP 5] Asking for confirmation...");
        boolean userConfirmed = registrationForm.askForConfirmation();
        
        if (!userConfirmed) {
            handleUserCancellation("User declined to confirm registration");
            return false;
        }
        
        // Check server connection after confirmation
        if (!checkServerConnection()) {
            activateErroredUseCase("Server connection lost after confirmation");
            return false;
        }
        
        // Step 6: Create new account with the data entered
        System.out.println("\n[STEP 6] Creating new account...");
        User newUser = registrationForm.createUserFromFormData();
        Account newAccount = new Account(newUser);
        
        // Validate the created account
        if (!newAccount.validate()) {
            activateErroredUseCase("Failed to create valid account");
            return false;
        }
        
        // Notify successful operation
        notifySuccess(newAccount);
        
        // Clean up resources
        registrationForm.close();
        
        System.out.println("=== REGISTRATION SYSTEM COMPLETED ===");
        return true;
    }
    
    /**
     * Alternative registration flow with retry mechanism.
     * This method allows the user to retry registration if it fails.
     * 
     * @param maxRetries maximum number of retry attempts
     * @return true if registration succeeded within retry limit, false otherwise
     */
    public boolean executeRegistrationWithRetry(int maxRetries) {
        int attempt = 1;
        
        while (attempt <= maxRetries) {
            System.out.println("\n=== REGISTRATION ATTEMPT " + attempt + " OF " + maxRetries + " ===");
            
            boolean success = executeRegistrationFlow();
            
            if (success) {
                return true;
            }
            
            if (attempt < maxRetries) {
                System.out.println("\nRegistration failed. Would you like to try again?");
                System.out.println("Enter 'yes' to retry or 'no' to cancel: ");
                
                // Simulate user response (in real system, would get actual input)
                // For demonstration, we'll assume user wants to retry
                System.out.println("User chose to retry registration...");
                
                // Reset system state for retry
                resetSystem();
            }
            
            attempt++;
        }
        
        System.out.println("\nMaximum retry attempts reached. Registration failed.");
        return false;
    }
    
    /**
     * Reset the system state for a new registration attempt.
     * Creates a new registration form and resets connection state.
     */
    private void resetSystem() {
        registrationForm.close();
        registrationForm = new RegistrationForm();
        registrationForm.setLoggingEnabled(true);
        serverConnected = true;
        
        System.out.println("[SYSTEM] Registration system has been reset for new attempt");
    }
    
    /**
     * Get the current registration form instance.
     * 
     * @return the RegistrationForm object
     */
    public RegistrationForm getRegistrationForm() {
        return registrationForm;
    }
    
    /**
     * Check if server is currently connected.
     * 
     * @return true if server is connected, false otherwise
     */
    public boolean isServerConnected() {
        return serverConnected;
    }
    
    /**
     * Manually set server connection status.
     * Useful for testing different scenarios.
     * 
     * @param connected true to set server as connected, false as disconnected
     */
    public void setServerConnected(boolean connected) {
        this.serverConnected = connected;
    }
    
    /**
     * Clean up system resources.
     * Should be called when the system is no longer needed.
     */
    public void cleanup() {
        if (registrationForm != null) {
            registrationForm.close();
        }
        System.out.println("[SYSTEM] Registration system resources cleaned up");
    }
}