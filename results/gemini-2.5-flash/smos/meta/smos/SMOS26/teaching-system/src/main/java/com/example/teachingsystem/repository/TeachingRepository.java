package com.example.teachingsystem.repository;

import com.example.teachingsystem.model.Teaching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Teaching entities.
 * Extends JpaRepository to provide standard CRUD operations and
 * custom query capabilities for the Teaching model.
 */
@Repository // Marks this interface as a Spring Data JPA repository
public interface TeachingRepository extends JpaRepository<Teaching, Long> {

    /**
     * Finds a Teaching entity by its unique identifier.
     *
     * @param id The ID of the teaching to find.
     * @return An Optional containing the Teaching entity if found, or an empty Optional otherwise.
     *         This method is inherited from JpaRepository.
     */
    @Override
    Optional<Teaching> findById(Long id);

    /**
     * Deletes a given Teaching entity.
     *
     * @param teaching The Teaching entity to delete.
     *                 This method is inherited from JpaRepository.
     */
    @Override
    void delete(Teaching teaching);

    /**
     * Returns all instances of the Teaching type.
     *
     * @return A list containing all Teaching entities.
     *         This method is inherited from JpaRepository.
     */
    @Override
    List<Teaching> findAll();
}