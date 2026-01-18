import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A comprehensive image validator for banner advertisement images.
 * This class performs various checks to ensure image files meet the required standards.
 */
public class ImageValidator {
    
    // Supported image formats
    private static final Set<String> SUPPORTED_FORMATS = new HashSet<>(Arrays.asList(
        ".jpg", ".jpeg", ".png", ".gif", ".bmp"
    ));
    
    // Maximum file size for banner images (5MB)
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    
    // Minimum dimensions for banner images
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 150;
    
    // Maximum dimensions for banner images
    private static final int MAX_WIDTH = 1920;
    private static final int MAX_HEIGHT = 1080;
    
    /**
     * Validates an image file for use as a banner.
     * Performs comprehensive checks including format, size, and basic file validation.
     * 
     * @param imagePath Path to the image file
     * @return ValidationResult object containing validation status and messages
     */
    public ValidationResult validateImage(String imagePath) {
        ValidationResult result = new ValidationResult();
        
        if (imagePath == null || imagePath.trim().isEmpty()) {
            result.addError("Image path cannot be null or empty");
            return result;
        }
        
        File imageFile = new File(imagePath);
        
        // Check file existence and accessibility
        if (!imageFile.exists()) {
            result.addError("Image file does not exist: " + imagePath);
            return result;
        }
        
        if (!imageFile.canRead()) {
            result.addError("Cannot read image file. Check file permissions: " + imagePath);
            return result;
        }
        
        if (!imageFile.isFile()) {
            result.addError("Path is not a file: " + imagePath);
            return result;
        }
        
        // Check file size
        long fileSize = imageFile.length();
        if (fileSize == 0) {
            result.addError("Image file is empty (0 bytes)");
            return result;
        }
        
        if (fileSize > MAX_FILE_SIZE) {
            result.addError("Image file is too large. Maximum size: " + 
                          (MAX_FILE_SIZE / (1024 * 1024)) + "MB. " +
                          "Current size: " + String.format("%.2f", fileSize / (1024.0 * 1024.0)) + "MB");
            return result;
        }
        
        // Check file extension
        String fileName = imageFile.getName().toLowerCase();
        boolean validExtension = false;
        String fileExtension = "";
        
        for (String format : SUPPORTED_FORMATS) {
            if (fileName.endsWith(format)) {
                validExtension = true;
                fileExtension = format;
                break;
            }
        }
        
        if (!validExtension) {
            result.addError("Unsupported image format. Supported formats: " + 
                          String.join(", ", SUPPORTED_FORMATS));
            return result;
        }
        
        // Check file header/magic numbers for basic format validation
        if (!validateFileSignature(imageFile, fileExtension)) {
            result.addError("File signature does not match the expected format for " + fileExtension);
            return result;
        }
        
        // Check image dimensions if possible
        try {
            // Note: In a real implementation, you would use an image library like ImageIO
            // to read dimensions. For this example, we'll simulate it.
            // String dimensions = getImageDimensions(imageFile);
            // result.setDimensions(dimensions);
            
            // For demonstration, we'll add a success message about dimensions
            result.addInfo("Image dimensions check would be performed here with an image library");
        } catch (Exception e) {
            result.addWarning("Could not read image dimensions: " + e.getMessage());
        }
        
        // All checks passed
        result.setValid(true);
        result.addInfo("Image validation successful");
        result.addInfo("File: " + fileName);
        result.addInfo("Size: " + String.format("%.2f", fileSize / 1024.0) + "KB");
        result.addInfo("Format: " + fileExtension.substring(1).toUpperCase());
        
        return result;
    }
    
