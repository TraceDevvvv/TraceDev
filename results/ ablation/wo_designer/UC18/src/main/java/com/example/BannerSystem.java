package com.example;

import java.util.Scanner;

/**
 * System that manages banner verification for a refreshment point.
 */
public class BannerSystem {
    private RefreshmentPoint refreshmentPoint;
    private int maxAllowedBanners;
    private Scanner scanner;

    public BannerSystem(RefreshmentPoint refreshmentPoint, int maxAllowedBanners) {
        this.refreshmentPoint = refreshmentPoint;
        this.maxAllowedBanners = maxAllowedBanners;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main verification flow as per use case.
     */
    public void verifyBannerNumber() {
        // 1. Entry Operator loads the data of the Convention of refreshment point.
        System.out.println("\n=== Loading convention data ===");
        System.out.println("Convention: " + refreshmentPoint.getConventionName());
        System.out.println("Current banners: " + refreshmentPoint.getCurrentBannerCount());
        
        // Quality requirement: verification within 2 seconds
        long startTime = System.currentTimeMillis();
        
        // 2. System verifies that the number of banners is less than the specified number.
        if (refreshmentPoint.getCurrentBannerCount() < maxAllowedBanners) {
            // Allow new banner addition
            System.out.println("Verification passed: Current banners (" + 
                refreshmentPoint.getCurrentBannerCount() + 
                ") are less than maximum allowed (" + maxAllowedBanners + ").");
            
            // Simulate Agency IS wanting to put a new banner
            System.out.println("\nAgency IS wants to put a new banner.");
            System.out.println("Enter banner ID for the new banner:");
            String bannerId = scanner.nextLine();
            
            Banner newBanner = new Banner("Agency_IS", bannerId);
            refreshmentPoint.addBanner(newBanner);
            
            // Display updated count
            System.out.println("New banner added successfully.");
            System.out.println("Updated banner count: " + refreshmentPoint.getCurrentBannerCount());
        } else {
            // 3. If the number of banners is not less than the specified number, 
            // the system ends the operation input.
            System.out.println("Verification failed: Current banners (" + 
                refreshmentPoint.getCurrentBannerCount() + 
                ") are NOT less than maximum allowed (" + maxAllowedBanners + ").");
            
            // 4. System displays a notification.
            System.out.println("\n=== NOTIFICATION ===");
            System.out.println("Maximum number of banners reached. Cannot add new banner.");
            
            // 5. Entry Operator confirms the reading of the notification.
            System.out.println("Please confirm you have read this notification (type 'confirm'):");
            String confirmation = scanner.nextLine();
            
            if ("confirm".equalsIgnoreCase(confirmation)) {
                System.out.println("Notification confirmed by Entry Operator.");
            }
            
            // 6. System recovers the previous state.
            // In this case, we don't need to recover as no change was made.
            System.out.println("System state remains unchanged.");
        }
        
        long endTime = System.currentTimeMillis();
        long verificationTime = endTime - startTime;
        
        // Check quality requirement
        System.out.println("\n=== Performance Check ===");
        System.out.println("Verification completed in " + verificationTime + " ms");
        if (verificationTime <= 2000) {
            System.out.println("Quality requirement MET: Verification within 2 seconds.");
        } else {
            System.out.println("Quality requirement NOT MET: Verification took more than 2 seconds.");
        }
        
        // Exit Conditions
        System.out.println("\n=== Exit Conditions ===");
        System.out.println("1. System returns control to user interaction.");
        System.out.println("2. Connection to server ETOUR is interrupted (simulated).");
        
        scanner.close();
    }
}