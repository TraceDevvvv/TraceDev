package com.etour.controller;

import com.etour.exception.ImageValidationException;
import com.etour.exception.MaxBannersExceededException;
import com.etour.exception.ServerConnectionException;
import com.etour.model.Banner;
import com.etour.model.RefreshmentPoint;
import com.etour.service.BannerService;
import java.util.List;
import java.util.Scanner;

/**
 * Controller handling the flow of banner insertion as per use case.
 * This simulates the UI interactions.
 */
public class BannerInsertionController {
    private final BannerService bannerService;
    private final Scanner scanner;

    public BannerInsertionController() {
        this.bannerService = new BannerService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main flow of the use case.
     *
     * @param refreshmentPoints List of refreshment points from SearchRefreshmentPoint use case.
     */
    public void startBannerInsertion(List<RefreshmentPoint> refreshmentPoints) {
        System.out.println("=== Banner Insertion for Refreshment Point ===");

        // Step 1: Agency Operator receives list (already provided).
        // Step 2: Select one refreshment point.
        RefreshmentPoint selectedPoint = selectRefreshmentPoint(refreshmentPoints);
        if (selectedPoint == null) {
            System.out.println("No refreshment point selected. Operation canceled.");
            return;
        }

        // Step 3-5: Access function, display form, select image.
        String imagePath = selectImage();
        if (imagePath == null) {
            System.out.println("Image selection canceled.");
            return;
        }

        // Step 6: Send request (gather image details).
        long imageSize = getImageSize();
        int width = getImageWidth();
        int height = getImageHeight();
        String format = getImageFormat();

        // Step 7: System checks image characteristics.
        try {
            bannerService.validateImage(imagePath, imageSize, width, height, format);
        } catch (ImageValidationException e) {
            System.out.println("Image validation failed: " + e.getMessage());
            // Enable use case Errored (could be logging, notification, etc.)
            handleError(e);
            return;
        }

        // Step 8: Check banner limit (simulate current count retrieval).
        int currentBannerCount = getCurrentBannerCount(selectedPoint.getId());
        try {
            bannerService.checkBannerLimit(selectedPoint, currentBannerCount);
        } catch (MaxBannersExceededException e) {
            System.out.println("Cannot insert banner: " + e.getMessage());
            return;
        }

        // Step 9: Ask for confirmation.
        if (!confirmInsertion()) {
            System.out.println("Insertion canceled by Agency Operator.");
            return;
        }

        // Step 10: Confirmation already done in previous step.
        // Step 11: Store the banner.
        Banner banner = bannerService.createBanner(selectedPoint.getId(), imagePath,
                imageSize, width, height, format);
        try {
            bannerService.storeBanner(banner);
            System.out.println("System notifies about the insertion of the new banner to the selected refreshment point.");
        } catch (ServerConnectionException e) {
            System.out.println("Error: " + e.getMessage());
            handleError(e);
        }
    }

    /**
     * Simulates selection of a refreshment point from the list.
     */
    private RefreshmentPoint selectRefreshmentPoint(List<RefreshmentPoint> points) {
        System.out.println("Available Refreshment Points:");
        for (int i = 0; i < points.size(); i++) {
            RefreshmentPoint p = points.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (Location: " + p.getLocation() +
                    ", Max Banners: " + p.getMaxBanners() + ")");
        }
        System.out.print("Select a refreshment point by number (or 0 to cancel): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (choice == 0) {
            return null;
        }
        if (choice > 0 && choice <= points.size()) {
            return points.get(choice - 1);
        } else {
            System.out.println("Invalid selection.");
            return null;
        }
    }

    /**
     * Simulates image selection (in reality this would be a file picker).
     */
    private String selectImage() {
        System.out.print("Enter image path (or 'cancel' to abort): ");
        String path = scanner.nextLine();
        if (path.equalsIgnoreCase("cancel")) {
            return null;
        }
        return path;
    }

    private long getImageSize() {
        System.out.print("Enter image size in bytes: ");
        return scanner.nextLong();
    }

    private int getImageWidth() {
        System.out.print("Enter image width in pixels: ");
        return scanner.nextInt();
    }

    private int getImageHeight() {
        System.out.print("Enter image height in pixels: ");
        return scanner.nextInt();
    }

    private String getImageFormat() {
        System.out.print("Enter image format (jpg, png, etc.): ");
        scanner.nextLine(); // consume leftover newline
        return scanner.nextLine();
    }

    /**
     * Simulates retrieving current banner count for a refreshment point.
     * In a real application, this would query a database.
     */
    private int getCurrentBannerCount(int pointId) {
        // Simulate a count retrieval (e.g., from database).
        // For demo, return a random number between 0 and 5.
        return (int) (Math.random() * 6);
    }

    private boolean confirmInsertion() {
        System.out.print("Confirm insertion of banner? (yes/no): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    /**
     * Placeholder for error handling (could log, notify, etc.).
     */
    private void handleError(Exception e) {
        System.err.println("Error handled: " + e.getMessage());
        // Additional error handling logic can be added here.
    }
}