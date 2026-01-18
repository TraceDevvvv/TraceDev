package com.etur.insertbanner;

import java.io.File;
import java.time.LocalDateTime;

/**
 * Represents a banner that can be associated with a refreshment point.
 * Contains image data, validation logic, and associated metadata.
 */
public class Banner {
    private String id;
    private String imagePath; // Path to the image file
    private String imageName; // Original image name
    private long imageSize; // Image size in bytes
    private String imageFormat; // Image format (jpg, png, etc.)
    private int width; // Image width in pixels
    private int height; // Image height in pixels
    private LocalDateTime creationDate;
    private String refreshmentPointId; // ID of the associated refreshment point
    
    // Constants for validation
    public static final long MAX_IMAGE_SIZE = 5 * 1024 * 1024; // 5MB
    public static final String[] ALLOWED_FORMATS = {"jpg", "jpeg", "png", "gif", "bmp"};
    public static final int MIN_WIDTH = 100;
    public static final int MIN_HEIGHT = 100;
    public static final int MAX_WIDTH = 1920;
    public static final int MAX_HEIGHT = 1080;
    
    /**
     * Constructor for creating a new banner.
     * 
     * @param id Unique identifier for the banner
     * @param imagePath Path to the image file
     * @param imageName Original image name
     * @param imageSize Image size in bytes
     * @param refreshmentPointId ID of the associated refreshment point
     */
    public Banner(String id, String imagePath, String imageName, long imageSize, String refreshmentPointId) {
        this.id = id;
        this.imagePath = imagePath;
        this.imageName = imageName;
        this.imageSize = imageSize;
        this.refreshmentPointId = refreshmentPointId;
        this.creationDate = LocalDateTime.now();
        
        // Attempt to extract format from image name
        if (imageName != null && imageName.contains(".")) {
            this.imageFormat = imageName.substring(imageName.lastIndexOf(".") + 1).toLowerCase();
        } else {
            this.imageFormat = "unknown";
        }
        
        // Default dimensions
        this.width = 800;
        this.height = 600;
    }
    
    /**
     * Validates the banner image according to system requirements.
     * 
     * @return true if the image is valid, false otherwise
     */
    public boolean validateImage() {
        // Check image size
        if (imageSize <= 0 || imageSize > MAX_IMAGE_SIZE) {
            return false;
        }
        
        // Check image format
        boolean validFormat = false;
        for (String format : ALLOWED_FORMATS) {
            if (format.equalsIgnoreCase(imageFormat)) {
                validFormat = true;
                break;
            }
        }
        if (!validFormat) {
            return false;
        }
        
        // Check image dimensions
        if (width < MIN_WIDTH || width > MAX_WIDTH || height < MIN_HEIGHT || height > MAX_HEIGHT) {
            return false;
        }
        
        // Check if file exists (simulated - in real app would check file system)
        if (imagePath == null || imagePath.trim().isEmpty() || imageName == null || imageName.trim().isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Simulates image dimension extraction.
     * In a real application, this would use an image processing library.
     * 
     * @param width Image width in pixels
     * @param height Image height in pixels
     */
    public void setImageDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    // Getters and setters
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public String getImageName() {
        return imageName;
    }
    
    public void setImageName(String imageName) {
        this.imageName = imageName;
        // Update format when name changes
        if (imageName != null && imageName.contains(".")) {
            this.imageFormat = imageName.substring(imageName.lastIndexOf(".") + 1).toLowerCase();
        }
    }
    
    public long getImageSize() {
        return imageSize;
    }
    
    public void setImageSize(long imageSize) {
        this.imageSize = imageSize;
    }
    
    public String getImageFormat() {
        return imageFormat;
    }
    
    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    
    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }
    
    public void setRefreshmentPointId(String refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
    }
    
    /**
     * Returns a string representation of the banner.
     * 
     * @return String containing banner details
     */
    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", imageName='" + imageName + '\'' +
                ", imageSize=" + imageSize +
                ", imageFormat='" + imageFormat + '\'' +
                ", dimensions=" + width + "x" + height +
                ", refreshmentPointId='" + refreshmentPointId + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
    
    /**
     * Returns detailed validation information.
     * Useful for error reporting when validation fails.
     * 
     * @return String containing validation status and issues
     */
    public String getValidationDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Banner Validation Report:\n");
        details.append("------------------------\n");
        
        // Check image size
        if (imageSize <= 0) {
            details.append("- ERROR: Image size must be greater than 0 bytes\n");
        } else if (imageSize > MAX_IMAGE_SIZE) {
            details.append("- ERROR: Image size exceeds maximum allowed size (")
                   .append(MAX_IMAGE_SIZE / (1024 * 1024)).append("MB)\n");
        } else {
            details.append("- OK: Image size is within limits (")
                   .append(imageSize / 1024).append("KB)\n");
        }
        
        // Check image format
        boolean validFormat = false;
        for (String format : ALLOWED_FORMATS) {
            if (format.equalsIgnoreCase(imageFormat)) {
                validFormat = true;
                break;
            }
        }
        if (!validFormat) {
            details.append("- ERROR: Image format '").append(imageFormat)
                   .append("' is not supported. Allowed formats: ");
            for (int i = 0; i < ALLOWED_FORMATS.length; i++) {
                details.append(ALLOWED_FORMATS[i]);
                if (i < ALLOWED_FORMATS.length - 1) details.append(", ");
            }
            details.append("\n");
        } else {
            details.append("- OK: Image format '").append(imageFormat).append("' is supported\n");
        }
        
        // Check image dimensions
        if (width < MIN_WIDTH || width > MAX_WIDTH) {
            details.append("- ERROR: Image width must be between ")
                   .append(MIN_WIDTH).append(" and ").append(MAX_WIDTH).append(" pixels\n");
        } else {
            details.append("- OK: Image width is ").append(width).append(" pixels\n");
        }
        
        if (height < MIN_HEIGHT || height > MAX_HEIGHT) {
            details.append("- ERROR: Image height must be between ")
                   .append(MIN_HEIGHT).append(" and ").append(MAX_HEIGHT).append(" pixels\n");
        } else {
            details.append("- OK: Image height is ").append(height).append(" pixels\n");
        }
        
        // Check required fields
        if (imagePath == null || imagePath.trim().isEmpty()) {
            details.append("- ERROR: Image path is required\n");
        } else {
            details.append("- OK: Image path is specified\n");
        }
        
        if (imageName == null || imageName.trim().isEmpty()) {
            details.append("- ERROR: Image name is required\n");
        } else {
            details.append("- OK: Image name is specified\n");
        }
        
        return details.toString();
    }
}