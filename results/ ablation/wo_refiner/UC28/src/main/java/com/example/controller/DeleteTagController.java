
package com.example.controller;

import com.example.auth.AuthenticationService;
import com.example.auth.AuthenticationServiceImpl;
import com.example.error.ErrorHandler;
import com.example.error.ErrorMessage;
import com.example.notification.Notification;
import com.example.tag.Tag;
import com.example.tag.TagDto;
import com.example.tag.TagService;
import com.example.utils.Logger;
import com.example.utils.ResponseTimeMonitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller handling HTTP requests and responses.
 * Orchestrates the flow of events from requirements REQ-005.
 */
public class DeleteTagController {
    private AuthenticationService authService;
    private TagService tagService;
    private ErrorHandler errorHandler;
    private Logger logger;
    private ResponseTimeMonitor monitor;

    public DeleteTagController() {
        this.authService = new AuthenticationServiceImpl();
        this.tagService = createTagService();
        this.errorHandler = new ErrorHandler();
        this.logger = Logger.getInstance();
        this.monitor = ResponseTimeMonitor.getInstance();
    }

    private TagService createTagService() {
        com.example.tag.JpaTagRepository repo = new com.example.tag.JpaTagRepository();
        com.example.tag.DeleteTagUseCase useCase = new com.example.tag.DeleteTagUseCase(repo);
        return new TagService(useCase, repo);
    }

    /**
     * Shows the delete tag page.
     * Added to satisfy requirement REQ-006
     * @return page name as String (simulated)
     */
    public String showDeleteTagPage() {
        long startTime = monitor.startTimer("showDeleteTagPage");
        String token = "dummyToken"; // In real scenario, get from request

        if (!authService.validateSession(token)) {
            logger.logAuthenticationFailure(token);
            monitor.measureOperationTime("showDeleteTagPage", startTime);
            return "redirectToLoginPage";
        }

        List<TagDto> tags = tagService.getAllTags();
        // Simulate display
        monitor.measureOperationTime("showDeleteTagPage", startTime);
        return "deleteTagPage";
    }

    /**
     * Handles delete tag request.
     * @param tagIds list of tag ids to delete
     * @return Notification
     */
    public Notification deleteTags(List<Long> tagIds) {
        long startTime = monitor.startTimer("deleteTags");
        String token = "dummyToken";

        // Validate session
        if (!authService.validateSession(token)) {
            logger.logAuthenticationFailure(token);
            monitor.measureOperationTime("deleteTags", startTime);
            return new Notification("Authentication required", false);
        }

        // Call service to delete tags
        Notification notification = tagService.deleteTags(tagIds);
        monitor.measureOperationTime("deleteTags", startTime);
        return notification;
    }

    /**
     * Converts Tag entities to TagDto list.
     * @param tags list of Tag entities
     * @return list of TagDto
     */
    public List<TagDto> convertToDto(List<Tag> tags) {
        // Delegate to service method
        return tagService.convertToDto(tags);
    }

    /**
     * Handles connection interruption.
     * @return ErrorMessage
     */
    public ErrorMessage handleConnectionInterruption() {
        ErrorMessage errorMessage = errorHandler.handleConnectionLoss();
        return errorMessage;
    }

    /**
     * Handles cancel / no selection request.
     * @return Notification
     */
    public Notification handleCancel() {
        return new Notification("Operation cancelled", true);
    }
}
