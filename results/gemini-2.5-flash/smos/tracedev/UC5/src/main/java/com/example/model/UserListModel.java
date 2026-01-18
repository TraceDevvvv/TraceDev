package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Model component in the MVC pattern for the user list.
 * Holds the current list of users to be displayed.
 */
public class UserListModel {
    private List<User> users;

    /**
     * Constructs a new UserListModel with an empty list of users.
     */
    public UserListModel() {
        this.users = new ArrayList<>();
    }

    /**
     * Returns an unmodifiable list of users currently stored in the model.
     * @return A list of User objects.
     */
    public List<User> getUsers() {
        // Return an unmodifiable list to prevent external modification
        return Collections.unmodifiableList(users);
    }

    /**
     * Sets the list of users in the model.
     * @param users The new list of User objects to set.
     */
    public void setUsers(List<User> users) {
        // Create a new ArrayList to ensure immutability of the 'users' reference
        // and prevent external changes to the original list passed in.
        this.users = new ArrayList<>(users);
        System.out.println("[UserListModel] Users updated. Total users: " + this.users.size());
    }
}