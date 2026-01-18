package com.etour.banner.service;

import com.etour.banner.dto.BannerUploadRequest;
import com.etour.banner.exception.EtourServiceException;
import com.etour.banner.exception.InvalidImageException;
import com.etour.banner.exception.MaxBannersExceededException;
import com.etour.banner.model.Banner;
import com.etour.banner.model.PointOfRest; // Conceptual, not directly used in persistence here
import com.etour.banner.repository.BannerRepository;
import com.etour.banner.util.ImageValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service layer for handling banner-related business logic.
 * This class orchestrates the process of inserting a new banner,
 * including image validation, checking banner limits, storing the image,
 * persisting banner metadata, and notifying external systems.
 */
@Service
public class BannerService {

    private static final Logger logger = LoggerFactory.getLogger(BannerService.class);

    private final BannerRepository bannerRepository;
    private final ImageValidator imageValidator;

    // Configuration for image storage and banner limits
    @Value("${banner.storage.location}")
    private String bannerStorageLocation;

    @Value("${banner.max-per-point-of-rest}")
    private int maxBannersPerPointOfRest;

    // Placeholder for ETOUR server interaction (e.g., a dedicated client)
    // In a real application, this would be an injected client for an external API.
    // For this implementation, we'll simulate it.
    private final EtourServerClient etourServerClient;

    /**
     * Constructs a BannerService with necessary dependencies.
     *
     * @param bannerRepository The repository for banner data persistence.
     * @param imageValidator The utility for validating image characteristics.
     * @param etourServerClient The client for interacting with the ETOUR server.
     */
    public BannerService(BannerRepository bannerRepository, ImageValidator imageValidator, EtourServerClient etourServerClient) {
        this.bannerRepository = bannerRepository;
        this.imageValidator = imageValidator;
        this.etourServerClient = etourServerClient;
    }

    /**
     * Inserts a new banner into the system.
     * This method performs several steps:
     * 1. Validates the uploaded image.
     * 2. Checks if the maximum number of banners for the given Point of Rest has been exceeded.
     * 3. Stores the image file to the configured storage location.
     * 4. Creates and persists a new Banner entity.
     * 5. Notifies the ETOUR server about the new banner.
     *
     * @param request The BannerUploadRequest containing the PointOfRest ID and the image file.
     * @return The newly created Banner object.
     * @throws InvalidImageException If the uploaded image does not meet the validation criteria.
     * @throws MaxBannersExceededException If the Point of Rest has already reached its maximum banner limit.
     * @throws EtourServiceException If there's an issue communicating with the ETOUR server.
     * @throws IOException If there's an error during image file storage.
     */
    public Banner insertBanner(BannerUploadRequest request) throws InvalidImageException, MaxBannersExceededException, EtourServiceException, IOException {
        String pointOfRestId = request.getPointOfRestId();
        MultipartFile imageFile = request.getImageFile();

        logger.debug("Starting banner insertion for PointOfRestId: {}", pointOfRestId);

        // 1. Validate the uploaded image
        imageValidator.validate(imageFile);
        logger.debug("Image validation successful for PointOfRestId: {}", pointOfRestId);

        // 2. Check if the maximum number of banners has been exceeded
        checkMaxBanners(pointOfRestId);
        logger.debug("Max banners check passed for PointOfRestId: {}", pointOfRestId);

        // 3. Store the image file
        String imageUrl = storeImage(imageFile);
        logger.debug("Image stored at: {}", imageUrl);

        // 4. Create and persist a new Banner entity
        Banner newBanner = createAndSaveBanner(pointOfRestId, imageFile, imageUrl);
        logger.info("Banner created and saved with ID: {} for PointOfRestId: {}", newBanner.getId(), pointOfRestId);

        // 5. Notify the ETOUR server
        notifyEtourServer(newBanner);
        logger.info("ETOUR server notified for banner ID: {}", newBanner.getId());

        return newBanner;
    }

    /**
     * Validates the image characteristics using the ImageValidator.
     *
     * @param image The MultipartFile representing the uploaded image.
     * @throws InvalidImageException If the image is invalid.
     */
    private void validateImage(MultipartFile image) throws InvalidImageException {
        imageValidator.validate(image);
    }

