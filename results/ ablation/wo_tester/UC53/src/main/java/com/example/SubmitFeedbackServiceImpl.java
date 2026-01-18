package com.example;

import java.util.Date;
import java.util.UUID;

/**
 * Implementation of SubmitFeedbackService.
 */
public class SubmitFeedbackServiceImpl implements SubmitFeedbackService {
    private FeedbackRepository feedbackRepository;
    private TouristSiteRepository touristSiteRepository;
    private ValidationService validationService;

    public SubmitFeedbackServiceImpl(FeedbackRepository feedbackRepository,
                                     TouristSiteRepository touristSiteRepository,
                                     ValidationService validationService) {
        this.feedbackRepository = feedbackRepository;
        this.touristSiteRepository = touristSiteRepository;
        this.validationService = validationService;
    }

    @Override
    public ResultDTO submitFeedback(FeedbackForm formData) {
        String touristId = formData.getTouristId();
        String siteId = formData.getSiteId();

        // 1. Validate tourist card
        if (!validateTouristCard(touristId)) {
            return new ResultDTO(false, "Invalid tourist card.", "InvalidTouristCard");
        }

        // 2. Validate site presence
        if (!validateSitePresence(siteId)) {
            return new ResultDTO(false, "Invalid site.", "InvalidSite");
        }

        // 3. Check duplicate feedback
        if (feedbackRepository.existsByTouristAndSite(touristId, siteId)) {
            return new ResultDTO(false, "Feedback already submitted for this site.", "FeedbackAlreadyReleased");
        }

        // 4. Validate feedback data
        if (!validationService.validateFeedback(formData)) {
            return new ResultDTO(false, "Invalid feedback data.", "Errored");
        }

        // 5. Check data sufficiency (step 10 in flow)
        if (!validationService.isDataSufficient(formData)) {
            return new ResultDTO(false, "Insufficient data.", "Errored");
        }

        // 6. Save feedback
        String feedbackId = UUID.randomUUID().toString();
        FeedbackDTO dto = new FeedbackDTO(feedbackId, touristId, siteId,
                formData.getRating(), formData.getComment(), new Date());
        boolean saveSuccess = feedbackRepository.save(dto);
        if (!saveSuccess) {
            return new ResultDTO(false, "Server connection error while saving feedback.", "ServerConnectionError");
        }

        // 7. Add to visited sites
        boolean addSuccess = touristSiteRepository.addToVisitedSites(touristId, siteId);
        if (!addSuccess) {
            return new ResultDTO(false, "Server connection error while updating visited sites.", "ServerConnectionError");
        }

        return new ResultDTO(true, "Feedback submitted successfully.", null);
    }

    @Override
    public boolean validateTouristCard(String touristId) {
        // Delegate to repository
        return touristSiteRepository.getTouristCardStatus(touristId);
    }

    @Override
    public boolean validateSitePresence(String siteId) {
        // Delegate to repository
        return touristSiteRepository.isValidSite(siteId);
    }

    // Sequence diagram messages
    public void verifyDuplicateFeedback() {
        System.out.println("Service: Verifying duplicate feedback.");
    }

    public ResultDTO errorResultDTO() {
        return new ResultDTO(false, "Generic error.", "Errored");
    }
}