package com.example.service;

import com.example.model.Site;
import com.example.repository.SiteRepository;
import java.util.List;

/**
 * Service for site-related operations.
 */
public class SiteService {
    private SiteRepository siteRepo;

    public SiteService(SiteRepository siteRepo) {
        this.siteRepo = siteRepo;
    }

    public List<Site> getAllSites() {
        System.out.println("SiteService: getAllSites called");
        return siteRepo.findAll();
    }

    public Site getSiteById(int id) {
        System.out.println("SiteService: getSiteById called for id " + id);
        return siteRepo.findById(id);
    }
}