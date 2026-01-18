package com.etour;

import com.etour.controller.BannerInsertionController;
import com.etour.model.RefreshmentPoint;
import java.util.List;

/**
 * Main application to simulate the Agency Operator inserting a banner.
 */
public class MainApplication {
    public static void main(String[] args) {
        // Simulate that Agency Operator HAS logged in (entry condition).
        System.out.println("Agency Operator logged in.");

        // Simulate list of refreshment points received from use case SearchRefreshmentPoint.
        List<RefreshmentPoint> refreshmentPoints = List.of(
                new RefreshmentPoint(1, "Cafe Central", "Main Street", 5),
                new RefreshmentPoint(2, "Rest Stop North", "Highway 101", 3),
                new RefreshmentPoint(3, "Mountain View Diner", "Route 66", 7)
        );

        // Start the banner insertion flow.
        BannerInsertionController controller = new BannerInsertionController();
        controller.startBannerInsertion(refreshmentPoints);

        System.out.println("Banner insertion process completed.");
    }
}