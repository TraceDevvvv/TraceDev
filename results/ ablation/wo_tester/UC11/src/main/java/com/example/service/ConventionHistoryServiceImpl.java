package com.example.service;

import com.example.model.ConventionHistory;
import com.example.model.PointOfRest;
import com.example.repository.ConventionHistoryRepository;
import com.example.cache.CacheManager;
import java.util.List;

/**
 * Implementation of ConventionHistoryService.
 * Incorporates caching to satisfy quality requirement.
 */
public class ConventionHistoryServiceImpl implements ConventionHistoryService {
    private ConventionHistoryRepository conventionHistoryRepository;
    private CacheManager cacheManager;

    public ConventionHistoryServiceImpl(ConventionHistoryRepository repository, CacheManager cacheManager) {
        this.conventionHistoryRepository = repository;
        this.cacheManager = cacheManager;
    }

    @Override
    public List<ConventionHistory> getConventionHistoryForPointOfRest(PointOfRest pointOfRest) {
        // Check cache first
        List<ConventionHistory> cached = cacheManager.getFromCache(pointOfRest.getId());
        if (cached != null) {
            return cached;
        }

        // If not in cache, fetch from repository
        List<ConventionHistory> histories = conventionHistoryRepository.findByPointOfRest(pointOfRest);

        // Store in cache for future requests
        cacheManager.addToCache(pointOfRest.getId(), histories);
        return histories;
    }
}