package com.example.repository;

import com.example.model.Site;
import com.example.model.SiteFeatures;
import java.util.List;

/**
 * Repository interface for bookmark operations.
 */
public interface IBookmarkRepository {
    boolean removeSiteFromBookmarks(String touristId, String siteId);
    String findSiteIdByFeatures(String touristId, SiteFeatures features);
    List<Site> getBookmarkedSites(String touristId); // REQ-006
}