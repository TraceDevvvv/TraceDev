package com.example.controller;

import com.example.banner.Banner;
import com.example.point.RefreshmentPoint;
import com.example.repository.BannerRepository;
import com.example.security.ImageValidator;
import com.example.connection.ConnectionInterruptedException;
import com.example.connection.ETOURConnectionHandler;
import java.util.List;

/**
 * Controller for modifying banners. Implements requirements REQ-005-013, REQ-012, REQ-015.
 */
public class ModifyBannerController {
    
    private BannerRepository bannerRepository;
    private ImageValidator imageValidator;
    private ETOURConnectionHandler connectionHandler;
    
    /**
     * Constructs the controller with dependencies.
     * @param bannerRepository the repository for banners
     * @param imageValidator the image validator
     * @param connectionHandler the ETOUR connection handler (REQ-015)
     */
    public ModifyBannerController(BannerRepository bannerRepository,
                                 ImageValidator imageValidator,
                                 ETOURConnectionHandler connectionHandler) {
        this.bannerRepository = bannerRepository;
        this.imageValidator = imageValidator;
        this.connectionHandler = connectionHandler;
    }
    
    /**
     * Searches for all refreshment points (REQ-005-013).
     * @return list of refreshment points
     */
    public List<RefreshmentPoint> searchRefreshmentPoints() {
        // In a real system, this would query a database.
        // For demonstration, we return a hard-coded list.
        return List.of(
            new RefreshmentPoint(1, "Main Plaza"),
            new RefreshmentPoint(2, "East Wing"),
            new RefreshmentPoint(3, "West Wing")
        );
    }
    
    /**
     * Retrieves banners for a given refreshment point.
     * @param pointId the refreshment point ID
     * @return list of banners at that point
     */
    public List<Banner> getBannersForPoint(int pointId) {
        return bannerRepository.findBannersByRefreshmentPoint(pointId);
    }
    
    /**
     * Loads a banner for editing.
     * @param bannerId the banner ID
     * @return the Banner object
     */
    public Banner loadBannerForEditing(int bannerId) {
        return bannerRepository.findBannerById(bannerId);
    }
    
    /**
     * Modifies the image of a banner (first step).
     * @param bannerId the banner ID
     * @param newImageData the new image data
     * @return true if the image is valid and modification can proceed, false otherwise
     */
    public boolean modifyBannerImage(int bannerId, byte[] newImageData) {
        return imageValidator.validateImage(newImageData);
    }
    
    /**
     * Confirms and performs the modification (REQ-012).
     * @param bannerId the banner ID
     * @param imageData the new image data (already validated)
     * @return true if the modification was successful, false otherwise
     * @throws ConnectionInterruptedException if connection to ETOUR is lost (REQ-015)
     */
    public boolean confirmModification(int bannerId, byte[] imageData) throws ConnectionInterruptedException {
        // Check connection first
        if (!connectionHandler.checkConnection()) {
            connectionHandler.handleConnectionLoss();
            throw new ConnectionInterruptedException("Connection to ETOUR lost.");
        }
        
        Banner banner = bannerRepository.findBannerById(bannerId);
        if (banner == null) {
            return false;
        }
        
        banner.setImageData(imageData);
        bannerRepository.save(banner);
        return true;
    }
    
    /**
     * Query banners by point.
     * @param pointId the refreshment point ID
     * @return list of banners
     */
    public List<Banner> queryBannersByPoint(int pointId) {
        return bannerRepository.findBannersByRefreshmentPoint(pointId);
    }
    
    /**
     * Retrieve banner data.
     * @param bannerId the banner ID
     * @return the Banner object
     */
    public Banner retrieveBannerData(int bannerId) {
        return bannerRepository.findBannerById(bannerId);
    }
    
    /**
     * Update banner image.
     * @param bannerId the banner ID
     * @param imageData the new image data
     * @return true if successful
     * @throws ConnectionInterruptedException if connection lost
     */
    public boolean updateBannerImage(int bannerId, byte[] imageData) throws ConnectionInterruptedException {
        return confirmModification(bannerId, imageData);
    }
}