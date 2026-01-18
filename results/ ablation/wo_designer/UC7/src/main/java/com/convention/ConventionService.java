
package com.convention;

/**
 * Service class for handling convention activation operations.
 */
public class ConventionService {
    private final EtourServerConnection serverConnection;

    public ConventionService() {
        this.serverConnection = new EtourServerConnection();
    }

    /**
     * Main method to activate a convention following the specified flow.
     * @param conventionId The ID of the convention to activate
     * @param agencyOperator The agency operator performing the activation
     * @return true if activation successful, false otherwise
     */
    public boolean activateConvention(String conventionId, AgencyOperator agencyOperator) {
        System.out.println("Step 1: Agency Operator enables the activation function of the Convention.");
        
        System.out.println("Step 2: The system loads the data request of the Convention from the point of rest.");
        Convention convention = loadConventionData(conventionId);
        if (convention == null) {
            System.out.println("Error: Convention not found for ID: " + conventionId);
            return false;
        }

        System.out.println("Step 3: The system displays the corresponding form.");
        displayConventionForm(convention);

        System.out.println("Step 4: The system checks the data of the agreement.");
        if (!checkAgreementData(convention.getAgreementData())) {
            System.out.println("Error: Agreement data validation failed.");
            return false;
        }

        System.out.println("Step 5: The system decides for activation.");
        if (!shouldActivateConvention(convention)) {
            System.out.println("System decision: Convention should not be activated.");
            return false;
        }

        System.out.println("Step 6: The system asks for confirmation of the activation.");
        boolean operatorConfirmed = agencyOperator.confirmActivation(convention);
        if (!operatorConfirmed) {
            System.out.println("Operation cancelled by Agency Operator.");
            return false;
        }

        System.out.println("Step 7: Agency Operator confirms the operation.");

        System.out.println("Step 8: The system processes the request.");
        boolean activationSuccessful = processActivationRequest(convention);
        
        if (activationSuccessful) {
            System.out.println("Exit: The system notifies the activation of the convention.");
            convention.setActive(true);
            notifyConventionActivation(convention);
            
            System.out.println("Exit: The system interrupts the connection to the server ETOUR.");
            serverConnection.disconnect();
        }
        
        return activationSuccessful;
    }

    /**
     * Load convention data from the point of rest (simulated).
     */
    private Convention loadConventionData(String conventionId) {
        // Simulating data loading from a REST point
        // In a real system, this would make an API call or database query
        if ("CONV001".equals(conventionId)) {
            return new Convention("CONV001", "Summer Refreshment Convention", 
                                 "Agreement terms: Operational from June-August");
        } else if ("CONV002".equals(conventionId)) {
            return new Convention("CONV002", "Winter Refreshment Convention", 
                                 "Agreement terms: Operational from December-February");
        }
        return null;
    }

    /**
     * Display the convention form (simulated).
     */
    private void displayConventionForm(Convention convention) {
        System.out.println("=== Convention Form ===");
        System.out.println("Convention ID: " + convention.getConventionId());
        System.out.println("Convention Name: " + convention.getConventionName());
        System.out.println("Agreement Data: " + convention.getAgreementData());
        System.out.println("Current Status: " + (convention.isActive() ? "Active" : "Inactive"));
        System.out.println("=====================");
    }

    /**
     * Check the agreement data for validity.
     */
    private boolean checkAgreementData(String agreementData) {
        // Simulate agreement data validation
        return agreementData != null && !agreementData.trim().isEmpty() 
               && agreementData.contains("Agreement terms");
    }

    /**
     * System decision logic for activation.
     */
    private boolean shouldActivateConvention(Convention convention) {
        // Simulate decision logic based on convention data
        // Could check dates, availability, etc.
        return !convention.isActive() && 
               convention.getAgreementData() != null &&
               convention.getAgreementData().length() > 10;
    }

    /**
     * Process the activation request.
     */
    private boolean processActivationRequest(Convention convention) {
        // Simulate processing with the server
        try {
            // Simulate network delay
            Thread.sleep(1000);
            
            // Check server connectivity (Quality Requirement)
            if (!serverConnection.isConnected()) {
                System.out.println("Error: No connectivity to server");
                return false;
            }
            
            // Simulate server processing
            boolean serverResponse = serverConnection.processActivation(convention);
            
            if (!serverResponse) {
                System.out.println("Error: Server rejected activation request");
                return false;
            }
            
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error: Activation process interrupted");
            return false;
        }
    }

    /**
     * Notify about convention activation.
     */
    private void notifyConventionActivation(Convention convention) {
        System.out.println("Notification: Convention '" + convention.getConventionName() 
                         + "' (ID: " + convention.getConventionId() + ") has been successfully activated.");
    }
}
