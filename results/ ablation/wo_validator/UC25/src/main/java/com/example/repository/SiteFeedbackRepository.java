package com.example.repository;

import com.example.model.SiteFeedbackDTO;
import java.util.List;

/**
 * Interface for Site Feedback Repository
 */
public interface SiteFeedbackRepository {
    List<SiteFeedbackDTO> findByLocation(String locationId);
}