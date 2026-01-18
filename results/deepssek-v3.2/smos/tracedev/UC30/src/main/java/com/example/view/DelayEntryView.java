package com.example.view;

import com.example.controller.DateSelectionController;
import com.example.dto.DelayFormData;
import com.example.dto.LogDataDTO;
import com.example.dto.RegisterDelayRequest;
import com.example.gateway.ServerGateway;
import com.example.interactor.RegisterDelayInteractor;
import com.example.service.AuthenticationService;
import java.util.ArrayList;
import java.util.Date;

/**
 * View for entering delay data.
 * Implements interactions as per the sequence diagram.
 */
public class DelayEntryView {
    private DateSelectionController controller;
    private ServerGateway gateway;
    private AuthenticationService authService;
    private RegisterDelayInteractor interactor;
    private LogDataDTO currentData;
    
    public DelayEntryView(DateSelectionController controller, ServerGateway gateway, 
                          AuthenticationService authService, RegisterDelayInteractor interactor) {
        this.controller = controller;
        this.gateway = gateway;
        this.authService = authService;
        this.interactor = interactor;
    }
    
    /**
     * Displays the form with existing data (corresponds to sequence return message m9).
     * This method is called when the date is selected.
     * @param data the log data to display
     */
    public void displayData(LogDataDTO data) {
        this.currentData = data;
        // In a real implementation, update UI components.
        System.out.println("Displaying data for date: " + data.getDate());
        System.out.println("Total delays: " + data.getTotalDelays());
        // Note: The sequence diagram return message "Displays form with existing data" is implemented here.
    }
    
    /**
     * Gets the form data entered by the administrator.
     * This corresponds to note m10: "Administrator enters students with delay".
     * @return the form data
     */
    public DelayFormData getFormData() {
        // Simulated form data entry.
        DelayFormData formData = new DelayFormData();
        formData.setStudentId("S001");
        formData.setStudentName("John Doe");
        formData.setDelayDuration(15);
        formData.setParentEmail("parent@example.com");
        formData.setParentPhone("+1234567890");
        return formData;
    }
    
    /**
     * Shows a success message after saving.
     */
    public void showSuccessMessage() {
        System.out.println("Delay registered successfully!");
    }
    
    /**
     * Shows an error message.
     * @param message the error message
     */
    public void showErrorMessage(String message) {
        System.err.println("Error: " + message);
    }
    
    /**
     * Called when the administrator clicks the Save button (Step 3).
     * Coordinates the save process as per sequence diagram.
     */
    public void clickSave() {
        // Step 2: Administrator fills form (simulated by getFormData)
        DelayFormData formData = getFormData();
        
        // Convert form data to request
        RegisterDelayRequest request = new RegisterDelayRequest();
        request.setEntryDate(new Date());
        // For simplicity, assume one student delay from the form.
        // In a real scenario, multiple students could be added.
        ArrayList<com.example.dto.StudentDelayData> studentList = new ArrayList<>();
        com.example.dto.StudentDelayData studentData = new com.example.dto.StudentDelayData();
        studentData.setStudentId(formData.getStudentId());
        studentData.setStudentName(formData.getStudentName());
        studentData.setDelayDuration(formData.getDelayDuration());
        studentData.setParentEmail(formData.getParentEmail());
        studentData.setParentPhone(formData.getParentPhone());
        studentList.add(studentData);
        request.setStudents(studentList);
        
        // Step 4: System sends data to server
        boolean success = gateway.sendData(request); // Updated to match ServerGateway signature
        
        if (success) {
            // Quality requirement: Process and confirm within 3 seconds (handled by interactor)
            // Step 5: System displays updated log data
            controller.loadDataForDate(request.getEntryDate());
            showSuccessMessage();
            // Note: Return message "success = true" in sequence diagram corresponds to this flow.
        } else {
            showErrorMessage("Server gateway returned false");
        }
    }
}