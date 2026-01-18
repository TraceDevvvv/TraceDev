package com.example.view;

import com.example.controller.TeachingAssignmentController;
import com.example.model.Address;
import com.example.model.Teaching;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UI form for assigning/removing teachings from an address.
 * Based on the UML class diagram and sequence diagram interactions.
 */
public class TeachingAssignmentForm {
    private Address address;
    private List<Teaching> availableTeachings;
    private List<Teaching> currentTeachings;
    private TeachingAssignmentController controller;
    private List<String> selectedIds = new ArrayList<>();

    public TeachingAssignmentForm(TeachingAssignmentController controller) {
        this.controller = controller;
        this.availableTeachings = new ArrayList<>();
        this.currentTeachings = new ArrayList<>();
    }

    public void loadForm(String addressId) {
        // This method is called by the controller to load data into the form.
        // In a real UI, this would populate form fields.
        System.out.println("Form loaded for address ID: " + addressId);
    }

    public void selectTeachings(List<String> selectedIds) {
        // Add selected teaching IDs to the list
        this.selectedIds.addAll(selectedIds);
        System.out.println("Selected teachings: " + selectedIds);
    }

    public void deselectTeachings(List<String> deselectedIds) {
        // Remove deselected teaching IDs from the list
        this.selectedIds.removeAll(deselectedIds);
        System.out.println("Deselected teachings: " + deselectedIds);
        // Notify controller about removal request (as per sequence diagram)
        controller.handleRemovalRequest(deselectedIds);
    }

    public List<Teaching> getSelectedTeachings() {
        // Return Teaching objects for selected IDs from availableTeachings
        return availableTeachings.stream()
                .filter(t -> selectedIds.contains(t.getTeachingId()))
                .collect(Collectors.toList());
    }

    public void submit() {
        // Called when "Send" button is clicked
        List<Teaching> selected = getSelectedTeachings();
        controller.handleFormSubmit(selected);
    }

    public void cancelOperation() {
        // Called when administrator cancels the operation
        System.out.println("Form operation cancelled.");
        controller.cancelOperation();
    }

    public void displayError(String errorMessage) {
        System.out.println("Error displayed on form: " + errorMessage);
    }

    // Getters and setters
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    public List<Teaching> getAvailableTeachings() { return availableTeachings; }
    public void setAvailableTeachings(List<Teaching> availableTeachings) { this.availableTeachings = availableTeachings; }
    public List<Teaching> getCurrentTeachings() { return currentTeachings; }
    public void setCurrentTeachings(List<Teaching> currentTeachings) { this.currentTeachings = currentTeachings; }
    public TeachingAssignmentController getController() { return controller; }
    public void setController(TeachingAssignmentController controller) { this.controller = controller; }
}