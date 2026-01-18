package com.tourist.presentation.controllers;

import com.tourist.app.dtos.TouristDto;
import com.tourist.app.handlers.GetTouristQueryHandler;
import com.tourist.app.handlers.UpdateTouristCommandHandler;
import com.tourist.app.interfaces.IAuthenticationService;
import com.tourist.app.queries.GetTouristQuery;
import com.tourist.app.commands.UpdateTouristCommand;

/**
 * Controller for tourist operations.
 */
public class TouristController {
    private UpdateTouristCommandHandler commandHandler;
    private GetTouristQueryHandler queryHandler;
    private IAuthenticationService authService;

    /**
     * Constructor.
     * @param cmdHandler command handler
     * @param qryHandler query handler
     * @param authService authentication service
     */
    public TouristController(UpdateTouristCommandHandler cmdHandler,
                             GetTouristQueryHandler qryHandler,
                             IAuthenticationService authService) {
        this.commandHandler = cmdHandler;
        this.queryHandler = qryHandler;
        this.authService = authService;
    }

    /**
     * Loads tourist data for a given id.
     * @param touristId the tourist id
     * @return the tourist DTO
     */
    public TouristDto LoadTouristData(String touristId) {
        // REQ-Q-001: response time constraint.
        long startTime = System.currentTimeMillis();

        // REQ-AUTH-001: verify tourist is authenticated.
        if (!authService.IsAuthenticated(touristId)) {
            throw new SecurityException("User not authenticated.");
        }

        GetTouristQuery query = new GetTouristQuery(touristId);
        TouristDto result = queryHandler.Handle(query);

        long endTime = System.currentTimeMillis();
        if ((endTime - startTime) > 5000) {
            System.err.println("Warning: LoadTouristData took more than 5 seconds.");
        }
        return result;
    }

    /**
     * Submits a profile update.
     * @param touristId the tourist id
     * @param updatedData the updated data
     * @return a status message
     */
    public String SubmitProfileUpdate(String touristId, TouristDto updatedData) {
        try {
            // REQ-Q-001: response time constraint.
            long startTime = System.currentTimeMillis();

            // REQ-AUTH-001: verify tourist is authenticated.
            if (!authService.IsAuthenticated(touristId)) {
                throw new SecurityException("User not authenticated.");
            }

            UpdateTouristCommand command = new UpdateTouristCommand(touristId, updatedData);
            commandHandler.Handle(command);

            long endTime = System.currentTimeMillis();
            if ((endTime - startTime) > 5000) {
                System.err.println("Warning: SubmitProfileUpdate took more than 5 seconds.");
            }
            // Sequence diagram shows return message: "Profile updated successfully"
            return "Profile updated successfully";
        } catch (UpdateTouristCommandHandler.ValidationException e) {
            // Sequence diagram shows "Throw ValidationException" leading to "Error Response".
            // Return error message as per "Display error message".
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            // Handle other exceptions (e.g., repository connection lost).
            // Sequence diagram shows "ServiceUnavailableException" and corresponding error message.
            return "Connection error. Please try again.";
        }
    }
}