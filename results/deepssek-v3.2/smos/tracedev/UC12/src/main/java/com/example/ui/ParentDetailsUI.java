package com.example.ui;

import com.example.controllers.ParentManagementController;

/**
 * Boundary class representing the Parent Details UI.
 * Corresponds to ParentDetailsUI in the class diagram.
 */
public class ParentDetailsUI {
    private ParentManagementController controller;

    public ParentDetailsUI(ParentManagementController controller) {
        this.controller = controller;
    }

    /**
     * Displays parent details (simulated).
     * Corresponds to displayParentDetails(parentId : int) in class diagram.
     */
    public void displayParentDetails(int parentId) {
        System.out.println("ParentDetailsUI: Displaying details for parent ID " + parentId);
        // In a real UI, this would render the parent details view.
    }

    /**
     * Called when the administrator clicks the Parentela button.
     * Triggers the child management use case.
     * Corresponds to onParentelaButtonClick(parentId : int) in class diagram.
     */
    public void onParentelaButtonClick(int parentId) {
        System.out.println("ParentDetailsUI: Parentela button clicked for parent ID " + parentId);
        // Delegate to controller to display the child management form.
        Object formData = controller.displayChildManagementForm(parentId);
        // Simulate displaying the form.
        System.out.println(formData.toString());
    }
}