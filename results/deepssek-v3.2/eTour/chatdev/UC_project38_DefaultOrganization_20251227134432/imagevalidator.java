/**
 * Utility class for validating images
 */
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
public class ImageValidator {
    // Validate image file
    public static ValidationResult validateImage(File imageFile) {
        try {
            // Check if file exists
            if (!imageFile.exists()) {
                return new ValidationResult(false, "File does not exist");
            }
            // Check file size (max 5MB)
            long fileSize = imageFile.length();
            if (fileSize > 5 * 1024 * 1024) { // 5MB in bytes
                return new ValidationResult(false, "Image size exceeds 5MB limit");
            }
            // Check file extension
            String fileName = imageFile.getName().toLowerCase();
            if (!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg") && 
                !fileName.endsWith(".png") && !fileName.endsWith(".gif")) {
                return new ValidationResult(false, "Invalid image format. Use JPG, PNG or GIF");
            }
            // Try to read image
            BufferedImage image = ImageIO.read(imageFile);
            if (image == null) {
                return new ValidationResult(false, "Cannot read image file");
            }
            // Check image dimensions (min 100x100, max 2000x2000)
            int width = image.getWidth();
            int height = image.getHeight();
            if (width < 100 || height < 100) {
                return new ValidationResult(false, "Image dimensions too small (min 100x100)");
            }
            if (width > 2000 || height > 2000) {
                return new ValidationResult(false, "Image dimensions too large (max 2000x2000)");
            }
            return new ValidationResult(true, "Image is valid", image);
        } catch (Exception e) {
            return new ValidationResult(false, "Error validating image: " + e.getMessage());
        }
    }
    // Inner class for validation results
    public static class ValidationResult {
        private boolean isValid;
        private String message;
        private BufferedImage image;
        public ValidationResult(boolean isValid, String message) {
            this.isValid = isValid;
            this.message = message;
        }
        public ValidationResult(boolean isValid, String message, BufferedImage image) {
            this.isValid = isValid;
            this.message = message;
            this.image = image;
        }
        public boolean isValid() {
            return isValid;
        }
        public String getMessage() {
            return message;
        }
        public BufferedImage getImage() {
            return image;
        }
    }
}