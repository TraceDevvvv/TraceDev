package com.tourism;

import com.tourism.application.AuthenticationService;
import com.tourism.application.SiteService;
import com.tourism.application.ViewSitesController;
import com.tourism.infrastructure.ISiteRepository;
import com.tourism.infrastructure.SiteRepositoryImpl;
import com.tourism.presentation.SiteListView;

/**
 * Main class to simulate the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies (IoC container style)
        ISiteRepository repository = new SiteRepositoryImpl();
        AuthenticationService authService = new AuthenticationService();
        SiteService siteService = new SiteService(repository);
        ViewSitesController controller = new ViewSitesController(authService, siteService);
        SiteListView view = new SiteListView(controller);

        // Simulate the tourist selecting "view visited sites" (touristId from session, REQ‑006)
        String touristId = "TOURIST123"; // Simulated session tourist ID
        System.out.println("Tourist selects 'view visited sites' (touristId: " + touristId + ")");
        view.onViewVisitedSitesSelected(touristId);

        // Additional test cases
        System.out.println("\n--- Additional test cases ---");
        System.out.println("1. Invalid tourist ID:");
        view.onViewVisitedSitesSelected("INVALID");

        System.out.println("\n2. Tourist with no sites:");
        view.onViewVisitedSitesSelected("TOURIST999");

        System.out.println("\n3. Simulate connection interruption (may occur randomly):");
        // The repository randomly throws connection error (20% chance)
        view.onViewVisitedSitesSelected("TOURIST456");
    }
}