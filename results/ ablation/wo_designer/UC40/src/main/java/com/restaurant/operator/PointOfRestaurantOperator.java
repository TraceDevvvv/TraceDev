package com.restaurant.operator;

import com.restaurant.banner.Banner;
import com.restaurant.banner.BannerService;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the Point Of Restaurant Operator.
 */
public class PointOfRestaurantOperator {
    private int operatorId;
    private int restaurantId;
    private BannerService bannerService;
    private Scanner scanner;

    public PointOfRestaurantOperator(int operatorId, int restaurantId) {
        this.operatorId = operatorId;
        this.restaurantId = restaurantId;
        this.bannerService = new BannerService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates operator authentication (always successful for this demo).
     */
    public boolean authenticate() {
        System.out.println("Operator " + operatorId + " authenticated successfully.");
        return true;
    }

    /**
     * Displays the list of banners for the restaurant.
     */
    private void displayBanners() {
        List<Banner> banners = bannerService.getBannersByRestaurantId(restaurantId);
        if (banners.isEmpty()) {
            System.out.println("No banners available for your restaurant.");
        } else {
            System.out.println("=== Banners for Restaurant ID " + restaurantId + " ===");
            for (Banner banner : banners) {
                System.out.println("ID: " + banner.getId() + " - Title: " + banner.getTitle());
            }
        }
    }

    /**
     * Main flow to delete a banner.
     */
    public void deleteBannerFlow() {
        // Step 1: Operator selects the feature for removal
        System.out.println("\n--- Banner Deletion Feature ---");
        System.out.println("Operator " + operatorId + " from Restaurant " + restaurantId);

        // Step 2: System displays the list of banners
        displayBanners();

        // Step 3: Operator selects a banner from the list
        System.out.print("Enter the ID of the banner to delete: ");
        int bannerId;
        try {
            bannerId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Operation cancelled.");
            return;
        }

        // Step 4: Operator enters the function of elimination
        System.out.println("You have selected banner ID " + bannerId + " for deletion.");

        // Step 5: System displays a message confirming the deletion
        System.out.println("Are you sure you want to delete this banner? (yes/no)");

        // Step 6: Operator confirms the operation
        String confirmation = scanner.nextLine();
        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Operation cancelled by operator.");
            return;
        }

        // Step 7: System removes the banner
        boolean success = bannerService.deleteBanner(bannerId, restaurantId);
        if (success) {
            System.out.println("Success: Banner ID " + bannerId + " has been deleted.");
        } else {
            System.out.println("Error: Banner not found or you do not have permission.");
        }
    }

    /**
     * Closes resources.
     */
    public void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        // Simulating a restaurant operator with ID 500 and restaurant ID 101
        PointOfRestaurantOperator operator = new PointOfRestaurantOperator(500, 101);

        // Entry condition: Operator has successfully authenticated
        if (operator.authenticate()) {
            operator.deleteBannerFlow();
        } else {
            System.out.println("Authentication failed. Exiting.");
        }

        operator.close();
    }
}