/*
Represents a user in the system with an ID, name, and email.
*/
package com.chatdev.deleteuser;
import java.util.Objects;
public class User {
    private String id;
    private String name;
    private String email;
    /**
     * Constructs a new User object.
     * @param id The unique identifier for the user.
     * @param name The full name of the user.
     * @param email The email address of the user.
     */
    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    /**
     * Returns the user's ID.
     * @return The user's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the user's ID.
     * @param id The new ID for the user.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Returns the user's name.
     * @return The user's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the user's name.
     * @param name The new name for the user.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the user's email.
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the user's email.
     * @param email The new email for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Provides a string representation of the User object, useful for display.
     * @return A formatted string containing user details.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email;
    }
    /**
     * Compares this User object to the specified object. The result is true if and only if
     * the argument is not null and is a User object that has the same id (case-sensitive)
     * as this object.
     * @param o The object to compare this User against.
     * @return true if the given object represents a User equivalent to this user, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }
    /**
     * Returns a hash code for this User.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}