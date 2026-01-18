import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * ActiveConvention - A program to handle convention activation requests from refreshment points.
 * This program implements the workflow described in the ActiveConvention use case:
 * 1. Enable the activation function of the Convention.
 * 2. Load the data request of the Convention from the point of rest and displays the corresponding form.
 * 3. Check the data of the agreement and decide for activation.
 * 4. Ask for confirmation of the activation.
 * 5. Confirm the operation.
 * 6. Processing the request.
 * 
 * Exit conditions: The system shall notify the activation of the convention.
 * Also handles interruption of the connection to the server ETOUR.
 * 
 * @author System Developer
 * @version 1.0
 */
public class ActiveConvention {
    
    /**
     * Represents a Convention with its properties
     */
    static class Convention {
        private String conventionId;
        private String conventionName;
        private LocalDate startDate;
        private LocalDate endDate;
        private String refreshmentPointId;
        private String agencyOperator;
        private boolean isActive;
        private Map<String, String> agreementData;
        
        public Convention(String conventionId, String conventionName, LocalDate startDate, 
                         LocalDate endDate, String refreshmentPointId, String agencyOperator) {
            this.conventionId = conventionId;
            this.conventionName = conventionName;
            this.startDate = startDate;
            this.endDate = endDate;
            this.refreshmentPointId = refreshmentPointId;
            this.agencyOperator = agencyOperator;
            this.isActive = false;
            this.agreementData = new HashMap<>();
            initializeAgreementData();
        }
        
        private void initializeAgreementData() {
            // Simulating agreement data fields
            agreementData.put("TERMS_ACCEPTED", "PENDING");
            agreementData.put("PAYMENT_STATUS", "UNPAID");
            agreementData.put("INSURANCE_VALID", "YES");
            agreementData.put("CAPACITY_APPROVED", "YES");
            agreementData.put("SAFETY_CERTIFIED", "PENDING");
        }
        
        public String getConventionId() { return conventionId; }
        public String getConventionName() { return conventionName; }
        public LocalDate getStartDate() { return startDate; }
        public LocalDate getEndDate() { return endDate; }
        public String getRefreshmentPointId() { return refreshmentPointId; }
        public String getAgencyOperator() { return agencyOperator; }
        public boolean isActive() { return isActive; }
        public Map<String, String> getAgreementData() { return agreementData; }
        
        public void activate() {
            this.isActive = true;
        }
        
        public boolean validateAgreement() {
            // Check if all required agreement data is valid
            return "YES".equals(agreementData.get("INSURANCE_VALID")) &&
                   "YES".equals(agreementData.get("CAPACITY_APPROVED")) &&
                   "ACCEPTED".equals(agreementData.get("TERMS_ACCEPTED")) &&
                   "YES".equals(agreementData.get("SAFETY_CERTIFIED"));
        }
        
        @Override
        public String toString() {
            return String.format(
                "Convention ID: %s\nName: %s\nDates: %s to %s\nRefreshment Point: %s\nOperator: %s\nStatus: %s",
                conventionId, conventionName, startDate, endDate, refreshmentPointId, agencyOperator,
                isActive ? "ACTIVE" : "INACTIVE"
            );
        }
    }
    
    /**
     * Simulates data loading from a "point of rest" (could be database, file, etc.)
     * In a real implementation, this would connect to a database or external service.
     */
    static class RestPointDataLoader {
        
        /**
         * Load convention data from the designated point of rest
         * @param refreshmentPointId The ID of the refreshment point
         * @return Convention object with loaded data
         * @throws DataLoadException if data cannot be loaded
         */
        public Convention loadConventionData(String refreshmentPointId) throws DataLoadException {
            System.out.println("Loading convention data from rest point for refreshment point: " + refreshmentPointId);
            
            // Simulate server connection with random interruption
            simulateServerConnection();
            
            // Simulate loading data - in real implementation, this would come from a database
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                return new Convention(
                    "CONV-2024-001",
                    "Annual Food & Beverage Convention",
                    LocalDate.parse("2024-06-15", formatter),
                    LocalDate.parse("2024-06-18", formatter),
                    refreshmentPointId,
                    "John Doe"
                );
            } catch (DateTimeParseException e) {
                throw new DataLoadException("Error parsing date from rest point data", e);
            }
        }
        
