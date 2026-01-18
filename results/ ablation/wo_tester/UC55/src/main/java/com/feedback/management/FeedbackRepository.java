// Interface for feedback repository operations.
package com.feedback.management;

import java.util.List;

public interface FeedbackRepository {
    List<Feedback> findBySiteAndUser(String siteId, String userId);
    void save(Feedback feedback);
}