package com.etour.banner.controller;

import com.etour.banner.dto.BannerUploadRequest;
import com.etour.banner.dto.BannerResponse;
import com.etour.banner.exception.EtourServiceException;
import com.etour.banner.exception.InvalidImageException;
import com.etour.banner.exception.MaxBannersExceededException;
import com.etour.banner.model.Banner;
import com.etour.banner.service.BannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST controller for handling banner-related requests.
 * This controller exposes endpoints for inserting new banners.
 */
@RestController
@RequestMapping("/api/banners")
public class BannerController {

    private static final Logger logger = LoggerFactory.getLogger(BannerController.class);

    private final BannerService bannerService;

    /**
     * Constructs a BannerController with the given BannerService.
     *
     * @param bannerService The service responsible for banner business logic.
     */
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    /**
     * Handles the POST request to insert a new banner.
     *
     * @param pointOfRestId The ID of the Point of Rest to associate the banner with.
     * @param imageFile The image file for the banner.
     * @return A ResponseEntity containing the BannerResponse and HTTP status.
     */
    @PostMapping
    public ResponseEntity<BannerResponse> insertBanner(
            @RequestParam("pointOfRestId") String pointOfRestId,
            @RequestParam("imageFile") MultipartFile imageFile) {

        logger.info("Received request to insert banner for PointOfRestId: {}", pointOfRestId);

        // Create a BannerUploadRequest object from the received parameters
        BannerUploadRequest request = new BannerUploadRequest(pointOfRestId, imageFile);

        try {
            // Call the service layer to handle the banner insertion logic
            Banner insertedBanner = bannerService.insertBanner(request);

            // Create a success response
            BannerResponse response = new BannerResponse(
                    insertedBanner.getId(),
                    "Banner successfully inserted for Point of Rest: " + pointOfRestId,
                    insertedBanner.getImageUrl()
            );
            logger.info("Banner successfully inserted with ID: {}", insertedBanner.getId());
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (InvalidImageException e) {
            logger.warn("Invalid image upload for PointOfRestId {}: {}", pointOfRestId, e.getMessage());
            // Handle invalid image characteristics
            return new ResponseEntity<>(new BannerResponse(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (MaxBannersExceededException e) {
            logger.warn("Max banners exceeded for PointOfRestId {}: {}", pointOfRestId, e.getMessage());
            // Handle exceeding maximum banner limit
            return new ResponseEntity<>(new BannerResponse(null, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        } catch (EtourServiceException e) {
            logger.error("ETOUR service error during banner insertion for PointOfRestId {}: {}", pointOfRestId, e.getMessage(), e);
            // Handle external service connection issues
            return new ResponseEntity<>(new BannerResponse(null, "Connection to ETOUR server interrupted. Please try again later.", null), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("An unexpected error occurred during banner insertion for PointOfRestId {}: {}", pointOfRestId, e.getMessage(), e);
            // Catch any other unexpected errors
            return new ResponseEntity<>(new BannerResponse(null, "An unexpected error occurred: " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}