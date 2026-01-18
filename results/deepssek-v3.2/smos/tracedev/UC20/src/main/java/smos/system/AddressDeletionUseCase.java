package smos.system;

import java.util.List;

/**
 * Use case handling address deletion business logic.
 */
public class AddressDeletionUseCase {
    private IAddressRepository addressRepository;

    public AddressDeletionUseCase(IAddressRepository repository) {
        this.addressRepository = repository;
    }

    /**
     * Executes the address deletion.
     * @param addressId the address identifier
     * @return result of the operation along with updated list of addresses if successful
     */
    public DeleteAddressResult execute(String addressId) {
        // Step 1: Find address
        Address address = addressRepository.findById(addressId);
        if (address == null) {
            return DeleteAddressResult.ADDRESS_NOT_FOUND;
        }

        // Step 2: Check associated classes
        boolean hasAssoc = addressRepository.hasAssociatedClasses(addressId);
        if (hasAssoc) {
            return DeleteAddressResult.ADDRESS_HAS_ASSOCIATED_CLASSES;
        }

        // Step 3: Delete address
        addressRepository.deleteById(addressId);

        // Step 4: Retrieve updated list (as per sequence diagram)
        List<Address> updatedList = addressRepository.findAll();
        // Note: The updated list is not returned directly but is implied in the sequence.
        // We'll rely on the controller to fetch updated list after success.
        return DeleteAddressResult.ADDRESS_DELETED_SUCCESSFULLY;
    }

    // Additional method to get updated addresses list, used after successful deletion.
    public List<Address> getUpdatedAddresses() {
        return addressRepository.findAll();
    }
}