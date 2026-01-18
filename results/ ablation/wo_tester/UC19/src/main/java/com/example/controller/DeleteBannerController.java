package com.example.controller;

import com.example.dto.BannerDTO;
import com.example.dto.OperationResultDTO;
import com.example.model.Banner;
import com.example.service.BannerService;
import com.example.util.ConnectionManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller orchestrating the banner deletion flow.
 * Implements steps 3-9 of the flow of events.
 */
public class DeleteBannerController {
    private BannerService bannerService;
    private ConnectionManager connectionManager;

    public DeleteBannerController(BannerService bannerService, ConnectionManager connectionManager) {
        this.bannerService = bannerService;
        this.connectionManager = connectionManager;
    }

    /**
     * Handles the initial delete banner request.
     * Corresponds to step 3 in the sequence diagram.
     */
    public OperationResultDTO handleDeleteBannerRequest(String pointId, String bannerId) {
        // Check connection first
        if (!checkConnection()) {
            return createFailureResult("Connection check failed");
        }

        // Fetch banners for the refreshment point
        List<BannerDTO> banners = fetchBannersForRefreshmentPoint(pointId);
        // In a real system, we would now display the list for selection
        System.out.println("Display banner list for selection");
        // Sequence diagram message: Display banner list for selection (return)
        return createSuccessResult("Display banner list for selection");
    }

    /**
     * Fetches banners for a given refreshment point and converts them to DTOs.
     * Corresponds to step 4 in the sequence diagram.
     */
    public List<BannerDTO> fetchBannersForRefreshmentPoint(String pointId) {
        List<Banner> banners = bannerService.getBannersByRefreshmentPoint(pointId);
        List<BannerDTO> dtoList = new ArrayList<>();
        for (Banner banner : banners) {
            BannerDTO dto = new BannerDTO(
                banner.getBannerId(),
                banner.getContentUrl(),
                banner.getStartDate(),
                banner.getEndDate()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Processes deletion confirmation.
     * Corresponds to step 8 in the sequence diagram.
     */
    public OperationResultDTO processDeletionConfirmation(String bannerId, boolean confirmed) {
        if (!confirmed) {
            return new OperationResultDTO(false, "Operation cancelled");
        }

        boolean deleted = bannerService.deleteBanner(bannerId);
        if (deleted) {
            return createSuccessResult("Banner deleted successfully");
        } else {
            return createFailureResult("Deletion failed");
        }
    }

    /**
     * Display banner details with delete option.
     * Sequence diagram message: Display banner details with delete option (return)
     */
    public OperationResultDTO displayBannerDetailsWithDeleteOption(String bannerId) {
        Banner banner = bannerService.getBannerDetails(bannerId);
        if (banner == null) {
            return createFailureResult("Banner not found");
        }
        System.out.println("Display banner details with delete option");
        return createSuccessResult("Display banner details with delete option");
    }

    /**
     * Checks connection via ConnectionManager.
     */
    public boolean checkConnection() {
        return connectionManager.checkConnection();
    }

    /**
     * Added to satisfy requirement Flow of Events 4 (consistency).
     * Same as fetchBannersForRefreshmentPoint but with different name.
     */
    public List<BannerDTO> viewBannerList(String pointId) {
        return fetchBannersForRefreshmentPoint(pointId);
    }

    /**
     * Added to satisfy requirement Flow of Events 5 (consistency).
     * Selects a banner and returns its DTO.
     */
    public BannerDTO selectBanner(String bannerId) {
        Banner banner = bannerService.getBannerDetails(bannerId);
        if (banner == null) {
            return null;
        }
        return new BannerDTO(
            banner.getBannerId(),
            banner.getContentUrl(),
            banner.getStartDate(),
            banner.getEndDate()
        );
    }

    /**
     * Private method to create a success result.
     */
    private OperationResultDTO createSuccessResult(String message) {
        return new OperationResultDTO(true, message);
    }

    /**
     * Private method to create a failure result.
     */
    private OperationResultDTO createFailureResult(String message) {
        return new OperationResultDTO(false, message);
    }
}