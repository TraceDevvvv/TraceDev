package com.example.repository;

import com.example.model.User;
import java.util.List;

/**
 * Interface defining the contract for user data access.
 * This is part of the Data Access Layer using the Repository Pattern.
 */
public interface UserRepository {
    /**
     * Retrieves all User objects from the underlying data source.
     * @return A list of all User objects.
     */
    List<User> findAllUsers();
}