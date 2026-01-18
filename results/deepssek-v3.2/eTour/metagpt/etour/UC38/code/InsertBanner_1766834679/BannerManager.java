import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * BannerManager class handles the business logic for banner insertion.
 * It coordinates between ImageValidator, RestaurantPoint, and other components
 * to ensure banners are inserted according to system requirements.
 * This class handles all error cases specified in the use case.
 */
public class BannerManager {
    
    private RestaurantPoint restaurantPoint;
    private List<Banner> insertedBanners;
    private List<String> operationLog;
    
    /**
     * Constructor for BannerManager.
     * @param restaurantPoint The restaurant point where banners will be managed
     * @throws IllegalArgumentException if restaurantPoint is null
     */
    public BannerManager(RestaurantPoint restaurantPoint) {
        if (restaurantPoint == null) {
            throw new IllegalArgumentException("RestaurantPoint cannot be null");
        }
        
        this.restaurantPoint = restaurantPoint;
        this.insertedBanners = new ArrayList<>();
        this.operationLog = new ArrayList<>();
        
        logOperation("BannerManager initialized for restaurant point: " + 
                    restaurantPoint.getPointName() + " (ID: " + restaurantPoint.getPointId() + ")");
    }
    
    /**
     * Main method to insert a banner with full validation.
     * This method follows the use case flow:
     * 1. Validates the banner image
     * 2. Checks maximum banner limit
     * 3. Handles error cases appropriately
     * 
     * @param banner The banner to insert
     * @return true if banner was successfully inserted, false otherwise
     */
    public boolean insertBanner(Banner banner) {
        if (banner == null) {
            logOperation("Insertion failed: Banner is null");
            return false;
        }
        
        logOperation("Starting banner insertion process for image: " + banner.getImagePath());
        logOperation("Banner uploaded by: " + banner.getUploadedBy() + 
                    " | Size: " + banner.getSize() + " bytes");
        
        // Step 1: Validate image characteristics
        if (!validateBannerImage(banner)) {
            logOperation("Insertion failed: Image validation failed for " + banner.getImagePath());
            System.out.println("Error: Invalid image characteristics. Use case Errored triggered.");
            return false;
        }
        
        // Step 2: Check maximum banners condition
        if (restaurantPoint.hasReachedMaxBanners()) {
            logOperation("Insertion failed: Maximum banners reached for restaurant point " + 
                        restaurantPoint.getPointId());
            System.out.println("Error: Restaurant point has reached maximum allowed banners (" + 
                             restaurantPoint.getMaxBanners() + ").");
            System.out.println("Exit condition: The point has already entered the maximum number of banners allowed.");
            return false;
        }
        
        // Additional check: Ensure banner doesn't already exist (by image path)
        if (isBannerAlreadyExists(banner)) {
            logOperation("Insertion failed: Banner with same image path already exists: " + 
                        banner.getImagePath());
            System.out.println("Error: A banner with the same image already exists.");
            return false;
        }
        
        // Step 3: Insert the banner into restaurant point
        boolean added = restaurantPoint.addBanner(banner);
        
        if (added) {
            insertedBanners.add(banner);
            logOperation("Banner successfully inserted: " + banner.getImagePath());
            logOperation("Restaurant point now has " + restaurantPoint.getCurrentBannerCount() + 
                        " out of " + restaurantPoint.getMaxBanners() + " banners.");
            
            // Step 4: Remember the banners (log to operation history)
            rememberBannerInsertion(banner);
            
            // Step 5: Notify about successful insertion
            notifyBannerInsertion(banner);
            
            return true;
        } else {
            logOperation("Insertion failed: Unknown error adding banner to restaurant point");
            System.out.println("Error: Failed to insert banner due to unknown error.");
            return false;
        }
    }
    
