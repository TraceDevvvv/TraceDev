package com.example.app.presentation;

import com.example.app.Presentation;
import com.example.app.dto.RegisterDetailsDTO;


public class RegisterDetailsView implements Presentation {

    private RegisterDetailsController controller; // Dependency on Controller

    public void setController(RegisterDetailsController controller) {
        this.controller = controller;
    }

 
    public void onDetailsButtonClick(String registerId) {
        System.out.println(" Admin: User clicked 'View Details' for Register ID: " + registerId);
        // Calls the controller to handle the request
        controller.requestRegisterDetails(registerId);
    }

  
    public void displayRegisterDetails(RegisterDetailsDTO dto) {
        System.out.println(" View: Displaying Register Details:");
        System.out.println(dto.toString());
        // In a real UI, this would render to a GUI component
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println(" View: ERROR - " + message);
        // In a real UI, this would show an alert box or error panel
    }
}