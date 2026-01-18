package com.example;

import com.example.model.Tourist;
import com.example.controller.ViewSiteController;
import com.example.service.SiteService;
import com.example.service.SiteServiceInterface;
import com.example.repository.SiteRepository;
import com.example.repository.SiteRepositoryInterface;
import com.example.database.DatabaseConnection;
import com.example.view.SiteDetailsView;
import com.example.model.VisitedSitesList;
import com.example.model.FavoritesList;
import com.example.model.ResearchResults;
import java.util.Arrays;

/**
 * Main class to simulate the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Use Case Simulation ===");

        // Entry Conditions: Tourist authenticated and located in one of the lists
        Tourist tourist = new Tourist("user123", "John Doe", "password123");
        boolean authenticated = tourist.authenticate();
        if (!authenticated) {
            System.out.println("Tourist not authenticated. Exiting.");
            return;
        }
        System.out.println("Tourist authenticated successfully.");

        // Simulate that the tourist is in ResearchResults, VisitedSitesList, or FavoritesList
        ResearchResults results = new ResearchResults("historical sites", Arrays.asList("site1", "site2", "site3"));
        VisitedSitesList visited = new VisitedSitesList("user123", Arrays.asList("site2", "site4"));
        FavoritesList favorites = new FavoritesList("user123", Arrays.asList("site1"));

        // The site ID is already available from context (e.g., selected from list)
        String siteId = "site1";
        // Verify that siteId exists in at least one list (entry condition)
        if (!results.selectSiteFromResults(siteId).equals(siteId) &&
            !visited.selectSiteFromList(siteId).equals(siteId) &&
            !favorites.selectSiteFromList(siteId).equals(siteId)) {
            System.out.println("Site ID not found in any list. Exiting.");
            return;
        }

        // Setup dependencies as per class diagram
        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.connect();
        SiteRepositoryInterface repository = new SiteRepository(dbConnection);
        SiteServiceInterface service = new SiteService(repository);
        SiteDetailsView view = new SiteDetailsView();
        ViewSiteController controller = new ViewSiteController(service, view);

        // Tourist initiates the request (sequence diagram step)
        tourist.selectSiteCard(siteId);

        // Controller handles the request
        System.out.println("\n--- Processing view site request ---");
        controller.handleViewSiteRequest(siteId);

        dbConnection.disconnect();
        System.out.println("=== Simulation Complete ===");
    }
}