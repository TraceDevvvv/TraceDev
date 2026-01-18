package com.example.app.presentation;

import com.example.app.Presentation;
import com.example.app.application.RegisterService;
import com.example.app.application.ServiceUnavailableException;
import com.example.app.dto.RegisterDetailsDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controller class for handling user requests related to register details.
 * It mediates between the View and the Application Service.
 */
public class RegisterDetailsController implements Presentation {
    private RegisterService registerService; // Dependency on RegisterService
    private RegisterDetailsView view;        // Dependency on RegisterDetailsView

    /**
     * Constructs a RegisterDetailsController.
     * @param registerService The service responsible for business logic.
     * @param view The view responsible for displaying information.
     */
    public RegisterDetailsController(RegisterService registerService, RegisterDetailsView view) {
        this.registerService = registerService;
        this.view = view;
        // Ensure the view has a reference back to this controller if it needs to call back (e.g., for user input actions)
        this.view.setController(this);
    }

  
    public void requestRegisterDetails(String registerId) {
        System.out.println("Controller: Received request for Register ID: " + registerId);

        // Precondition: Administrator is logged in and has selected a register.
        // This logic is assumed to be handled by a higher-level module or authentication system
        // and is not explicitly implemented here.

        // Get today's date in "YYYY-MM-DD" format
        String todayDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        try {
            // Delegate to the application service to get the detailed DTO
            System.out.println("Controller: Calling RegisterService.getRegisterDetails...");
            RegisterDetailsDTO registerDetailsDTO = registerService.getRegisterDetails(registerId, todayDate);

            // If successful, display the details via the view
            System.out.println("Controller: RegisterService returned DTO. Displaying details...");
            view.displayRegisterDetails(registerDetailsDTO);

        } catch (ServiceUnavailableException e) {
            // Handle the specific case of external service interruption as per sequence diagram opt block
            System.err.println("Controller: Caught ServiceUnavailableException.");
            view.displayErrorMessage("Server connection interrupted: " + e.getMessage());
        } catch (Exception e) {
            // Generic error handling
            System.err.println("Controller: An unexpected error occurred: " + e.getMessage());
            view.displayErrorMessage("Failed to retrieve register details due to an unexpected error.");
        }
    }
}