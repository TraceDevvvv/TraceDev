package com.etour.banner.service;

import com.etour.banner.exception.BannerLimitExceededException;
import com.etour.banner.exception.ImageValidationException;
import com.etour.banner.exception.ResourceNotFoundException;
import com.etour.banner.model.Banner;
import com.etour.banner.model.RefreshmentPoint;
import com.etour.banner.repository.BannerRepository;
import com.etour.banner.repository.RefreshmentPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing banner-related business logic.
 * This class orchestrates the process of inserting new banners,
 * including validation, image storage, database persistence, and
 * notification to external systems.
 */
@Service
public class BannerService {

    private final BannerRepository bannerRepository;
    private final RefreshmentPointRepository refreshmentPointRepository;
    private final ImageService imageService;
    private final EtourClient etourClient;

    /**
     * Constructs a new BannerService with the necessary dependencies.
     * Spring's dependency injection automatically provides these beans.
     *
     * @param bannerRepository The repository for Banner entities.
     * @param refreshmentPointRepository The repository for RefreshmentPoint entities.
     * @param imageService The service for image handling (validation, storage).
     * @param etourClient The client for interacting with the ETOUR server.
     */
    @Autowired
    public BannerService(BannerRepository bannerRepository,
                         RefreshmentPointRepository refreshmentPointRepository,
                         ImageService imageService,
                         EtourClient etourClient) {
        this.bannerRepository = bannerRepository;
        this.refreshmentPointRepository = refreshmentPointRepository;
        this.imageService = imageService;
        this.etourClient = etourClient;
    }

    /**
     * Inserts a new banner associated with a specified refreshment point.
     * This method performs several steps:
     * 1. Retrieves the refreshment point to ensure it exists.
     * 2. Validates the uploaded image (type, size, dimensions).
     * 3. Checks if the refreshment point has reached its maximum banner limit.
     * 4. Stores the image file and gets its URL.
     * 5. Creates and saves the banner record in the database.
     * 6. Notifies the ETOUR server about the new banner.
     *
     * @param image The {@link MultipartFile} representing the banner image.
     * @param refreshmentPointId The ID of the refreshment point.
     * @return The newly created {@link Banner} object.
     * @throws ResourceNotFoundException if the refreshment point does not exist.
     * @throws ImageValidationException if the image fails validation.
     * @throws BannerLimitExceededException if the refreshment point has too many banners.
     */
    @Transactional
    public Banner insertBanner(MultipartFile image, String refreshmentPointId) {
        // 1. Retrieve RefreshmentPoint
        RefreshmentPoint refreshmentPoint = refreshmentPointRepository.findById(refreshmentPointId)
                .orElseThrow(() -> new ResourceNotFoundException("Refreshment Point not found with ID: " + refreshmentPointId));

        // 2. Validate Image
        imageService.validateImage(image);

        // 3. Check Banner Limit
        checkBannerLimit(refreshmentPoint);

        // 4. Store Image
        String uniqueFileName = imageService.storeImage(image);
        String imageUrl = imageService.getImageUrl(uniqueFileName);

        // 5. Create and Save Banner
        Banner newBanner = new Banner();
        newBanner.setRefreshmentPointId(refreshmentPointId);
        newBanner.setImageUrl(imageUrl);
        newBanner.setUploadDate(LocalDateTime.now());
        Banner savedBanner = bannerRepository.save(newBanner);

        // 6. Notify ETOUR Server
        try {
            etourClient.notifyBannerInsertion(savedBanner, refreshmentPoint);
        } catch (Exception e) {
            // Log the error but do not roll back the transaction for banner insertion
            // as the banner is already saved and image stored.
            // This assumes ETOUR notification is not critical for banner persistence.
            System.err.println("Failed to notify ETOUR server for banner " + savedBanner.getId() + ": " + e.getMessage());
            // Depending on requirements, this could trigger a retry mechanism or a different error handling.
        }

        return savedBanner;
    }

    /**
     * Retrieves a list of all refreshment points available.
     * This method delegates to the EtourClient to fetch the data,
     * and then saves any new refreshment points to the local repository.
     * This ensures the local database is populated with available refreshment points.
     *
     * @return A list of {@link RefreshmentPoint} objects.
     */
    public List<RefreshmentPoint> getRefreshmentPoints() {
        List<RefreshmentPoint> fetchedPoints = etourClient.fetchRefreshmentPoints();
        // Optionally, save/update these in the local database if they don't exist
        // This ensures that the local repository has a copy of the refreshment points
        // for validation and other operations.
        fetchedPoints.forEach(rp -> {
            if (!refreshmentPointRepository.existsById(rp.getId())) {
                refreshmentPointRepository.save(rp);
            }
        });
        return refreshmentPointRepository.findAll();
    }

    /**
     * Checks if the number of banners associated with a refreshment point
     * has exceeded its maximum allowed limit.
     *
     * @param refreshmentPoint The {@link RefreshmentPoint} to check.
     * @throws BannerLimitExceededException if the banner limit is exceeded.
     */
    private void checkBannerLimit(RefreshmentPoint refreshmentPoint) {
        List<Banner> existingBanners = bannerRepository.findByRefreshmentPointId(refreshmentPoint.getId());
        if (existingBanners.size() >= refreshmentPoint.getMaxBanners()) {
            throw new BannerLimitExceededException(
                    "Maximum number of banners (" + refreshmentPoint.getMaxBanners() +
                            ") reached for Refreshment Point: " + refreshmentPoint.getName() +
                            " (ID: " + refreshmentPoint.getId() + ")"
            );
        }
    }
}