package com.ata.repository;

import com.ata.entity.Class;
import java.util.List;

/**
 * JPA implementation of ClassRepository.
 * Simulates database access; in real scenario uses JPA and database.
 */
public class ClassJpaRepository implements ClassRepository {
    /**
     * Simulates SELECT * FROM classes query.
     * Returns a hard-coded list for demonstration.
     * @return List of classes with registry keys.
     */
    @Override
    public List<Class> findAll() {
        // Simulating database result set as per sequence diagram.
        // In a real implementation, this would be a JPA call.
        return List.of(
            new Class("C101", "Mathematics", "Basics of Algebra", "REG_KEY_101"),
            new Class("C102", "Physics", "Newtonian Mechanics", "REG_KEY_102"),
            new Class("C103", "Chemistry", "Organic Chemistry", "REG_KEY_103")
        );
    }

    /**
     * Implements the sequence diagram message: SELECT * FROM classes.
     */
    public List<Class> selectAllFromClasses() {
        return findAll();
    }
}