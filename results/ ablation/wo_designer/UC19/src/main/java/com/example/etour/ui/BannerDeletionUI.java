package com.example.etour.ui;

import com.example.etour.model.Banner;
import com.example.etour.model.RefreshmentPoint;
import com.example.etour.service.BannerService;
import com.example.etour.service.RefreshmentPointService;
import java.util.List;
import java.util.Scanner;

/**
 * Console‑based UI for the Agency Operator to delete a banner.
 * Simulates the steps described in the use case.
 */
public class BannerDeletionUI {
    private RefreshmentPointService pointService;
    private BannerService bannerService;
    private Scanner scanner;

    public BannerDeletionUI() {
        pointService = new RefreshmentPointService();
        bannerService = new BannerService();
        scanner = new Scanner(System.in);
    }

    /**
     * Main interaction loop.
     * Assumes Agency Operator is already logged in.
     */
    public void start() {
        System.out.println("=== Agency Operator Banner Deletion ===");

        // Step 1: Show list of refreshment points (turning points)
        List<RefreshmentPoint> points = pointService.getAllRefreshmentPoints();
        System.out.println("Available Refreshment Points:");
        for (int i = 0; i < points.size(); i++) {
            RefreshmentPoint p = points.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (" + p.getLocation() + ")");
        }

        // Step 2: Agency Operator selects a point
        System.out.print("Select a point by number: ");
        int pointIndex = readInt(1, points.size()) - 1;
        RefreshmentPoint selectedPoint = points.get(pointIndex);
        System.out.println("Selected: " + selectedPoint.getName());

        // Step 3: Show banners for the selected point
        List<Banner> banners = bannerService.getBannersByRefreshmentPoint(selectedPoint.getId());
        if (banners.isEmpty()) {
            System.out.println("No banners associated with this point.");
            return;
        }
        System.out.println("Associated Banners:");
        for (int i = 0; i < banners.size(); i++) {
            Banner b = banners.get(i);
            System.out.println((i + 1) + ". " + b.getName() + " [ID: " + b.getId() + "]");
        }

        // Step 4: Agency Operator selects a banner
        System.out.print("Select a banner to delete by number: ");
        int bannerIndex = readInt(1, banners.size()) - 1;
        Banner selectedBanner = banners.get(bannerIndex);
        System.out.println("Selected Banner: " + selectedBanner);

        // Step 5 & 6: Ask for confirmation
        System.out.println("Are you sure you want to delete this banner? (yes/no)");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            // Step 7 & 8: Delete the banner
            boolean success = bannerService.deleteBanner(selectedBanner.getId());
            if (success) {
                System.out.println("SUCCESS: Banner has been deleted.");
                // Exit condition: successful elimination
                System.out.println("System will now disconnect from ETOUR server.");
            } else {
                System.out.println("ERROR: Could not delete banner.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
        scanner.close();
    }

    /**
     * Helper to read an integer within a range.
     */
    private int readInt(int min, int max) {
        while (true) {
            try {
                int val = Integer.parseInt(scanner.nextLine());
                if (val >= min && val <= max) {
                    return val;
                }
                System.out.print("Please enter a number between " + min + " and " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please try again: ");
            }
        }
    }
}