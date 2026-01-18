
package com.example.ui;

import com.example.controller.TouristController;
import com.example.dto.TouristCardDTO;
import com.example.dto.TouristDTO;
import java.util.List;

/**
 * Boundary class representing the User Interface (simulated).
 * As per sequence diagram, UI interacts with Controller.
 */
public class TouristUI {
    private TouristController controller;

    public TouristUI() {
        controller = new TouristController();
    }

    /**
     * Simulates activation of use case SearchTourist by Agency Operator.
     * This method mirrors the sequence diagram steps.
     */
    public void activateSearchTourist() {
        System.out.println("=== Tourist Search Use Case ===");
        // AO -> UI: activate use case SearchTourist
        // UI -> Controller: searchTourists("")
        List<TouristDTO> tourists = controller.searchTourists("");
        // UI --> AO: display list of Tourists
        displayTouristList(tourists);
    }

    private void displayTouristList(List<TouristDTO> tourists) {
        // Implementation to display the list
        System.out.println("Tourist List:");
        for (TouristDTO tourist : tourists) {
            System.out.println(tourist);
        }
    }
}