    /**
     * Checks if the number of banners associated with a Point of Rest
     * has exceeded the maximum allowed limit.
     *
     * @param pointOfRestId The ID of the Point of Rest.
     * @throws MaxBannersExceededException If the limit is exceeded.
     */
    private void checkMaxBanners(String pointOfRestId) throws MaxBannersExceededException {
        long currentBannerCount = bannerRepository.countByPointOfRestId(pointOfRestId);
        if (currentBannerCount >= maxBannersPerPointOfRest) {
            logger.warn("Max banners ({}) exceeded for PointOfRestId: {}", maxBannersPerPointOfRest, pointOfRestId);
            throw new MaxBannersExceededException("Maximum number of banners (" + maxBannersPerPointOfRest + ") reached for Point of Rest: " + pointOfRestId);
        }
    }

    /**
     * Stores the uploaded image file to the configured storage location.
     *
     * @param imageFile The MultipartFile to store.
     * @return The URL or path where the image is stored.
     * @throws IOException If an error occurs during file storage.
     */
    private String storeImage(MultipartFile imageFile) throws IOException {
        if (imageFile.isEmpty()) {
            throw new IOException("Failed to store empty file.");
        }

        // Ensure the storage directory exists
        Path uploadPath = Paths.get(bannerStorageLocation);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generate a unique file name to prevent collisions
        String originalFilename = imageFile.getOriginalFilename();
        String fileExtension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
        Path filePath = uploadPath.resolve(uniqueFileName);

        // Copy the file to the target location
        Files.copy(imageFile.getInputStream(), filePath);

        // Return a relative path or a URL that can be used to access the image
        // For simplicity, returning the file name. In a real app, this might be a full URL.
        return "/banners/" + uniqueFileName; // Assuming a web server serves from /banners
    }

    /**
     * Creates a new Banner entity and saves it to the database.
     *
     * @param pointOfRestId The ID of the Point of Rest.
     * @param imageFile The uploaded image file.
     * @param imageUrl The URL/path where the image is stored.
     * @return The saved Banner entity.
     */
    private Banner createAndSaveBanner(String pointOfRestId, MultipartFile imageFile, String imageUrl) {
        Banner banner = new Banner();
        banner.setId(UUID.randomUUID().toString()); // Generate a unique ID for the banner
        banner.setPointOfRestId(pointOfRestId);
        banner.setImageUrl(imageUrl);
        banner.setOriginalFileName(imageFile.getOriginalFilename());
        banner.setFileType(imageFile.getContentType());
        banner.setFileSize(imageFile.getSize());
        banner.setUploadDate(LocalDateTime.now());

        return bannerRepository.save(banner);
    }

    /**
     * Notifies the ETOUR server about the newly inserted banner.
     * This is a placeholder for actual external service integration.
     *
     * @param banner The banner that was inserted.
     * @throws EtourServiceException If the notification fails due to connection issues or server errors.
     */
    private void notifyEtourServer(Banner banner) throws EtourServiceException {
        logger.info("Attempting to notify ETOUR server for banner ID: {}", banner.getId());
        // Simulate ETOUR server interaction
        etourServerClient.sendBannerNotification(banner);
        logger.info("Successfully notified ETOUR server for banner ID: {}", banner.getId());
    }
}

// Placeholder for ETOUR Server Client
// In a real application, this would be a more sophisticated client,
// possibly using RestTemplate or WebClient to call an external API.
@Service
class EtourServerClient {
    private static final Logger logger = LoggerFactory.getLogger(EtourServerClient.class);

    // Simulate a connection interruption or server error
    @Value("${etour.service.simulate-error:false}")
    private boolean simulateEtourError;

    public void sendBannerNotification(Banner banner) throws EtourServiceException {
        if (simulateEtourError) {
            logger.error("Simulating ETOUR server connection interruption for banner ID: {}", banner.getId());
            throw new EtourServiceException("Simulated ETOUR server connection interruption", new RuntimeException("Simulated network error"));
        }
        // In a real scenario, this would involve making an HTTP call to the ETOUR server
        // e.g., using RestTemplate or WebClient
        logger.debug("ETOUR Server: Received notification for banner ID: {} (PointOfRestId: {})", banner.getId(), banner.getPointOfRestId());
        // Simulate some processing time
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new EtourServiceException("ETOUR notification interrupted", e);
        }
    }
}