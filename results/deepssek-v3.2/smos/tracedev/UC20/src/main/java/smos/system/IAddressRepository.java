package smos.system;

import java.util.List;

/**
 * Interface for address data operations.
 */
public interface IAddressRepository {
    Address findById(String id);
    void deleteById(String id);
    List<Address> findAll();
    boolean hasAssociatedClasses(String addressId);
}