package com.example.presentation;

/**
 * Service for managing the context of the selected registry.
 * Added to satisfy requirement R4.
 */
public class RegistrySelectionService {

    /**
     * Retrieves the context of the currently selected registry.
     * This typically means the ID of the class registry that the user previously selected.
     *
     * @return The ID of the selected class registry.
     */
    public String getSelectedRegistryContext() {
        // --- Assumption: For simulation purposes, return a dummy selected class ID. ---
        System.out.println("[RegistrySelectionService] Retrieving selected registry context... Context 'class101' (R4).");
        return "class101"; // Example class ID
    }
}