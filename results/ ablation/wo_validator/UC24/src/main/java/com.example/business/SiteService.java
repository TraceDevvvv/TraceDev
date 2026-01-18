package com.example.business;

import com.example.data.SiteRepository;
import com.example.domain.Site;
import java.util.List;

/**
 * Service for site‑related operations (Business Logic Layer).
 * Acts as a control class in the Class Diagram.
 */
public class SiteService {
    private SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    public Site getSiteById(int siteId) {
        return siteRepository.findById(siteId);
    }
}