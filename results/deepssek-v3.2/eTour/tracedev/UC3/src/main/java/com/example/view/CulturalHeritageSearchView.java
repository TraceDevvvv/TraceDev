package com.example.view;

import com.example.dto.CulturalObjectDTO;
import com.example.controller.ChangeCulturalObjectController;
import com.example.service.SearchService;
import java.util.List;

/**
 * View for displaying and selecting cultural heritage objects.
 */
public class CulturalHeritageSearchView {
    private ChangeCulturalObjectController controller;
    private SearchService searchService;

    public CulturalHeritageSearchView(ChangeCulturalObjectController controller, SearchService searchService) {
        this.controller = controller;
        this.searchService = searchService;
    }

    public void displayCulturalObjects(List<CulturalObjectDTO> objects) {
        // Simulated display - in real implementation would update UI
        System.out.println("Displaying cultural objects:");
        for (CulturalObjectDTO obj : objects) {
            System.out.println(" - ID: " + obj.getId() + ", Name: " + obj.getName());
        }
    }

    public void onObjectSelected(int objectId) {
        // Called when user selects an object from the list
        controller.handleObjectSelection(objectId);
    }

    /**
     * Simulates user action: viewing list and selecting an object.
     */
    public void simulateUserInteraction() {
        // Load data from search service
        List<CulturalObjectDTO> results = searchService.getSearchResults();
        displayCulturalObjects(results);
        // Simulate selecting first object
        onObjectSelected(results.get(0).getId());
    }
    
    /**
     * Views list of cultural goods (from SearchCulturalHeritage).
     * Corresponds to sequence diagram message m3.
     */
    public void viewsListOfCulturalGoods() {
        List<CulturalObjectDTO> results = searchService.getSearchResults();
        displayCulturalObjects(results);
    }
    
    /**
     * Displays list.
     * Corresponds to sequence diagram message m4.
     */
    public void displaysList() {
        // This method is essentially the same as displayCulturalObjects.
        // We'll call the existing method.
        List<CulturalObjectDTO> results = searchService.getSearchResults();
        displayCulturalObjects(results);
    }
    
    /**
     * Selects one cultural object.
     * Corresponds to sequence diagram message m5.
     */
    public void selectsOneCulturalObject(int objectId) {
        onObjectSelected(objectId);
    }
}