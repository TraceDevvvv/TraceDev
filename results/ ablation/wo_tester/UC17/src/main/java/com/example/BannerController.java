package com.example;

import java.util.List;

/**
 * Controller handling banner-related operations with session and permission checks.
 */
public class BannerController {
    private UpdateBannerImageUseCase updateBannerImageUseCase;
    private SessionManager sessionManager;
    private PermissionService permissionService;
    private ETOURService etourService;
    private BannerRepository bannerRepository;
    private ErrorHandler errorHandler;

    public BannerController(UpdateBannerImageUseCase updateBannerImageUseCase, 
                          SessionManager sessionManager, 
                          PermissionService permissionService,
                          ETOURService etourService,
                          BannerRepository bannerRepository,
                          ErrorHandler errorHandler) {
        this.updateBannerImageUseCase = updateBannerImageUseCase;
        this.sessionManager = sessionManager;
        this.permissionService = permissionService;
        this.etourService = etourService;
        this.bannerRepository = bannerRepository;
        this.errorHandler = errorHandler;
    }

    public List<RefreshmentPoint> getRefreshmentPoints() {
        if (!etourService.isConnected()) {
            errorHandler.handleError("Server connection lost");
            throw new RuntimeException("Server connection lost");
        }
        
        if (!sessionManager.isLoggedIn()) {
            throw new SecurityException("User not logged in");
        }
        
        return bannerRepository.findAllRefreshmentPoints();
    }

    public List<Banner> getBannersByPoint(String pointId) {
        if (!etourService.isConnected()) {
            errorHandler.handleError("Server connection lost");
            throw new RuntimeException("Server connection lost");
        }
        
        if (!sessionManager.isLoggedIn()) {
            throw new SecurityException("User not logged in");
        }
        
        return bannerRepository.findByRefreshmentPointId(pointId);
    }

    public Banner showEditForm(String bannerId) {
        if (!sessionManager.isLoggedIn()) {
            throw new SecurityException("User not logged in");
        }
        
        if (!permissionService.hasEditingAccess(sessionManager.getCurrentUser())) {
            throw new SecurityException("User lacks editing permission");
        }
        
        return bannerRepository.findById(bannerId);
    }

    public UpdateResult updateBannerImage(BannerUpdateRequest request) {
        try {
            if (!etourService.isConnected()) {
                return new UpdateResult(false, "Server connection lost", null);
            }
            
            if (!sessionManager.isLoggedIn()) {
                return new UpdateResult(false, "User not logged in", null);
            }
            
            if (!permissionService.hasEditingAccess(sessionManager.getCurrentUser())) {
                return new UpdateResult(false, "User lacks editing permission", null);
            }
            
            return updateBannerImageUseCase.execute(request);
            
        } catch (Exception e) {
            errorHandler.handleError("Controller error: " + e.getMessage());
            return new UpdateResult(false, "Operation failed: " + e.getMessage(), null);
        }
    }
}