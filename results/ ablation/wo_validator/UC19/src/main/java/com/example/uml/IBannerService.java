package com.example.uml;

import java.util.List;

/**
 * Service interface for banner-related operations.
 */
public interface IBannerService {
    List<Banner> getBannersByRefreshmentPoint(String refreshmentPointId);
    boolean deleteBanner(String bannerId);
    boolean confirmBannerExists(String bannerId);
}