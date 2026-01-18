package com.example.ui;

import com.example.domain.Banner;
import java.util.List;

/**
 * Form class to handle UI interactions as per sequence diagram.
 * This is a new class to resolve missing participants.
 */
public class Form {
    
    /**
     * Display banner list with selection options
     * As per m11: return from Form to PO
     */
    public void displayBannerListWithSelectionOptions(List<Banner> banners) {
        System.out.println("Displaying banner list with selection options");
        for (Banner banner : banners) {
            System.out.println("Selectable banner: " + banner.getBannerId());
        }
    }
    
    /**
     * Highlight selected banner
     * As per m14: return from Form to PO
     */
    public void highlightSelectedBanner(String bannerId) {
        System.out.println("Highlighting selected banner: " + bannerId);
    }
}