package com.example.banner;

import com.example.banner.model.Banner;
import com.example.banner.service.BannerService;
import com.example.banner.exception.BannerException;
import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Main class simulating the Point Of Restaurant Operator's interaction.
 * This is a console-based simulation for demonstration.
 */
public class MainApplication {

    private static BannerService bannerService = new BannerService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Restaurant Banner Management System ===");

        // Simulate authentication (already authenticated per entry condition)
        Long restaurantId = 1L; // Example restaurant ID

        boolean running = true;
        while (running) {
            System.out.println("\nOptions:");
            System.out.println("1. Insert new banner");
            System.out.println("2. View existing banners");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    insertBanner(restaurantId);
                    break;
                case 2:
                    viewBanners(restaurantId);
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void insertBanner(Long restaurantId) {
        System.out.println("\n--- Insert New Banner ---");

        // Step 1: Operator selects the feature (already done via menu)
        // Step 2 & 3: System displays form for image selection.
        // In console, we ask for the image file path.
        System.out.print("Enter the full path to the image file: ");
        String imagePath = scanner.nextLine().trim();
        File imageFile = new File(imagePath);

        // Step 4: Operator sends request (we proceed with validation)
        try {
            // Steps 5-9 are performed inside the service.
            Banner banner = bannerService.insertBanner(restaurantId, imageFile);
            // Exit condition: Success notification
            System.out.println("SUCCESS: Banner inserted with ID: " + banner.getId());
        } catch (BannerException e) {
            // Handle various exit conditions (failure cases)
            System.out.println("ERROR: " + e.getMessage());
            // Additional info in some cases
            if (e.getMessage().contains("Maximum number of banners")) {
                System.out.println("Please remove an existing banner before adding a new one.");
            }
        } catch (Exception e) {
            // Simulate connection interruption or other unexpected errors
            System.out.println("ERROR: Operation failed due to an unexpected problem: " + e.getMessage());
        }
    }

    private static void viewBanners(Long restaurantId) {
        List<Banner> banners = bannerService.getBannersByRestaurant(restaurantId);
        System.out.println("\n--- Banners for Restaurant ID " + restaurantId + " ---");
        if (banners.isEmpty()) {
            System.out.println("No banners found.");
        } else {
            for (Banner b : banners) {
                System.out.println("Banner ID: " + b.getId() + ", Image: " + b.getImagePath() + ", Created: " + b.getCreatedAt());
            }
        }
        System.out.println("Total: " + banners.size() + " banners.");
    }
}