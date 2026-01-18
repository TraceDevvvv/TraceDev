package com.example.infrastructure;

import com.example.domain.Address;
import java.util.List;

/**
 * Repository interface for Address entities.
 * Extends the generic SearchRepository for Address type.
 */
public interface AddressRepository extends SearchRepository<Address> {
    /**
     * Finds addresses containing the specified keywords in their searchable content.
     * @param keywords the search keywords.
     * @return a list of matching addresses.
     */
    List<Address> findByKeywords(String keywords);
    // Additional address-specific methods could be added here
}