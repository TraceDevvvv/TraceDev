package com.example.service;

import com.example.dto.FeedbackDTO;
import com.example.exception.ConnectionException;
import com.example.model.Feedback;
import com.example.repository.FeedbackRepository;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of FeedbackService.
 * Depends on FeedbackRepository to retrieve feedback entities.
 */
public class FeedbackServiceImpl implements FeedbackService {
    private FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository repository) {
        this.feedbackRepository = repository;
    }

    @Override
    public List<FeedbackDTO> getFeedbackForSite(String siteId) throws ConnectionException {
        try {
            List<Feedback> feedbackList = feedbackRepository.findBySiteId(siteId);
            // Convert Feedback entities to FeedbackDTO objects
            return feedbackList.stream()
                    .map(f -> {
                        String formattedTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(f.getTimestamp());
                        return new FeedbackDTO(f.getSiteId(), f.getContent(), f.getRating(), formattedTimestamp);
                    })
                    .collect(Collectors.toList());
        } catch (ConnectionException e) {
            handleConnectionError(e);
            throw e; // re-throw to be handled by controller
        }
    }

    /**
     * Handles connection error as per Exit Conditions requirement.
     * Logs the error or performs recovery actions.
     */
    public void handleConnectionError(ConnectionException exception) {
        System.err.println("Service layer handling connection error: " + exception.getMessage());
        // Additional error handling logic can be added here
    }
}