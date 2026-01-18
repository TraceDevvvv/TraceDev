
package com.example.adapters.controllers;

import com.example.business.UpdateAccountUseCase;
import com.example.business.command.UpdateAccountCommand;
import com.example.business.interactor.UpdateAccountInteractor;
import com.example.business.result.UpdateAccountResult;
import java.util.Map;

/**
 * Interface Adapters Layer: Controller handling HTTP requests for account operations.
 */
public class AccountController {
    private UpdateAccountUseCase updateUseCase;
    private Object presenter;
    // We need the interactor for the proceedWithUpdate call (sequence diagram).
    // Assuming UpdateAccountInteractor implements UpdateAccountUseCase, we can cast.
    private UpdateAccountInteractor interactor;

    public AccountController(UpdateAccountUseCase useCase, Object presenter) {
        this.updateUseCase = useCase;
        this.presenter = presenter;
        if (useCase instanceof UpdateAccountInteractor) {
            this.interactor = (UpdateAccountInteractor) useCase;
        } else {
            // Fallback: create a dummy interactor (should not happen in proper setup)
            this.interactor = null;
        }
    }

    /**
     * Gets current account data for pre‑populating the form (sequence diagram step 3).
     */
    public Map<String, Object> getAccountData(String touristId) {
        // Using the interactor's getAccountData method (added for this purpose)
        if (interactor != null) {
            UpdateAccountResult result = interactor.getAccountData(touristId);
            if (result.isSuccess()) {
                // Cast presenter to appropriate type if needed
                return ((Map<String, Object>) presenter);
            } else {
                // Cast presenter to appropriate type if needed
                return ((Map<String, Object>) presenter);
            }
        }
        // Fallback if interactor not available
        return java.util.Collections.singletonMap("error", "Interactor not available");
    }

    /**
     * Handles update account request (sequence diagram step 10).
     */
    public Map<String, Object> updateAccount(String touristId, Map<String, String> data) {
        String username = data.getOrDefault("username", "");
        String email = data.getOrDefault("email", "");
        String phone = data.getOrDefault("phone", "");

        UpdateAccountCommand command = new UpdateAccountCommand(touristId, username, email, phone);
        UpdateAccountResult result = updateUseCase.execute(command);

        if (result.isSuccess()) {
            // Data valid: request confirmation (sequence diagram step 16)
            // Return a special result indicating confirmation needed
            Map<String, Object> response = new java.util.HashMap<>();
            response.put("status", "CONFIRMATION_REQUIRED");
            response.put("data", data);
            response.put("message", "Please confirm the changes");
            return response;
        } else {
            // Data invalid: present errors (sequence diagram step 23)
            return java.util.Collections.singletonMap("errors", result.getErrors());
        }
    }

    /**
     * Called after user confirms the update (sequence diagram step 20 → 21).
     */
    public Map<String, Object> confirmationReceived() {
        // In a real scenario, we would have stored the pending command.
        // For simplicity, we assume the command is stored in session or controller state.
        // We'll just call proceedWithUpdate with a dummy command (assumption).
        // In practice, you would retrieve the pending command from context.
        return java.util.Collections.singletonMap("error", "Pending command not implemented in this example");
    }

    /**
     * Actually proceeds with the update after confirmation (sequence diagram step 24).
     * This method would be called with the pending command.
     */
    public Map<String, Object> proceedWithUpdate(UpdateAccountCommand command) {
        if (interactor != null) {
            UpdateAccountResult result = interactor.proceedWithUpdate(command);
            if (result.isSuccess()) {
                return java.util.Collections.singletonMap("message", result.getMessage());
            } else {
                return java.util.Collections.singletonMap("errors", result.getErrors());
            }
        }
        return java.util.Collections.singletonMap("error", "Interactor not available");
    }
}
