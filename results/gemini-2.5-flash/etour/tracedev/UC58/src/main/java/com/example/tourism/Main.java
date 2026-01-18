package com.example.tourism;

import com.example.tourism.application.SiteService;
import com.example.tourism.data.ISiteRepository;
import com.example.tourism.data.SiteRepositoryImpl;
import com.example.tourism.domain.SiteSourceArea;
import com.example.tourism.presentation.SiteDetailsView;
import com.example.tourism.session.UserSession;

/**
 * Main class to demonstrate the execution of the application flow,
 * adhering to the class and sequence diagrams.
 * This acts as the entry point and orchestrates the creation and interaction
 * of the different layers.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Starting Tourist Application Simulation...");

        // --- Setup Dependency Injection (Manual for this example) ---

        // 1. Data Access Layer
        // Simulate a database connection object. In a real app, this would be a proper connection pool or ORM context.
        Object mockDatabaseConnection = new Object();
        ISiteRepository siteRepository = new SiteRepositoryImpl(mockDatabaseConnection);

        // 2. Application/Service Layer
        SiteService siteService = new SiteService(siteRepository);

        // 3. Presentation Layer - Scenario 1: User tries to view site details from Research Results
        System.out.println("\n--- Scenario 1: Authenticated user views site from Research Results ---");
        UserSession.setAuthenticated(true); // Authenticate the user
        SiteDetailsView touristUI1 = new SiteDetailsView(siteService, SiteSourceArea.RESEARCH_RESULTS);
        touristUI1.selectSite("1"); // Select Site ID 1 (Eiffel Tower)

        // 4. Presentation Layer - Scenario 2: Unauthenticated user tries to view site details
        System.out.println("\n--- Scenario 2: Unauthenticated user tries to view site ---");
        UserSession.setAuthenticated(false); // De-authenticate the user
        SiteDetailsView touristUI2 = new SiteDetailsView(siteService, SiteSourceArea.FAVORITES);
        touristUI2.selectSite("2"); // Select Site ID 2 (Great Wall of China)

        // 5. Presentation Layer - Scenario 3: Authenticated user tries to view a non-existent site
        System.out.println("\n--- Scenario 3: Authenticated user views non-existent site from Visited Sites ---");
        UserSession.setAuthenticated(true); // Authenticate the user again
        SiteDetailsView touristUI3 = new SiteDetailsView(siteService, SiteSourceArea.VISITED_SITES);
        touristUI3.selectSite("999"); // Select a non-existent Site ID

        // 6. Presentation Layer - Scenario 4: Simulate connection interruption
        System.out.println("\n--- Scenario 4: Authenticated user, simulate ETOURConnectionException ---");
        // To reliably trigger the exception, we might need to modify `SiteService` for this test
        // For now, it has a 10% random chance to fail. Running multiple times might show it.
        // If it doesn't fail, it will just show the site details.
        UserSession.setAuthenticated(true);
        SiteDetailsView touristUI4 = new SiteDetailsView(siteService, SiteSourceArea.FAVORITES);
        System.out.println("Attempting to select site '1' with a chance of connection error...");
        touristUI4.selectSite("1");

        System.out.println("\nTourist Application Simulation Finished.");
    }
}