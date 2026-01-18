// Tourist.java
package com.etour.visitedsites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a tourist in the system.
 * This class manages the tourist's personal information and the feedback they have provided for sites.
 */
public class Tourist {
    private String touristId;
    private String username;
    private String passwordHash; // Storing a hash for security, not plain password
    private Set<Feedback> feedbacks; // Using a Set to store unique feedback entries

    /**
     * Constructs a new Tourist object.
     *
     * @param touristId The unique identifier for the tourist.
     * @param username The username of the tourist.
     * @param passwordHash The hashed password of the tourist.
     * @throws IllegalArgumentException if any required parameter is null or empty.
     */
    public Tourist(String touristId, String username, String passwordHash) {
        if (touristId == null || touristId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist ID cannot be null or empty.");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (passwordHash == null || passwordHash.trim().isEmpty()) {
            throw new IllegalArgumentException("Password hash cannot be null or empty.");
        }

        this.touristId = touristId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.feedbacks = new HashSet<>(); // Initialize the set of feedbacks
    }

    /**
     * Returns the unique identifier of the tourist.
     *
     * @return The tourist ID.
     */
    public String getTouristId() {
        return touristId;
    }

    /**
     * Returns the username of the tourist.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Authenticates the tourist with the provided password hash.
     * In a real application, this would involve more robust password verification (e.g., salting).
     *
     * @param providedPasswordHash The password hash provided by the user for authentication.
     * @return true if the provided password hash matches the stored hash, false otherwise.
     */
    public boolean authenticate(String providedPasswordHash) {
        return this.passwordHash.equals(providedPasswordHash);
    }

    /**
     * Adds a feedback entry for a site.
     *
     * @param feedback The Feedback object to add.
     * @return true if the feedback was added successfully (i.e., it was not already present), false otherwise.
     * @throws IllegalArgumentException if the feedback is null or its tourist ID does not match this tourist's ID.
     */
    public boolean addFeedback(Feedback feedback) {
        if (feedback == null) {
            throw new IllegalArgumentException("Feedback cannot be null.");
        }
        if (!this.touristId.equals(feedback.getTouristId())) {
            throw new IllegalArgumentException("Feedback tourist ID does not match this tourist.");
        }
        return feedbacks.add(feedback);
    }

    /**
     * Retrieves an unmodifiable list of all feedback entries provided by this tourist.
     *
     * @return An unmodifiable List of Feedback objects.
     */
    public List<Feedback> getFeedbacks() {
        return Collections.unmodifiableList(new ArrayList<>(feedbacks));
    }

    /**
     * Retrieves an unmodifiable set of unique sites for which this tourist has issued feedback.
     *
     * @return An unmodifiable Set of Site objects.
     */
    public Set<Site> getVisitedSitesWithFeedback() {
        return Collections.unmodifiableSet(
            feedbacks.stream()
                     .map(Feedback::getSite)
                     .collect(Collectors.toSet())
        );
    }

    /**
     * Provides a string representation of the Tourist object.
     *
     * @return A string containing the tourist ID and username.
     */
    @Override
    public String toString() {
        return "Tourist ID: " + touristId + ", Username: " + username;
    }

    /**
     * Compares this Tourist object to the specified object. The result is true if and only if
     * the argument is not null and is a Tourist object that represents the same tourist ID as this object.
     *
     * @param o The object to compare this Tourist against.
     * @return true if the given object represents a Tourist equivalent to this tourist, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourist tourist = (Tourist) o;
        return touristId.equals(tourist.touristId);
    }

    /**
     * Returns a hash code for this Tourist object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(touristId);
    }
}