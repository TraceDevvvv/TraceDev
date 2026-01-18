package com.atastaff.absencesystem.repository;

import com.atastaff.absencesystem.model.Class; // Explicitly import model.Class to avoid conflict with java.lang.Class
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA Repository for Class entities.
 * Provides methods for performing CRUD operations and custom queries on Class objects.
 * It extends JpaRepository, specifying the entity type (Class) and the type of its primary key (String).
 */
@Repository
public interface ClassRepository extends JpaRepository<Class, String> {
    // JpaRepository provides standard CRUD operations (save, findById, findAll, delete, etc.)
    // No custom methods are explicitly required by the system design for ClassRepository beyond standard CRUD.
}