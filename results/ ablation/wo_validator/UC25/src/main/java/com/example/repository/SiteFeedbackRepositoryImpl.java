package com.example.repository;

import com.example.model.SiteFeedbackDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Site Feedback Repository
 */
public class SiteFeedbackRepositoryImpl implements SiteFeedbackRepository {
    
    @Override
    public List<SiteFeedbackDTO> findByLocation(String locationId) {
        // Simulating database call - in real implementation, this would connect to actual data source
        List<SiteFeedbackDTO> feedbackList = new ArrayList<>();
        
        // Sample data for demonstration based on location ID
        if ("LOC001".equals(locationId)) {
            SiteFeedbackDTO feedback1 = new SiteFeedbackDTO();
            feedback1.setSiteId("SITE001");
            feedback1.setRating(4);
            feedback1.setCategory("Facilities");
            feedback1.setComment("Good facilities");
            
            SiteFeedbackDTO feedback2 = new SiteFeedbackDTO();
            feedback2.setSiteId("SITE002");
            feedback2.setRating(5);
            feedback2.setCategory("Cleanliness");
            feedback2.setComment("Very clean");
            
            feedbackList.add(feedback1);
            feedbackList.add(feedback2);
        } else if ("LOC002".equals(locationId)) {
            SiteFeedbackDTO feedback1 = new SiteFeedbackDTO();
            feedback1.setSiteId("SITE003");
            feedback1.setRating(3);
            feedback1.setCategory("Parking");
            feedback1.setComment("Limited parking");
            
            feedbackList.add(feedback1);
        }
        
        return feedbackList;
    }
}