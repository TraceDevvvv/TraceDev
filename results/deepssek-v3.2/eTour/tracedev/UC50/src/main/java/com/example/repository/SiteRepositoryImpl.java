package com.example.repository;

import com.example.entity.Site;
import com.example.entity.Feedback;
import com.example.service.ETOURService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * Implementation of SiteRepository that fetches data via ETOURService.
 * Note: R3-Flow: Fetches/Uploads data via ETOURService
 */
public class SiteRepositoryImpl implements SiteRepository {
    private ETOURService eTourService;

    public SiteRepositoryImpl(ETOURService eTourService) {
        this.eTourService = eTourService;
    }

    @Override
    public List<Site> findSitesWithFeedbackByTouristId(String touristId) {
        // Fetch data via external service
        List<Map<String, Object>> rawData = eTourService.findSitesWithFeedback(touristId);
        List<Site> sites = new ArrayList<>();
        for (Map<String, Object> raw : rawData) {
            String siteId = (String) raw.get("siteId");
            String name = (String) raw.get("name");
            String location = (String) raw.get("location");
            List<Map<String, Object>> feedbackRawList = (List<Map<String, Object>>) raw.get("feedbackList");
            List<Feedback> feedbackList = new ArrayList<>();
            if (feedbackRawList != null) {
                for (Map<String, Object> fRaw : feedbackRawList) {
                    String feedbackId = (String) fRaw.get("feedbackId");
                    Integer rating = (Integer) fRaw.get("rating");
                    String comment = (String) fRaw.get("comment");
                    Date date = (Date) fRaw.get("date");
                    feedbackList.add(new Feedback(feedbackId, rating, comment, date));
                }
            }
            sites.add(new Site(siteId, name, location, feedbackList));
        }
        return sites;
    }
}