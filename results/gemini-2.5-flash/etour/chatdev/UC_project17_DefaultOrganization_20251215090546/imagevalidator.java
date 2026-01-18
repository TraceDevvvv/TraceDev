'''
Utility class for validating image file characteristics.
This implements a basic set of checks to ensure a selected file is a plausible image.
'''
package utils;
import java.io.File;
import javax.swing.ImageIcon; // Used to check if an image file is readable and valid initially
/**
 * Utility class for validating image file characteristics.
 * This implements a basic set of checks to ensure a selected file is a plausible image.
 */
public class ImageValidator {
    private static final long MAX_FILE_SIZE_BYTES = 5 * 1024 * 1024; // 5 MB
    private static final String[] ALLOWED_EXTENSIONS = {".jpg", ".jpeg", ".png", ".gif"};
    /**
     * Validates if a given file path points to a valid image file based on specific criteria.
     * This method performs checks for:
     * 1. Non-empty file path.
     * 2. File existence, whether it's a file, and if it's readable.
     * 3. Basic image format recognition by attempting to load it as an ImageIcon.
     * 4. File size against a maximum limit.
     * 5. File extension against a list of allowed types.
     *
     * @param filePath The absolute path to the image file.
     * @return true if the file satisfies all validation rules, false otherwise.
     */
    public boolean isValidImage(String filePath) {
        // Check 1: File path is not null or empty
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println("Validation Error: File path is null or empty.");
            return false;
        }
        File file = new File(filePath);
        // Check 2: File exists, is a file, and is readable
        if (!file.exists() || !file.isFile() || !file.canRead()) {
            System.out.println("Validation Error: File does not exist, is not a regular file, or cannot be read: " + filePath);
            return false;
        }
        // Check 3: Basic image format validation using ImageIcon
        // ImageIcon constructor returns a valid icon or an icon with -1 for dimensions if invalid/unsupported format.
        ImageIcon tempIcon = new ImageIcon(filePath);
        if (tempIcon.getIconWidth() == -1 || tempIcon.getIconHeight() == -1) {
            System.out.println("Validation Error: File is not a valid or recognized image format: " + filePath);
            return false;
        }
        // Check 4: File size validation
        long fileSizeInBytes = file.length();
        if (fileSizeInBytes > MAX_FILE_SIZE_BYTES) {
            double fileSizeMB = fileSizeInBytes / (1024.0 * 1024.0);
            double maxFileSizeMB = MAX_FILE_SIZE_BYTES / (1024.0 * 1024.0);
            System.out.printf("Validation Error: Image size (%.2f MB) exceeds maximum allowed (%.2f MB): %s%n",
                    fileSizeMB, maxFileSizeMB, filePath);
            return false;
        }
        // Check 5: File extension validation
        String fileName = file.getName().toLowerCase();
        boolean extensionAllowed = false;
        for (String ext : ALLOWED_EXTENSIONS) {
            if (fileName.endsWith(ext)) {
                extensionAllowed = true;
                break;
            }
        }
        if (!extensionAllowed) {
            System.out.print("Validation Error: Image file has an unsupported extension. Allowed: ");
            for (String ext : ALLOWED_EXTENSIONS) System.out.print(ext + " ");
            System.out.println(": " + filePath);
            return false;
        }
        // All checks passed
        return true;
    }
}