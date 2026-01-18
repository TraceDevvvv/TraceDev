'''
Represents a tourist who can visit sites and provide feedback.
Manages the tourist's current location, visited sites, and given feedbacks.
'''
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Collections;
public class Tourist {
    private String id;
    private String name;
    private Site currentSite; // The site the tourist is currently 'at'
    private List<Site> visitedSites; // List of sites this tourist has visited
    // Map to store feedback given by this tourist, keyed by Site ID
    private Map<String, Feedback> feedbacksGiven;
    /**
     * Constructs a new Tourist object.
     * @param id The unique identifier for the tourist.
     * @param name The name of the tourist.
     */
    public Tourist(String id, String name) {
        this.id = id;
        this.name = name;
        this.visitedSites = new ArrayList<>();
        this.feedbacksGiven = new HashMap<>();
    }
    // --- Getters and Setters ---
    /**
     * @return The unique ID of the tourist.
     */
    public String getId() {
        return id;
    }
    /**
     * @return The name of the tourist.
     */
    public String getName() {
        return name;
    }
    /**
     * @return The site the tourist is currently located at.
     */
    public Site getCurrentSite() {
        return currentSite;
    }
    /**
     * Sets the current site for the tourist.
     * @param currentSite The Site object where the tourist is currently.
     */
    public void setCurrentSite(Site currentSite) {
        this.currentSite = currentSite;
        // Automatically add to visited sites if not already present
        addVisitedSite(currentSite);
    }
    /**
     * @return An unmodifiable list of sites visited by this tourist.
     */
    public List<Site> getVisitedSites() {
        return Collections.unmodifiableList(visitedSites);
    }
    /**
     * @return An unmodifiable map of feedbacks given by this tourist, keyed by site ID.
     */
    public Map<String, Feedback> getFeedbacksGiven() {
        return Collections.unmodifiableMap(feedbacksGiven);
    }
    // --- Business Logic Methods ---
    /**
     * Adds a site to the list of sites visited by this tourist, if not already present.
     * @param site The Site object to add to visited sites.
     */
    public void addVisitedSite(Site site) {
        if (site != null && !visitedSites.contains(site)) {
            this.visitedSites.add(site);
        }
    }
    /**
     * Checks if this tourist has visited a specific site.
     * @param site The Site object to check.
     * @return True if the tourist has visited the site, false otherwise.
     */
    public boolean hasVisitedSite(Site site) {
        return site != null && visitedSites.contains(site);
    }
    /**
     * Checks if this tourist has already given feedback for a specific site.
     * @param site The Site object to check.
     * @return True if feedback has been given for the site, false otherwise.
     */
    public boolean hasGivenFeedback(Site site) {
        return site != null && feedbacksGiven.containsKey(site.getId());
    }
    /**
     * Records the given feedback for a specific site by this tourist.
     * @param site The Site object for which feedback is given.
     * @param feedback The Feedback object to record.
     */
    public void giveFeedback(Site site, Feedback feedback) {
        if (site != null && feedback != null && feedback.getTouristId().equals(this.id) && feedback.getSiteId().equals(site.getId())) {
            feedbacksGiven.put(site.getId(), feedback);
            // Ensure the site is marked as visited
            addVisitedSite(site);
        }
    }
    /**
     * Overrides the equals method to compare Tourist objects based on their ID.
     * @param o The object to compare with.
     * @return True if the objects are equal (have the same ID), false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourist tourist = (Tourist) o;
        return Objects.equals(id, tourist.id);
    }
    /**
     * Overrides the hashCode method consistent with the equals method.
     * @return The hash code of the tourist's ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    /**
     * Provides a string representation of the Tourist object, primarily its name.
     * @return The name of the tourist.
     */
    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}