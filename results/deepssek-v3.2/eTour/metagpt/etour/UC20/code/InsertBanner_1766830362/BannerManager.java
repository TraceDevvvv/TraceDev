I'll create the BannerManager.java class that handles the business logic for inserting banners. This class will coordinate between agency operators, refreshment points, and banner validation, implementing the main insertion flow with error handling and confirmation logic.

```
package com.etur.insertbanner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Scanner;

/**
 * BannerManager class handles the core business logic for inserting banners.
 * Coordinates between agency operators, refreshment points, and banner validation.
 * Implements the main insertion flow with error handling and confirmation logic.
 */
public class BannerManager {
    private List<RefreshmentPoint> refreshmentPoints;
    private AgencyOperator currentOperator;
    private Scanner scanner;
    
    /**
     * Constructor for BannerManager.
     * Initializes with empty refreshment points list and no current operator.
     */
    public BannerManager() {
        this.refreshmentPoints = new ArrayList<>();
        this.currentOperator = null;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Sets the current agency operator for banner operations.
     * 
     * @param operator The logged-in agency operator
     * @throws IllegalArgumentException if operator is null or not logged in
     */
    public void setCurrentOperator(AgencyOperator operator) {
        if (operator == null) {
            throw new IllegalArgumentException("Operator cannot be null");
        }
        
        if (!operator.isLoggedIn()) {
            throw new IllegalStateException("Operator must be logged in");
        }
        
        this.currentOperator = operator;
        System.out.println("Current operator set to: " + operator.getUsername());
    }
    
    /**
     * Adds a refreshment point to the manager's list.
     * 
     * @param point The refreshment point to add
     */
    public void addRefreshmentPoint(RefreshmentPoint point) {
        if (point != null && !refreshmentPoints.contains(point)) {
            refreshmentPoints.add(point);
            
            // Also add to operator's managed points if operator is set
            if (currentOperator != null) {
                currentOperator.addManagedRefreshmentPoint(point.getId());
            }
            
            System.out.println("Refreshment point added: " + point.getName());
        }
    }
    
    /**
     * Gets a list of all refreshment points available for banner insertion.
     * 
     * @return List of refreshment points
     */
    public List<RefreshmentPoint> getRefreshmentPoints() {
        return new ArrayList<>(refreshmentPoints);
    }
    
    /**
     * Executes the main banner insertion flow according to the use case.
     * This is the core method that implements the use case steps.
     * 
     * @return true if banner was successfully inserted, false otherwise
     */
    public boolean executeInsertBannerFlow() {
        System.out.println("\n=== Starting Insert Banner Use Case ===\n");
        
        // Step 1: Check entry conditions
        if (!checkEntryConditions()) {
            System.out.println("Entry conditions not met. Cannot proceed with banner insertion.");
            return false;
        }
        
        // Step 2: Display list of refreshment points and select one
        RefreshmentPoint selectedPoint = selectRefreshmentPoint();
        if (selectedPoint == null) {
            System.out.println("No refreshment point selected. Operation cancelled.");
            return false;
        }
        
        // Step 3: Display form for image selection and get banner data
        Banner newBanner = getBannerDataFromUser(selectedPoint.getId());
        if (newBanner == null) {
            System.out.println("Banner data collection cancelled.");
            return false;
        }
        
        // Step 4: Validate banner and check refreshment point capacity
        if (!validateBannerAndCapacity(newBanner, selectedPoint)) {
            System.out.println("Banner validation failed. Cannot proceed.");
            return false;
        }
        
        // Step 5: Ask for confirmation
        if (!askForConfirmation(newBanner, selectedPoint)) {
            System.out.println("Insertion cancelled by operator.");
            return false;
        }
        
        // Step 6: Insert the banner
        boolean success = insertBanner(newBanner, selectedPoint);
        
        if (success) {
            System.out.println("\n=== Banner Insertion Completed Successfully ===\n");
            sendNotification(selectedPoint, newBanner);
        } else {
            System.out.println("\n=== Banner Insertion Failed ===\n");
        }
        
        return success;
    }
    
    /**
     * Checks the entry conditions for the use case.
     * According to use case: "Entry Operator conditions: The agency has logged."
     * 
     * @return true if all entry conditions are met, false otherwise
     */
    private boolean checkEntryConditions() {
        System.out.println("Checking entry conditions...");
        
        // Check 1: Operator must be set and logged in
        if (currentOperator == null) {
            System.out.println("ERROR: No agency operator is set.");
            return false;
        }
        
        if (!currentOperator.isLoggedIn()) {
            System.out.println("ERROR: Agency operator is not logged in.");
            return false;
        }
        
        // Check 2: Operator session must be valid
        if (!currentOperator.isSessionValid()) {
            System.out.println("ERROR: Operator session is not valid or has expired.");
            return false;
        }
        
        // Check 3: Operator must have INSERT_BANNER permission
        if (!currentOperator.hasPermission("INSERT_BANNER")) {
            System.out.println("ERROR: Operator does not have INSERT_BANNER permission.");
            return false;
        }
        
        // Check 4: Check server connection (ETOUR server)
        if (!currentOperator.checkServerConnection()) {
            System.out.println("ERROR: Connection to ETOUR server interrupted.");
            return false;
        }
        
        // Check 5: Must have at least one refreshment point
        if (refreshmentPoints.isEmpty()) {
            System.out.println("ERROR: No refreshment points available.");
            return false;
        }
        
        System.out.println("All entry conditions are satisfied.");
        return true;
    }
    
    /**
     * Displays a list of refreshment points and allows the operator to select one.
     * Implements step 1 of the use case flow.
     * 
     * @return The selected refreshment point, or null if cancelled
     */
    private RefreshmentPoint selectRefreshmentPoint() {
        System.out.println("\n--- Step 1: Select Refreshment Point ---");
        System.out.println("Available refreshment points:");
        
        for (int i = 0; i < refreshmentPoints.size(); i++) {
            RefreshmentPoint point = refreshmentPoints.get(i);
            System.out.printf("%d. %s - %s, %s (Banners: %d/%d)%n",
                    i + 1,
                    point.getName(),
                    point.getAddress(),
                    point.getCity(),
                    point.getBannerCount(),
                    point.getMaximumBanners());
        }
        
        System.out.print("\nEnter the number of the refreshment point (or 0 to cancel): ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            
            if (choice == 0) {
                System.out.println("Selection cancelled by operator.");
                return null;
            }
            
            if (choice < 1 || choice > refreshmentPoints.size()) {
                System.out.println("Invalid selection. Please try again.");
                return selectRefreshmentPoint(); // Recursive call for retry
            }
            
            RefreshmentPoint selectedPoint = refreshmentPoints.get(choice - 1);
            
            // Check if operator can manage this refreshment point
            if (!currentOperator.canManageRefreshmentPoint(selectedPoint.getId())) {
                System.out.println("ERROR: You are not authorized to manage this refreshment point.");
                return null;
            }
            
            System.out.println("Selected: " + selectedPoint.getName());
            return selectedPoint;
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return selectRefreshmentPoint(); // Recursive call for retry
        }
    }
    
    /**
     * Displays a form for image selection and collects banner data from the user.
     * Implements steps 2-3 of the use case flow.
     * 
     * @param refreshmentPointId The ID of the selected refreshment point
     * @return A Banner object with the collected data, or null if cancelled
     */
    private Banner getBannerDataFromUser(String refreshmentPointId) {
        System.out.println("\n--- Step 2-3: Banner Image Selection ---");
        
        System.out.println("Please enter banner details:");
        
        // Get image name
        System.out.print("Image file name (e.g., banner.jpg): ");
        String imageName = scanner.nextLine().trim();
        
        if (imageName.isEmpty()) {
            System.out.println("Image name cannot be empty.");
            return null;
        }
        
        // Get image path
        System.out.print("Image file path (e.g., /images/banners/): ");
        String imagePath = scanner.nextLine().trim();
        
        if (imagePath.isEmpty()) {
            System.out.println("Image path cannot be empty.");
            return null;
        }
        
        // Get image size
        long imageSize = 0;
        while (true) {
            System.out.print("Image size in bytes (e.g., 204800 for 200KB): ");
            String sizeInput = scanner.nextLine().trim();
            
            try {
                imageSize = Long.parseLong(sizeInput);
                if (imageSize <= 0) {
                    System.out.println("Image size must be positive.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        
        // Get image dimensions
        int width = 0;
        int height = 0;
        
        while (true) {
            System.out.print("Image width in pixels (e.g., 800): ");
            String widthInput = scanner.nextLine().trim();
            
            try {
                width = Integer.parseInt(widthInput);
                if (width <= 0) {
                    System.out.println("Width must be positive.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        
        while (true) {
            System.out.print("Image height in pixels (e.g., 600): ");
            String heightInput = scanner.nextLine().trim();
            
            try {
                height = Integer.parseInt(heightInput);
                if (height <= 0) {
                    System.out.println("Height must be positive.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        
        // Create banner object
        String bannerId = UUID.randomUUID().toString();
        Banner banner = new Banner(bannerId, imagePath, imageName, imageSize, refreshmentPointId);
        banner.setImageDimensions(width, height);
        
        System.out.println("Banner data collected successfully.");
        return banner;
    }
    
    /**
     * Validates the banner and checks if the refreshment point has capacity.
     * Implements step 4 of the use case flow.
     * 
     * @param banner The banner to validate
     * @param point The refreshment point to check capacity for
     * @return true if validation passes, false otherwise
     */
    private boolean validateBannerAndCapacity(Banner banner, RefreshmentPoint point) {
        System.out.println("\n--- Step 4: Banner Validation and Capacity Check ---");
        
        // Check 1: Validate banner image
        System.out.println("Validating banner image...");
        if (!banner.validateImage()) {
            System.out.println("ERROR: Banner image validation failed.");
            System.out.println(banner.getValidationDetails());
            triggerErroredUseCase(banner);
            return false;
        }
        
        System.out.println("Banner image validation passed.");
        
        // Check 2: Check refreshment point capacity
        System.out.println("Checking refreshment point capacity...");
        if (point.hasReachedMaxBanners()) {
            System.out.println("ERROR: Refreshment point has reached maximum banner capacity.");
            System.out.println(point.getBannerStatusSummary());
            return false;
        }
        
        System.out.println("Capacity check passed. " + point.getRemainingBannerSlots() + " slots available.");
        
        // Check 3: Verify server connection again (ETOUR server)
        if (!currentOperator.checkServerConnection()) {
            System.out.println("ERROR: Connection to ETOUR server interrupted during validation.");
            return false;
        }
        
        return true;
    }
    
    /**
     * Asks the operator for confirmation before inserting the banner.
     * Implements part of step 4 and step 5 of the use case flow.
     * 
     * @param banner The banner to insert
     * @param point The refreshment point where banner will be inserted
     * @return true if operator confirms, false if cancels
     */
    private boolean askForConfirmation(Banner banner, RefreshmentPoint point) {
        System.out.println("\n--- Step 5: Insertion Confirmation ---");
        
        System.out.println("\nPlease review the banner details:");
        System.out.println("Refreshment Point: " + point.getName());
        System.out.println("Image Name: " + banner.getImageName());
        System.out.println("Image Size: " + banner.getImageSize() + " bytes");
        System.out.println("Image Format: " + banner.getImageFormat());
        System.out.println("Dimensions: " + banner.getWidth() + "x" + banner.getHeight());
        System.out.println("Current banners at point: " + point.getBannerCount() + "/" + point.getMaximumBanners());
        
        System.out.print("\nDo you want to insert this banner? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        
        return response.equals("yes") || response.equals("y");
    }
    
    /**
     * Inserts the banner into the refreshment point.
     * Implements step 6 of the use case flow.
     * 
     * @param banner The banner to insert
     * @param point The refreshment point where banner will be inserted
     * @return true if insertion successful, false otherwise
     */
    private boolean insertBanner(Banner banner, RefreshmentPoint point) {
        System.out.println("\n--- Step 6: Banner Insertion ---");
        
        try {
            // Final server connection check
            if (!currentOperator.checkServerConnection()) {
                System.out.println("ERROR: Connection to ETOUR server interrupted during insertion.");
                return false;
            }
            
            // Insert the banner
            boolean success = point.addBanner(banner);
            
            if (success) {
                System.out.println("Banner inserted successfully!");
                System.out.println("Banner ID: " + banner.getId());
                System.out.println("Total banners at " + point.getName() + ": " + point.getBannerCount());
                
                // Remember the banner association (implicitly done by addBanner)
                System.out.println("Banner association with refreshment point recorded.");
                return true;
            } else {
                System.out.println("ERROR: Failed to insert banner. Maximum capacity may have been reached.");
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: Unexpected error during banner insertion: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Triggers the Errored use case when banner validation fails.
     * According to use case: "In the event that the inserted image is not valid, enable the use case Errored."
     * 
     * @param banner The banner that failed validation
     */
    private void triggerErroredUseCase(Banner banner) {
        System.out.println("\n=== Triggering Errored Use Case ===");
        System.out.println("Banner validation failed for: " + banner.getImageName());
        System.out.println("Please check the image characteristics and try again.");
        System.out.println("Error details have been logged for administrator review.");
        
        // In a real application, this would trigger a separate error handling workflow
        // For this simulation, we just log the error
        System.out.println("ERROR LOG: Invalid banner attempted - " + banner.toString());
    }
    
    /**
     * Sends notification about the banner insertion.
     * According to use case: "The notification about the insertion of new banner to the Refreshments selected."
     * 
     * @param point The refreshment point where banner was inserted
     * @param banner The banner that was inserted
     */
    private void sendNotification(RefreshmentPoint point, Banner banner) {
        System.out.println("\n=== Sending Notification ===");
        System.out.println("Notification sent: New banner has been inserted at " + point.getName());
        System.out.println("Banner details: " + banner.getImageName() + " (" + banner.getImageFormat() + ")");
        System.out.println("Refreshment point: " + point.getName() + ", " + point.getAddress());
        System.out.println("Total banners now: " + point.getBannerCount() + "/" + point.getMaximumBanners());
        
        // In a real application, this would send an actual notification (email, message, etc.)
        System.out.println("Notification has been recorded in the system.");
    }
    
    /**
     * Gets a refreshment point by its ID.
     * 
     * @param pointId The ID of the refreshment point
     * @return The refreshment point if found, null otherwise
     */
    public RefreshmentPoint getRefreshmentPointById(String pointId) {
        for (RefreshmentPoint point : refreshmentPoints) {
            if (point.getId().equals(pointId)) {
                return point;
            }
        }
        return null;
    }
    
    /**
     * Gets a summary of all refreshment points and their banner status.
     * 
     * @return String containing summary information
     */
    public String getRefreshmentPointsSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Refreshment Points Summary:\n");
        summary.append("===========================\n");
        
        if (refreshmentPoints.isEmpty()) {
            summary.append("No refreshment points available.\n");
        } else {
            for (int i = 0; i < refreshmentPoints.size(); i++) {
                RefreshmentPoint point = refreshmentPoints.get(i);
                summary.append(i + 1).append(". ").append(point.getBannerStatusSummary()).append("\n");
            }
        }
        
        return summary.toString();
    }
    
    /**
     * Closes the scanner resource.
     * Should be called when the BannerManager is no longer needed.
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    /**
     * Simulates the complete banner insertion process for testing.
     * This method provides a test flow without user interaction.
     * 
     * @param pointId ID of the refreshment point
     * @param imageName Name of the image
