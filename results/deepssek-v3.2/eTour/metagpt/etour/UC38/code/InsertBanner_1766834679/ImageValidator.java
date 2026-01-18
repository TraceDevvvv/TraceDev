import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * ImageValidator class provides static methods to validate image characteristics.
 * This class handles validation of image format, size, dimensions, and basic content checks.
 * It's used in the InsertBanner use case to ensure uploaded banners meet system requirements.
 */
public class ImageValidator {
    
    // Allowed image MIME types and extensions
    private static final Set<String> ALLOWED_MIME_TYPES = new HashSet<>(Arrays.asList(
        "image/jpeg", "image/jpg", "image/png", "image/gif", "image/webp"
    ));
    
    private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(Arrays.asList(
        "jpg", "jpeg", "png", "gif", "webp"
    ));
    
    // Maximum allowed file size in bytes (5MB)
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    
    // Minimum dimensions for banners
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 150;
    
    // Maximum dimensions for banners (optional constraint)
    private static final int MAX_WIDTH = 1920;
    private static final int MAX_HEIGHT = 1080;
    
    /**
     * Validates a Banner object's image characteristics.
     * This method performs multiple checks on the banner's image.
     * 
     * @param banner The Banner object to validate
     * @return true if all validations pass, false otherwise
     */
    public static boolean validate(Banner banner) {
        if (banner == null) {
            System.out.println("Validation failed: Banner is null");
            return false;
        }
        
        // Check image type/format
        if (!isValidImageType(banner)) {
            System.out.println("Validation failed: Invalid image type for banner " + banner.getImagePath());
            return false;
        }
        
        // Check image size
        if (!isValidImageSize(banner)) {
            System.out.println("Validation failed: Image size exceeds limit for banner " + banner.getImagePath());
            return false;
        }
        
        // Check image dimensions
        if (!isValidImageDimensions(banner)) {
            System.out.println("Validation failed: Invalid image dimensions for banner " + banner.getImagePath());
            return false;
        }
        
        // Additional check: if image path is a file, verify file exists and is readable
        if (!isImageAccessible(banner.getImagePath())) {
            System.out.println("Validation warning: Image file may not be accessible at " + banner.getImagePath());
            // Continue validation anyway as path might be a URL or in a different location
        }
        
        return true;
    }
    
    /**
     * Validates an image file directly from file path.
     * This is an alternative method that can be used when only the file path is available.
     * 
     * @param imagePath Path to the image file
     * @return true if image is valid, false otherwise
     */
    public static boolean validate(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            System.out.println("Validation failed: Image path is null or empty");
            return false;
        }
        
        // Check if file exists and is readable (for local files)
        if (!isImageAccessible(imagePath)) {
            System.out.println("Validation failed: Image file not accessible at " + imagePath);
            return false;
        }
        
        // Check file extension
        if (!hasValidExtension(imagePath)) {
            System.out.println("Validation failed: Invalid file extension for " + imagePath);
            return false;
        }
        
        // Check file size
        if (!isValidFileSize(imagePath)) {
            System.out.println("Validation failed: File size exceeds limit for " + imagePath);
            return false;
        }
        
        // Note: Dimensions check would require image processing libraries
        // For this simple implementation, we'll skip dimensions check for file path validation
        
