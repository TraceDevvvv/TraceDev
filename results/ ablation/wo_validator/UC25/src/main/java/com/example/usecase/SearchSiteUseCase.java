package com.example.usecase;

import com.example.model.SiteFeedbackDTO;
import com.example.repository.SiteFeedbackRepository;
import java.util.List;

/**
 * Use Case for searching site feedback
 */
public class SearchSiteUseCase {
    private SiteFeedbackRepository siteFeedbackRepository;
    
    public SearchSiteUseCase(SiteFeedbackRepository siteFeedbackRepository) {
        this.siteFeedbackRepository = siteFeedbackRepository;
    }
    
    public List<SiteFeedbackDTO> execute(String locationId) {
        // Execute the search by calling the repository
        return siteFeedbackRepository.findByLocation(locationId);
    }
}