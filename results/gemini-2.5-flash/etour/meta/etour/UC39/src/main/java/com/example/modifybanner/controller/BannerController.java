package com.example.modifybanner.controller;

import com.example.modifybanner.dto.BannerResponse;
import com.example.modifybanner.model.Banner;
import com.example.modifybanner.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for banner operations.
 * Handles HTTP requests related to viewing and modifying banner ads.
 */
@RestController
@RequestMapping("/api/banners")
public class BannerController {

    private final BannerService bannerService;

    /**
     * Constructor for BannerController, injecting the BannerService.
     *
     * @param bannerService The service layer for banner business logic.
     */
    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    /**
     * Endpoint to view a list of banners associated with a specific restaurant.
     * Corresponds to step 2 of the use case: "View the list of banner associated with the point of rest."
     *
     * @param restaurantId The ID of the restaurant whose banners are to be retrieved.
     * @return A ResponseEntity containing a list of BannerResponse objects.
     */
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<BannerResponse>> getBannersByRestaurant(@PathVariable Long restaurantId) {
        List<Banner> banners = bannerService.getBannersByRestaurantId(restaurantId);
        List<BannerResponse> bannerResponses = banners.stream()
                .map(BannerResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bannerResponses);
    }

    /**
     * Endpoint to modify the image of an existing banner.
     * Corresponds to steps 3-8 of the use case:
     * 3. Select a banner from the list and enter the editing functionality.
     * 4. Displays a form for the selection of an image.
     * 5. Select a picture and send the request to change the system.
     * 6. Check the characteristics of the inserted and asks for confirmation of the change of the banner.
     *    In the event that the inserted image is not valid, enable the use case Errored.
     * 7. Confirmation of the transaction change.
     * 8. Bookmark this new image for the selected banner.
     *
     * @param bannerId  The ID of the banner to be modified.
     * @param imageFile The new image file to upload for the banner.
     * @return A ResponseEntity containing the updated BannerResponse object.
     * @throws IOException If there's an issue with file operations during image storage.
     */
    @PutMapping("/{bannerId}/image")
    public ResponseEntity<BannerResponse> modifyBannerImage(
            @PathVariable Long bannerId,
            @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        // The BannerService handles validation, storage, and database updates.
        Banner updatedBanner = bannerService.modifyBannerImage(bannerId, imageFile);

        // Notify successful modification (Exit condition)
        return new ResponseEntity<>(BannerResponse.fromEntity(updatedBanner), HttpStatus.OK);
    }
}