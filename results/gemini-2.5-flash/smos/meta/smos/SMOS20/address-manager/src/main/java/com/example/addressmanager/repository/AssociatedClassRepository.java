package com.example.addressmanager.repository;

import com.example.addressmanager.domain.AssociatedClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link AssociatedClass} entities.
 * Extends {@link JpaRepository} to provide standard CRUD operations
 * and querying capabilities for AssociatedClass objects.
 */
@Repository
public interface AssociatedClassRepository extends JpaRepository<AssociatedClass, Long> {

    /**
     * Counts the number of associated classes linked to a specific address ID.
     * This method is crucial for checking if an address has any dependencies
     * before it can be deleted.
     *
     * @param addressId The ID of the address for which to count associated classes.
     * @return The number of associated classes found for the given address ID.
     */
    long countByAddressId(Long addressId);
}