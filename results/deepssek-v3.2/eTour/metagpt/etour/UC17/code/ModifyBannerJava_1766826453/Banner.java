import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Represents a Banner with an image and associated metadata.
 * This class handles banner data including image file validation.
 */
public class Banner {
    private String bannerId;
    private String refreshmentPointId;
    private String imagePath;
    private Date createdAt;
    private Date updatedAt;
    
    /**
     * Constructor for creating a new banner.
     * @param bannerId Unique identifier for the banner
     * @param refreshmentPointId ID of the associated refreshment point
     * @param imagePath Path to the banner image file
     */
    public Banner(String bannerId, String refreshmentPointId, String imagePath) {
        this.bannerId = bannerId;
        this.refreshmentPointId = refreshmentPointId;
        this.imagePath = imagePath;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
    
    /**
     * Validates the banner image file.
     * Checks if file exists, is readable, and has valid image extension.
     * @return true if image is valid, false otherwise
     */
    public boolean validateImage() {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return false;
        }
        
        File imageFile = new File(imagePath);
        
        // Check if file exists and is readable
        if (!imageFile.exists() || !imageFile.canRead()) {
            return false;
        }
        
        // Check file size (max 10MB for banner images)
        long fileSize = imageFile.length();
        if (fileSize > 10 * 1024 * 1024) { // 10MB limit
            return false;
        }
        
        // Check file extension for common image formats
        String fileName = imageFile.getName().toLowerCase();
        return fileName.endsWith(".jpg") || 
               fileName.endsWith(".jpeg") || 
               fileName.endsWith(".png") || 
               fileName.endsWith(".gif") ||
               fileName.endsWith(".bmp");
    }
    
    /**
     * Updates the banner image with a new image file.
     * @param newImagePath Path to the new image file
     * @return true if update was successful, false otherwise
     */
    public boolean updateImage(String newImagePath) {
        if (newImagePath == null || newImagePath.trim().isEmpty()) {
            return false;
        }
        
        String oldImagePath = this.imagePath;
        this.imagePath = newImagePath;
        
        // Validate the new image
        if (!validateImage()) {
            // Revert to old image if new one is invalid
            this.imagePath = oldImagePath;
            return false;
        }
        
        this.updatedAt = new Date();
        return true;
    }
    
    /**
     * Gets the banner ID.
     * @return banner ID
     */
    public String getBannerId() {
        return bannerId;
    }
    
    /**
     * Gets the refreshment point ID.
     * @return refreshment point ID
     */
    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }
    
    /**
     * Gets the image file path.
     * @return image path
     */
    public String getImagePath() {
        return imagePath;
    }
    
    /**
     * Gets the creation timestamp.
     * @return creation date
     */
    public Date getCreatedAt() {
        return createdAt;
    }
    
    /**
     * Gets the last update timestamp.
     * @return last update date
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    /**
     * Sets the image path (for testing or manual updates).
     * @param imagePath New image path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        this.updatedAt = new Date();
    }
    
    /**
     * Returns a string representation of the banner.
     * @return string representation
     */
    @Override
    public String toString() {
        return "Banner{" +
                "bannerId='" + bannerId + '\'' +
                ", refreshmentPointId='" + refreshmentPointId + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
    
    /**
     * Creates a backup of the current image file.
     * @param backupDirectory Directory to store the backup
     * @return true if backup was successful, false otherwise
     */
    public boolean backupImage(String backupDirectory) {
        if (imagePath == null || !validateImage()) {
            return false;
        }
        
        try {
            Path source = Paths.get(imagePath);
            String fileName = "backup_" + bannerId + "_" + System.currentTimeMillis() + 
                            getFileExtension(imagePath);
            Path target = Paths.get(backupDirectory, fileName);
            
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            System.err.println("Failed to backup image: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Helper method to extract file extension.
     * @param filePath Path to the file
     * @return file extension including the dot
     */
    private String getFileExtension(String filePath) {
        int lastDot = filePath.lastIndexOf('.');
        if (lastDot > 0 && lastDot < filePath.length() - 1) {
            return filePath.substring(lastDot);
        }
        return "";
    }
}