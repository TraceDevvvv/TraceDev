package com.tourism.presentation;

import com.tourism.application.ViewSitesController;
import com.tourism.dto.SiteDTO;
import java.util.List;

/**
 * Boundary class representing the UI for listing visited sites.
 * Displays sites as per the trace requirement.
 */
public class SiteListView {
    private ViewSitesController controller;

    public SiteListView(ViewSitesController controller) {
        this.controller = controller;
    }

    /**
     * Displays the list of visited sites.
     * <<trace>> The system displays a list of visited sites.
     * @param sites the list of SiteDTO objects to display
     */
    public void displaySites(List<SiteDTO> sites) {
        System.out.println("=== Visited Sites ===");
        if (sites.isEmpty()) {
            System.out.println("No sites found.");
        } else {
            for (SiteDTO site : sites) {
                System.out.println("- " + site.getName() + " (" + site.getLocation() + ") [ID: " + site.getSiteId() + "]");
            }
        }
        System.out.println("====================");
    }

    /**
     * Shows an error message to the user.
     * @param message the error message
     */
    public void showError(String message) {
        System.err.println("ERROR: " + message);
    }

    /**
     * Simulates the UI flow as described in the sequence diagram.
     * @param touristId the tourist ID (from session, REQ-006)
     */
    public void onViewVisitedSitesSelected(String touristId) {
        try {
            List<SiteDTO> sites = controller.getSitesForTourist(touristId);
            displaySites(sites);
        } catch (SecurityException e) {
            showError("Authentication failed");
        } catch (RuntimeException e) {
            showError("Connection lost");
        } catch (Exception e) {
            showError("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Message "select \"view visited sites\"" from the sequence diagram.
     * This method is called by the Tourist actor.
     */
    public void selectViewVisitedSites() {
        // This method represents the UI receiving the selection from Tourist.
        // Actual implementation will depend on the UI framework.
        // For now, we delegate to onViewVisitedSitesSelected with a default tourist ID.
        // In real application, touristId would come from session.
        onViewVisitedSitesSelected("TOURIST123");
    }

    /**
     * Message "show \"No sites found\"" from the sequence diagram.
     */
    public void showNoSitesFound() {
        System.out.println("No sites found.");
    }

    /**
     * Message "empty list" from the sequence diagram.
     * Returns an empty list of SiteDTO.
     */
    public List<SiteDTO> emptyList() {
        return new java.util.ArrayList<>();
    }

    /**
     * Note "Server connection interrupted" from the sequence diagram.
     * This is a note, so we create a method that logs the note.
     */
    public void serverConnectionInterrupted() {
        System.out.println("Note: Server connection interrupted");
    }
}