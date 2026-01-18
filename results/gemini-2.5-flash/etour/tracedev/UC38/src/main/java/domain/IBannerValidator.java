package domain;

/**
 * Interface for validating banner-related data, such as image URLs.
 */
public interface IBannerValidator {
    /**
     * Validates the provided image URL.
     *
     * @param imageUrl The URL of the image to validate.
     * @return true if the image URL is valid, false otherwise.
     */
    boolean validate(String imageUrl);
}