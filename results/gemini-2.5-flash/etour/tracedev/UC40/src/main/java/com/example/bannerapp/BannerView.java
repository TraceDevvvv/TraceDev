package com.example.bannerapp;

import java.util.List;
import java.util.Scanner;

/**
 * Handles the display of information and user input for banner management.
 * This class simulates a UI component.
 */
public class BannerView {
    private Scanner scanner;

    public BannerView(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Displays a list of banners to the user.
     * @param banners The list of banners to display.
     */
    public void displayBannerList(List<Banner> banners) {
        System.out.println("\n--- Available Banners ---");
        if (banners == null || banners.isEmpty()) {
            System.out.println("No banners available.");
        } else {
            for (Banner banner : banners) {
                System.out.println("  ID: " + banner.getId() + ", Name: " + banner.getName());
            }
        }
        System.out.println("-------------------------");
    }

    /**
     * Displays a confirmation prompt for deleting a specific banner.
     * @param banner The banner proposed for deletion.
     */
    public void displayConfirmationPrompt(Banner banner) {
        if (banner == null) {
            System.out.println("Error: Banner details for confirmation are missing.");
            return;
        }
        System.out.println("\n--- Confirm Deletion ---");
        System.out.println("Are you sure you want to delete banner:");
        System.out.println("  ID: " + banner.getId());
        System.out.println("  Name: " + banner.getName());
        System.out.print("Type 'yes' to confirm, or 'no' to cancel: ");
    }

    /**
     * Displays a success message after a banner has been deleted.
     */
    public void displayDeletionSuccess() {
        System.out.println("\nBanner successfully deleted.");
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void displayError(String message) {
        System.err.println("\nError: " + message);
    }

    /**
     * Prompts the user to select a banner by its ID.
     * @return The ID of the selected banner.
     */
    public String promptSelectBanner() {
        System.out.print("Please enter the ID of the banner you want to delete: ");
        return scanner.nextLine().trim();
    }

    /**
     * Prompts the user to confirm the deletion action.
     * @return true if the user confirms, false otherwise.
     */
    public boolean promptConfirmDeletion() {
        String input = scanner.nextLine().trim().toLowerCase();
        return "yes".equals(input);
    }
}