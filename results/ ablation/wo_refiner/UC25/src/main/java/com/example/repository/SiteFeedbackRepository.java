package com.example.repository;

import com.example.model.SiteFeedback;
import java.util.List;

/**
 * Repository interface for SiteFeedback entities.
 */
public interface SiteFeedbackRepository {
    List<SiteFeedback> findByLocationId(String locationId) throws com.example.exception.ConnectionException;
}