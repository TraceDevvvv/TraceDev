
package com.example.controller;

import com.example.model.*;
import com.example.service.TeachingAssignmentService;
import com.example.view.TeachingAssignmentForm;
import java.util.List;

/**
 * Controller that coordinates between the form and the service.
 * Based on the UML class diagram and sequence diagram interactions.
 */
public class TeachingAssignmentController {
    private TeachingAssignmentForm form;
    private TeachingAssignmentService teachingService;

    public TeachingAssignmentController(TeachingAssignmentService teachingService) {
        this.teachingService = teachingService;
        this.form = new TeachingAssignmentForm(this);
    }

    public TeachingAssignmentForm displayForm(String addressId) {
        // Load current teachings for the address
        List<Teaching> currentTeachings = teachingService.loadAddressTeachings(addressId);
        // Load all available teachings
        List<Teaching> availableTeachings = teachingService.loadAvailableTeachings();
        // Create form with data (assuming form is already instantiated)
        form.setAddress(teachingService.getAddress(addressId)); // Assuming a method to get address
        form.setAvailableTeachings(availableTeachings);
        form.setCurrentTeachings(currentTeachings);
        return form;
    }

    public void handleFormSubmit(List<Teaching> selectedTeachings) {
        // Extract teaching IDs
        List<String> teachingIds = selectedTeachings.stream()
                .map(Teaching::getTeachingId)
                .toList();
        // Get address from form
        String addressId = form.getAddress().getAddressId();
        boolean success = teachingService.associateTeachings(addressId, teachingIds);
        if (success) {
            System.out.println("Teaching association successful.");
        } else {
            System.out.println("Teaching association failed.");
        }
        // After submit, return to address details view (as per sequence diagram)
        showAddressDetails(addressId);
    }

    public void showAddressDetails(String addressId) {
        // Load address details and current teachings
        Address address = teachingService.getAddress(addressId);
        List<Teaching> currentTeachings = teachingService.loadAddressTeachings(addressId);
        // Update form with updated details (or could be displayed elsewhere)
        form.setAddress(address);
        form.setCurrentTeachings(currentTeachings);
        System.out.println("Returning to address details view for address: " + address.getDetails());
    }

    public void cancelOperation() {
        // Handle administrator interrupt
        System.out.println("Operation cancelled by administrator.");
        teachingService.handleConnectionLoss();
        // Return to address details view (assuming we have the address ID from form)
        if (form.getAddress() != null) {
            showAddressDetails(form.getAddress().getAddressId());
        }
    }

    public void handleError(String error) {
        System.out.println("Error occurred: " + error);
        // Propagate error to form
        form.displayError(error);
    }

    // Method for handling removal request (from sequence diagram alt branch)
    public void handleRemovalRequest(List<String> deselectedIds) {
        String addressId = form.getAddress().getAddressId();
        boolean success = teachingService.removeTeachings(addressId, deselectedIds);
        if (success) {
            System.out.println("Teaching removal successful.");
        } else {
            System.out.println("Teaching removal failed.");
        }
    }

    // Getters and setters
    public TeachingAssignmentForm getForm() { return form; }
    public void setForm(TeachingAssignmentForm form) { this.form = form; }
    public TeachingAssignmentService getTeachingService() { return teachingService; }
    public void setTeachingService(TeachingAssignmentService teachingService) { this.teachingService = teachingService; }
}
