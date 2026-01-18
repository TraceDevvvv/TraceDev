'''
Represents the data model for a "Point of Rest" (Punto Di Ristoro).
'''
import java.time.LocalDateTime;
import java.util.Objects;
public class PuntoDiRistoro {
    private String id;
    private String name;
    private String address;
    private String type; // e.g., "Restaurant", "Cafe", "Hotel", "Bar"
    private boolean isActive;
    private boolean isFunctional;
    private LocalDateTime lastModified;
    /**
     * Constructs a new PuntoDiRistoro with specified details.
     * The lastModified timestamp is set to the current time upon creation.
     *
     * @param id The unique identifier for the point of rest.
     * @param name The name of the point of rest.
     * @param address The physical address of the point of rest.
     * @param type The type of the point of rest (e.g., "Restaurant", "Cafe").
     * @param isActive True if the point of rest is currently active.
     * @param isFunctional True if the point of rest is currently functional.
     */
    public PuntoDiRistoro(String id, String name, String address, String type, boolean isActive, boolean isFunctional) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.isActive = isActive;
        this.isFunctional = isFunctional;
        this.lastModified = LocalDateTime.now();
    }
    /**
     * Copy constructor for creating an editable copy of an existing PuntoDiRistoro.
     * This is useful to allow modifications without affecting the original object
     * until changes are explicitly saved.
     *
     * @param other The PuntoDiRistoro object to copy.
     */
    public PuntoDiRistoro(PuntoDiRistoro other) {
        this.id = other.id;
        this.name = other.name;
        this.address = other.address;
        this.type = other.type;
        this.isActive = other.isActive;
        this.isFunctional = other.isFunctional;
        // Keep original modification time for the copy until it's actually modified by a setter.
        this.lastModified = other.lastModified;
    }
    // --- Getters ---
    /**
     * Returns the unique identifier of the point of rest.
     * @return The ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the name of the point of rest.
     * @return The name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the address of the point of rest.
     * @return The address.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Returns the type of the point of rest.
     * @return The type.
     */
    public String getType() {
        return type;
    }
    /**
     * Checks if the point of rest is active.
     * @return True if active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }
    /**
     * Checks if the point of rest is functional.
     * @return True if functional, false otherwise.
     */
    public boolean isFunctional() {
        return isFunctional;
    }
    /**
     * Returns the last modification timestamp of the point of rest.
     * @return The LocalDateTime of last modification.
     */
    public LocalDateTime getLastModified() {
        return lastModified;
    }
    // --- Setters (used when editing) ---
    /**
     * Sets the name of the point of rest and updates the last modified timestamp.
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
        this.lastModified = LocalDateTime.now(); // Update modification time on change
    }
    /**
     * Sets the address of the point of rest and updates the last modified timestamp.
     * @param address The new address.
     */
    public void setAddress(String address) {
        this.address = address;
        this.lastModified = LocalDateTime.now();
    }
    /**
     * Sets the type of the point of rest and updates the last modified timestamp.
     * @param type The new type.
     */
    public void setType(String type) {
        this.type = type;
        this.lastModified = LocalDateTime.now();
    }
    /**
     * Sets the active status of the point of rest and updates the last modified timestamp.
     * @param active The new active status.
     */
    public void setActive(boolean active) {
        isActive = active;
        this.lastModified = LocalDateTime.now();
    }
    /**
     * Sets the functional status of the point of rest and updates the last Modified timestamp.
     * @param functional The new functional status.
     */
    public void setFunctional(boolean functional) {
        isFunctional = functional;
        this.lastModified = LocalDateTime.now();
    }
    /**
     * Provides a string representation of the PuntoDiRistoro object.
     * @return A formatted string showing key details.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s - %s (Active: %b, Functional: %b)", id, name, address, isActive, isFunctional);
    }
    /**
     * Compares this PuntoDiRistoro to the specified object.
     * The result is true if and only if the argument is not null and is a PuntoDiRistoro object
     * that has the same 'id' as this object.
     * @param o The object to compare with.
     * @return True if the objects are equal based on their ID, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuntoDiRistoro that = (PuntoDiRistoro) o;
        return id.equals(that.id);
    }
    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     * Based on the 'id' field.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}