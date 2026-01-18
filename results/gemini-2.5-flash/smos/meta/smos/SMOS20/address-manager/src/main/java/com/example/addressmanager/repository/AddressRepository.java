package com.example.addressmanager.repository;

import com.example.addressmanager.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for {@link Address} entities.
 * Extends {@link JpaRepository} to provide standard CRUD operations
 * and querying capabilities for Address objects.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * Retrieves an Address entity by its ID.
     *
     * @param id The ID of the address to retrieve.
     * @return An {@link Optional} containing the Address if found, or empty if not.
     */
    Optional<Address> findById(Long id);

    /**
     * Deletes an Address entity by its ID.
     *
     * @param id The ID of the address to delete.
     */
    void deleteById(Long id);

    /**
     * Checks if an Address entity with the given ID exists.
     *
     * @param id The ID of the address to check.
     * @return true if an address with the given ID exists, false otherwise.
     */
    boolean existsById(Long id);
}