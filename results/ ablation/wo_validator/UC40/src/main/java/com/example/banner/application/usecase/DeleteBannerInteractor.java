package com.example.banner.application.usecase;

import com.example.banner.application.command.DeleteBannerCommand;
import com.example.banner.domain.model.Banner;
import com.example.banner.domain.port.BannerRepository;
import com.example.banner.domain.port.NotificationPort;
import java.util.List;
import java.util.Optional;

/**
 * Interactor implementing the delete banner use case.
 */
public class DeleteBannerInteractor implements DeleteBannerUseCase {
    private BannerRepository bannerRepository;
    private NotificationPort notificationService;
    private volatile boolean cancellationRequested = false;

    public DeleteBannerInteractor(BannerRepository bannerRepository, NotificationPort notificationService) {
        this.bannerRepository = bannerRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void execute(DeleteBannerCommand command) {
        try {
            // Step: Validate authorization & perform deletion
            if (!validateAuthorization(command.getPointOfRestId(), command.getOperatorId())) {
                notificationService.notifyError("Authorization failed");
                throw new RuntimeException("Authorization failed");
            }

            // Connection availability check (simulated)
            if (!isConnectionAvailable()) {
                notificationService.notifyError("Connection lost");
                throw new RuntimeException("Connection lost");
            }

            // Check for cancellation before proceeding
            if (cancellationRequested) {
                abortDeletion();
                return;
            }

            List<Banner> banners = bannerRepository.findByPointOfRestId(command.getPointOfRestId());
            Optional<Banner> targetBanner = banners.stream()
                    .filter(b -> b.getId().equals(command.getBannerId()))
                    .findFirst();

            if (targetBanner.isPresent()) {
                performDeletion(command.getBannerId());
                notificationService.sendConfirmation("Deletion successful");
            } else {
                notificationService.notifyError("Banner not found");
                throw new RuntimeException("Banner not found");
            }
        } catch (Exception e) {
            notificationService.notifyError(e.getMessage());
            throw e;
        }
    }

    private boolean validateAuthorization(String pointOfRestId, String operatorId) {
        // Simplified authorization logic.
        // Assumption: operator must be associated with the pointOfRestId.
        return operatorId != null && !operatorId.trim().isEmpty();
    }

    private void performDeletion(String bannerId) {
        // Step: Permanent deletion of banner
        bannerRepository.deleteById(bannerId);
    }

    private boolean isConnectionAvailable() {
        // Simulate connection check.
        // Assumption: 90% of the time connection is available.
        return Math.random() > 0.1;
    }

    /**
     * Called when a cancellation signal is received.
     */
    public void abortDeletion() {
        notificationService.sendConfirmation("Operation cancelled");
    }

    /**
     * External cancellation trigger (e.g., from controller).
     */
    public void cancelOperation() {
        cancellationRequested = true;
    }
}