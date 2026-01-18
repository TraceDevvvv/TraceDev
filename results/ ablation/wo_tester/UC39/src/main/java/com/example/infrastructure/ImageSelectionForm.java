package com.example.ui;

import com.example.domain.Banner;
import java.util.List;

/**
 * UI form for image selection.
 * As per requirements R-5, R-2, R-3.
 */
public class ImageSelectionForm {
    
    /**
     * Display the image selection form.
     * As per R-5.
     */
    public void displayImageSelectionForm() {
        System.out.println("Displaying image selection form");
    }
    
    /**
     * Render the list of banners.
     * As per R-2 and R-3.
     * @param banners the list of banners
     */
    public void renderBannerList(List<Banner> banners) {
        System.out.println("Rendering banner list with " + banners.size() + " banners");
        for (Banner banner : banners) {
            System.out.println("Banner: " + banner.getBannerId() + " - " + banner.getImageUrl());
        }
    }
    
    /**
     * Select a banner.
     * As per R-3.
     * @param bannerId the banner ID to select
     */
    public void selectBanner(String bannerId) {
        System.out.println("Banner selected: " + bannerId);
    }
    
    /**
     * Request image upload.
     * As per Flow of Events 5.
     */
    public void requestImageUpload() {
        System.out.println("Requesting image upload");
    }
}