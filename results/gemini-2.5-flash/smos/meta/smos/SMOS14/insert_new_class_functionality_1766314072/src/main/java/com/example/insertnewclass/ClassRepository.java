package com.example.insertnewclass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for {@link ClassEntity} operations.
 * This interface extends {@link JpaRepository} to provide standard CRUD
 * (Create, Read, Update, Delete) functionalities for ClassEntity objects.
 * Spring Data JPA will automatically generate the implementation for this interface.
 */
@Repository // Marks this interface as a Spring Data repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {

    /**
     * Saves a given class entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param classEntity The class entity to be saved.
     * @return The saved class entity.
     */
    @Override
    ClassEntity save(ClassEntity classEntity);

    /**
     * Retrieves a class entity by its ID.
     *
     * @param id The ID of the class entity to retrieve.
     * @return An {@link Optional} containing the class entity if found, or an empty Optional if not found.
     */
    @Override
    Optional<ClassEntity> findById(Long id);
}