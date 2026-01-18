package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BannerService {
    // Simulate database of refreshment points and banners
    private List<RefreshmentPoint> refreshmentPoints;
    private Scanner scanner;

    public BannerService() {
        refreshmentPoints = new ArrayList<>();
        scanner = new Scanner(System.in);
        initializeSampleData();
    }

    // Initialize with sample data for demonstration
    private void initializeSampleData() {
        List<Banner> banners1 = new ArrayList<>();
        banners1.add(new Banner("B1", "RP1", "/images/banner1.jpg"));
        banners1.add(new Banner("B2", "RP1", "/images/banner2.png"));
        RefreshmentPoint rp1 = new RefreshmentPoint("RP1", "Downtown Cafe", banners1);

        List<Banner> banners2 = new ArrayList<>();
        banners2.add(new Banner("B3", "RP2", "/images/banner3.gif"));
        RefreshmentPoint rp2 = new RefreshmentPoint("RP2", "Airport Lounge", banners2);

        refreshmentPoints.add(rp1);
        refreshmentPoints.add(rp2);
    }

    // Step 1: Search refreshment points (simplified)
    public List<RefreshmentPoint> searchRefreshmentPoints() {
        // In a real application, this would query a database
        return new ArrayList<>(refreshmentPoints);
    }

    // Step 4: Get banners for a refreshment point
    public List<Banner> getBannersForRefreshmentPoint(String refreshmentPointId) {
        for (RefreshmentPoint rp : refreshmentPoints) {
            if (rp.getId().equals(refreshmentPointId)) {
                return rp.getBanners();
            }
        }
        return new ArrayList<>();
    }

    // Step 10, 14: Validate and update banner image
    public boolean updateBannerImage(String bannerId, String newImagePath) {
        long startTime = System.currentTimeMillis();
        // Validate image
        if (!ImageValidator.validateImage(newImagePath)) {
            triggerErroredUseCase();
            return false;
        }

        // Find and update banner
        for (RefreshmentPoint rp : refreshmentPoints) {
            for (Banner banner : rp.getBanners()) {
                if (banner.getBannerId().equals(bannerId)) {
                    banner.setImagePath(newImagePath);
                    long endTime = System.currentTimeMillis();
                    // Quality requirement: within 5 seconds
                    if ((endTime - startTime) <= 5000) {
                        System.out.println("Banner image updated successfully.");
                    } else {
                        System.out.println("Update completed but exceeded 5-second requirement.");
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // Step 12: Trigger errored use case (simulated)
    private void triggerErroredUseCase() {
        System.out.println("Image validation failed. Triggering Errored use case...");
    }

    // Utility for user input
    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Clean up
    public void close() {
        scanner.close();
    }
}