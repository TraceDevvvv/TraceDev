package com.example.repository;

import com.example.model.AddressTeaching;
import java.util.List;

/**
 * Repository interface for AddressTeaching associations.
 * Based on the UML class diagram.
 */
public interface AddressTeachingRepository {
    List<AddressTeaching> findByAddressId(String addressId);
    boolean save(AddressTeaching association);
    boolean delete(AddressTeaching association);
}