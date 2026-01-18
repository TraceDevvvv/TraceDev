package com.example.service;

import com.example.model.Feedback;
import com.example.model.Site;
import com.example.repository.IFeedbackRepository;
import com.example.repository.ISiteRepository;

import java.util.List;

/**
 * Application service for managing feedback-related operations.
 * This class orchestrates interactions between repositories and cache.
 */
public class FeedbackService {
    private ISiteRepository siteRepository;
    private IFeedbackRepository feedbackRepository;
    private CacheService cacheService; // Added to satisfy requirement Quality Requirement: The system shall display feedback within a reasonable response time.

    /**
     * Constructs a FeedbackService with required repositories and cache service.
     * @param siteRepo The site repository.
     * @param feedbackRepo The feedback repository.
     * @param cacheService The cache service.
     */
    public FeedbackService(ISiteRepository siteRepo, IFeedbackRepository feedbackRepo, CacheService cacheService) {
        this.siteRepository = siteRepo;
        this.feedbackRepository = feedbackRepo;
        this.cacheService = cacheService;
        System.out.println("FeedbackService: Initialized.");
    }

    /**
     * Retrieves a list of all available sites, utilizing caching for performance.
     * @return A list of Site objects.
     * @throws NetworkConnectionException if there's an issue connecting to the site data source.
     */
    public List<Site> getAllSites() throws NetworkConnectionException {
        System.out.println("FeedbackService: Requesting all sites.");
        List<Site> sites = cacheService.getCachedSites(); // Try to get from cache first

        if (sites != null) {
            System.out.println("FeedbackService: Sites retrieved from cache.");
            return sites; // Cache hit
        } else {
            System.out.println("FeedbackService: Cache miss for sites. Fetching from repository.");
            // Cache miss, fetch from repository
            sites = siteRepository.getAllSites(); // This might throw NetworkConnectionException
            cacheService.storeSites(sites); // Store in cache for future requests
            System.out.println("FeedbackService: Sites fetched from repository and stored in cache.");
            // ref over FS : Data processing/aggregation for response time (sites)
            // (Dummy processing, for example: sorting, filtering, light transformation)
            // For now, no explicit processing for simplicity
            return sites;
        }
    }

    /**
     * Retrieves feedback for a specific site, utilizing caching for performance.
     * @param siteId The ID of the site.
     * @return A list of Feedback objects for the specified site.
     * @throws NetworkConnectionException if there's an issue connecting to the feedback data source.
     */
    public List<Feedback> viewSiteFeedback(String siteId) throws NetworkConnectionException {
        System.out.println("FeedbackService: Requesting feedback for site ID: " + siteId);
        List<Feedback> feedbackList = cacheService.getCachedFeedback(siteId); // Try to get from cache first

        if (feedbackList != null) {
            System.out.println("FeedbackService: Feedback for site " + siteId + " retrieved from cache.");
            return feedbackList; // Cache hit
        } else {
            System.out.println("FeedbackService: Cache miss for feedback for site " + siteId + ". Fetching from repository.");
            // Cache miss, fetch from repository
            feedbackList = feedbackRepository.getFeedbackBySiteId(siteId); // This might throw NetworkConnectionException
            cacheService.storeFeedback(siteId, feedbackList); // Store in cache for future requests
            System.out.println("FeedbackService: Feedback for site " + siteId + " fetched from repository and stored in cache.");
            // ref over FS : Data processing/aggregation for response time (feedback)
            // (Dummy processing, for example: sorting, filtering, light transformation)
            // For now, no explicit processing for simplicity
            return feedbackList;
        }
    }
}