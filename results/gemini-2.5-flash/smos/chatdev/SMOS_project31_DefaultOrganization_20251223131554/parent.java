/*
 * DOCSTRING: Defines the Parent data model class.
 * This class holds basic parent information, primarily for sending notifications.
 */
package model;
import java.util.Objects;
public class Parent {
    private final int id;
    private final String name;
    private final String email;
    /**
     * Constructor for a Parent.
     *
     * @param id The unique ID of the parent.
     * @param name The name of the parent.
     * @param email The email address of the parent.
     */
    public Parent(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    // --- Getters ---
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    @Override
    public String toString() {
        return "Parent{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return id == parent.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}