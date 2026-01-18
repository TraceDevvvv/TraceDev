'''
This file defines the UserService class, which acts as a mock data source
for user information. In a real application, this service would interact with a database
or a remote API. For this use case, it provides a static list of predefined User objects
and methods to retrieve them, simulating a backend interaction.
'''
package com.chatdev.adminapp.service;
import com.chatdev.adminapp.model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
public class UserService {
    private static List<User> mockUsers;
    static {
        // Initialize with some mock user data
        mockUsers = new ArrayList<>(Arrays.asList(
            new User("John", "Doe", "john.doe@example.com", "123-456-7890", "johnd", "pass123"),
            new User("Jane", "Smith", "jane.smith@example.com", "098-765-4321", "janes", "securepwd"),
            new User("Alice", "Johnson", "alice.j@example.com", "555-123-4567", "alicej", "secret01"),
            new User("Bob", "Williams", "bob.w@example.com", "555-987-6543", "bobw", "password456")
        ));
    }
    /**
     * Retrieves all mock users available in the system.
     * @return A list of User objects.
     */
    public static List<User> getAllUsers() {
        // In a real scenario, this would fetch from a database.
        return new ArrayList<>(mockUsers); // Return a copy to prevent external modification
    }
    /**
     * Retrieves a user by their login username.
     *
     * @param login The login username to search for.
     * @return An Optional containing the User object if found, or an empty Optional otherwise.
     */
    public static Optional<User> getUserByLogin(String login) {
        // Simulate fetching a user by login from a data source.
        return mockUsers.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }
}