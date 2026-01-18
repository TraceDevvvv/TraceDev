package com.etour.banner;

import com.etour.image.*;
import com.etour.command.*;
import java.io.File;
import java.util.Scanner;

/**
 * BannerService - orchestrates the banner insertion process.
 * Interacts with validator, command handler, storage and repository.
 */
public class BannerService {
    private BannerInsertionValidator validator;
    private BannerCommandHandler handler;
    private ImageStorageService imageStorageService;
    private BannerRepository bannerRepository;

    public BannerService(BannerInsertionValidator validator, 
                         BannerCommandHandler handler,
                         ImageStorageService imageStorageService,
                         BannerRepository bannerRepository) {
        this.validator = validator;
        this.handler = handler;
        this.imageStorageService = imageStorageService;
        this.bannerRepository = bannerRepository;
    }

    // Main method called by AgencyOperator to insert a banner.
    public Banner insertBanner(String restPointId, File imageFile) {
        System.out.println("BannerService: displaying image selection form (simulated)");
        // Simulate form display and image selection (as per sequence diagram)
        // In real scenario, UI would handle form.
        System.out.println("BannerService: image selected - " + imageFile.getName());

        InsertBannerCommand command = new InsertBannerCommand(restPointId, imageFile, "operator-1");
        Banner banner = null;

        try {
            banner = handler.handle(command);
        } catch (Exception e) {
            System.err.println("BannerService: error - " + e.getMessage());
            return null;
        }

        // Simulate confirmation request
        System.out.println("BannerService: requesting confirmation from operator");
        // In a real application, this would be a UI interaction.
        // For simulation, we assume confirmation is given.
        confirmInsertion();

        notifyInsertion(banner);
        System.out.println("BannerService: insertion successful");
        return banner;
    }

    // Validates an image file using ImageStorageService.
    public boolean validateImage(File imageFile) {
        return imageStorageService.validateImageFormat(imageFile) 
            && imageStorageService.validateImageSize(imageFile);
    }

    // Checks if the rest point has reached its banner limit.
    public boolean checkBannerLimit(String restPointId) {
        int count = bannerRepository.countByRestPointId(restPointId);
        // Assuming max banners per rest point is 5 (should be obtained from RestPoint).
        return count < 5;
    }

    // Persists a banner via repository.
    public void persistBanner(Banner banner) {
        bannerRepository.save(banner);
    }

    // Notifies about a successful insertion (could be logging, event, etc.).
    public void notifyInsertion(Banner banner) {
        System.out.println("Notification: Banner " + banner.getId() + " inserted for rest point " 
            + banner.getAssociatedRestPointId());
    }

    // Simulates operator confirmation.
    public void confirmInsertion() {
        System.out.println("BannerService: operator confirmed insertion.");
    }

    // Simulates operation cancellation.
    public void cancelOperation() {
        System.out.println("BannerService: cleaning up resources.");
        System.out.println("BannerService: operation cancelled.");
    }
}