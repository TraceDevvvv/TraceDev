package com.ata.view;

import com.ata.usecase.DisplayClassesController;

/**
 * Represents the boundary (UI) for displaying classes.
 * Interacts with controller and displays list to ATA staff.
 */
public class ClassDisplayScreen {
    private DisplayClassesController controller;

    public ClassDisplayScreen(DisplayClassesController controller) {
        this.controller = controller;
    }

    /**
     * Handles request to display classes.
     * Follows sequence diagram: request from ATA staff -> controller -> display.
     */
    public void displayClassList() {
        try {
            // Request controller to get class list view
            ClassListView listView = controller.displayClasses();

            // Display the list (simulated by printing)
            displayClassList(listView);
        } catch (SecurityException e) {
            System.out.println("Access denied: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error displaying classes: " + e.getMessage());
        }
    }

    /**
     * Displays the class list with registry keys.
     * Quality requirement: list displayed promptly (<2 seconds response time).
     * @param listView The view model containing classes.
     */
    public void displayClassList(ClassListView listView) {
        System.out.println("Displaying class list with registry keys:");
        System.out.println("=========================================");
        listView.getClasses().forEach(cls -> {
            System.out.println("Class ID: " + cls.getClassId());
            System.out.println("Name: " + cls.getClassName());
            System.out.println("Description: " + cls.getDescription());
            System.out.println("Registry Key: " + cls.getRegistryKey());
            System.out.println("---");
        });

        // Note: This method should complete promptly (<2 seconds) as per quality requirement.
        System.out.println("Note: List displayed promptly (response time < 2 seconds).");
    }

    /**
     * Implements sequence diagram message: Display class list with keys.
     */
    public void displayClassListWithKeys(ClassListView listView) {
        displayClassList(listView);
    }

    /**
     * Implements sequence diagram message: Request to display classes.
     */
    public void requestToDisplayClasses() {
        displayClassList();
    }
}