package com.atastaff.absencesystem.repository;

import com.atastaff.absencesystem.model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA Repository for Absence entities.
 * Provides methods for performing CRUD operations on Absence objects.
 * It extends JpaRepository, specifying the entity type (Absence) and the type of its primary key (String).
 */
@Repository
public interface AbsenceRepository extends JpaRepository<Absence, String> {
    // JpaRepository provides standard CRUD operations (save, findById, findAll, delete, etc.)
    // No custom methods are explicitly required by the system design for AbsenceRepository beyond standard CRUD.
}