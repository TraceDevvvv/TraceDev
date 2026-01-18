/*
 * Manages the business logic for inserting banners, including validation,
 * checking limits, and simulating network operations.
 */
import javax.swing.JOptionPane;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File; // Needed for ImageIO.read(File)
public class BannerService {
    // Enum to represent the possible outcomes of a banner insertion attempt
    public enum BannerInsertionResult {
        SUCCESS,
        CANCELLED_BY_USER,
        INVALID_IMAGE_CHARACTERISTICS,
        MAX_BANNERS_EXCEEDED,
        CONNECTION_ERROR, // Simulates ETOUR or other network issues
        UNKNOWN_ERROR
    }
    private Restaurant restaurant;
    private Random random = new Random(); // For simulating random connection errors
    /**
     * Constructs a BannerService for a specific restaurant.
     * @param restaurant The Restaurant object to manage banners for.
     */
    public BannerService(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    /**
     * Attempts to insert a new banner associated with the restaurant.
     * This method orchestrates the full flow of events for banner insertion.
     *
     * @param imagePath The file path of the image to be used for the banner.
     * @return A BannerInsertionResult indicating the outcome of the operation.
     */
    public BannerInsertionResult insertBanner(String imagePath) {
        // Step 4: Check characteristics and banner limit, ask for confirmation
        // Check for connection error first, as it's a critical infrastructure issue
        if (simulateConnectionError()) {
            return BannerInsertionResult.CONNECTION_ERROR;
        }
        // Validate image characteristics
        if (!checkImageCharacteristics(imagePath)) {
            // "Errored" use case enabled: invalid image
            return BannerInsertionResult.INVALID_IMAGE_CHARACTERISTICS;
        }
        // Check if maximum banners limit is exceeded
        if (!restaurant.canAddMoreBanners()) {
            return BannerInsertionResult.MAX_BANNERS_EXCEEDED;
        }
        // Ask for confirmation from the operator
        if (!confirmInsertion(imagePath)) {
            // Exit condition: Point Of Operator Restaurant cancels the operation
            return BannerInsertionResult.CANCELLED_BY_USER;
        }
        // Step 5: Confirm the operation of insertion
        // If all checks pass and confirmed, proceed to create and store the banner
        Banner newBanner = new Banner(imagePath);
        if (restaurant.addBanner(newBanner)) {
            // Step 6: Remember the banners associated with the point of rest.
            System.out.println("Banner added: " + newBanner);
            return BannerInsertionResult.SUCCESS;
        } else {
            // Should not happen if canAddMoreBanners() was checked and passed,
            // but good for robustness.
            return BannerInsertionResult.UNKNOWN_ERROR;
        }
    }
    /**
     * Simulates checking the characteristics of the inserted image.
     * This version includes checks for file existence, common image extensions,
     * image decodeability, dimensions, and file size.
     *
     * @param imagePath The path to the image file.
     * @return True if the image is considered valid, false otherwise.
     */
    private boolean checkImageCharacteristics(String imagePath) {
        Path path = Path.of(imagePath);
        File imageFile = path.toFile(); // Convert Path to File for ImageIO usage
        // 1. Initial checks: does the file exist and is it a regular file?
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            System.err.println("Image file does not exist or is not a regular file: " + imagePath);
            return false;
        }
        // 2. Basic extension check (can be more thorough with ImageIO but good initial filter)
        String fileName = path.getFileName().toString().toLowerCase();
        boolean isValidExtension = fileName.endsWith(".jpg") ||
                                   fileName.endsWith(".jpeg") ||
                                   fileName.endsWith(".png") ||
                                   fileName.endsWith(".gif");
        if (!isValidExtension) {
            System.err.println("Invalid image file type (extension check) for: " + imagePath);
            return false;
        }
        // 3. Attempt to read the image to check for validity/corruption and get dimensions
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Failed to read image file due to I/O error or corruption: " + imagePath + " - " + e.getMessage());
            return false; // File is likely corrupted or not a valid image format
        }
        if (image == null) {
            System.err.println("Could not decode image data for: " + imagePath + ". File might be invalid or unsupported even with correct extension.");
            return false; // ImageIO couldn't decode it despite existing
        }
        // 4. Define and check image dimensions (example criteria for banners)
        final int MIN_WIDTH = 100;
        final int MIN_HEIGHT = 50;
        final int MAX_WIDTH = 1200;
        final int MAX_HEIGHT = 600;
        if (image.getWidth() < MIN_WIDTH || image.getHeight() < MIN_HEIGHT ||
            image.getWidth() > MAX_WIDTH || image.getHeight() > MAX_HEIGHT) {
            System.err.println("Image dimensions (" + image.getWidth() + "x" + image.getHeight() + 
                               ") are outside allowed range [" + MIN_WIDTH + "x" + MIN_HEIGHT + 
                               " to " + MAX_WIDTH + "x" + MAX_HEIGHT + "] for: " + imagePath);
            return false;
        }
        // 5. Define and check file size limit (example criteria: max 2MB)
        final long MAX_FILE_SIZE_BYTES = 2 * 1024 * 1024; // 2 MB
        try {
            long fileSize = Files.size(path);
            if (fileSize > MAX_FILE_SIZE_BYTES) {
                System.err.println("Image file size (" + String.format("%.2f", (double)fileSize / (1024 * 1024)) + 
                                   " MB) exceeds maximum allowed " + (MAX_FILE_SIZE_BYTES / (1024 * 1024)) + 
                                   " MB for: " + imagePath);
                return false;
            }
        } catch (IOException e) {
            System.err.println("Could not determine file size for: " + imagePath + " - " + e.getMessage());
            return false; // Treat as invalid if file size can't be checked
        }
        // If all checks pass
        return true;
    }
    /**
     * Displays a confirmation dialog to the operator for banner insertion.
     * @param imagePath The path of the image to be inserted.
     * @return True if the operator confirms, false if they cancel.
     */
    private boolean confirmInsertion(String imagePath) {
        int response = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to insert this banner?\nImage: " + imagePath,
            "Confirm Banner Insertion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        return response == JOptionPane.YES_OPTION;
    }
    /**
     * Simulates a connection error (ETOUR).
     * This method has a 10% chance of returning true, simulating network unavailability.
     * @return True if a connection error is simulated, false otherwise.
     */
    private boolean simulateConnectionError() {
        // 10% chance of a simulated connection error
        return random.nextInt(100) < 10;
    }
    /**
     * Provides access to the list of banners currently associated with the restaurant.
     * @return An unmodifiable list of banners.
     */
    public java.util.List<Banner> getRestaurantBanners() {
        return restaurant.getBanners();
    }
    /**
     * Gets the name of the restaurant associated with this service.
     * @return The restaurant's name.
     */
    public String getRestaurantName() {
        return restaurant.getName();
    }
    /**
     * Gets the maximum number of banners allowed for the associated restaurant.
     * @return The maximum banner limit.
     */
    public int getMaxRestaurantBanners() {
        return restaurant.getMaxBanners();
    }
    /**
     * Gets the current count of banners associated with the restaurant.
     * @return The number of banners.
     */
    public int getRestaurantBannerCount() {
        return restaurant.getBannerCount();
    }
}