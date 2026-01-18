import java.util.Date;

/**
 * Represents a banner advertisement for a restaurant point.
 * Contains image metadata and validation logic.
 */
public class Banner {
    private String imagePath;
    private long size; // in bytes
    private String uploadedBy;
    private Date uploadDate;
    private String imageType; // e.g., "jpg", "png"
    private int width;
    private int height;
    
    // Maximum allowed image size (5MB)
    private static final long MAX_IMAGE_SIZE = 5 * 1024 * 1024;
    
    // Allowed image types
    private static final String[] ALLOWED_IMAGE_TYPES = {"jpg", "jpeg", "png", "gif"};
    
    /**
     * Constructor for creating a banner.
     * @param imagePath Path or URL to the image
     * @param uploadedBy ID of the operator who uploaded the image
     * @throws IllegalArgumentException if imagePath is null or empty
     */
    public Banner(String imagePath, String uploadedBy) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Image path cannot be null or empty.");
        }
        if (uploadedBy == null || uploadedBy.trim().isEmpty()) {
            throw new IllegalArgumentException("Uploader ID cannot be null or empty.");
        }
        
        this.imagePath = imagePath;
        this.uploadedBy = uploadedBy;
        this.uploadDate = new Date();
        
        // Simulate image properties (in real app, these would be extracted from the image)
        this.size = (long) (Math.random() * 4 * 1024 * 1024) + 1024; // 1KB to 4MB
        this.imageType = extractImageType(imagePath);
        this.width = 800; // default width
        this.height = 600; // default height
    }
    
    /**
     * Extracts image type from the file path.
     * @param imagePath The path to the image
     * @return The image extension (lowercase) or "unknown" if not found
     */
    private String extractImageType(String imagePath) {
        if (imagePath == null) {
            return "unknown";
        }
        
        int lastDotIndex = imagePath.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < imagePath.length() - 1) {
            return imagePath.substring(lastDotIndex + 1).toLowerCase();
        }
        return "unknown";
    }
    
    /**
     * Checks if the image type is allowed.
     * @return true if the image type is in the allowed list, false otherwise
     */
    public boolean isAllowedImageType() {
        for (String allowedType : ALLOWED_IMAGE_TYPES) {
            if (allowedType.equalsIgnoreCase(this.imageType)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the image size is within limits.
     * @return true if size <= MAX_IMAGE_SIZE, false otherwise
     */
    public boolean isSizeValid() {
        return this.size <= MAX_IMAGE_SIZE;
    }
    
    /**
     * Checks if the image dimensions are valid.
     * Minimum dimensions required for a banner.
     * @return true if width >= 300 and height >= 150, false otherwise
     */
    public boolean areDimensionsValid() {
        return this.width >= 300 && this.height >= 150;
    }
    
    /**
     * Validates the banner image against all criteria.
     * @return true if all validations pass, false otherwise
     */
    public boolean validate() {
        return isAllowedImageType() && isSizeValid() && areDimensionsValid();
    }
    
    // Getters
    public String getImagePath() {
        return imagePath;
    }
    
    public long getSize() {
        return size;
    }
    
    public String getUploadedBy() {
        return uploadedBy;
    }
    
    public Date getUploadDate() {
        return uploadDate;
    }
    
    public String getImageType() {
        return imageType;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    /**
     * Returns a string representation of the banner.
     * @return Banner details
     */
    @Override
    public String toString() {
        return "Banner{" +
                "imagePath='" + imagePath + '\'' +
                ", size=" + size +
                ", uploadedBy='" + uploadedBy + '\'' +
                ", uploadDate=" + uploadDate +
                ", imageType='" + imageType + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
