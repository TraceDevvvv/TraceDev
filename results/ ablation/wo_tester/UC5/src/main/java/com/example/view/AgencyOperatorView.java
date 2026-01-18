package com.example.view;

import com.example.controller.CulturalHeritageController;
import com.example.controller.SearchCulturalHeritageController;
import com.example.model.CulturalHeritageDTO;
import java.util.List;

/**
 * View component for Agency Operator interface.
 */
public class AgencyOperatorView {
    private CulturalHeritageController controller;
    private AuthenticationService authenticationService;
    private SearchCulturalHeritageController searchController;
    
    public AgencyOperatorView() {
        // In real implementation, dependencies would be injected
        this.authenticationService = new AuthenticationService();
        // Note: controller and searchController need to be set separately
    }
    
    public void setController(CulturalHeritageController controller) {
        this.controller = controller;
    }
    
    public void setSearchController(SearchCulturalHeritageController searchController) {
        this.searchController = searchController;
    }
    
    /**
     * Displays list of cultural heritage items.
     * Added to satisfy requirement for Flow of Events #1.
     * @param list list of CulturalHeritageDTO objects
     */
    public void displayHeritageList(List<CulturalHeritageDTO> list) {
        System.out.println("\n=== Cultural Heritage List ===");
        for (CulturalHeritageDTO item : list) {
            System.out.println(item.getId() + ": " + item.getName() + 
                             " - " + item.getLocation());
        }
        System.out.println("=============================\n");
    }
    
    /**
     * Handles heritage selection by user.
     * @param id the selected heritage ID
     */
    public void onHeritageSelected(String id) {
        System.out.println("Selected heritage with ID: " + id);
        
        // Check authentication - entry condition
        if (!authenticationService.isLoggedIn()) {
            displayError("User not authenticated");
            return;
        }
        
        try {
            CulturalHeritageDTO dto = controller.viewDetails(id);
            displayDetails(dto);
        } catch (Exception e) {
            displayError(e.getMessage());
        }
    }
    
    /**
     * Displays cultural heritage details.
     * @param dto CulturalHeritageDTO containing details
     */
    public void displayDetails(CulturalHeritageDTO dto) {
        System.out.println("\n=== Cultural Heritage Details ===");
        System.out.println("ID: " + dto.getId());
        System.out.println("Name: " + dto.getName());
        System.out.println("Description: " + dto.getDescription());
        System.out.println("Location: " + dto.getLocation());
        System.out.println("Historical Period: " + dto.getHistoricalPeriod());
        System.out.println("===============================\n");
    }
    
    /**
     * Displays error message.
     * @param message error message to display
     */
    public void displayError(String message) {
        System.err.println("ERROR: " + message);
    }
    
    /**
     * Simulates user viewing heritage list (from SearchCulturalHeritage).
     */
    public void viewHeritageList() {
        if (searchController == null) {
            displayError("Search controller not initialized");
            return;
        }
        
        List<CulturalHeritageDTO> list = searchController.getList();
        displayHeritageList(list);
    }
    
    /**
     * Views heritage list (from SearchCulturalHeritage) - sequence diagram m3
     */
    public void displayList() {
        // Displays list - sequence diagram m5
        viewHeritageList();
    }
    
    /**
     * Selects one heritage item - sequence diagram m6
     */
    public void selectHeritageItem(String id) {
        onHeritageSelected(id);
    }
    
    /**
     * Displays heritage details - sequence diagram m21
     */
    public void displayHeritageDetails(CulturalHeritageDTO dto) {
        displayDetails(dto);
    }
    
    /**
     * Displays error message - sequence diagram m31
     */
    public void displayErrorMessage(String message) {
        displayError(message);
    }
}