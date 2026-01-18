package com.example.controller;

import com.example.model.Feedback;
import com.example.model.Site;
import com.example.command.EditCommentCommand;
import com.example.command.CommandResult;
import com.example.command.EditCommentCommandHandler;
import com.example.service.FeedbackService;
import com.example.service.SiteService;
import com.example.service.NotificationService;
import java.util.List;

/**
 * Controller orchestrating the feedback editing flow as per sequence diagram.
 */
public class FeedbackController {
    private FeedbackService feedbackService;
    private SiteService siteService;
    private EditCommentCommandHandler commandHandler;
    private NotificationService notificationService;

    public FeedbackController(FeedbackService feedbackService,
                              SiteService siteService,
                              EditCommentCommandHandler commandHandler,
                              NotificationService notificationService) {
        this.feedbackService = feedbackService;
        this.siteService = siteService;
        this.commandHandler = commandHandler;
        this.notificationService = notificationService;
    }

    /**
     * Returns list of all sites (Step 1).
     * @return List of sites.
     */
    public List<Site> viewSiteList() {
        System.out.println("FeedbackController: viewSiteList called");
        return siteService.getAllSites();
    }

    /**
     * Returns site by ID (Step 2).
     * @param siteId The site ID.
     * @return The site, or null if not found.
     */
    public Site selectSite(int siteId) {
        System.out.println("FeedbackController: selectSite called for siteId " + siteId);
        Site site = siteService.getSiteById(siteId);
        if (site == null) {
            System.out.println("Site not found for id " + siteId);
        }
        return site;
    }

    /**
     * Returns feedbacks for a given site (Step 4).
     * @param siteId The site ID.
     * @return List of feedbacks.
     */
    public List<Feedback> viewFeedback(int siteId) {
        System.out.println("FeedbackController: viewFeedback called for siteId " + siteId);
        Site site = siteService.getSiteById(siteId);
        if (site == null) {
            return List.of();
        }
        return feedbackService.getFeedbackBySite(site);
    }

    /**
     * Returns feedback for editing (Step 8).
     * @param feedbackId The feedback ID.
     * @return The feedback, or null if not found.
     */
    public Feedback editCommentForm(int feedbackId) {
        System.out.println("FeedbackController: editCommentForm called for feedbackId " + feedbackId);
        return feedbackService.getFeedbackById(feedbackId);
    }

    /**
     * Submits edit command for validation (Step 11).
     * @param command The edit command.
     * @return CommandResult with validation outcome.
     */
    public CommandResult submitEditComment(EditCommentCommand command) {
        System.out.println("FeedbackController: submitEditComment called for feedback " + command.getFeedbackId());
        CommandResult result = commandHandler.handle(command);
        return result;
    }

    /**
     * Confirms and executes the edit operation (Step 14).
     * @param feedbackId The feedback ID.
     * @return CommandResult of the final execution.
     */
    public CommandResult confirmEditComment(int feedbackId) {
        // In a real scenario, we would retrieve the stored command or re-validate.
        // For simplicity, we assume the edit is already performed in submitEditComment
        // and we just notify.
        System.out.println("FeedbackController: confirmEditComment called for feedbackId " + feedbackId);
        Feedback feedback = feedbackService.getFeedbackById(feedbackId);
        if (feedback != null) {
            notificationService.modifyNotificationSystem(feedback);
            return new CommandResult(true, "Edit confirmed and notification system updated", List.of());
        }
        return new CommandResult(false, "Feedback not found", List.of("Feedback not found"));
    }
}