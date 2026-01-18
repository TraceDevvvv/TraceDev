package com.example.app.repository;

import com.example.app.model.User;
import com.example.app.infrastructure.Database;

/**
 * Concrete implementation of the UserRepository interface.
 * This class corresponds to the 'UserRepositoryImpl' in the UML Class Diagram.
 * It interacts with the Database layer for data persistence.
 */
public class UserRepositoryImpl implements UserRepository {
    // Association: UserRepositoryImpl accesses Database
    private Database database;

    /**
     * Constructor for UserRepositoryImpl.
     * @param database The database access layer.
     */
    public UserRepositoryImpl(Database database) {
        this.database = database;
    }

    /**
     * Finds a User by their unique identifier using the Database.
     * @param id The ID of the user to find.
     * @return The User object if found, null otherwise.
     */
    @Override
    public User findById(String id) {
        System.out.println("UserRepositoryImpl: Finding user by ID: " + id);
        // Sequence Diagram: Repository -> DB : retrieve(id : String)
        return database.retrieve(id);
    }

    /**
     * Saves a User entity using the Database.
     * @param user The User entity to save.
     * @return The saved User entity.
     */
    @Override
    public User save(User user) {
        System.out.println("UserRepositoryImpl: Saving user: " + user.getUsername());
        // Sequence Diagram: Repository -> DB : persist(userEntity : User)
        User persistedUser = database.persist(user);
        // Sequence Diagram: DB --> Repository : persistedUser(User)
        return persistedUser;
    }
}