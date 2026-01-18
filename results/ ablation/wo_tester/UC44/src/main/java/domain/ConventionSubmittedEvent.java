package domain;

import java.time.LocalDateTime;

/**
 * Event indicating a convention has been submitted.
 */
public interface ConventionSubmittedEvent {
    String getConventionId();
    String getRestaurantId();
    String getAgencyId();
    LocalDateTime getTimestamp();
}