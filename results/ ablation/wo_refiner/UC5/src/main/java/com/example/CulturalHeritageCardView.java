package com.example;

/**
 * Boundary class for displaying cultural heritage details.
 * Implements error display per requirement REQ-009.
 */
public class CulturalHeritageCardView {
    private CulturalHeritageController culturalHeritageController;

    public CulturalHeritageCardView() {
        this.culturalHeritageController = new CulturalHeritageController();
    }

    public CulturalHeritageCardView(CulturalHeritageController culturalHeritageController) {
        this.culturalHeritageController = culturalHeritageController;
    }

    /**
     * Displays details of a cultural heritage item.
     */
    public void displayDetails(CulturalHeritage culturalHeritage) {
        clearDisplay();
        System.out.println("=== Cultural Heritage Details ===");
        System.out.println("ID: " + culturalHeritage.getId());
        System.out.println("Name: " + culturalHeritage.getName());
        System.out.println("Description: " + culturalHeritage.getDescription());
        System.out.println("Location: " + culturalHeritage.getLocation());
        System.out.println("Historical Period: " + culturalHeritage.getHistoricalPeriod());
        System.out.println("Images: " + String.join(", ", culturalHeritage.getImages()));
        System.out.println("================================");
    }

    /**
     * Displays an error message.
     */
    public void displayErrorMessage(String errorMessage) {
        clearDisplay();
        System.out.println("=== ERROR ===");
        System.out.println(errorMessage);
        System.out.println("=============");
    }

    /**
     * Clears the display area.
     */
    public void clearDisplay() {
        System.out.println("Clearing display...");
        // In a real UI, this would clear the screen or panel
    }
}