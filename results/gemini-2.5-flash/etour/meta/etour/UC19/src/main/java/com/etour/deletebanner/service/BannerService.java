package com.etour.deletebanner.service;

import com.etour.deletebanner.model.Banner;
import com.etour.deletebanner.model.RefreshmentPoint;
import com.etour.deletebanner.util.NetworkException;

import java.util.List;

/**
 * Service layer for managing banner-related operations.
 * Acts as an intermediary between the UI controller and the EtourApiClient.
 */
public class BannerService {

    private final EtourApiClient etourApiClient;

    public BannerService() {
        this.etourApiClient = new EtourApiClient();
    }

    /**
     * Retrieves a list of all available refreshment points.
     * @return A list of RefreshmentPoint objects.
     * @throws NetworkException if there's an issue communicating with the ETOUR server.
     */
    public List<RefreshmentPoint> getRefreshmentPoints() throws NetworkException {
        return etourApiClient.getRefreshmentPoints();
    }

    /**
     * Retrieves a list of banners associated with a specific refreshment point.
     * @param refreshmentPointId The ID of the refreshment point.
     * @return A list of Banner objects.
     * @throws NetworkException if there's an issue communicating with the ETOUR server.
     */
    public List<Banner> getBannersForRefreshmentPoint(String refreshmentPointId) throws NetworkException {
        return etourApiClient.getBannersForRefreshmentPoint(refreshmentPointId);
    }

    /**
     * Deletes a specific banner.
     * @param bannerId The ID of the banner to be deleted.
     * @return true if the banner was successfully deleted, false otherwise.
     * @throws NetworkException if there's an issue communicating with the ETOUR server.
     */
    public boolean deleteBanner(String bannerId) throws NetworkException {
        return etourApiClient.deleteBanner(bannerId);
    }
}