package com.example.ui;

import com.example.model.Administrator;
import com.example.controller.TeachingManagementController;
import com.example.dto.TeachingListDTO;
import com.example.dto.TeachingDTO;

/**
 * Boundary class representing the management user interface.
 * Interacts with the administrator and the controller.
 */
public class ManagementUI {
    private TeachingManagementController controller;

    public ManagementUI(TeachingManagementController controller) {
        this.controller = controller;
    }

    /**
     * Simulates navigation to the management screen.
     * Step 1: Administrator looks for the archive and teacher screen.
     */
    public void navigateToManagementScreen() {
        System.out.println("Navigating to Management Screen...");
    }

    /**
     * Shows the teaching list screen to the administrator.
     * Step 1 (continued): UI shows the teaching list screen.
     */
    public void showTeachingListScreen() {
        System.out.println("Showing Teaching List Screen...");
    }

    /**
     * Requests the teaching list from the controller.
     * This method corresponds to the sequence diagram message "requests teaching list".
     */
    public void requestTeachingList() {
        TeachingListDTO teachingList = controller.getTeachingList();
        displayTeachingList(teachingList);
    }

    /**
     * Displays the teaching list to the administrator.
     * Step 2-4: System shows the list.
     * @param teachingList The teaching list data to display.
     */
    public void displayTeachingList(TeachingListDTO teachingList) {
        if (teachingList == null || teachingList.getTeachings() == null) {
            System.out.println("No teachings to display.");
            return;
        }
        System.out.println("=== Teaching List ===");
        for (TeachingDTO dto : teachingList.getTeachings()) {
            System.out.println("ID: " + dto.getId() + ", Title: " + dto.getTitle() +
                    ", Description: " + dto.getDescription() + ", Status: " + dto.getStatus());
        }
        System.out.println("=====================");
    }

    /**
     * Handles the administrator's request to view the management screen.
     * This method orchestrates the entire flow as per the sequence diagram.
     * @param admin The administrator making the request.
     */
    public void requestManagementView(Administrator admin) {
        // Validate entry conditions.
        if (!controller.validateAdmin(admin)) {
            System.out.println("Access denied: Administrator not logged in or does not have required role.");
            return;
        }

        // Proceed with the main sequence.
        showTeachingListScreen();
        TeachingListDTO teachingList = controller.getTeachingList();
        displayTeachingList(teachingList);
    }
}