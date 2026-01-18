package com.etour.banner.service;

import com.etour.banner.exception.ImageValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Service for handling image validation, storage, and retrieval.
 * This class provides methods to validate uploaded image files against
 * predefined criteria (file type, size, dimensions), store them on the
 * file system, and generate their accessible URLs.
 */
@Service
public class ImageService {

    // Configurable path for storing uploaded images
    @Value("${app.upload.dir:${user.home}/uploads/images}")
    private String uploadDir;

    // Configurable maximum allowed image size in bytes (e.g., 2MB)
    @Value("${app.image.max-size:2097152}") // Default to 2MB
    private long maxImageSize;

    // Configurable list of allowed image content types
    @Value("${app.image.allowed-types:image/jpeg,image/png}")
    private List<String> allowedImageTypes;

    // Configurable minimum image width
    @Value("${app.image.min-width:100}")
    private int minWidth;

    // Configurable minimum image height
    @Value("${app.image.min-height:50}")
    private int minHeight;

    /**
     * Validates an uploaded image file based on predefined criteria:
     * - Not empty
     * - Allowed content type (JPEG, PNG)
     * - Within maximum size limit
     * - Meets minimum dimensions (width, height)
     *
     * @param image The {@link MultipartFile} to validate.
     * @throws ImageValidationException if the image fails any validation check.
     */
    public void validateImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw new ImageValidationException("Uploaded image cannot be empty.");
        }

        // Validate content type
        String contentType = image.getContentType();
        if (contentType == null || !allowedImageTypes.contains(contentType)) {
            throw new ImageValidationException("Invalid image file type. Allowed types are: " + String.join(", ", allowedImageTypes));
        }

        // Validate file size
        if (image.getSize() > maxImageSize) {
            throw new ImageValidationException("Image file size exceeds the maximum allowed limit of " + (maxImageSize / (1024 * 1024)) + "MB.");
        }

        // Validate image dimensions
        try {
            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
            if (bufferedImage == null) {
                throw new ImageValidationException("Could not read image data. Ensure it's a valid image file.");
            }
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            if (width < minWidth || height < minHeight) {
                throw new ImageValidationException(String.format("Image dimensions are too small. Minimum required: %dx%d pixels.", minWidth, minHeight));
            }
        } catch (IOException e) {
            throw new ImageValidationException("Failed to read image dimensions: " + e.getMessage());
        }
    }

    /**
     * Stores the uploaded image file to the configured upload directory.
     * The image is renamed to a unique UUID to prevent naming conflicts.
     *
     * @param image The {@link MultipartFile} to store.
     * @return The unique file name of the stored image.
     * @throws ImageValidationException if there is an error during file storage.
     */
    public String storeImage(MultipartFile image) {
        try {
            // Create the upload directory if it doesn't exist
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            // Generate a unique file name
            String originalFilename = image.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
            Path filePath = uploadPath.resolve(uniqueFileName);

            // Copy the image file to the target location
            Files.copy(image.getInputStream(), filePath);

            return uniqueFileName; // Return the unique file name
        } catch (IOException e) {
            throw new ImageValidationException("Failed to store image file: " + e.getMessage());
        }
    }

    /**
     * Generates the accessible URL for a stored image.
     * This URL is relative to the application's context path and is used
     * by the frontend to display the image.
     *
     * @param imageName The unique file name of the stored image.
     * @return The URL string for accessing the image.
     */
    public String getImageUrl(String imageName) {
        // Assuming the images are served under /images/ endpoint as configured in AppConfig
        return "/images/" + imageName;
    }
}