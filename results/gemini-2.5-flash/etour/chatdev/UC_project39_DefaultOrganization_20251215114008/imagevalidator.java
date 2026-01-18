'''
Utility class for validating image files.
It checks basic characteristics like file existence, image format, and potentially dimensions.
'''
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
// Removed unused imports Font and Graphics2D as they were not directly used for validation logic itself.
// import java.awt.Font;
// import java.awt.Graphics2D;
public class ImageValidator {
    /**
     * Checks if a given file path points to a valid image file.
     * It verifies if the file exists, is readable, and can be read as a common image format.
     *
     * @param imagePath The absolute or relative path to the image file.
     * @return true if the image is considered valid, false otherwise.
     */
    public boolean isValidImage(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return false;
        }
        File imageFile = new File(imagePath);
        // Check if the file exists and is actually a file, not a directory.
        if (!imageFile.exists() || !imageFile.isFile()) {
            System.err.println("Validation failed: File does not exist or is not a file: " + imagePath);
            return false;
        }
        // Check if the file is readable.
        if (!imageFile.canRead()) {
            System.err.println("Validation failed: File is not readable: " + imagePath);
            return false;
        }
        // Try to read the image to check if it's a valid image format.
        // This is a more robust check than just file extension.
        try {
            BufferedImage image = ImageIO.read(imageFile);
            if (image == null) {
                System.err.println("Validation failed: Could not decode image file: " + imagePath + ". (ImageIO.read returned null)");
                return false; // ImageIO.read returns null if no registered ImageReader is found
            }
            // Optionally, add checks for dimensions or size here.
            // For example:
            if (image.getWidth() < 10 || image.getHeight() < 10) { // Reduced min dimensions for dummy images
                 System.err.println("Validation failed: Image dimensions are too small (min 10x10): " + image.getWidth() + "x" + image.getHeight());
                 // return false; // Uncomment to enforce minimum dimensions
            }
            return true;
        } catch (IOException e) {
            System.err.println("Validation failed: Error reading image file: " + imagePath + " - " + e.getMessage());
            return false; // Error during reading (e.g., corrupt file, unsupported format)
        }
    }
    /**
     * Gathers and returns characteristics of an image file.
     * For simplification, this method will return a basic string.
     * In a real application, it might return an object with width, height, size, format, etc.
     *
     * @param imagePath The path to the image file.
     * @return A string describing the image characteristics, or an error message if invalid.
     */
    public String getImageDetails(String imagePath) {
        File imageFile = new File(imagePath);
        if (!isValidImage(imagePath)) { // Reuse isValidImage for initial check
            return "Invalid or unreadable image file.";
        }
        try {
            BufferedImage image = ImageIO.read(imageFile);
            long fileSizeKB = imageFile.length() / 1024; // Size in KB
            String format = ImageValidator.getFileExtension(imagePath); // Use the static getFileExtension
            return String.format("Format: %s, Dimensions: %dx%d pixels, Size: %d KB",
                                 format.toUpperCase(), image.getWidth(), image.getHeight(), fileSizeKB);
        } catch (IOException e) {
            System.err.println("Error getting image details: " + e.getMessage());
            return "Error retrieving details.";
        }
    }
    /**
     * Extracts the file extension from a given file path.
     * @param filename The path to the file.
     * @return The file extension (e.g., "png", "jpg"), or an empty string if no extension.
     */
    public static String getFileExtension(String filename) { // Changed to public static
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }
}