
package com.system.usecase;

import com.system.Banner;
import com.system.BannerManager;
import com.system.Image;
import com.system.RefreshmentPoint;
import com.system.repository.BannerRepository;
import com.system.repository.RefreshmentPointRepository;
import com.system.validation.ValidationResult;
import com.system.ETOURService;
import java.util.List;
import java.util.Optional;

/**
 * Use case for inserting a new banner at a refreshment point.
 */
public class InsertBannerUseCase {
    private BannerManager bannerManager;
    private BannerRepository bannerRepository;
    private RefreshmentPointRepository pointRepository;
    private ETOURService etourService;

    public InsertBannerUseCase(
            BannerManager bannerManager,
            BannerRepository bannerRepository,
            RefreshmentPointRepository pointRepository,
            ETOURService etourService) {
        this.bannerManager = bannerManager;
        this.bannerRepository = bannerRepository;
        this.pointRepository = pointRepository;
        this.etourService = etourService;
    }

    /**
     * Executes the banner insertion process.
     * @param pointId the ID of the refreshment point
     * @param imageData the raw image data
     * @return a Result object indicating success or failure
     */
    public Result execute(String pointId, byte[] imageData) {
        // Check connection to external service (as per sequence diagram)
        if (!etourService.isConnected()) {
            return new Result(false, null, List.of("Connection to ETOUR server lost."));
        }

        // Step 1: Create image and validate
        Image image = new Image(imageData);
        List<ValidationResult> imageValidationResults = validateImage(image);
        boolean allImageValid = imageValidationResults.stream().allMatch(ValidationResult::isValid);
        if (!allImageValid) {
            List<String> errors = imageValidationResults.stream()
                    .filter(r -> !r.isValid())
                    .map(ValidationResult::getErrorMessage)
                    .toList();
            return new Result(false, null, errors);
        }

        // Step 2: Retrieve point and validate banner count
        Optional<RefreshmentPoint> pointOpt = pointRepository.findById(pointId);
        if (pointOpt.isEmpty()) {
            return new Result(false, null, List.of("Refreshment point not found."));
        }
        RefreshmentPoint point = pointOpt.get();
        ValidationResult countValidation = validateBannerCount(point);
        if (!countValidation.isValid()) {
            return new Result(false, null, List.of(countValidation.getErrorMessage()));
        }

        // Step 3: Create and save banner
        Banner banner = saveBanner(point, image);
        return new Result(true, banner, List.of());
    }

    /**
     * Validates an image using the BannerManager.
     */
    public List<ValidationResult> validateImage(Image image) {
        return bannerManager.validateImage(image);
    }

    /**
     * Validates the banner count for a refreshment point.
     */
    public ValidationResult validateBannerCount(RefreshmentPoint point) {
        return bannerManager.validateBannerCount(point);
    }

    /**
     * Creates a banner and saves it via repository.
     */
    public Banner saveBanner(RefreshmentPoint point, Image image) {
        // Generate a simple ID (in real app, use proper ID generation)
        String bannerId = "BNR" + System.currentTimeMillis();
        Banner banner = new Banner(bannerId, image, point);
        bannerRepository.save(banner);
        point.addBanner(banner); // Also add to point's list as per sequence diagram
        return banner;
    }

    /**
     * Result class to encapsulate the execution outcome.
     */
    public static class Result {
        private final boolean success;
        private final Banner banner;
        private final List<String> errors;

        public Result(boolean success, Banner banner, List<String> errors) {
            this.success = success;
            this.banner = banner;
            this.errors = errors;
        }

        public boolean isSuccess() {
            return success;
        }

        public Banner getBanner() {
            return banner;
        }

        public List<String> getErrors() {
            return errors;
        }
    }
}
