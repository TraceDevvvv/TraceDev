package com.example;

import com.example.model.Tourist;
import com.example.model.Site;
import com.example.repository.BookmarkRepository;
import com.example.repository.BookmarkRepositoryImpl;
import com.example.interactor.RemoveBookmarkInteractor;
import com.example.controller.BookmarkController;
import com.example.ui.TouristUI;
import com.example.interactor.RemoveBookmarkRequest;
import com.example.interactor.RemoveBookmarkResponse;

/**
 * Main class to simulate the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Simulating Bookmark Removal Use Case ===\n");

        // Setup
        BookmarkRepository repository = new BookmarkRepositoryImpl();
        RemoveBookmarkInteractor interactor = new RemoveBookmarkInteractor(repository);
        BookmarkController controller = new BookmarkController(interactor);
        TouristUI ui = new TouristUI(controller);

        // Create a tourist and simulate they are at site1
        Tourist tourist = new Tourist("tourist1");
        tourist.visitSite("site1");
        Site.addSite("site1"); // Ensure site exists

        // Step 1: Tourist chooses to remove site from bookmarks
        System.out.println("1. Tourist chooses to remove site from bookmarks");
        ui.displayRemovalPrompt();

        // Step 2: Tourist confirms removal
        System.out.println("\n2. Tourist confirms removal");
        controller.confirmRemoval("tourist1", "site1");

        // Step 3: Simulate connection interruption scenario
        System.out.println("\n3. Simulating connection interruption...");
        ((BookmarkRepositoryImpl) repository).setConnectionAlive(false);
        RemoveBookmarkRequest request = new RemoveBookmarkRequest("tourist1", "site1");
        RemoveBookmarkResponse response = interactor.execute(request);
        ui.displayResult(response);

        // Step 4: Simulate cancellation
        System.out.println("\n4. Simulating cancellation...");
        controller.cancelRemoval();

        System.out.println("\n=== Simulation Complete ===");
    }
}