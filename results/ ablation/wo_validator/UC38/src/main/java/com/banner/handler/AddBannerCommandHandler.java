package com.banner.handler;

import com.banner.command.AddBannerCommand;
import com.banner.model.Banner;
import com.banner.model.PointOfRestaurant;
import com.banner.repository.BannerRepository;
import com.banner.service.ImageValidatorService;
import com.banner.model.Result;

/**
 * Handler for processing AddBannerCommand.
 */
public class AddBannerCommandHandler {
    private BannerRepository bannerRepository;
    private ImageValidatorService imageValidatorService;
    private PointOfRestaurant pointOfRestaurant;

    public AddBannerCommandHandler(BannerRepository bannerRepository,
                                   ImageValidatorService imageValidatorService,
                                   PointOfRestaurant pointOfRestaurant) {
        this.bannerRepository = bannerRepository;
        this.imageValidatorService = imageValidatorService;
        this.pointOfRestaurant = pointOfRestaurant;
    }

    public Result handle(AddBannerCommand command) {
        // Image validation step (step 5 in sequence diagram)
        boolean imageValid = imageValidatorService.validate(command.getImageFile());
        if (!imageValid) {
            // enable use case "Errored"
            return new Result(false, "Invalid image", null);
        }

        // Check max banner count (step 6)
        int currentCount = bannerRepository.countByPointOfRestaurantId(pointOfRestaurant.getId());
        if (currentCount >= pointOfRestaurant.getMaxBanners()) {
            return new Result(false, "Max banners reached", null);
        }

        // For confirmation, we return a result asking for confirmation.
        // In the sequence diagram, the handler returns a success with a confirmation message.
        // We assume that if the command is already confirmed, we proceed to creation.
        // Since the sequence diagram shows an extra confirmation step, we simulate it by
        // checking if the command is "confirmed". We add a flag in command? Not in UML.
        // Instead we handle confirmation in controller. We'll assume that after confirmation,
        // the controller calls handle again with a flag. Since UML doesn't specify, we'll
        // treat the first call as validation and confirmation request.
        // We'll implement a simple flow: we always create banner (as per sequence diagram, after operator confirmation).
        // The confirmation step is handled in controller. So we assume this handle is called after confirmation.

        // Create new banner (step 7-9 after confirmation)
        // Generate image URL - assume from image file name or ID.
        String imageUrl = "http://example.com/banners/" + java.util.UUID.randomUUID().toString() + ".jpg";
        Banner banner = new Banner(command.getPointOfRestaurantId(), imageUrl);

        // Save to repository (step 10)
        try {
            bannerRepository.save(banner);
        } catch (Exception e) {
            // Simulate connection interruption (last alt block)
            handleConnectionError();
            return new Result(false, "Connection error", null);
        }

        return new Result(true, "Banner created", banner.getId());
    }

    // Method to handle connection error (as per sequence diagram)
    private void handleConnectionError() {
        // Log or handle connection error appropriately
        System.err.println("Connection lost to ETOUR server");
    }
}