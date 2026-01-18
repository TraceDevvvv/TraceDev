package com.example;

import com.example.agency.AgencyOperator;
import com.example.controller.ViewFeedbackController;
import com.example.datasource.DataSource;
import com.example.dto.SiteDTO;
import com.example.repository.FeedbackRepository;
import com.example.repository.FeedbackRepositoryImpl;
import com.example.service.FeedbackService;
import com.example.service.FeedbackServiceImpl;
import com.example.ui.UI;
import java.util.Arrays;
import java.util.List;

/**
 * Main class to demonstrate the runnable system.
 * Sets up dependencies and simulates the use case flow.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Create Agency Operator (already logged in)
        AgencyOperator operator = new AgencyOperator("john_doe", "password123");

        // 2. Setup data source (simulated)
        DataSource dataSource = new DataSource("jdbc:h2:mem:test", "sa", "");

        // 3. Create repository, service, controller, and UI
        FeedbackRepository repository = new FeedbackRepositoryImpl(dataSource);
        FeedbackService service = new FeedbackServiceImpl(repository);
        ViewFeedbackController controller = new ViewFeedbackController(service);
        UI ui = new UI(controller);

        // 4. Simulate the site list from SearchSite use case (step 1)
        List<SiteDTO> sites = Arrays.asList(
                new SiteDTO("S001", "Central Park", "New York"),
                new SiteDTO("S002", "Eiffel Tower", "Paris")
        );
        ui.displaySiteList(sites);

        // 5. Simulate the view feedback flow for a selected site (steps 2-4)
        String selectedSiteId = "S001";
        ui.simulateViewFeedbackFlow(selectedSiteId);

        // 6. Demonstrate error handling with connection exception
        System.out.println("\n--- Simulating connection error scenario ---");
        // This would occur if the repository throws a ConnectionException.
        // In a real scenario, we might inject a mock repository that throws.
        System.out.println("Error handling demonstrated in code. See ConnectionException class.");
    }
}