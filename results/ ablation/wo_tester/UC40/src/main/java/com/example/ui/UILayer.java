package com.example.ui;

import com.example.controller.DeleteBannerController;
import com.example.entity.Banner;
import com.example.response.DeleteBannerResponse;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI Layer for the Delete Banner use case.
 * Handles user interactions and displays information.
 */
public class UILayer {
    private DeleteBannerController controller;
    private List<Banner> currentBanners;
    private Scanner scanner;

    public UILayer(DeleteBannerController controller) {
        this.controller = controller;
        this.currentBanners = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a list of banners to the user.
     * @param banners the list of banners to display
     */
    public void displayBannerList(List<Banner> banners) {
        this.currentBanners = banners;
        System.out.println("\n=== Available Banners ===");
        if (banners.isEmpty()) {
            System.out.println("No banners found.");
        } else {
            for (int i = 0; i < banners.size(); i++) {
                Banner banner = banners.get(i);
                System.out.printf("%d. %s (ID: %s)%n", i + 1, banner.getName(), banner.getId());
            }
        }
        System.out.println("=========================\n");
    }

    /**
     * Prompts the user to select a banner from the current list.
     * @return the selected banner ID, or null if cancelled
     */
    public String selectBanner(String bannerId) {
        // Simplified: in real UI, would highlight or pre-select based on bannerId
        System.out.print("Enter the number of the banner to delete (or 0 to cancel): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                return null;
            }
            if (choice > 0 && choice <= currentBanners.size()) {
                return currentBanners.get(choice - 1).getId();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return null;
    }

    /**
     * Prompts the user for confirmation before deletion.
     * @return true if user confirms, false otherwise
     */
    public boolean promptForConfirmation() {
        System.out.print("Are you sure you want to delete this banner? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    /**
     * Prompts the user for confirmation of deletion.
     * @return true if user confirms, false otherwise
     */
    public boolean confirmDeletion() {
        System.out.print("Confirm deletion? (confirm/cancel): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("confirm");
    }

    /**
     * Displays a success message to the user.
     * @param message the success message to display
     */
    public void showSuccessMessage(String message) {
        System.out.println("SUCCESS: " + message);
    }

    /**
     * Displays an error message to the user.
     * @param message the error message to display
     */
    public void showErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }
}