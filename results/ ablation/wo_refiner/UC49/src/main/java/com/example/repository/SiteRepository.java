package com.example.repository;

import com.example.dto.SiteDTO;
import java.util.List;

/**
 * Interface for the Site Repository.
 * Defines the contract for fetching bookmarked sites.
 */
public interface SiteRepository {
    /**
     * Uploads (fetches) bookmarks for a given user.
     * Name changed from getPreferredSites to uploadBookmarks per requirement 7.
     *
     * @param userId the ID of the tourist
     * @return List of SiteDTOs representing the preferred sites
     */
    List<SiteDTO> uploadBookmarks(String userId);
}