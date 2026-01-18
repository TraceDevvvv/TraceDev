package com.example.service;

import com.example.dto.FeedbackDTO;
import com.example.exception.ConnectionException;
import java.util.List;

/**
 * Interface for feedback service operations.
 */
public interface FeedbackService {
    /**
     * Retrieves all feedback for a given site ID.
     * @param siteId the site identifier
     * @return list of FeedbackDTO objects
     * @throws ConnectionException if connection to the server is interrupted
     */
    List<FeedbackDTO> getFeedbackForSite(String siteId) throws ConnectionException;
}