/**
 * Service layer for handling banner operations
 */
import java.io.File;
import javax.swing.JOptionPane;
public class BannerService {
    private RestaurantPoint restaurantPoint;
    private boolean serverConnected;
    public BannerService(RestaurantPoint restaurantPoint) {
        this.restaurantPoint = restaurantPoint;
        this.serverConnected = true; // Start with server connected
    }
    // Simulate server connection status
    public void setServerConnected(boolean connected) {
        this.serverConnected = connected;
    }
    // Check if server is connected
    public boolean isServerConnected() {
        return serverConnected;
    }
    // Main method to insert a banner
    public InsertionResult insertBanner(File imageFile) {
        // Check server connection
        if (!serverConnected) {
            return new InsertionResult(false, "Interruption of the connection to the server ETOUR");
        }
        // Check if operator is authenticated
        if (!restaurantPoint.isAuthenticated()) {
            return new InsertionResult(false, "Operator not authenticated");
        }
        // Validate the image
        ImageValidator.ValidationResult validationResult = ImageValidator.validateImage(imageFile);
        if (!validationResult.isValid()) {
            // Enable the use case Errored (simulated by returning error result)
            return new InsertionResult(false, "Invalid image: " + validationResult.getMessage());
        }
        // Check if maximum banners reached
        if (restaurantPoint.getBannerCount() >= restaurantPoint.getMaxBanners()) {
            return new InsertionResult(false, 
                "The point of eating has already entered the maximum number of banners allowed (" + 
                restaurantPoint.getMaxBanners() + ")");
        }
        // Create banner
        Banner banner = new Banner(validationResult.getImage(), imageFile.getAbsolutePath());
        // Ask for confirmation of the insertion of the banner (GUI dialog)
        int response = JOptionPane.showConfirmDialog(null, 
            "Insert this banner?\nImage: " + imageFile.getName() + 
            "\nCurrent banners: " + restaurantPoint.getBannerCount() + "/" + restaurantPoint.getMaxBanners(),
            "Confirm Banner Insertion", 
            JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            // Add banner to restaurant point
            if (restaurantPoint.addBanner(banner)) {
                // Remember the banners associated with the point of rest (stored in restaurantPoint)
                return new InsertionResult(true, 
                    "Banner inserted successfully!\nTotal banners: " + 
                    restaurantPoint.getBannerCount() + "/" + restaurantPoint.getMaxBanners());
            } else {
                return new InsertionResult(false, "Failed to add banner (maximum reached)");
            }
        } else {
            return new InsertionResult(false, "The Point Of Operator Restaurant cancels the operation");
        }
    }
    // Inner class for insertion results
    public static class InsertionResult {
        private boolean success;
        private String message;
        public InsertionResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
        public boolean isSuccess() {
            return success;
        }
        public String getMessage() {
            return message;
        }
    }
}