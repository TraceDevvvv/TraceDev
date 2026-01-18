package com.example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller handling the edit feedback comment use case.
 * Orchestrates the flow from UI to business logic.
 */
public class EditFeedbackCommentController {
    private EditFeedbackCommentInputPort inputPort;
    private SiteRepository siteRepository;
    private FeedbackRepository feedbackRepository;
    private AgencyOperator currentOperator;

    public EditFeedbackCommentController(EditFeedbackCommentInputPort inputPort,
                                         SiteRepository siteRepository,
                                         FeedbackRepository feedbackRepository,
                                         AgencyOperator operator) {
        this.inputPort = inputPort;
        this.siteRepository = siteRepository;
        this.feedbackRepository = feedbackRepository;
        this.currentOperator = operator;
    }

    /**
     * Handles the edit request by delegating to the interactor.
     * @param request DTO containing edit details
     */
    public void handleEditRequest(EditFeedbackCommentRequestDTO request) {
        if (validateSession(request.getOperatorId())) {
            inputPort.execute(request);
        } else {
            System.out.println("Error: Invalid session.");
        }
    }

    /**
     * Retrieves list of all sites.
     * @return DTO containing list of sites
     */
    public SiteListDTO viewSiteList() {
        List<Site> sites = siteRepository.findAll();
        List<SiteDTO> siteDTOs = sites.stream()
                .map(s -> new SiteDTO(s.getId(), s.getName(), s.getAddress()))
                .collect(Collectors.toList());
        return new SiteListDTO(siteDTOs);
    }

    /**
     * Selects a site and activates change function, then retrieves its feedbacks.
     * @param siteId the site ID
     * @return DTO containing feedback list for the site
     */
    public FeedbackListDTO selectSiteAndActivateChange(String siteId) {
        // Activate change function (placeholder for any pre-processing)
        activateChangeFunction();

        Site site = siteRepository.findById(siteId);
        if (site == null) {
            return new FeedbackListDTO(null, "Site not found");
        }

        List<Feedback> feedbacks = feedbackRepository.findAllBySiteId(siteId);
        List<FeedbackDTO> feedbackDTOs = feedbacks.stream()
                .map(f -> new FeedbackDTO(f.getId(), f.getComment(), f.getStatus()))
                .collect(Collectors.toList());

        return new FeedbackListDTO(feedbackDTOs, site.getName());
    }

    /**
     * Submits feedback selection form and retrieves the selected feedback.
     * @param siteId the site ID
     * @param feedbackId the feedback ID
     * @return DTO of the selected feedback
     */
    public FeedbackDTO submitFeedbackSelectionForm(String siteId, String feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId);
        if (feedback == null) {
            return null;
        }
        return new FeedbackDTO(feedback.getId(), feedback.getComment(), feedback.getStatus());
    }

    /**
     * Displays the edit comment form for the given feedback.
     * @param feedbackId the feedback ID
     * @return DTO of the feedback to edit
     */
    public FeedbackDTO displayEditCommentForm(String feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId);
        if (feedback == null) {
            return null;
        }
        return new FeedbackDTO(feedback.getId(), feedback.getComment(), feedback.getStatus());
    }

    /**
     * Cancels an ongoing operation.
     * @param requestId the request ID to cancel
     */
    public void cancelOperation(String requestId) {
        // Log cancellation and clean up if needed.
        System.out.println("Operation " + requestId + " cancelled by user.");
    }

    /**
     * Validates the operator's session.
     * @param operatorId the operator ID
     * @return true if session is valid
     */
    private boolean validateSession(String operatorId) {
        // Simplified validation: check if operator exists and is logged in.
        return currentOperator != null && currentOperator.getId().equals(operatorId);
    }

    /**
     * Creates an edit request DTO from provided parameters.
     * @param operatorId operator ID
     * @param siteId site ID
     * @param feedbackId feedback ID
     * @param newComment new comment text
     * @return the constructed DTO
     */
    EditFeedbackCommentRequestDTO createEditRequestDTO(String operatorId, String siteId,
                                                       String feedbackId, String newComment) {
        return new EditFeedbackCommentRequestDTO(operatorId, siteId, feedbackId, newComment);
    }

    /**
     * Activates the change function (placeholder for any UI state change).
     */
    private void activateChangeFunction() {
        // Could set UI state or log activity.
        System.out.println("Change function activated for site selection.");
    }
}