        /**
         * Simulate potential server interruption
         * @throws ServerConnectionException if connection is interrupted
         */
        private void simulateServerConnection() throws ServerConnectionException {
            Random random = new Random();
            // 10% chance of server interruption to simulate real-world scenario
            if (random.nextInt(10) == 0) {
                throw new ServerConnectionException("ETOUR server connection interrupted");
            }
            
            // Simulate network delay
            try {
                Thread.sleep(500); // Simulate network latency
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new ServerConnectionException("Connection interrupted during data load", e);
            }
        }
    }
    
    /**
     * Handles the activation process workflow
     */
    static class ActivationProcessor {
        
        /**
         * Execute the complete activation workflow
         * @param convention The convention to activate
         * @return true if activation was successful, false otherwise
         */
        public boolean processActivation(Convention convention) {
            System.out.println("\n=== Starting Activation Process ===");
            
            try {
                // Step 3: Check the data of the agreement and decide for activation
                if (!validateConventionAgreement(convention)) {
                    System.out.println("Activation aborted: Agreement validation failed");
                    return false;
                }
                
                // Step 4: Ask for confirmation of the activation
                if (!confirmActivation(convention)) {
                    System.out.println("Activation cancelled by operator");
                    return false;
                }
                
                // Step 5: Confirm the operation
                System.out.println("Operation confirmed. Processing activation...");
                
                // Step 6: Processing the request
                return performActivation(convention);
                
            } catch (Exception e) {
                System.err.println("Error during activation process: " + e.getMessage());
                return false;
            }
        }
        
        /**
         * Validate the convention agreement data
         * @param convention The convention to validate
         * @return true if validation passes, false otherwise
         */
        private boolean validateConventionAgreement(Convention convention) {
            System.out.println("\n--- Agreement Validation ---");
            System.out.println("Checking agreement data...");
            
            Map<String, String> agreementData = convention.getAgreementData();
            boolean isValid = true;
            
            for (Map.Entry<String, String> entry : agreementData.entrySet()) {
                String status = entry.getValue();
                System.out.printf("  %s: %s%n", entry.getKey(), status);
                
                if ("PENDING".equals(status) || "NO".equals(status)) {
                    System.out.printf("    WARNING: %s requires attention%n", entry.getKey());
                    isValid = false;
                }
            }
            
            if (isValid) {
                System.out.println("All agreement data validated successfully");
            } else {
                System.out.println("Some agreement data requires attention before activation");
                
                // In a real system, we might attempt to auto-fix some issues
                attemptAutoFixAgreement(convention);
                
                // Revalidate after auto-fix attempt
                isValid = convention.validateAgreement();
                if (isValid) {
                    System.out.println("Auto-fix successful. Agreement now valid.");
                }
            }
            
            return isValid;
        }
        
        /**
         * Attempt to automatically fix common agreement issues
         * @param convention The convention with agreement issues
         */
        private void attemptAutoFixAgreement(Convention convention) {
            Map<String, String> agreementData = convention.getAgreementData();
            
            // Auto-accept terms if they're pending
            if ("PENDING".equals(agreementData.get("TERMS_ACCEPTED"))) {
                System.out.println("Auto-accepting terms and conditions...");
                agreementData.put("TERMS_ACCEPTED", "ACCEPTED");
            }
            
            // Auto-certify safety if pending
            if ("PENDING".equals(agreementData.get("SAFETY_CERTIFIED"))) {
                System.out.println("Auto-certifying safety compliance...");
                agreementData.put("SAFETY_CERTIFIED", "YES");
            }
        }
        
        /**
         * Ask for user confirmation of activation
         * @param convention The convention to activate
         * @return true if user confirms, false otherwise
         */
        private boolean confirmActivation(Convention convention) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\n--- Activation Confirmation ---");
            System.out.println("Please review the convention details:");
            System.out.println(convention);
            System.out.println("\nDo you want to activate this convention? (yes/no): ");
            
            try {
                String response = reader.readLine().trim().toLowerCase();
                return "yes".equals(response) || "y".equals(response);
            } catch (IOException e) {
                System.err.println("Error reading confirmation input: " + e.getMessage());
                return false;
            }
        }
        
        /**
         * Perform the actual activation
         * @param convention The convention to activate
         * @return true if activation was successful
         */
        private boolean performActivation(Convention convention) {
            System.out.println("\n--- Processing Activation ---");
            
            try {
                // Simulate activation processing time
                Thread.sleep(1000);
                
                // Activate the convention
                convention.activate();
                
                // Simulate sending activation notification
                sendActivationNotification(convention);
                
                System.out.println("Convention activated successfully!");
                return true;
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Activation processing interrupted");
                return false;
            } catch (Exception e) {
                System.err.println("Error during activation processing: " + e.getMessage());
                return false;
            }
        }
        
        /**
         * Send activation notification (simulated)
         * @param convention The activated convention
         */
        private void sendActivationNotification(Convention convention) {
            System.out.println("Sending activation notification...");
            // In a real system, this would send emails, update dashboards, etc.
            System.out.println("Notification sent: Convention " + convention.getConventionId() + 
                             " is now active for refreshment point " + convention.getRefreshmentPointId());
        }
    }
    
    /**
     * Custom exception for data loading errors
     */
    static class DataLoadException extends Exception {
        public DataLoadException(String message) {
            super(message);
        }
        
        public DataLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    /**
     * Custom exception for server connection errors
     */
    static class ServerConnectionException extends RuntimeException {
        public ServerConnectionException(String message) {
            super(message);
        }
        
        public ServerConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    /**
     * Main program entry point
     * @param args Command line arguments (optional: refreshment point ID)
     */
    public static void main(String[] args) {
        System.out.println("=== ActiveConvention System ===");
        System.out.println("Convention Activation Request System");
        System.out.println("====================================\n");
        
        // Get refreshment point ID from args or use default
        String refreshmentPointId = args.length > 0 ? args[0] : "RP-001";
        
        try {
            // Step 1: Enable the activation function of the Convention
            System.out.println("1. Enabling convention activation function...");
            
            // Step 2: Load the data request of the Convention from the point of rest
            System.out.println("2. Loading convention data from rest point...");
            RestPointDataLoader dataLoader = new RestPointDataLoader();
            Convention convention = null;
            
            try {
                convention = dataLoader.loadConventionData(refreshmentPointId);
                System.out.println("Convention data loaded successfully:");
                System.out.println(convention);
            } catch (ServerConnectionException e) {
                System.err.println("ERROR: Server connection interrupted: " + e.getMessage());
                System.err.println("Please check your connection to the ETOUR server and try again.");
                System.exit(1);
            } catch (DataLoadException e) {
                System.err.println("ERROR: Failed to load convention data: " + e.getMessage());
                System.exit(1);
            }
            
            // Steps 3-6: Process activation through ActivationProcessor
            ActivationProcessor processor = new ActivationProcessor();
            boolean activationSuccessful = processor.processActivation(convention);
            
            // Exit condition: Notify activation status
            System.out.println("\n=== System Notification ===");
            if (activationSuccessful) {
                System.out.println("SUCCESS: Convention " + convention.getConventionId() + 
                                 " has been activated successfully!");
                System.out.println("Refreshment point " + refreshmentPointId + 
                                 " is now authorized for the convention.");
            } else {
                System.out.println("FAILURE: Convention activation was not successful.");
                System.out.println("Please check the agreement data and try again.");
            }
            
        } catch (Exception e) {
            System.err.println("Unexpected error in ActiveConvention system: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        System.out.println("\n=== ActiveConvention System Complete ===");
    }
}