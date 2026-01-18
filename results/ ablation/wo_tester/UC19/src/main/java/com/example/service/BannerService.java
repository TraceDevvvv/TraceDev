package com.example.service;

import com.example.model.Banner;
import java.util.List;

/**
 * Service interface for banner operations.
 */
public interface BannerService {
    List<Banner> getBannersByRefreshmentPoint(String pointId);
    boolean deleteBanner(String bannerId);
    Banner getBannerDetails(String bannerId);
}