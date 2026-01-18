package com.example.controller;

import com.example.application.CommandResult;
import com.example.application.InsertCulturalGoodCommand;
import com.example.application.InsertCulturalGoodCommandHandler;
import com.example.authentication.AuthenticationService;
import com.example.domain.CulturalGoodType;
import com.example.domain.GeoPoint;
import com.example.domain.Location;
import com.example.presentation.FormData;
import com.example.presentation.FormModel;
import com.example.presentation.RedirectView;
import com.example.presentation.ViewModel;
import com.example.presentation.LocationData;

import java.util.UUID;

/**
 * Controller for cultural good operations.
 */
public class CulturalGoodController {
    private InsertCulturalGoodCommandHandler commandHandler;
    private AuthenticationService authenticationService;
    
    public CulturalGoodController(InsertCulturalGoodCommandHandler handler, AuthenticationService authService) {
        this.commandHandler = handler;
        this.authenticationService = authService;
    }
    
    /**
     * Shows the insert form.
     * Corresponds to sequence diagram step 2-3.
     * @return FormModel for the empty insert form
     */
    public FormModel showInsertForm() {
        // In a real web app, this might also check authentication.
        return new FormModel();
    }
    
    /**
     * Handles form submission.
     * Corresponds to sequence diagram step 7-25.
     * @param formData data from the form
     * @return ViewModel with result
     */
    public ViewModel submitInsertForm(FormData formData) {
        // Step 8: Create InsertCulturalGoodCommand from formData
        LocationData locData = formData.getLocationData();
        GeoPoint geoPoint = new GeoPoint(locData.getLatitude(), locData.getLongitude());
        Location location = new Location(locData.getAddress(), geoPoint);
        
        InsertCulturalGoodCommand command = new InsertCulturalGoodCommand(
            formData.getName(),
            formData.getDescription(),
            formData.getType(),
            location
        );
        
        // Step 9: Call command handler
        CommandResult result = commandHandler.handle(command);
        
        // Step 24: Return appropriate ViewModel
        if (result.isSuccess()) {
            return new ViewModel(true, "Proper inclusion notification. Cultural good ID: " + result.getCulturalGoodId());
        } else {
            return new ViewModel(false, result.getMessage());
        }
    }
    
    /**
     * Handles cancellation of the insert operation.
     * Corresponds to sequence diagram Flow 3.
     * @return RedirectView to the list page
     */
    public RedirectView handleCancel() {
        // Exit Condition: Operator cancels the operation.
        return new RedirectView("/cultural-goods/list");
    }
}