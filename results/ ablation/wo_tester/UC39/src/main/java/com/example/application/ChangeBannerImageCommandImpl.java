package com.example.application;

import com.example.infrastructure.ImageData;

/**
 * Implementation of the change banner image command.
 */
public class ChangeBannerImageCommandImpl implements ChangeBannerImageCommand {
    private String bannerId;
    private String pointOfRestaurantId;
    private ImageData newImageData;
    
    public ChangeBannerImageCommandImpl(String bannerId, String pointOfRestaurantId, ImageData newImageData) {
        this.bannerId = bannerId;
        this.pointOfRestaurantId = pointOfRestaurantId;
        this.newImageData = newImageData;
    }
    
    public String getBannerId() {
        return bannerId;
    }
    
    public String getPointOfRestaurantId() {
        return pointOfRestaurantId;
    }
    
    public ImageData getNewImageData() {
        return newImageData;
    }
    
    @Override
    public void execute() {
        // The actual execution is delegated to the use case
        // This is just a data holder.
    }
}