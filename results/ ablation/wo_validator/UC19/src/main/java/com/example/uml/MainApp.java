package com.example.uml;

/**
 * Main application class to simulate the sequence diagram flow.
 * This class serves as an entry point and simulates the UI layer and actor interactions.
 */
public class MainApp {
    public static void main(String[] args) {
        // Setup components as per class diagram.
        BannerRepository repository = new BannerRepository();
        BannerService service = new BannerService(repository);
        NotificationService notificationService = new NotificationService();
        DeleteBannerController controller = new DeleteBannerController(service, notificationService);

        // Simulate Agency Operator.
        AgencyOperator operator = new AgencyOperator("U001", "john_doe", "OP001", "AG001");
        operator.login();

        // Simulate the sequence diagram steps.
        System.out.println("\n--- Sequence Diagram Simulation ---");
        // Step 1 & 2: Operator selects refreshment point.
        String refreshmentPointId = "RP001";
        RefreshmentPoint rp = operator.selectRefreshmentPoint(refreshmentPointId);

        // Step 3: Controller accesses banner removal function.
        controller.displayBanners(refreshmentPointId);

        // Step 4 & 5: UI returns banner list (simulated).
        System.out.println("Banners displayed to operator.");

        // Step 6: Operator selects a banner.
        String selectedBannerId = "B001";
        operator.selectBanner(selectedBannerId);

        // Step 7: Operator enters banner elimination function.
        // Step 8: Controller confirms deletion.
        boolean confirm = controller.confirmDeletion(selectedBannerId);
        if (confirm) {
            System.out.println("Deletion confirmed.");
        } else {
            System.out.println("Deletion not confirmed.");
            return;
        }

        // Step 9: Operator confirms deletion operation (simulated via controller).
        controller.performDeletion(selectedBannerId);

        // Simulate alternative flow: connection interruption.
        System.out.println("\n--- Simulating Connection Interruption ---");
        try {
            // Force a connection exception by passing a banner ID that triggers a simulated error.
            controller.performDeletion("ERROR_BANNER");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        operator.logout();
        System.out.println("\n--- Simulation Complete ---");
    }
}