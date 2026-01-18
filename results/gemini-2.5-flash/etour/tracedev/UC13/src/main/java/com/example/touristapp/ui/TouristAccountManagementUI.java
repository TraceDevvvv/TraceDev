package com.example.touristapp.ui;

import com.example.touristapp.application.DisableTouristAccountCommand;
import com.example.touristapp.application.EnableTouristAccountCommand;
import com.example.touristapp.application.TouristAccountCommandService;
import com.example.touristapp.application.TouristAccountQueryService;
import com.example.touristapp.domain.TouristAccount;
import com.example.touristapp.infrastructure.RepositoryException;

import java.util.Scanner;

/**
 * User Interface Adapter for managing tourist accounts.
 * This class interacts with the application serv and handles user input/output.
 */
public class TouristAccountManagementUI {
    private final TouristAccountCommandService touristAccountCommandService;
    private final TouristAccountQueryService touristAccountQueryService; // Added for displayAccountDetails
    private final Scanner scanner; // For simulating user input

    // State to hold pending operation details before confirmation
    private String pendingAccountId;
    private boolean pendingEnableAction;
    private String pendingOperatorId;

    /**
     * Constructs a TouristAccountManagementUI.
     * @param commandService The command service for modifying tourist accounts.
     * @param queryService The query service for retrieving tourist account details.
     */
    public TouristAccountManagementUI(TouristAccountCommandService commandService, TouristAccountQueryService queryService) {
        if (commandService == null) {
            throw new IllegalArgumentException("TouristAccountCommandService cannot be null.");
        }
        if (queryService == null) {
            throw new IllegalArgumentException("TouristAccountQueryService cannot be null.");
        }
        this.touristAccountCommandService = commandService;
        this.touristAccountQueryService = queryService;
        this.scanner = new Scanner(System.in); // Initialize scanner for user input simulation
    }

    /**
     * Displays the details of a specific tourist account.
     * This method is part of the class diagram but not directly in the sequence diagram's flow.
     * It's assumed to be called prior to initiating status changes.
     * @param accountId The ID of the account whose details are to be displayed.
     */
    public void displayAccountDetails(String accountId) {
        System.out.println("\n--- Displaying Account Details for " + accountId + " ---");
        try {
            TouristAccount account = touristAccountQueryService.getAccountDetails(accountId);
            System.out.println("Account Found: " + account);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (RepositoryException e) {
            System.err.println("Error fetching account details: " + e.getMessage());
        }
        System.out.println("----------------------------------------------");
    }

    /**
     * Initiates a status change operation for a tourist account.
     * This method prompts the operator for confirmation before proceeding.
     * It sets internal state for the pending operation.
     * @param accountId The ID of the account to change.
     * @param enable True to enable, false to disable.
     * @param operatorId The ID of the operator performing the action.
     */
    public void initiateStatusChange(String accountId, boolean enable, String operatorId) {
        System.out.println("\n[UI] Operator initiates status change for account: " + accountId);
        
        // Store context for pending operation
        this.pendingAccountId = accountId;
        this.pendingEnableAction = enable;
        this.pendingOperatorId = operatorId;

        // R5: Internal UI interaction for confirmation. This just displays the prompt.
        requestConfirmation(
            "Confirm " + (enable ? "activation" : "deactivation") +
            " for account " + accountId + " (yes/no)? "
        );
    }

    /**
     * Simulates the operator confirming or cancelling the initiated operation.
     * This method checks the internal state set by `initiateStatusChange` and
     * proceeds with the command service call if confirmed.
     * @param accountId The account ID related to the operation (should match pendingAccountId).
     * @param confirmed True if the operation is confirmed, false otherwise.
     * @return true if the operation was processed (either confirmed or cancelled), false if no pending operation matched.
     */
    public boolean confirmOperation(String accountId, boolean confirmed) {
        System.out.println("[UI] Operator confirms operation for account: " + accountId + ", confirmed: " + confirmed);

        if (this.pendingAccountId == null || !this.pendingAccountId.equals(accountId)) {
            System.err.println("[UI] No pending operation or account ID mismatch for confirmation: " + accountId);
            notifyOutcome("Error: No pending operation found for account " + accountId + " or mismatch.");
            return false;
        }

        if (confirmed) {
            try {
                if (pendingEnableAction) {
                    touristAccountCommandService.handle(
                        new EnableTouristAccountCommand(pendingAccountId, pendingOperatorId)
                    );
                } else {
                    touristAccountCommandService.handle(
                        new DisableTouristAccountCommand(pendingAccountId, pendingOperatorId)
                    );
                }
                notifyOutcome("Account status updated successfully for " + pendingAccountId + ".");
            } catch (IllegalArgumentException e) {
                notifyOutcome("Operation failed: " + e.getMessage());
            } catch (RepositoryException e) {
                notifyOutcome("Error: Connection to ETOUR server interrupted. " + e.getMessage());
                // Simulate exception propagation in the UI for the error scenario
                // This is where "Service --x UI : errorMessage" is handled.
            }
        } else {
            notifyOutcome("Operation cancelled by operator for " + pendingAccountId + ".");
        }

        // Clear pending operation state
        this.pendingAccountId = null;
        this.pendingEnableAction = false;
        this.pendingOperatorId = null;
        return true;
    }

    /**
     * Notifies the user about the outcome of an operation.
     * @param message The message to display to the user.
     */
    public void notifyOutcome(String message) {
        System.out.println("[UI] Notification: " + message);
    }

    /**
     * (Internal UI interaction) Requests confirmation from the user.
     * For a console application, this prints a message.
     * In a real GUI, this would open a confirmation dialog.
     * @param message The confirmation message to display.
     * @return true if confirmed, false otherwise.
     */
    private void requestConfirmation(String message) {
        System.out.print("[UI] " + message);
        // This method only displays the prompt. The actual confirmation (yes/no) comes via confirmOperation from the actor.
    }

    // Helper for interactive confirmation in a console setup
    public boolean getConfirmationInput() {
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }

    // Close scanner when done
    public void closeScanner() {
        scanner.close();
    }
}