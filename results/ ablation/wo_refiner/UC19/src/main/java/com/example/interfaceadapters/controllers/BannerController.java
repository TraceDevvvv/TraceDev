
package com.example.interfaceadapters.controllers;

import com.example.application.DeleteBannerCommand;
import com.example.application.DeleteBannerResult;
import com.example.application.DeleteBannerUseCase;
import com.example.domain.Banner;
import com.example.domain.repository.BannerRepository;

import java.util.List;

/**
 * REST controller for banner operations.
 * Implements REQ-007 (getBannersByRefreshmentPoint) and REQ-014 performance requirement.
 */
public class BannerController {
    
    private final DeleteBannerUseCase deleteBannerUseCase;
    private final BannerRepository bannerRepository;
    
    public BannerController(DeleteBannerUseCase useCase, BannerRepository bannerRepository) {
        this.deleteBannerUseCase = useCase;
        this.bannerRepository = bannerRepository;
    }
    
    public DeleteBannerResult deleteBanner(
            String bannerId,
            String agencyOperatorId) {
        
        // Note: In a real application, agencyOperatorId would be validated from authentication context
        DeleteBannerCommand command = new DeleteBannerCommand(bannerId, agencyOperatorId);
        DeleteBannerResult result = deleteBannerUseCase.execute(command);
        
        return result;
    }
    
    public List<Banner> getBannersByRefreshmentPoint(
            String refreshmentPointId) {
        // Implementation for REQ-007
        List<Banner> banners = bannerRepository.findByRefreshmentPointId(refreshmentPointId);
        return banners;
    }
}
