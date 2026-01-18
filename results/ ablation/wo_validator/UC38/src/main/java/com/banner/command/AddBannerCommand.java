package com.banner.command;

import com.banner.model.ImageFile;

/**
 * Command for adding a banner.
 */
public class AddBannerCommand {
    private String pointOfRestaurantId;
    private ImageFile imageFile;

    public AddBannerCommand(String pointOfRestaurantId, ImageFile imageFile) {
        this.pointOfRestaurantId = pointOfRestaurantId;
        this.imageFile = imageFile;
    }

    public String getPointOfRestaurantId() {
        return pointOfRestaurantId;
    }

    public ImageFile getImageFile() {
        return imageFile;
    }
}