package com.example.banner;

import com.example.banner.application.CheckBannerCountController;
import com.example.banner.application.IRefreshmentPointRepository;
import com.example.banner.application.NotificationService;
import com.example.banner.infrastructure.ConventionDataAdapter;
import com.example.banner.infrastructure.RefreshmentPointRepositoryImpl;
import com.example.banner.presentation.EntryOperatorUI;

/**
 * Main class to run the banner management system.
 * Simulates the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Banner Management System Simulation ===\n");

        // Infrastructure setup
        ConventionDataAdapter adapter = new ConventionDataAdapter();
        IRefreshmentPointRepository repository = new RefreshmentPointRepositoryImpl(adapter);

        // Application layer
        CheckBannerCountController controller = new CheckBannerCountController(repository);
        NotificationService notificationService = new NotificationService();

        // Presentation layer
        EntryOperatorUI ui = new EntryOperatorUI(controller, notificationService);
        ui.setAdapter(adapter);

        // Simulate operator action: load refreshment point data
        System.out.println("\n--- Scenario 1: Load refreshment point (limit not exceeded) ---");
        ui.loadRefreshmentPoint("RP001");

        // Simulate operator confirming notification (for limit exceeded scenario)
        System.out.println("\n--- Scenario 2: Operator confirms notification ---");
        ui.confirmNotification();

        // Simulate recovering previous state
        System.out.println("\n--- Scenario 3: Recover previous state ---");
        ui.recoverPreviousState();

        System.out.println("\n=== Simulation complete ===");
    }
}