    /**
     * Validates a banner's image using ImageValidator.
     * @param banner The banner to validate
     * @return true if validation passes, false otherwise
     */
    private boolean validateBannerImage(Banner banner) {
        try {
            logOperation("Validating banner image: " + banner.getImagePath());
            
            // Use ImageValidator to check image characteristics
            boolean isValid = ImageValidator.validate(banner);
            
            if (!isValid) {
                logOperation("Image validation failed for: " + banner.getImagePath());
                
                // Check specific validation failures for better error messages
                if (!banner.isAllowedImageType()) {
                    logOperation("Invalid image type: " + banner.getImageType());
                }
                if (!banner.isSizeValid()) {
                    logOperation("Invalid image size: " + banner.getSize() + " bytes (max: " + 
                                ImageValidator.getMaxFileSize() + ")");
                }
                if (!banner.areDimensionsValid()) {
                    logOperation("Invalid image dimensions: " + banner.getWidth() + "x" + 
                                banner.getHeight() + " pixels (min: " + 
                                ImageValidator.getMinWidth() + "x" + 
                                ImageValidator.getMinHeight() + ")");
                }
            } else {
                logOperation("Image validation passed for: " + banner.getImagePath());
            }
            
            return isValid;
        } catch (Exception e) {
            logOperation("Image validation error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Checks if a banner with the same image path already exists.
     * @param banner The banner to check
     * @return true if a banner with same image path exists, false otherwise
     */
    private boolean isBannerAlreadyExists(Banner banner) {
        List<Banner> existingBanners = restaurantPoint.getBanners();
        
        for (Banner existingBanner : existingBanners) {
            if (existingBanner.getImagePath().equals(banner.getImagePath())) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Remembers the banner insertion by logging it to persistent storage.
     * In a real application, this would save to a database or file.
     * @param banner The banner that was inserted
     */
    private void rememberBannerInsertion(Banner banner) {
        String record = String.format(
            "Banner inserted at %s: Image='%s', UploadedBy='%s', RestaurantPoint='%s'",
            new Date(),
            banner.getImagePath(),
            banner.getUploadedBy(),
            restaurantPoint.getPointId()
        );
        
        logOperation("Remembering banner insertion: " + record);
        
        // In a real application, this would save to database or file system
        // For this demo, we just log it to the operation log
    }
    
    /**
     * Notifies about banner insertion.
     * In a real application, this might send notifications to users or other systems.
     * @param banner The banner that was inserted
     */
    private void notifyBannerInsertion(Banner banner) {
        String notification = String.format(
            "New banner inserted successfully!\n" +
            "Image: %s\n" +
            "Size: %s bytes\n" +
            "Uploaded by: %s\n" +
            "Restaurant point: %s (%s)\n" +
            "Current banners: %d/%d",
            banner.getImagePath(),
            banner.getSize(),
            banner.getUploadedBy(),
            restaurantPoint.getPointName(),
            restaurantPoint.getPointId(),
            restaurantPoint.getCurrentBannerCount(),
            restaurantPoint.getMaxBanners()
        );
        
        logOperation("Sending notification: " + notification);
        
        // In a real application, this would send email, push notification, etc.
        // For this demo, we print to console
        System.out.println("\n=== NOTIFICATION ===");
        System.out.println(notification);
        System.out.println("====================\n");
    }
    
    /**
     * Logs an operation to the operation log.
     * @param message The operation message to log
     */
    private void logOperation(String message) {
        String timestamp = new Date().toString();
        String logEntry = String.format("[%s] %s", timestamp, message);
        operationLog.add(logEntry);
        
        // For debugging, also print to console
        System.out.println("[BannerManager] " + message);
    }
    
    /**
     * Gets the current operation log.
     * @return List of operation log entries
     */
    public List<String> getOperationLog() {
        return new ArrayList<>(operationLog);
    }
    
    /**
     * Clears the operation log.
     */
    public void clearOperationLog() {
        operationLog.clear();
        logOperation("Operation log cleared");
    }
    
    /**
     * Gets the number of banners inserted by this BannerManager instance.
     * @return Count of inserted banners
     */
    public int getInsertedBannerCount() {
        return insertedBanners.size();
    }
    
    /**
     * Gets all banners inserted by this BannerManager instance.
     * @return List of inserted banners
     */
    public List<Banner> getInsertedBanners() {
        return new ArrayList<>(insertedBanners);
    }
    
    /**
     * Gets the restaurant point associated with this BannerManager.
     * @return The restaurant point
     */
    public RestaurantPoint getRestaurantPoint() {
        return restaurantPoint;
    }
    
    /**
     * Displays a summary of the banner management status.
     */
    public void displayStatus() {
        System.out.println("\n=== Banner Manager Status ===");
        System.out.println("Restaurant Point: " + restaurantPoint.getPointName() + 
                          " (ID: " + restaurantPoint.getPointId() + ")");
        System.out.println("Current banners: " + restaurantPoint.getCurrentBannerCount() + 
                          " / " + restaurantPoint.getMaxBanners());
        System.out.println("Has reached max: " + restaurantPoint.hasReachedMaxBanners());
        System.out.println("Banners inserted by this manager: " + insertedBanners.size());
        System.out.println("Operation log entries: " + operationLog.size());
        System.out.println("=============================\n");
    }
    
    /**
     * Simulates server connection check as per use case exit condition.
     * @return true if server is connected, false otherwise
     */
    public boolean checkServerConnection() {
        // In a real application, this would ping the server
        // For this demo, assume connection is usually available
        boolean isConnected = Math.random() > 0.15; // 85% chance of being connected
        
        if (!isConnected) {
            logOperation("Server connection check failed: Connection to server ETOUR interrupted");
            System.out.println("Exit condition: Interruption of the connection to the server ETOUR.");
        } else {
            logOperation("Server connection check passed");
        }
        
        return isConnected;
    }
    
    /**
     * Handles the "Errored" use case as specified in the requirements.
     * This method would be called when image validation fails.
     * @param banner The banner that caused the error
     * @param errorMessage Description of the error
     */
    public void handleErroredUseCase(Banner banner, String errorMessage) {
        logOperation("Errored use case triggered for banner: " + 
                    (banner != null ? banner.getImagePath() : "null"));
        logOperation("Error: " + errorMessage);
        
        System.out.println("\n=== ERROR HANDLING ===");
        System.out.println("Use case 'Errored' has been triggered.");
        System.out.println("Error details: " + errorMessage);
        if (banner != null) {
            System.out.println("Problematic banner: " + banner.getImagePath());
        }
        System.out.println("Please contact system administrator.");
        System.out.println("=====================\n");
        
        // In a real application, this might trigger error reporting, logging, etc.
    }
    
    /**
     * Simulates cancellation of operation by the operator.
     */
    public void simulateOperationCancellation() {
        logOperation("Operation cancelled by operator");
        System.out.println("Exit condition: The Point Of Operator Restaurant cancels the operation.");
    }
    
    /**
     * Returns a string representation of the BannerManager.
     * @return String representation
     */
    @Override
    public String toString() {
        return "BannerManager{" +
                "restaurantPointId='" + restaurantPoint.getPointId() + '\'' +
                ", insertedBanners=" + insertedBanners.size() +
                ", operationLogEntries=" + operationLog.size() +
                '}';
    }
}