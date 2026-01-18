package com.etour.banner.controller;

import com.etour.banner.dto.RefreshmentPointDTO;
import com.etour.banner.dto.UploadResponseDTO;
import com.etour.banner.model.Banner;
import com.etour.banner.model.RefreshmentPoint;
import com.etour.banner.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for handling banner-related API requests.
 * This controller exposes endpoints for inserting new banners and
 * retrieving refreshment points. It interacts with the {@link BannerService}
 * to perform business logic.
 */
@RestController
@RequestMapping("/api/banners")
public class BannerController {

    private final BannerService bannerService;

    /**
     * Constructs a new BannerController with the BannerService dependency.
     *
     * @param bannerService The service responsible for banner-related operations.
     */
    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    /**
     * Handles the POST request to insert a new banner.
     * This endpoint expects a multipart file for the image and a refreshment point ID.
     *
     * @param image The banner image file.
     * @param refreshmentPointId The ID of the refreshment point to associate the banner with.
     * @return A {@link ResponseEntity} containing an {@link UploadResponseDTO} with the result of the operation.
     *         Returns HTTP 201 Created on success, or appropriate error status on failure.
     */
    @PostMapping
    public ResponseEntity<UploadResponseDTO> insertBanner(
            @RequestParam("image") MultipartFile image,
            @RequestParam("refreshmentPointId") String refreshmentPointId) {

        Banner insertedBanner = bannerService.insertBanner(image, refreshmentPointId);

        UploadResponseDTO response = new UploadResponseDTO(
                "Banner inserted successfully!",
                insertedBanner.getId(),
                insertedBanner.getImageUrl()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Handles the GET request to retrieve a list of all available refreshment points.
     * This is used by the frontend to allow the operator to select a refreshment point.
     *
     * @return A {@link ResponseEntity} containing a list of {@link RefreshmentPointDTO} objects.
     *         Returns HTTP 200 OK on success.
     */
    @GetMapping("/refreshment-points")
    public ResponseEntity<List<RefreshmentPointDTO>> getRefreshmentPoints() {
        List<RefreshmentPoint> refreshmentPoints = bannerService.getRefreshmentPoints();
        List<RefreshmentPointDTO> dtos = refreshmentPoints.stream()
                .map(rp -> {
                    RefreshmentPointDTO dto = new RefreshmentPointDTO();
                    dto.setId(rp.getId());
                    dto.setName(rp.getName());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}