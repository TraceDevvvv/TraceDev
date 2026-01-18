package presentation;

import application.BannerCreationRequestDTO;
import application.BannerInsertionResponseDTO;

import java.util.Scanner;

/**
 * Simulates the User Interface (UI) for banner insertion.
 * It interacts with the operator, collects input, displays information,
 * and calls the BannerController.
 */
public class BannerInsertionUI {
    private final BannerController bannerController;
    private final Scanner scanner;

    /**
     * Constructs a BannerInsertionUI with a dependency on BannerController.
     *
     * @param bannerController The controller for banner operations.
     */
    public BannerInsertionUI(BannerController bannerController) {
        this.bannerController = bannerController;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the operator selecting the "Insert Banner" feature.
     * This method orchestrates the entire UI flow for banner insertion.
     */
    public void selectsInsertBannerFeature() {
        System.out.println("\n--- Operator selects 'Insert New Banner' feature ---");
        displayBannerForm();

        // Simulate operator input
        String imageUrl = getImageSelection();
        System.out.print("Enter Point Of Restaurant ID (e.g., por123, por456): ");
        String pointOfRestaurantId = scanner.nextLine();
        String operatorId = "op123"; // Fixed operator ID for demonstration

        sendInsertionRequest(imageUrl, pointOfRestaurantId, operatorId);
    }

    /**
     * Displays a form for banner details.
     */
    public void displayBannerForm() {
        System.out.println("Displaying banner insertion form:");
        System.out.println(" - Image URL input field");
        System.out.println(" - Point Of Restaurant ID input field");
    }

    /**
     * Simulates the operator selecting an image and returning its URL.
     *
     * @return The selected image URL.
     */
    public String getImageSelection() {
        System.out.print("Enter Image URL (e.g., http://example.com/banner.png): ");
        return scanner.nextLine();
    }

    /**
     * Sends the banner insertion request to the controller.
     *
     * @param imageUrl The URL of the banner image.
     * @param pointOfRestaurantId The ID of the PointOfRestaurant.
     * @param operatorId The ID of the authenticated operator.
     */
    public void sendInsertionRequest(String imageUrl, String pointOfRestaurantId, String operatorId) {
        System.out.println("[UI] Sending insertion request to Controller...");
        BannerCreationRequestDTO request = new BannerCreationRequestDTO(imageUrl, pointOfRestaurantId, operatorId);
        BannerInsertionResponseDTO response = bannerController.requestBannerInsertion(request);

        if (response.isConfirmationRequired()) {
            boolean confirmed = displayConfirmationPrompt(response.getMessage());
            if (confirmed) {
                System.out.println("[UI] Operator confirms insertion.");
                BannerInsertionResponseDTO finalResponse = bannerController.confirmInsertion(response.getBannerId(), pointOfRestaurantId);
                if (finalResponse.isSuccess()) {
                    displayNotification(finalResponse.getMessage());
                } else {
                    displayError(finalResponse.getMessage());
                }
            } else {
                System.out.println("[UI] Operator cancels insertion.");
                bannerController.cancelInsertion(response.getBannerId());
                displayNotification("Banner insertion cancelled.");
            }
        } else if (!response.isSuccess()) { // It's an error and no confirmation required
            displayError(response.getMessage());
        } else {
            // Should not happen for initial request, as it always requires confirmation or fails immediately
            displayNotification(response.getMessage());
        }
    }

    /**
     * Displays a confirmation prompt to the operator and gets their decision.
     *
     * @param message The message to display in the prompt.
     * @return true if the operator confirms, false otherwise.
     */
    public boolean displayConfirmationPrompt(String message) {
        System.out.println("\n--- Confirmation Required ---");
        System.out.println(message);
        System.out.print("Do you want to confirm? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes");
    }

    /**
     * Displays a notification message to the operator.
     *
     * @param message The notification message.
     */
    public void displayNotification(String message) {
        System.out.println("\n--- Notification ---");
        System.out.println(message);
        System.out.println("--------------------");
    }

    /**
     * Displays an error message to the operator.
     *
     * @param errorMessage The error message.
     */
    public void displayError(String errorMessage) {
        System.err.println("\n--- ERROR ---");
        System.err.println(errorMessage);
        System.err.println("-------------");
    }
}