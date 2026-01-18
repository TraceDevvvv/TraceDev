package com.example.viewstudentdata;

import java.util.List;
import java.util.Collections; // Import for unmodifiable list

/**
 * Represents a Parent in the system.
 * A parent has a unique ID, a username, a password, and a list of student IDs
 * corresponding to their children.
 */
public class Parent {
    private String parentId;
    private String username;
    private String password;
    private List<String> childrenStudentIds;

    /**
     * Constructs a new Parent instance.
     *
     * @param parentId           The unique identifier for the parent.
     * @param username           The parent's username for login.
     * @param password           The parent's password for login.
     * @param childrenStudentIds A list of student IDs associated with this parent's children.
     */
    public Parent(String parentId, String username, String password, List<String> childrenStudentIds) {
        this.parentId = parentId;
        this.username = username;
        this.password = password;
        // Create an unmodifiable list to prevent external modification of the children's list
        this.childrenStudentIds = Collections.unmodifiableList(childrenStudentIds);
    }

    /**
     * Returns the unique identifier of the parent.
     *
     * @return The parent's ID.
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Returns the username of the parent.
     *
     * @return The parent's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the parent.
     * Note: In a real application, passwords should be hashed and never exposed directly.
     *
     * @return The parent's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns an unmodifiable list of student IDs associated with this parent's children.
     *
     * @return A list of children's student IDs.
     */
    public List<String> getChildrenStudentIds() {
        return childrenStudentIds;
    }
}