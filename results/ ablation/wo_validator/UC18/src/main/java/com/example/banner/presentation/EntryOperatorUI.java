package com.example.banner.presentation;

import com.example.banner.application.CheckBannerCountController;
import com.example.banner.application.CheckBannerCountResult;
import com.example.banner.application.NotificationService;
import com.example.banner.infrastructure.ConventionDataAdapter;

/**
 * User interface for the entry operator.
 * Coordinates user actions and displays results.
 */
public class EntryOperatorUI {
    private CheckBannerCountController controller;
    private NotificationService notificationService;
    private ConventionDataAdapter adapter; // added to terminate connection as per sequence diagram

    /**
     * Constructor.
     * @param controller the banner count controller
     * @param notificationService the notification service
     */
    public EntryOperatorUI(CheckBannerCountController controller, NotificationService notificationService) {
        this.controller = controller;
        this.notificationService = notificationService;
        this.adapter = null; // can be set later if needed
    }

    /**
     * Sets the adapter for connection termination.
     * @param adapter the ConventionDataAdapter
     */
    public void setAdapter(ConventionDataAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * Loads refreshment point data (triggered by operator).
     * @param refreshmentPointId the point identifier
     */
    public void loadRefreshmentPoint(String refreshmentPointId) {
        System.out.println("UI: Loading refreshment point " + refreshmentPointId);
        // Simulate operator ID (could be passed from UI context)
        String operatorId = "OP001";
        CheckBannerCountResult result = controller.execute(operatorId, refreshmentPointId);

        if (result.canPlaceBanner()) {
            System.out.println("UI: " + result.getMessage());
            System.out.println("UI: Current banners: " + result.getCurrentCount() + ", Max: " + result.getMaxCount());
            // Display details to operator (simulated)
            displayNotification("Proceed with banner placement.");
        } else {
            System.out.println("UI: " + result.getMessage());
            // Send notification about limit exceeded
            notificationService.sendNotification("Banner limit exceeded");
            displayNotification("Banner limit exceeded. Cannot place new banner.");
        }

        // Terminate connection as per sequence diagram
        if (adapter != null) {
            adapter.terminateConnection();
        }
        System.out.println("UI: Control returned to user interface.");
    }

    /**
     * Displays a notification message to the operator.
     * @param message the message to display
     */
    public void displayNotification(String message) {
        System.out.println("UI Notification: " + message);
    }

    /**
     * Confirms notification (triggered by operator).
     */
    public void confirmNotification() {
        notificationService.acknowledgeNotification();
        System.out.println("UI: Notification confirmed by operator.");
    }

    /**
     * Recovers the previous system state.
     */
    public void recoverPreviousState() {
        // In a real system, this would rollback or restore state
        System.out.println("UI: Previous state recovered.");
    }
}