package com.example.touristapp.application;

import com.example.touristapp.dataaccess.IFeedbackRepository;
import com.example.touristapp.dataaccess.ISiteRepository;
import com.example.touristapp.dataaccess.NetworkConnectionException;
import com.example.touristapp.domain.Feedback;
import com.example.touristapp.domain.Site;
import com.example.touristapp.dto.SiteFeedbackDto;

import java.util.ArrayList;
import java.util.List;

/**
 * The application service layer for handling business logic related to sites and feedback.
 * This class orchestrates operations between repositories and prepares data for the presentation layer.
 */
public class SiteService {
    private ISiteRepository siteRepository;
    private IFeedbackRepository feedbackRepository;

    /**
     * Constructs a SiteService with the necessary repository dependencies.
     *
     * @param siteRepository An implementation of {@link ISiteRepository} for site data access.
     * @param feedbackRepository An implementation of {@link IFeedbackRepository} for feedback data access.
     */
    public SiteService(ISiteRepository siteRepository, IFeedbackRepository feedbackRepository) {
        this.siteRepository = siteRepository;
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * Retrieves a list of visited sites along with their corresponding feedback for a given tourist.
     * This method implements REQ-005: System uploads list of sites with feedback.
     * The `touristId` parameter implies an authenticated user (Satisfies REQ-003).
     * Satisfies REQ-007: Exit Conditions: Connection to the server ETOUR IS interrupted.
     * // Traceability: Sequence Diagram: m16: List<SiteFeedbackDto> siteFeedbackDtos (return)
     * // Traceability: Sequence Diagram: m19: throws NetworkConnectionException (lost message Service to Controller)
     * // Traceability: Sequence Diagram: m9: throws NetworkConnectionException (lost message SiteRepo to Service)
     * // Traceability: Sequence Diagram: m14: throws NetworkConnectionException (lost message FeedbackRepo to Service)
     *
     * @param touristId The unique identifier of the tourist.
     * @return A list of {@link SiteFeedbackDto} containing combined site and feedback information.
     * @throws NetworkConnectionException If a network connection issue occurs during data retrieval from either repository.
     */
    public List<SiteFeedbackDto> getVisitedSitesWithFeedback(String touristId) throws NetworkConnectionException {
        System.out.println("DEBUG: SiteService: Fetching visited sites with feedback for tourist: " + touristId);
        List<SiteFeedbackDto> siteFeedbackDtos = new ArrayList<>();

        // 1. Get visited sites from SiteRepository
        // Sequence Diagram: Service -> SiteRepo: findVisitedSitesByTourist
        List<Site> siteEntities = siteRepository.findVisitedSitesByTourist(touristId);

        // Loop for each site in siteEntities (as per sequence diagram)
        // This loop processes each site to retrieve its feedback and create a DTO.
        for (Site site : siteEntities) {
            // 2. For each site, get feedback from FeedbackRepository
            // Sequence Diagram: Service -> FeedbackRepo: findFeedbackBySiteAndTourist
            Feedback feedbackEntity = feedbackRepository.findFeedbackBySiteAndTourist(site.getId(), touristId);

            // 3. Convert site and feedback to SiteFeedbackDto
            // Sequence Diagram: note right of Service: Creates SiteFeedbackDto
            siteFeedbackDtos.add(convertToDto(site, feedbackEntity));
        }

        System.out.println("DEBUG: SiteService: Successfully compiled " + siteFeedbackDtos.size() + " SiteFeedbackDtos.");
        return siteFeedbackDtos;
    }

    /**
     * Private helper method to convert a {@link Site} and {@link Feedback} object into a {@link SiteFeedbackDto}.
     * If feedback is null, default values are used for feedback fields in the DTO.
     * // Traceability: Sequence Diagram: m15: Creates SiteFeedbackDto from site and feedbackEntity
     *
     * @param site The site object.
     * @param feedback The feedback object (can be null if no feedback exists for the site/tourist).
     * @return A new {@link SiteFeedbackDto} instance.
     */
    private SiteFeedbackDto convertToDto(Site site, Feedback feedback) {
        String comment = (feedback != null) ? feedback.getComment() : null;
        int rating = (feedback != null) ? feedback.getRating() : 0; // 0 can signify no rating or default

        return new SiteFeedbackDto(
                site.getId(),
                site.getName(),
                site.getLocation(),
                comment,
                rating,
                // Assuming touristId is consistent and known here, or derived from feedback
                feedback != null ? feedback.getTouristId() : "N/A" // Fallback if feedback is null
        );
    }
}