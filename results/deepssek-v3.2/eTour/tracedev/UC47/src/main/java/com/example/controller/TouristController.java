
package com.example.controller;

import com.example.service.ITouristService;
import com.example.dto.TouristProfileDTO;
import com.example.dto.UpdateTouristRequest;
import com.example.dto.UpdateTouristResponse;

/**
 * Controller for tourist profile operations.
 * Orchestrates the flow between tourist interactions and backend serv.
 */
public class TouristController {
    private ITouristService touristService;
    private Object authenticator;
    private Object confirmationService;

    // Constructors
    public TouristController() {}

    public TouristController(ITouristService touristService,
                            Object authenticator,
                            Object confirmationService) {
        this.touristService = touristService;
        this.authenticator = authenticator;
        this.confirmationService = confirmationService;
    }

    /**
     * Handle profile view request.
     * Sequence Diagram step: handleProfileView()
     */
    public TouristProfileDTO handleProfileView() {
        System.out.println("[Controller] Handling profile view request");
        // Implementation would get touristId from session/context
        String touristId = "TOUR123"; // Example tourist ID
        TouristProfileDTO profile = touristService.loadTouristProfile(touristId);
        return profile; // Returns "Display form with data"
    }

    /**
     * Handle profile update request.
     * Sequence Diagram step: handleProfileUpdate(request: UpdateTouristRequest)
     */
    public UpdateTouristResponse handleProfileUpdate(UpdateTouristRequest request) {
        System.out.println("[Controller] Handling profile update request for tourist: " + request.getTouristId());
        
        // Validate request
        if (request == null || request.getProfileData() == null) {
            return new UpdateTouristResponse(false, "Invalid request data", null);
        }
        
        // Call service to update profile
        boolean success = touristService.updateTouristProfile(request.getTouristId(), request.getProfileData());
        
        if (!success) {
            // Error occurred - show error message
            System.out.println("[Controller] Update failed, showing error message");
            return new UpdateTouristResponse(false, "Update failed", null);
        }
        
        // Step 7-8: Confirmation (Sequence Diagram note)
        System.out.println("[Controller] Update successful, proceeding to confirmation");
        
        // Request confirmation
        boolean confirmationRequested = requestConfirmation();
        if (!confirmationRequested) {
            System.out.println("[Controller] Confirmation request failed");
            return new UpdateTouristResponse(false, "Confirmation failed", null);
        }
        
        // Show confirmation dialog would happen here (UI layer)
        System.out.println("[Controller] Showing confirmation dialog");
        
        // For now, simulate tourist confirming the transaction
        confirmTransaction();
        
        // Store confirmation
        storeConfirmation();
        
        // Return success response with updated data
        TouristProfileDTO updatedProfile = touristService.loadTouristProfile(request.getTouristId());
        System.out.println("[Controller] Showing success notification");
        return new UpdateTouristResponse(true, "Profile updated successfully", updatedProfile);
    }

    /**
     * Cancel operation.
     * Sequence Diagram step: cancelOperation()
     */
    public void cancelOperation() {
        System.out.println("[Controller] Cancelling operation");
        // Abort transaction
        System.out.println("[Controller] Aborting transaction");
        // Additional cleanup logic would go here
    }

    /**
     * Request confirmation from tourist.
     * Sequence Diagram step: requestConfirmation()
     */
    public boolean requestConfirmation() {
        System.out.println("[Controller] Requesting confirmation");
        // Delegate to confirmation service
        return true;
    }

    /**
     * Confirm transaction.
     * Sequence Diagram step: confirmTransaction()
     */
    public void confirmTransaction() {
        System.out.println("[Controller] Confirming transaction");
        // Logic to confirm the transaction
        // This would typically update transaction status
    }

    /**
     * Store confirmation record.
     * Sequence Diagram step: storeConfirmation()
     */
    public void storeConfirmation() {
        System.out.println("[Controller] Storing confirmation");
        // Delegate to confirmation service
    }

    // Getters and setters
    public ITouristService getTouristService() {
        return touristService;
    }

    public void setTouristService(ITouristService touristService) {
        this.touristService = touristService;
    }

    public Object getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(Object authenticator) {
        this.authenticator = authenticator;
    }

    public Object getConfirmationService() {
        return confirmationService;
    }

    public void setConfirmationService(Object confirmationService) {
        this.confirmationService = confirmationService;
    }
}
