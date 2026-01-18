package com.example.addressmanager.service;

import com.example.addressmanager.domain.Address;
import com.example.addressmanager.exception.AddressDeletionForbiddenException;
import com.example.addressmanager.exception.AddressNotFoundException;
import com.example.addressmanager.repository.AddressRepository;
import com.example.addressmanager.repository.AssociatedClassRepository;
import com.example.addressmanager.util.SmosConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer for managing Address entities.
 * This class encapsulates the business logic for operations related to addresses,
 * particularly the deletion process, including checks for associated entities
 * and interaction with external systems like SMOS.
 */
@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    private final AddressRepository addressRepository;
    private final AssociatedClassRepository associatedClassRepository;
    private final SmosConnectionManager smosConnectionManager;

    /**
     * Constructs an AddressService with necessary repositories and utility.
     * Spring's dependency injection automatically provides these beans.
     *
     * @param addressRepository The repository for Address entities.
     * @param associatedClassRepository The repository for AssociatedClass entities.
     * @param smosConnectionManager The utility for managing SMOS server connection.
     */
    public AddressService(AddressRepository addressRepository,
                          AssociatedClassRepository associatedClassRepository,
                          SmosConnectionManager smosConnectionManager) {
        this.addressRepository = addressRepository;
        this.associatedClassRepository = associatedClassRepository;
        this.smosConnectionManager = smosConnectionManager;
    }

    /**
     * Deletes an address from the system after performing necessary checks.
     * This method ensures that an address can only be deleted if it has no
     * associated classes. It also handles the disconnection from the SMOS server
     * as a post-deletion step.
     *
     * @param addressId The ID of the address to be deleted.
     * @throws AddressNotFoundException If no address with the given ID is found.
     * @throws AddressDeletionForbiddenException If the address has associated classes.
     */
    @Transactional
    public void deleteAddress(Long addressId) {
        logger.info("Attempting to delete address with ID: {}", addressId);

        // 1. Check if the address exists
        // The design specifies findById, but existsById followed by deleteById is also common.
        // For consistency with the sequence diagram, we'll find it first to ensure it exists
        // before proceeding with association checks.
        Address addressToDelete = addressRepository.findById(addressId)
                .orElseThrow(() -> {
                    logger.warn("Address with ID {} not found.", addressId);
                    return new AddressNotFoundException("Address with ID " + addressId + " not found.");
                });

        // 2. Check if the address has associated classes
        long associatedClassesCount = associatedClassRepository.countByAddressId(addressId);
        if (associatedClassesCount > 0) {
            logger.warn("Deletion forbidden for address ID {} due to {} associated classes.", addressId, associatedClassesCount);
            throw new AddressDeletionForbiddenException(
                    "Unable to delete the address, delete the associated classes and try again. " +
                    "Associated classes count: " + associatedClassesCount
            );
        }

        // 3. If no associated classes, delete the address
        addressRepository.deleteById(addressId);
        logger.info("Address with ID {} successfully deleted.", addressId);

        // 4. Disconnect from SMOS server if connected
        if (smosConnectionManager.isConnected()) {
            logger.debug("SMOS server is connected, attempting to disconnect after address deletion.");
            smosConnectionManager.disconnect();
        } else {
            logger.debug("SMOS server is not connected, no disconnection action needed.");
        }
    }
}