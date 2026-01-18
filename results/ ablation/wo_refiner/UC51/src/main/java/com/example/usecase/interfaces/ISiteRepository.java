package com.example.usecase.interfaces;

import com.example.domain.entity.Site;

/**
 * Port: Interface for site repository operations
 */
public interface ISiteRepository {
    Site saveSite(Site site);
    Site findSiteById(String siteId);
    Site findById(String siteId);
}