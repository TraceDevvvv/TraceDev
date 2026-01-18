package domain;

/**
 * Concrete implementation of IBannerValidator for image URLs.
 * This implementation provides a simplified validation logic for demonstration purposes.
 */
public class ImageBannerValidator implements IBannerValidator {

    /**
     * Validates an image URL.
     * For this example, it simply checks if the URL is not null or empty
     * and contains ".png" or ".jpg".
     *
     * @param imageUrl The URL of the image to validate.
     * @return true if the image URL is considered valid, false otherwise.
     */
    @Override
    public boolean validate(String imageUrl) {
        // In a real application, this would involve more sophisticated checks:
        // - Reachability of the URL
        // - Image format and dimensions
        // - Content filtering
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            return false;
        }
        // Simple check for image file extensions
        return imageUrl.endsWith(".png") || imageUrl.endsWith(".jpg") || imageUrl.endsWith(".jpeg");
    }
}