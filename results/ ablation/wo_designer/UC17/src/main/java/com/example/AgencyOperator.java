package com.example;

import java.util.List;

public class AgencyOperator {
    private String username;
    private BannerService bannerService;

    public AgencyOperator(String username) {
        this.username = username;
        this.bannerService = new BannerService();
    }

    // Main method to change banner image following the flow of events
    public void changeBannerImage() {
        // Entry condition: Operator is logged (simulated by constructor)
        System.out.println("Agency Operator " + username + " logged in.");

        // Step 1: Receive list of refreshment points
        List<RefreshmentPoint> refreshmentPoints = bannerService.searchRefreshmentPoints();
        System.out.println("Available refreshment points:");
        for (int i = 0; i < refreshmentPoints.size(); i++) {
            RefreshmentPoint rp = refreshmentPoints.get(i);
            System.out.println((i + 1) + ". " + rp.getName() + " (ID: " + rp.getId() + ")");
        }

        // Step 2: Select one refreshment point
        String selectedRpId = null;
        try {
            int choice = Integer.parseInt(bannerService.getUserInput("Select a refreshment point (number): "));
            if (choice > 0 && choice <= refreshmentPoints.size()) {
                selectedRpId = refreshmentPoints.get(choice - 1).getId();
            } else {
                System.out.println("Invalid selection.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        // Step 3 & 4: Access editing and view banners
        List<Banner> banners = bannerService.getBannersForRefreshmentPoint(selectedRpId);
        if (banners.isEmpty()) {
            System.out.println("No banners found for selected refreshment point.");
            return;
        }
        System.out.println("Banners for selected refreshment point:");
        for (int i = 0; i < banners.size(); i++) {
            Banner b = banners.get(i);
            System.out.println((i + 1) + ". Banner ID: " + b.getBannerId() + ", Image: " + b.getImagePath());
        }

        // Step 5: Select a banner
        String selectedBannerId = null;
        try {
            int choice = Integer.parseInt(bannerService.getUserInput("Select a banner to edit (number): "));
            if (choice > 0 && choice <= banners.size()) {
                selectedBannerId = banners.get(choice - 1).getBannerId();
            } else {
                System.out.println("Invalid selection.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        // Step 6 & 7: Enter editing and display form for image selection
        System.out.println("Entering banner editing...");
        System.out.println("Form for image selection displayed.");

        // Step 8: Operator selects a picture
        String newImagePath = bannerService.getUserInput("Enter new image file path (e.g., /images/newbanner.jpg): ");

        // Step 9: Send request to change banner (implicitly done in update)

        // Step 11: Ask for confirmation
        String confirm = bannerService.getUserInput("Confirm change? (yes/no): ");
        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Change cancelled.");
            bannerService.close();
            return;
        }

        // Step 13: Operator confirms, system updates (Steps 10, 12, 14 handled in service)
        boolean success = bannerService.updateBannerImage(selectedBannerId, newImagePath);

        // Exit conditions
        if (success) {
            System.out.println("Banner modified successfully.");
        } else {
            System.out.println("Banner modification failed.");
        }

        // Simulate interruption of connection to server ETOUR
        System.out.println("Connection to server ETOUR interrupted.");

        bannerService.close();
    }
}