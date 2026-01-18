
package com.example.ui;

import com.example.controller.ViewConventionHistoryUseCaseController;
import com.example.dto.ViewConventionHistoryRequest;
import com.example.dto.ViewConventionHistoryResponse;
import com.example.model.Convention;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User Interface Layer that handles user interactions.
 * Added to satisfy requirements REQ-006, REQ-007, REQ-008.
 */
public class UILayer {
    private ViewConventionHistoryUseCaseController controller;

    public UILayer(ViewConventionHistoryUseCaseController controller) {
        this.controller = controller;
    }

    /**
     * Called when the user accesses the historical conventions feature.
     * Steps:
     * 1. Access historical conventions feature (triggered by user).
     * 2. Load designated point of rest ID from Agency context.
     * 3. Call controller with request.
     * 4. Process response and display results.
     */
    public void accessHistoricalConventionsFeature() {
        // Step 2: Load point of rest ID from Agency context.
        String pointOfRestId = loadPointOfRestId();
        // Assume agency ID is also needed; we'll use a default for now.
        String agencyId = "defaultAgencyId";
        
        ViewConventionHistoryRequest request = new ViewConventionHistoryRequest();
        request.setPointOfRestId(pointOfRestId);
        request.setAgencyId(agencyId);
        
        // Step 3: Execute use case.
        ViewConventionHistoryResponse response = controller.execute(request);
        
        if (response.isSuccess()) {
            // Step: Process and format conventions for display.
            List<Convention> conventions = response.getConventions();
            List<String> formattedConventions = processAndFormat(conventions);
            displayConventionHistory(conventions);
        } else {
            displayErrorMessage(response.getErrorMessage());
        }
    }

    /**
     * Loads the designated point of rest ID from the Agency context.
     * This is a simulation; in a real application, this would retrieve from a session or context.
     * @return the point of rest ID.
     */
    public String loadPointOfRestId() {
        // Simulate retrieving from an Agency object.
        Agency agency = new Agency();
        agency.setDesignatedPointOfRestId("POR123"); // Example ID
        return agency.getDesignatedPointOfRestId();
    }

    /**
     * Displays the convention history to the user.
     * @param conventions list of Convention objects.
     */
    public void displayConventionHistory(List<Convention> conventions) {
        // In a real UI, this would update the display.
        System.out.println("Convention History:");
        for (Convention convention : conventions) {
            System.out.println("  Convention ID: " + convention.getId());
            System.out.println("  Data: " + convention.getConventionData());
            System.out.println("  Timestamp: " + convention.getTimestamp());
        }
    }

    /**
     * Displays an error message to the user.
     * @param message the error message.
     */
    public void displayErrorMessage(String message) {
        // In a real UI, this would show an error dialog or message.
        System.err.println("Error: " + message);
    }

    /**
     * Processes and formats the list of conventions for display.
     * @param conventions list of Convention objects.
     * @return list of formatted strings.
     */
    public List<String> processAndFormat(List<Convention> conventions) {
        // Simple formatting: convert each convention to a string.
        return conventions.stream()
                .map(c -> c.getId() + " - " + c.getConventionData() + " at " + c.getTimestamp())
                .collect(Collectors.toList());
    }

    // Inner class for Agency to avoid separate file dependency in this example.
    // In a real project, Agency would be in its own file (as generated above).
    static class Agency {
        private String designatedPointOfRestId;
        public String getDesignatedPointOfRestId() {
            return designatedPointOfRestId;
        }
        public void setDesignatedPointOfRestId(String pointOfRestId) {
            this.designatedPointOfRestId = pointOfRestId;
        }
    }
}
