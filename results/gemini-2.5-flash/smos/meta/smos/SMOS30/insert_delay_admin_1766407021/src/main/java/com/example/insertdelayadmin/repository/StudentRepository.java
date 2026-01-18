package com.example.insertdelayadmin.repository;

import com.example.insertdelayadmin.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link Student} entities.
 * Extends JpaRepository to provide standard CRUD operations and custom query methods.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    /**
     * Finds a Student by their unique identifier.
     *
     * @param id The ID of the student to find.
     * @return An {@link Optional} containing the found Student, or empty if no student with the given ID exists.
     */
    Optional<Student> findById(String id);

    /**
     * Finds a list of Students whose names contain the given string, ignoring case.
     * This can be used for searching students by name.
     *
     * @param name The string to search for within student names.
     * @return A {@link List} of Students whose names match the search criteria.
     */
    List<Student> findByNameContainingIgnoreCase(String name);
}