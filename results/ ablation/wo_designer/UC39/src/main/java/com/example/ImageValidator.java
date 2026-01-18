import java.io.File;

public class ImageValidator {
    // Validates the image file based on certain criteria
    public boolean isValid(File imageFile) {
        // Check if file exists
        if (imageFile == null || !imageFile.exists()) {
            System.out.println("File does not exist.");
            return false;
        }

        // Check file size (max 5MB)
        long fileSizeInBytes = imageFile.length();
        long fileSizeInMB = fileSizeInBytes / (1024 * 1024);
        if (fileSizeInMB > 5) {
            System.out.println("File size exceeds 5MB.");
            return false;
        }

        // Check file extension
        String fileName = imageFile.getName().toLowerCase();
        if (!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg") && !fileName.endsWith(".png")) {
            System.out.println("Invalid file format. Only JPG, JPEG, PNG are allowed.");
            return false;
        }

        // Additional checks could be added (e.g., dimensions)
        return true;
    }
}