'''
/**
 * Represents an address in the system, uniquely identified by an ID,
 * and associated with a description and a set of teachings.
 * This class manages the assignment and removal of teachings for a specific address.
 */
'''
import java.util.HashSet;
import java.util.Set;
public class Address {
    private String id;
    private String description;
    private Set<Teaching> assignedTeachings; // Stores teachings associated with this address
    /**
     * Constructs a new Address object.
     * @param id The unique identifier for the address.
     * @param description A descriptive name or location of the address.
     */
    public Address(String id, String description) {
        this.id = id;
        this.description = description;
        this.assignedTeachings = new HashSet<>(); // Initialize with an empty set
    }
    /**
     * Gets the ID of the address.
     * @return The address ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Gets the description of the address.
     * @return The address description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Gets the set of teachings currently assigned to this address.
     * @return A Set of Teaching objects.
     */
    public Set<Teaching> getAssignedTeachings() {
        // Return a new HashSet to prevent direct modification of the internal set from outside.
        return new HashSet<>(assignedTeachings);
    }
    /**
     * Sets the teachings for this address, replacing any previously assigned teachings.
     * @param newTeachings A Set of Teaching objects to be assigned to this address.
     */
    public void setAssignedTeachings(Set<Teaching> newTeachings) {
        // Clear and add all to ensure the internal set accurately reflects the new set.
        this.assignedTeachings.clear();
        if (newTeachings != null) {
            this.assignedTeachings.addAll(newTeachings);
        }
    }
    /**
     * Adds a single teaching to this address.
     * @param teaching The Teaching object to add.
     * @return true if the teaching was added, false if it was already present.
     */
    public boolean addTeaching(Teaching teaching) {
        return this.assignedTeachings.add(teaching);
    }
    /**
     * Removes a single teaching from this address.
     * @param teaching The Teaching object to remove.
     * @return true if the teaching was removed, false if it was not found.
     */
    public boolean removeTeaching(Teaching teaching) {
        return this.assignedTeachings.remove(teaching);
    }
}