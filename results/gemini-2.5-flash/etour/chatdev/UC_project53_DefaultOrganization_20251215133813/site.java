'''
Represents a tourist attraction or site.
Stores information about the site and a record of feedback it has received.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Collections;
public class Site {
    private String id;
    private String name;
    private String description;
    private List<Feedback> feedbacks; // List to store feedback received for this site
    /**
     * Constructs a new Site object.
     * @param id The unique identifier for the site.
     * @param name The name of the site.
     * @param description A brief description of the site.
     */
    public Site(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.feedbacks = new ArrayList<>();
    }
    // --- Getters ---
    /**
     * @return The unique ID of the site.
     */
    public String getId() {
        return id;
    }
    /**
     * @return The name of the site.
     */
    public String getName() {
        return name;
    }
    /**
     * @return The description of the site.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @return An unmodifiable list of feedback received for this site.
     */
    public List<Feedback> getFeedbacks() {
        return Collections.unmodifiableList(feedbacks);
    }
    /**
     * Adds a feedback to this site's list of received feedbacks.
     * @param feedback The Feedback object to add.
     */
    public void addFeedback(Feedback feedback) {
        if (feedback != null) {
            this.feedbacks.add(feedback);
        }
    }
    /**
     * Overrides the equals method to compare Site objects based on their ID.
     * @param o The object to compare with.
     * @return True if the objects are equal (have the same ID), false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return Objects.equals(id, site.id);
    }
    /**
     * Overrides the hashCode method consistent with the equals method.
     * @return The hash code of the site's ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    /**
     * Provides a string representation of the Site object, primarily its name.
     * @return The name of the site.
     */
    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}