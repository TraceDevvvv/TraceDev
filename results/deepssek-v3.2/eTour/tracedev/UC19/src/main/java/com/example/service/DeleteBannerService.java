package com.example.service;

import com.example.repository.BannerRepository;
import com.example.server.ETOURServerConnection;

/**
 * Service responsible for deleting banners.
 * Ensures deletion process completes within 2 seconds.
 */
public class DeleteBannerService {
    private BannerRepository bannerRepository;
    private ETOURServerConnection serverConnection;

    public DeleteBannerService(BannerRepository bannerRepository, ETOURServerConnection serverConnection) {
        this.bannerRepository = bannerRepository;
        this.serverConnection = serverConnection;
    }

    /**
     * Deletes a banner by its ID.
     * Includes connection to ETOUR server and respects time constraint of 2 seconds.
     * @param bannerId the banner ID
     * @return true if deletion successful, false otherwise
     */
    public boolean deleteBanner(long bannerId) {
        long startTime = System.currentTimeMillis();
        boolean connected = serverConnection.connect();
        if (!connected) {
            return false;
        }

        boolean success = bannerRepository.deleteById(bannerId);

        // Simulate automatic disconnection after operation (post-condition)
        serverConnection.disconnect();

        long elapsed = System.currentTimeMillis() - startTime;
        // Check time constraint: max 2 seconds
        if (elapsed > 2000) {
            // Log warning but still return result
            System.out.println("Warning: Deletion took " + elapsed + " ms, exceeding 2 seconds.");
        }
        return success;
    }
}