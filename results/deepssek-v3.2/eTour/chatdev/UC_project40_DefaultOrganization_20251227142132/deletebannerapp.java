'''
Main application class for deleting a banner ad associated with a point of rest.
Implements the complete flow as per the DeleteBanner use case.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class DeleteBannerApp {
    private static List<Banner> banners = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            // Simulate authentication - assumed successful per entry conditions
            System.out.println("Authentication successful. Welcome Point Of Restaurant Operator.");
            // Initialize with some sample banners for demonstration
            initializeSampleBanners();
            // Step 1: Select the feature for removal of the banner
            System.out.println("\n=== Banner Removal Feature ===");
            // Main program loop
            boolean continueRunning = true;
            while (continueRunning) {
                // Step 2: View the list of banners associated with the point of rest
                displayBanners();
                // Step 3: Select a banner from the list and enter the function of elimination
                System.out.print("\nEnter the name of the banner to delete (or 'exit' to quit, 'cancel' to cancel operation): ");
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting application.");
                    continueRunning = false;
                    continue;
                }
                if (input.equalsIgnoreCase("cancel")) {
                    System.out.println("Operation cancelled by operator.");
                    continue;
                }
                Banner selectedBanner = findBanner(input);
                if (selectedBanner == null) {
                    System.out.println("Error: Banner '" + input + "' not found.");
                    continue;
                }
                // Step 4: Display a message confirming the deletion
                System.out.println("\n=== Delete Confirmation ===");
                System.out.println("You have selected to delete: " + selectedBanner);
                System.out.println("Name: " + selectedBanner.getName());
                System.out.println("Image URL: " + selectedBanner.getImageUrl());
                System.out.println("Status: " + selectedBanner.getStatus());
                // Step 5: Confirm the operation
                System.out.print("\nAre you sure you want to delete this banner? (yes/no): ");
                String confirmation = scanner.nextLine().trim().toLowerCase();
                if (confirmation.equals("yes")) {
                    // Step 6: Remove the banner
                    boolean success = deleteBanner(selectedBanner);
                    if (success) {
                        // Exit condition: Success notification
                        System.out.println("\nSUCCESS: Banner '" + selectedBanner.getName() + "' has been successfully deleted.");
                    } else {
                        System.out.println("Error: Failed to delete the banner.");
                    }
                } else {
                    System.out.println("Operation cancelled. Banner was not deleted.");
                }
                // Ask if user wants to continue
                System.out.print("\nDo you want to delete another banner? (yes/no): ");
                String continueChoice = scanner.nextLine().trim().toLowerCase();
                if (!continueChoice.equals("yes")) {
                    System.out.println("Thank you for using the Banner Removal system.");
                    continueRunning = false;
                }
            }
        } catch (Exception e) {
            // Handle server interruption or other exceptions
            System.err.println("ERROR: Connection to server interrupted or unexpected error: " + e.getMessage());
            System.out.println("Please try again later or contact support.");
        } finally {
            scanner.close();
        }
    }
    /**
     * Initialize the system with sample banners for demonstration.
     */
    private static void initializeSampleBanners() {
        banners.add(new Banner("Summer Special", "https://example.com/summer.jpg", "Active"));
        banners.add(new Banner("Winter Promotion", "https://example.com/winter.jpg", "Active"));
        banners.add(new Banner("Grand Opening", "https://example.com/opening.jpg", "Inactive"));
        banners.add(new Banner("Happy Hour", "https://example.com/happyhour.jpg", "Active"));
    }
    /**
     * Display all banners associated with the point of rest.
     */
    private static void displayBanners() {
        System.out.println("\n=== Available Banners ===");
        if (banners.isEmpty()) {
            System.out.println("No banners available for this point of rest.");
        } else {
            System.out.println("Total banners: " + banners.size());
            for (int i = 0; i < banners.size(); i++) {
                System.out.println((i + 1) + ". " + banners.get(i));
            }
        }
    }
    /**
     * Find a banner by name (case-insensitive).
     * @param name The name of the banner to find
     * @return The Banner object if found, null otherwise
     */
    private static Banner findBanner(String name) {
        for (Banner banner : banners) {
            if (banner.getName().equalsIgnoreCase(name)) {
                return banner;
            }
        }
        return null;
    }
    /**
     * Delete a banner from the system.
     * @param banner The Banner object to delete
     * @return true if deletion was successful, false otherwise
     */
    private static boolean deleteBanner(Banner banner) {
        try {
            // Simulate server operation with potential interruption
            System.out.println("Connecting to server ETOUR to delete banner...");
            // Simulate potential server interruption (10% chance for demonstration)
            if (Math.random() < 0.1) {
                throw new RuntimeException("Server ETOUR connection interrupted");
            }
            // Perform the actual deletion
            boolean removed = banners.remove(banner);
            // Simulate brief delay for server operation
            Thread.sleep(500);
            return removed;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("ERROR: Operation interrupted: " + e.getMessage());
            return false;
        } catch (RuntimeException e) {
            // Handle server interruption specifically
            System.err.println("ERROR: " + e.getMessage());
            System.out.println("Please check your connection and try again.");
            return false;
        } catch (Exception e) {
            System.err.println("ERROR: Failed to delete banner: " + e.getMessage());
            return false;
        }
    }
    /**
     * Method to add a banner to the list.
     * @param banner The Banner to add
     */
    public static void addBanner(Banner banner) {
        banners.add(banner);
    }
}