import java.util.*;
import java.io.*;

/**
 * Represents an Agency Operator who can modify banner images.
 * This class handles the complete flow of banner modification as per the use case.
 */
public class AgencyOperator {
    private String operatorId;
    private String operatorName;
    private boolean isLoggedIn;
    private List<RestPoint> availableRestPoints;
    private Scanner scanner;
    
    /**
     * Constructor for creating an Agency Operator.
     * @param operatorId Unique identifier for the operator
     * @param operatorName Name of the operator
     */
    public AgencyOperator(String operatorId, String operatorName) {
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.isLoggedIn = false;
        this.availableRestPoints = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Simulates login process for the agency operator.
     * @param username Username for login
     * @param password Password for login
     * @return true if login successful, false otherwise
     */
    public boolean login(String username, String password) {
        // Simple login simulation - in real application, this would connect to authentication service
        if (username != null && password != null && 
            username.equals("agency") && password.equals("password123")) {
            this.isLoggedIn = true;
            System.out.println("Login successful. Welcome, " + operatorName + "!");
            return true;
        }
        this.isLoggedIn = false;
        System.out.println("Login failed. Invalid credentials.");
        return false;
    }
    
    /**
     * Logs out the agency operator.
     */
    public void logout() {
        this.isLoggedIn = false;
        System.out.println("Logged out successfully.");
    }
    
    /**
     * Checks if operator is logged in.
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     * Sets the list of available rest points (from SearchRefreshmentPoint use case).
     * @param restPoints List of rest points
     */
    public void setAvailableRestPoints(List<RestPoint> restPoints) {
        if (restPoints != null) {
            this.availableRestPoints = new ArrayList<>(restPoints);
        }
    }
    
    /**
     * Displays the list of available rest points.
     */
    public void displayRestPoints() {
        if (availableRestPoints.isEmpty()) {
            System.out.println("No rest points available.");
            return;
        }
        
        System.out.println("\n=== Available Rest Points ===");
        for (int i = 0; i < availableRestPoints.size(); i++) {
            RestPoint point = availableRestPoints.get(i);
            System.out.println((i + 1) + ". " + point.getPointName() + 
                             " (ID: " + point.getPointId() + 
                             ", Location: " + point.getLocation() + 
                             ", Banners: " + point.getBannerCount() + ")");
        }
        System.out.println("=============================\n");
    }
    
    /**
     * Main method to execute the banner modification flow.
     * This implements the complete use case flow.
     */
    public void executeModifyBannerFlow() {
        if (!isLoggedIn) {
            System.out.println("Error: Operator must be logged in to modify banners.");
            return;
        }
        
        try {
            // Step 1: Receive list of rest points and select one
            System.out.println("\n=== Step 1: Select a Rest Point ===");
            displayRestPoints();
            
            if (availableRestPoints.isEmpty()) {
                System.out.println("No rest points available. Exiting flow.");
                return;
            }
            
            System.out.print("Enter the number of the rest point to select: ");
            int pointChoice = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (pointChoice < 0 || pointChoice >= availableRestPoints.size()) {
                System.out.println("Invalid selection. Exiting flow.");
                return;
            }
            
            RestPoint selectedPoint = availableRestPoints.get(pointChoice);
            System.out.println("Selected: " + selectedPoint.getPointName());
            
            // Step 2: View list of banners associated with the selected rest point
            System.out.println("\n=== Step 2: Banners at Selected Rest Point ===");
            List<Banner> banners = selectedPoint.getBanners();
            
            if (banners.isEmpty()) {
                System.out.println("No banners available at this rest point. Exiting flow.");
                return;
            }
            
            for (int i = 0; i < banners.size(); i++) {
                Banner banner = banners.get(i);
                System.out.println((i + 1) + ". Banner ID: " + banner.getBannerId() + 
                                 ", Image: " + banner.getImagePath() + 
                                 ", Status: " + (banner.validateImage() ? "Valid" : "Invalid"));
            }
            
            // Step 3: Select a banner from the list
            System.out.println("\n=== Step 3: Select a Banner to Edit ===");
            System.out.print("Enter the number of the banner to edit: ");
            int bannerChoice = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (bannerChoice < 0 || bannerChoice >= banners.size()) {
                System.out.println("Invalid banner selection. Exiting flow.");
                return;
            }
            
            Banner selectedBanner = banners.get(bannerChoice);
            System.out.println("Selected Banner: " + selectedBanner.getBannerId());
            
            // Step 4: Display form for image selection
            System.out.println("\n=== Step 4: Image Selection Form ===");
            System.out.println("Current image: " + selectedBanner.getImagePath());
            System.out.print("Enter the path to the new image file: ");
            String newImagePath = scanner.nextLine().trim();
            
            // Step 5: Send request to change the system
            System.out.println("\n=== Step 5: Processing Image Change Request ===");
            
            // Step 6: Check characteristics of the inserted image
            System.out.println("\n=== Step 6: Image Validation ===");
            boolean isValid = validateImageForBanner(newImagePath);
            
            if (!isValid) {
                System.out.println("ERROR: The inserted image is not valid.");
                System.out.println("Enabling use case Errored...");
                handleErrorCase(selectedBanner, newImagePath);
                return;
            }
            
            // Ask for confirmation
            System.out.println("\nImage validation successful!");
            System.out.println("New image path: " + newImagePath);
            System.out.print("Confirm banner change? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            // Step 7: Confirmation of transaction change
            if (confirmation.equals("yes") || confirmation.equals("y")) {
                System.out.println("\n=== Step 7: Confirming Transaction ===");
                
                // Step 8: Bookmark the new image for the selected banner
                System.out.println("\n=== Step 8: Updating Banner Image ===");
                boolean updateSuccess = selectedBanner.updateImage(newImagePath);
                
                if (updateSuccess) {
                    System.out.println("SUCCESS: Banner image has been updated successfully!");
                    System.out.println("Banner ID: " + selectedBanner.getBannerId());
                    System.out.println("New Image: " + selectedBanner.getImagePath());
                    System.out.println("Updated at: " + selectedBanner.getUpdatedAt());
                    
                    // Exit condition: Notify successful modification
                    System.out.println("\n=== Exit Condition: Successful Modification ===");
                    System.out.println("The system has successfully modified the banner.");
                } else {
                    System.out.println("ERROR: Failed to update banner image.");
                    handleErrorCase(selectedBanner, newImagePath);
                }
            } else {
                System.out.println("Transaction cancelled by user.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            handleServerInterruption();
        }
    }
    
    /**
     * Validates an image file for banner use.
     * @param imagePath Path to the image file
     * @return true if image is valid, false otherwise
     */
    private boolean validateImageForBanner(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            System.out.println("Error: Image path cannot be empty.");
            return false;
        }
        
        File imageFile = new File(imagePath);
        
        // Check if file exists
        if (!imageFile.exists()) {
            System.out.println("Error: Image file does not exist: " + imagePath);
            return false;
        }
        
        // Check if file is readable
        if (!imageFile.canRead()) {
            System.out.println("Error: Cannot read image file: " + imagePath);
            return false;
        }
        
        // Check file size (max 5MB for banner images)
        long fileSize = imageFile.length();
        long maxSize = 5 * 1024 * 1024; // 5MB
        if (fileSize > maxSize) {
            System.out.println("Error: Image file is too large. Maximum size is 5MB.");
            System.out.println("Current size: " + (fileSize / 1024) + "KB");
            return false;
        }
        
        // Check file extension
        String fileName = imageFile.getName().toLowerCase();
        String[] validExtensions = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        
        for (String ext : validExtensions) {
            if (fileName.endsWith(ext)) {
                System.out.println("Image validation passed: " + fileName);
                System.out.println("File size: " + (fileSize / 1024) + "KB");
                return true;
            }
        }
        
        System.out.println("Error: Invalid image format. Supported formats: JPG, JPEG, PNG, GIF, BMP");
        return false;
    }
    
    /**
     * Handles error case when image validation fails.
     * @param banner The banner being modified
     * @param invalidImagePath The invalid image path
     */
    private void handleErrorCase(Banner banner, String invalidImagePath) {
        System.out.println("\n=== Error Case Handler ===");
        System.out.println("Banner ID: " + banner.getBannerId());
        System.out.println("Invalid image path: " + invalidImagePath);
        System.out.println("Current image preserved: " + banner.getImagePath());
        System.out.println("Please try again with a valid image file.");
        
        // Log the error for monitoring
        logError("Image validation failed for banner " + banner.getBannerId() + 
                ", invalid path: " + invalidImagePath);
    }
    
    /**
     * Handles server interruption (ETOUR connection loss).
     */
    private void handleServerInterruption() {
        System.out.println("\n=== Server Interruption Handler ===");
        System.out.println("ERROR: Connection to server ETOUR has been interrupted.");
        System.out.println("Please check your network connection and try again.");
        System.out.println("Any pending transactions may need to be restarted.");
        
        // Log the interruption
        logError("Server ETOUR connection interrupted at " + new Date());
    }
    
    /**
     * Logs error messages to a file.
     * @param errorMessage The error message to log
     */
    private void logError(String errorMessage) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("banner_errors.log", true))) {
            writer.println(new Date() + " - " + errorMessage);
        } catch (IOException e) {
            System.err.println("Failed to write to error log: " + e.getMessage());
        }
    }
    
