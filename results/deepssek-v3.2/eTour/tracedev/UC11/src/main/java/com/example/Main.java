package com.example;

import com.example.ui.ConventionHistoryController;
import com.example.ui.ConventionHistoryView;
import com.example.application.ViewConventionHistoryInteractor;
import com.example.infrastructure.*;
import com.example.domain.AgencyService;

/**
 * Main class to set up the dependency graph and simulate the use case.
 */
public class Main {
    public static void main(String[] args) {
        // Infrastructure layer
        EtourServerClient client = new EtourServerClient();
        ConventionRepository etourRepo = new EtourConventionRepository(client);
        ConventionRepository cachedRepo = new CachingConventionRepository(etourRepo);

        // Domain service
        AgencyService agencyService = new AgencyService();

        // Application layer
        ViewConventionHistoryInteractor interactor = new ViewConventionHistoryInteractor(cachedRepo, agencyService);

        // UI layer
        ConventionHistoryController controller = new ConventionHistoryController(interactor, agencyService, null);
        ConventionHistoryView view = new ConventionHistoryView(controller);

        // Simulate the agency operator triggering the flow
        System.out.println("Simulating Agency Operator accessing historical conventions display...");
        controller.displayConventionHistory("point123", "agency456");
    }
}
