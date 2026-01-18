package com.example.insertdelayadmin.repository;

import com.example.insertdelayadmin.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link Administrator} entities.
 * Extends JpaRepository to provide standard CRUD operations and custom query methods.
 */
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, String> {

    /**
     * Finds an Administrator by their username.
     * This method is crucial for authentication purposes to retrieve administrator details
     * based on the provided username during login.
     *
     * @param username The username of the administrator to find.
     * @return An {@link Optional} containing the found Administrator, or empty if no administrator with the given username exists.
     */
    Optional<Administrator> findByUsername(String username);
}