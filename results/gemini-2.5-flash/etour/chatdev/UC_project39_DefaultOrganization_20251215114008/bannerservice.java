'''
This class acts as a service layer for managing Banner objects.
It simulates interaction with a data store (in this case, an in-memory list)
and incorporates business logic like image validation.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
public class BannerService {
    private List<Banner> banners; // Simulates a database or external data source
    private ImageValidator imageValidator;
    /**
     * Constructs a BannerService instance.
     * Initializes the in-memory banner list and the ImageValidator.
     */
    public BannerService() {
        this.banners = new ArrayList<>();
        this.imageValidator = new ImageValidator();
        // Create a dummy 'images' directory for testing if it doesn't exist
        java.io.File imageDir = new java.io.File("images");
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }
        // Create dummy image files if they don't exist
        createDummyImageFiles();
    }
    /**
     * Adds a banner to the in-memory list.
     * @param banner The Banner object to add.
     */
    public void addBanner(Banner banner) {
        this.banners.add(banner);
    }
    /**
     * Retrieves all banners associated with the restaurant operator.
     * In a real application, this might filter by operator ID.
     * @return A list of Banner objects.
     */
    public List<Banner> getAllBanners() {
        // Simulates fetching banners for the authenticated operator.
        // For this use case, we assume all banners belong to the operator.
        return new ArrayList<>(banners); // Return a copy to prevent external modification
    }
    /**
     * Updates the image path for a specific banner.
     * This method includes the core business logic of validating the new image.
     *
     * @param bannerId The ID of the banner to update.
     * @param newImagePath The new file path for the banner's image.
     * @return A {@code BannerUpdateResult} indicating success or failure and a message.
     */
    public BannerUpdateResult updateBannerImage(String bannerId, String newImagePath) {
        // Step 6: Check the characteristics of the inserted image.
        if (!imageValidator.isValidImage(newImagePath)) {
            // In the event that the inserted image is not valid, enable the use case Errored.
            return new BannerUpdateResult(false, "Invalid image: The selected file is not a valid image or does not meet requirements.");
        }
        // Simulate a network or server connection check
        // For a local application, this could be a dummy check or omitted.
        // For the "server ETOUR" mentioned, this would be an actual API call.
        if (simulateNetworkInterruption()) {
             return new BannerUpdateResult(false, "Connection to server ETOUR interrupted. Please try again.");
        }
        Optional<Banner> bannerOptional = banners.stream()
                .filter(b -> b.getId().equals(bannerId))
                .findFirst();
        if (bannerOptional.isPresent()) {
            Banner bannerToUpdate = bannerOptional.get();
            // Step 8: Bookmark this new image for the selected banner.
            // In a real system, this would involve updating a database record.
            bannerToUpdate.setImagePath(newImagePath);
            System.out.println("Banner '" + bannerToUpdate.getName() + "' image updated to: " + newImagePath);
            // Step 7: Confirmation of the transaction change.
            // This is handled by the GUI post-service call.
            return new BannerUpdateResult(true, "Banner image updated successfully.");
        } else {
            return new BannerUpdateResult(false, "Banner with ID " + bannerId + " not found.");
        }
    }
    /**
     * Helper method to simulate a network interruption or server error.
     * @return true if a simulated interruption occurs, false otherwise.
     */
    private boolean simulateNetworkInterruption() {
        // Simulate a 10% chance of network interruption
        return Math.random() < 0.1;
    }
    /**
     * Creates dummy image files for testing purposes if they don't exist.
     * This ensures the application has some images to validate against.
     */
    private void createDummyImageFiles() {
        String[] dummyImageNames = {"default_banner_1.png", "default_banner_2.jpg", "default_banner_3.gif"};
        // Ensure the 'images' directory exists first
        java.io.File imageDir = new java.io.File("images");
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }
        for (String name : dummyImageNames) {
            java.io.File file = new java.io.File(imageDir, name); // Use imageDir for creating file
            if (!file.exists() && !file.isDirectory()) { // Check if it's not an existing file or directory
                try {
                    // Create a minimal valid image file (e.g., 100x50 pixels)
                    int width = 200;
                    int height = 100;
                    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2d = img.createGraphics();
                    // Fill with a background color
                    g2d.setColor(Color.LIGHT_GRAY);
                    g2d.fillRect(0, 0, width, height);
                    // Add some text for identification
                    g2d.setColor(Color.BLACK);
                    g2d.setFont(new Font("Arial", Font.BOLD, 12));
                    String displayText = name.substring(0, name.lastIndexOf('.')); // e.g., "default_banner_1"
                    g2d.drawString(displayText, 10, height / 2 + 5);
                    g2d.dispose();
                    // Use the centralized ImageValidator's getFileExtension method
                    String format = ImageValidator.getFileExtension(name);
                    if (format.isEmpty()) format = "png"; // Default to png if no obvious extension or unable to extract
                    boolean written = ImageIO.write(img, format, file);
                    if (written) {
                        System.out.println("Created dummy image file: " + file.getAbsolutePath());
                    } else {
                        System.err.println("Could not write dummy image file with format " + format + ": " + file.getName());
                    }
                } catch (java.io.IOException e) {
                    System.err.println("Could not create dummy image file " + file.getName() + ": " + e.getMessage());
                }
            }
        }
        // Also create an 'invalid' file for testing validation failures
        java.io.File invalidFile = new java.io.File(imageDir, "invalid.txt"); // Use imageDir for creating file
        if (!invalidFile.exists() && !invalidFile.isDirectory()) {
            try {
                invalidFile.createNewFile();
                // Write some text content to make it a non-image file
                Files.write(invalidFile.toPath(), "This is not an image file. It's just plain text.".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Created dummy invalid file: " + invalidFile.getAbsolutePath());
            } catch (java.io.IOException e) {
                System.err.println("Could not create dummy invalid file: " + e.getMessage());
            }
        }
    }
    // Removed the duplicate getFileExtension method as per instructions.
    /*
    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }
    */
    /**
     * Nested static class to encapsulate the result of a banner update operation.
     * This helps in returning both success status and a descriptive message.
     */
    public static class BannerUpdateResult {
        private final boolean success;
        private final String message;
        /**
         * Constructs a new BannerUpdateResult.
         * @param success True if the update was successful, false otherwise.
         * @param message A descriptive message about the update operation.
         */
        public BannerUpdateResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
        /**
         * Checks if the update operation was successful.
         * @return True if successful, false otherwise.
         */
        public boolean isSuccess() {
            return success;
        }
        /**
         * Returns the message associated with the update operation.
         * @return The message (e.g., success message, error message).
         */
        public String getMessage() {
            return message;
        }
    }
}