    /**
     * Gets the operator ID.
     * @return operator ID
     */
    public String getOperatorId() {
        return operatorId;
    }
    
    /**
     * Gets the operator name.
     * @return operator name
     */
    public String getOperatorName() {
        return operatorName;
    }
    
    /**
     * Gets the list of available rest points.
     * @return list of rest points
     */
    public List<RestPoint> getAvailableRestPoints() {
        return Collections.unmodifiableList(availableRestPoints);
    }
    
    /**
     * Adds a rest point to available list.
     * @param restPoint Rest point to add
     */
    public void addRestPoint(RestPoint restPoint) {
        if (restPoint != null) {
            availableRestPoints.add(restPoint);
        }
    }
    
    /**
     * Removes a rest point from available list.
     * @param pointId ID of the rest point to remove
     * @return true if removed, false if not found
     */
    public boolean removeRestPoint(String pointId) {
        return availableRestPoints.removeIf(point -> point.getPointId().equals(pointId));
    }
    
    /**
     * Clears the scanner resource.
     * Should be called when the operator is no longer needed.
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    /**
     * Simulates a test run of the modify banner flow with sample data.
     * This is for demonstration purposes.
     */
    public void testModifyBannerFlow() {
        System.out.println("\n=== Starting Test Modify Banner Flow ===");
        
        // Create sample rest points and banners
        RestPoint testPoint = new RestPoint("RP001", "Downtown Cafe", "123 Main St");
        Banner testBanner1 = new Banner("B001", "RP001", "/images/banner1.jpg");
        Banner testBanner2 = new Banner("B002", "RP001", "/images/banner2.png");
        
        testPoint.addBanner(testBanner1);
        testPoint.addBanner(testBanner2);
        
        List<RestPoint> testPoints = new ArrayList<>();
        testPoints.add(testPoint);
        
        setAvailableRestPoints(testPoints);
        
        // Execute the flow
        executeModifyBannerFlow();
        
        System.out.println("\n=== Test Flow Complete ===");
    }
}