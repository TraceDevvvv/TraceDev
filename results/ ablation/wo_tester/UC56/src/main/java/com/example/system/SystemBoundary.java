package com.example.system;

import com.example.model.Tourist;
import com.example.initialization.SystemInitialization;

/**
 * System boundary that receives tourist requests.
 */
public class SystemBoundary {
    private SystemInitialization systemInitialization;

    public SystemBoundary(SystemInitialization systemInitialization) {
        this.systemInitialization = systemInitialization;
    }

    /**
     * Handles a tourist request (either beginSearch or beginAdvancedSearch).
     */
    public void handleTouristRequest(Tourist tourist) {
        System.out.println("SystemBoundary handling request from tourist: " + tourist.getName());
        // For simplicity, we assume any request leads to initialization
        systemInitialization.initializeSystem(tourist);
    }
    
    /**
     * Message: begin search / advanced search from TOURIST.
     */
    public void beginSearchOrAdvancedSearch() {
        System.out.println("System received begin search / advanced search.");
    }
}