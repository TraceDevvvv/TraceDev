/*
Manages the collection of User objects, simulating a user archive or database.
Provides methods to add, delete, and retrieve users.
*/
package com.chatdev.deleteuser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
public class UserManager {
    private List<User> users; // Simulates the user archive/database
    /**
     * Constructs a new UserManager with an empty list of users.
     */
    public UserManager() {
        this.users = new ArrayList<>();
        // Add some initial data for demonstration purposes
        users.add(new User("U001", "Alice Smith", "alice.s@example.com"));
        users.add(new User("U002", "Bob Johnson", "bob.j@example.com"));
        users.add(new User("A003", "Administrator Admin", "admin@example.com")); // Administrator user
        users.add(new User("U004", "Charlie Brown", "charlie.b@example.com"));
    }
    /**
     * Adds a new user to the archive.
     *
     * @param user The User object to be added.
     * @return true if the user was added successfully, false if a user with the same ID already exists.
     */
    public boolean addUser(User user) {
        if (user == null || getUserById(user.getId()) != null) {
            return false; // User is null or ID already exists
        }
        return users.add(user);
    }
    /**
     * Deletes a user from the archive based on their user ID.
     *
     * @param userId The ID of the user to be deleted.
     * @return true if the user was found and deleted, false otherwise.
     */
    public boolean deleteUser(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            return false;
        }
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId().equals(userId)) {
                iterator.remove(); // Remove the user
                return true; // User found and deleted
            }
        }
        return false; // User not found
    }
    /**
     * Retrieves a user from the archive by their user ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The User object if found, otherwise null.
     */
    public User getUserById(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            return null;
        }
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null; // User not found
    }
    /**
     * Returns an unmodifiable list of all users currently in the archive.
     *
     * @return A list of all User objects.
     */
    public List<User> getAllUsers() {
        return Collections.unmodifiableList(new ArrayList<>(users)); // Return a defensive copy
    }
}