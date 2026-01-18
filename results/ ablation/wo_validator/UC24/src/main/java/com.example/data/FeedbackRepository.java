package com.example.data;

import com.example.domain.Feedback;
import java.util.List;

/**
 * Repository interface for Feedback data access.
 * Part of the Data Access Layer in the Class Diagram.
 */
public interface FeedbackRepository {
    List<Feedback> findBySiteId(int siteId);
    List<Feedback> findAll();
}