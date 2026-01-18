package com.example.bookmarking;

import com.example.bookmarking.application.BookmarkApplicationService;
import com.example.bookmarking.domain.Site;
import com.example.bookmarking.domain.Tourist;
import com.example.bookmarking.infrastructure.ETOURBookmarkRepository;
import com.example.bookmarking.infrastructure.NotificationService;
import com.example.bookmarking.presentation.TouristUI;

/**
 * Main class to demonstrate the bookmark insertion use case
 * as described in the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Bookmark Insertion Use Case Demonstration ---");

        // 1. Setup Infrastructure Layer components
        ETOURBookmarkRepository etourBookmarkRepository = new ETOURBookmarkRepository();
        NotificationService notificationService = new NotificationService();

        // 2. Setup Application Layer components
        // Inject infrastructure dependencies into the application service
        BookmarkApplicationService bookmarkApplicationService =
                new BookmarkApplicationService(etourBookmarkRepository, notificationService);

        // 3. Setup Presentation Layer components
        // Inject application service dependency into the UI
        TouristUI touristUI = new TouristUI(bookmarkApplicationService);

        // 4. Simulate a Tourist and a Site (Domain Layer objects)
        Tourist tourist = new Tourist("T123", "Alice Smith");
        Site selectedSite = new Site("S456", "Eiffel Tower", "Paris, France");

        System.out.println("\n--- Simulating User Interaction ---");

        // Step 1: Tourist initiates bookmarking action via UI
        // T -> UI : activateInsertionFeature(currentSite : Site) - Renamed from activateBookmarkInsertion
        System.out.println("\n--- T -> UI : activateInsertionFeature ---");
        System.out.println("Tourist '"+ tourist.name +"' selects '"+ selectedSite.name +"' on their TouristCard.");
        touristUI.activateInsertionFeature(selectedSite);
        // Note: currentSite is the Site object representing the TouristCard's location, available in UI.

        // Step 2: UI displays prompt
        // UI -> T : displayBookmarkInsertionPrompt()
        // (This call was made inside activateInsertionFeature)

        // Step 3: Tourist confirms insertion
        // T -> UI : confirmBookmarkInsertion()
        System.out.println("\n--- T -> UI : confirmBookmarkInsertion ---");
        System.out.println("Tourist '"+ tourist.name +"' decides to bookmark '"+ selectedSite.name +"'.");
        // Simulate the tourist's ID being available (e.g., from session)
        String touristIdFromSession = tourist.getTouristId();
        touristUI.confirmBookmarkInsertion(touristIdFromSession);

        System.out.println("\n--- End of Bookmark Insertion Use Case Demonstration ---");
    }
}