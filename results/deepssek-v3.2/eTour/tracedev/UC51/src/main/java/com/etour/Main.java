package com.etour;

import com.etour.application.AddSiteToBookmarksUseCase;
import com.etour.domain.BookmarkRepository;
import com.etour.infrastructure.BookmarkRepositoryImpl;
import com.etour.interfaceadapters.BookmarkController;
import com.etour.interfaceadapters.ResponseDTO;

/**
 * Main class that demonstrates the complete flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup the clean architecture layers
        BookmarkRepository repository = new BookmarkRepositoryImpl();
        AddSiteToBookmarksUseCase useCase = new AddSiteToBookmarksUseCase(repository);
        BookmarkController controller = new BookmarkController(useCase);

        // Simulate a tourist at a site (Entry Condition satisfied)
        String touristId = "tourist_123";
        String siteId = "site_456";

        System.out.println("=== Starting Use Case: Insert Site to Bookmarks ===");
        System.out.println("Tourist " + touristId + " is at site " + siteId);

        // Tourist activates the feature
        ResponseDTO result = controller.insertSite(touristId, siteId);

        System.out.println("\n=== Result ===");
        System.out.println("Status: " + result.getStatus());
        System.out.println("Message: " + result.getMessage());
        if (result.getData() != null) {
            System.out.println("Data: " + result.getData());
        }

        // Try adding the same site again (should fail with duplicate error)
        System.out.println("\n=== Attempting to add the same site again ===");
        ResponseDTO duplicateResult = controller.insertSite(touristId, siteId);
        System.out.println("Status: " + duplicateResult.getStatus());
        System.out.println("Message: " + duplicateResult.getMessage());
    }
}