    /**
     * Validates file signature (magic numbers) to ensure file format matches its extension.
     * 
     * @param file The image file to validate
     * @param extension The expected file extension
     * @return true if file signature matches the extension, false otherwise
     */
    private boolean validateFileSignature(File file, String extension) {
        try {
            byte[] header = new byte[8];
            Path path = Paths.get(file.getAbsolutePath());
            byte[] fileBytes = Files.readAllBytes(path);
            
            if (fileBytes.length < 8) {
                return false;
            }
            
            System.arraycopy(fileBytes, 0, header, 0, 8);
            
            // Check for common image file signatures
            switch (extension) {
                case ".jpg":
                case ".jpeg":
                    // JPEG files start with FF D8 FF
                    return (header[0] == (byte)0xFF && header[1] == (byte)0xD8 && 
                            header[2] == (byte)0xFF);
                    
                case ".png":
                    // PNG files start with 89 50 4E 47 0D 0A 1A 0A
                    return (header[0] == (byte)0x89 && header[1] == (byte)0x50 && 
                            header[2] == (byte)0x4E && header[3] == (byte)0x47 &&
                            header[4] == (byte)0x0D && header[5] == (byte)0x0A &&
                            header[6] == (byte)0x1A && header[7] == (byte)0x0A);
                    
                case ".gif":
                    // GIF files start with "GIF8"
                    return (header[0] == 'G' && header[1] == 'I' && 
                            header[2] == 'F' && header[3] == '8');
                    
                case ".bmp":
                    // BMP files start with "BM"
                    return (header[0] == 'B' && header[1] == 'M');
                    
                default:
                    return true; // For unknown formats, assume valid
            }
        } catch (IOException e) {
            System.err.println("Error reading file signature: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Quick validation method for basic checks only.
     * 
     * @param imagePath Path to the image file
     * @return true if image passes basic validation, false otherwise
     */
    public boolean quickValidate(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return false;
        }
        
        File imageFile = new File(imagePath);
        
        // Basic checks only
        if (!imageFile.exists() || !imageFile.canRead()) {
            return false;
        }
        
        // Check file size
        long fileSize = imageFile.length();
        if (fileSize == 0 || fileSize > MAX_FILE_SIZE) {
            return false;
        }
        
        // Check file extension
        String fileName = imageFile.getName().toLowerCase();
        for (String format : SUPPORTED_FORMATS) {
            if (fileName.endsWith(format)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Gets the maximum allowed file size in bytes.
     * 
     * @return maximum file size in bytes
     */
    public static long getMaxFileSize() {
        return MAX_FILE_SIZE;
    }
    
    /**
     * Gets the maximum allowed file size in MB.
     * 
     * @return maximum file size in MB
     */
    public static double getMaxFileSizeMB() {
        return MAX_FILE_SIZE / (1024.0 * 1024.0);
    }
    
    /**
     * Gets the set of supported image formats.
     * 
     * @return set of supported formats
     */
    public static Set<String> getSupportedFormats() {
        return new HashSet<>(SUPPORTED_FORMATS);
    }
    
    /**
     * Inner class to represent the result of image validation.
     * Contains validation status, error messages, and informational messages.
     */
    public static class ValidationResult {
        private boolean isValid;
        private StringBuilder errors;
        private StringBuilder warnings;
        private StringBuilder info;
        private String dimensions;
        
        public ValidationResult() {
            this.isValid = false;
            this.errors = new StringBuilder();
            this.warnings = new StringBuilder();
            this.info = new StringBuilder();
            this.dimensions = "";
        }
        
        public boolean isValid() {
            return isValid;
        }
        
        public void setValid(boolean valid) {
            this.isValid = valid;
        }
        
        public String getErrors() {
            return errors.toString();
        }
        
        public void addError(String error) {
            if (errors.length() > 0) {
                errors.append("\n");
            }
            errors.append("ERROR: ").append(error);
        }
        
        public String getWarnings() {
            return warnings.toString();
        }
        
        public void addWarning(String warning) {
            if (warnings.length() > 0) {
                warnings.append("\n");
            }
            warnings.append("WARNING: ").append(warning);
        }
        
        public String getInfo() {
            return info.toString();
        }
        
        public void addInfo(String information) {
            if (info.length() > 0) {
                info.append("\n");
            }
            info.append("INFO: ").append(information);
        }
        
        public String getDimensions() {
            return dimensions;
        }
        
        public void setDimensions(String dimensions) {
            this.dimensions = dimensions;
        }
        
        /**
         * Returns a formatted string with all validation messages.
         * 
         * @return formatted validation report
         */
        public String getReport() {
            StringBuilder report = new StringBuilder();
            report.append("=== Image Validation Report ===\n");
            report.append("Status: ").append(isValid ? "VALID" : "INVALID").append("\n");
            
            if (errors.length() > 0) {
                report.append("\nErrors:\n").append(errors.toString()).append("\n");
            }
            
            if (warnings.length() > 0) {
                report.append("\nWarnings:\n").append(warnings.toString()).append("\n");
            }
            
            if (info.length() > 0) {
                report.append("\nInformation:\n").append(info.toString()).append("\n");
            }
            
            if (!dimensions.isEmpty()) {
                report.append("\nDimensions: ").append(dimensions).append("\n");
            }
            
            report.append("==============================\n");
            return report.toString();
        }
        
        @Override
        public String toString() {
            return getReport();
        }
    }
    
    /**
     * Main method for testing the ImageValidator class.
     * 
     * @param args Command line arguments (optional image file path)
     */
    public static void main(String[] args) {
        ImageValidator validator = new ImageValidator();
        
        if (args.length > 0) {
            String imagePath = args[0];
            System.out.println("Validating image: " + imagePath);
            ValidationResult result = validator.validateImage(imagePath);
            System.out.println(result.getReport());
            System.exit(result.isValid() ? 0 : 1);
        } else {
            // Demo mode
            System.out.println("=== ImageValidator Demo ===");
            System.out.println("Maximum file size: " + getMaxFileSizeMB() + " MB");
            System.out.println("Supported formats: " + String.join(", ", getSupportedFormats()));
            System.out.println("Minimum dimensions: " + MIN_WIDTH + "x" + MIN_HEIGHT);
            System.out.println("Maximum dimensions: " + MAX_WIDTH + "x" + MAX_HEIGHT);
            
            // Test with a non-existent file
            ValidationResult testResult = validator.validateImage("/nonexistent/image.jpg");
            System.out.println("\nTest 1 - Non-existent file:");
            System.out.println(testResult.getReport());
            
            // Test quick validation
            System.out.println("\nQuick validation test (should be false): " + 
                             validator.quickValidate("/nonexistent/image.jpg"));
        }
    }
}