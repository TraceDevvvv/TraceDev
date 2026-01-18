package com.example;

/**
 * Controller that manages the bookmark insertion use case.
 */
public class UseCaseController {
    private SiteRepository repository;

    public UseCaseController() {
        // Assuming SiteRepositoryImpl as the concrete implementation
        repository = new SiteRepositoryImpl();
    }

    /**
     * Inserts a bookmark for a given site ID.
     * Returns true if successful, false otherwise.
     */
    public boolean insertBookmark(String siteId) {
        promptInclusion();
        boolean confirmed = confirmTouristInput();
        if (confirmed) {
            boolean inserted = repository.insertSite(siteId);
            if (inserted) {
                NotificationService notificationService = new NotificationService();
                notificationService.displayInsertionNotification("Bookmark added");
                cleanup();
                System.out.println("Operation complete.");
                return true;
            } else {
                System.out.println("Bookmark insertion failed at repository.");
                return false;
            }
        } else {
            System.out.println("Operation cancelled by tourist.");
            return false;
        }
    }

    /**
     * Prompts the tourist for inclusion.
     */
    public void promptInclusion() {
        System.out.println("Controller: Prompting tourist for bookmark inclusion...");
    }

    /**
     * Simulates confirming input from the tourist.
     * In a real scenario, this would interact with the Tourist object.
     */
    public boolean confirmTouristInput() {
        // Simulate tourist confirmation
        Tourist tourist = new Tourist("T001", "John Doe"); // Example tourist
        tourist.confirmInsertion();
        return true; // Assuming tourist always confirms
    }

    /**
     * Cleanup after operation, e.g., in case of connection interruption.
     */
    public void cleanup() {
        System.out.println("Controller: Cleaning up resources...");
    }
}