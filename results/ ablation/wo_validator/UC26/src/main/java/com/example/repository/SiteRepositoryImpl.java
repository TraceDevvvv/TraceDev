package com.example.repository;

import com.example.model.Site;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of SiteRepository.
 */
public class SiteRepositoryImpl implements SiteRepository {
    private Map<Integer, Site> siteMap = new HashMap<>();

    public SiteRepositoryImpl() {
        // Initialize with dummy sites
        Site s1 = new Site(1, "Central Park", "New York");
        Site s2 = new Site(2, "Golden Gate", "San Francisco");
        siteMap.put(1, s1);
        siteMap.put(2, s2);
    }

    @Override
    public List<Site> findAll() {
        System.out.println("SiteRepositoryImpl: findAll called");
        return new ArrayList<>(siteMap.values());
    }

    @Override
    public Site findById(int id) {
        System.out.println("SiteRepositoryImpl: finding site by id " + id);
        return siteMap.get(id);
    }
}