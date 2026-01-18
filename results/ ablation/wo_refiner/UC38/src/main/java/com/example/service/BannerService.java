package com.example.service;

import com.example.web.BannerDTO;
import java.util.List;

/**
 * Service interface for banner operations.
 */
public interface BannerService {
    BannerDTO insertBanner(BannerDTO bannerDTO);
    List<BannerDTO> getBannersForPointOfRest(String pointOfRestId);
    int getBannerCountForPointOfRest(String pointOfRestId);
    void rememberBanners(String pointOfRestId); // Added to satisfy requirement REQ-11
}