        return true;
    }
    
    /**
     * Checks if the banner's image type is allowed.
     * 
     * @param banner The Banner object
     * @return true if image type is in allowed set, false otherwise
     */
    private static boolean isValidImageType(Banner banner) {
        if (banner == null || banner.getImageType() == null) {
            return false;
        }
        
        String imageType = banner.getImageType().toLowerCase();
        return ALLOWED_EXTENSIONS.contains(imageType);
    }
    
    /**
     * Checks if the banner's image size is within allowed limits.
     * 
     * @param banner The Banner object
     * @return true if image size <= MAX_FILE_SIZE, false otherwise
     */
    private static boolean isValidImageSize(Banner banner) {
        if (banner == null) {
            return false;
        }
        
        return banner.getSize() <= MAX_FILE_SIZE;
    }
    
    /**
     * Checks if the banner's image dimensions are within allowed ranges.
     * 
     * @param banner The Banner object
     * @return true if dimensions meet minimum requirements and don't exceed maximum, false otherwise
     */
    private static boolean isValidImageDimensions(Banner banner) {
        if (banner == null) {
            return false;
        }
        
        int width = banner.getWidth();
        int height = banner.getHeight();
        
        return width >= MIN_WIDTH && 
               height >= MIN_HEIGHT && 
               width <= MAX_WIDTH && 
               height <= MAX_HEIGHT;
    }
    
    /**
     * Checks if an image file at the given path exists and is readable.
     * For URLs, this method returns true (URL validation would require network access).
     * 
     * @param imagePath Path to the image file
     * @return true if file exists and is readable (or is a URL), false otherwise
     */
    private static boolean isImageAccessible(String imagePath) {
        if (imagePath == null) {
            return false;
        }
        
        // Check if path is a URL
        if (imagePath.startsWith("http://") || imagePath.startsWith("https://")) {
            // URL validation would require network connectivity check
            // For simplicity, assume URLs are accessible
            return true;
        }
        
        // Check if path is a local file
        Path path = Paths.get(imagePath);
        File file = path.toFile();
        
        return file.exists() && file.isFile() && file.canRead();
    }
    
    /**
     * Checks if the file has a valid image extension.
     * 
     * @param imagePath Path to the image file
     * @return true if extension is in allowed set, false otherwise
     */
    private static boolean hasValidExtension(String imagePath) {
        if (imagePath == null) {
            return false;
        }
        
        String extension = getFileExtension(imagePath).toLowerCase();
        return ALLOWED_EXTENSIONS.contains(extension);
    }
    
    /**
     * Extracts file extension from a file path.
     * 
     * @param filePath Path to the file
     * @return File extension without the dot, or empty string if no extension
     */
    private static String getFileExtension(String filePath) {
        if (filePath == null) {
            return "";
        }
        
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < filePath.length() - 1) {
            return filePath.substring(lastDotIndex + 1);
        }
        return "";
    }
    
    /**
     * Checks if the file size is within allowed limits.
     * 
     * @param imagePath Path to the image file
     * @return true if file size <= MAX_FILE_SIZE, false otherwise
     */
    private static boolean isValidFileSize(String imagePath) {
        if (imagePath == null) {
            return false;
        }
        
        // Skip size check for URLs (would require downloading)
        if (imagePath.startsWith("http://") || imagePath.startsWith("https://")) {
            return true;
        }
        
        try {
            Path path = Paths.get(imagePath);
            long fileSize = Files.size(path);
            return fileSize <= MAX_FILE_SIZE;
        } catch (IOException e) {
            System.out.println("Error checking file size: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Returns the maximum allowed file size in bytes.
     * 
     * @return Maximum file size in bytes
     */
    public static long getMaxFileSize() {
        return MAX_FILE_SIZE;
    }
    
    /**
     * Returns the minimum required width for banners.
     * 
     * @return Minimum width in pixels
     */
    public static int getMinWidth() {
        return MIN_WIDTH;
    }
    
    /**
     * Returns the minimum required height for banners.
     * 
     * @return Minimum height in pixels
     */
    public static int getMinHeight() {
        return MIN_HEIGHT;
    }
    
    /**
     * Returns the maximum allowed width for banners.
     * 
     * @return Maximum width in pixels
     */
    public static int getMaxWidth() {
        return MAX_WIDTH;
    }
    
    /**
     * Returns the maximum allowed height for banners.
     * 
     * @return Maximum height in pixels
     */
    public static int getMaxHeight() {
        return MAX_HEIGHT;
    }
    
    /**
     * Returns a set of allowed file extensions.
     * 
     * @return Set of allowed extensions
     */
    public static Set<String> getAllowedExtensions() {
        return new HashSet<>(ALLOWED_EXTENSIONS);
    }
    
    /**
     * Returns a set of allowed MIME types.
     * 
     * @return Set of allowed MIME types
     */
    public static Set<String> getAllowedMimeTypes() {
        return new HashSet<>(ALLOWED_MIME_TYPES);
    }
    
    /**
     * Formats file size in human-readable format.
     * 
     * @param size File size in bytes
     * @return Human-readable file size string
     */
    public static String formatFileSize(long size) {
        if (size < 1024) {
            return size + " bytes";
        } else if (size < 1024 * 1024) {
            return String.format("%.1f KB", size / 1024.0);
        } else {
            return String.format("%.1f MB", size / (1024.0 * 1024.0));
        }
    }
    
    /**
     * Displays validation requirements for user information.
     */
    public static void displayValidationRequirements() {
        System.out.println("\n--- Image Validation Requirements ---");
        System.out.println("Allowed formats: " + String.join(", ", ALLOWED_EXTENSIONS));
        System.out.println("Maximum file size: " + formatFileSize(MAX_FILE_SIZE));
        System.out.println("Minimum dimensions: " + MIN_WIDTH + "x" + MIN_HEIGHT + " pixels");
        System.out.println("Maximum dimensions: " + MAX_WIDTH + "x" + MAX_HEIGHT + " pixels");
        System.out.println("-------------------------------------\n");
    }
}